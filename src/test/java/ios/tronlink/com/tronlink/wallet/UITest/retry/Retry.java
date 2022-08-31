package ios.tronlink.com.tronlink.wallet.UITest.retry;

import ios.tronlink.com.tronlink.wallet.UITest.base.Base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


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


    public void ScreenShot(String clasname, String methodname){
        //调用截图功能
        try {
            DateFormat dateFormat = new SimpleDateFormat("MM-dd-hh-mm-ss");
            String path = "build/tmp/" + clasname +"."+ methodname + dateFormat.format(new Date()) + ".png";
            System.out.println(path);
            Runtime.getRuntime().exec("idevicescreenshot " + path);
        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
}