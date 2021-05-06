package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class PrivateKeySetPwdAgainPage extends AbstractPage {
    public IOSDriver<?> driver;

    public PrivateKeySetPwdAgainPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(className = "XCUIElementTypeSecureTextField")
    public WebElement pwd_input;

    @FindBy(className = "XCUIElementTypeButton" )
    public List<WebElement> error_hits;


    public String  getError_hits(){
        return error_hits.get(0).getText()+error_hits.get(1).getText()+error_hits.get(2).getText();
    }

    public WebElement getComplish_btn(){
        return driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '确定'");
    }

    public AssetPage enterAssetPage(String pwdAgain) throws Exception {
        pwd_input.sendKeys(pwdAgain);
        Helper.tapWhitePlace(driver);
        getComplish_btn().click();
        TimeUnit.SECONDS.sleep(1);
        return new AssetPage(driver);
    }



}
