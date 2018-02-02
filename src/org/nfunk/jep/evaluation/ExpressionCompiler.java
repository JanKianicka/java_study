/*****************************************************************************

JEP - Java Math Expression Parser 2.3.0
      October 3 2004
      (c) Copyright 2004, Nathan Funk and Richard Morris
      See LICENSE.txt for license information.

*****************************************************************************/


package org.nfunk.jep.evaluation;

import org.nfunk.jep.*;
import java.util.*;


public class ExpressionCompiler implements ParserVisitor {
	/** Commands */
	private Vector commands;	// NOSONAR kvoli spatnej kompatibilite Vector
		
	public ExpressionCompiler() {
		commands = new Vector();	// NOSONAR kvoli spatnej kompatibilite Vector
	}
	
	public CommandElement[] compile(Node node) throws ParseException{
		commands.removeAllElements();
		node.jjtAccept(this, null);
		CommandElement[] temp = new CommandElement[commands.size()];
		// enum zmenene na enum1, aby slo prekompilovat pod Java 1.5, kde je "enum" reserved.
		// MatoG 8.4.2005
		Enumeration enum1 = commands.elements();
		int i = 0;
		while (enum1.hasMoreElements()) {
			 temp[i++] = (CommandElement)enum1.nextElement();
		}
		return temp;
	}

	public Object visit(ASTFunNode node, Object data) throws ParseException {
		node.childrenAccept(this,data);
		
		CommandElement c = new CommandElement();
		c.setType(CommandElement.FUNC);
		c.setPFMC(node.getPFMC());
		c.setNumParam(node.jjtGetNumChildren());
		commands.addElement(c);
		
		return data;
	}

	public Object visit(ASTVarNode node, Object data) {
		CommandElement c = new CommandElement();
		c.setType(CommandElement.VAR);
		c.setVarName(node.getName());
		commands.addElement(c);

		return data;
	}

	public Object visit(ASTConstant node, Object data) {
		CommandElement c = new CommandElement();
		c.setType(CommandElement.CONST);
		c.setValue(node.getValue());
		commands.addElement(c);

		return data;
	}
	
	public Object visit(SimpleNode node, Object data) {
		return data;
	}
	
	public Object visit(ASTStart node, Object data) {
		return data;
	}
}
