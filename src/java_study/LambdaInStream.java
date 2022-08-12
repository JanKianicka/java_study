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

/*
 * Important is that 
 * lambda expressions are compiled objects which are invoked dynamic
 * See mapping of dis-assambled class:
 * > javap -c ../../maven_test/target/classes/java_study/LambdaInStream.class
 * Compiled from "LambdaInStream.java"
public class java_study.LambdaInStream {
  public java_study.LambdaInStream();
    Code:
       0: aload_0
       1: invokespecial #8                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: new           #1                  // class java_study/LambdaInStream
       3: dup
       4: invokespecial #16                 // Method "<init>":()V
       7: astore_1
       8: iconst_3
       9: anewarray     #17                 // class java_study/LambdaInStream$SmallClass
      12: dup
      13: iconst_0
      14: new           #17                 // class java_study/LambdaInStream$SmallClass
      17: dup
      18: aload_1
      19: dup
      20: invokevirtual #19                 // Method java/lang/Object.getClass:()Ljava/lang/Class;
      23: pop
      24: iconst_1
      25: invokespecial #23                 // Method java_study/LambdaInStream$SmallClass."<init>":(Ljava_study/LambdaInStream;I)V
      28: aastore
      29: dup
      30: iconst_1
      31: new           #17                 // class java_study/LambdaInStream$SmallClass
      34: dup
      35: aload_1
      36: dup
      37: invokevirtual #19                 // Method java/lang/Object.getClass:()Ljava/lang/Class;
      40: pop
      41: iconst_2
      42: invokespecial #23                 // Method java_study/LambdaInStream$SmallClass."<init>":(Ljava_study/LambdaInStream;I)V
      45: aastore
      46: dup
      47: iconst_2
      48: new           #17                 // class java_study/LambdaInStream$SmallClass
      51: dup
      52: aload_1
      53: dup
      54: invokevirtual #19                 // Method java/lang/Object.getClass:()Ljava/lang/Class;
      57: pop
      58: iconst_3
      59: invokespecial #23                 // Method java_study/LambdaInStream$SmallClass."<init>":(Ljava_study/LambdaInStream;I)V
      62: aastore
      63: invokestatic  #26                 // Method java/util/Arrays.asList:([Ljava/lang/Object;)Ljava/util/List;
      66: astore_2
      67: getstatic     #32                 // Field java/lang/System.out:Ljava/io/PrintStream;
      70: ldc           #38                 // String Lambda with type specified:
      72: invokevirtual #40                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      75: aload_2
      76: invokedynamic #46,  0             // InvokeDynamic #0:accept:()Ljava/util/function/Consumer;
      81: invokeinterface #50,  2           // InterfaceMethod java/util/List.forEach:(Ljava/util/function/Consumer;)V
      86: getstatic     #32                 // Field java/lang/System.out:Ljava/io/PrintStream;
      89: ldc           #56                 // String Lambda with type inference:
      91: invokevirtual #40                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      94: aload_2
      95: invokedynamic #58,  0             // InvokeDynamic #1:accept:()Ljava/util/function/Consumer;
     100: invokeinterface #50,  2           // InterfaceMethod java/util/List.forEach:(Ljava/util/function/Consumer;)V
     105: getstatic     #32                 // Field java/lang/System.out:Ljava/io/PrintStream;
     108: ldc           #59                 // String Lambda with double colon:
     110: invokevirtual #40                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     113: aload_2
     114: invokedynamic #61,  0             // InvokeDynamic #2:accept:()Ljava/util/function/Consumer;
     119: invokeinterface #50,  2           // InterfaceMethod java/util/List.forEach:(Ljava/util/function/Consumer;)V
     124: return
}

 * 
 * 
 *
 *
 *
 */

