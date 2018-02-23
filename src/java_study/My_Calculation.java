package java_study;

// Demonstration of invoking super class constructor
class Superclass {
	int age;

	Superclass(int age) {
		this.age = age;
	}
	
	Superclass(){
		
	}

	public void getAge() {
		System.out.println("The value of the variable named age in super class is: " + age);
	}
}

class Subclass extends Superclass {
	Subclass(int age) {
		super(age);
		
	}
	
}


class Super_class {
	int num = 20;

	// display method of superclass
	public void display() {
		System.out.println("This is the display method of superclass");
	}
}


class Sub_class extends Super_class {
	int num = 10;

	// display method of sub class
	public void display() {
		System.out.println("This is the display method of subclass");
	}

	public void my_method() {
		// Instantiating subclass
		Sub_class sub = new Sub_class();

		// Invoking the display() method of sub class
		sub.display();

		// Invoking the display() method of superclass
		super.display();

		// printing the value of variable num of subclass
		System.out.println("value of the variable named num in sub class:" + sub.num);

		// printing the value of variable num of superclass
		System.out.println("value of the variable named num in super class:" + super.num);
	}

}

class Calculation {
   int z;
	
   public void addition(int x, int y) {
      z = x + y;
      System.out.println("The sum of the given numbers:"+z);
   }
	
   public void Subtraction(int x, int y) {
      z = x - y;
      System.out.println("The difference between the given numbers:"+z);
   }
}

// Demonstration of multiply inheritance
class Animal {
	public void move() {
		System.out.println("Animals can move");
	}
}

class Mammal extends Animal {
}

class Reptile extends Animal {
}

class Dog2 extends Mammal {
	public void move() {
		super.move(); // invokes the super class method
		System.out.println("Dogs can walk and run");
	}
}

public class My_Calculation extends Calculation {
   public void multiplication(int x, int y) {
      z = x * y;
      System.out.println("The product of the given numbers:"+z);
   }
	
	public static void main(String args[]) {
		int a = 20, b = 10;
		My_Calculation demo = new My_Calculation();
		demo.addition(a, b);
		demo.Subtraction(a, b);
		demo.multiplication(a, b);

		Sub_class obj = new Sub_class();
		obj.my_method();

		Subclass s = new Subclass(24);
		s.getAge();

		Animal a2 = new Animal();
		Mammal m = new Mammal();
		Dog2 d = new Dog2();

		System.out.println(m instanceof Animal);
		System.out.println(d instanceof Mammal);
		System.out.println(d instanceof Animal);
		
		Animal b2 = new Dog2(); // Animal reference but Dog object
		b2.move(); // runs the method in Dog class
		
	}
	
}

// An overriding method can throw any uncheck exceptions, regardless of whether
// the overridden method throws exceptions or not. However, the overriding
// method should not throw checked exceptions that are new or broader than the
// ones declared by the overridden method. The overriding method can throw
// narrower or fewer exceptions than the overridden method.
