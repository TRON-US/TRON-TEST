package com.tronlink.wallet.ios;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.utils.IosHelper;
import wallet.UITest.base.IosBase;
import wallet.pages.AssetPage;
import wallet.pages.SendTrxPage;
import wallet.pages.VotePage;

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
        DRIVER.activateApp("com.tronlink.hdwallet");
    }

    @AfterClass
    public void tearDownAfterClass() {
        //Base.tearDownAfterClass();
        DRIVER.quit();
    }*/


    @Test //测试是否正常进行投票页
    public void test01_checkPopularSearch() throws Exception {

        System.out.print(DRIVER.getBatteryInfo());
    }



}
