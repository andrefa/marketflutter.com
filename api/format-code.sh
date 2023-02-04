#!/bin/bash -e

if [ "$1" == "check" ]; then
    PARAMS="check"
else
    PARAMS="apply"    
fi

mvn spotless:${PARAMS}