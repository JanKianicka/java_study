package java_study;

/**
 * 
 * Abstract method declaring the method which we 
 * will use in the parametrized and inherited classes 
 */

abstract class DotCalculus {
	public abstract void evaluateDot();
	public abstract int getDot();
}



class DotCalculus1D extends DotCalculus {
	
	private int dot;
	private int[] vect_A;
	private int[] vect_B;
	
    //Function that return
    //dot product of two vector array.
	public DotCalculus1D(int vect_A_in[], int vect_B_in[]) {
		this.vect_A = vect_A_in;
		this.vect_B = vect_B_in;
	}
	
	@Override
	public void evaluateDot() {

		int product = 0;

		// Loop for calculate cot product
		for (int i = 0; i < this.vect_A.length; i++)

			product = product + this.vect_A[i] * this.vect_B[i];
		this.dot = product;
	}
	
	@Override
	public int getDot() {
		return this.dot;
	}
}

/* Parametrized class */
class BoxDot<T extends DotCalculus, R extends Result> {
	private T t;
	private R res;

	public BoxDot(T tIn, R resIn) {
		this.t = tIn;
		this.res = resIn;
	}
	
	public void addType(T t) {
		this.t = t;
	}

	public T getType() {
		return t;
	}
	
	public void evaluateDot() {
		this.t.evaluateDot();
		this.res.storeNewRes(this.t.getDot());
	}
	
	public void printCurrentResult() {
		this.res.printResult();
	}
}

/* 
 * Let us try to use one more type and encapsulate results into
 * the the result object.
 * */
class Result<T> {
	private T res;
	
	public Result(T tIn) {
		this.res = tIn;
	}
	
	public void storeNewRes(T tIn) {
		this.res = tIn;
	}
	
	public void printResult() {
		System.out.println("Result is:" + this.res.toString());
	}
}



class GenericMethodRun {
	// execute evaluateDot on a generic object which has this void method
	public static <E extends DotCalculus> void evaluateDot(E inObj) {
		inObj.evaluateDot();
	}
}




public class GenericClassTest_Calc {

	public static void main(String[] args) {

		int a[][]={{1,1,1},{2,2,2},{3,3,3}};
		int b[][]={{1,1,1},{2,2,2},{3,3,3}};
		int vect_A[] = { 3, -5, 4 };
		int vect_B[] = { 2, 6, 5 };
		int vect_A1[] = { 3, -5, -4 };
		int vect_B1[] = { 2, 6, -10 };
		int vect_A2[] = { 0, -5, -4 };
		int vect_B2[] = { 0, 6, -10 };		
		int c[][]=new int[3][3];
		int vect_C[] = new int[3];
		
		DotCalculus1D dotCalculus1D = new DotCalculus1D(vect_A, vect_B);
		/* Standard implementation without generic types */
		dotCalculus1D.evaluateDot();
		
		/* Static method calling declared evaluateDot on the generic object */
		DotCalculus1D dotCalculus1D1 = new DotCalculus1D(vect_A1, vect_B1);
		GenericMethodRun.evaluateDot(dotCalculus1D1);
		System.out.println("1D dot calculus using static method:"+ dotCalculus1D1.getDot());
		
		/* Wrapped in the parametrized class */
		DotCalculus1D dotCalculus1D2 = new DotCalculus1D(vect_A2, vect_B2);
		Result result2 = new Result("Initial value 0");
		BoxDot<DotCalculus1D, Result> oneDBox = new BoxDot<DotCalculus1D, Result>(dotCalculus1D2, result2);
		System.out.println("1D dot calculus from the BoxDot instance before evaluate:"+ dotCalculus1D2.getDot());
		
		oneDBox.printCurrentResult();
		oneDBox.evaluateDot();
		System.out.println("1D dot calculus from the BoxDot instance after evaluate:"+ dotCalculus1D2.getDot());
		/* Calling method from the other parametrized and generic class via wrapping BoxDot */
		/* We can insert into it any type of object - String and then integer result and print it like this. */
		oneDBox.printCurrentResult();
		
		
/**
 * Samples of various calculations of the dot product:
 * https://stackoverflow.com/questions/7781150/how-to-apply-java-generics-to-calculator
 * https://www.geeksforgeeks.org/program-dot-product-cross-product-two-vector/		
 * https://www.javatpoint.com/java-program-to-multiply-two-matrices
 * 
 */
		
	}
}


