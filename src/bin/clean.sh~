#!/bin/bash

LIBS="coding root testing framework"

for LIB in $LIBS;
do
    (cd yukar-$LIB; mvn clean install -Dmaven.test.skip=true;)
done;
