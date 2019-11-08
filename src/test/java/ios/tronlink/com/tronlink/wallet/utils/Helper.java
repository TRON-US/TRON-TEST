package ios.tronlink.com.tronlink.wallet.utils;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Helper {



    public IOSDriver DRIVER = null;



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



}
