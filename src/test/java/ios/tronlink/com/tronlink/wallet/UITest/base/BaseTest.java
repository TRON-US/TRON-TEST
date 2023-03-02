package ios.tronlink.com.tronlink.wallet.UITest.base;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MinePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.NodeSetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.SettingPage;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseTest extends Base {
    public enum importType {normal,coldWallet,coldShieldWallet,shieldWallet}

    @Parameters({"privateKey","bundleId"})
    @BeforeClass(groups = {"P0"},alwaysRun = true)
    public void setUpBefore(String privateKey,String bundleId) throws Exception {
        log("BaseTest --Begin");
        restartApp(bundleId);
        log("BaseTest Import ---start");
        importFirstWallet(importType.normal,privateKey);
        log("BaseTest Import ---Success");
    }

    @Parameters({"bundleId"})
    @AfterMethod(groups = {"P0"},alwaysRun = true)
    public void afterMethod(Method method, String bundleId) throws Exception {
        TimeUnit.SECONDS.sleep(2);
        Map<String, Object> params = new HashMap<>();
        params.put("bundleId", bundleId);
        DRIVER.executeScript("mobile: terminateApp", params);
        TimeUnit.SECONDS.sleep(2);

    }

    @Parameters({"bundleId"})
    @BeforeMethod(groups = {"P0"},alwaysRun = true)
    public void beforeMethod(String bundleId,Method method) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("bundleId", bundleId);
        int tries = 0;
        Boolean driver_is_start = false;
        while (!driver_is_start && tries < 5) {
            tries++;
            try {
                DRIVER.executeScript("mobile: activateApp", params);
                driver_is_start = true;
            } catch (Exception e) {
                System.out.println(e);
                DRIVER.executeScript("mobile: terminateApp", params);
                TimeUnit.SECONDS.sleep(2);
            }
        }
        TimeUnit.SECONDS.sleep(2);
    }

    @Parameters({"bundleId"})
    @AfterClass (groups = {"P0"},alwaysRun = true)
    public void afterClass(String bundleId) throws Exception {

        try {
            DRIVER.quit();
        } catch (Exception e) {
        }

//        Map<String, Object> params = new HashMap<>();
//        params.put("bundleId", bundleId);
//        DRIVER.executeScript("mobile: terminateApp", params);
//        TimeUnit.SECONDS.sleep(2);
    }




    public void guaranteeDAppChain() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        if(Helper.assetFindMainChain(asset)) {
            MinePage mine = asset.enterMinePage();
            SettingPage set =  mine.enterSettingPage();
            set.changeToDAppChain();
            TimeUnit.SECONDS.sleep(1);
            set.enterMinePage();
            mine.enterAssetPage();
        }
    }

    public  void guaranteeShastaChain(String udid) throws  Exception{
        AssetPage asset = new AssetPage(DRIVER);
        if (asset.chainNameLabel.getText().contains("Shasta Testnet")){

        }else {
            MinePage mine = asset.enterMinePage();
            SettingPage set = mine.enterSettingPage();
            set.changeToShastaChain();
            TimeUnit.SECONDS.sleep(1);
            restartApp(udid);

        }
    }

    public void restartApp(String bundleId) throws Exception{
        System.out.println("restartApp1");
        Map<String, Object> params = new HashMap<>();
        params.put("bundleId", bundleId);
        DRIVER.executeScript("mobile: terminateApp", params);
        TimeUnit.SECONDS.sleep(6);
        System.out.println("restartApp2");
        DRIVER.executeScript("mobile: activateApp", params);
        TimeUnit.SECONDS.sleep(6);

    }

    public void restartApp() throws Exception{
        System.out.println("restartApp1");
        Map<String, Object> params = new HashMap<>();
        params.put("bundleId", "com.tronlink.hdwallet");
        DRIVER.executeScript("mobile: terminateApp", params);
        TimeUnit.SECONDS.sleep(6);
        System.out.println("restartApp2");
        DRIVER.executeScript("mobile: activateApp", params);
        TimeUnit.SECONDS.sleep(6);

    }

    public String timeYMD(){
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        return  ft.format(dNow);
    }


    public void importFirstWallet(importType type, String privateKey) throws Exception{
        DRIVER.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(3);
        Boolean haveImport = isElementExist("walletName");
        System.out.println("Imported State: " + haveImport);
        if(!haveImport){
            importFirstWallet(type,privateKey,"Auto_test","Test0001");
        }
    }

    public void importFirstWallet(importType type, String privateKey, String name, String pass) throws Exception{
        DRIVER.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        switch (type){
            case normal:
            {
                findWebElement("导入钱包").click();
                findAcceptAndClick();
                break;
            }
            case coldWallet:
            {
                findWebElement("冷钱包").click();
                DRIVER.findElement(By.name("选择此模式")).click();
                findAcceptAndClick();
                break;
            }
        }

        importUsePrivateKey(privateKey,name,pass);
        TimeUnit.SECONDS.sleep(6);
        Boolean haveImport = isElementExist("trxLabel") ;
        System.out.println("Imported State: " + haveImport);
        if(!haveImport) {
            for (int i = 0; i < 3; i++) {
                haveImport = isElementExist("walletName");
                System.out.println("Try Import Wallet Address: " + haveImport + "Times： " + i);
                if(!haveImport){
                    findWebElement("导入钱包").click();
                    findAcceptAndClick();
                    importUsePrivateKey(privateKey,name,pass);
                    restartApp("com.tronlink.hdwallet");
                    TimeUnit.SECONDS.sleep(7);
                }else {
                    break;
                }
            }
        }


    }

    public void importUsePrivateKey(String privatekey,String name,String pass){
        try {
            DRIVER.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
            DRIVER.findElementByClassName("XCUIElementTypeTextField").sendKeys(privatekey);
            closeKeyBoard();
            findWebElement("下一步").click();
            slideScreenLitter(DRIVER);
            TimeUnit.SECONDS.sleep(1);
            DRIVER.findElementByClassName("XCUIElementTypeTextField").clear();
            DRIVER.findElementByClassName("XCUIElementTypeTextField").sendKeys(name);
            closeKeyBoard();
            WebElement pass1 = DRIVER.findElementsByClassName("XCUIElementTypeSecureTextField").get(0);
            WebElement pass2 = DRIVER.findElementsByClassName("XCUIElementTypeSecureTextField").get(1);
            pass1.sendKeys("Test0001");
            closeKeyBoard();
            pass2.sendKeys("Test0001");
            closeKeyBoard();
            findWebElement("导入私钥").click();
            TimeUnit.SECONDS.sleep(10);
            try {
                if (DRIVER.findElementByName("备份资产").isDisplayed()){
                    DRIVER.findElementByName("备份资产").click();
                    DRIVER.findElementById("black path").click();
                }
            }catch (Exception es){}

        }catch (Exception e){}
    }


    public WebElement findWebElement(String element) throws Exception {
        int tries = 0;
        Boolean Element_is_exist = false;
        WebElement el = null;
        while (!Element_is_exist && tries < 3) {
            tries++;
            try {
                el = DRIVER.findElementByName(element);
                Element_is_exist = true;
            }catch (NoSuchElementException e){
                DRIVER.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
            }
        }
        if(el != null){
            return  el;
        }else {
            el = DRIVER.findElementById(element);
            return el;
        }

    }


    public void findAcceptAndClick(){
        DRIVER.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        try {
            WebElement accBtn = DRIVER.findElementByName("接受");
            while (!accBtn.isEnabled()) {
                IOSTouchAction action = new IOSTouchAction(DRIVER);
                int width = DRIVER.manage().window().getSize().width;
                int height = DRIVER.manage().window().getSize().height;
                Duration duration = Duration.ofMillis(30);
                action.press(
                                PointOption.point(width/2, height*4/5))
                        .waitAction(WaitOptions.waitOptions(duration))
                        .moveTo(PointOption.point(width/2, height/5))
                        .release().perform();
            }
            accBtn.click();
        }catch (Exception e){
            DRIVER.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        }
        DRIVER.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
    }

    public static void slideScreenLitter(IOSDriver<?> driver){
        IOSTouchAction action = new IOSTouchAction(driver);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        Duration duration = Duration.ofMillis(200);
        action.press(
                        PointOption.point(width/2, height*4/5))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(width/2, height*3/5))
                .release().perform();
    }
}
