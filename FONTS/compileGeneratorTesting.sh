#!/bin/bash

touch ../EXE/testingGenerador/blabla.class
find ../EXE/testingGenerador -name "*.class" -type f -delete

javac -d ../EXE/testingGenerador -cp . dominio/GeneradorDriver.java

echo Program Compiled!
