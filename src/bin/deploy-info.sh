#!/bin/bash

DIRNAME=`dirname "$0"`
YUKAR_HOME=`cd "$DIRNAME/.."; pwd`

(cd $YUKAR_HOME/yukar-baseline;
 cmd /c jboss-cli -c --commands="deployment-info","quit";
)
