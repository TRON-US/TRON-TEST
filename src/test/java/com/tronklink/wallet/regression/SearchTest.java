package com.tronklink.wallet.regression;


import com.tronlink.wallet.UITest.base.Base;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

/**
 * 搜索功能测试
 * 在行情页面->搜索
 */
public class SearchTest extends Base {

    @BeforeClass
    public void setUpBeforeClass() throws Exception {
        setUp();
    }

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
