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



}
