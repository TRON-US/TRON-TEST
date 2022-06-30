sleep 5
ps -ef | grep  listenTronlinkException.sh | grep -v grep | awk '{print $2}' | xargs kill -9
ps -ef | grep  "adb logcat" | grep -v grep | awk '{print $2}' | xargs kill -9

exceptionLogs_dir="exceptionLogs_`date +%Y%m%d%H%M%S`_dir"
cp -rf exceptionLogs /Users/tron/Documents/ExceptionLogs/$exceptionLogs_dir
find /Users/tron/Documents/ExceptionLogs/ -mtime +20 -name "*_*_dir" -exec rm -rf {} \;
echo "Finish android.com.tronlink task"