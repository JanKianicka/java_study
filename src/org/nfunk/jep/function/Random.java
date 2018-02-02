/*****************************************************************************

JEP - Java Math Expression Parser 2.3.0
      October 3 2004
      (c) Copyright 2004, Nathan Funk and Richard Morris
      See LICENSE.txt for license information.

*****************************************************************************/
package org.nfunk.jep.function;

import java.lang.Math;
import java.util.*;
import org.nfunk.jep.*;
/**
* Encapsulates the Math.random() function.
*/
public class Random extends PostfixMathCommand
{
	public Random()
	{
		numberOfParameters = 0;

	}

	public void run(Stack inStack)
		throws ParseException
	{
		checkStack(inStack);// check the stack
		inStack.push(Double.valueOf(Math.random()));
		return;
	}
}
