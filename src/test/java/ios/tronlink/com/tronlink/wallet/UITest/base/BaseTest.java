package ios.tronlink.com.tronlink.wallet.UITest.base;

import ios.tronlink.com.tronlink.wallet.utils.Helper;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseTest extends Base {

    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        log("我是类之间BaseTest的BeforeClass");
        new Helper().getSign(privateKey, DRIVER);
    }


    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {

        try {
            DRIVER.quit();
        } catch (Exception e) {
        }

    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(Method methed) throws Exception {
        try {

            String name = this.getClass().getSimpleName() + "." +
                    methed.getName();
            screenshotAction(name);
            DRIVER.closeApp();
        } catch (Exception e) {
        }

    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() throws Exception {
        int tries = 0;
        Boolean driver_is_start = false;
        while (!driver_is_start && tries < 5) {
            tries++;
            try {
                DRIVER.launchApp();
                driver_is_start = true;
            } catch (Exception e) {
                System.out.println(e);
                TimeUnit.SECONDS.sleep(2);
            }
        }
    }


    //    @AfterMethod(alwaysRun = true)
//    public void afterMethod(Method methed) throws Exception {
//        try {
//
//            String name = this.getClass().getSimpleName() + "." +
//                    methed.getName();
//            screenshotAction(name);
//            DRIVER.closeApp();
//            DRIVER.launchApp();
//        } catch (Exception e) {
//        }
//
//    }


}
