#!/bin/sh
#list of devices to consider , $1 is argument from command line

ideviceinstaller -u $1 --uninstall com.apple.test.WebDriverAgentRunner-Runner
#wait for 18 sec
Sleep 18s
done