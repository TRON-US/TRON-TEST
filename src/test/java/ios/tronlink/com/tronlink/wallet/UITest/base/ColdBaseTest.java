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
    @BeforeClass(groups = {"P0"},alwaysRun = true)
    public void setUpBefore(String privateKey,String bundleId,String udid) throws Exception {
        DRIVER.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        Map<String, Object> params = new HashMap<>();
        params.put("bundleId", bundleId);
        try {
            final boolean wasRunningBefore = (Boolean)DRIVER.executeScript("mobile: terminateApp", params);
            TimeUnit.SECONDS.sleep(2);
            DRIVER.findElement(By.name("设置")).click();
            DRIVER.findElement(By.name("无线局域网")).click();
            TimeUnit.SECONDS.sleep(2);
            DRIVER.findElementByClassName("XCUIElementTypeSwitch").click();
            DRIVER.navigate().back();
        } catch (Exception e) {
        }
        log("网络设置完成");
        DRIVER.executeScript("mobile: activateApp", params);
        new Helper().importFirstWallet(Helper.importType.coldWallet,privateKey,DRIVER);
        log("已经导入完成----BeforeClass");
    }

    @Parameters({"bundleId","udid"})
    @AfterClass(groups = {"P0"},alwaysRun = true)
    public void tearDownAfterClass(String bundleId,String udid) {
        DRIVER.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
//        DRIVER.closeApp();
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("bundleId", bundleId);
            final boolean wasRunningBefore = (Boolean)DRIVER.executeScript("mobile: terminateApp", params);
            TimeUnit.SECONDS.sleep(2);
            DRIVER.findElement(By.name("设置")).click();
            DRIVER.findElement(By.name("无线局域网")).click();
            TimeUnit.SECONDS.sleep(2);
            DRIVER.findElementByClassName("XCUIElementTypeSwitch").click();
//            TimeUnit.SECONDS.sleep(2);
            DRIVER.navigate().back();
        } catch (Exception e) {
        }
        DRIVER.quit();

    }

    //    @Parameters({"bundleId"})
//    @BeforeMethod(groups = {"P0"},alwaysRun = true)
//    public void beforeMethod(String bundleId,Method method) throws Exception {
//        log("BeforeMethod");
//        DRIVER.launchApp();
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: " + method.getName());
//
//    }
    @Parameters({"bundleId"})
    @BeforeMethod(groups = {"P0"},alwaysRun = true)
    public void beforeMethod(String bundleId,Method method) throws Exception {
        log("beforeMethod");

        Map<String, Object> params = new HashMap<>();
        params.put("bundleId", bundleId);
        DRIVER.executeScript("mobile: terminateApp", params);
        int tries = 0;
        Boolean driver_is_start = false;
        while (!driver_is_start && tries < 5) {
            tries++;
            try {
                DRIVER.executeScript("mobile: activateApp", params);
                driver_is_start = true;
            } catch (Exception e) {
                System.out.println(e);
                TimeUnit.SECONDS.sleep(2);
            }
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: " + method.getName());

    }
    @Parameters({"bundleId"})
    @AfterMethod(groups = {"P0"},alwaysRun = true)
    public void afterMethod(Method method, String bundleId)  {
        log("AfterMethod");
//        try {
//            DRIVER.closeApp();
//            DRIVER.activateApp(bundleId);
//
//        } catch (Exception e) {
//        }

    }



}
