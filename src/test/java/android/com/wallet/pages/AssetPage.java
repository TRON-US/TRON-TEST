package android.com.wallet.pages;

import android.com.utils.Helper;
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
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        {
            //        try {
//            driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
//            // if page display AD , cloese the AD
//            if (ad_pic.isDisplayed()){
//                adClose_btn.click();
//                driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
//            }
//        }catch (Exception e){}
//
//        try {
//            TimeUnit.SECONDS.sleep(1);
//            // if updateview display ,close
//            if (update_topview.isDisplayed()) {
//                update_btn.click();
//                TimeUnit.SECONDS.sleep(1);
//            }
//        }catch (Exception e){}

        }

//        try {
//            if (isTextExist("我知道了")) {
//                findElementByText("我知道了").click();
//            }
//        }catch (Exception e){
//            driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
//
//        }
//
//        try {
//            if (isTextExist("下次再说")) {
//                findElementByText("下次再说").click();
//            }
//        }catch (Exception e){
//            driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
//
//        }
//
//        try {
//            if (mutisign_tipview.isDisplayed()) {
//                mutisign_closebtn.click();
//            }
//        }catch (Exception e){
//            driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
//
//        }

        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);


    }

@FindBy(id = "com.tronlinkpro.wallet:id/title")
public WebElement title;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_count")
    public WebElement tv_count;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_balance")
    public WebElement banlance_trx_page;

    @FindBy(id = "com.tronlinkpro.wallet:id/rl_deal_sign_tip")
    public WebElement mutisign_tipview;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_sign_close")
    public WebElement mutisign_closebtn;

    @FindBy(id = "com.tronlinkpro.wallet:id/top")
    public WebElement update_topview;

    @FindBy(id = "com.tronlinkpro.wallet:id/asset_status")
    public WebElement eyesButton;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_cancle")
    public WebElement update_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_main_title")
    public WebElement tv_main_title;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_pic")
    public WebElement ad_pic;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_money_value")
    public WebElement abountmoneyvalue;



    @FindBy(id = "com.tronlinkpro.wallet:id/iv_close")
    public WebElement adClose_btn;



    @FindBy(id = "com.tronlinkpro.wallet:id/iv_send")
    public WebElement transfer_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/rl_safe_tip")
    public WebElement safe_tip;



    @FindBy(id="com.tronlinkpro.wallet:id/iv_vote")
    public WebElement vote_btn;

    @FindBy(id="com.tronlinkpro.wallet:id/ll_tab_market")
    public WebElement market_btn;

    @FindBy(id="com.tronlinkpro.wallet:id/tv_market")
    public WebElement tv_market;


    @FindBy(id="com.tronlinkpro.wallet:id/tv_trx_value")
    public WebElement trxValue;



    @FindBy(id="com.tronlinkpro.wallet:id/rl_receive")
    public WebElement receipt_btn;



    @FindBy(id = "com.tronlinkpro.wallet:id/iv_add_assets")
    public WebElement addAssert_btn;



    @FindBy(id = "com.tronlinkpro.wallet:id/assets_name")
    public List<WebElement> assetsName;


    @FindBy(id = "com.tronlinkpro.wallet:id/iv_freeze")
    public WebElement freeze_btn;


//    @FindBy(id = "com.tronlinkpro.wallet:id/ll_tab_my")
//    public WebElement mine_btn;
    @FindBy(id = "com.tronlinkpro.wallet:id/iv_tab_my")
    public WebElement mine_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/ll_tab_discovery")
    public WebElement discover_btn;



    @FindBy(id = "com.tronlinkpro.wallet:id/ll_tab_assets")
    public WebElement assetsMain_btn;



    @FindBy(xpath = "//*[@text='TRX']")
    public WebElement trx_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/rl_main")
    public WebElement trz_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/assets_name")
    public List<WebElement> asset_names;


    @FindBy(xpath = "//*[@text='tronlink_token']")
    public WebElement trx10_btn;

    @FindBy(xpath = "//*[@text='BTTOLD']")
    public WebElement online_trc10_btn;

    @FindBy(xpath = "//*[@text='USDT']")
    public WebElement online_trc20_btn;

    @FindBy(xpath = "//*[@text='TRZ']")
    public WebElement public_account_trz_btn;

    //com.tronlinkpro.wallet:id/iv_renzheng
    @FindBy(id = "com.tronlinkpro.wallet:id/iv_renzheng")
    public List<WebElement> trx10_icon_btns;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_renzheng")
    public WebElement vip_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_chain_name")
    public WebElement currChain_name;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_walletname")
    public WebElement walletName_text;

    @FindBy(className = "com.tronlinkpro.wallet:id/tv_walletname")
    public WebElement assetList_class;

    @FindBy(id = "com.tronlinkpro.wallet:id/ll_transfer2")
    public WebElement trc10Page_transfer_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/ll_transfer2")
    public WebElement shieldDataSyn_area;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_current_block")
    public WebElement synCurrent_blockNum;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_block_amount")
    public WebElement chainCurrent_blockNum;

    @FindBy(id = "com.tronlinkpro.wallet:id/assets_count")
    public WebElement assets_count;

    @FindBy(id = "com.tronlinkpro.wallet:id/address")
    public WebElement shieldAddress_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_common_right2")
    public WebElement saveQR_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_trx_value")
    public WebElement enterWalletManageBtn;

    @FindBy(xpath = "//*[@resource-id=\"com.tronlinkpro.wallet:id/ll_transfer2\"]/android.widget.ImageView[1]")
    public WebElement trxSendBottomBtn ;

    @FindBy(id = "com.tronlinkpro.wallet:id/network")
    public WebElement network;

    public void changeChainToDappChain() throws Exception{
        if(!currChain_name.getText().contains("DAppChain")){
            MinePage mine = enterMinePage();
            SettingPage set = mine.enterSettingPage();
            network.click();
            findElementByText("DAppChain 主网").click();
            findElementByText("确认切换").click();
            TimeUnit.SECONDS.sleep(2);
            try {
                driver.closeApp();
                driver.activateApp("com.tronlinkpro.wallet");
            } catch (Exception e){}
            TimeUnit.SECONDS.sleep(6);

        }
    }


    public void waitShieldDataSynFinished() {
        Long startSynTime = System.currentTimeMillis();
        Long currentSynTime;
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (Exception e) {

        }
        while (Integer.valueOf(synCurrent_blockNum.getText()) + 400
                < Integer.valueOf(chainCurrent_blockNum.getText())) {
            try {
                TimeUnit.SECONDS.sleep(10);
                currentSynTime = System.currentTimeMillis();
                //同步大于三十分钟，强制退出
                if (currentSynTime - startSynTime > 1800000L) {
                    break;
                }
            } catch (Exception e){}
        }

    }

    public void refrashWaiteTime(Integer second) throws Exception {
        for (int i = 0 ;i<second;i++){
            Helper.swipScreenToTop(driver);
            TimeUnit.SECONDS.sleep(1);
        }

    }

    public TrxPage enterTrx20Page() throws Exception {

        Helper.slideAssetTokenScreen(driver);
        System.out.printf("asset_names.size() :  " +  asset_names.size());
        for (int i = 1; i < asset_names.size(); i++) {
            System.out.printf("asset_names item " + i + "  :  " +  asset_names.get(i).getText());

            if (asset_names.get(i).getText().contains("TRX")){
                System.out.println("从第" + i + "个找到了 TRX20币");
                asset_names.get(i).click();
                break;
            }
        }
        return new TrxPage(driver);

    }


    public TrxPage enterTrx10Page() throws Exception {
        Helper.swipScreen(driver);
        try {
            waiteTime();
            trx10_btn.click();
        } catch (Exception e) {
            if (trx10_icon_btns.size() > 2){
                trx10_icon_btns.get(1).click();
            }
        }
        TimeUnit.SECONDS.sleep(2);
        return new TrxPage(driver);
    }


    public SendTrxPage enterSendTrxPage() {
        transfer_btn.click();
        return new SendTrxPage(driver);
    }

    public SendTrxPage trxSendTrxPage() {
        trxSendBottomBtn.click();
        return new SendTrxPage(driver);
    }

//*[@resource-id="com.tronlinkpro.wallet:id/ll_transfer2"]/android.widget.ImageView[1]

    public SendTrxPage enterSendTrc10Page() {
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        Helper.swipScreenLitte(driver);
        try {
            trx10_btn.click();
        } catch (Exception e) {
            if (trx10_icon_btns.size() > 2){
                trx10_icon_btns.get(1).click();
            }
        }
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        trc10Page_transfer_btn.click();
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        return new SendTrxPage(driver);
    }

    public SendTrxPage enterOnlineSendTrc10Page() throws Exception {

        SendTrxPage page  = enterSendTrxPage();
        page.selectTokenByName("BTT");
        return page;
    }



    public SendTrxPage enterSendTrc20Page() throws  Exception {

        enterTrx20Page();
        trc10Page_transfer_btn.click();
        return new SendTrxPage(driver);
    }

    public SendTrxPage enterOnlineSendTrc20Page() throws Exception{


        SendTrxPage page = enterSendTrxPage();
        page.selectTokenByName("WIN");
        return page;

    }


    public SendTrxPage publicAccountenterSendTrzPage() {
        Helper.swipScreen(driver);
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        try {
            public_account_trz_btn.click();
        } catch (Exception e) {
            public_account_trz_btn.click();
        }
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        trc10Page_transfer_btn.click();
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        return new SendTrxPage(driver);
    }











    public VotePage enterVotePage(){
        try {
            vote_btn.click();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new VotePage(driver);
    }

    public MarketPage enterMarketPage() {
        market_btn.click();
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        try{
            findElementByText("行情").click();
        }catch (Exception e){
            System.out.println("market_btn did notfount");
            driver.findElementById("com.tronlinkpro.wallet:id/rl_bg_just_swap").click();
            findElementByText("行情").click();
        }
        return new MarketPage(driver);
    }

    public ReceiptPage enterReceiptPage(){
//        try {
//            TimeUnit.SECONDS.sleep(2);
//            // if page display AD , cloese the AD
//            if (ad_pic.isDisplayed()){
//                adClose_btn.click();
//                TimeUnit.SECONDS.sleep(1);
//            }
//        }catch (Exception e){}
        receipt_btn.click();
        return new ReceiptPage(driver);
    }

    public String getTrxCount() throws Exception {
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        String trxCount = trxValue.getText().split(" ")[0];
        return trxCount;
    }

    public AddAssertPage enterAddAssertPage(){
        addAssert_btn.click();
        return new AddAssertPage(driver);
    }


    public FrozenAndUnfreezePage enterFrozenAndUnfreezePage() throws Exception{

            TimeUnit.SECONDS.sleep(1);
            freeze_btn.click();

        return new FrozenAndUnfreezePage(driver);
    }




    public MinePage enterMinePage(){
        try {
            TimeUnit.SECONDS.sleep(1);
            mine_btn.click();
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e ){
            System.out.println( e );
        }

        return new MinePage(driver);
    }



    public DiscoverPage enterDiscoverPage() throws Exception{
        TimeUnit.SECONDS.sleep(3);
        discover_btn.click();
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(10);
        return new DiscoverPage(driver);
    }


    public TrxPage enterTrxPage() throws Exception {
        Helper.scrollToElementUntilVisible(driver,trx_btn);
        trx_btn.click();
        return new TrxPage(driver);
    }

    public TrxPage enterBTTPage() throws Exception {
        Helper.scrollToElementUntilVisible(driver,online_trc10_btn);
        online_trc10_btn.click();
        return new TrxPage(driver);
    }


    //入参0，为代表进入trx 详细交易页面；入参1，为代表进入trc10详细交易页面；入参2，为代表进入trc20详细交易页面
    public TransactionDetailInfomaitonPage enterTransactionDetailPage(Integer type) throws Exception {
        TrxPage trx;
        switch (type) {
            case 0 :
                trx = enterTrxPage();
                break;
            case 1 :
                trx = enterTrx10Page();
                break;
            case 2 :
                trx = enterTrx20Page();
                break;
            case 3 :
                trx = publicAccountEnterTrzPage();
                break;
            default :
                trx = enterTrxPage();
        }
        waiteTime();
        findElementByText("转账").click();
        findByShotId("address").click();
        TimeUnit.SECONDS.sleep(2);
        return new TransactionDetailInfomaitonPage(driver);
    }

    public TransactionDetailInfomaitonPage pubilcEnterTrzReceiveTransactionDetailPage() throws Exception {
        TrxPage trx = publicAccountEnterTrzPage();
        trx.tranfer_tab.get(2).click();
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        trx.tranferIncount_text.get(1).click();
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(3);
        return new TransactionDetailInfomaitonPage(driver);
    }

    //0代表进入第一条转账接收页面，1代编进入第一条收款接收页面
    public TransactionDetailInfomaitonPage enterTrzTransactionDetailPage(Integer type) throws Exception {
        TrzPage trz = enterTrzPage();

        switch (type) {
            case 0 :
                trz.tranfer_tab.get(1).click();
                break;
            case 1 :
                trz.tranfer_tab.get(2).click();
                break;
            default :
                trz.tranfer_tab.get(0).click();
        }

        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        trz.tranferRecordCount_text.get(1).click();
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        return new TransactionDetailInfomaitonPage(driver);
    }


    //入参0，为代表进入trx 详细交易页面；入参1，为代表进入trc10详细交易页面；入参2，为代表进入trc20详细交易页面
    public TransactionDetailInfomaitonPage enterReceiverTransactionDetailPage(Integer type) throws Exception {
        TrxPage trx;
        switch (type) {
            case 0 :
                trx = enterTrxPage();
                break;
            case 1 :
                trx = enterTrx10Page();
                break;
            case 2 :
                trx = enterTrx20Page();
                break;
            default :
                trx = enterTrxPage();
        }
        trx.tranfer_tab.get(2).click();
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        trx.tranferIncount_text.get(1).click();
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        return new TransactionDetailInfomaitonPage(driver);
    }


    //入参0，为代表进入trx 详细交易页面；入参1，为代表进入trc10详细交易页面；入参2，为代表进入trc20详细交易页面
    public TransactionDetailInfomaitonPage enterDepositTransactionDetailPage(Integer type) throws Exception {
        TrxPage trx;
        switch (type) {
            case 0 :
                trx = enterTrxPage();
                break;
            case 1 :
                trx = enterTrx10Page();
                break;
            case 2 :
                trx = enterTrx20Page();
                break;
            default :
                trx = enterTrxPage();
        }
        trx.tranfer_tab.get(3).click();
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        trx.tranferIncount_text.get(1).click();
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        return new TransactionDetailInfomaitonPage(driver);
    }

    //入参0，为代表进入trx 详细交易页面；入参1，为代表进入trc10详细交易页面；入参2，为代表进入trc20详细交易页面
    public TransactionDetailInfomaitonPage enterWithdrawTransactionDetailPage(Integer type) throws Exception {
        TrxPage trx;
        switch (type) {
            case 0 :
                trx = enterTrxPage();
                break;
            case 1 :
                trx = enterTrx10Page();
                break;
            case 2 :
                trx = enterTrx20Page();
                break;
            default :
                trx = enterTrxPage();
        }
        trx.tranfer_tab.get(3).click();
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        trx.tranferIncount_text.get(1).click();
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        return new TransactionDetailInfomaitonPage(driver);
    }





    public TrzPage enterTrzPage() throws Exception {
        waitShieldDataSynFinished();
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        try {
            mine_btn.click();
            assetsMain_btn.click();
        } catch (Exception e) {
            assetsMain_btn.click();
        }
        TimeUnit.SECONDS.sleep(1);
        trz_btn.click();
        TimeUnit.SECONDS.sleep(10);
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        return new TrzPage(driver);
    }

    public TrxPage publicAccountEnterTrzPage() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        waiteTime();
        mine_btn.click();
        waiteTime();
        assetsMain_btn.click();
        Helper.swipScreen(driver);
        TimeUnit.SECONDS.sleep(1);
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        Helper.swipScreen(driver);
        try {
            waiteTime();

            public_account_trz_btn.click();
        } catch (Exception e) {
            public_account_trz_btn.click();
        }
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(1);
        return new TrxPage(driver);
    }





    public MyPursePage enterMyPursePage() throws Exception {
        enterWalletManageBtn.click();
        return new MyPursePage(driver);
    }



}
