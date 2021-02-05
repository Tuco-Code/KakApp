#!/bin/bash

touch ../EXE/programData/blabla.class
find ../EXE/programData -name "*.class" -type f -delete

javac -d ../EXE/programData -cp . MAIN.java
cp ../DATA/media/*.png ../EXE/programData/presentacio/

echo Program Compiled!
