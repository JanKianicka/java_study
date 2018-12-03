package java_study;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Context {
	public volatile double[] OutListEncaps;
	Context(double[] OutListArg){
		OutListEncaps = OutListArg;
		OutListEncaps[0] = 1.5;
	}
}

class ThreadArray extends Thread {
	private Thread t;
	double[] InList;
	double[] OutList;
	int[] indexes;
	double val;
	private String threadName;
	Context Con;
	
	ThreadArray(String ThreadNameArg, double[] InListArg, double[] OutListArg, int[] indexes, double val, Context ConArg){
		InList = InListArg;
		OutList = OutListArg;
		threadName = ThreadNameArg;
		this.indexes = indexes;
		this.val = val;
		this.Con = ConArg;
	}
	
	public void run() {
		System.out.println("Value of 2-nd value:"+InList[1]);
		System.out.println("Value of OutListCon, inside threads: " + Con.OutListEncaps[0]);
//		OutList[0] = 1.1;
//		Con.OutListEncaps[0] = 2.5;
		System.out.println("Value of OutListCon, inside threads 2: " + Con.OutListEncaps[0]);
		// We modify per thread some parts of the shared array
//		synchronized (this) {
			for (int i : this.indexes) {
				Con.OutListEncaps[i] = this.val;
			}

			try {
				System.out.println("Sleeping");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		}
		System.out.println("Value of OutListCon, inside threads:" + Arrays.toString(OutList));
	}
	
	 public void start () {
	      System.out.println("Starting " +  threadName );
	      if (t == null) {
	         t = new Thread (this, threadName);
	         t.start ();
	      }
	 }

}

class RunnableArray implements Runnable {
	private Thread t;
	private String threadName;
	private volatile double[] OutList;
	int[] indexes;
	double val;
	
	RunnableArray(String name, double[] OutListArg, int[] indexes, double val) {
		threadName = name;
		System.out.println("Creating " + threadName);
		this.OutList = OutListArg;
		System.out.println("Runnable value:" + this.OutList[0]);
		this.indexes = indexes;
		this.val = val;
	}

	public void run() {
		//this.OutList[0] = 3.5;
		for (int i : this.indexes) {
			this.OutList[i] = this.val;
		}		
		System.out.println("Runnable value:" + this.OutList[0]);
		try {
			System.out.println("Sleeping");
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void start() {
		System.out.println("Starting " + threadName);
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
	
	public void join() {
		if (this.t != null) {
			try {
				this.t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}


/**
 * Test of multithreading with CallAble
 * 
 *
 */

class FactorialTask implements Callable<Integer>{
	int number;
	public FactorialTask(int input) {
		this.number = input;
	}
	
	public Integer call() {
		int fact=1;
				
		for(int count = number; count>1; count --) {
			fact = fact*number;
		}
		return fact;
	}
}


public class TestThreadArray {

	public static void main(String args[]) throws InterruptedException, ExecutionException {
		double[] InList = { 1.1, 1.2, 1.3, 1.4 };
		double[] OutList = { 0.0, 0.0, 0.0, 0.0 };
		Context Con = new Context(OutList);
		System.out.println("== ThreadArray class: Value of OutList, before threads: " + OutList[0]);
		
		int[] a = {0,1};
		int[] b = {2,3};
		ThreadArray ThreadArray_1 = new ThreadArray("Thread-1", InList, OutList, a, 120, Con);
		ThreadArray ThreadArray_2 = new ThreadArray("Thread-2", InList, OutList, b, 360, Con);
		ThreadArray_1.start(); // wait for threads to end
		ThreadArray_1.run();
		ThreadArray_2.start();
		ThreadArray_2.run();
		try {
			ThreadArray_1.join();
		} catch (Exception e) {
			System.out.println("Interrupted");
		}
		System.out.println("Value of OutList, after threads: " + OutList[0]);
		System.out.println("Value of OutList, after threads direct: " + Con.OutListEncaps[0]);
		System.out.println("Value of OutList: " + Arrays.toString(OutList));
		System.out.println("== Accomplished ThreadArrau class.");
		
//      Using just thread has executed run one after another , no concurrent run - therefore not applicable 
//		for our purpose.
		System.out.println("== RunnableArray class.");
		RunnableArray R1 = new RunnableArray( "Thread-1", OutList, a, 120);
		RunnableArray R2 = new RunnableArray( "Thread-1", OutList, b, 120);
	    R1.start();
	    R2.start();
	    // Lets try to put here join block as was in C
		try {
			R1.join();
			R2.join();
		} catch (Exception e) {
			System.out.println("Interrupted");
		}	    
	    
//		try {
//			Thread.sleep(50);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
// 		So there is issue with synchronization.
		
	    System.out.println("Value of OutList, after runable threads: " + OutList[0]);
	    System.out.println("Value of OutList, after runable threads: " + Arrays.toString(OutList));
//      This has worked out, sleeping just these 5 seconds.
	    
// 		So it means call of join has resolved the problem.
	    System.out.println("== Accomplished RunnableArray class.");
	    
	    System.out.println("== Callable FactorialTask class.");
	    // Test of CallAble
	    FactorialTask task = new FactorialTask(5);
	    Callable<Integer> callable = new FactorialTask(5);
	    
	    System.out.println("Input into callable for factorial: "+task.number);
	    ExecutorService pool = Executors.newFixedThreadPool(4);
	    Future<Integer> future = pool.submit(task);
	    int res = future.get();
	    System.out.println("Result using FactorialTask: "+res);
	    
	    // Now lets try to submit another job into the ThreadPool
	    Future<Integer> future2_callable = pool.submit(callable);
	    System.out.println("Result using callable directly: "+ future2_callable.get());
	    // We have to shutdown the pool in order to terminate the program.
	    pool.shutdown();
	    System.out.println("== Accomplished FactorialTask class.");
	}
}
