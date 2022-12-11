package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.ios.touch.IOSPressOptions;
import io.appium.java_client.touch.offset.PointOption;
import ios.tronlink.com.tronlink.wallet.utils.*;
import ios.tronlink.com.tronlink.wallet.UITest.*;


import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.Action;

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

    public void multiSignFirst() throws Exception{
        TimeUnit.SECONDS.sleep(2);
        getFirstCell.click();
        nextBtn.click();
    }


    @FindBy(id = "resource_type_bandwidth_btn")
    public WebElement resource_type_bandwidth_btn;

    @FindBy(name = "timeLeftLabel")
    public WebElement timeLeftLabel;

    //ad
    @FindBy(xpath = "XCUIElementTypeApplication[@name='TronLink']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeImage")
    public WebElement ad_pic;

    @FindBy(className = "XCUIElementTypeTextField")
    public WebElement et_amount;

    @FindBy(id = "availableTRX")
    public WebElement  tv_available_amount;

    @FindBy(xpath = "//XCUIElementTypeButton[@name=\"25%\"]")
    public WebElement amount_percent_25;

    @FindBy(xpath = "//XCUIElementTypeButton[@name=\"50%\"]")
    public WebElement amount_percent_50;

    @FindBy(xpath = "//XCUIElementTypeButton[@name=\"75%\"]")
    public WebElement amount_percent_75;

    @FindBy(xpath = "//XCUIElementTypeButton[@name=\"100%\"]")
    public WebElement amount_percent_100;

    @FindBy(id = "home pop close")
    public WebElement adClose_btn;

    @FindBy(className = "XCUIElementTypeTextField")
    public WebElement inputTextField;



    @FindBy(name = "资源")
    public WebElement assert_title;

    @FindBy(name = "下一步")
    public WebElement freeze_btn;

    @FindBy(className = "XCUIElementTypeTextView")
    public WebElement receivedTF;

//    @FindBy(name = "解冻")
    @FindBy(id = "解锁")
    public WebElement unfreeze_btn;

    @FindBy(id = "timeLeftLabel")
    public WebElement unfreezeTimetitle;

    @FindBy(name = "assets_show_normal")
    public List<WebElement> freezeEnergyDetail_btn;



    @FindBy(name = "energyFoldBtn")
    public WebElement  energyFoldBtn;

    @FindBy(name = "ower_frreze_energy")
    public WebElement  freezenEnergyNumber;

    @FindBy(name = "other_frreze_energy")
    public WebElement  otherfreezenEnergyNumber;

    @FindBy(name = "total_frreze_energy")
    public WebElement  totalfreezenEnergyNumber;


    @FindBy(name = "bandwidthFoldBtn")
    public WebElement  bandwidthFoldBtn;

    @FindBy(name = "ower_frreze_bandwidth")
    public List<WebElement>  freezenbandwidthNumber;

    @FindBy(name = "other_frreze_bandwidth")
    public WebElement  otherfreezenbandwidthNumber;

    @FindBy(name = "total_frreze_bandwidth")
    public WebElement  totalfreezenbandwidthNumber;

    @FindBy(name = "expect_btn")
    public WebElement  expect_btn;


    @FindBy(name = "ower_frreze_energy")
    public List<WebElement> myFreeze_btn;


    @FindBy(name = "otherFreezedLabel")
    public List<WebElement> otherFreeze_btn;


    @FindBy(name = "totalFreezedLabel")
    public List<WebElement> totalFreeze_btn;


    @FindBy(id = "resource manager introduce")
    public WebElement detailsAndRules_btn;



    @FindBy(name = "expectGetTitleLabel")
    public WebElement BandwidthQuestion_btn;


    @FindBy(id = "textLabel")
    public WebElement questionContent_btn;


    @FindBy(className = "XCUIElementTypeTextField")
    public List<WebElement> freezeCount_input;

    @FindBy(className = "XCUIElementTypeTextField")
    public WebElement freezeCount_inputF;

    @FindBy(name = "com.tronlink.wallet:id/bt_go")
    public WebElement freezeNow_btn;


    @FindBy(className = "XCUIElementTypeSecureTextField")
    public WebElement checkPasswotd_input;


    @FindBy(name = "com.tronlink.wallet:id/bt_send")
    public WebElement confirm_btn;



    @FindBy(name = "com.tronlink.wallet:id/iv_common_left")
    public WebElement back_btn;

//开发时候用id都可以,调试时候找不到
    @FindBy(id = "incorrectLabel")
    public WebElement error_hits;


    @FindBy(id = "current_available_label")
    public WebElement availableTrx_text;


    @FindBy(id = "resource_switch_btn")
    public WebElement resource_switch_btn;


    public WebElement getConfirm_btn(){
        return driver.findElementByIosNsPredicate("type='XCUIElementTypeButton' AND name = '完成'");
    }

    public WebElement getFinish_btn(){
        return driver.findElementByIosNsPredicate("type='XCUIElementTypeButton' AND name = '完成'");
    }


    public WebElement getbandwidth_btn(){
        return driver.findElementById("resource_type_bandwidth_label");
//        return driver.findElementByIosNsPredicate("type='XCUIElementTypeButton' AND name = '带宽'");
    }
    public WebElement getenergy_btn(){
        return driver.findElementById("resource_type_energy_label");
    }
    public WebElement getConfirmGo_btn(){
        return driver.findElementByIosNsPredicate("type='XCUIElementTypeButton' AND name = '继续'");
    }
    public WebElement getfreezeNow_btn(){
        return driver.findElementByIosNsPredicate("type='XCUIElementTypeButton' AND name = '确认'");
    }

    @FindBy(xpath = "//XCUIElementTypeButton[@name=\"能量\"]")
    public WebElement tv_stake_energy;

    @FindBy(xpath = "//XCUIElementTypeButton[@name=\"带宽\"]")
    public WebElement tv_stake_bandwidth;

    public void selectEnergyTab() throws Exception{
        slideScreenTop();
        tv_stake_energy.click();
    }

    public void selectBandWidthTab() throws Exception{
        slideScreenTop();
        tv_stake_bandwidth.click();
    }

    public void inputAmount(String amount) throws Exception{
        et_amount.sendKeys(amount);
        closeKeyBoard();
    }
    @FindBy(id = "Stake Resource Edit Icon")
    public WebElement iv_stake_edit;

    @FindBy(xpath = "//XCUIElementTypeApplication[@name=\"TronLink\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeTextField")
    public WebElement ed_input_res;

    public void enterEnergyBandWidth(String amount) throws Exception{
        iv_stake_edit.click();
        TimeUnit.SECONDS.sleep(3);
        ed_input_res.clear();
        ed_input_res.sendKeys(amount);
        closeKeyBoard();
        TimeUnit.SECONDS.sleep(1);
    }


    public WebElement getfreezenBtnPopView(){

        return driver.findElementByIosNsPredicate("type='XCUIElementTypeStaticText' AND name = '解冻'");

    }



    public WebElement getFreeze_btn(){
        return  driver.findElementByIosNsPredicate("label == \"下一步\" AND name == \"下一步\" AND type == \"XCUIElementTypeButton\"");
    }

    public WebElement confirmDeposit(){
        return  driver.findElementByXPath("//XCUIElementTypeButton[@name=\"确认质押\"]");
        //predicate 不好用换 xpath
//        return driver.findElementByIosNsPredicate("label == \"确认质押\" AND name == \"确认质押\" AND type == \"XCUIElementTypeButton\"");
    }

    public WebElement bandWidthTab(){
        return driver.findElementByIosNsPredicate("label == \"带宽\" AND name == \"带宽\" AND type == \"XCUIElementTypeButton\"");
    }

    public WebElement energyTab(){
        return driver.findElementByXPath("//XCUIElementTypeButton[@name=\"能量\"]");
    }

    public DetailsAndRulesPage enterDetailsAndRulesPage() {
        try {
            detailsAndRules_btn.click();
        }catch (Exception e){

        }
        return new DetailsAndRulesPage(driver);
    }

    @FindBy(name = "多重签名质押")
    public WebElement multiSignBtn;

    public void multiSignView(){
        multiSignBtn.click();
    }

    public WebElement multiSignDirectBtn(){
        return driver.findElementByIosClassChain("**/XCUIElementTypeStaticText[`label == \"多重签名质押\"`][2]");
    }
//(   xpath
    public void multiSignViewDirect() throws Exception{
        TimeUnit.SECONDS.sleep(3);
        multiSignDirectBtn().click();
    }
    public boolean multiSignBtnIsShow(){
        try {
            driver.findElementById("多重签名质押");
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public MultiSignRecodPage FrozenMutiSignWith(String addr) throws Exception{
        driver.findElementById("多重签名").click();
        TimeUnit.SECONDS.sleep(1);
        driver.findElementByClassName("XCUIElementTypeTextField").clear();
        driver.findElementByClassName("XCUIElementTypeTextField").sendKeys(addr);
        closeKeyBoard();
        TimeUnit.SECONDS.sleep(2);
        driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '确认'").click();
        log("确认已点击");
        inputFrozenCount("20");
        Helper.swipScreenLitter(driver);
        getFreeze_btn().click();
        TimeUnit.SECONDS.sleep(1);
        try{
            driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '继续'").click();
            log("click go on button");
        }catch (Exception e){
            log("not show go on button");
        }
        TimeUnit.SECONDS.sleep(1);
        getfreezeNow_btn().click();
        TimeUnit.SECONDS.sleep(1);
        driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '下一步'").click();
        TimeUnit.SECONDS.sleep(1);
        checkPasswotd_input.sendKeys("Test0001");
        TimeUnit.SECONDS.sleep(3);
        getConfirm_btn().click();
        TimeUnit.SECONDS.sleep(12);
        return new  MultiSignRecodPage(driver);
    }

    public AssetPage forzenSuccessEnterAssetPage(String count) throws Exception {
        Helper.scrollToElementUntilVisible(driver,getFreeze_btn());
        TimeUnit.SECONDS.sleep(1);
        freezeCount_input.get(0).sendKeys(count);
        closeKeyBoard();
        getFreeze_btn().click();
        TimeUnit.SECONDS.sleep(2);
        getfreezeNow_btn().click();
        TimeUnit.SECONDS.sleep(2);
        checkPasswotd_input.sendKeys("Test0001");
        TimeUnit.SECONDS.sleep(1);
        getConfirm_btn().click();
        TimeUnit.SECONDS.sleep(1);
        swipToLeave();
        return new AssetPage(driver);
    }

    public void frozenTheEnergy() {
        Helper.swipScreen(driver);
        try {
            TimeUnit.SECONDS.sleep(2);
            getFreeze_btn().click();
            TimeUnit.SECONDS.sleep(1);
            if(Helper.isElementExist(driver,"继续")){
                getConfirmGo_btn().click();
                TimeUnit.SECONDS.sleep(3);
            }
            TimeUnit.SECONDS.sleep(1);
            getfreezeNow_btn().click();
            checkPasswotd_input.sendKeys("Test0001");
            getConfirm_btn().click();
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void frozenInputPassword() throws Exception{
        checkPasswotd_input.sendKeys("Test0001");
        getConfirm_btn().click();
        TimeUnit.SECONDS.sleep(7);
    }

    public AssetPage enterAssetPage() {
        back_btn.click();
        return new AssetPage(driver);
    }

    public void questionClick() {
        expect_btn.click();

    }



    public WebElement inputTextField(){
        return driver.findElementByIosNsPredicate("value == \"输入质押数量\"");
    }
//
    public void inputFrozenCount(String count) throws Exception {
        inputTextField().sendKeys(count);
        TimeUnit.SECONDS.sleep(1);
        closeKeyBoard();
    }

    public void click100(){
        driver.findElementByIosNsPredicate("label == \"100%\" AND name == \"100%\" AND value == \"100%\"").click();
    }

    @FindBy(id = "transfer address clear")
    public WebElement addressClear;

    public void inputReceivingAddress(String address) throws Exception {
        TimeUnit.SECONDS.sleep(1);
        addressClear.click();
        TimeUnit.SECONDS.sleep(1);
        receivedTF.sendKeys(address);
        closeKeyBoard();

    }

    @FindBy(className = "XCUIElementTypeImage")
    public WebElement agreeRect;

    public void agreeClick(){
        agreeRect.click();
    }

    public String getAvailableTrx() {
        String availableTrx = availableTrx_text.getText();
        return availableTrx;
    }

    public void inputFrozenCount1() throws Exception{
        Helper.scrollToElementUntilVisible(driver,freeze_btn);
        TimeUnit.SECONDS.sleep(1);
        freezeCount_input.get(0).sendKeys("10");

        TimeUnit.SECONDS.sleep(1);
        getFreeze_btn().click();
        TimeUnit.SECONDS.sleep(1);
        freezeNow_btn.click();
        TimeUnit.SECONDS.sleep(2);
        checkPasswotd_input.sendKeys("Test0001");
        confirm_btn.click();
        TimeUnit.SECONDS.sleep(1);


    }

}
