package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
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

    public String  getError_hits(){
        String temper = new String();
        for (WebElement e: error_hits
             ) {
            temper += e.getText();
        }
        return  temper;
//        return error_hits.get(0).getText() + error_hits.get(1).getText() + error_hits.get(2).getText();
    }

    public WebElement getNext_btn(){
        return driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '下一步'");
    }

    public WebElement getComplish_btn(){
        return driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '确定'");
    }
    public PrivateKeySetPwdPage enterPrivateKeySetPwdPage(String name) throws Exception {
        TimeUnit.SECONDS.sleep(2);
        name_input.sendKeys(name);
        closeKeyBoard();
        TimeUnit.SECONDS.sleep(2);
        getNext_btn().click();
        TimeUnit.SECONDS.sleep(2);
        return new PrivateKeySetPwdPage(driver);
    }

    public void setName(String name) throws Exception {
        TimeUnit.SECONDS.sleep(2);
        name_input.sendKeys(name);
        getNext_btn().click();
    }
    public void setNameover(String name) throws Exception {
        TimeUnit.SECONDS.sleep(2);
        name_input.sendKeys(name);
        TimeUnit.SECONDS.sleep(2);
        getComplish_btn().click();
    }
    public void goback(){
        back_btn.click();
    }


}
