package ios.tronlink.com.tronlink.wallet.UITest.pages;


import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;


public class AddwalletPage extends AbstractPage {

    public IOSDriver<?> driver;


    public AddwalletPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }



    @FindBy(xpath = "//*[@text='私钥']")
    public WebElement privateKeyC_btn;


    @FindBy(xpath = "//*[@text='Private key']")
    public WebElement privateKeyE_btn;

    @FindBy(name = "com.tronlink.wallet:id/cd_kt")
    public WebElement keystore_btn;



    public ImportPrivateKeyPage enterImportPrivateKeyPage() throws Exception{
        try {
            privateKeyC_btn.click();
        }catch (Exception e){
            privateKeyE_btn.click();
        }
        TimeUnit.SECONDS.sleep(1);
        return new ImportPrivateKeyPage(driver);
    }

    public ImportKeystorePage enterImportKeystorePage() throws Exception{
        try {
            keystore_btn.click();
        }catch (Exception e){
            keystore_btn.click();
        }
        TimeUnit.SECONDS.sleep(1);
        return new ImportKeystorePage(driver);
    }




}
