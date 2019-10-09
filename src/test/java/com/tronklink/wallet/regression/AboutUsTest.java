package com.tronklink.wallet.regression;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import wallet.UITest.base.Base;

/**
 * 关于我们功能测试
 */
public class AboutUsTest extends Base {

//    @BeforeClass
//    public void setUpBeforeClass() throws Exception {
//        setUp();
//    }

    @BeforeMethod()
    public void setUpBefore() throws Exception{
        DRIVER.closeApp();
        DRIVER.launchApp();
        getSign();
    }

    @AfterClass
    public void tearDownAfterClass() {
        DRIVER.quit();
    }


}
