package java_study;

interface Sports {
	public void setHomeTeam(String name);

	public void setVisitingTeam(String name);
}

interface Football extends Sports {
	public void homeTeamScored(int points);

	public void visitingTeamScored(int points);

	public void endOfQuarter(int quarter);
}

interface Hockey extends Sports {
	public void homeGoalScored();

	public void visitingGoalScored();

	public void endOfPeriod(int period);

	public void overtimePeriod(int ot);
}

/*
 * The Hockey interface has four methods, but it inherits two from Sports; thus,
 * a class that implements Hockey needs to implement all six methods. Similarly,
 * a class that implements Football needs to define the three methods from
 * Football and the two methods from Sports.
 * 
 */

// Tagging interface 
interface EventListener
{}

/*
 * An interface with no methods in it is referred to as a tagging interface.
 * There are two basic design purposes of tagging interfaces −
 * 
 * Creates a common parent − As with the EventListener interface, which is
 * extended by dozens of other interfaces in the Java API, you can use a tagging
 * interface to create a common parent among a group of interfaces. For example,
 * when an interface extends EventListener, the JVM knows that this particular
 * interface is going to be used in an event delegation scenario.
 * 
 * Adds a data type to a class − This situation is where the term, tagging comes
 * from. A class that implements a tagging interface does not need to define any
 * methods (since the interface does not have any), but the class becomes an
 * interface type through polymorphism.
 */

// A class can implement several interfaces, but must implement all methods
class Multiply_implementation implements Hockey, Animal_interface {

	@Override
	public void setHomeTeam(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVisitingTeam(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void travel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void homeGoalScored() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitingGoalScored() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endOfPeriod(int period) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void overtimePeriod(int ot) {
		// TODO Auto-generated method stub
		
	}
	
}

class MammalInt implements Animal_interface {

	public void eat() {
		System.out.println("Mammal eats");
	}

	public void travel() {
		System.out.println("Mammal travels");
	}

	public int noOfLegs() {
		return 0;
	}
}

public class Interface_demo {

	public static void main(String[] args) {
		MammalInt m = new MammalInt();
		m.eat();
		m.travel();

	}

}
