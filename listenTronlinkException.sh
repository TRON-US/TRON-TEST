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
  nohup /Users/tron/Library/Android/sdk/platform-tools/adb -s $deviceName logcat | grep " E " | grep -E 'Fatal|Crash|AndroidRuntime' | grep -E 'com.tronlinkpro.wallet'  > exception_$deviceName.log 2>&1 &
  echo "adb nohup"
done
