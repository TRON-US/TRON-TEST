package wallet.pages;

import common.utils.Helper;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
        try {
            TimeUnit.SECONDS.sleep(2);
            // if page display AD , cloese the AD
            if (ad_pic.isDisplayed()){
                adClose_btn.click();
                TimeUnit.SECONDS.sleep(1);
            }
        }catch (Exception e){}

    }



    @FindBy(id = "com.tronlink.wallet:id/iv_pic")
    public WebElement ad_pic;


    @FindBy(id = "com.tronlink.wallet:id/iv_close")
    public WebElement adClose_btn;



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


    @FindBy(xpath = "//*[@text='TRX']")
    public List<WebElement> trx20_btn;



    @FindBy(xpath = "//*[@text='tronlink_token']")
    public WebElement trx10_btn;


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
        try {
            vote_btn.click();
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new VotePage(driver);
    }

    public MarketPage enterMarketPage(){
        try {
            TimeUnit.SECONDS.sleep(2);
            // if page display AD , cloese the AD
            if (ad_pic.isDisplayed()){
                adClose_btn.click();
                TimeUnit.SECONDS.sleep(1);
            }
        }catch (Exception e){}
        market_btn.click();
        return new MarketPage(driver);
    }

    public ReceiptPage enterReceiptPage(){
        try {
            TimeUnit.SECONDS.sleep(2);
            // if page display AD , cloese the AD
            if (ad_pic.isDisplayed()){
                adClose_btn.click();
                TimeUnit.SECONDS.sleep(1);
            }
        }catch (Exception e){}
        receipt_btn.click();
        return new ReceiptPage(driver);
    }

    public String getTrxCount() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        String trxCount = trxValue.getText().split(" ")[0];
        return trxCount;
    }

    public AddAssertPage enterAddAssertPage(){
        try {
            TimeUnit.SECONDS.sleep(2);
            // if page display AD , cloese the AD
            if (ad_pic.isDisplayed()){
                adClose_btn.click();
                TimeUnit.SECONDS.sleep(1);
            }
        }catch (Exception e){}
        addAssert_btn.click();
        return new AddAssertPage(driver);
    }


    public FrozenAndUnfreezePage enterFrozenAndThawingPage(){
        try {
            freeze_btn.click();
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){
            System.out.println(e);
        }
        return new FrozenAndUnfreezePage(driver);
    }


    public MinePage enterMinePage(){
        try {
            TimeUnit.SECONDS.sleep(2);
            // if page display AD , cloese the AD
            if (ad_pic.isDisplayed()){
                adClose_btn.click();
                TimeUnit.SECONDS.sleep(1);
            }
        }catch (Exception e){}
        mine_btn.click();
        return new MinePage(driver);
    }



    public DiscoverPage enterDiscoverPage(){
        discover_btn.click();
        return new DiscoverPage(driver);
    }


    public TrxPage enterTrxPage() throws Exception {
        Helper.scrollToElementUntilVisible(driver,trx_btn);
        trx_btn.click();
        TimeUnit.SECONDS.sleep(3);
        return new TrxPage(driver);
    }


    public TrxPage enterTrx10Page() throws Exception {
        Helper.scrollToElementUntilVisible(driver,trx10_btn);
        trx10_btn.click();
        TimeUnit.SECONDS.sleep(3);
        return new TrxPage(driver);
    }


    public TrxPage enterTrx20Page() throws Exception {
        Helper.swipScreen(driver);
        trx20_btn.get(1).click();
        TimeUnit.SECONDS.sleep(3);
        return new TrxPage(driver);
    }



}
