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

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_step")
    public WebElement tv_step;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_account")
    public WebElement tv_account;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_main_title")
    public WebElement tv_main_title;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_under_control_tips")
    public WebElement tv_under_control_tips;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_available_amount")
    public WebElement tv_available_amount;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_stake_edit")
    public WebElement iv_stake_edit;

    @FindBy(id = "com.tronlinkpro.wallet:id/amount_percent_25")
    public WebElement amount_percent_25;

    @FindBy(id = "com.tronlinkpro.wallet:id/amount_percent_50")
    public WebElement amount_percent_50;

    @FindBy(id = "com.tronlinkpro.wallet:id/amount_percent_75")
    public WebElement amount_percent_75;

    @FindBy(id = "com.tronlinkpro.wallet:id/amount_percent_100")
    public WebElement amount_percent_100;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_resource_get_amount")
    public WebElement tv_resource_get_amount;


    public void enterMultiSign(){
        tv_common_right2.click();
    }

    public void inputMultiAddress(String address) throws Exception{
        et_input_address.sendKeys(address);
        TimeUnit.SECONDS.sleep(2);
    }


    public void enterEnergyBandWidth(String amount) throws Exception{
        iv_stake_edit.click();
        TimeUnit.SECONDS.sleep(3);
        ed_input_res.clear();
        ed_input_res.sendKeys(amount);
        TimeUnit.SECONDS.sleep(1);
    }



    public void gotoMultiPageTwo(){
        btn_next.click();
    }

    public void stakeEnergyWithAmount(String amount) throws Exception{
        TimeUnit.SECONDS.sleep(2);
        tv_stake_energy.click();
        stakeAmountAndNext(amount);
    }

    public void stakeBandWidthWithAmount(String amount) throws Exception{
        tv_stake_bandwidth.click();
        stakeAmountAndNext(amount);

    }

    public void stakeAmountAndNext(String amount) throws Exception{
        inputAmount(amount);
        TimeUnit.SECONDS.sleep(2);
        btn_next_step.click();
    }

    public void inputAmount(String amount){
        et_amount.sendKeys(amount);
    }

    public void selectEnergyTab() throws Exception{
        slideScreenTop();
        tv_stake_energy.click();

    }

    public void selectBandWidthTab() throws Exception{
        slideScreenTop();
        tv_stake_bandwidth.click();
    }

    public void stakeWithThisAddress() throws Exception{
        chk_stake_amount.click();
        TimeUnit.SECONDS.sleep(2);
        btn_next.click();
        TimeUnit.SECONDS.sleep(2);
    }

    public void stakeConfirm(){
        btn_confirm.click();
    }

    public void multiSignOptionSign(){
        rl_bottom_next.click();
    }

    public void toSelectAddress(){
        rl_buttons.click();
    }

@FindBy(id = "com.tronlinkpro.wallet:id/tv_name")
public WebElement tv_name;
    @FindBy(id = "com.tronlinkpro.wallet:id/tv_trans_content")
    public WebElement tv_trans_content;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_trans_type")
    public WebElement tv_trans_type;

    @FindBy(id = "com.tronlinkpro.wallet:id/resource_type")
    public WebElement resource_type;

    @FindBy(id = "com.tronlinkpro.wallet:id/rl_bottom_next")
    public WebElement rl_bottom_next;

    @FindBy(id = "com.tronlinkpro.wallet:id/ll_container")
    public WebElement ll_container;

@FindBy(id = "com.tronlinkpro.wallet:id/tv_stake_energy")
public WebElement tv_stake_energy;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_qr3")
    public WebElement QRcodeImage;

    @FindBy(id = "com.tronlinkpro.wallet:id/ll_energy_arrow")
    public WebElement freezeEnergyDetail_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_myfreeze")
    public WebElement myFreeze_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_otherfreeze")
    public WebElement otherFreeze_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_totalfreeze")
    public WebElement totalFreeze_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_common_right2")
    public WebElement tv_common_right2;

    @FindBy(id = "com.tronlinkpro.wallet:id/ll_bandwidth_arrow")
    public WebElement freezeBandwidthDetail_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_myfreeze_bandwidth")
    public WebElement myFreezeBandwidth_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_otherfreeze_bandwidth")
    public WebElement otherFreezeBandwidth_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_totalfreeze_bandwidth")
    public WebElement totalFreezeBandwidth_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_stake_amount")
    public WebElement tv_stake_amount;


    @FindBy(id = "com.tronlinkpro.wallet:id/bandwidth_question")
    public WebElement BandwidthQuestion_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/content")
    public WebElement questionContent_btn;



    @FindBy(id = "com.tronlinkpro.wallet:id/select_bandwidth")
    public WebElement bandwidth_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/select_energy")
    public WebElement energy_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/current_use")
    public WebElement currentCanUse_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/et_amount")
    public WebElement et_amount;

    @FindBy(id = "com.tronlinkpro.wallet:id/btn_next_step")
    public WebElement btn_next_step;

    @FindBy(id = "com.tronlinkpro.wallet:id/btn_next_step")
    public WebElement freeze_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/btn_next")
    public WebElement btn_next;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_common_title2")
    public WebElement tv_common_title2;

    @FindBy(id = "com.tronlinkpro.wallet:id/et_new_password")
    public WebElement checkPasswotd_input;

    @FindBy(id = "com.tronlinkpro.wallet:id/chk_stake_amount")
    public WebElement chk_stake_amount;

    @FindBy(id = "com.tronlinkpro.wallet:id/btn_confirm")
    public WebElement btn_confirm;


    @FindBy(id = "com.tronlinkpro.wallet:id/ll_common_left")
    public WebElement back_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_back")
    public WebElement iv_back;

    @FindBy(id = "com.tronlinkpro.wallet:id/error_trx_count")
    public WebElement error_hits;


    @FindBy(id = "com.tronlinkpro.wallet:id/current_use")
    public WebElement availableTrx_text;



    @FindBy(id = "com.tronlinkpro.wallet:id/et_freeze_address")
    public WebElement freezeAddress_input;



    @FindBy(id = "com.tronlinkpro.wallet:id/iv_delete")
    public WebElement cleanAddress_btn;



    @FindBy(id = "com.tronlinkpro.wallet:id/error_address")
    public WebElement errorAddress_hits;

    @FindBy(id = "com.tronlinkpro.wallet:id/select_power_type")
    public WebElement currentType_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_me")
    public WebElement unfreezeType_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_all")
    public WebElement freezeType_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_unfreeze")
    public WebElement unfreezeTargetAddress_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/btn_confirm")
    public WebElement unfreezeInfoConfirm_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/unfreeze")
    public WebElement unfreeze_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/rl_bottom_next")
    public WebElement signNext_btn;



    @FindBy(id = "com.tronlinkpro.wallet:id/tv_invalid_time")
    public WebElement invalidTime_input;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_address")
    public List<WebElement> signAddress_input;

    @FindBy(id = "com.tronlinkpro.wallet:id/et_input_address")
    public WebElement et_input_address;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_selected_name")
    public WebElement selectSignName_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/rl_address_book")
    public WebElement addressBook_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_address_name")
    public WebElement addressName_display;

    @FindBy(id = "com.tronlinkpro.wallet:id/bt_send")
    public WebElement bt_send;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_address")
    public WebElement tv_address;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_address")
    public List<WebElement> tv_addresss;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_result")
    public WebElement tv_result;

    @FindBy(id = "com.tronlinkpro.wallet:id/btn_done_success")
    public WebElement btn_done_success;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_right_first")
    public WebElement tv_right_first;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_stake_bandwidth")
    public WebElement tv_stake_bandwidth;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_common_title2")
    public WebElement iv_common_title2;

    @FindBy(id = "com.tronlinkpro.wallet:id/doc0_spe")
    public WebElement doc0_spe;

    @FindBy(id = "com.tronlinkpro.wallet:id/btn_know_it")
    public WebElement btn_know_it;

    @FindBy(id = "com.tronlinkpro.wallet:id/rl_buttons")
    public WebElement rl_buttons;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_tutorial")
    public WebElement tv_tutorial;

    @FindBy(id = "com.tronlinkpro.wallet:id/root")
    public WebElement root;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_common_title")
    public WebElement tv_common_title;


    @FindBy(id = "com.tronlinkpro.wallet:id/iv_tip2")
    public WebElement iv_tip2;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_content")
    public WebElement tv_content;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_stake_unstake")
    public WebElement tv_stake_unstake;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_multi_sign")
    public WebElement tv_multi_sign;

    @FindBy(id = "com.tronlinkpro.wallet:id/error_view")
    public WebElement error_view;


    @FindBy(id = "com.tronlinkpro.wallet:id/iv_select")
    public WebElement iv_select;


    @FindBy(id = "com.tronlinkpro.wallet:id/ed_input_res")
    public WebElement ed_input_res;

    @FindBy(id = "com.tronlinkpro.wallet:id/ed_input_res")
    public List <WebElement> ed_input_res_array;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_resource_per_transaction")
    public WebElement tv_resource_per_transaction;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_tips")
    public WebElement iv_tips;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_vote_get_amount")
    public WebElement tv_vote_get_amount;


    public void enterDetailsAndRulesPage() {
        iv_common_title2.click();
    }

    public void inputPassword(String word){
        checkPasswotd_input.sendKeys(word);
        bt_send.click();
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
        tv_stake_energy.click();
    }

    public void frozenTheBandwidth() {
        tv_stake_bandwidth.click();
    }

    public void frozenButtonClickAndConfirm() throws Exception{
        TimeUnit.SECONDS.sleep(1);
        freeze_btn.click();
        try{
            unfreezeInfoConfirm_btn.click();
        }catch (Exception e){
        }

    }



    public AssetPage enterAssetPage() throws Exception{
        iv_back.click();
        return new AssetPage(driver);
    }



    public void inputFrozenCount(String count) throws Exception {
        //Helper.swipScreen(driver);
        Helper.scrollToElementUntilVisible(driver,freeze_btn);
        TimeUnit.SECONDS.sleep(1);
        et_amount.sendKeys(count);
        frozenButtonClickAndConfirm();
    }



    public AssetPage forzenSuccessEnterAssetPage(String count) throws Exception {
        Helper.scrollToElementUntilVisible(driver,freeze_btn);
        TimeUnit.SECONDS.sleep(1);
        et_amount.sendKeys(count);
        frozenButtonClickAndConfirm();
        TimeUnit.SECONDS.sleep(1);
        checkPasswotd_input.sendKeys("Test0001");
        TimeUnit.SECONDS.sleep(1);
        btn_confirm.click();
        TimeUnit.SECONDS.sleep(1);
        back_btn.click();
        return new AssetPage(driver);
    }


    public void forzenSign(String count) throws Exception {
        Helper.scrollToElementUntilVisible(driver,freeze_btn);
        TimeUnit.SECONDS.sleep(1);
        et_amount.sendKeys(count);
        frozenButtonClickAndConfirm();
        confirm_btn().click();
        TimeUnit.SECONDS.sleep(2);
        checkPasswotd_input.sendKeys("Test0001");
        TimeUnit.SECONDS.sleep(1);
        finish_btn().click();
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
        et_amount.sendKeys("1");
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


    public void confirmTransferPage() throws Exception{
        freeze_btn.click();
        chk_stake_amount.click();
        btn_next.click();
        TimeUnit.SECONDS.sleep(6);

    }



    public void inputOtherAddress(String address) throws Exception{
        freeze_btn.click();
        rl_buttons.click();
        et_input_address.sendKeys(address);
        chk_stake_amount.click();
        btn_next.click();
        TimeUnit.SECONDS.sleep(2);
    }

    public void  interMultiSignGuidePage() throws Exception{
        tv_common_right2.click();
        tv_tutorial.click();
    }


    public void toUnfreezePage(){
        tv_stake_unstake.click();
    }


}
