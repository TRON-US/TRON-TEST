package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class ImportPrivateKeyPage extends AbstractPage {

    public IOSDriver<?> driver;

    public ImportPrivateKeyPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(name = "com.tronlink.wallet:id/et_content")
    public WebElement content_text;



    @FindBy(name = "com.tronlink.wallet:id/bt_next")
    public WebElement next_btn;



    @FindBy(name = "com.tronlink.wallet:id/tv_error")
    public WebElement error_hits;


    public String checkPrivateKey(String key) throws Exception {
        content_text.sendKeys(key);
        next_btn.click();
        TimeUnit.SECONDS.sleep(1);
        String hits = error_hits.getText();
        return hits;
    }


    public PrivateKeySetNamePage enterPrivateKeySetNamePage(String key) throws Exception{
        content_text.sendKeys(key);
        next_btn.click();
        TimeUnit.SECONDS.sleep(2);
        return new PrivateKeySetNamePage(driver);
    }



    public String inputErrorKeyGetHits(String key) throws Exception {
        content_text.sendKeys(key);
        next_btn.click();
        TimeUnit.SECONDS.sleep(2);
        return error_hits.getText();
    }



}
