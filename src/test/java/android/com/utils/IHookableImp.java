package android.com.utils;

import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.internal.ConstructorOrMethod;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IHookableImp implements IHookable {
    private SimpleDateFormat timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


    @Override
    public void run(IHookCallBack iHookCallBack, ITestResult iTestResult) {
        ConstructorOrMethod method = iTestResult.getMethod().getConstructorOrMethod();
        String name = method.getName();
        String time = timeStamp.format(new Date()).toString();
        System.out.println( " 测试method是 : " + name + " 开始执行~ " + time);
        //测试用例开始执行
        iHookCallBack.runTestMethod(iTestResult);
        String endTime = timeStamp.format(new Date()).toString();
        System.out.println( " method :" + name + " 结束~ " + time);
    }
}
