package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class PrivateKeySetNamePage extends AbstractPage {

    public IOSDriver<?> driver;

    public PrivateKeySetNamePage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(className = "XCUIElementTypeTextField")
    public WebElement name_input;

    @FindBy(name = "钱包名称过长")
    public WebElement toolongname;

    @FindBy(id = "black path")
    public WebElement back_btn;



    @FindBy(className = "XCUIElementTypeButton" )
    public List<WebElement> error_hits;

    public WebElement getNext_btn(){
        return driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '下一步'");
    }

    public String  getError_hits(){
        return error_hits.get(2).getText();
    }

    public PrivateKeySetPwdPage enterPrivateKeySetPwdPage(String name) throws Exception {
        name_input.sendKeys(name);
        Helper.tapWhitePlace(driver);
        getNext_btn().click();
        TimeUnit.SECONDS.sleep(1);
        return new PrivateKeySetPwdPage(driver);
    }

    public void setName(String name){
        name_input.sendKeys(name);
        getNext_btn().click();
    }
    public void goback(){
        back_btn.click();
    }


}
