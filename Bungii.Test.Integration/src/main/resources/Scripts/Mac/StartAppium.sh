#!/bin/sh
sentence="$1"",device2"
echo "senetence $sentence"
sentence=${sentence//,/$'\n'}
for device in $sentence
do
query=".connection.$device"
echo "${query}"
OUTPUT="$(cat $2 | jq ${query})"
echo "${OUTPUT}"
appium -p ${OUTPUT}  &
done
