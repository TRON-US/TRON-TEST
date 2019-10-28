package wallet.pages;

import common.utils.Helper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import org.apache.tools.ant.taskdefs.EchoXML;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Parameters;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 资产页
 */

public class AssetPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public AssetPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "com.tronlink.wallet:id/rl_send")
    public WebElement assets_btn;

    @FindBy(id="com.tronlink.wallet:id/rl_bg_vote")
    public WebElement vote_btn;

    @FindBy(id="com.tronlink.wallet:id/appmarket")
    public WebElement market_btn;

    @FindBy(id="com.tronlink.wallet:id/tv_trx_value")
    public WebElement trxValue;

    @FindBy(id="com.tronlink.wallet:id/rl_receive")
    public WebElement receipt_btn;

    @FindBy(id = "com.tronlink.wallet:id/rl_bg_add_assets")
    public WebElement addAssert_btn;


    @FindBy(id = "com.tronlink.wallet:id/assets_name")
    public List<WebElement> myAddedAssert_btn;


    @FindBy(id = "com.tronlink.wallet:id/rl_bg_freeze_unfreeze")
    public WebElement freeze_btn;


    @FindBy(id = "com.tronlink.wallet:id/my")
    public WebElement mine_btn;


    @FindBy(id = "com.tronlink.wallet:id/app1")
    public WebElement discover_btn;


    @FindBy(id = "com.tronlink.wallet:id/assets")
    public WebElement assetsMain_btn;


    @FindBy(xpath = "//*[@text='TRX']")
    public WebElement trx_btn;


//    public void isAssetPage(String privateKey){
//        try {
//            assetsMain_btn.isDisplayed();
//        }catch (Exception e){
//            Helper.getSign(privateKey,driver);
//        }
//    }



    public SendTrxPage enterSendTrxPage() {
        assets_btn.click();
//        try {assets_btn.click();
//        }catch (Exception e){
//            Base.log("assets_btn button not found");
//        }
        return new SendTrxPage(driver);
    }

    public VotePage enterVotePage(){
        vote_btn.click();
        return new VotePage(driver);
    }

    public MarketPage enterMarketPage(){
        market_btn.click();
        return new MarketPage(driver);
    }

    public ReceiptPage enterReceiptPage(){
        receipt_btn.click();
        return new ReceiptPage(driver);
    }

    public String getTrxCount() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        String trxCount = trxValue.getText().split(" ")[0];
        return trxCount;
    }

    public AddAssertPage enterAddAssertPage(){
        addAssert_btn.click();
        return new AddAssertPage(driver);
    }


    public FrozenAndThawingPage enterFrozenAndThawingPage(){
        freeze_btn.click();
        return new FrozenAndThawingPage(driver);
    }


    public MinePage enterMinePage() {
        try {
            TimeUnit.SECONDS.sleep(1);
            mine_btn.click();
        }catch (Exception e){
            System.out.println(e);
        }
        return new MinePage(driver);
    }



    public DiscoverPage enterDiscoverPage(){
        discover_btn.click();
        return new DiscoverPage(driver);
    }


    public TrxPage enterTrxPage() throws Exception {
        trx_btn.click();
        TimeUnit.SECONDS.sleep(3);
        return new TrxPage(driver);
    }



}
