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

import org.junit.Assert;
import org.junit.Test;

/**
 * @author janki
 *
 */
public class HelloTest {

	@Test
	public void test() {
		int ret;
		ret = HelloClass.addTwoNumbers(10, 15);
		Assert.assertEquals(26, ret);
	}

}
