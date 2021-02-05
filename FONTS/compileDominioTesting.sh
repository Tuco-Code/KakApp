#!/bin/bash

touch ../EXE/testingDominio/blabla.class
find ../EXE/testingDominio -name "*.class" -type f -delete

javac -d ../EXE/testingDominio -cp . dominio/ControladorDominioDriver.java

echo Program Compiled!
