#ps -ef | grep  listenTronlinkException.sh | grep -v grep | awk '{print $2}' | xargs kill -9
#ps -ef | grep  "adb logcat" | grep -v grep | awk '{print $2}' | xargs kill -9
devices_info=`/Users/tron/Library/Android/sdk/platform-tools/adb devices`
devicesArray=(${devices_info// /})
declare -a devicesList
local_dir=`pwd`
cd $local_dir
rm -rf exceptionLogs
mkdir exceptionLogs
cd exceptionLogs
count=0
for deviceName in ${devicesArray[@]}
do
   result=$(echo $deviceName | grep "device")
   if [[ "$result" == "" ]]
   then
      devicesList[count]=$deviceName
      count=$[$count+1]
   fi
done
for deviceName in ${devicesList[@]}
do
  echo $deviceName
  rm -rf todaylog_$deviceName.log
  /Users/tron/Library/Android/sdk/platform-tools/adb -s $deviceName logcat > todaylog_$deviceName.log 2>&1 &
  cat todaylog_$deviceName.log | grep -E "HTTP FAILED" -A 10 | grep "tronlink" -A 9 > http_failed_$deviceName.log 2>&1 &
  cat todaylog_$deviceName.log | grep -E "FATAL" -A 30 | grep "tronlink" -A 29  > FATAL_exception_$deviceName.log 2>&1 &
  cp -rf todaylog_$deviceName.log /Users/tron/Documents/ExceptionLogs/Day_`date +%Y%m%d%H%M%S`_log.log
  rm -rf todaylog_$deviceName.log
#  nohup /Users/tron/Library/Android/sdk/platform-tools/adb -s $deviceName logcat | grep " E " -A 35 | grep  'FATAL|Crash|AndroidRuntime' -A 35 | grep -A 35 'com.tronlinkpro.wallet'  > exception_$deviceName.log 2>&1 &
#  echo "adb nohup"
done
