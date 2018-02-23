package java_study;

abstract class AnonymousInner {
	public abstract void mymethod();
}

//interface
interface Message {
	String greet();
}

class Outer_Demo {
   int num;
   // private variable of the outer class
   private int num2 = 175;  
   
   // inner class
   public class Inner_Demo {
      public void print() {
         System.out.println("This is an inner class");
      }
      
      public int getNum() {
          System.out.println("This is the getnum method of the inner class");
          return num2;
       }
   }
   
   // Accessing he inner class from the method within
   void display_Inner() {
      Inner_Demo inner = new Inner_Demo();
      inner.print();
   }
   
   // instance method of the outer class 
   void my_Method() {
      int num = 23;

      // method-local inner class
      class MethodInner_Demo {
         public void print() {
            System.out.println("This is method inner class "+num);	   
         }   
      } // end of inner class
	   
      // Accessing the inner class
		MethodInner_Demo inner = new MethodInner_Demo();
		inner.print();
	}

	static class Nested_Demo {
		public void my_method() {
			System.out.println("This is my nested class");
		}
	}
}
   
public class Outer_Demo_Run {

	public void displayMessage(Message m) {
		System.out.println(m.greet() + ", This is an example of anonymous inner class as an argument");
	}

	public static void main(String args[]) {
		// Instantiating the outer class
		Outer_Demo outer = new Outer_Demo();

		// Accessing the display_Inner() method.
		outer.display_Inner();

		// Instantiating the inner class
		Outer_Demo.Inner_Demo inner = outer.new Inner_Demo();
		System.out.println(inner.getNum());

		// Calling the method with local inner class
		outer.my_Method();

		// Usage of anonymous class type
		AnonymousInner inner2 = new AnonymousInner() {
			public void mymethod() {
				System.out.println("This is an example of anonymous inner class");
			}
		};
		inner2.mymethod();
		
		// Instantiating the class
		Outer_Demo_Run obj = new Outer_Demo_Run();

		// Passing an anonymous inner class as an argument
		obj.displayMessage(new Message() {
			public String greet() {
				return "Hello";
			}
		});
		
		Outer_Demo.Nested_Demo nested = new Outer_Demo.Nested_Demo();	 
	    nested.my_method();		

	}
}
