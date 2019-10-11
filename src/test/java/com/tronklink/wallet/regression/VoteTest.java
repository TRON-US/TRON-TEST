package com.tronklink.wallet.regression;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import wallet.UITest.base.Base;
import org.testng.annotations.*;
/**
 * 投票功能测试
 */
public class VoteTest extends Base {

//    @BeforeClass
//    public void setUpBeforeClass() throws Exception {
//        setUp();
//    }

    @Parameters({"privateKey"})
    @BeforeMethod()
    public void setUpBefore(String privateKey) throws Exception{
        DRIVER.closeApp();
        DRIVER.launchApp();
        getSign(privateKey);
    }

    @AfterClass
    public void tearDownAfterClass() {
        DRIVER.quit();
    }



}
