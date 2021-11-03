package android.com.wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

public class VoteConfirmPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public VoteConfirmPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "com.tronlinkpro.wallet:id/btn_asset_confirm")
    public WebElement voteNow_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/et_new_password")
    public WebElement password_input;


    @FindBy(id = "com.tronlinkpro.wallet:id/bt_send")
    public WebElement confirm_btn;


    public void voteOperate(){
        try {
            TimeUnit.SECONDS.sleep(1);
            try {
                voteNow_btn.click();
            } catch (Exception e) {
            }
            password_input.sendKeys("Test0001");
            confirm_btn.click();
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){
            System.out.println(e);
        }

    }






}
