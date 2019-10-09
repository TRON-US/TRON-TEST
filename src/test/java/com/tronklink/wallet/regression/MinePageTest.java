package com.tronklink.wallet.regression;


import com.tronlink.wallet.UITest.base.Base;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

/**
 * 我的页面功能测试
 */
public class MinePageTest extends Base {

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
