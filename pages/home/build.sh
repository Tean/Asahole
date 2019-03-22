#!/bin/bash
res=`go build`
if [ "$1" == "" ];then
    echo "none"
else
    if [ ! -d "./$1/" ];then
        mkdir ./$1
    fi
fi
echo $res