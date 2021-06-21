package ui.mobile.utils.retry;

import org.testng.ITestResult;
import org.testng.util.RetryAnalyzerCount;


public class TestRetryAnalyzer extends RetryAnalyzerCount{
    public TestRetryAnalyzer(){
        setCount(1);
    }
    @Override
    public boolean retryMethod(ITestResult arg0) {
        //TODO Auto-generated method stub
        return true;
    }
}