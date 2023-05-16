package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class AssetPage extends AbstractPage {


    public IOSDriver<?> driver;


    public AssetPage(IOSDriver<?> driver) {
        super(driver);
//        waiteTime(5);
        this.driver = driver;
//        try {
//            if (ad_pic.isDisplayed()) {
//                adClose_btn.click();
//                log("已关闭广告图");
//            }
//        } catch (Exception e) {
//        }
//
        try {
            if(isElementExist("开始使用")){
                driver.findElementByName("开始使用").click();
                log("开始使用");
            }
        }catch (Exception  e){
        }

        try {
            if (mutisign_closebtn.isDisplayed()) {
                mutisign_closebtn.click();
                System.out.println("启动后,关闭多签提示view");
            }
        } catch (Exception e) {
        }

        waiteTime();
//        try {
//            unTillSomeThing("我的");
//        } catch (Exception e) {
//        }

//        log("time1");
//        try {
//            if (ProxyBtn.isDisplayed()) {
//                ProxyBtn.click();
//            }
//        }catch (Exception e){}
//        log("time2");

    }


    @FindBy(id = "nameLabel")
    public List<WebElement> nameLabels;

    @FindBy(name = "cashLabel")
    public WebElement cashLabel;

    @FindBy(id = "titleLabel")
    public List<WebElement> titleLabel;

    @FindBy(name = "收款")
    public WebElement receipt_btn;

    @FindBy(name = "转账")
    public WebElement send_btn;

    @FindBy(name = "配对冷钱包")
    public WebElement pairColdTitle;

    public void enterPairColdWallet() throws Exception {
        pairColdTitle.click();
        TimeUnit.SECONDS.sleep(1);
    }

    @FindBy(name = "配对离线冷钱包")
    public WebElement pairColdWallet_btn;

    public void pairColdWallet() throws Exception {
        pairColdWallet_btn.click();
        TimeUnit.SECONDS.sleep(1);
    }

    @FindBy(name = "闪兑")
    public WebElement swap_btn;

    @FindBy(xpath = "//XCUIElementTypeApplication[@name=\"TronLink\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeButton[2]")
    public WebElement market_btn;

    @FindBy(id = "chainNameLabel")
    public WebElement chainNameLabel;

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
    @FindBy(name = "我知道了")
    public WebElement ProxyBtn;

    @FindBy(name = "投票")
    public WebElement vote_btn;

    @FindBy(name = "资产")
    public WebElement asset_btn;

    @FindBy(name = "质押")
    public WebElement frozen_btn;

    @FindBy(id = "nameLabel")
    public WebElement nameLabel;

    @FindBy(id = "home addAssetBtn")
    public WebElement addAssert_btn;

    @FindBy(id = "home addAssetBtn")
    public List<WebElement> addAssert_btns;

    @FindBy(name = "闪兑")
    public WebElement eneryRant_btn;

    @FindBy(xpath = "//XCUIElementTypeApplication[@name=\"TronLink\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeButton[2]")
    public WebElement market_Tab_Button;

    @FindBy(xpath = "//XCUIElementTypeApplication[@name=\"TronLink\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeButton[4]")
    public WebElement mine_btn;

    @FindBy(xpath = "//XCUIElementTypeApplication[@name=\"TronLink\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeButton[6]")
    public WebElement mine_btn1;

    @FindBy(id = "com.tronlink.wallet:id/app1")
    public WebElement discover_btn;

    @FindBy(name = "home manager")
    public WebElement addWallet_btn;

    @FindBy(className = "XCUIElementTypeTextField")
    public List<WebElement> textFields;

    @FindBy(name = "下一步")
    public WebElement nextBtn;

    public  void  enterAddressColdWallet() throws Exception{
        textFields.get(0).sendKeys("TX99hM37vw18V6GzTHfeftrgGH61jMjQHc");
        closeKeyBoard();
        unTillSomeThingEnable("下一步");
        nextBtn.click();

    }

    public void addWallet() throws Exception {
        addWallet_btn.click();
        TimeUnit.SECONDS.sleep(1);
    }
    @FindBy(name = "trxLabel")
    public WebElement trxValue;

    @FindBy(name = "assetsLabel")
    public WebElement assetsLabel;

    @FindBy(name = "balanceLabel")
    public WebElement balanceLabel;

    @FindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"balanceLabel\"])[1]")
    public WebElement balanceTrxAsset;

    @FindBy(name = "nameLabel")
    public List<WebElement> cellArray;

    @FindBy(name = "balanceLabel")
    public List<WebElement> balanceLabelArray;


    @FindBy(name = "walletName")
    public WebElement walletNameBtn;

    @FindBy(name = "home walletName arrow")
    public WebElement changeWalletBtn;

    @FindBy(id = "blockNumberLabel")//块数量
    public WebElement blockNumberLabel;

    @FindBy(id = "blockSyncName")//块同步中...
    public WebElement blockSyncName;

    @FindBy(name = "home walletName arrow")
    public WebElement walletArrow;

    @FindBy(name = "WalletManageMenuView")
    public WebElement activePath;

    @FindBy(id = "tab discover")
    public WebElement browserBtn;

    @FindBy(xpath = "//XCUIElementTypeApplication[@name=\"TronLink\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeButton[5]")
    public WebElement browserXpathTab;

    @FindBy(xpath = "//XCUIElementTypeApplication[@name=\"TronLink\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeButton[3]")
    public WebElement browserXpathTab1;

    public void cancelAction(){
        driver.findElementByIosClassChain("**/XCUIElementTypeButton[`label == \"取消\"`]").click();
    }

    public void cancelTipsByConfirm(){
        driver.findElementByIosClassChain("**/XCUIElementTypeButton[`label == \"确认\"`]").click();
    }


    public ImportPage enterImportPage(){
        addWallet_btn.click();
        driver.findElementByName("导入钱包").click();
        return new ImportPage(driver);
    }

    public WebElement transfer_btn(){
        return  driver.findElementByName("转账");
    }



    public BrowserPage enterBrowserPage() throws Exception{
        try {
            browserXpathTab.click();
        }catch (Exception e){
        }
        try {
            browserXpathTab1.click();
        }catch (Exception e){
        }

        TimeUnit.SECONDS.sleep(3);
        return new BrowserPage(driver);
    }
    public VotePage enterVotePage() {
        try {
            vote_btn.click();
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new VotePage(driver);
    }

    //enter MyPurse Page
    public MyPursePage enterMyPursePage() throws Exception{
        trxValue.click();
        TimeUnit.SECONDS.sleep(1);
        return new MyPursePage(driver);
    }

    //enter MyPurse Page
    public String getWalletName() {
        try {
            waiteTime();
            String name = walletNameBtn.getText();
            System.out.println("成功得到名称");
            return name;
        } catch (Exception e) {
            System.out.println("失败进得到名称");

            e.printStackTrace();
        }
        return "";
    }


    //enter transfer Page
    public TransferPage enterTransportPage() {
        try {
            send_btn.click();
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new TransferPage(driver);
    }

    //ReceiptPage
    public ReceiptPage enterReceiptCoinPage() {
        try {
            driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
            receipt_btn.click();
            driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ReceiptPage(driver);
    }

    //enter FrozenAndUnfreeze Page
    public FrozenAndUnfreezePage enterFrozenAndThawingPage() {
        frozen_btn.click();
        return new FrozenAndUnfreezePage(driver);
    }


    //enter AddAssert Page
    public AddAssertPage enterAddAssertPage() {
        try {
            driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);

            if (ad_pic.isDisplayed()) {
                adClose_btn.click();
                driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
                //TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
        }
        if (addAssert_btns.size()>1){
            addAssert_btns.get(1).click();
        }else {
            addAssert_btn.click();
        }
        return new AddAssertPage(driver);
    }


    //    EnergyRentPage
    public EnergyRentPage entereneryRantage() throws Exception {
        eneryRant_btn.click();
        TimeUnit.SECONDS.sleep(3);
        return new EnergyRentPage(driver);
    }

    @FindBy(name = "我的")
    public WebElement mine_btn_selected;

    //enter mine page
    public MinePage enterMinePage() throws Exception {
        try{
            mine_btn1.click();
            log("6号位置");
        }catch (Exception e){
            mine_btn.click();
            log("4号位置--Exception选择");
        }
        if(!isElementExist("我的钱包")){
            mine_btn.click();
            log("4号位置---判断选择");

        }
        TimeUnit.SECONDS.sleep(1);

        return new MinePage(driver);
    }


    public SendTrxPage enterSendTrxPage() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        send_btn.click();
        return new SendTrxPage(driver);
    }
    public SendTrxPage enterSendTrzPage() throws Exception {
        waiteTime();
        transfer_btn().click();
        for (int i = 0; i < 5; i++) {
            if(Helper.contentTexts(titleLabel,"转出账户")){
                break;
            }else {
                TimeUnit.SECONDS.sleep(10);
                log("\n第" + i + "次 等待同步中....");
                transfer_btn().click();
            }
        }
        return new SendTrxPage(driver);

    }

    public DiscoverPage enterDiscoverPage() {
        discover_btn.click();
        return new DiscoverPage(driver);
    }


//    @FindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"nameLabel\"])[1]")
    @FindBy(xpath = "//XCUIElementTypeApplication[@name=\"TronLink\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeTable/XCUIElementTypeCell[1]")
    public WebElement TRXCell;

    public TrxPage enterTrxPage() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        TRXCell.click();
        return new TrxPage(driver);
    }
    public TrxPage enterPublicTrzPage() throws Exception {
        waiteTime();
        cellArray.get(1).click();
        waiteTime();
        return new TrxPage(driver);
    }

    public TrxPage enterTrx10Page() throws Exception {
        Helper.swipScreen(driver);
        TimeUnit.SECONDS.sleep(2);

        for (int i = 1; i < nameLabels.size(); i++) {
            if (nameLabels.get(i).getText().contains("tronlink_token")){
                System.out.println("从第" + i + "个找到了 tronlink_token");
                cellArray.get(i).click();
                break;
            }
        }


        TimeUnit.SECONDS.sleep(2);
        return new TrxPage(driver);
    }


    public TrxPage enterTrx20Page() throws Exception {
        Helper.swipScreen(driver);
        TimeUnit.SECONDS.sleep(2);
        for (int i = 1; i < nameLabels.size(); i++) {
            if (nameLabels.get(i).getText().contains("TRX")){
                System.out.println("从第" + i + "个找到了 TRX20币");
                cellArray.get(i).click();
                break;
            }
        }

        TimeUnit.SECONDS.sleep(4);
        return new TrxPage(driver);
    }

    public String getTrxCount() throws Exception {
        waiteTime();
        String trxCount =  balanceTrxAsset.getText();
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

    public MarketPage enterMarketPage() throws  Exception {

        market_Tab_Button.click();
        TimeUnit.SECONDS.sleep(1);
        return new MarketPage(driver);
    }

    public SwapPage enterSwapPage() throws  Exception {
        market_btn.click();
        TimeUnit.SECONDS.sleep(2);
        swap_btn.click();
        return new SwapPage(driver);
    }

    public SwapPage enterFinancialPage() throws  Exception {
        market_btn.click();
        TimeUnit.SECONDS.sleep(2);
        unTillSomeThing("TRX");
        return new SwapPage(driver);
    }

    public ReceiptPage enterReceiptPage() {

        receipt_btn.click();
        return new ReceiptPage(driver);
    }

    public MultiSupportPage enterMultiSupportPage() {
        try {
            TimeUnit.SECONDS.sleep(2);
            driver.findElementByName("Auto_test").click();
            MultiSupportPage wl = new MultiSupportPage(driver);
            wl.walletName = MultiSupportPage.multiWallet.Auto_test;
            TimeUnit.SECONDS.sleep(2);
            return wl;

        } catch (Exception e) {
            try {
                TimeUnit.SECONDS.sleep(2);
                driver.findElementByName("multiWallet").click();
                MultiSupportPage wl = new MultiSupportPage(driver);
                wl.walletName = MultiSupportPage.multiWallet.multiWallet;
                TimeUnit.SECONDS.sleep(2);
                return wl;
            } catch (Exception er) {
            }

        }

        return new MultiSupportPage(driver);
    }

    public void goBackAndSeeMultiTips() throws Exception {
        MyPursePage myPursePage = enterMyPursePage();
        myPursePage.swipWalletTochangeNext();
        enterMyPursePage();
        myPursePage.swipWalletTochangeNext();
        TimeUnit.SECONDS.sleep(3);
    }

    public boolean isMultiSignViewShow() throws Exception{
        TimeUnit.SECONDS.sleep(3);
        try {
            log(contentLabel.getText());
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public MultiSignRecodPage enterMultiSignRecordView() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        gotoDetailBtn.click();
        TimeUnit.SECONDS.sleep(3);
        return new MultiSignRecodPage(driver);
    }

    public void importWatchWallet() throws Exception {
        waiteTime();
        addWallet_btn.click();
        waiteTime();
        driver.findElementById("添加观察钱包").click();
        waiteTime();

        List<WebElement> Secure = (List<WebElement>) driver.findElementsByClassName("XCUIElementTypeTextField");
        if (Secure.size() > 1) {
            Secure.get(0).sendKeys("TQ1EL7zJei3VePq5B6R6r8dcGHUTXrE4oe");
            Secure.get(1).click();
            driver.findElementByName("清除文本").click();
            Secure.get(1).sendKeys("WatchWallet");
            closeKeyBoard();
            driver.findElementByIosNsPredicate("type='XCUIElementTypeButton' AND label = '添加观察钱包'").click();
            TimeUnit.SECONDS.sleep(2);

        }


    }



    public void waitShieldDataSynFinished() {
//        Long startSynTime = System.currentTimeMillis();
//        Long currentSynTime;
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (Exception e) {

        }
        String[]  arr = blockNumberLabel.getText().split("/");

        while (Integer.valueOf(arr[0])
                < Integer.valueOf(arr[1])) {
            try {
                Helper.refreshWalletScreen(driver);
                TimeUnit.SECONDS.sleep(10);
                arr = blockNumberLabel.getText().split("/");
                log("arr[0]:"+arr[0] + "  arr[1]:"+ arr[1] + "\n");
//                currentSynTime = System.currentTimeMillis();
                //同步大于十分钟，强制退出
//                if (currentSynTime - startSynTime > 900000L) {
//                    break;
//                }
            } catch (Exception e){}
        }

    }

    public MultiSignManagerPage enterMultiSignManagerPage() throws Exception {
        return  fasternterMultiSignManagerPage();

    }

    public MultiSignManagerPage fasternterMultiSignManagerPage() throws Exception {
        waiteTime();
        trxValue.click();
        log("walletArrow ed");
        TimeUnit.SECONDS.sleep(1);
        driver.findElementByName("权限管理").click();
        TimeUnit.SECONDS.sleep(4);
        return new MultiSignManagerPage(driver);


    }

    public void swipWalletTochange(String name) throws Exception{
        clickOffsetElement(walletNameBtn);
        TimeUnit.SECONDS.sleep(1);

        clickOffsetElement(driver.findElementByName(name));
        TimeUnit.SECONDS.sleep(1);

    }


}
