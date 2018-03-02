package java_study;


// In course of abstraction study change to asbtract class
abstract class Employee_polym {
	private String name;
	private String address;
	private int number;

	public Employee_polym(String name, String address, int number) {
		System.out.println("Constructing an Employee");
		this.name = name;
		this.address = address;
		this.number = number;
	}

	public void mailCheck() {
		System.out.println("Mailing a check to " + this.name + " " + this.address);
	}

	public String toString() {
		return name + " " + address + " " + number;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String newAddress) {
		address = newAddress;
	}

	public int getNumber() {
		return number;
	}
	
	public abstract double computePay();
}

class Salary extends Employee_polym {
	private double salary; // Annual salary

	public Salary(String name, String address, int number, double salary) {
		super(name, address, number);
		setSalary(salary);
	}

	public void mailCheck() {
		System.out.println("Within mailCheck of Salary class ");
		System.out.println("Mailing check to " + getName() + " with salary " + salary);
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double newSalary) {
		if (newSalary >= 0.0) {
			salary = newSalary;
		}
	}

	@Override
	public double computePay() {
		System.out.println("Computing salary pay for " + getName());
		return salary / 52;
	}
}

public class Polymorphizm_demo {

	public static void main(String[] args) {
		Salary s = new Salary("Mohd Mohtashim", "Ambehta, UP", 3, 3600.00);
		Employee_polym e = new Salary("John Adams", "Boston, MA", 2, 2400.00);
		System.out.println("Call mailCheck using Salary reference --");
		s.mailCheck();
		System.out.println("\n Call mailCheck using Employee reference--");
		e.mailCheck();
		
		/*
		 * This behavior is referred to as virtual method invocation, and these methods
		 * are referred to as virtual methods. An overridden method is invoked at run
		 * time, no matter what data type the reference is that was used in the source
		 * code at compile time.
		 */
	}
}
