#!/bin/bash
debug=true
echo 'p:'$*
if [ "$*" == "" ]
then
	echo "regex \$* cannot null, can be pid/pname"
	exit
fi

parm=`echo $*| sed 's/  */\\\|/g'`
if [ $debug == true ]
then
	echo 'parm: '$parm
fi

proc=`ps haux|grep $parm|awk '{print $2}'`
if [ $debug == true ]
then
	echo 'procid: '$proc
fi

pid=`echo $proc| sed 's/  */\ \\\|\ /g'`

res="lsof -nP -iTCP |grep ' "$pid" '"
eval $res
