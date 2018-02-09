package java_study;

import java.util.Arrays;
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
		
	}

}
