package java_study;

import java.io.Serializable;

public class StudentTransient implements Serializable{

	int id;
	String name;
	transient int age;// Now it will not be serialized

	public StudentTransient(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
}
