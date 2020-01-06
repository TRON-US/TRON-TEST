package ios.tronlink.com.tronlink.wallet.UITest.base;

import ios.tronlink.com.tronlink.wallet.utils.Helper;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;

public class BaseTest extends Base {

    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(Method methed) throws Exception {
        try {

            String name = this.getClass().getSimpleName() + "." +
                    methed.getName();

            log(name);
            screenshotAction(name);
            DRIVER.closeApp();
            DRIVER.launchApp();
        } catch (Exception e) {
        }

    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {

        try {
            DRIVER.quit();
        } catch (Exception e) {
        }

    }

}
