package android.com.wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

public class SendTrxSuccessPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public SendTrxSuccessPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "com.tronlink.wallet:id/tv_count")
    public WebElement trxCount;


    @FindBy(id = "com.tronlink.wallet:id/iv_common_left")
    public WebElement back_btn;

    public AssetPage enterSendTrxPage() throws Exception {
        back_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new AssetPage(driver);
    }




}
