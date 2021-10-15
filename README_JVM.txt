# Notes from the general Java Study.
First I read:
0.JVM-Architecture.pdf

Java Virtual Machine converts Java bytecode into the machine language
and is opart of Java Runtime Environment.

Between host system and Java Source code byte code is 'intermediary
language'.

Parts of JVM:
1. Class Loader
JVM Memory:
2. Method Area
3. Heap
4. JVM Language Stacks
5. PC Registers
6. Native Method Stacks

7. Execution engine
8. Native Method Interface
9. Native Method Libraries

#Details
1. Class loader
   - loading
   - linking
   - initialization

2. Method Area
   - metadata
   - constant runtime pool
   - code for methods

3. Heap
   - objects, instance variables, arrays
   Shared among the threads.

4. JVM Language Stacks
   - local variables and partial results
   Each thread has its own stack.

5. PC Registers
   - stores JVM instructions
   Each thread has its own PC Register.

6. Native Method Stacks
   - instructions of native method code - e.g. C code instructions
   (JNI - Java Native Interface)

7. Execution engine
   - testing software???

8. JNI - Java Native Interface
   - allows java code to be called by other native code

Now finish the first document and continue to:
JVMS-SE5.0-Ch3-Overview.pdf
1. Class instruction translation
   - JVM is using two kinds of internal data types: prmitive type, reference type
   Primitive types: byte, short, int, long, char, float, double,
   - boolean, return address
   Reference types:
     1. Class type
     2. Array type
     3. Interface type
   (and null reference)
   Runtime Data Area:
     1. Each thread has its own Program Counter - PC Register.
     (If the method currently executed is not native, it contains the address of the instruction.)
     2. Java Virtual Machine Stacks - stores frames, like in C - holds local variables and pertial results.
        StackOverflow error in fixed size stack systems.
     3. Heap - The heap is created on virtual machine start-up. Heap storage for objects is
        reclaimed by an automatic storage management system (known as a garbage collector);
	objects are never explicitly deallocated.
     4. Method area -
        The method area is analogous to the storage area for compiled
	code of a conventional language or analogous to the “text”
	segment in a UNIX process.  It stores per-class structures
	such as the runtime constant pool, field and method data, and
	the code for methods and constructors, including the special
	methods.
	The method area is created on virtual machine start-up.
     5. Runtime Constant Pool
        is a per-class or per-interface runtime representation of the
	constant_pool table in a class file (§4.4). It contains several kinds of constants,
	ranging from numeric literals known at compile time to method and field references
	that must be resolved at run time.
	Is allcated in Method Area of the memory.
     6. Native Method Stacks
     	An implementation of the Java virtual machine may use conventional stacks, colloquially
	called “C stacks,” to support native methods, methods written in a language
	other than the Java programming language.
	Native method stacks are typically allocated per thread when each thread is created.
	
Ended in chapter 3.6 Frames.


    
   

