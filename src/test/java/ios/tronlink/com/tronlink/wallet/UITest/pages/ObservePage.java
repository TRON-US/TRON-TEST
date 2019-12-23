package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ObservePage extends  AbstractPage {
    public IOSDriver<?> driver;

    public ObservePage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;

    }

    @FindBy(className = "XCUIElementTypeTextView")
    public WebElement input_content;


    @FindBy(className = "XCUIElementTypeButton" )
    public List<WebElement> error_hits;

    public String  getError_hits(){
        return error_hits.get(2).getText();
    }

    public WebElement getNext_btn(){
        return driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '下一步'");
    }

    public void inputAddreseString(String address) throws Exception {
        TimeUnit.SECONDS.sleep(2);
        input_content.sendKeys(address);
        Helper.tapWhitePlace(driver);
        TimeUnit.SECONDS.sleep(3);
        getNext_btn().click();
        TimeUnit.SECONDS.sleep(2);
    }

    public PrivateKeySetNamePage enterPrivateKeySetNamePage() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        return  new PrivateKeySetNamePage(driver);
    }
}
