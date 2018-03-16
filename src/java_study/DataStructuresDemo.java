package java_study;

import java.util.BitSet;
import java.util.Dictionary;
import java.util.EmptyStackException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

class EnumerationTester {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void run() {
		Enumeration days;
		Vector dayNames = new Vector();

		dayNames.add("Sunday");
		dayNames.add("Monday");
		dayNames.add("Tuesday");
		dayNames.add("Wednesday");
		dayNames.add("Thursday");
		dayNames.add("Friday");
		dayNames.add("Saturday");
		days = dayNames.elements();

		while (days.hasMoreElements()) {
			System.out.println(days.nextElement());
		}
	}
}


class BitSetDemo {

	public static void run() {
		BitSet bits1 = new BitSet(16);
		BitSet bits2 = new BitSet(16);

		// set some bits
		for (int i = 0; i < 16; i++) {
			if ((i % 2) == 0)
				bits1.set(i);
			if ((i % 5) != 0)
				bits2.set(i);
		}

		System.out.println("Initial pattern in bits1: ");
		System.out.println(bits1);
		System.out.println(Long.toString(bits1.toLongArray()[0], 2));
		System.out.println("\nInitial pattern in bits2: ");
		System.out.println(bits2);
		System.out.println(Long.toString(bits2.toLongArray()[0], 2));

		// AND bits
		bits2.and(bits1);
		System.out.println("\nbits2 AND bits1: ");
		System.out.println(bits2);

		// OR bits
		bits2.or(bits1);
		System.out.println("\nbits2 OR bits1: ");
		System.out.println(bits2);

		// XOR bits
		bits2.xor(bits1);
		System.out.println("\nbits2 XOR bits1: ");
		System.out.println(bits2);
	}
}

// The Vector class is similar to a traditional Java array, except that it can
// grow as necessary to accommodate new elements.
//
// Like an array, elements of a Vector object can be accessed via an index into
// the vector.
//
// The nice thing about using the Vector class is that you don't have to worry
// about setting it to a specific size upon creation; it shrinks and grows
// automatically when necessary.
// It is similar to ArrayList, but with two differences −
//
// Vector is synchronized.
//
// Vector contains many legacy methods that are not part of the collections
// framework.

class VectorDemo {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void run() {
		// initial size is 3, increment is 2

		Vector v = new Vector(3, 2);
		System.out.println("Initial size: " + v.size());
		System.out.println("Initial capacity: " + v.capacity());

		v.addElement(new Integer(1));
		v.addElement(new Integer(2));
		v.addElement(new Integer(3));
		v.addElement(new Integer(4));
		System.out.println("Capacity after four additions: " + v.capacity());

		v.addElement(new Double(5.45));
		System.out.println("Current capacity: " + v.capacity());

		v.addElement(new Double(6.08));
		v.addElement(new Integer(7));
		System.out.println("Current capacity: " + v.capacity());

		v.addElement(new Float(9.4));
		v.addElement(new Integer(10));
		System.out.println("Current capacity: " + v.capacity());

		v.addElement(new Integer(11));
		v.addElement(new Integer(12));
		System.out.println("First element: " + (Integer) v.firstElement());
		System.out.println("Last element: " + (Integer) v.lastElement());

		if (v.contains(new Integer(3)))
			System.out.println("Vector contains 3.");

		// enumerate the elements in the vector.
		Enumeration vEnum = v.elements();
		System.out.println("\nElements in vector:");

		while (vEnum.hasMoreElements())
			System.out.print(vEnum.nextElement() + " ");
		System.out.println();
	}
}

// The Stack class implements a last-in-first-out (LIFO) stack of elements.
//
// You can think of a stack literally as a vertical stack of objects; when you
// add a new element, it gets stacked on top of the others.
//
// When you pull an element off the stack, it comes off the top. In other words,
// the last element you added to the stack is the first one to come back off.

class StackDemo {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	static void showpush(Stack st, int a) {
		st.push(new Integer(a));
		System.out.println("push(" + a + ")");
		System.out.println("stack: " + st);
	}

	@SuppressWarnings("rawtypes")
	static void showpop(Stack st) {
		System.out.print("pop -> ");
		Integer a = (Integer) st.pop();
		System.out.println(a);
		System.out.println("stack: " + st);
	}

	@SuppressWarnings("rawtypes")
	public static void run() {
		Stack st = new Stack();
		System.out.println("stack: " + st);
		showpush(st, 42);
		showpush(st, 66);
		showpush(st, 99);
		showpop(st);
		showpop(st);
		showpop(st);
		try {
			showpop(st);
		} catch (EmptyStackException e) {
			System.out.println("empty stack");
		}
	}
}

// Hashtable was part of the original java.util and is a concrete implementation
// of a Dictionary.
//
// However, Java 2 re-engineered Hashtable so that it also implements the Map
// interface. Thus, Hashtable is now integrated into the collections framework.
// It is similar to HashMap, but is synchronized.

class DictionaryDemo {

	public static void run() {
		Dictionary<Integer, String> d = new Hashtable<Integer, String>();
		d.put(1, "Passion");
		d.put(2, "Motion");
		d.put(3, "Caution");
		Enumeration<Integer> key = d.keys();
		while (key.hasMoreElements()) {
			System.out.println(key.nextElement());
		}
		Enumeration<String> element = d.elements();
		while (element.hasMoreElements()) {
			System.out.println(element.nextElement());
		}
	}
}

// Properties is a subclass of Hashtable. It is used to maintain lists of values
// in which the key is a String and the value is also a String.
//
// The Properties class is used by many other Java classes. For example, it is
// the type of object returned by System.getProperties( ) when obtaining
// environmental values.

class PropDemo {

	@SuppressWarnings("rawtypes")
	public static void run() {
		Properties capitals = new Properties();
		Set states;
		String str;

		capitals.put("Illinois", "Springfield");
		capitals.put("Missouri", "Jefferson City");
		capitals.put("Washington", "Olympia");
		capitals.put("California", "Sacramento");
		capitals.put("Indiana", "Indianapolis");

		// Show all states and capitals in hashtable.
		states = capitals.keySet(); // get set-view of keys
		Iterator itr = states.iterator();

		while (itr.hasNext()) {
			str = (String) itr.next();
			System.out.println("The capital of " + str + " is " + capitals.getProperty(str) + ".");
		}
		System.out.println();

		// look for state not in list -- specify default
		str = capitals.getProperty("Florida", "Not Found");
		System.out.println("The capital of Florida is " + str + ".");
	}
}

public class DataStructuresDemo {

	public static void main(String[] args) {
		EnumerationTester.run();
		BitSetDemo.run();
		VectorDemo.run();
		StackDemo.run();
		DictionaryDemo.run();
		PropDemo.run();
	}
}
