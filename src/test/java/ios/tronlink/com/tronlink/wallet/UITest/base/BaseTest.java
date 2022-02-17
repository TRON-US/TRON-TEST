package ios.tronlink.com.tronlink.wallet.UITest.base;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.offset.PointOption;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseTest extends Base {

    @Parameters({"privateKey"})
    @BeforeClass(groups = {"P0"},alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
//        log("setUpBefore");
        new Helper().importFirstWallet(Helper.importType.normal,privateKey,DRIVER);
        log("TestClass Import ---Over");

    }

    @Parameters({"bundleId"})
    @AfterMethod(groups = {"P0"},alwaysRun = true)
    public void afterMethod(Method methed, String bundleId) throws Exception {
//    log("afterMethod");
    }

    @Parameters({"bundleId"})
    @BeforeMethod(groups = {"P0"},alwaysRun = true)
    public void beforeMethod(String bundleId,Method method) throws Exception {
//        log("beforeMethod");
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

    public  boolean isElementExist( String name) {
        IOSDriver driver = DRIVER;
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        try {
            driver.findElementByName(name);
            System.out.println("IsFindByName: "+name);
            driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
            return  true;
        }catch (org.openqa.selenium.NoSuchElementException ex){
            try {
                driver.findElementById(name);
                System.out.println("IsFindById: "+name);
                driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
                return  true;
            }catch (org.openqa.selenium.NoSuchElementException eex){
                try {
                    if (driver.findElementByClassName("XCUIElementTypeButton").getText().contains(name)){
                        System.out.println("IsFindByBtn: "+name);
                        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
                        return  true;
                    }else {
                        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
                        return  false;
                    }
                }catch (org.openqa.selenium.NoSuchElementException e){
                    System.out.println("NotFound: "+name);
                    driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
                    return  false;
                }
            }
        }
    }

    public  void closeKeyBoard()  throws Exception{
        IOSDriver driver = DRIVER;

        try {
            driver.findElementByName("Done").click();
        }catch (Exception e){
            try {
                driver.findElementByName("完成").click();

            }catch (Exception el){
                System.out.println("not found keyboard done");
                TouchAction action = new TouchAction(driver);
                PointOption whiteplace = PointOption.point(8,200);
                action.tap(whiteplace).perform().release();
            }
        }

    }


}
