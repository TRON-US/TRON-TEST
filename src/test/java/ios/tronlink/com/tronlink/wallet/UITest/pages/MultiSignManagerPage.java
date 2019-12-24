package ios.tronlink.com.tronlink.wallet.UITest.pages;


import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class MultiSignManagerPage extends AbstractPage {


    public IOSDriver<?> driver;


    public MultiSignManagerPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }



}
