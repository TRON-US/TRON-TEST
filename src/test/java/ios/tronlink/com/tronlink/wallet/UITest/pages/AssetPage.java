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
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        this.driver = driver;
        try {
            if (ad_pic.isDisplayed()) {
                adClose_btn.click();
                log("已关闭广告图");
            }
        } catch (Exception e) {
            try {
                if (adClose_btn.isDisplayed()) {
                    adClose_btn.click();
                    System.out.println("adClose_btn:已关闭广告图");
                }
            } catch (Exception el) {
            }
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
        } catch (Exception e) {
        }
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);

    }


    @FindBy(id = "nameLabel")
    public List<WebElement> nameLabels;


    @FindBy(id = "titleLabel")
    public List<WebElement> titleLabel;

    @FindBy(name = "接收")
    public WebElement receipt_btn;

    @FindBy(name = "发送")
    public WebElement send_btn;

    @FindBy(name = "闪兑")
    public WebElement swap_btn;

    @FindBy(id = "chainNameLabel")
    public WebElement chainNameLabel;

    @FindBy(name = "行情")
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

    @FindBy(name = "资产")
    public WebElement asset_btn;

    @FindBy(name = "质押")
    public WebElement frozen_btn;


    @FindBy(id = "home addAssetBtn")
    public WebElement addAssert_btn;

    @FindBy(id = "home addAssetBtn")
    public List<WebElement> addAssert_btns;

    @FindBy(name = "闪兑")
    public WebElement eneryRant_btn;

    @FindBy(xpath = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeButton[3]")
    public WebElement market_Tab_Button;

    @FindBy(xpath = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeButton[4]")
    public WebElement mine_btn;

    @FindBy(id = "com.tronlink.wallet:id/app1")
    public WebElement discover_btn;

    @FindBy(name = "home manager")
    public WebElement addWallet_btn;

    @FindBy(name = "trxLabel")
    public WebElement trxValue;

    @FindBy(name = "assetsLabel")
    public WebElement assetsLabel;


    @FindBy(name = "nameLabel")
    public List<WebElement> cellArray;

    @FindBy(id = "walletName")
    public WebElement walletNameBtn;

    @FindBy(id = "blockNumberLabel")//块数量
    public WebElement blockNumberLabel;

    @FindBy(id = "blockSyncName")//块同步中...
    public WebElement blockSyncName;

    @FindBy(name = "home walletName arrow")
    public WebElement walletArrow;

    @FindBy(name = "WalletManageMenuView")
    public WebElement activePath;

    public ImportPage enterImportPage(){
        addWallet_btn.click();
        return new ImportPage(driver);
    }

    public WebElement transfer_btn(){
        return  driver.findElementByName("发送");
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
    public MyPursePage enterMyPursePage() {

        try {
            walletNameBtn.click();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("成功进入钱包管理页面");

        } catch (Exception e) {
            System.out.println("失败进入钱包管理页面");
            e.printStackTrace();
        }
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
            driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
            send_btn.click();
//            transfer_btn().click();
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

    //enter mine page
    public MinePage enterMinePage() {
        waiteTime();
        mine_btn.click();
        return new MinePage(driver);
    }


    public SendTrxPage enterSendTrxPage() throws Exception {
        waiteTime();
        transfer_btn().click();
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


    public TrxPage enterTrxPage() throws Exception {
        waiteTime();
        cellArray.get(0).click();
        waiteTime();
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
        String trxCount =  trxValue.getText().split(" ")[0];
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
        market_btn.click();
        return new MarketPage(driver);
    }

    public SwapPage enterSwapPage() throws  Exception {
        swap_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new SwapPage(driver);
    }

    public ReceiptPage enterReceiptPage() {
        try {
            TimeUnit.SECONDS.sleep(2);
            // if page display AD , cloese the AD
            if (ad_pic.isDisplayed()) {
                adClose_btn.click();
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
        }

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
        TimeUnit.SECONDS.sleep(2);
        waiteTime();
        gotoDetailBtn.click();
        TimeUnit.SECONDS.sleep(3);
        return new MultiSignRecodPage(driver);
    }

    public void importWatchWallet() throws Exception {
        waiteTime();
        addWallet_btn.click();
        waiteTime();
        driver.findElementById("观察钱包").click();
        waiteTime();
        driver.findElementByClassName("XCUIElementTypeTextView").sendKeys("TQ1EL7zJei3VePq5B6R6r8dcGHUTXrE4oe");
        Helper.closeKeyBoard(driver);
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '下一步'").click();
        waiteTime();
        driver.findElementByClassName("XCUIElementTypeTextField").sendKeys("WatchWallet");
        Helper.closeKeyBoard(driver);
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '确定'").click();
        TimeUnit.SECONDS.sleep(2);

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
//        MinePage minePage = enterMinePage();
//        MyPursePage pursePage = minePage.enterMyPursePage();
//        MultiSignManagerPage managerPage = pursePage.enterMultiSignManagerPageNew();
//        try {
//            if (managerPage.instructionBtn.isDisplayed()) {
//                System.out.println("\n1 times success 成功进入MultiSignMange");
//                return managerPage;
//            } else {
//                managerPage = pursePage.enterMultiSignManagerPageNew();
//                if (managerPage.instructionBtn.isDisplayed()) {
//                    System.out.println("\n2 times success 成功进入MultiSignMange");
//                    return managerPage;
//                }else{
//                    managerPage = pursePage.enterMultiSignManagerPageNew();
//                    if (managerPage.instructionBtn.isDisplayed()) {
//                        System.out.println("\n3 times success 成功进入MultiSignMange");
//                        return managerPage;
//                    }else {
//                        managerPage = pursePage.enterMultiSignManagerPageNew();
//                    }
//                }
//            }
//        } catch (Exception e) {
//            log(e.getMessage());
//        }
//
//        return managerPage;
    }

    public MultiSignManagerPage fasternterMultiSignManagerPage() throws Exception {
        waiteTime();
        walletArrow.click();
        log("walletArrow ed");
        TimeUnit.SECONDS.sleep(1);
        System.out.println(activePath.getLocation());
        System.out.println(activePath.getRect());
        Helper.TapLocationOffset(driver,activePath,100,100);
        log("activePath ed");
        TimeUnit.SECONDS.sleep(3);
        return new MultiSignManagerPage(driver);


    }


}
