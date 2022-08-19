package java_study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.IntStream.Builder;
import java.util.stream.Stream;

/*
 * Examples from Java 8 Streams Tutorial
 * http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
 * 
 * From the presentation:
 * https://www.youtube.com/watch?v=1OpAgZvYXLQ
 * Using java stream might be called:
 *  - function composition
 *  - or a pipeline
 *  
 *  java.util.stream.Collectors provides concurrency safe 
 *  transformation for me
 * 
 */

public class Java8StreamsTest {

	public static void main(String[] args) throws InterruptedException {
		List<String> myList =
			    Arrays.asList("a1", "a2", "b1", "c2", "c1");

		myList.stream()
			    .filter(s -> s.startsWith("c"))
			    .map(String::toUpperCase)
			    .sorted()
			    .forEach(System.out::println);
		
		Arrays.asList("a1", "a2", "a3")
	    .stream()
	    .findFirst()
	    .ifPresent(System.out::println);  // a1
		
		IntStream.range(1, 4)
	    .forEach(System.out::println);
		
		Random random = new Random();
		random.ints();
		// supplier = random::ints(10,20);
		IntStream intStream = random.ints(0, 100);
		//intStream.limit(100).forEach(System.out::print);
		IntStream inStream = intStream.limit(10000000);
		// Here is predicate
		System.out.println(inStream.filter(n -> n > 10).average().getAsDouble());
		
		DoubleStream doubleStream = random.doubles(0, 100000);
		DoubleStream doubleStream2 = doubleStream.limit(1000000);
		System.out.println(doubleStream2.filter(n -> n > 10).average().getAsDouble());
		
		// this fails with heap memory issue - OutOfMemory
		// System.out.println(intStream.toArray().length);
		
		Builder Builder = IntStream.builder();
		Builder.add(12);
		
		// Again from the tutorial
		Arrays.stream(new int[] {1, 2, 3})
	    .map(n -> 2 * n + 1)
	    .average()
	    .ifPresent(System.out::println);  // 5.0
		
		IntStream mappedStream = Arrays.stream(new int[] {1, 2, 3})
	    .map(n -> 2 * n + 1);
		mappedStream.forEachOrdered(x -> System.out.printf("Value: %d, ", x));
		
		Stream.of("a1", "a2", "a3")
	    .map(s -> s.substring(1))
	    .mapToInt(Integer::parseInt)
	    .max()
	    .ifPresent(System.out::println);  // 3
		
		// Mapping of primitive streams to object streams
		IntStream.range(1, 4)
	    .mapToObj(i -> "a" + i)
	    .forEach(System.out::println);
		
		// ended here, I would try yet parallelStream, but when executing
		// average for a big double stream, all logical CPU cores were loaded.
		
		CompareHashMapAndStream compObj = new CompareHashMapAndStream();
		compObj.findUsingGetKey();
		compObj.calculateHashCode();
		compObj.findUsingIntStramFilter();
		compObj.findUsingGetKeyInStringKeys();
		// compObj.findUsingStringStreamFilter(); -- this is very very slow
		
		compObj.filterAndListUsingStrStream();
		compObj.filterAndListUsingGet();
		compObj.printStatisticsInfo();
		
		int i=10;
		while(i>0) {
			compObj.findUsingGetKey();
			compObj.findUsingGetKeyInStringKeys();
			i-=1;
			System.out.println("I:" + i);
			// Thread.sleep(2000);
		}
		compObj.printStatisticsInfo();
		/*
		 * Single run with:
		 * 10 000 000 size of map
		 * search ran 100 times
		 * 
		 * Searching in intMap using contains, get took: 18 [ms]
		 * Calculate hash codes took: intMap 267 [ms], strMap 481 [ms], strMapSmall 0 [ms]
         * Searching in intMap using IntStream plus filter, get took: 35502 [ms]
         * Searching in strMap using contains, get took: 10 [ms]
         * Searching in strMap using string stream, reduced 10x took: 165620 [ms]
         * Filtering in strMapSmall from StrObjec using streams took: 7 [ms]
         * Filtering in strMapSmall from StrObjec using map.values listing took: 3 [ms]
		 * 
		 * In order to verify also Just In time compilation
		 * we run two searches 10 times
		 * Searching in intMap using contains, get took: 12 [ms]
         * Searching in strMap using contains, get took: 10 [ms]
		 * 
		 * Conclusion:
		 * -----------
		 * Using streams on really big data HashMaps
		 * is sub-optimal.
		 * Using small String or Integer keys is
		 * comparable and recommended.
		 * 
		 */
	}

}


class CompareHashMapAndStream {
	/*
	 * Create a bit bigger concurrent hash map
	 * first with integer keys, then with String keys
	 * Then find there entry and retrieve it.
	 * 1. Using hasKey, getKey
	 * 2. Using streams for both
	 * 
	 * Result of run with size of the ConcurrentHashMap
	 * 10 millions records
	 * Performing 100*10 searches
	 * 
	 * Searching in intMap using contains, get took: 22 [ms]
	 * Searching in intMap using IntStream plus filter, get took: 30365 [ms]
     * Searching in strMap using contains, get took: 14 [ms]
     * Searching in strMap using string stream, reduced 10x took: 193643 [ms]
     * (which would be 193 sec,  3.2 min *10 -> 0.5hour)
     * 
     * Added comparison stream and direct listing in HashMap
     * and get values.
     * 
     * Searching in intMap using contains, get took: 20 [ms]
     * Calculate hash codes took: intMap 303 [ms], strMap 575 [ms], strMapSmall 0 [ms]
     * Searching in intMap using IntStream plus filter, get took: 38267 [ms]
     * Searching in strMap using contains, get took: 11 [ms]
     * Filtering in strMapSmall from StrObjec using streams took: 5 [ms]
     * Filtering in strMapSmall from StrObjec using map.values listing took: 3 [ms]
     * 
	 * */
	private ConcurrentHashMap<Integer, String> intMap = new  ConcurrentHashMap<Integer, String>();
	private ConcurrentHashMap<String, String> strMap = new ConcurrentHashMap<String, String>();
	private ConcurrentHashMap<String, StrObject> strMapSmall = new ConcurrentHashMap<String, StrObject>();
	private int size = 10000000;
	private int sizeSmall = 1000;
	private String statisticsInfo;
	private final int [] toBeFound = {5846875, 5346875, 584687, 6875, 6846875, 46875, 584, 8, 5877875, 5846975};
	
	public CompareHashMapAndStream() {
		IntStream.range(0, size).forEachOrdered(x -> intMap.put(x, String.format("String: %d", x)));
		IntStream.range(0, size).forEachOrdered(x -> strMap.put(String.format("%d", x), String.format("String: %d", x)));
		IntStream.range(0, sizeSmall).forEachOrdered(x -> strMapSmall.put(String.format("%d", x), new StrObject(
				String.format("String:%d", x)))
				);
		
		System.out.println(String.format("intMap size: %d", intMap.size()));
		System.out.println(String.format("strMap size: %d", strMap.size()));
		
	}
	
	public void findUsingGetKey() {
		long start = System.currentTimeMillis();
		for (int j=0; j< 100; j++) {
			for (int i: toBeFound) {
				if (intMap.containsKey(i)) {
					System.out.println(String.format("Found int key using containsKey: %s", intMap.get(i)));
				}
			}
		}
		
		long end = System.currentTimeMillis();
		System.out.println(String.format("Searching in intMap using contains, get took: %d [ms]", 
				end - start));
		statisticsInfo = String.format("%n%nSearching in intMap using contains, get took: %d [ms]", 
				end - start);
	}
	
	public void calculateHashCode() {
		long start = System.currentTimeMillis();
		intMap.hashCode();
		long endIntHashCode = System.currentTimeMillis();
		strMap.hashCode();
		long endStrHashCode = System.currentTimeMillis();
		strMapSmall.hashCode();
		long endStrObjectHashCode = System.currentTimeMillis();
		
		long end = System.currentTimeMillis();
		System.out.println(String.format("Calculate hash codes took: intMap %d [ms], strMap %d [ms], strMapSmall %d [ms]", 
				endIntHashCode - start, 
				endStrHashCode - endIntHashCode,
				endStrObjectHashCode - endStrHashCode
				));
		
		statisticsInfo = String.format("%s%n%s",statisticsInfo,
				(String.format(
						"Calculate hash codes took: intMap %d [ms], strMap %d [ms], strMapSmall %d [ms]", 
						endIntHashCode - start, 
						endStrHashCode - endIntHashCode,
						endStrObjectHashCode - endStrHashCode
						)
				));
		
	}
	
	public void findUsingIntStramFilter() {
		long start = System.currentTimeMillis();
		for (int j=0; j< 100; j++) {
			for (int i: toBeFound) {
				System.out.println("Found int key using stream filter:" + 
						intMap.keySet().stream().filter(n -> n > i).findFirst().get());
			}
		}
		
		long end = System.currentTimeMillis();
		System.out.println(String.format("Searching in intMap using IntStream plus filter, get took: %d [ms]", 
				end - start));
		statisticsInfo = String.format("%s%n%s",statisticsInfo,
				(String.format("Searching in intMap using IntStream plus filter, get took: %d [ms]", 
						end - start))
				);
	}
	
	public void findUsingGetKeyInStringKeys() {
		long start = System.currentTimeMillis();
		String iStr;
		for (int j=0; j< 100; j++) {
			for (int i: toBeFound) {
				iStr = String.format("%s", i);
				if (strMap.containsKey(iStr)) {
					System.out.println(String.format("Found string key using containsKey: %s", strMap.get(iStr)));
				}
			}
		}
		
		long end = System.currentTimeMillis();
		System.out.println(String.format("Searching in strMap using contains, get took: %d [ms]", 
				end - start));
		
		statisticsInfo = String.format("%s%n%s",statisticsInfo,
				(String.format("Searching in strMap using contains, get took: %d [ms]", 
						end - start))
				);
		
	}
	
	public void findUsingStringStreamFilter() {
		long start = System.currentTimeMillis();
		for (int j=0; j< 10; j++) {
			for (int i: toBeFound) {
				// iStr = String.format("%s", i); - this is not allowed in String stream filtering - varianle out of scope
				System.out.println("Found int key using string stream filter:" + 
						strMap.keySet().stream().filter((s) -> s.equals(String.format("%s", i))).findFirst().get()
						);
			}
		}
		
		long end = System.currentTimeMillis();
		System.out.println(String.format("Searching in strMap using string stream, reduced 10x took: %d [ms]", 
				end - start));
		
		statisticsInfo = String.format("%s%n%s",statisticsInfo,
				(String.format("Searching in strMap using string stream, reduced 10x took: %d [ms]", 
						end - start))
				);
	}
	
	public void filterAndListUsingStrStream() {
		long start = System.currentTimeMillis();
		List<String> res = strMapSmall.values().stream()
							.map(n -> n.getValue())
							.filter(n -> n.split(":")[1].toString().startsWith("1"))							
							.collect(Collectors.toList());
		for(String in:res) {
			System.out.println(
					String.format("Filtered string object: %s", in) //.getValue())
					);
		}
		long end = System.currentTimeMillis();
		statisticsInfo = String.format("%s%n%s",statisticsInfo,
				(String.format("Filtering in strMapSmall from StrObjec using streams took: %d [ms]", 
						end - start))
				);
	}
	
	public void filterAndListUsingGet() {
		long start = System.currentTimeMillis();
		List<String> res = new ArrayList<String>();
		
		for(StrObject in: strMapSmall.values()) {
			if(in.getValue().split(":")[1].toString().startsWith("1")) {
				res.add(in.getValue());
			}
		}

		for(String in:res) {
			System.out.println(
					String.format("Found string object: %s", in) //.getValue())
					);
		}
		
		long end = System.currentTimeMillis();
		statisticsInfo = String.format("%s%n%s",statisticsInfo,
				(String.format("Filtering in strMapSmall from StrObjec using map.values listing took: %d [ms]", 
						end - start))
				);
	}
	
	
	public void printStatisticsInfo() {
		System.out.println(this.statisticsInfo);
	}
	
	class StrObject {
		private String str;
		
		public StrObject(String in) {
			str = in;
		}
		
		public String getValue() {
			return str;
		}
	}
}
