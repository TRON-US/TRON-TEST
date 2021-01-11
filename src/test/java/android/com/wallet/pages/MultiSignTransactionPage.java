package android.com.wallet.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class MultiSignTransactionPage extends AbstractPage {



    public AndroidDriver<?> driver;



    public MultiSignTransactionPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "com.tronlinkpro.wallet:id/bt_sign")
    public WebElement  sign_btn;



    @FindBy(id = "com.tronlinkpro.wallet:id/et_new_password")
    public WebElement password_input;


    @FindBy(id = "com.tronlinkpro.wallet:id/bt_send")
    public WebElement send_btn;



    @FindBy(id = "com.tronlinkpro.wallet:id/ll_common_left")
    public WebElement back_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_note")
    public WebElement tv_note;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_note_more")
    public WebElement tv_note_more;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_cancle")
    public WebElement tv_cancle;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_sign_success")
    public WebElement signSuccess_tab;



    @FindBy(id = "com.tronlinkpro.wallet:id/tv_trans_type")
    public WebElement trans_text;



    @FindBy(id = "com.tronlinkpro.wallet:id/tv_trans_content")
    public WebElement transContent_text;




    @FindBy(id = "com.tronlinkpro.wallet:id/transaction_from")
    public WebElement transactionFrom_text;




    @FindBy(id = "com.tronlinkpro.wallet:id/tv_sign_success")
    public WebElement transactionSuc_text;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_sign_already")
    public WebElement transactionAlreadySign_text;



    @FindBy(id = "com.tronlinkpro.wallet:id/transaction_to")
    public WebElement transactionTo_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/rl_bottom_next")
    public WebElement rl_bottom_next;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_trans_content")
    public WebElement transConten_text;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_invalid_time")
    public WebElement tv_invalid_time;

    @FindBy(id = "com.tronlinkpro.wallet:id/transaction_from")
    public WebElement transFrom_text;



    @FindBy(id = "com.tronlinkpro.wallet:id/transaction_to")
    public WebElement transTo_text;



    @FindBy(id = "com.tronlinkpro.wallet:id/cv_countdownView")
    public WebElement invaTime_text;


    public void broadcaseNow() throws Exception {
        rl_bottom_next.click();
        password_input.sendKeys("Test0001");
        send_btn.click();
        TimeUnit.SECONDS.sleep(5);
    }
    public void sign() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        sign_btn.click();
        password_input.sendKeys("Test0001");
        send_btn.click();
        TimeUnit.SECONDS.sleep(2);
    }



    public MyPursePage enterMyPursePage() {
        back_btn.click();
        return new MyPursePage(driver);
    }





}
