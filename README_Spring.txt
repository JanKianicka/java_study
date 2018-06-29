# Here are the notes from the Spring Java tutorial.
Important abrevations:
JTA - Java Transaction API (https://en.wikipedia.org/wiki/Java_Transaction_API)
MDV - Model-View-Controler (MDV) (https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller)
Struts - Apache Struts for creating modern Java Web Applications (https://struts.apache.org/)
POJO - Plain Old Java Object (https://en.wikipedia.org/wiki/Plain_old_Java_object).
DI - Dependency Injection, is one type of
IoC - Inversion of Control concepts.
AOP - Aspect Oriented Programming where 'unit of modularity' is aspect.
OOP - Object Oriented Programming where 'unit of modularity' is class.

Architecture:
    Core container -
         - Core
	 - Bean
	 - Context
	 - SpEL - expression language for querrying and manupylating object graph
    Data Access/Integration -
         - JDBC - Java Database Connectivity Abstraction layer
	 - ORM - Object Relational Mapping - amond others Hibernate
	 - OXM - Object XML mapping
	 - JMS - Java Messaging Service
	 - Transactions
    Web
         - Web
	 - Web-MVC
	 - Web-Socket
	 - Web-Portlet
    Miscellaneous
         - AOP
	 - Aspects - acess to AspectJ
	 - Instrumentation
	 - Messaging - support for STOMP
	 - Test - JUnit and TestNG

Successfully created HelloWorld spring projects
based on standard Java project without automatically managed dependencies.

Localy installed spring and appache logging:
c:\spring\libs\
c:\commons-logging-1.2\

Project:
java_study/spring_hello

We have ended up with:
https://www.tutorialspoint.com/spring/spring_ioc_containers.htm


