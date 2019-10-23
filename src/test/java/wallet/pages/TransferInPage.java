package wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

public class TransferInPage extends AbstractPage {


    public AndroidDriver<?> driver;


    public TransferInPage(AndroidDriver<?> driver) {
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



    public String getTransferInInfo(String info) throws Exception {
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

}
