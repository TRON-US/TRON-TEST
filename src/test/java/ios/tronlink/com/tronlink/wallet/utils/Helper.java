package ios.tronlink.com.tronlink.wallet.utils;


import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Helper {



    public IOSDriver DRIVER = null;

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
        PointOption whiteplace = PointOption.point(10,130);
        action.tap(whiteplace).perform().release();
    }
    public static String getPrettyNumber(String number) {
        return BigDecimal.valueOf(Double.parseDouble(number))
                .stripTrailingZeros().toPlainString();
    }

    public static boolean isElementExist(IOSDriver driver,String name) {
       try {
           driver.findElementByName(name);
           return  true;
       }catch (org.openqa.selenium.NoSuchElementException ex){
           return  false;
       }
    }

    public void getSign(String testPrivateKey, IOSDriver driver) throws Exception{
        this.DRIVER = driver;
        getSignOperate(testPrivateKey);
    }



    public void getSignOperate(String testPrivateKey) throws Exception{
        System.out.println("222222222222222");
        try {
            findWebElement("导入钱包").click();
            getSignStep(testPrivateKey);
        }catch (Exception e){
        }



    }


    public void getSignStep(String testPrivateKey){
        System.out.println("111111111111");
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
        try {
            findWebElement("私钥导入").click();
            DRIVER.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"TronLink\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeTextView")).sendKeys(testPrivateKey);
            findWebElement("Done").click();
            findWebElement("下一步").click();
            TimeUnit.SECONDS.sleep(7);
            DRIVER.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"TronLink\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeTextField")).sendKeys("Auto_test");
            findWebElement("Done").click();
            findWebElement("下一步").click();
            DRIVER.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"TronLink\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeSecureTextField")).sendKeys("Test0001");
            findWebElement("Done").click();
            findWebElement("下一步").click();
            DRIVER.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"TronLink\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeSecureTextField")).sendKeys("Test0001");
            findWebElement("Done").click();
            findWebElement("完成").click();
            TimeUnit.SECONDS.sleep(5);
        }catch (Exception e){}

    }



    public WebElement findWebElement(String element) throws Exception {
        int tries = 0;
        Boolean Element_is_exist = false;
        WebElement el = null;
        while (!Element_is_exist && tries < 5) {
            tries++;
            try {
                el = DRIVER.findElementByName(element);
                Element_is_exist = true;
            }catch (NoSuchElementException e){
                //Element_is_exist = false;
                TimeUnit.SECONDS.sleep(2);
            }
        }
        el = DRIVER.findElementById(element);
        return el;
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
                System.out.println("swip the screen...");
            }
        }

    }

    public static void swipScreen(IOSDriver<?> driver){
        IOSTouchAction action = new IOSTouchAction(driver);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        System.out.print("   " + width + "   " + height);
        Duration duration = Duration.ofMillis(200);
        action.press(
                PointOption.point(width/2, height*4/5))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(width/2, height/5))
                .release().perform();
    }

    public static void swipRefreshScreen(IOSDriver<?> driver){
        IOSTouchAction action = new IOSTouchAction(driver);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        System.out.print("   " + width + "   " + height);
        Duration duration = Duration.ofMillis(200);
        action.press(
                PointOption.point(width/2, height/5))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(width/2, height*4/5))
                .release().perform();
    }

    public void swipUntilElementEnable(String id,IOSDriver<?> driver) throws Exception{
        TimeUnit.SECONDS.sleep(1);
        //while (findWebElement(id).isEnabled() == false) {
        while (!findWebElement(id).isEnabled()) {
            IOSTouchAction action = new IOSTouchAction(DRIVER);
            int width = DRIVER.manage().window().getSize().width;
            int height = DRIVER.manage().window().getSize().height;
            System.out.print("  " + width + "   " + height);
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
        System.out.print("   " + width + "   " + height);
        Duration duration = Duration.ofMillis(200);
        action.press(
                PointOption.point(width*4/5, height*4/5))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(width*1/5, height*4/5))
                .release().perform();
    }
}
