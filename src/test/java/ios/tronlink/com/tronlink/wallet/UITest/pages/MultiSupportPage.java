package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MultiSupportPage extends AbstractPage {

    public  enum multiWallet {
        Auto_test, multiWallet
    };

    public multiWallet walletName;


    public IOSDriver<?> driver;

    public MultiSupportPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "black path")
    public WebElement backBtn;

    public AssetPage backAction() throws Exception{
        TimeUnit.SECONDS.sleep(1);
        backBtn.click();
        TimeUnit.SECONDS.sleep(1);
        return new AssetPage(driver);
    }

    public void switchWallet() throws  Exception{
        if (this.walletName == multiWallet.multiWallet){
            swipWalletTochangeBefore();

        }else {
            swipWalletTochangeNext();

        }
    }



    public void swipWalletTochangeNext() throws Exception {
        WebElement wl = driver.findElementByXPath("//XCUIElementTypeApplication[@name=\"TronLink\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]");
        List<WebElement> lwl = wl.findElements(By.className("XCUIElementTypeStaticText"));
//        Helper.contentTexts(lwl,"2222222");
        int topY = wl.getLocation().y + 10;
        int botY = wl.getLocation().y + wl.getSize().height - 50;
        IOSTouchAction action = new IOSTouchAction(driver);
        Duration duration = Duration.ofMillis(200);
        System.out.println("start");
        action.press(
                PointOption.point(120, botY))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(120, topY))
                .release().perform();
        System.out.println("end");
        TimeUnit.SECONDS.sleep(2);
        action.tap(PointOption.point(120,botY)).perform();
        TimeUnit.SECONDS.sleep(2);

    }

    public void swipWalletTochangeBefore() throws Exception {
        WebElement wl = driver.findElementByXPath("//XCUIElementTypeApplication[@name=\"TronLink\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]");
        int topY = wl.getLocation().y + 10;
        int botY = wl.getLocation().y + wl.getSize().height - 50;
        IOSTouchAction action = new IOSTouchAction(driver);
        Duration duration = Duration.ofMillis(200);
        System.out.println("start");
        action.press(
                PointOption.point(120, topY))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(120, botY))
                .release().perform();
        System.out.println("end");
        TimeUnit.SECONDS.sleep(2);
        action.tap(PointOption.point(120,botY)).perform();
        TimeUnit.SECONDS.sleep(2);
    }
}
