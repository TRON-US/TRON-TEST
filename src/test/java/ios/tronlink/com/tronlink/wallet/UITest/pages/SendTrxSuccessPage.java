package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class SendTrxSuccessPage extends AbstractPage {

    public IOSDriver<?> driver;

    public SendTrxSuccessPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(name = "com.tronlink.wallet:id/tv_count")
    public WebElement trxCount;


    @FindBy(name = "com.tronlink.wallet:id/iv_common_left")
    public WebElement back_btn;

    public AssetPage enterSendTrxPage() throws Exception {
        back_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new AssetPage(driver);
    }




}
