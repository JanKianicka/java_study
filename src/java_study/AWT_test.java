package java_study;

import java.awt.*;

class AWT_test extends Frame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String args[]) {
		AWT_test f = new AWT_test();
		System.out.print(f.toString());
	}
	
	AWT_test() {
		Button b = new Button("click me");
		b.setBounds(30, 100, 80, 30);// setting button position
		add(b);// adding button into frame
		setSize(300, 300);// frame size 300 width and 300 height
		setLayout(null);// no layout manager
		setVisible(true);// now frame will be visible, by default not visible
	}


}