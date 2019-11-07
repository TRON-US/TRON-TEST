package ios.tronlink;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ios.tronlink.com.tronlink.wallet.utils.Helper;

import static org.junit.Assert.assertEquals;

public class DemoTest extends IosBase {
//    @Parameters({"privateKey"})
//    @BeforeMethod()
//    public void setUpBefore(String privateKey) throws Exception{
//        DRIVER.closeApp();
//        DRIVER.launchApp();
//        getSign(privateKey);
//    }

//    @BeforeClass()
//    public void setUpBefore(String privateKey) throws Exception {
//        Helper.getSign(privateKey,DRIVER);
//    }
//
//    @AfterMethod
//    public void afterMethod(){
//        DRIVER.closeApp();
//        DRIVER.activateApp("com.tronlink.wallet");
//    }

    @Parameters({"privateKey"})
    @BeforeClass()
    public void setUpBefore(String privateKey) throws Exception {
        System.out.print("status:" + DRIVER.getBatteryInfo().getState() + "\n");
        DRIVER.getBatteryInfo().getState();
        //DRIVER.getBatteryInfo().toString();
        new IosHelper().getSign(privateKey,DRIVER);
    }
/*
    @AfterMethod
    public void afterMethod(){
        DRIVER.closeApp();
        DRIVER.activateApp("com.android.com.tronlink.hdwallet");
    }

    @AfterClass
    public void tearDownAfterClass() {
        //Base.tearDownAfterClass();
        DRIVER.quit();
    }*/

    @Parameters({"privateKey"})
    @Test //测试是否正常进行投票页
    public void test01_checkPopularSearch(String privateKey) throws Exception {

        new Helper().getSign(privateKey,DRIVER);
        System.out.print(DRIVER.getBatteryInfo());

    }



}
