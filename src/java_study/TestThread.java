package java_study;

class RunnableDemo implements Runnable {
   private Thread t;
   private String threadName;
   
   RunnableDemo( String name) {
      threadName = name;
      System.out.println("Creating " +  threadName );
   }
   
   public void run() {
      System.out.println("Running " +  threadName );
      try {
         for(int i = 4; i > 0; i--) {
            System.out.println("Thread: " + threadName + ", " + i);
            // Let the thread sleep for a while.
            Thread.sleep(1000);
         }
      } catch (InterruptedException e) {
         System.out.println("Thread " +  threadName + " interrupted.");
      }
      System.out.println("Thread " +  threadName + " exiting.");
   }
   
   public void start () {
      System.out.println("Starting " +  threadName );
      if (t == null) {
         t = new Thread (this, threadName);
         t.start ();
      }
   }
}

// The second option is to extend Thread class
class ThreadDemo extends Thread {

	private String threadName;

	ThreadDemo(String name) {
		threadName = name;
		System.out.println("Creating " + threadName);
	}

	public void run() {
		System.out.println("Running " + threadName);
		try {
			for (int i = 4; i > 0; i--) {
				System.out.println("Thread: " + threadName + ", " + i);
				// Let the thread sleep for a while.
				Thread.sleep(50);
			}
		} catch (InterruptedException e) {
			System.out.println("Thread " + threadName + " interrupted.");
		}
		System.out.println("Thread " + threadName + " exiting.");
	}
}


public class TestThread {

   public static void main(String args[]) {
      RunnableDemo R1 = new RunnableDemo( "Thread-1");
      R1.start();
      
      RunnableDemo R2 = new RunnableDemo( "Thread-2");
      R2.start();
      
      ThreadDemo T1 = new ThreadDemo( "Thread-extended-1");
      T1.start();
     
      ThreadDemo T2 = new ThreadDemo( "Thread-extended-2");
      T2.start();
   }   
}


/*
 * Mstep:
 * Records about Concurrency patterns in PreparedTerminal integration test
 *
 * #1.  java.util.concurrent.CountDownLatch
 * 
 * A synchronization aid that allows one or more threads to wait
 * untila set of operations being performed in other threads
 * completes.
 *
 * A CountDownLatch is initialized with a given count.The await
 * methods block until the current count reacheszero due to
 * invocations of the countDown method, after whichall waiting threads
 * are released and any subsequent invocations of await return
 * immediately. This is a one-shot phenomenon-- the count cannot be
 * reset. If you need a version that resets thecount, consider using a
 * CyclicBarrier.
 *
 * A CountDownLatch is a versatile synchronization tooland can be used
 * for a number of purposes. A CountDownLatch initialized with a count
 * of one serves as asimple on/off latch, or gate: all threads
 * invoking awaitwait at the gate until it is opened by a thread
 * invoking countDown. A CountDownLatch initialized to Ncan be used to
 * make one thread wait until N threads havecompleted some action, or
 * some action has been completed N times. *
 *
 * A useful property of a CountDownLatch is that itdoesn't require
 * that threads calling countDown wait forthe count to reach zero
 * before proceeding, it simply prevents anythread from proceeding
 * past an await until allthreads could pass.
 *
 * IMS4: In PreparedTerminal test it is used
 * for purpose of handling not finished communicaiton channels.
 *    latch = new CountDownLatch(channels.size());
 *    testTerminalSucceded() -> latch.countDown();
 *    // when a channel got stuck
 *    if (!latch.await(config.timeout, MILLISECONDS)) -> fail
 *    
 * 
 * #2. java.util.concurrent.atomic
 *
 * A small toolkit of classes that support lock-free
 * thread-safeprogramming on single variables. In essence, the classes
 * in thispackage extend the notion of volatile values, fields,
 * andarray elements to those that also provide an atomic conditional
 * updateoperation of the form: boolean compareAndSet(expectedValue,
 * updateValue);
 *
 * This method (which varies in argument types across
 * differentclasses) atomically sets a variable to the updateValue if
 * itcurrently holds the ex * pectedValue, reporting true
 * onsuccess. The classes in this package also contain methods to get
 * andunconditionally set values, as well as a weaker conditional
 * atomicupdate operation weakCompareAndSet described below.
 * 
 * The specifications of these methods enable implementations to employ
 * efficient machine-level atomic instructions that are availableon
 * contemporary processors. However on some platforms, support
 * mayentail some form of internal locking. Thus the methods are
 * notstrictly guaranteed to be non-blocking --a thread may block
 * transiently before performing the operation.
 * 
 * Instances of classes java.util.concurrent.atomic.AtomicBoolean,
 * java.util.concurrent.atomic.AtomicInteger,
 * java.util.concurrent.atomic.AtomicLong, and
 * java.util.concurrent.atomic.AtomicReferenceeach provide access and
 * updates to a single variable of thecorresponding type. Each class
 * also provides appropriate utilitymethods for that type. For
 * example, classes AtomicLong and AtomicInteger provide atomic
 * increment methods. Oneapplication is to generate sequence numbers
 *
 * IMS4: In PreparedTerminal test is used
 * just for counting the running channels.
 *    AtomicInteger testTerminalsCount
 *    testTerminalRegister() 
 *     -> testTerminalsCount.incrementAndGet() 
 *
 *    AtomicLong count
 *    run()
 *     -> count.incrementAndGet();
 *
 *
*/
