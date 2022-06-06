package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class WatchWalletHelpPage extends AbstractPage {

    public IOSDriver<?> driver;

    public WatchWalletHelpPage(io.appium.java_client.ios.IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    public void sendTrx() throws Exception {
        AssetPage assetPage = new AssetPage(driver);
        SendTrxPage trxPage = assetPage.enterSendTrxPage();
        waiteTime();
        trxPage.textFieldArray.get(1).sendKeys("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
        closeKeyBoard();
        trxPage.textFieldArray.get(2).sendKeys("1");
        closeKeyBoard();
        waiteTime();
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '发送'").click();
        TimeUnit.SECONDS.sleep(4);
        confirm_btn().click();
        TimeUnit.SECONDS.sleep(4);
    }
    public void send10token() throws Exception {
        AssetPage assetPage = new AssetPage(driver);
        waiteTime();
        TrxPage page = assetPage.enterTrx10Page();
        waiteTime();
        page.sendBtn.click();
        waiteTime();
        SendTrxPage trxPage = new SendTrxPage(driver);
        trxPage.textFieldArray.get(1).sendKeys("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
        closeKeyBoard();
        trxPage.textFieldArray.get(2).sendKeys("1");
        closeKeyBoard();
        waiteTime();
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '发送'").click();
        TimeUnit.SECONDS.sleep(4);
        confirm_btn().click();
        TimeUnit.SECONDS.sleep(4);
    }

    public void frozenTRX() throws Exception {
        AssetPage assetPage = new AssetPage(driver);
        FrozenAndUnfreezePage frozenAndUnfreezePage = assetPage.enterFrozenAndThawingPage();
        frozenAndUnfreezePage.getenergy_btn().click();
        frozenAndUnfreezePage.inputFrozenCount("1");
        Helper.swipScreenLitter(driver);
        waiteTime();
        frozenAndUnfreezePage.getFreeze_btn().click();
        waiteTime();
        if(Helper.isElementExist(driver,"继续")){
            waiteTime();
            frozenAndUnfreezePage.getConfirmGo_btn().click();
        }
        TimeUnit.SECONDS.sleep(4);
        confirm_btn().click();
    }

    public void frozenTRXbandwidth() throws Exception {
        AssetPage assetPage = new AssetPage(driver);
        FrozenAndUnfreezePage frozenAndUnfreezePage = assetPage.enterFrozenAndThawingPage();
        driver.findElementByIosNsPredicate("type='XCUIElementTypeButton' AND name = '带宽'").click();
        frozenAndUnfreezePage.inputFrozenCount("1");
        waiteTime();
        frozenAndUnfreezePage.getFreeze_btn().click();
        waiteTime();
        if(Helper.isElementExist(driver,"继续")){
            waiteTime();
            frozenAndUnfreezePage.getConfirmGo_btn().click();
        }
        TimeUnit.SECONDS.sleep(4);
        confirm_btn().click();
    }
    public void makeApropos() throws Exception {
        AssetPage assetPage = new AssetPage(driver);
        MinePage mine = assetPage.enterMinePage();
        AdvanceFuncPage minePage = mine.enterAdvancePage();
        CommitteePage committeePage = minePage.enterCommitteePage();
        waiteTime(6);
        committeePage.Setuppropos.click();
        TimeUnit.SECONDS.sleep(10);
        committeePage.textfieldList.get(2).click();
        committeePage.textfieldList.get(2).clear();
        committeePage.textfieldList.get(2).sendKeys("0.21");
        closeKeyBoard();
        waiteTime();
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '确认'").click();
        TimeUnit.SECONDS.sleep(7);
        log("开始点击确认页的 确认");
        List<WebElement> list = (List<WebElement>) driver.findElementsByName("确认");
        System.out.println(list.size());
        int max = list.size();
        list.get(max-1).click();

//        comfirm_btn().click();
    }
    public void makemultiSign() throws Exception {
        AssetPage assetPage = new AssetPage(driver);
        MinePage minePage = assetPage.enterMinePage();
        MyPursePage pursePage = minePage.enterMyPursePage();
        MultiSignManagerPage managerPage = pursePage.enterMultiSignManagerPageNew();
        try {
            if (managerPage.instructionBtn.isDisplayed()){
                System.out.println("\n1 times success 成功进入MultiSignMange");
            }else {
                System.out.println("\n1 times fails 进入MultiSignMange");
                System.out.println("\n2 times Try 进入MultiSignMange");
                managerPage = pursePage.enterMultiSignManagerPageNew();
                if(managerPage.instructionBtn.isDisplayed()){
                    System.out.println("\n2 times success 进入MultiSignMange");
                }
            }
        }catch (Exception e){
            System.out.println("\n1 times fails 进入MultiSignMange Exception");
            System.out.println("\n2 times Try 进入MultiSignMange");
            managerPage = pursePage.enterMultiSignManagerPage();
            if(managerPage.instructionBtn.isDisplayed()){
                System.out.println("\n2 times success  进入MultiSignMange");
            }else {
                System.out.println("\n last try 进入MultiSignMange");
                managerPage = pursePage.enterMultiSignManagerPageNew();
                if(managerPage.instructionBtn.isDisplayed()){
                    System.out.println("\n last times success 进入MultiSignMange");
                }
            }
        }
        System.out.println("into addPermission");
        TimeUnit.SECONDS.sleep(1);
        managerPage.addauthorBtn.click();
        System.out.println("添加权限 clicked");
        TimeUnit.SECONDS.sleep(1);
        managerPage.activeNameTF.sendKeys("duoActive");
        System.out.println("添加权限名称 clicked");
        closeKeyBoard();
        Helper.swipScreen(driver);
        TimeUnit.SECONDS.sleep(2);
        managerPage.activeContentEditBtn.click();
        System.out.println("进入编辑权限页面 clicked");
        TimeUnit.SECONDS.sleep(2);
        managerPage.trxTranPower.click();
        System.out.println("点击添加trx转账 clicked");
        TimeUnit.SECONDS.sleep(2);
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        System.out.println("确认添加对应权限 clicked");
        TimeUnit.SECONDS.sleep(1);
        managerPage.thresholdTextField.sendKeys("1");
        System.out.println("总threahold clicked");
        closeKeyBoard();
        TimeUnit.SECONDS.sleep(1);
        driver.findElementsById("addressInputTF").get(0).sendKeys("TFrK5qvApM5h9HAubPRFeNN1pAGbk8tAup");
        System.out.println("输入地址 clicked");
        driver.findElementsById("addressThreadholdNumberTF").get(0).sendKeys("1");
        System.out.println("地址对应阈值 clicked");
        TimeUnit.SECONDS.sleep(3);
        closeKeyBoard();//
        System.out.println("进入确定流程...");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        TimeUnit.SECONDS.sleep(8);
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        TimeUnit.SECONDS.sleep(1);

    }
    public void maketransferIn() throws Exception {
        AssetPage assetPage = new AssetPage(driver);
        TrxPage trxPage = assetPage.enterTrxPage();
        TransferPage page = trxPage.enterTransferInPage();
        waiteTime();
        page.count_text.sendKeys("10");
        closeKeyBoard();
        waiteTime();
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
