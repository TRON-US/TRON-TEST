package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ImportPrivateKeyPage extends AbstractPage {

    public IOSDriver<?> driver;

    public ImportPrivateKeyPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(className = "XCUIElementTypeTextView")
    public WebElement content_text;



    @FindBy(name = "com.tronlink.wallet:id/bt_next")
    public WebElement next_btn;



    @FindBy(className = "XCUIElementTypeButton" )
    public List<WebElement> error_hits;

    public WebElement getNext_btn(){
       return driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '下一步'");
    }

    //XCUIElementTypeButton
    public WebElement getError_hits_btn(){
        return error_hits.get(2);

    }
    public String checkPrivateKey(String key) throws Exception {
        content_text.sendKeys(key);
        Helper.tapWhitePlace(driver);
        TimeUnit.SECONDS.sleep(1);
        String hits = getError_hits_btn().getText();
        return hits;
    }


    public PrivateKeySetNamePage enterPrivateKeySetNamePage(String key) throws Exception{
        content_text.sendKeys(key);
        Helper.tapWhitePlace(driver);
        getNext_btn().click();
        TimeUnit.SECONDS.sleep(2);
        return new PrivateKeySetNamePage(driver);
    }



    public String inputErrorKeyGetHits(String key) throws Exception {
        content_text.sendKeys(key);
        Helper.tapWhitePlace(driver);
        getNext_btn().click();
        TimeUnit.SECONDS.sleep(2);
        return getError_hits_btn().getText();
    }



}
