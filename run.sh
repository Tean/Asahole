#!/bin/bash
echo 'app: '"'$1'"
app=`echo $1|sed -e 's/^ *| *$//'`
echo 'trimproc: '"'$app'"
if [ "$app" == "" ]
then
	echo "\$1 cannot null, app required"
	exit
fi

nohup java -jar $app >/dev/null 2>&1 &
echo $app' start success'