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





    @FindBy(name = "timeLeftLabel")
    public WebElement timeLeftLabel;

    //ad
    @FindBy(xpath = "XCUIElementTypeApplication[@name='TronLink']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeImage")
    public WebElement ad_pic;


    @FindBy(name = "home pop close")
    public WebElement adClose_btn;


    @FindBy(name = "资源")
    public WebElement assert_title;

    @FindBy(name = "冻结/解冻")
    public WebElement freeze_btn;


//    @FindBy(name = "解冻")
    @FindBy(id = "解冻")
    public WebElement unfreeze_btn;

    @FindBy(id = "timeLeftLabel")
    public WebElement unfreezeTimetitle;

    @FindBy(name = "assets_show_normal")
    public List<WebElement> freezeEnergyDetail_btn;

    @FindBy(name = "owerFreezedLabel")
    public List<WebElement> myFreeze_btn;


    @FindBy(name = "otherFreezedLabel")
    public List<WebElement> otherFreeze_btn;


    @FindBy(name = "totalFreezedLabel")
    public List<WebElement> totalFreeze_btn;


    @FindBy(id = "assets instruction")
    public WebElement detailsAndRules_btn;



    @FindBy(name = "expectGetTitleLabel")
    public WebElement BandwidthQuestion_btn;


    @FindBy(id = "textLabel")
    public WebElement questionContent_btn;



    @FindBy(name = "com.tronlink.wallet:id/select_bandwidth")
    public WebElement bandwidth_btn;


    @FindBy(name = "com.tronlink.wallet:id/current_use")
    public WebElement currentCanUse_btn;


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


    @FindBy(id = "currentLabel")
    public WebElement availableTrx_text;



    @FindBy(name = "com.tronlink.wallet:id/iv_cancle")
    public WebElement cleanAddress_btn;



    @FindBy(id = "addressErrorLabel")
    public WebElement errorAddress_hits;


    public WebElement getConfirm_btn(){
        return driver.findElementByIosNsPredicate("type='XCUIElementTypeButton' AND name = '完成'");
    }
    public WebElement getbandwidth_btn(){
        return driver.findElementByIosNsPredicate("type='XCUIElementTypeButton' AND name = '带宽'");
    }
    public WebElement getenergy_btn(){
        return driver.findElementByIosNsPredicate("type='XCUIElementTypeButton' AND name = '能量'");
    }
    public WebElement getConfirmGo_btn(){
        return driver.findElementByIosNsPredicate("type='XCUIElementTypeButton' AND name = '继续'");
    }
    public WebElement getfreezeNow_btn(){
        return driver.findElementByIosNsPredicate("type='XCUIElementTypeButton' AND name = '确认'");
    }

    public WebElement getDirectionFzUfz_btn(){
        List<WebElement> list = (List<WebElement>) driver.findElementsByIosNsPredicate("type='XCUIElementTypeButton' AND name = '冻结' OR name = '解冻'");
        return list.get(0);
    }
    public WebElement getFreeze_btn(){
        List<WebElement> list = (List<WebElement>) driver.findElementsByIosNsPredicate("type='XCUIElementTypeButton' AND name = '冻结'");
        return list.get((list.size()-1 >0)?list.size()-1:0);
    }
    public DetailsAndRulesPage enterDetailsAndRulesPage() {
        try {
            detailsAndRules_btn.click();
            driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
            //TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){

        }
        return new DetailsAndRulesPage(driver);
    }

    public boolean multiSignBtnIsShow(){
        try {
            driver.findElementById("多重签名");
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
        Helper.closeKeyBoard(driver);
        TimeUnit.SECONDS.sleep(1);
        driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '确认'").click();
        TimeUnit.SECONDS.sleep(1);
        inputFrozenCount("20");
        getFreeze_btn().click();
        TimeUnit.SECONDS.sleep(1);
        try{
            driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '继续'").click();
            log("click go on button");
        }catch (Exception e){
            log("not show go on button");
        }
        TimeUnit.SECONDS.sleep(1);
        comfirm_btn().click();
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
        Helper.tapWhitePlace(driver);
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
            comfirm_btn().click();
            checkPasswotd_input.sendKeys("Test0001");
            getConfirm_btn().click();
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public AssetPage enterAssetPage() {
        back_btn.click();
        return new AssetPage(driver);
    }

    public void questionClick() {
        try {
            //swip
            //Helper.scrollToElementUntilVisible(driver,BandwidthQuestion_btn);
            //Helper.swipScreen(driver);
            int x = BandwidthQuestion_btn.getLocation().getX();
            int y = BandwidthQuestion_btn.getLocation().getY();
            IOSTouchAction action = new IOSTouchAction(driver);
            action.press(PointOption.point(x+50,y+10)).release().perform();
//            BandwidthQuestion_btn.click();
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



    public void inputFrozenCount(String count) throws Exception {
        count = count.replace(" ","");
        log("count:" + count + "  size:" + count.length());
        Helper.swipScreen(driver);
        TimeUnit.SECONDS.sleep(2);
        log("TextField count: " + freezeCount_input.size());
        if (freezeCount_input.size() == 2){
            if (freezeCount_input.get(0).getText().contains("输入冻结资源数量")){
                freezeCount_input.get(0).click();
                freezeCount_input.get(0).sendKeys(count);
            }
            if (freezeCount_input.get(1).getText().contains("输入冻结资源数量")){
                freezeCount_input.get(1).click();
                freezeCount_input.get(1).sendKeys(count);
            }
        }

        Helper.closeKeyBoard(driver);

    }



    public void inputReceivingAddress(String address) throws Exception {
        //Helper.swipScreen(driver);
        Helper.scrollToElementUntilVisible(driver,getFreeze_btn());
        TimeUnit.SECONDS.sleep(2);
        freezeCount_input.get(0).sendKeys("1");
        Helper.tapWhitePlace(driver);
        TimeUnit.SECONDS.sleep(2);
        freezeCount_input.get(1).sendKeys("");
        freezeCount_input.get(1).sendKeys(address);
        Helper.tapWhitePlace(driver);
        TimeUnit.SECONDS.sleep(2);

    }

    public String getAvailableTrx() {
        waiteTime(10);
        String availableTrx = availableTrx_text.getText();
        String[]   array = availableTrx.split("：");
        availableTrx = array[1];
        availableTrx = availableTrx.substring(0,availableTrx.length()-3);
        return availableTrx;
    }

    public void inputFrozenCount1() throws Exception{
        Helper.scrollToElementUntilVisible(driver,freeze_btn);
        TimeUnit.SECONDS.sleep(1);
        freezeCount_input.get(0).sendKeys("10");

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
