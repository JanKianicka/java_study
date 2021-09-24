package java_study;

abstract class TermType extends Object {
	public abstract void concatenateStrings(String inStr);
}


class TermTypeA extends TermType {
	private String type;
	
	public TermTypeA(String typeIn) {
		this.type = typeIn;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String typeIn) {
		this.type = typeIn;
	}
	
	public void concatenateStrings(String inStr) {
		System.out.println(String.format("Concatenate: %s%s", this.type, inStr));
	}
	
	@Override
	public String toString() {
		return String.format("Type is: %s", this.type);
	}
}

class TermTypeB extends TermType {
	private String type;

	public TermTypeB(String typeIn) {
		this.type = typeIn;
	}

	
	public String getType() {
		return this.type;
	}
	
	public void setType(String typeIn) {
		this.type = typeIn;
	}
	
	public void concatenateStrings(String inStr) {
		System.out.println(String.format("Concatenate: %s%s", this.type, inStr));
	}	
	
	@Override
	public String toString() {
		return String.format("Type is: %s", this.type);
	}	
}

class Box<T> {
	private T t;

	public Box(T tIn) {
		this.t = tIn;
	}
	
	public void addType(T t) {
		this.t = t;
	}

	public T getType() {
		return t;
	}
}

public class GenericMethodTest {
	// generic method printArray
	public static <E> void printArray(E[] inputArray) {
		// Display array elements
		for (E element : inputArray) {
			System.out.printf("%s ", element);
		}
		System.out.println();
	}
	
	// execute contatenate method on generic type objects in the array
	public static <E extends TermType> void concatenateArr(E[] inputArray, String appendix) {
		for (E element : inputArray) {
			element.concatenateStrings(appendix);
		}
	}
	
	@SuppressWarnings("unused")
	public static <E> void procesTerminals(E[] inputArray) {
		for(E element: inputArray) {
//			None of these works
//			System.out.println(element.getType());
//			System.out.println(element.type);
//		    This means I actually can this way use just Object's methods
//			and not self defined ones.			
			
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void processBoxes(Box[] inBoxesArray) {
		for(Box element: inBoxesArray) {
			System.out.println(element.getType());
		}
	}
	
	// bounded type parameter limited to Comparable family of types, 
	// either extends or implements can be used in this place.
	public static <T extends Comparable<T>> T maximum(T x, T y, T z) {
		T max = x; // assume x is initially the largest

		if (y.compareTo(max) > 0) {
			max = y; // y is the largest so far
		}

		if (z.compareTo(max) > 0) {
			max = z; // z is the largest now
		}
		return max; // returns the largest object
	}	

	@SuppressWarnings("unchecked")
	static <T> T[] newArray(Class<T> type, int length)
	{
	    return (T[]) java.lang.reflect.Array.newInstance(type, length);
	}	
	
	public static void main(String args[]) {
		// Create arrays of Integer, Double and Character
		Integer[] intArray = { 1, 2, 3, 4, 5 };
		Double[] doubleArray = { 1.1, 2.2, 3.3, 4.4 };
		Character[] charArray = { 'H', 'E', 'L', 'L', 'O' };
		TermTypeA[] termTypeAArray = {new TermTypeA("FirstATerm"), new TermTypeA("SecondATerm")};
		TermTypeB[] termTypeBArray = {new TermTypeB("FirstBTerm"), new TermTypeB("SecondBTerm")};
		
		
		System.out.println("Array integerArray contains:");
		printArray(intArray); // pass an Integer array

		System.out.println("\nArray doubleArray contains:");
		printArray(doubleArray); // pass a Double array

		System.out.println("\nArray characterArray contains:");
		printArray(charArray); // pass a Character array
		
		System.out.println("\nTermA array contains:");
		printArray(termTypeAArray); // pass a Character array
		
		System.out.println("\nTermB array contains:");
		printArray(termTypeBArray); // pass a Character array
		
		System.out.printf("Max of %d, %d and %d is %d\n\n", 3, 4, 5, maximum(3, 4, 5));

		System.out.printf("Max of %.1f,%.1f and %.1f is %.1f\n\n", 6.6, 8.8, 7.7, maximum(6.6, 8.8, 7.7));

		System.out.printf("Max of %s, %s and %s is %s\n", "pear", "apple", "orange",
				maximum("pear", "apple", "orange"));
		
		Box<String> stringBox = new Box<String>("Box1");
		System.out.println(stringBox.getType());
		
		// this is not allowed.
		// There reason you can't create a Array of a generic type is this:
		// When a generic type is instantiated, the compiler translates those types by a
		// technique called type erasure — a process where the compiler removes all
		// information related to type parameters and type arguments within a class or
		// method. Type erasure enables Java applications that use generics to maintain
		// binary compatibility with Java libraries and applications that were created
		// before generics.
		
		// Box<String>[] inStringBoxArray = {stringBox};
		@SuppressWarnings("rawtypes")
		Box[] boxArray = newArray(Box.class, 3);
		@SuppressWarnings("rawtypes")
		Class<Box> type = Box.class;
		System.out.println(type);
		
		boxArray[0] = stringBox;
		boxArray[1] = stringBox;
		boxArray[2] = stringBox;
		processBoxes(boxArray);
		
		// Trying to extend generic type methods by concatenate method with certain string
		concatenateArr(termTypeAArray, "APPENDIX");
		concatenateArr(termTypeBArray, "APPENDIX2");
	}
}