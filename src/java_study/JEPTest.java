package java_study;

import org.nfunk.jep.*;

public class JEPTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		JEP jep = new JEP();
		jep.addVariable("x", 10);
		Node node = jep.parse("x+1");
		Object result = jep.evaluate(node);			
		System.out.println("x + 1 = " + result);
		// Super, it works when I have copied JEP package from jlib.
		
	}

}
