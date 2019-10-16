package common.utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Helper {

    public static void swipScreen(AndroidDriver<?> driver){
        AndroidTouchAction action = new AndroidTouchAction(driver);
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


    public static void scrollToElementUntilVisible(AndroidDriver<?> driver, WebElement we){
        try {
            while(we.isDisplayed() == false) {
                AndroidTouchAction action = new AndroidTouchAction(driver);
                int width = driver.manage().window().getSize().width;
                int height = driver.manage().window().getSize().height;
                Duration duration = Duration.ofMillis(200);
                action.press(
                        PointOption.point(width/2, height*4/5))
                        .waitAction(WaitOptions.waitOptions(duration))
                        .moveTo(PointOption.point(width/2, height/5))
                        .release().perform();
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }


    public static AndroidDriver DRIVER = null;


    @FindBy(id = "com.tronlink.wallet:id/assets")
    public static WebElement assetsMain_btn;


    public static void getSign(String testPrivateKey,AndroidDriver driver){
        DRIVER = driver;
        try {
            assetsMain_btn.isDisplayed();
        }catch (Exception e){
            Helper.getSignOperate(testPrivateKey);
        }
    }


    public static void getSignOperate(String testPrivateKey){
        try {
            findWebElement("com.tronlink.wallet:id/tv_import").click();
            //TimeUnit.SECONDS.sleep(3);
            while (findWebElement("com.tronlink.wallet:id/bt_accept").isEnabled() == false) {
                AndroidTouchAction action = new AndroidTouchAction(DRIVER);
                int width = DRIVER.manage().window().getSize().width;
                int height = DRIVER.manage().window().getSize().height;
                //System.out.print("   " + width + "   " + height);
                Duration duration = Duration.ofMillis(200);
                action.press(
                        PointOption.point(width/2, height*4/5))
                        .waitAction(WaitOptions.waitOptions(duration))
                        .moveTo(PointOption.point(width/2, height/5))
                        .release().perform();
            }
            findWebElement("com.tronlink.wallet:id/bt_accept").click();
            findWebElement("com.tronlink.wallet:id/cd_pk").click();
            findWebElement("com.tronlink.wallet:id/et_content").sendKeys(testPrivateKey);
            findWebElement("com.tronlink.wallet:id/bt_next").click();
            findWebElement("com.tronlink.wallet:id/et_name").sendKeys("Auto-test");
            findWebElement("com.tronlink.wallet:id/creat").click();
            findWebElement("com.tronlink.wallet:id/et_password").sendKeys("Test0001");
            findWebElement("com.tronlink.wallet:id/creat").click();
            findWebElement("com.tronlink.wallet:id/creat").click();
            findWebElement("com.tronlink.wallet:id/et_password").sendKeys("Test0001");
            findWebElement("com.tronlink.wallet:id/creat").click();
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){
            System.out.println(e);
        }

    }



    public static WebElement findWebElement(String element) throws Exception {
        int tries = 0;
        Boolean Element_is_exist = false;
        WebElement el = null;
        while (!Element_is_exist && tries < 5) {
            tries++;
            try {
                el = DRIVER.findElementById(element);
            }catch (NoSuchElementException e){
                Element_is_exist = true;
                TimeUnit.SECONDS.sleep(2);
            }
        }
        el = DRIVER.findElementById(element);
        return el;
    }



}
