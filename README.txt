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




 
 


