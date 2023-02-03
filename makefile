JC =javac
.SUFFIXES:.java .class
.java.class:
	$(JC) $*.java

CLASSES = \
	Auto.java

classes:$(CLASSES:.java=.class)

clean:\
	$(RM) *.class