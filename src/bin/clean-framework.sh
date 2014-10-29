#!/bin/bash

DIRNAME=`dirname "$0"`
YUKAR_HOME=`cd "$DIRNAME/.."; pwd`
LIBS="coding root testing framework"

for LIB in $LIBS;
do
    (cd $YUKAR_HOME/yukar-$LIB; mvn clean;)
done;
