package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * 交易记录页
 */
public class TransactionRecordPage extends AbstractPage {

    public IOSDriver<?> driver;

    public TransactionRecordPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


//
    @FindBy(id = "ic account")
    public  WebElement icNav_Icon;
    @FindBy(id = "com.tronlink.wallet:id/tv_three")
    public WebElement owner_text;


    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.support.v7.app.ActionBar.Tab[2]/android.widget.TextView")
    public WebElement navigation_tab;


}
