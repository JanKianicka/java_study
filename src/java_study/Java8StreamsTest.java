package java_study;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.IntStream.Builder;
import java.util.stream.Stream;

/*
 * Examples from Java 8 Streams Tutorial
 * http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
 * 
 */

public class Java8StreamsTest {

	public static void main(String[] args) {
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
		compObj.findUsingIntStramFilter();
		compObj.findUsingGetKeyInStringKeys();
		compObj.findUsingStringStreamFilter();
		compObj.printStatisticsInfo();
		
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
	 * */
	private ConcurrentHashMap<Integer, String> intMap = new  ConcurrentHashMap<Integer, String>();
	private ConcurrentHashMap<String, String> strMap = new ConcurrentHashMap<String, String>();
	private int size = 10000000;
	private String statisticsInfo;
	private final int [] toBeFound = {5846875, 5346875, 584687, 6875, 6846875, 46875, 584, 8, 5877875, 5846975};
	
	public CompareHashMapAndStream() {
		IntStream.range(0, size).forEachOrdered(x -> intMap.put(x, String.format("String: %d", x)));
		IntStream.range(0, size).forEachOrdered(x -> strMap.put(String.format("%d", x), String.format("String: %d", x)));
		
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
	
	public void printStatisticsInfo() {
		System.out.println(this.statisticsInfo);
	}	
}
