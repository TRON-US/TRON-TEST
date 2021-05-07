package ios.tronlink.com.tronlink.wallet.utils;


import io.appium.java_client.TouchAction;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MinePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.NodeSetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.SettingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Helper {

    public enum importType {normal,coldWallet,coldShieldWallet,shieldWallet}
    private SimpleDateFormat timeStamp = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss ");

    public IOSDriver DRIVER = null;

    public static  boolean assetFindMainChain(AssetPage assetPage) {
        try {
            assetPage.driver.findElementById("chainNameLabel");
            if(assetPage.chainNameLabel.getText().contains("MainChain")){
                return  true;
            }else {
                return  false;
            }

        }catch (org.openqa.selenium.NoSuchElementException ex){
            System.out.println("NoSuchElementSlowlyFound Chain Name");

            for (int i = 0; i < assetPage.textArray.size(); i++) {

                if (assetPage.textArray.get(i).getText().contains("MainChain")) {
                    return true;
                }
                if (assetPage.textArray.get(i).getText().contains("DAppChain")) {
                    return false;
                }
            }
            return  true;
        }
    }

    public static  boolean fastFindMainChain(List<WebElement> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getText().contains("MainChain")){
                return  true;
            }
            if (list.get(i).getText().contains("DAppChain")){
                return  false;
            }
        }
        return  true;
    }

    public static boolean contentTexts(List<WebElement> list, String name) {
        if (list.size() < 1) return  false;
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Totalsize:" + list.size() + " itme:" + i + "name:" + list.get(i).getText());
            if (list.get(i).getText().contains(name)){
                return  true;
            }
        }
        return  false;
    }
    public static boolean contentTextsFromDown(List<WebElement> list, String name) {
        for (int i = list.size() - 1 ; i > -1; i--) {
            System.out.println("Totalsize:" + list.size() + " itme:" + i + "name:" + list.get(i).getText());
            if (list.get(i).getText().contains(name)){
                return  true;
            }
        }
        return  false;
    }

    public static void tapWhitePlace(IOSDriver driver)  throws Exception{
        TouchAction action = new TouchAction(driver);
        PointOption whiteplace = PointOption.point(8,166);
        action.tap(whiteplace).perform().release();
    }
    public static void closeKeyBoard(IOSDriver driver)  throws Exception{
        try {
            driver.findElementById("完成").click();
        }catch (Exception e){
            TouchAction action = new TouchAction(driver);
            PointOption whiteplace = PointOption.point(6,130);
            action.tap(whiteplace).perform().release();
        }

    }

    public static String getPrettyNumber(String number) {
        return BigDecimal.valueOf(Double.parseDouble(number))
                .stripTrailingZeros().toPlainString();
    }

    public static boolean isElementExist(IOSDriver driver,String name) {
        try {
            driver.findElementByName(name);
            System.out.println("IsFindByName: "+name);
            return  true;
        }catch (org.openqa.selenium.NoSuchElementException ex){
            try {
                driver.findElementById(name);
                System.out.println("IsFindById: "+name);

                return  true;
            }catch (org.openqa.selenium.NoSuchElementException eex){
                try {
                    if (driver.findElementByClassName("XCUIElementTypeButton").getText().contains(name)){
                        System.out.println("IsFindByBtn: "+name);
                        return  true;
                    }else {
                        return  false;
                    }
                }catch (org.openqa.selenium.NoSuchElementException e){
                    System.out.println("NotFound: "+name);
                    return  false;
                }
            }
        }
    }
    public static boolean containElement(WebElement wl,String name) {
        try {
            wl.findElement(By.name(name));
            return  true;
        }catch (org.openqa.selenium.NoSuchElementException ex){
            try {
                wl.findElement(By.id(name));
                return  true;
            }catch (org.openqa.selenium.NoSuchElementException eex){
                try {
                    WebElement btn = wl.findElement(By.className("XCUIElementTypeButton"));
                    if(btn.getText() == name){
                        return  true;
                    }else {
                        return  false;
                    }
                }catch (org.openqa.selenium.NoSuchElementException evex){
                    return  false;
                }
            }
        }
    }


    public static boolean guaranteeMainChain(IOSDriver driver) throws Exception {
        AssetPage asset = new AssetPage(driver);
        if(assetFindMainChain(asset)){
            return true;
        }else{
            MinePage mine = asset.enterMinePage();
            SettingPage set = mine.enterSettingPage();
            NodeSetPage nodeSet = set.enterNodeSetPage();
            SettingPage settingPage = nodeSet.enterSettingPageChoiseMainChain();
            boolean ismain = fastFindMainChain(settingPage.textArray);
            mine = settingPage.enterMinePage();
            mine.enterAssetPage();
            return  ismain;
        }
    }
    public static boolean guaranteeDappChain(IOSDriver driver) throws Exception {
        AssetPage asset = new AssetPage(driver);
        if(assetFindMainChain(asset)){
            MinePage mine = asset.enterMinePage();
            SettingPage set = mine.enterSettingPage();
            NodeSetPage nodeSet = set.enterNodeSetPage();
            SettingPage settingPage = nodeSet.enterSettingPageChoiseDappChain();
            mine = settingPage.enterMinePage();
            mine.enterAssetPage();
        }
        return true;


    }
    public static void switchToReleaseNet(IOSDriver driver) throws Exception {
        AssetPage asset = new AssetPage(driver);
        MinePage minePage = asset.enterMinePage();
        SettingPage settingPage = minePage.enterSettingPage();
        settingPage.switchToRelease();
        minePage = settingPage.enterMinePage();
        minePage.enterAssetPage();
    }

    public static void switchToDevNet(IOSDriver driver) throws Exception {
        AssetPage asset = new AssetPage(driver);
        MinePage minePage = asset.enterMinePage();
        SettingPage settingPage = minePage.enterSettingPage();
        settingPage.switchToDev();
        minePage = settingPage.enterMinePage();
        minePage.enterAssetPage();
    }



    public void findAcceptAndClick(){
        try {
            DRIVER.findElement(By.name("接受"));
            while (!findWebElement("接受").isEnabled()) {
                IOSTouchAction action = new IOSTouchAction(DRIVER);
                int width = DRIVER.manage().window().getSize().width;
                int height = DRIVER.manage().window().getSize().height;
                Duration duration = Duration.ofMillis(200);
                action.press(
                        PointOption.point(width/2, height*4/5))
                        .waitAction(WaitOptions.waitOptions(duration))
                        .moveTo(PointOption.point(width/2, height/5))
                        .release().perform();
            }
            findWebElement("接受").click();
        }catch (Exception e){
        }
    }

    public void  importWatchShieldWallet(String udid,String nsk,String ak,String ovk,String shieldAddress,IOSDriver driver) throws Exception{
        this.DRIVER = driver;
        if(!isElementExist(DRIVER,"home manager")) {
            importFirstWatchShieldWallet("WSW",nsk,ak,ovk,shieldAddress);
        }
    }

    public void importFirstWallet(importType type,String privateKey, IOSDriver driver) throws Exception{
        this.DRIVER = driver;
        System.out.println(timeStamp.format(new Date()).toString());
        Boolean haveImport = isElementExist(DRIVER,"home manager");
        System.out.println(timeStamp.format(new Date()).toString());
        System.out.println("haveImportedValue: " + haveImport);
        if(!haveImport){
            try{
                DRIVER.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
                driver.findElementByName("允许").click();
            }catch (Exception ee){
                System.out.println("alert Not Found!!!");
            }
            importFirstWallet(type,privateKey,"Auto_test","Test0001");
        }
    }
    //导入正常的,需要报错信息不能使用此方法
    public  void importMoreWallet(importType type,String privateKey,String name,String pass,IOSDriver driver) throws Exception{
        this.DRIVER = driver;
        WebElement addwalletBtn = driver.findElementById("home manager");
        switch (type){
            case normal:
            case coldWallet: {
                addwalletBtn.click();
                try {
                    driver.findElementById("normalWallet").click();
                }catch (Exception nooutput){

                }
                break;
            }
        }

        importUsePrivateKey(privateKey,name,pass);
    }

    public void importFirstWallet(importType type,String privateKey,String name,String pass) throws Exception{

        DRIVER.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);

        switch (type){
            case normal:
            {
                findWebElement("导入钱包").click();
                findAcceptAndClick();
                try {
                    DRIVER.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
                    DRIVER.findElementById("normalWallet").click();
                }catch (Exception nooutput){

                }

                break;
            }
            case coldWallet:
            {
                findWebElement("冷钱包").click();
                DRIVER.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
                DRIVER.findElement(By.name("选择此模式")).click();
                findAcceptAndClick();
                try {
                    DRIVER.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
                    DRIVER.findElementById("normalWallet").click();
                }catch (Exception nooutput){

                }

                break;
            }
            case shieldWallet:
            {
                findWebElement("导入钱包").click();
                findAcceptAndClick();
                DRIVER.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
                DRIVER.findElementById("shieldedWallet").click();
                break;
            }
        }

        importUsePrivateKey(privateKey,name,pass);

    }

    public void importFirstWatchShieldWallet(String name,String nsk,String ak,String ovk,String shieldAddress) throws Exception{
        DRIVER.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        findWebElement("导入钱包").click();
        findAcceptAndClick();
        DRIVER.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        DRIVER.findElementById("shieldedWallet").click();
        importWatchShieldWallet(name, nsk, ak, ovk, shieldAddress);

    }

    public void importWatchShieldWallet(String name,String nsk,String ak,String ovk,String shieldAddress) {
        try {
            DRIVER.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            findWebElement("观察钱包").click();
            DRIVER.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            DRIVER.findElementById("card_one_textview").sendKeys(nsk);
            closeKeyBoard(DRIVER);
            DRIVER.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            DRIVER.findElementById("card_two_textview").sendKeys(ak);
            closeKeyBoard(DRIVER);
            DRIVER.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            DRIVER.findElementById("card_three_textview").sendKeys(ovk);
            closeKeyBoard(DRIVER);
            DRIVER.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            DRIVER.findElementById("card_four_textview").sendKeys(shieldAddress);
            closeKeyBoard(DRIVER);
            DRIVER.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            findWebElement("下一步").click();
            DRIVER.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            DRIVER.findElementByClassName("XCUIElementTypeTextField").sendKeys(name);
            tapWhitePlace(DRIVER);
            findWebElement("确定").click();

            TimeUnit.SECONDS.sleep(10);
        } catch (Exception e) {
        }

    }
    public void importUsePrivateKey(String privatekey,String name,String pass){
        try {
            DRIVER.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
            findWebElement("私钥").click();
            DRIVER.findElementByClassName("XCUIElementTypeTextView").sendKeys(privatekey);
            tapWhitePlace(DRIVER);
            findWebElement("下一步").click();
            DRIVER.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
            DRIVER.findElementByClassName("XCUIElementTypeTextField").sendKeys(name);
            tapWhitePlace(DRIVER);
            findWebElement("下一步").click();
            DRIVER.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
            DRIVER.findElementByClassName("XCUIElementTypeSecureTextField").sendKeys(pass);
            tapWhitePlace(DRIVER);
            findWebElement("下一步").click();
            TimeUnit.SECONDS.sleep(5);
            DRIVER.findElementByClassName("XCUIElementTypeSecureTextField").sendKeys(pass);
            tapWhitePlace(DRIVER);
            findWebElement("确定").click();
            TimeUnit.SECONDS.sleep(10);
        }catch (Exception e){}
    }

    public WebElement findWebElement(String element) throws Exception {
        int tries = 0;
        Boolean Element_is_exist = false;
        WebElement el = null;
        while (!Element_is_exist && tries < 3) {
//            System.out.println("find  ("+  element  +") WElementTimes:" + tries);
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

    // swip the screen until element is display
    public static void scrollToElementUntilVisible(IOSDriver<?> driver, WebElement we){
        for (int i=0;i<5;i++) {
            try {
                we.isDisplayed();
            }catch (Exception e){
                IOSTouchAction action = new IOSTouchAction(driver);
                int width = driver.manage().window().getSize().width;
                int height = driver.manage().window().getSize().height;
                Duration duration = Duration.ofMillis(200);
                action.press(
                        PointOption.point(width/2, height*1/3))
                        .waitAction(WaitOptions.waitOptions(duration))
                        .moveTo(PointOption.point(width/2, height/5))
                        .release().perform();
            }
        }

    }

    public static void swipScreen(IOSDriver<?> driver){
        IOSTouchAction action = new IOSTouchAction(driver);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        Duration duration = Duration.ofMillis(200);
        action.press(
                PointOption.point(width/2, height*4/5))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(width/2, height/5))
                .release().perform();
    }
    public static void swipScreenLitter(IOSDriver<?> driver){
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

    public static void swipRefreshScreen(IOSDriver<?> driver){
        IOSTouchAction action = new IOSTouchAction(driver);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
//        System.out.print("   " + width + "   " + height);
        Duration duration = Duration.ofMillis(200);
        action.press(
                PointOption.point(width/2, height/5))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(width/2, height*4/5))
                .release().perform();
    }
    public static void refreshWalletScreen(IOSDriver<?> driver){
        IOSTouchAction action = new IOSTouchAction(driver);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
//        System.out.print("   " + width + "   " + height);
        Duration duration = Duration.ofMillis(200);
        action.press(
                PointOption.point(width/2, height/2))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(width/2, height*3/4))
                .release().perform();
    }
    public void swipUntilElementEnable(String id,IOSDriver<?> driver) throws Exception{
        TimeUnit.SECONDS.sleep(1);
        //while (findWebElement(id).isEnabled() == false) {
        while (!findWebElement(id).isEnabled()) {
            IOSTouchAction action = new IOSTouchAction(DRIVER);
            int width = DRIVER.manage().window().getSize().width;
            int height = DRIVER.manage().window().getSize().height;
//            System.out.print("  " + width + "   " + height);
            Duration duration = Duration.ofMillis(200);
            action.press(
                    PointOption.point(width/2, height*4/5))
                    .waitAction(WaitOptions.waitOptions(duration))
                    .moveTo(PointOption.point(width/2, height/5))
                    .release().perform();
        }
    }


    public static void swipeLeftScreen(IOSDriver<?> driver){
        IOSTouchAction action = new IOSTouchAction(driver);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
//        System.out.print("   " + width + "   " + height);
        Duration duration = Duration.ofMillis(200);
        action.press(
                PointOption.point(width*4/5, height*4/5))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(width*1/5, height*4/5))
                .release().perform();
    }

    public static  void TapLocationIosNsPredicate(IOSDriver<?> driver,String predicate){
        WebElement wl = driver.findElementByIosNsPredicate(predicate);
        TapLocationOffset(driver,wl,4,4);
    }
    public static  void TapLocationName(IOSDriver<?> driver,String name){
        WebElement wl = driver.findElementByName(name);
        TapLocationOffset(driver,wl,4,4);
    }
    public static  void TapLocationWL(IOSDriver<?> driver,WebElement wl){
        TapLocationOffset(driver,wl,4,4);
    }
    public static  void TapLocationId(IOSDriver<?> driver,String id){
        WebElement wl = driver.findElementById(id);
        TapLocationOffset(driver,wl,4,4);
    }
    public static  void TapLocationOffset(IOSDriver<?> driver,WebElement wl,int offsetX,int offsetY){
        int topX = wl.getLocation().x + offsetX;
        int topY = wl.getLocation().y + offsetY;
        IOSTouchAction action = new IOSTouchAction(driver);
        action.tap(PointOption.point(topX,topY)).perform();
    }
}
