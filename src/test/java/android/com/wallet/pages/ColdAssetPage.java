package android.com.wallet.pages;

import android.com.utils.Helper;
import io.appium.java_client.android.AndroidDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * 资产页
 */

public class ColdAssetPage extends AbstractPage {


    public AndroidDriver<?> driver;


    public ColdAssetPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "com.tronlink.wallet:id/tv_offline_sign_desc")
    public WebElement scanQrSign_btn;

    @FindBy(id = "com.tronlink.wallet:id/ll_receive")
    public WebElement receive_btn;

    @FindBy(id = "com.tronlink.wallet:id/ll_offline_sign")
    public WebElement offlineSign_btn;

    @FindBy(id = "com.tronlink.wallet:id/tv_nonet_desc")
    public WebElement coldWalletKnowledge_btn;

    @FindBy(id = "com.tronlink.wallet:id/iv_nonet_tip_close")
    public WebElement deleteColdWalletKnowledge_btn;

    @FindBy(id = "com.tronlink.wallet:id/assets")
    public WebElement asset_btn;

    @FindBy(id = "com.tronlink.wallet:id/my")
    public WebElement me_btn;

    @FindBy(id = "com.tronlink.wallet:id/bt_copy")
    public WebElement copy_btn;

    @FindBy(id = "com.tronlink.wallet:id/tv_address")
    public WebElement address_text;

    @FindBy(className = "android.view.View")
    public List<WebElement> knowledgeText_list;

    @FindBy(id = "com.tronlink.wallet:id/iv_wallet_manager")
    public WebElement addWallet_btn;

    @FindBy(id = "com.tronlink.wallet:id/cd_pk")
    public WebElement privateKey_btn;

    @FindBy(id = "com.tronlink.wallet:id/cd_kt2")
    public WebElement keystore_btn;

    @FindBy(id = "com.tronlink.wallet:id/cd_cw")
    public WebElement createWallet_btn;

    @FindBy(id = "com.tronlink.wallet:id/rl_mm")
    public WebElement mnemonic_btn;

    @FindBy(id = "com.tronlink.wallet:id/iv_qr")
    public WebElement privateKeyQrScan_btn;






    public ColdMinePage enterColdMinePage() {
      driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
      me_btn.click();
      driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
      return new ColdMinePage(driver);
    }


}
