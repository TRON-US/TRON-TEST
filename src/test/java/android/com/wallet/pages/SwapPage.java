package android.com.wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

public class SwapPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public SwapPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }




    @FindBy(id = "com.tronlinkpro.wallet:id/ll_common_left")
    public WebElement back_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_token_name_from")
    public WebElement tv_token_name_from;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_token_name_to")
    public WebElement tv_token_name_to;
    
    @FindBy(id = "com.tronlinkpro.wallet:id/tv_token_from")
    public WebElement tv_token_from;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_token_to")
    public WebElement tv_token_to;

    @FindBy(id = "com.tronlinkpro.wallet:id/et_amount_from")
    public WebElement et_amount_from;

    @FindBy(id = "com.tronlinkpro.wallet:id/et_amount_to")
    public WebElement et_amount_to;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_rate_number")
    public WebElement tv_rate_number;

    @FindBy(id = "com.tronlinkpro.wallet:id/btn_swap_instant")
    public WebElement btn_swap_instant;

    @FindBy(id = "com.tronlinkpro.wallet:id/text_token_to")
    public WebElement text_token_to;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_rate_right")
    public WebElement tv_rate_right ;
    
    @FindBy(id = "com.tronlinkpro.wallet:id/token_consume_name")
    public WebElement token_consume_name;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_date")
    public WebElement tv_date;

    @FindBy(id = "com.tronlinkpro.wallet:id/token_consume_count")
    public WebElement token_consume_count;

    public void  inputFromAmount(String amount){
        et_amount_from.sendKeys(amount);
    }

    public void swapConfirmView() throws Exception{
        btn_swap_instant.click();
        TimeUnit.SECONDS.sleep(3);
    }

    public void swapSentAction() throws Exception{
        send.click();
        et_new_password.sendKeys("Test0001");
        bt_send.click();
    }

}
