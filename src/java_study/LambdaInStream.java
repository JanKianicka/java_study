package java_study;

import java.util.Arrays;
import java.util.List;

/**
 * Studied from beginning of 
 * https://www.youtube.com/watch?v=1OpAgZvYXLQ
 * and 
 * https://www.baeldung.com/java-8-double-colon-operator
 *
 */
public class LambdaInStream {
	
	public static void main(String[] args) {
		LambdaInStream aux = new LambdaInStream();
		
		// here are two options - either to use generic objects or type specified list
		List<SmallClass> counters = Arrays.asList(
				aux.new SmallClass(1),
				aux.new SmallClass(2),
				aux.new SmallClass(3)
				);
		
		/* First call with data type specified */
		System.out.println("Lambda with type specified: ");
		// Did not allow to specify 
		counters.forEach((SmallClass value) -> value.printCounter());
		
		// Without type specification - type inference
		System.out.println("Lambda with type inference: ");
		counters.forEach(value -> System.out.println(value));
		
		// With double colon referencing of the function
		System.out.println("Lambda with double colon: ");
		counters.forEach(SmallClass::printCounter);
	}
	
	private class SmallClass {
		private int counter;


		public SmallClass(int counterIn) {
			this.counter = counterIn;
		}
		
		public void printCounter() {
			System.out.println("Counter is: " + this.counter);
		}
		
		public String toString() {
			return "Calls toString";
		}
	}
}

