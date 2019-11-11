package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AssetPage extends AbstractPage {


    public IOSDriver<?> driver;


    public AssetPage(IOSDriver<?> driver) {
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



    //ad
    @FindBy(xpath = "//XCUIElementTypeApplication[@name='TronLink']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeImage")
    public WebElement ad_pic;



    @FindBy(name = "home pop close")
    public WebElement adClose_btn;



    @FindBy(name = "投票")
    public WebElement vote_btn;



    @FindBy(name = "冻结/解冻")
    public WebElement frozen_btn;



    @FindBy(name = "添加资产")
    public WebElement addAssert_btn;



    @FindBy(name = "testAssetIssue_1567077083240")
    public WebElement myNewAddAsset_text;



    @FindBy(name = "我")
    public WebElement mine_btn;



    public VotePage enterVotePage(){
        try {
            vote_btn.click();
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new VotePage(driver);
    }



    //enter FrozenAndUnfreeze Page
    public FrozenAndUnfreezePage enterFrozenAndUnfreezePage(){
        try {
            frozen_btn.click();
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new FrozenAndUnfreezePage(driver);
    }



    //enter AddAssert Page
    public AddAssertPage enterAddAssertPage() throws Exception {
        addAssert_btn.click();
        TimeUnit.SECONDS.sleep(2);
        return new AddAssertPage(driver);
    }



    //enter mine page
    public MinePage enterMinePage(){
        mine_btn.click();
        return new MinePage(driver);
    }








}
