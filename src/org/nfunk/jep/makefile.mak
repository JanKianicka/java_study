#Id            :  $Id: makefile.mak,v 1.1 2005/04/08 09:28:15 matog Exp $
#Status        :  $Date: 2005/04/08 09:28:15 $

# Sem dopiseme nazov adresara, v ktorom sa nachadza class, ktoru chceme kompilovat
# (je lepsie pouzivat v ceste lomitka namiesto spatnych lomitok)
DIR=
#Sem dopiseme nazov (bez pripony) Java class, ktoru chceme kompilovat, a ktora vola native metody 
MAIN=
# Sem dopiseme pripadne meno package-u v ktorom sa nachadza Java class, ktora vola nativne metody
PACKAGE=

# Sem dopiseme nazvy zdrojakov nativnych metod (piseme bez pripony.cpp, ak ich je viac,
# oddelujeme ich medzerou) 
NATIVE=

# Sem dopiseme nazov dynamickej kniznice, ktoru loadujeme v Java classe (bez pripony)
DLIB=
# Sem dopiseme cestu k adresaru, v ktotom chceme mat ulozenu nasu dynamicku kniznicu (napr. p:/jlib/bin/)
DLLWINDIR=
DLLLINDIR=
# Sem dopiseme pripadne .o (.obj) subory nativnych kniznic, ktore su pouzivane nativnymi metodami (bez
# pripony .o resp. .obj, predpoklad je, ze pod Windowsami bude pripona kniznic .obj, pod Linuxom
# bude pripona .o, ak je kniznic viac, oddelujeme ich medzerou)
MYNATIVELIB=

# YoYo: Libs to pass to linker (i.e. -lssl, -lnetapi32, etc.)
WINLIBS=
LINLIBS=

# Sem dopiseme dalsie kniznice, ktore chceme prilinkovat, piseme ich aj s cestou, priponami...Prva
# premenna je pre kompilovanie pod windowsami, druha pod linuxom
OTHERWINLIBS=
OTHERLINLIBS=

#******************************************************************************************************#
#                                     koniec manualneho nastavovania                                   #
#******************************************************************************************************#

CPPFLAGSWIN = -Wall -mno-cygwin  -I/cygdrive/c/java/include -I/cygdrive/c/java/include/win32 -Wl,--add-stdcall-alias -shared 
CPPFLAGSLIN = -Wall -c -I/usr/local/jdk1.5.0/include -I/usr/local/jdk1.5.0/include/linux -fPIC
LDFLAGSLIN = -shared -W1

ONATIVES=$(foreach nat,$(NATIVE),$(nat).o)
NATIVES=$(foreach natcpp,$(NATIVE),$(natcpp).cpp)
MYNATIVELIBSLIN=$(foreach nat,$(MYNATIVELIB),$(nat).o)
MYNATIVELIBSWIN=$(foreach nat,$(MYNATIVELIB),$(nat).obj)
#zistenie operacneho systemu
OSTYPE1=$(findstring Windows,$(OS))
OSTYPE2=$(findstring linux,$(OSTYPE))
OSTYPE3=$(findstring Windows,$(OSTYPE))
OSTYPE4=$(findstring linux,$(OS))

ifneq ($(strip $(PACKAGE)),)
	PACKAGEDOT=$(PACKAGE).
endif


ifeq ($(OSTYPE1),Windows)
  TOS=Windows
endif

ifeq ($(OSTYPE3),Windows)
  TOS=Windows
endif

ifeq ($(OSTYPE2),linux)
  TOS=linux
endif

ifeq ($(OSTYPE4),linux)
  TOS=linux
endif

#nastavenie premennych podla typu OS
ifeq ($(TOS),Windows)
  DLL=$(DLIB).dll
	MYNATIVELIBS=$(MYNATIVELIBSWIN)
  JAVAC=c:\java\bin\CompileJava.bat
  JAVAH=c:\java\bin\javah
else
	MYNATIVELIBS=$(MYNATIVELIBSLIN)
  DLL=lib$(DLIB).so
  JAVAC=javac
  JAVAH=javah
endif

All: $(MAIN).class $(DLL)

#vytvorenie dynamickej kniznice
ifeq ($(TOS),Windows)
$(DLL): $(MAIN).h $(NATIVES) $(MYNATIVELIBS) $(OTHERWINLIBS)
	g++ $(CPPFLAGSWIN) -o $(DLLWINDIR)$(DLL) $(NATIVES) $(MYNATIVELIBS) $(OTHERWINLIBS) $(WINLIBS) 
else
$(DLL): $(ONATIVES) $(MYNATIVELIBS) $(OTHERLINLIBS)
	g++ $(LDFLAGSLIN) -o $(DLLLINDIR)$(DLL) $(ONATIVES) $(MYNATIVELIBS) $(OTHERLINLIBS) $(LINLIBS) 
$(ONATIVES): $(MAIN).h $(NATIVES)
	g++ $(CPPFLAGSLIN) $(NATIVES)
endif

# Kompilovanie javy
ifeq ($(TOS),Windows)
$(MAIN).class: $(MAIN).java
	$(JAVAC) $(DIR)$(MAIN).java
else
$(MAIN).class: $(MAIN).java
	$(JAVAC) $(MAIN).java
endif


# Vytvorenie hlavickoveho suboru, ktory includujeme v nasich nativnych metodach	
$(MAIN).h: $(MAIN).java 
	$(JAVAH) -jni -o $(MAIN).h $(PACKAGEDOT)$(MAIN) 
