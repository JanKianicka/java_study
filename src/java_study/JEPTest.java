package java_study;

/**
 * All of these excercises comes from
 * http://www.singularsys.com/jep/doc/html/index.html
 */


import org.nfunk.jep.*;
// import com.singularsys.jep.bigDecimals.BigDecComponents;
// big decimals is not supported by our JEP

public class JEPTest {

	public static void main(String[] args) throws Exception {
		JEP jep = new JEP();
		jep.addVariable("x", 10);
		Node node = jep.parse("x+1");
		Object result = jep.evaluate(node);			
		System.out.println("x + 1 = " + result);
		// Super, it works when I have copied JEP package from jlib.
		
		// evaluation of multiply expressions
		jep.setAllowAssignment(true);
		try {
			// in spite of Allow Assignement y was not recognized as a symbol
			// y=x^2
			Node n1 = jep.parse("x^2");
			
//			Node n2 = jep.parse("z=x+y");
//          org.nfunk.jep.ParseException: Syntax Error (assignment not enabled)
			
			for (double x=0.0; x<=1.0; x+=0.1) {
				jep.addVariable("x", x);
				Object value1 = jep.evaluate(n1);
//				Object value2 = jep.evaluate(n2);
				System.out.println("y=x^2: " + value1);
//				System.out.println("z=x+y: " + value2);
			}
		}
		// JepException does not exist in our version of JEP
		catch(Exception e) {
			System.err.println(e);
		}
		
		jep.addVariable("y2", 0);
		Node n3 = jep.parse("2*y2-4.23*x");
		// sin, cos, and other functions not available in this version of JEP
		// evaluate expression for x = 0 to x = 99
		for (int i=0; i<100; i++) {
			// update the value of x
		    jep.addVariable("y2", i);
			// print the result
		    // The method evaluate(Node) in the type JEP is not applicable for the arguments ()
		    // when we have jep.evaluate()
		    System.out.println("Value at y2 = " + i + ": " + jep.evaluate(n3));
		}
		
		// JEP jep2 = new JEP(new RealEvaluator());
		// Real Evaluator is also not available.
		
		Variable var1 = jep.getVar("x");
		System.out.format("var1: %s\n", var1.toString());
		Object var2 = jep.getVarValue("x");
		System.out.format("var2: %s\n", var2.toString());
		Variable var3 = jep.getVar("y2");
		System.out.format("var3: %s\n", var3.toString());
		
		SymbolTable vars = jep.getSymbolTable();
		
		System.out.println("vars:" + vars.toString());
		System.out.println("vars as hash:" + vars.entrySet());
		
		// standard initialisation
		JEP j = new JEP();
		// so this was needed to perform the operation of assignment in our old JEP
		j.setAllowUndeclared(true);

		// switch assignment facilities on
		j.setAllowAssignment(true);

		// parse assignment equations
		j.addVariable("y2", 2);
		try {
			Node n4 = j.parse("x=3^y2");
			Node n5 = j.parse("y=x+y2");
			Object value1 = j.evaluate(n4);
			Object value2 = j.evaluate(n5);
			System.out.println("value1: " + value1);
			System.out.println("value2: " + value2);
			System.out.println("Symbol table:" + j.getSymbolTable());
		} catch (ParseException e) {
		}
		
		// Data types:
		// These include numbers, strings, vectors, and complex numbers.
		j.addVariable("foo", "foo");
		j.addVariable("bar", "bar");
		j.addVariable("foobar", "foobar");
		Node n6 = j.parse("foo + bar == foobar");
		Object value3 = j.evaluate(n6);
		// returns true
		System.out.println("value3: " + value3);
		
		// Add the variable y with value [1.2,3.4]
	    jep.addVariable("y", new Vector<Object>(Arrays.asList(new Double[]{1.2,3.4})));
	    // Use the y[2] syntax to get a specific element
	    Object res = jep.evaluate(jep.parse("y[2]"));
	    assertEquals(3.4,res);
		
		
	}

}
