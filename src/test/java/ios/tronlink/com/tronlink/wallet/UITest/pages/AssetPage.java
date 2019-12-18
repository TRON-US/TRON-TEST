package ios.tronlink.com.tronlink.wallet.UITest.pages;
import io.appium.java_client.ios.IOSDriver;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.w3c.dom.html.HTMLInputElement;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AssetPage extends AbstractPage {


    public IOSDriver<?> driver;


    public AssetPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
        try {
//            TimeUnit.SECONDS.sleep(1);
            // if page display AD , cloese the AD
            if (ad_pic.isDisplayed()){
                adClose_btn.click();
            }

        }catch (Exception e){
           try {
               if (adClose_btn.isDisplayed()) {
                   adClose_btn.click();
               }
           }catch (Exception el){}
        }
//        try {
//            // if updateview display ,close
//            if (update_topview.isDisplayed()) {
//                update_btn.click();
//                TimeUnit.SECONDS.sleep(1);
//            }
//        }catch (Exception e){}
//
        try {
            // if mutisignview display ,close
            if (mutisign_tipview.isDisplayed()) {
                mutisign_closebtn.click();
//                TimeUnit.SECONDS.sleep(1);
            }
        }catch (Exception e){
        }
    }

    @FindBy(name = "转账")
    public WebElement transfer_btn;

    @FindBy(name = "收款")
    public WebElement receipt_btn;


    @FindBy(name="行情")
    public WebElement market_btn;

    //ad
    @FindBy(xpath = "//XCUIElementTypeApplication[@name='TronLink']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeImage")
    public WebElement ad_pic;

//提示
    @FindBy(name = "提示")
    public WebElement mutisign_tipview;

    //home popView close
    @FindBy(name = "home popView close")
    public WebElement mutisign_closebtn;


    @FindBy(name = "home pop close")
    public WebElement adClose_btn;



    @FindBy(name = "投票")
    public WebElement vote_btn;



    @FindBy(name = "冻结/解冻")
    public WebElement frozen_btn;



    @FindBy(name = "添加资产")
    public WebElement addAssert_btn;

    @FindBy(name = "能量租赁")
    public WebElement eneryRant_btn;


    @FindBy(name = "testAssetIssue_1567077083240")
    public WebElement myNewAddAsset_text;



    @FindBy(name = "我")
    public WebElement mine_btn;

    @FindBy(id = "com.tronlink.wallet:id/app1")
    public WebElement discover_btn;

    @FindBy(name = "home manager")
    public WebElement addWallet_btn;

    @FindBy(id = "com.tronlink.wallet:id/assets")
    public WebElement assetsMain_btn;


    @FindBy(xpath = "//*[@text='TRX']")
    public WebElement trx_btn;


    @FindBy(xpath = "//*[@text='TRX']")
    public List<WebElement> trx20_btn;

    @FindBy(id = "com.tronlink.wallet:id/rl_send")
    public WebElement assets_btn;

    @FindBy(xpath = "//*[@text='tronlink_token']")
    public WebElement trx10_btn;

    @FindBy(name ="trxLabel")
    public WebElement trxValue;

    @FindBy(name ="nameLabel")
    public List<WebElement> cellArray;

    public VotePage enterVotePage(){
        try {
            vote_btn.click();
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new VotePage(driver);
    }


    //enter transfer Page
    public TransferPage enterTransportPage(){
        try {
            transfer_btn.click();
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new TransferPage(driver);
    }
    //ReceiptPage
    public ReceiptPage enterReceiptCoinPage(){
        try {
            receipt_btn.click();
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ReceiptPage(driver);
    }

    //enter FrozenAndUnfreeze Page
    public FrozenAndUnfreezePage enterFrozenAndThawingPage(){
        try {
            frozen_btn.click();
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new FrozenAndUnfreezePage(driver);
    }



    //enter AddAssert Page
    public AddAssertPage enterAddAssertPage() {
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

//    EnergyRentPage
public EnergyRentPage entereneryRantage(){
    eneryRant_btn.click();
    return new EnergyRentPage(driver);
}
    //enter mine page
    public MinePage enterMinePage(){
        mine_btn.click();
        return new MinePage(driver);
    }



    public SendTrxPage enterSendTrxPage() {
        transfer_btn.click();
//        try {assets_btn.click();
//        }catch (Exception e){
//            Base.log("assets_btn bton not found");
//        }
        return new SendTrxPage(driver);
    }


    public DiscoverPage enterDiscoverPage(){
        discover_btn.click();
        return new DiscoverPage(driver);
    }


    public TrxPage enterTrxPage() throws Exception {
        cellArray.get(0).click();
        TimeUnit.SECONDS.sleep(2);
        return new TrxPage(driver);
    }


    public TrxPage enterTrx10Page() throws Exception {
        cellArray.get(2).click();
        TimeUnit.SECONDS.sleep(2);
        return new TrxPage(driver);
    }


    public TrxPage enterTrx20Page() throws Exception {
        Helper.swipScreen(driver);
        cellArray.get(1).click();
        TimeUnit.SECONDS.sleep(2);
        return new TrxPage(driver);
    }

    public String getTrxCount() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        String trxCount = trxValue.getText().split(" ")[0];
        return trxCount;
    }
    public String getTrx10Count() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        String trxCount = cellArray.get(2).getText().split(" ")[0];
        return trxCount;
    }
    public String getTrx20Count() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        String trxCount = cellArray.get(1).getText().split(" ")[0];
        return trxCount;
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
}
