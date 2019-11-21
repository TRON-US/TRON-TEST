package android.com.tronlink.wallet.regression;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;

import org.testng.annotations.*;

/**
 * 发现页面功能测试
 */
public class FindPageTest extends Base {

    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        DRIVER.closeApp();
        DRIVER.activateApp("com.tronlink.wallet");
    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }


}
