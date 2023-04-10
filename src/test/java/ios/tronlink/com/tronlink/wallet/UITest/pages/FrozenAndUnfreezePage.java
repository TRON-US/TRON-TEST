package ios.tronlink.com.tronlink.wallet.UITest.pages;

import ios.tronlink.com.tronlink.wallet.utils.*;


import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
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



    @FindBy(name = "下一步")
    public WebElement freeze_btn;

    @FindBy(className = "XCUIElementTypeTextView")
    public WebElement receivedTF;

    @FindBy(id = "解锁")
    public WebElement unfreeze_btn;


    @FindBy(name = "expect_btn")
    public WebElement  expect_btn;


    @FindBy(id = "StakeHome Introduce Icon")
    public WebElement detailsAndRules_btn;


    @FindBy(className = "XCUIElementTypeTextField")
    public List<WebElement> freezeCount_input;

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


    public WebElement getUnFreeze20(){
        return driver.findElementByIosClassChain("**/XCUIElementTypeStaticText[`label == \"质押中\"`][1]");
    }
    public WebElement getConfirm_btn(){
        return driver.findElementByIosNsPredicate("type='XCUIElementTypeButton' AND name = '完成'");
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



    public WebElement getFreeze_btn(){
        return  driver.findElementByIosNsPredicate("name == \"质押\" AND type == \"XCUIElementTypeButton\"");
    }

    public void confirmAndInputPassword() throws Exception{
        TimeUnit.SECONDS.sleep(3);
        driver.findElementByIosNsPredicate("name == \"确认\" AND type == \"XCUIElementTypeButton\"").click();
        checkPasswotd_input.sendKeys("Test0001");
        driver.findElementByIosNsPredicate("name == \"完成\" AND type == \"XCUIElementTypeButton\"").click();
    }


    public void enterDepositPage(){
        if (isElementExist("质押获取资源及收益")){
            driver.findElementByIosNsPredicate("name == \"质押获取资源及收益\" AND type == \"XCUIElementTypeButton\"").click();
        }else {
            driver.findElementByIosNsPredicate("name == \"质押\" AND type == \"XCUIElementTypeButton\"").click();
        }

    }
    public WebElement willGetTip(){
        return driver.findElementByIosNsPredicate("name == \"StakeV2 WillGet Tip\" AND type == \"XCUIElementTypeButton\"");
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

    public void inputText(String num) throws Exception{
        inputTextField.sendKeys(num);
        closeKeyBoard();
    }
    public WebElement comfirmBtn(){
        return driver.findElementByIosClassChain("**/XCUIElementTypeButton[`label == \"确认\"`]");
    }
    public void unFreezeAndInputPassword() throws Exception{
        unfreeze_btn.click();
        unTillSomeThing("确认交易");
        TimeUnit.SECONDS.sleep(3);
        comfirmBtn().click();
        checkPasswotd_input.sendKeys("Test0001");
        driver.findElementByIosNsPredicate("name == \"完成\" AND type == \"XCUIElementTypeButton\"").click();
    }

    @FindBy(name = "投票")
    public WebElement vote_btn;

    public void enterVotePage(){
        vote_btn.click();
    }

    @FindBy(name = "资源")
    public WebElement resource_btn;

    public void enterResourcePage(){
        resource_btn.click();
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




    public AssetPage enterAssetPage() {
        back_btn.click();
        return new AssetPage(driver);
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

    public void enterUnFreezePage() {
        unfreeze_btn.click();

    }

    public void click100(){
        driver.findElementByIosNsPredicate("label == \"100%\" AND name == \"100%\" AND value == \"100%\"").click();
    }

    @FindBy(id = "transfer address clear")
    public WebElement addressClear;

    @FindBy(className = "XCUIElementTypeImage")
    public WebElement agreeRect;

    public void agreeClick(){
        agreeRect.click();
    }



}
