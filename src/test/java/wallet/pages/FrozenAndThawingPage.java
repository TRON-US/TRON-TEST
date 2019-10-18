package wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import common.utils.Helper;
import io.appium.java_client.android.AndroidDriver;

public class FrozenAndThawingPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public FrozenAndThawingPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "com.tronlink.wallet:id/ll_energy_arrow")
    public WebElement freezeEnergyDetail_btn;


    @FindBy(id = "com.tronlink.wallet:id/tv_myfreeze")
    public WebElement myFreeze_btn;


    @FindBy(id = "com.tronlink.wallet:id/tv_otherfreeze")
    public WebElement otherFreeze_btn;


    @FindBy(id = "com.tronlink.wallet:id/tv_totalfreeze")
    public WebElement totalFreeze_btn;


    @FindBy(id = "com.tronlink.wallet:id/tv_common_right2")
    public WebElement detailsAndRules_btn;

    @FindBy(id = "com.tronlink.wallet:id/ll_bandwidth_arrow")
    public WebElement freezeBandwidthDetail_btn;


    @FindBy(id = "com.tronlink.wallet:id/tv_myfreeze_bandwidth")
    public WebElement myFreezeBandwidth_btn;

    @FindBy(id = "com.tronlink.wallet:id/tv_otherfreeze_bandwidth")
    public WebElement otherFreezeBandwidth_btn;

    @FindBy(id = "com.tronlink.wallet:id/tv_totalfreeze_bandwidth")
    public WebElement totalFreezeBandwidth_btn;


    @FindBy(id = "com.tronlink.wallet:id/tv_voting_power")
    public WebElement votingPower_btn;


    @FindBy(id = "com.tronlink.wallet:id/bandwidth_question")
    public WebElement BandwidthQuestion_btn;


    @FindBy(id = "com.tronlink.wallet:id/content")
    public WebElement questionContent_btn;



    @FindBy(id = "com.tronlink.wallet:id/select_bandwidth")
    public WebElement bandwidth_btn;


    @FindBy(id = "com.tronlink.wallet:id/current_use")
    public WebElement currentCanUse_btn;


    @FindBy(id = "com.tronlink.wallet:id/et_freeze_count")
    public WebElement freezeCount_input;


    @FindBy(id = "com.tronlink.wallet:id/freeze")
    public WebElement freeze_btn;


    @FindBy(id = "com.tronlink.wallet:id/bt_go")
    public WebElement freezeNow_btn;


    @FindBy(id = "com.tronlink.wallet:id/et_new_password")
    public WebElement checkPasswotd_input;


    @FindBy(id = "com.tronlink.wallet:id/bt_send")
    public WebElement confirm_btn;


    @FindBy(id = "com.tronlink.wallet:id/iv_common_left")
    public WebElement back_btn;


    @FindBy(id = "com.tronlink.wallet:id/error_trx_count")
    public WebElement error_hits;


    @FindBy(id = "com.tronlink.wallet:id/current_use")
    public WebElement availableTrx_text;



    @FindBy(id = "com.tronlink.wallet:id/et_freeze_address")
    public WebElement freezeAddress_input;



    @FindBy(id = "com.tronlink.wallet:id/iv_cancle")
    public WebElement cleanAddress_btn;



    @FindBy(id = "com.tronlink.wallet:id/error_address")
    public WebElement errorAddress_hits;



    public DetailsAndRulesPage enterDetailsAndRulesPage() {
        try {
            detailsAndRules_btn.click();
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){

        }
        return new DetailsAndRulesPage(driver);
    }

    public void questionClick() {
        try {
            //swip
            //Helper.scrollToElementUntilVisible(driver,BandwidthQuestion_btn);
            //Helper.swipScreen(driver);
            BandwidthQuestion_btn.click();
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public String getCurrentCanUseTrx() {
        String currentCanUseTrx = (currentCanUse_btn.getText().split(" ")[1]);
        currentCanUseTrx = currentCanUseTrx.substring(0,currentCanUseTrx.length()-3);
        return currentCanUseTrx;
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


    public void inputFrozenCount(String count) throws Exception {
        //Helper.swipScreen(driver);
        Helper.scrollToElementUntilVisible(driver,freeze_btn);
        TimeUnit.SECONDS.sleep(1);
        freezeCount_input.sendKeys(count);
        freeze_btn.click();
        TimeUnit.SECONDS.sleep(2);
    }

    public void inputReceivingAddress(String address) throws Exception {
        //Helper.swipScreen(driver);
        Helper.scrollToElementUntilVisible(driver,freeze_btn);
        TimeUnit.SECONDS.sleep(1);
        cleanAddress_btn.click();
        freezeCount_input.sendKeys("1");
        freezeAddress_input.sendKeys(address);
        freeze_btn.click();
        TimeUnit.SECONDS.sleep(2);
    }

    public String getAvailableTrx() {
        String availableTrx = availableTrx_text.getText();
        availableTrx = availableTrx.split(" ")[1];
        availableTrx = availableTrx.substring(0,availableTrx.length()-3);
        return availableTrx;
    }




}
