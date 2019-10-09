package com.tronklink.wallet.regression;


import com.tronlink.wallet.UITest.base.Base;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 资产页面测试
 */
public class AssetsPageTest extends Base {

    @BeforeClass
    public void setUpBeforeClass() throws Exception {
        setUp();
        //Base.getSign();
    }

    @BeforeMethod()
    public void setUpBefore() throws Exception{
        DRIVER.closeApp();
        DRIVER.launchApp();
        getSign();
    }

    @AfterClass
    public void tearDownAfterClass() {
        //Base.tearDownAfterClass();
        DRIVER.quit();
    }



}
