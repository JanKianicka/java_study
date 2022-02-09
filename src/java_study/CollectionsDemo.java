package java_study;

import java.io.IOException;
import java.io.Serializable;
import java.lang.instrument.Instrumentation;
import java.util.*;

//import org.openjdk.jol.info.ClassLayout;

public class CollectionsDemo {

   public static void main(String[] args) {
      Map<String, String> m1 = new HashMap<String, String>(); 
      m1.put("Zara", "8");
      m1.put("Mahnaz", "31");
      m1.put("Ayan", "12");
      m1.put("Daisy", "14");
      String z = m1.get("Zara");
      System.out.println();
      System.out.println(" Map Elements");
      System.out.print("\t" + m1);
      System.out.println("Zara " + z);
      Set<String> keySet = m1.keySet();
      for(String key: keySet) {
    	  System.out.println(m1.get(key));
    	  
      }
      
      Map<Integer, String> m2 = new HashMap<Integer, String>();
      m2.put(1, "First value");
      System.out.print("\t" + m2);
      System.out.print(m2.keySet());
      
      Test_class t = new CollectionsDemo(). new Test_class();
      System.out.println(t.a);
      changeTestClass(t);
      System.out.println("\n\n"+ t.listOfInt);
      
      System.out.println("Test of static method.");
      ArrayList<Integer> inArray = new ArrayList<Integer>();
      ChangeCollectionTest(inArray);
      System.out.println(inArray);
      
      
      TestMapSizeAndOverriding testMapSize = new CollectionsDemo().new TestMapSizeAndOverriding();
      testMapSize.fillAndDestroyMap();
      
      
   }
   
   private class Test_class{
	   public int a = 1;
	   public List<Integer> listOfInt = new ArrayList<Integer>();
	   
	   public void Test_class() {}
	   
   }
   
   private class TestMapSizeAndOverriding{
	   private Map<Integer, Map<String, Object>> firstMap = new HashMap<Integer, Map<String, Object>>();
	   private Map<Integer, Map<String, Object>> secondMap = new HashMap<Integer, Map<String, Object>>();
	   
	   public TestMapSizeAndOverriding() {
		   
	   }
	   
	   public void fillAndDestroyMap() {
		   firstMap.put(1, new HashMap<String, Object>());
		   firstMap.get(1).put("FirstRecord", (String)"Value1");
		   System.out.println("fillAndDestroyMap");
		   System.out.println("firstMap first value: " + firstMap.get(1).values().toString());
		   secondMap = firstMap;
		   secondMap.put(2, new HashMap<String, Object>());
		   firstMap.get(2).put("SecondRecord", (String)"Value2");
		   System.out.println("firstMap first value: " + firstMap.get(2).values().toString());
		   //secondMap = new HashMap<Integer, Map<String, Object>>();
		   //secondMap = null;
		   Map<String, Object> loc1 = new HashMap<String, Object>();
		   loc1.put("ThirdRecord", (String)"Value3");
		   secondMap.put(3, loc1);
		   //firstMap = new HashMap<Integer, Map<String, Object>>();
		   System.out.println("firstMap first value - after reset of second map: " + firstMap.get(1).values().toString());
		   System.out.println("firstMap first value - after reset of second map: " + firstMap.get(2).values().toString());
		   System.out.println("firstMap first value - after reset of second map: " + firstMap.get(3).values().toString());
		   
		   //InstrumentationAgent instrumentAgent = new InstrumentationAgent();
		   //instrumentAgent.getObjectSize("String");
		   // Shallow size of the complex object
		   // VM.current().sizeOf(secondMap);
		   // Class Layout also not available
		   // System.out.println(ClassLayout.parseInstance("String").toPrintable());
	   }
	   
	   public int sizeof(Serializable obj) {
		   try {
		      java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
		      new java.io.ObjectOutputStream(baos).writeObject(obj);
		      return baos.size();
		   } catch(IOException ignore) {}
		   return 0;
		}	   
	   
   }
   
   public class InstrumentationAgent {
	    private volatile Instrumentation globalInstrumentation;

//	    public static void premain(final String agentArgs, final Instrumentation inst) {
//	        globalInstrumentation = inst;
//	    }
	    public InstrumentationAgent(final Instrumentation inst) {
	    	globalInstrumentation = inst;
	    	
	    }
	    
	    public long getObjectSize(final Object object) {
	        if (globalInstrumentation == null) {
	            throw new IllegalStateException("Agent not initialized.");
	        }
	        return globalInstrumentation.getObjectSize(object);
	    }
	}
   
   
   private static void changeTestClass(Test_class t) {
	   t.listOfInt.add(6);
	   
   }
   
   public static void ChangeCollectionTest(ArrayList<Integer> inArray) {
	   
	   inArray.add(1);
   }   
}