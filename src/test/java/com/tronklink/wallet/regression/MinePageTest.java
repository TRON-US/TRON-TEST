package com.tronklink.wallet.regression;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import common.utils.Helper;
import wallet.UITest.base.Base;
import org.testng.annotations.*;
/**
 * 我的页面功能测试
 */
public class MinePageTest extends Base {



    @Parameters({"privateKey"})
    @BeforeClass()
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey,DRIVER);
    }

    @AfterMethod
    public void afterMethod(){
        DRIVER.closeApp();
        DRIVER.activateApp("com.tronlink.wallet");
    }

    @AfterClass
    public void tearDownAfterClass() {
        DRIVER.quit();
    }




}
