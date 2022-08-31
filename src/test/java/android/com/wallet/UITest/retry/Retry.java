package android.com.wallet.UITest.retry;

import android.com.wallet.UITest.base.Base;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class Retry implements IRetryAnalyzer {
    private int retryCount = 0;
    public boolean retry(ITestResult result) {
        int maxRetryCount = new Base().RetryAgainTimes;
        if (retryCount < maxRetryCount) {
            retryCount++;
            System.out.println("Retry Times:" + retryCount + " Test: " + result.getMethod().getMethodName() );
            if(retryCount == 1){
                String[] timestamp = result.getTestClass().getName().split("\\.");
                ScreenShot(timestamp[timestamp.length-1],result.getMethod().getMethodName());
            }
            return true;
        }
        System.err.println("Giving up after " + maxRetryCount + " failures");

        return false;
    }

    public void ScreenShot(String className, String methodName) {

        System.out.println("ScreenShot-----" );
        try {
            DateFormat dateFormat = new SimpleDateFormat("MM-dd-hh-mm-ss");
            String path =   className +"."+ methodName + dateFormat.format(new Date()) + ".png";

            Runtime.getRuntime().exec("mkdir /Users/tron/Desktop/screenshot");
            Runtime.getRuntime().exec("adb shell screencap -p /sdcard/screen.png");
            TimeUnit.SECONDS.sleep(1);
            Runtime.getRuntime().exec("adb pull /sdcard/screen.png  /Users/tron/Desktop/screenshot/" + path);

        }catch (Exception e){
            System.out.println("exec Fail");
        }
    }


}