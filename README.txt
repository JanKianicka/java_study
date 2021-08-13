# This is the study of Java from this tutorial:
# https://www.tutorialspoint.com/java

1. We are now here:
https://www.tutorialspoint.com/java/java_basic_syntax.htm

Running hello world from command line:
> cd /home/local/kianicka/repositories/var_study/java_study/src
> export CLASSPATH="/home/local/kianicka/repositories/var_study/java_study/src"
> javac java_study/MyFirstJavaProgram.java
> java java_study/MyFirstJavaProgram
Hello World

On windows to set and see envirnmental variables:
C:\Users\janki>echo %path%
C:\Users\janki>echo %classpath%
But did not make command promt java running on my f. windows.

I have made it running in cygwin:
janki@nod293 /cygdrive/c/repo/java_study/src/java_study
$ export CLASSPATH="."
$ java java_study/MyFirstJavaProgram
Hello World

And windows CMD:
C:\repo\java_study\src\java_study>echo %classpath%\
.;C:\repo\java_study\src\sungeom_java;C:\repo\java_study\src\java_study;\

C:\repo\java_study\src\java_study>java java_study/MyFirstJavaProgram
Hello World

and it is possible to use also dot:
C:\repo\java_study\src\java_study>java java_study.MyFirstJavaProgram
Hello World

And on windows it is possible to set classpath like this:
set CLASSPATH=C:\users\jack\java\classes

And I have successfully also executed the program for calculation of sungeometry in
the raster, hurrah:
C:\repo\java_study\src\sungeom_java>javac -d . sungeom.java
C:\repo\java_study\src\sungeom_java>javac -d . Sunrise_globe.java
C:\repo\java_study\src\sungeom_java>javac -d . Sunrise_globe.java


Crucial realities in Java:
- identifiers (names)
- modifiers
- variables
- arrays
- enums

- reserved words:
abstract 	assert 	boolean 	break
byte 	case 	catch 	char
class 	const 	continue 	default
do 	double 	else 	enum
extends 	final 	finally 	float
for 	goto 	if 	implements
import 	instanceof 	int 	interface
long 	native 	new 	package
private 	protected 	public 	return
short 	static 	strictfp 	super
switch 	synchronized 	this 	throw
throws 	transient 	try 	void
volatile 	while

Operators:
- Precedence of Java Operators
Category 	Operator 	Associativity
1. Postfix 	expression++ expression-- 	Left to right
2. Unary 	++expression –-expression +expression –expression ~ ! 	Right to left
3. Multiplicative 	* / % 	Left to right
4. Additive 	+ - 	Left to right
5. Shift 	<< >> >>> 	Left to right
6. Relational 	< > <= >= instanceof 	Left to right
7. Equality 	== != 	Left to right
8. Bitwise AND 	& 	Left to right
9. Bitwise XOR 	^ 	Left to right
10. Bitwise OR 	| 	Left to right
11. Logical AND 	&& 	Left to right
12. Logical OR 	|| 	Left to right
13. Conditional 	?: 	Right to left
14. Assignment 	= += -= *= /= %= ^= |= <<= >>= >>>= 	Right to left



Here I miss whole block of study which I conducted at home.
We continue in:
https://www.tutorialspoint.com/java/java_methods.htm
 - Interesting:
 1. Overloading of the method - the same name, but different parameters
 2. Overriding of the method - when using inheritance
 3. Finalize method is called before destruction by the garbage collector.
    protected void finalize( ) {
      // finalization code here
    }
    
java.io
Input stream, output stream
- byte stream in 8-bit
- standard input, standard output, standard console
Streams are: File, BiteArray, Filter (two sorts data and buffered), Object
Ended here:
https://www.tutorialspoint.com/java/java_files_io.htm
we need to experiment yet with File, FileReader, FileWriter classes.

Java Data Structures:
- Enumeration
- BitSet
- Vector
- Stack
- Dictionary
- Hashtable
- Properties
All these classes are now legacy and Java-2 has introduced a new framework called Collections Framework, which is discussed in the next chapter.

Collections types:
java.util.LinkedList - AbstractSequentialList and implements the List interface
java.util.ArrayList - The ArrayList class extends AbstractList and implements the List interface. 
                      ArrayList supports dynamic arrays that can grow as needed.
java.util.HashSet - HashSet extends AbstractSet and implements the Set interface. 
                    It creates a collection that uses a hash table for storage.
java.util.LinkedHashSet - LinkedHashSet maintains a linked list of the entries in the set, in the order in which they were inserted. 
                          This allows insertion-order iteration over the set.
                          That is, when cycling through a LinkedHashSet using an iterator, 
                          the elements will be returned in the order in which they were inserted.

Network protocol types:
- TCP/IP - Transmission Control Protocol/Internet Protocol
- UDP - User Datagram Protocol

Procedure:
1. Server instantiates a ServerSocket object, denoting which port number communication is to occur on.
2. The server invokes the accept() method of the ServerSocket class.
3. After the server is waiting, a client instantiates a Socket object, specifying the server name and the port number to connect to.
4. The constructor of the Socket class attempts to connect the client to the specified server and the port number.
5. On the server side, the accept() method returns a reference to a new socket.

java.net.ServerSocket class is used by server applications to obtain a port and listen for client requests.
java.net.Socket class represents the socket that both the client and the server use to communicate with each other.


Procedure of porting the java_study project to different Eclipse and workspace:
Two issues have been encountered:
1. That java package was not resolved as package, but as a mere folder, and 'default package' was created.
This had to be resolved by proper set up in:
 '-> project properties -> Java Build Path -> Source
 Keep there only two paths:
 java_study/spring_hello/src
 java_study/src
 with no exclusion, whereas these directories are translated into CLASSPATH

2. Second issue was conflict in JNI, java versions and this is resolved by:
- on the level of workspace mast match the default JRE and Java compliance level
  '-> Java - Compiler - complience level set to 1.8
  '-> JRE
- second option is on the level of the project preferences
  '-> Java Build path - Libraries - proper set-up of the execution environment


 Hurah, hurah, afer this learning on my own mistakes, I could 
 complile my study project in the end.
 
# 6.8.2021 Mstep - Refreshement of Java basic knowledge - I commence to go again through all these lesssons.
- today finished on https://www.tutorialspoint.com/java/java_nonaccess_modifiers.htm - transient and 
  tried try-catch with resources.
  
  
