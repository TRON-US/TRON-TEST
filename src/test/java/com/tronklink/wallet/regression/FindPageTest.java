package com.tronklink.wallet.regression;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import common.utils.Helper;
import wallet.UITest.base.Base;
import org.testng.annotations.*;
/**
 * 发现页面功能测试
 */
public class FindPageTest extends Base {

    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey,DRIVER);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(){
        DRIVER.closeApp();
        DRIVER.activateApp("com.tronlink.wallet");
    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        DRIVER.quit();
    }





}
