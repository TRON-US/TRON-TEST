package android.com.wallet.UITest.retry;

import android.com.wallet.UITest.base.Base;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


public class Retry implements IRetryAnalyzer {
    private int retryCount = 0;
    public boolean retry(ITestResult result) {
        int maxRetryCount = new Base().RetryAgainTimes;
        if (retryCount < maxRetryCount) {
            retryCount++;
            System.out.println("Retry #" + retryCount + " for test: " + result.getMethod().getMethodName() + ", on thread: " + Thread.currentThread().getName());
            return true;
        }
        System.err.println("giving up after " + maxRetryCount + " failures");
        System.err.println("screenshot as ... png in pic");
        //调用截图功能

        return false;
    }
}