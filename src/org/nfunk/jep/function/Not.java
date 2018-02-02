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
 * $Id: Not.java,v 1.3 2012/03/01 09:11:22 matog Exp $
 */
public class Not extends PostfixMathCommand
{
	public Not()
	{
		numberOfParameters = 1;
	
	}
	
	public void run(Stack inStack)
		throws ParseException 
	{
		checkStack(inStack);// check the stack
		Object param = inStack.pop();
		if (param instanceof Number)
		{
			int r = (((Number)param).doubleValue() == 0) ? 1 : 0;
			inStack.push(Double.valueOf(r));//push the result on the inStack
		}
		else {
			throw new ParseException("Invalid parameter type");
		}
		return;
	}

}
