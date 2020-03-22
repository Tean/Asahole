#!/bin/bash
echo 'proc: '"'$1'"
proc=`echo $1|sed -e 's/^ *| *$//'`
echo 'trimproc: '"'$proc'"
if [ "$proc" == "" ]
then
	echo "\$1 cannot null, port required"
	exit
fi

pid=`ps -ef|grep 'java -jar'|grep $proc|awk '{print $2}'`
if [ "$pid" == "" ]
then
	echo "\$pid of $proc not found, confirm again"
	exit
fi
kill -9 $pid
echo $pid' of '$proc' stoped'