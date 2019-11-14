package ios.tronlink.com.tronlink.wallet.UITest.pages;

import ios.tronlink.com.tronlink.wallet.utils.*;
import ios.tronlink.com.tronlink.wallet.UITest.*;


import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class FrozenAndUnfreezePage extends AbstractPage {

    public IOSDriver<?> driver;


    public FrozenAndUnfreezePage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
        try {
            TimeUnit.SECONDS.sleep(2);
            // if page display AD , cloese the AD
            if (ad_pic.isDisplayed()){
                adClose_btn.click();
                TimeUnit.SECONDS.sleep(1);
            }
        }catch (Exception e){}
    }



    //ad
    @FindBy(xpath = "XCUIElementTypeApplication[@name='TronLink']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeImage")
    public WebElement ad_pic;


    @FindBy(name = "home pop close")
    public WebElement adClose_btn;


    @FindBy(name = "资源")
    public WebElement assert_title;

    @FindBy(name = "冻结/解冻")
    public WebElement freeze_btn;


    @FindBy(name = "com.tronlink.wallet:id/ll_energy_arrow")
    public WebElement freezeEnergyDetail_btn;


    @FindBy(name = "com.tronlink.wallet:id/tv_myfreeze")
    public WebElement myFreeze_btn;


    @FindBy(name = "com.tronlink.wallet:id/tv_otherfreeze")
    public WebElement otherFreeze_btn;


    @FindBy(name = "com.tronlink.wallet:id/tv_totalfreeze")
    public WebElement totalFreeze_btn;


    @FindBy(name = "com.tronlink.wallet:id/tv_common_right2")
    public WebElement detailsAndRules_btn;

    @FindBy(name = "com.tronlink.wallet:id/ll_bandwidth_arrow")
    public WebElement freezeBandwidthDetail_btn;


    @FindBy(name = "com.tronlink.wallet:id/tv_myfreeze_bandwidth")
    public WebElement myFreezeBandwidth_btn;

    @FindBy(name = "com.tronlink.wallet:id/tv_otherfreeze_bandwidth")
    public WebElement otherFreezeBandwidth_btn;

    @FindBy(name = "com.tronlink.wallet:id/tv_totalfreeze_bandwidth")
    public WebElement totalFreezeBandwidth_btn;


    @FindBy(name = "com.tronlink.wallet:id/tv_voting_power")
    public WebElement votingPower_btn;


    @FindBy(name = "com.tronlink.wallet:id/bandwidth_question")
    public WebElement BandwidthQuestion_btn;


    @FindBy(name = "com.tronlink.wallet:id/content")
    public WebElement questionContent_btn;



    @FindBy(name = "com.tronlink.wallet:id/select_bandwidth")
    public WebElement bandwidth_btn;


    @FindBy(name = "com.tronlink.wallet:id/current_use")
    public WebElement currentCanUse_btn;


    @FindBy(name = "com.tronlink.wallet:id/et_freeze_count")
    public WebElement freezeCount_input;


    @FindBy(name = "com.tronlink.wallet:id/bt_go")
    public WebElement freezeNow_btn;


    @FindBy(name = "com.tronlink.wallet:id/et_new_password")
    public WebElement checkPasswotd_input;


    @FindBy(name = "com.tronlink.wallet:id/bt_send")
    public WebElement confirm_btn;


    @FindBy(name = "com.tronlink.wallet:id/iv_common_left")
    public WebElement back_btn;


    @FindBy(name = "com.tronlink.wallet:id/error_trx_count")
    public WebElement error_hits;


    @FindBy(name = "com.tronlink.wallet:id/current_use")
    public WebElement availableTrx_text;



    @FindBy(name = "com.tronlink.wallet:id/et_freeze_address")
    public WebElement freezeAddress_input;



    @FindBy(name = "com.tronlink.wallet:id/iv_cancle")
    public WebElement cleanAddress_btn;



    @FindBy(name = "com.tronlink.wallet:id/error_address")
    public WebElement errorAddress_hits;



    public AssetPage forzenSuccessEnterAssetPage(String count) throws Exception {
        Helper.scrollToElementUntilVisible(driver,freeze_btn);
        TimeUnit.SECONDS.sleep(1);
        freezeCount_input.sendKeys(count);
        freeze_btn.click();
        TimeUnit.SECONDS.sleep(2);
        freezeNow_btn.click();
        TimeUnit.SECONDS.sleep(2);
        checkPasswotd_input.sendKeys("Test0001");
        TimeUnit.SECONDS.sleep(1);
        confirm_btn.click();
        TimeUnit.SECONDS.sleep(1);
        back_btn.click();
        return new AssetPage(driver);
    }

    public void frozenTheEnergy() {
        //swipToFrozenBtnDisplay();
        Helper.swipScreen(driver);
        try {
            freeze_btn.click();
            TimeUnit.SECONDS.sleep(1);
            freezeNow_btn.click();
            TimeUnit.SECONDS.sleep(1);
            checkPasswotd_input.sendKeys("Test0001");
            confirm_btn.click();
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public AssetPage enterAssetPage() {
        back_btn.click();
        return new AssetPage(driver);
    }
}
