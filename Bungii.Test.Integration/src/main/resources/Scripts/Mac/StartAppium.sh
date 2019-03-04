#!/bin/sh
#list of devices to consider , $1 is argument from command line
devicelist="$1"",device2"
echo "Devicelist : $devicelist"
devicelist=${devicelist//,/$'\n'}
for device in $devicelist
do

#json parser expression to fetch Appium port and wda port
queryAppiumPort=".connection.$device"
querywdaPort=".$device.wdaLocalPort"

# get Appium port and wda port
APPIUMPORT="$(cat $2 | jq ${queryAppiumPort})"
WDAPORT="$(cat $2 | jq ${querywdaPort})"

#Log file name
today=`date '+%Y_%m_%d__%H_%M_%S'`;
filename="/Users/for-bungiiqa/Documents/Bungii-AppiumLogs/AppuimLogs_$today.log"

#start Appium server with parameter
echo "Starting Appium server forDevice :$device with details , WDAPORT:${WDAPORT} , APPIUMPORT:${APPIUMPORT} , filename:${filename} "
appium -p ${APPIUMPORT} --webdriveragent-port ${WDAPORT} --log-timestamp --local-timezone --log ${filename} &

#wait for 5 sec
Sleep 5s
done