package wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

public class TransferPage extends AbstractPage {


    public AndroidDriver<?> driver;


    public TransferPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }



    @FindBy(id = "com.tronlink.wallet:id/tv_trx_amount")
    public WebElement trx_text;



    @FindBy(id = "com.tronlink.wallet:id/tv_no_bandwidth")
    public WebElement hits_text;



    @FindBy(id = "com.tronlink.wallet:id/tv_chain_name")
    public WebElement chain_text;



    @FindBy(id = "com.tronlink.wallet:id/et_count")
    public WebElement count_text;



    @FindBy(id = "com.tronlink.wallet:id/bt_go")
    public WebElement transferIn_btn;



    @FindBy(id = "com.tronlink.wallet:id/tv_bw_amount")
    public WebElement fee_text;



    @FindBy(id = "com.tronlink.wallet:id/tv_balance")
    public WebElement availableBalance_text;



    @FindBy(id = "com.tronlink.wallet:id/et_new_password")
    public WebElement password_input;



    @FindBy(id = "com.tronlink.wallet:id/bt_send")
    public WebElement finish_btn;



    @FindBy(id = "com.tronlink.wallet:id/tv_tab_title")
    public WebElement transferIn_tab;



    @FindBy(id = "com.tronlink.wallet:id/time")
    public WebElement time_text;






    public String getTransferInfo(String info) throws Exception {
        count_text.sendKeys("10");
        transferIn_btn.click();
        TimeUnit.SECONDS.sleep(1);
        String value = "";
        switch (info){
            case "trx":
                value = trx_text.getText();
                break;
            case "hits":
                value = hits_text.getText();
                break;
            case "fee":
                value = fee_text.getText().split(" ")[0];
                break;
        }
        return value;
    }


    public TrxPage enterTrxPageWithTransferSuccess() throws Exception {
        count_text.sendKeys("10");
        transferIn_btn.click();
        TimeUnit.SECONDS.sleep(2);
        password_input.sendKeys("Test0001");
        finish_btn.click();
        TimeUnit.SECONDS.sleep(3);
        return new TrxPage(driver);
    }


    public TrxPage enterTrxPageWithTransferSuccess(String count) throws Exception {
        count_text.sendKeys(count);
        transferIn_btn.click();
        TimeUnit.SECONDS.sleep(2);
        password_input.sendKeys("Test0001");
        finish_btn.click();
        TimeUnit.SECONDS.sleep(3);
        return new TrxPage(driver);
    }



    public boolean checkStep() {

        transferIn_tab.click();
        try {
            time_text.getText();
        }catch (Exception e){

        }
        return false;
    }









}
