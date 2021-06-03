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

//File Name: SingletonDemo.java
public class SingletonDemo {

	public static void main(String[] args) {
		Singleton tmp = Singleton.getInstance();
		tmp.demoMethod();
	}
}