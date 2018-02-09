package java_study;

import java.util.*;

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
      
   }
   
   private class Test_class{
	   public int a = 1;
	   public List<Integer> listOfInt = new ArrayList<Integer>();
	   
	   public void Test_class() {}
   }
   
   private static void changeTestClass(Test_class t) {
	   t.listOfInt.add(6);
	   
   }
}