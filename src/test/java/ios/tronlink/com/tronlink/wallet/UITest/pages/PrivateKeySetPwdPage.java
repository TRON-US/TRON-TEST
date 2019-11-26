package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class PrivateKeySetPwdPage extends AbstractPage {

    public IOSDriver<?> driver;

    public PrivateKeySetPwdPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }




    @FindBy(className = "XCUIElementTypeSecureTextField")
    public WebElement pwd_input;



    @FindBy(name = "com.tronlink.wallet:id/tv_common_title")
    public WebElement pwd_title;


    public WebElement getNext_btn(){
        return driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '下一步'");
    }


    public PrivateKeySetPwdAgainPage enterPrivateKeySetPwdAgainPage(String pwd) throws Exception {
        pwd_input.sendKeys(pwd);
        Helper.tapWhitePlace(driver);
        getNext_btn().click();
        TimeUnit.SECONDS.sleep(1);
        return new PrivateKeySetPwdAgainPage(driver);
    }


}
