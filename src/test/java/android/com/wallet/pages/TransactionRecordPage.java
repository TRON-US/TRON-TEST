package android.com.wallet.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.android.AndroidDriver;

/**
 * 交易记录页
 */
public class TransactionRecordPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public TransactionRecordPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }



    @FindBy(id = "com.tronlinkpro.wallet:id/tv_three")
    public WebElement owner_text;


    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.support.v7.app.ActionBar.Tab[2]/android.widget.TextView")
    public WebElement navigation_tab;

    @FindBy(id = "com.tronlinkpro.wallet:id/root_view")
    public List<WebElement> transactionRecords;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_contract_title")
    public List<WebElement> transactionTypeList;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_contract_title")
    public WebElement transactionType;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_two")
    public WebElement freezeDetail;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_one")
    public List<WebElement> voteDetailList;


}
