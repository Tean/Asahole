#!/bin/bash
echo 'p:'$*
if [ "$*" == "" ]
then
	echo "regex \$* cannot null"
	exit
fi

parm=`echo $*| sed 's/  */\\\|/g'`
proc=`ps haux|grep $parm|awk '{print $2}'`
pid=`echo $proc| sed 's/  */\ \\\|\ /g'`
res="lsof -nP -iTCP |grep ' "$pid" '"
eval $res