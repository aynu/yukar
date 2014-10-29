#!/bin/bash

DIRNAME=`dirname "$0"`
YUKAR_HOME=`cd "$DIRNAME/.."; pwd`

(cd $YUKAR_HOME/yukar-baseline;
 cmd /c jboss-cli -c --commands="undeploy yukar-baseline-bep-0.0.1-SNAPSHOT.ear","undeploy yukar-baseline-fep-0.0.1-SNAPSHOT.ear","quit";
)
