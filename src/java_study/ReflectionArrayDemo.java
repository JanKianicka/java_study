package java_study;

import java.lang.reflect.Array;
/*
 * https://www.tutorialspoint.com/javareflect/java_reflect_array.htm
 */
public class ReflectionArrayDemo {
	public static void main(String[] args) {

		String[] stringArray = (String[]) Array.newInstance(String.class, 3);

		Array.set(stringArray, 0, "Mahesh");
		Array.set(stringArray, 1, "Ramesh");
		Array.set(stringArray, 2, "Suresh");

		System.out.println("stringArray[0] = " + Array.get(stringArray, 0));
		System.out.println("stringArray[1] = " + Array.get(stringArray, 1));
		System.out.println("stringArray[2] = " + Array.get(stringArray, 2));

		double[] doubleArray = new double[] { 1.0, 2.0, 3.0 };

		System.out.println("array[0] = " + Array.getDouble(doubleArray, 0));
		System.out.println("array[1] = " + Array.getDouble(doubleArray, 1));
		System.out.println("array[2] = " + Array.getDouble(doubleArray, 2));

		String[][] string2DArray = (String[][]) Array.newInstance(String.class, 3, 3);

		Array.set(string2DArray[0], 0, "Mahesh");
		Array.set(string2DArray[1], 1, "Ramesh");
		Array.set(string2DArray[2], 2, "Suresh");

		System.out.println("stringArray[0][0] = " + Array.get(string2DArray[0], 0));
		System.out.println("stringArray[1][1] = " + Array.get(string2DArray[1], 1));
		System.out.println("stringArray[2][2] = " + Array.get(string2DArray[2], 2));

	}

}
