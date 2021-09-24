package java_study;


abstract class DotCalculus {
	public abstract void evaluateDot();
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

			product = product + this.vect_A[i] * this.vect_A[i];
		this.dot = product;
	}
	
	public int getDot() {
		return this.dot;
	}
}


class BoxDot<T extends DotCalculus> {
	private T t;

	public BoxDot(T tIn) {
		this.t = tIn;
		//this.t.evaluateDot();
	}
	
	public void addType(T t) {
		this.t = t;
	}

	public T getType() {
		return t;
	}
	
	public void evaluateDot() {
		this.t.evaluateDot();
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
		int c[][]=new int[3][3];
		int vect_C[] = new int[3];
		
		DotCalculus1D dotCalculus1D = new DotCalculus1D(vect_A, vect_B);
		// Standard implementation without generic types
		dotCalculus1D.evaluateDot();
		
		// GenericMethodRun.evaluateDot(dotCalculus1D);
		// System.out.println("1D dot calculus:"+ dotCalculus1D.getDot());
		
		BoxDot<DotCalculus1D> oneDBox = new BoxDot<DotCalculus1D>(dotCalculus1D);
		System.out.println("1D dot calculus:"+ dotCalculus1D.getDot());
		// This does not work - The method evaluateDot() is undefined for the type Box<DotCalculus1D>
		oneDBox.evaluateDot();
		System.out.println("1D dot calculus:"+ dotCalculus1D.getDot());
		
		//    https://stackoverflow.com/questions/7781150/how-to-apply-java-generics-to-calculator
	
		// https://www.geeksforgeeks.org/program-dot-product-cross-product-two-vector/
		// https://www.javatpoint.com/java-program-to-multiply-two-matrices
		
	}
}


//private T dotArr;
//// Suported types
//private int[] type1;
//private int[][] type2;
//
//public DotProdCalc() {
//	
//}
//
//public T dotProduct(T arr1, T arr2)  {
//	Class<?> clazz = arr1.getClass();
//	System.out.println(String.format("Type: %s", clazz));
//	if (arr1.getClass().equals(int[].class)) {
//		System.out.println("One dimensional array.");
		// This is the error: The type of the expression must be an array type but it 
		// resolved to T
//		dotArr[0] = arr1[1] * arr2[2]
//                - arr1[2] * arr2[1];
//		dotArr[1] = arr1[2] * arr2[0]
//                - arr1[0] * arr2[2];
//		dotArr[2] = arr1[0] * arr2[1]
//                - arr1[1] * arr2[0];
	    // And now we have problem with casting again
		// whole this is wrong
//	} else if (arr1.getClass().equals(int[][].class)) {
//		System.out.println("Two dimensional array.");
//	} else {
//		System.out.println("Not usupported format.");
//	}
//		
//	
//	return this.dotArr;
//}
//
//public T getResult() {
//	return dotArr;
//}
//}

