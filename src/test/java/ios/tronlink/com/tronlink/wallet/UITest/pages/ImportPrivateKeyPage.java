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

    @FindBy(className = "XCUIElementTypeTextField")
    public WebElement content_textfield;



    @FindBy(name = "com.tronlink.wallet:id/bt_next")
    public WebElement next_btn;



    @FindBy(className = "XCUIElementTypeButton" )
    public List<WebElement> error_hits;

    public WebElement getNext_btn(){
       return driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '下一步'");
    }

    //XCUIElementTypeButton
    public WebElement getError_hits_btn()  throws Exception{
        TimeUnit.SECONDS.sleep(2);
        return error_hits.get(2);

    }
    public void interPrivateKey(String key) throws Exception {
        TimeUnit.SECONDS.sleep(1);
        content_textfield.sendKeys(key);
        closeKeyBoard();
    }



}
