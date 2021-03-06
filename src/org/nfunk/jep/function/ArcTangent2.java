/*****************************************************************************

JEP - Java Math Expression Parser 2.3.0
      October 3 2004
      (c) Copyright 2004, Nathan Funk and Richard Morris
      See LICENSE.txt for license information.

*****************************************************************************/

package org.nfunk.jep.function;

import java.util.*;
import org.nfunk.jep.*;

/**
 * atan2(y, x) Returns the angle whose tangent is y/x. 
 * @author nathan
 * 
 * $Id: ArcTangent2.java,v 1.3 2012/03/01 09:11:21 matog Exp $
 */
public class ArcTangent2 extends PostfixMathCommand
{
	public ArcTangent2()
	{
		numberOfParameters = 2;
	}
	
	public void run(Stack inStack)
		throws ParseException 
	{
		checkStack(inStack);// check the stack
		Object param2 = inStack.pop();
		Object param1 = inStack.pop();
		
		if ((param1 instanceof Number) && (param2 instanceof Number))
		{
			double y = ((Number)param1).doubleValue();
			double x = ((Number)param2).doubleValue();
			inStack.push(Double.valueOf(Math.atan2(y, x)));//push the result on the inStack
		}
		else {
			throw new ParseException("Invalid parameter type");
		}
		return;
	}
}
