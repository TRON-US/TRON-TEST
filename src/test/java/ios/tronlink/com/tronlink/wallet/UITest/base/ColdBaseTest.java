package ios.tronlink.com.tronlink.wallet.UITest.base;

import android.com.utils.AppiumTestCase;

import ios.tronlink.com.tronlink.wallet.utils.Helper;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class ColdBaseTest extends Base {

    @Parameters({"privateKey","bundleId","udid"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey,String bundleId,String udid) throws Exception {

        DRIVER.closeApp();
        log("开始移除app");
        AppiumTestCase.cmdReturn("ideviceinstaller -U com.tronlink.hdwallet -u " + udid); //00008020-000D04D62132002E ideviceinstaller -U com.tronlink.hdwallet -u
        log("开始安装app");
        AppiumTestCase.cmdReturn("ideviceinstaller -i Tronlink.ipa -u " + udid);
        log("开始导入ownerPrivatekey");
        DRIVER.closeApp();
        DRIVER.launchApp();

        log("我是类之间BaseTest的BeforeClass");
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("bundleId", bundleId);
            final boolean wasRunningBefore = (Boolean)DRIVER.executeScript("mobile: terminateApp", params);
            //DRIVER.closeApp();
            TimeUnit.SECONDS.sleep(2);
            DRIVER.findElement(By.name("设置")).click();
            //DRIVER.findElement(By.name("无线局域网")).click();
            DRIVER.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
            TimeUnit.SECONDS.sleep(2);
//            DRIVER.findElementByIosNsPredicate("label CONTAINS '飞行模式' AND name == '飞行模式' AND enabled == true").click();
            DRIVER.findElementByClassName("XCUIElementTypeSwitch").click();
            DRIVER.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
            TimeUnit.SECONDS.sleep(2);
            //findWebElement("无线局域网").click();
            int tries = 0;
            Boolean driver_is_start = false;
            while (!driver_is_start && tries < 5) {
                tries++;
                try {
                    Map<String, Object> params1 = new HashMap<>();
                    params1.put("bundleId", bundleId);
                    DRIVER.executeScript("mobile: activateApp", params1);
                    //DRIVER.launchApp();
                    driver_is_start = true;
                } catch (Exception e) {
                    System.out.println(e);
                    TimeUnit.SECONDS.sleep(2);
                }
            }
        } catch (Exception e) {
        }


        new Helper().getColdSign(privateKey, DRIVER);
    }

    @Parameters({"bundleId"})
    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass(String bundleId) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("bundleId", bundleId);
            final boolean wasRunningBefore = (Boolean)DRIVER.executeScript("mobile: terminateApp", params);
            //DRIVER.closeApp();
            TimeUnit.SECONDS.sleep(2);
            DRIVER.findElement(By.name("设置")).click();
            //DRIVER.findElement(By.name("无线局域网")).click();
            DRIVER.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
            TimeUnit.SECONDS.sleep(2);
//            DRIVER.findElementByIosNsPredicate("label CONTAINS '飞行模式' AND name == '飞行模式' AND enabled == true").click();
            DRIVER.findElementByClassName("XCUIElementTypeSwitch").click();
            DRIVER.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
            TimeUnit.SECONDS.sleep(2);
            DRIVER.findElement(By.name("好")).click();
            DRIVER.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
            TimeUnit.SECONDS.sleep(2);
            DRIVER.quit();
        } catch (Exception e) {
        }

    }

    @Parameters({"bundleId"})
    @AfterMethod(alwaysRun = true)
    public void afterMethod(Method methed, String bundleId) throws Exception {
        try {

            String name = this.getClass().getSimpleName() + "." +
                    methed.getName();
            screenshotAction(name);
            Map<String, Object> params = new HashMap<>();
            params.put("bundleId", bundleId);
            final boolean wasRunningBefore = (Boolean)DRIVER.executeScript("mobile: terminateApp", params);
            //DRIVER.closeApp();
        } catch (Exception e) {
        }

    }

    @Parameters({"bundleId"})
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(String bundleId) throws Exception {
        int tries = 0;
        Boolean driver_is_start = false;
        while (!driver_is_start && tries < 5) {
            tries++;
            try {
                Map<String, Object> params = new HashMap<>();
                params.put("bundleId", bundleId);
                DRIVER.executeScript("mobile: activateApp", params);
                //DRIVER.launchApp();
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
