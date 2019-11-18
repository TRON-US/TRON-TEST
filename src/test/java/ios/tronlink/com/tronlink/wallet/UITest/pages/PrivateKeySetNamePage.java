package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class PrivateKeySetNamePage extends AbstractPage {

    public IOSDriver<?> driver;

    public PrivateKeySetNamePage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(name = "com.tronlink.wallet:id/et_name")
    public WebElement name_input;



    @FindBy(name = "com.tronlink.wallet:id/creat")
    public WebElement next_btn;



    @FindBy(name = "com.tronlink.wallet:id/tv_error")
    public WebElement error_hits;






    public PrivateKeySetPwdPage enterPrivateKeySetPwdPage(String name) throws Exception {
        name_input.sendKeys(name);
        next_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new PrivateKeySetPwdPage(driver);
    }

    public void setName(String name){
        name_input.sendKeys(name);
        next_btn.click();
    }



}
