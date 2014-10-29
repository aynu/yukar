#!/bin/bash

DIRNAME=`dirname "$0"`
YUKAR_HOME=`cd "$DIRNAME/.."; pwd`

(cd $YUKAR_HOME/yukar-baseline; mvn clean install -Dmaven.test.skip=true;)
