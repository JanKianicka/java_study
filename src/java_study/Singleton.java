/**
 * *****************************************************************************
 * Copyright 2021, MicroStep-MIS spol. s r.o. (www.microstep-mis.com)
 * All rights reserved.
 * *****************************************************************************
 * This program/code is the exclusive and proprietary property of MicroStep-MIS.
 * Any unauthorized use of this program/code without the prior written consent of 
 * MicroStep-MIS is strictly prohibited.
 * *****************************************************************************
 */
package java_study;

//File Name: Singleton.java
public class Singleton {

	private static Singleton singleton = new Singleton();

	/*
	 * A private Constructor prevents any other class from instantiating.
	 */
	private Singleton() {
	}

	/* Static 'instance' method */
	public static Singleton getInstance() {
		return singleton;
	}

	/* Other methods protected by singleton-ness */
	protected static void demoMethod() {
		System.out.println("demoMethod for singleton");
	}
}