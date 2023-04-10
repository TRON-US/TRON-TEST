package android.com.wallet.pages;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class GuidePage extends AbstractPage {

    public AndroidDriver<?> driver;

    public GuidePage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "com.tronlinkpro.wallet:id/input_address")
    public WebElement inputAddress;

    @FindBy(id = "com.tronlinkpro.wallet:id/input_name")
    public WebElement inputName;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_import")
    public WebElement impAccount;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_title")
    public WebElement title;

    @FindBy(id = "com.tronlinkpro.wallet:id/btn_know")
    public WebElement knowBtn ;

    @FindBy(id = "com.tronlinkpro.wallet:id/btn_confirm")
    public WebElement modeconfirmBtn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_note_notice")
    public WebElement noticeTitle;

    @FindBy(id = "com.tronlinkpro.wallet:id/indicator_1")
    public WebElement indicator1;

    @FindBy(id = "com.tronlinkpro.wallet:id/indicator_2")
    public WebElement indicator2;

    @FindBy(id = "com.tronlinkpro.wallet:id/indicator_3")
    public WebElement indicator3;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_observation")
    public WebElement observationBtn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_ledger")
    public WebElement ledgerBtn;

    @FindBy(id = "com.tronlinkpro.wallet:id/rl_switch")
    public WebElement switchBtn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_inner_title")
    public WebElement subtitle;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_import")
    public WebElement importBtn;

    @FindBy(id = "com.tronlinkpro.wallet:id/rl_import")
    public WebElement rl_import;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_create")
    public WebElement creatBtn;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_common_right")
    public WebElement safariBtn;

    @FindBy(id = "com.tronlinkpro.wallet:id/bt_accept")
    public WebElement accBtn;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_common_left")
    public WebElement backBtn;

    public UserAgreementPage enterUserAgreementPage() {
        try {
            TimeUnit.SECONDS.sleep(1);
            impAccount.click();
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){
            new Base().log("impAccount button not found");
        }
        return new UserAgreementPage(driver);
    }


    public void LeftSwapScreen(){
        AndroidTouchAction action = new AndroidTouchAction(driver);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        Duration duration = Duration.ofMillis(200);
        action.press(
                PointOption.point(width*3/4, height/2))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(width*1/4, height/2))
                .release().perform();
    }


    public ImportRoutePage enterImportPage() throws Exception{
        importBtn.click();
        if (isElementExist("com.tronlinkpro.wallet:id/bt_accept")){
            swipUntilElementEnable(accBtn);
            accBtn.click();
        }
        return new ImportRoutePage(driver);
    }

    public ImportRoutePage enterImportPageNone() throws Exception{
        importBtn.click();
        return new ImportRoutePage(driver);
    }

    public ImportRoutePage enterCreatePage() throws Exception{
        creatBtn.click();
        if (isElementExist("com.tronlinkpro.wallet:id/bt_accept")){
            swipUntilElementEnable(accBtn);
            accBtn.click();
        }
        return new ImportRoutePage(driver);
    }

    public ImportRoutePage enterObservationPage() throws Exception{
        observationBtn.click();
        if (isElementExist("com.tronlinkpro.wallet:id/bt_accept")){
            swipUntilElementEnable(accBtn);
            accBtn.click();
        }
        return new ImportRoutePage(driver);
    }

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_cold")
    public WebElement tv_cold;
    public void enterColdPairedPage() throws Exception{
        tv_cold.click();
        if (isElementExist("com.tronlinkpro.wallet:id/bt_accept")){
            swipUntilElementEnable(accBtn);
            accBtn.click();
        }
    }


    public ImportRoutePage enterLedgerPage() throws Exception{
        ledgerBtn.click();
        return new ImportRoutePage(driver);
    }


}
