package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class VoteConfirmPage extends AbstractPage {

    public IOSDriver<?> driver;

    public VoteConfirmPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(name = "com.tronlink.wallet:id/bt_go")
    public WebElement voteNow_btn;


    @FindBy(name = "com.tronlink.wallet:id/et_new_password")
    public WebElement password_input;


    @FindBy(name = "com.tronlink.wallet:id/bt_send")
    public WebElement confirm_btn;


    public void voteOperate(){
        try {
            TimeUnit.SECONDS.sleep(1);
            voteNow_btn.click();
            password_input.sendKeys("Test0001");
            confirm_btn.click();
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){
            System.out.println(e);
        }

    }






}
