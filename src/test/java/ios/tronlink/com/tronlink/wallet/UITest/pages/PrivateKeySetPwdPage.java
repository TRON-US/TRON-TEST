package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class PrivateKeySetPwdPage extends AbstractPage {

    public IOSDriver<?> driver;

    public PrivateKeySetPwdPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }




    @FindBy(name = "com.tronlink.wallet:id/et_password")
    public WebElement pwd_input;



    @FindBy(name = "com.tronlink.wallet:id/creat")
    public WebElement next_btn;



    @FindBy(name = "com.tronlink.wallet:id/tv_common_title")
    public WebElement pwd_title;





    public PrivateKeySetPwdAgainPage enterPrivateKeySetPwdAgainPage(String pwd) throws Exception {
        pwd_input.sendKeys(pwd);
        next_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new PrivateKeySetPwdAgainPage(driver);
    }


}
