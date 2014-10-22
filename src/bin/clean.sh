#!/bin/bash

LIBS="coding root testing framework"

for LIB in $LIBS;
do
    (cd yukar-$LIB; mvn clean;)
done;
