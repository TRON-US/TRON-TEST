sleep 5
ps -ef | grep  listenTronlinkException.sh | grep -v grep | awk '{print $2}' | xargs kill -9
ps -ef | grep  "adb logcat" | grep -v grep | awk '{print $2}' | xargs kill -9
rm -rf exceptionLogs
rm -rf /Users/tron/Documents/tronlink_task/logs/exceptionLogs
cp -rf exceptionLogs /Users/tron/Documents/tronlink_task/logs/exceptionLogs
echo "Finish tronlink task"