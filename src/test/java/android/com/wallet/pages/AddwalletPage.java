package android.com.wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.concurrent.TimeUnit;
import io.appium.java_client.android.AndroidDriver;


public class AddwalletPage extends AbstractPage {


    public AndroidDriver<?> driver;


    public AddwalletPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(xpath = "//*[@text='私钥']")
    public WebElement privateKeyC_btn;


    @FindBy(xpath = "//*[@text='Private key']")
    public WebElement privateKeyE_btn;

    @FindBy(id = "wallet.tronlink.global:id/cd_pk")
    public WebElement privateKey_btn;

    @FindBy(id = "wallet.tronlink.global:id/cd_kt")
    public WebElement keystore_btn;


    @FindBy(id = "wallet.tronlink.global:id/ll_common_left")
    public WebElement back_btn;

    @FindBy(id = "wallet.tronlink.global:id/create_option_desc")
    public WebElement normal_account_type;

    //wallet.tronlink.global:id/create_option_desc_shield
    @FindBy(id = "wallet.tronlink.global:id/create_option_desc_shield")
    public WebElement shield_account_type;



    public ImportPrivateKeyPage enterImportPrivateKeyPage() throws Exception{
        try {
            normal_account_type.click();
            privateKey_btn.click();
        }catch (Exception e){
            privateKey_btn.click();
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
