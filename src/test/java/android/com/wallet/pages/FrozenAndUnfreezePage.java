package android.com.wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

import android.com.utils.Helper;

import io.appium.java_client.android.AndroidDriver;

public class FrozenAndUnfreezePage extends AbstractPage {

    public AndroidDriver<?> driver;

    public FrozenAndUnfreezePage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "wallet.tronlink.global:id/ll_energy_arrow")
    public WebElement freezeEnergyDetail_btn;


    @FindBy(id = "wallet.tronlink.global:id/tv_myfreeze")
    public WebElement myFreeze_btn;

    @FindBy(id = "wallet.tronlink.global:id/tv_otherfreeze")
    public WebElement otherFreeze_btn;


    @FindBy(id = "wallet.tronlink.global:id/tv_totalfreeze")
    public WebElement totalFreeze_btn;


    @FindBy(id = "wallet.tronlink.global:id/tv_common_right2")
    public WebElement detailsAndRules_btn;

    @FindBy(id = "wallet.tronlink.global:id/ll_bandwidth_arrow")
    public WebElement freezeBandwidthDetail_btn;


    @FindBy(id = "wallet.tronlink.global:id/tv_myfreeze_bandwidth")
    public WebElement myFreezeBandwidth_btn;

    @FindBy(id = "wallet.tronlink.global:id/tv_otherfreeze_bandwidth")
    public WebElement otherFreezeBandwidth_btn;

    @FindBy(id = "wallet.tronlink.global:id/tv_totalfreeze_bandwidth")
    public WebElement totalFreezeBandwidth_btn;


    @FindBy(id = "wallet.tronlink.global:id/tv_voting_power")
    public WebElement votingPower_btn;


    @FindBy(id = "wallet.tronlink.global:id/bandwidth_question")
    public WebElement BandwidthQuestion_btn;


    @FindBy(id = "wallet.tronlink.global:id/content")
    public WebElement questionContent_btn;



    @FindBy(id = "wallet.tronlink.global:id/select_bandwidth")
    public WebElement bandwidth_btn;

    @FindBy(id = "wallet.tronlink.global:id/select_energy")
    public WebElement energy_btn;


    @FindBy(id = "wallet.tronlink.global:id/current_use")
    public WebElement currentCanUse_btn;


    @FindBy(id = "wallet.tronlink.global:id/et_freeze_count")
    public WebElement freezeCount_input;


    @FindBy(id = "wallet.tronlink.global:id/freeze")
    public WebElement freeze_btn;


    @FindBy(id = "wallet.tronlink.global:id/freeze")
    public WebElement freezeNow_btn;


    @FindBy(id = "wallet.tronlink.global:id/et_new_password")
    public WebElement checkPasswotd_input;


    @FindBy(id = "wallet.tronlink.global:id/bt_send")
    public WebElement confirm_btn;

    @FindBy(id = "wallet.tronlink.global:id/ll_common_left")
    public WebElement back_btn;


    @FindBy(id = "wallet.tronlink.global:id/error_trx_count")
    public WebElement error_hits;


    @FindBy(id = "wallet.tronlink.global:id/current_use")
    public WebElement availableTrx_text;



    @FindBy(id = "wallet.tronlink.global:id/et_freeze_address")
    public WebElement freezeAddress_input;



    @FindBy(id = "wallet.tronlink.global:id/iv_cancle")
    public WebElement cleanAddress_btn;



    @FindBy(id = "wallet.tronlink.global:id/error_address")
    public WebElement errorAddress_hits;

    @FindBy(id = "wallet.tronlink.global:id/select_power_type")
    public WebElement currentType_btn;

    @FindBy(id = "wallet.tronlink.global:id/tv_me")
    public WebElement unfreezeType_btn;

    @FindBy(id = "wallet.tronlink.global:id/tv_all")
    public WebElement freezeType_btn;

    @FindBy(id = "wallet.tronlink.global:id/iv_unfreeze")
    public WebElement unfreezeTargetAddress_btn;

    @FindBy(id = "wallet.tronlink.global:id/tv_ok")
    public WebElement unfreezeInfoConfirm_btn;

    @FindBy(id = "wallet.tronlink.global:id/unfreeze")
    public WebElement unfreeze_btn;

    @FindBy(id = "wallet.tronlink.global:id/rl_bottom_next")
    public WebElement signNext_btn;



    @FindBy(id = "wallet.tronlink.global:id/tv_invalid_time")
    public WebElement invalidTime_input;


    @FindBy(id = "wallet.tronlink.global:id/tv_address")
    public List<WebElement> signAddress_input;



    @FindBy(id = "wallet.tronlink.global:id/tv_selected_name")
    public WebElement selectSignName_text;

    @FindBy(id = "wallet.tronlink.global:id/rl_address_book")
    public WebElement addressBook_btn;

    @FindBy(id = "wallet.tronlink.global:id/tv_address_name")
    public WebElement addressName_display;


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
        //currentCanUseTrx = currentCanUseTrx.substring(0,currentCanUseTrx.length()-3);
        return currentCanUseTrx;
    }

    public MultiSignTransactionPage frozenMultiSign() {
        Helper.swipScreen(driver);
        try {
            frozenButtonClickAndConfirm();
            TimeUnit.SECONDS.sleep(1);
            bt_send.click();
        }catch (Exception e){
            System.out.println(e);
        }
        return new MultiSignTransactionPage(driver);

    }

    public void frozenTheEnergy() {
        //swipToFrozenBtnDisplay();
        Helper.swipScreen(driver);
        try {
            frozenButtonClickAndConfirm();
            confirm_btn.click();
            TimeUnit.SECONDS.sleep(1);
            checkPasswotd_input.sendKeys("Test0001");
            bt_send.click();
            TimeUnit.SECONDS.sleep(5);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void frozenTheBandwidth() {
        //swipToFrozenBtnDisplay();
        Helper.swipScreen(driver);
        try {
            frozenButtonClickAndConfirm();
            confirm_btn.click();
            TimeUnit.SECONDS.sleep(1);
            checkPasswotd_input.sendKeys("Test0001");
            bt_send.click();
            TimeUnit.SECONDS.sleep(5);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void frozenButtonClickAndConfirm() throws Exception{
        TimeUnit.SECONDS.sleep(1);
        freeze_btn.click();
        try{
            unfreezeInfoConfirm_btn.click();
        }catch (Exception e){
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
        frozenButtonClickAndConfirm();
    }



    public AssetPage forzenSuccessEnterAssetPage(String count) throws Exception {
        Helper.scrollToElementUntilVisible(driver,freeze_btn);
        TimeUnit.SECONDS.sleep(1);
        freezeCount_input.sendKeys(count);
        frozenButtonClickAndConfirm();
        confirm_btn.click();
        TimeUnit.SECONDS.sleep(1);
        confirm_btn.click();
        TimeUnit.SECONDS.sleep(1);
        checkPasswotd_input.sendKeys("Test0001");
        TimeUnit.SECONDS.sleep(1);
        confirm_btn.click();
        TimeUnit.SECONDS.sleep(1);
        back_btn.click();
        return new AssetPage(driver);
    }


    public void forzenSign(String count) throws Exception {
        Helper.scrollToElementUntilVisible(driver,freeze_btn);
        TimeUnit.SECONDS.sleep(1);
        freezeCount_input.sendKeys(count);
        frozenButtonClickAndConfirm();
        confirm_btn.click();
        TimeUnit.SECONDS.sleep(1);
        signNext_btn.click();
        TimeUnit.SECONDS.sleep(2);
        checkPasswotd_input.sendKeys("Test0001");
        TimeUnit.SECONDS.sleep(1);
        confirm_btn.click();
    }


    public void scrollToBottom() throws Exception {
        //Helper.swipScreen(driver);
        Helper.scrollToElementUntilVisible(driver,freeze_btn);
        TimeUnit.SECONDS.sleep(1);
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
        swipScreenLitte();
        String availableTrx = availableTrx_text.getText();
        availableTrx = availableTrx.split(" ")[1];
        availableTrx = availableTrx.substring(0,availableTrx.length()-3);
        return availableTrx;
    }



    public void inputFrozenCount1() throws Exception{
        Helper.scrollToElementUntilVisible(driver,freeze_btn);
        TimeUnit.SECONDS.sleep(1);
        freezeCount_input.sendKeys("10");

        TimeUnit.SECONDS.sleep(1);
        freeze_btn.click();
        TimeUnit.SECONDS.sleep(1);
        freezeNow_btn.click();
        TimeUnit.SECONDS.sleep(2);
        checkPasswotd_input.sendKeys("Test0001");
        confirm_btn.click();
        TimeUnit.SECONDS.sleep(1);

    }






}
