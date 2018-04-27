package java_study;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

class IteratorDemo {
	
	private IteratorDemo() {}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void run(String args[]) {
		// Create an array list
		ArrayList al = new ArrayList();

		// add elements to the array list
		al.add("C");
		al.add("A");
		al.add("E");
		al.add("B");
		al.add("D");
		al.add("F");

		// Use iterator to display contents of al
		System.out.print("Original contents of al: ");
		Iterator itr = al.iterator();

		while (itr.hasNext()) {
			Object element = itr.next();
			System.out.print(element + " ");
		}
		System.out.println();

		// Modify objects being iterated
		ListIterator litr = al.listIterator();

		while (litr.hasNext()) {
			Object element = litr.next();
			litr.set(element + "+");
		}
		System.out.print("Modified contents of al: ");
		itr = al.iterator();

		while (itr.hasNext()) {
			Object element = itr.next();
			System.out.print(element + " ");
		}
		System.out.println();

		// Now, display the list backwards
		System.out.print("Modified list backwards: ");

		while (litr.hasPrevious()) {
			Object element = litr.previous();
			System.out.print(element + " ");
		}
		System.out.println();
	}
}


public class CollectionAlgorithmsDemo {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String args[]) {

		// Create and initialize linked list
		LinkedList ll = new LinkedList();
		ll.add(new Integer(-8));
		ll.add(new Integer(20));
		ll.add(new Integer(-20));
		ll.add(new Integer(8));

		// Create a reverse order comparator
		Comparator r = Collections.reverseOrder();

		// Sort list by using the comparator
		Collections.sort(ll, r);

		// Get iterator
		Iterator li = ll.iterator();
		System.out.print("List sorted in reverse: ");

		while (li.hasNext()) {
			System.out.print(li.next() + " ");
		}
		System.out.println();
		Collections.shuffle(ll);

		// display randomized list
		li = ll.iterator();
		System.out.print("List shuffled: ");

		while (li.hasNext()) {
			System.out.print(li.next() + " ");
		}

		System.out.println();
		System.out.println("Minimum: " + Collections.min(ll));
		System.out.println("Maximum: " + Collections.max(ll));
		
		IteratorDemo.run(new String[0]);
	}
}
