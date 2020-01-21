package ios.tronlink.com.tronlink.wallet.UITest.pages;
import io.appium.java_client.ios.IOSDriver;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;



public class AssetPage extends AbstractPage {



    public IOSDriver<?> driver;


    public AssetPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;

            try {
                TimeUnit.SECONDS.sleep(1);
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
//        }
//        try {
//            // if updateview display ,close
//            if (update_topview.isDisplayed()) {
//                update_btn.click();
//                TimeUnit.SECONDS.sleep(1);
//            }
//        }catch (Exception e){}
//
        try {
            if (mutisign_tipview.isDisplayed()) {
                mutisign_closebtn.click();
                System.out.println("启动后,关闭多签提示view");
            }
        }catch (Exception e){
        }
    }
    @FindBy(id = "nameLabel")
    public List<WebElement> nameLabels;

    @FindBy(name = "转账")
    public WebElement transfer_btn;

    @FindBy(name = "收款")
    public WebElement receipt_btn;

    @FindBy(id = "chainNameLabel")
    public WebElement chainNameLabel;

    @FindBy(name="行情")
    public WebElement market_btn;

    @FindBy(id = "contentLabel")
    public WebElement contentLabel;
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

    @FindBy(id = "gotoDetailBtn")
    public WebElement gotoDetailBtn;

    //gotoDetailBtn
    //contentLabel

    @FindBy(name = "投票")
    public WebElement vote_btn;

    @FindBy(name = "资源")
    public WebElement asset_btn;

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

    @FindBy(id = "walletName")
    public WebElement walletNameBtn;

    public VotePage enterVotePage(){
        try {
            vote_btn.click();
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new VotePage(driver);
    }

    //enter MyPurse Page
    public MyPursePage enterMyPursePage(){
        System.out.println("准备进入钱包管理页面");
        try {
            walletNameBtn.click();
            TimeUnit.SECONDS.sleep(2);
            System.out.println("成功进入钱包管理页面");

        }catch (Exception e) {
            System.out.println("失败进入钱包管理页面");

            e.printStackTrace();
        }
        return new MyPursePage(driver);
    }

    //enter MyPurse Page
    public String getWalletName(){
        try {
            String name = walletNameBtn.getText();
            TimeUnit.SECONDS.sleep(2);
            System.out.println("成功得到名称");
            return  name;
        }catch (Exception e) {
            System.out.println("失败进得到名称");

            e.printStackTrace();
        }
        return "";
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
public EnergyRentPage entereneryRantage()throws Exception {
    eneryRant_btn.click();
    TimeUnit.SECONDS.sleep(3);
    return new EnergyRentPage(driver);
}
    //enter mine page
    public MinePage enterMinePage() throws Exception{
        TimeUnit.SECONDS.sleep(2);
        mine_btn.click();
        mine_btn.click();
        TimeUnit.SECONDS.sleep(2);
        return new MinePage(driver);
    }



    public SendTrxPage enterSendTrxPage() throws Exception{
        transfer_btn.click();
        waiteTime();
        return new SendTrxPage(driver);
    }


    public DiscoverPage enterDiscoverPage(){
        discover_btn.click();
        return new DiscoverPage(driver);
    }


    public TrxPage enterTrxPage() throws Exception {
        waiteTime();
        cellArray.get(0).click();
        waiteTime();
        return new TrxPage(driver);
    }


    public TrxPage enterTrx10Page() throws Exception {
        Helper.swipRefreshScreen(driver);
        TimeUnit.SECONDS.sleep(3);
        if(nameLabels.get(2).getText().contains("tronlink_token")){
            System.out.println("点击了10币种tronlink_token");
        }
        cellArray.get(2).click();
        TimeUnit.SECONDS.sleep(4);
        return new TrxPage(driver);
    }


    public TrxPage enterTrx20Page() throws Exception {
        Helper.swipScreen(driver);
        if(nameLabels.get(1).getText().contains("TRX")){
            System.out.println("点击了20币种TRX");
        }
        cellArray.get(1).click();
        TimeUnit.SECONDS.sleep(4);
        return new TrxPage(driver);
    }

    public String getTrxCount() throws Exception {
//        TimeUnit.SECONDS.sleep(3);
        waiteTime();
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

    public MultiSupportPage enterMultiSupportPage(){
        try {
            TimeUnit.SECONDS.sleep(2);
            driver.findElementByName("Auto_test").click();
            MultiSupportPage wl = new  MultiSupportPage(driver);
            wl.walletName =  MultiSupportPage.multiWallet.Auto_test;
            TimeUnit.SECONDS.sleep(2);
            return  wl;

        }catch (Exception e){
            try{
                TimeUnit.SECONDS.sleep(2);
                driver.findElementByName("multiWallet").click();
                MultiSupportPage wl = new  MultiSupportPage(driver);
                wl.walletName =  MultiSupportPage.multiWallet.multiWallet;
                TimeUnit.SECONDS.sleep(2);
                return  wl;
            }catch (Exception er){}

        }

        return new MultiSupportPage(driver);
    }

    public void goBackAndSeeMultiTips() throws Exception{
        mine_btn.click();
        asset_btn.click();
        mine_btn.click();
        asset_btn.click();
        TimeUnit.SECONDS.sleep(4);
    }

    public boolean isMultiSignViewShow(){
        try{
            log(contentLabel.getText());
            return  true;
        }catch (Exception e){
            return false;
        }

    }

    public MultiSignRecodPage enterMultiSignRecordView()  throws Exception{
        gotoDetailBtn.click();
        TimeUnit.SECONDS.sleep(3);
        return new MultiSignRecodPage(driver);
    }

    public void importWatchWallet() throws Exception{
        addWallet_btn.click();
        TimeUnit.SECONDS.sleep(2);
        driver.findElementById("观察钱包").click();
        TimeUnit.SECONDS.sleep(2);
        driver.findElementByClassName("XCUIElementTypeTextView").sendKeys("TQ1EL7zJei3VePq5B6R6r8dcGHUTXrE4oe");
        Helper.closeKeyBoard(driver);
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '下一步'").click();
        TimeUnit.SECONDS.sleep(2);
        driver.findElementByClassName("XCUIElementTypeTextField").sendKeys("WatchWallet");
        Helper.closeKeyBoard(driver);
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '完成'").click();
        TimeUnit.SECONDS.sleep(2);

    }
}
