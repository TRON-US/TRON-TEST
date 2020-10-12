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
            driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
            if (ad_pic.isDisplayed()) {
                adClose_btn.click();
            }

        } catch (Exception e) {
            try {
                if (adClose_btn.isDisplayed()) {

                    adClose_btn.click();
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
    }

    @FindBy(id = "nameLabel")
    public List<WebElement> nameLabels;


    @FindBy(id = "titleLabel")
    public List<WebElement> titleLabel;

    @FindBy(id = "scanBlockView")
    public WebElement scanBlockView;

    @FindBy(id = "shieldIV")
    public WebElement shieldIV;

    @FindBy(name = "转账")
    public WebElement transfer_btn;

    @FindBy(name = "收款")
    public WebElement receipt_btn;

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

    @FindBy(name = "冻结/解冻")
    public WebElement frozen_btn;


    @FindBy(name = "添加资产")
    public WebElement addAssert_btn;

    @FindBy(name = "能量租赁")
    public WebElement eneryRant_btn;


    @FindBy(name = "testAssetIssue_1567077083240")
    public WebElement myNewAddAsset_text;

    @FindBy(id = "市场")
    public WebElement market_Tab_Button;

    @FindBy(name = "我的")
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
        System.out.println("准备进入钱包管理页面");
        try {
            walletNameBtn.click();
            TimeUnit.SECONDS.sleep(2);
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
            transfer_btn.click();
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new TransferPage(driver);
    }

    //ReceiptPage
    public ReceiptPage enterReceiptCoinPage() {
        try {
            driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
            receipt_btn.click();
            driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ReceiptPage(driver);
    }

    //enter FrozenAndUnfreeze Page
    public FrozenAndUnfreezePage enterFrozenAndThawingPage() {
        try {
            driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
            frozen_btn.click();
            driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
            //TimeUnit.SECONDS.sleep(1);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new FrozenAndUnfreezePage(driver);
    }


    //enter AddAssert Page
    public AddAssertPage enterAddAssertPage() {
        try {
            driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

            if (ad_pic.isDisplayed()) {
                adClose_btn.click();
                driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
                //TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
        }
        addAssert_btn.click();
        return new AddAssertPage(driver);
    }


    //    EnergyRentPage
    public EnergyRentPage entereneryRantage() throws Exception {
        eneryRant_btn.click();
        TimeUnit.SECONDS.sleep(3);
        return new EnergyRentPage(driver);
    }

    //enter mine page

    public MinePage enterMinePage() throws Exception{
        waiteTime(15);
        mine_btn.click();
        waiteTime();
        mine_btn.click();
        return new MinePage(driver);
    }


    public SendTrxPage enterSendTrxPage() throws Exception {
        waiteTime();
        transfer_btn.click();
        return new SendTrxPage(driver);
    }
    public SendTrxPage enterSendTrzPage() throws Exception {
        waiteTime();
        transfer_btn.click();
        for (int i = 0; i < 5; i++) {
            if(Helper.contentTexts(titleLabel,"转出账户")){
                break;
            }else {
                TimeUnit.SECONDS.sleep(10);
                log("\n第" + i + "次 等待同步中....");
                transfer_btn.click();
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

    public MarketPage enterMarketPage() throws  Exception {

        market_Tab_Button.click();
        TimeUnit.SECONDS.sleep(1);
        return new MarketPage(driver);
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
        mine_btn.click();
        asset_btn.click();
        mine_btn.click();
        asset_btn.click();
        TimeUnit.SECONDS.sleep(6);
    }

    public boolean isMultiSignViewShow() {
        try {
            log(contentLabel.getText());
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public MultiSignRecodPage enterMultiSignRecordView() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        waiteTime(10);
        gotoDetailBtn.click();
        TimeUnit.SECONDS.sleep(3);
        return new MultiSignRecodPage(driver);
    }

    public void importWatchWallet() throws Exception {
        waiteTime();
        addWallet_btn.click();
        waiteTime();
        driver.findElementById("normalWallet").click();
        waiteTime();
        driver.findElementById("观察钱包").click();
        waiteTime();
        driver.findElementByClassName("XCUIElementTypeTextView").sendKeys("TQ1EL7zJei3VePq5B6R6r8dcGHUTXrE4oe");
        Helper.closeKeyBoard(driver);
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '下一步'").click();
        waiteTime();
        driver.findElementByClassName("XCUIElementTypeTextField").sendKeys("WatchWallet");
        Helper.closeKeyBoard(driver);
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '完成'").click();
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

}
