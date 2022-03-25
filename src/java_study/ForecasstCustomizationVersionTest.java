/*******************************************************************************
 * Copyright 2022, MicroStep-MIS spol. s r.o. (www.microstep-mis.com)
 * All rights reserved.
 * ********************
 * This program/code is the exclusive and proprietary property of MicroStep-MIS.
 * Any unauthorized use of this program/code without the prior written consent of
 * MicroStep-MIS is strictly prohibited.
 * This document is for Internal use.
 ******************************************************************************/
package com.microstepmis.forecast.customization;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.junit.Test;

/**
 * @author janki
 *
 */
public class ForecasstCustomizationVersionTest {

	@Test	
	public void testForecastModuleVersionRetrieval() throws MalformedURLException, IOException {
		String implementationTitle = MeteogramAgent.class.getClass().getPackage().getImplementationTitle();
		String implementationVersion = MeteogramAgent.class.getClass().getPackage().getImplementationVersion();
		System.out.println(implementationTitle);
		System.out.println(implementationVersion);
		
		Class<MeteogramAgent> classInst = MeteogramAgent.class;
	    String className = classInst.getSimpleName() + ".class";
	    String classPath = classInst.getResource(className).toString();
	    System.out.println(classPath);
	    
	    File f = new File(classPath);
	    System.out.println(f.getParent());
	    
	    Manifest manifest = new Manifest(new URL(f.getParent() + "/META-INF/MANIFEST.MF").openStream());
	    Attributes attrs = manifest.getMainAttributes();
	    Map<String, Attributes> entries = manifest.getEntries();
	    String implementationVersionL = attrs.getValue("Main-Class");
	    System.out.println(implementationVersionL);
	    for(Entry<String, Attributes> entry:entries.entrySet()) {
	    	for(Entry<Object, Object> val:entry.getValue().entrySet()) {
	    		System.out.println(val.getKey()+","+val.getValue());
	    	}
	    }
	    
	}
	
}
