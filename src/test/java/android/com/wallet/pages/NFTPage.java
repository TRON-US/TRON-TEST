package android.com.wallet.pages;

import com.google.gson.internal.$Gson$Preconditions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

public class NFTPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public NFTPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_name")
    public WebElement tv_name;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_contract_address")
    public WebElement tv_contract_address;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_common_right2")
    public WebElement tv_common_right2;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_contract_address_title")
    public WebElement tv_contract_address_title ;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_transfer")
    public WebElement iv_transfer;

    @FindBy(id = "com.tronlinkpro.wallet:id/rl_inner")
    public List<WebElement> inners ;

    @FindBy(id = "com.tronlinkpro.wallet:id/address_title")
    public WebElement address_title ;

    @FindBy(id = "com.tronlinkpro.wallet:id/time")
    public WebElement time;

    @FindBy(xpath = "//*[@resource-id=\"com.tronlinkpro.wallet:id/ll_receive\"]/android.widget.ImageView[1]")
    public WebElement receive;


    public void enterHistory() throws Exception{
        tv_common_right2.click();
        TimeUnit.SECONDS.sleep(1);
    }

    public ReceiptPage enterReceive() throws Exception{
        TimeUnit.SECONDS.sleep(2);
        if (receive.isEnabled()){
            receive.click();
        }else {
            TimeUnit.SECONDS.sleep(3);
            receive.click();
        }
        TimeUnit.SECONDS.sleep(1);
        return new ReceiptPage(driver);
    }

    public SendTrxPage enterSendPage() throws Exception{
        TimeUnit.SECONDS.sleep(2);
        if (!iv_transfer.isEnabled()){
            TimeUnit.SECONDS.sleep(3);
        }
        iv_transfer.click();
        TimeUnit.SECONDS.sleep(1);
        return new SendTrxPage(driver);
    }
}
