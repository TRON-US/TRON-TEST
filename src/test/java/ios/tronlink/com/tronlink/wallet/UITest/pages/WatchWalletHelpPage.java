package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class WatchWalletHelpPage extends AbstractPage {

    public IOSDriver<?> driver;

    public WatchWalletHelpPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
        waiteTime();
    }

    public void closeWatchTips() throws Exception {
        if (isElementExist("home function watch icon close")) {
            driver.findElementByName("home function watch icon close").click();
        }
    }

    public void sendTrx() throws Exception {
        AssetPage assetPage = new AssetPage(driver);
        SendTrxPage trxPage = assetPage.enterSendTrxPage();
        trxPage.inputReceivedAddress("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
        trxPage.goToSecondPage();
        trxPage.inputReceivedAmount("1");
        trxPage.sendButtonClick();
        TimeUnit.SECONDS.sleep(8);
        trxPage.broadcastWatchButtonClick();
        TimeUnit.SECONDS.sleep(4);
    }

    public void send10token() throws Exception {
        AssetPage assetPage = new AssetPage(driver);
        SendTrxPage trxPage = assetPage.enterSendTrxPage();
        trxPage.inputReceivedAddress("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
        trxPage.goToSecondPage();
        Double value = trxPage.inputTRC10AndSendAmount("1");
        trxPage.broadcastWatchButtonClick();

        TimeUnit.SECONDS.sleep(4);
    }
    public void send20token() throws Exception {
        AssetPage assetPage = new AssetPage(driver);
        SendTrxPage trxPage = assetPage.enterSendTrxPage();
        trxPage.inputReceivedAddress("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
        trxPage.goToSecondPage();
        Double value = trxPage.inputTRC20AndSendAmount("1");
        trxPage.broadcastWatchButtonClick();
        TimeUnit.SECONDS.sleep(4);

    }


    public void makeApropos() throws Exception {
        AssetPage assetPage = new AssetPage(driver);
        MinePage mine = assetPage.enterMinePage();
        AdvanceFuncPage minePage = mine.enterAdvancePage();
        CommitteePage committeePage = minePage.enterCommitteePage();
        committeePage.Setuppropos.click();
        TimeUnit.SECONDS.sleep(5);
        String count = String.format("%.0f", Math.random() * 100000);
        System.out.println("委员会提议修改超级代表燃烧TRX值："+count);
        TimeUnit.SECONDS.sleep(1);
        WebElement textField = committeePage.textfieldList.get(1);
        textField.clear();
        textField.sendKeys("");
        textField.sendKeys(count);
        closeKeyBoard();
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '确认'").click();
        TimeUnit.SECONDS.sleep(10);
        log("开始找第二个确认");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '生成交易二维码'").click();
        log("开始输入密码--观察钱包看是否出现二维码");
    }



    public void maketransferIn() throws Exception {
        AssetPage assetPage = new AssetPage(driver);
        TrxPage trxPage = assetPage.enterTrxPage();
        TransferPage page = trxPage.enterTransferInPage();
        waiteTime();
        page.count_text.sendKeys("10");
        closeKeyBoard();
        TimeUnit.SECONDS.sleep(2);
        page.get_inter_btn().click();
        TimeUnit.SECONDS.sleep(8);
        confirm_btn().click();
    }
    public void maketransferIn10Token() throws Exception {
        AssetPage assetPage = new AssetPage(driver);
        TimeUnit.SECONDS.sleep(2);
        assetPage.cellArray.get(1).click();
        TimeUnit.SECONDS.sleep(4);
        TrxPage trxPage = new TrxPage(driver);
        TransferPage page = trxPage.enterTransferInPage();
        TimeUnit.SECONDS.sleep(2);
        page.count_text.sendKeys("10");
        closeKeyBoard();
        TimeUnit.SECONDS.sleep(3);
        page.get_inter_btn().click();
        TimeUnit.SECONDS.sleep(8);

    }
    public boolean maketransferOut() throws Exception {
        AssetPage assetPage = new AssetPage(driver);
        TrxPage trxPage = assetPage.enterTrxPage();
        TransferPage page = trxPage.enterTransferOutPage();
        waiteTime();
        page.count_text.sendKeys("10");
        closeKeyBoard();
        waiteTime();
        page.get_out_btn().click();
        TimeUnit.SECONDS.sleep(3);
        try {
            driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '转出'").getText();
            return  true;
        }catch (Exception e){
            return  false;
        }
    }



    public boolean isEnterColdPage(){
        try {
            waiteTime();
            driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '冷钱包已被扫描'").getText();
            return  true;
        }catch (Exception e){
            try {
                driver.findElementById("签名").getText();
                return true;
            }catch (Exception e1){
                log("没有进入冷钱包页面,没有出现 '冷钱包已被扫描' 按钮");
                return  false;

            }
        }
    }
}
