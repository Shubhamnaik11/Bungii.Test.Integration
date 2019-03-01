#!/bin/sh
sentence="$0"",device2"
sentence=${sentence//,/$'\n'}
for device in $sentence
do
#echo "$device"
query=".connection.$device"
echo "${query}"
OUTPUT="$(cat $1 | jq ${query})"
echo "${OUTPUT}"
appium -p ${OUTPUT}  &
done