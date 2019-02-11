/**
 * *****************************************************************************
 * Copyright 2019, MicroStep-MIS spol. s r.o. (www.microstep-mis.com)
 * All rights reserved.
 * *****************************************************************************
 * This program/code is the exclusive and proprietary property of MicroStep-MIS.
 * Any unauthorized use of this program/code without the prior written consent of 
 * MicroStep-MIS is strictly prohibited.
 * *****************************************************************************
 */
package group1;

/**
 * @author janki
 *
 */
public class HelloClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HelloClass.addTwoNumbers(10, 5);
	}
	
	public static int addTwoNumbers(int a, int b) {
		System.out.format("Addition of these two is: %d", a+b);
		return a+b;
	}

}
