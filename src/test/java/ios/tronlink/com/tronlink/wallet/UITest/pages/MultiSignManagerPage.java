package ios.tronlink.com.tronlink.wallet.UITest.pages;


import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.touch.offset.PointOption;
import ios.tronlink.com.tronlink.wallet.utils.Helper;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class MultiSignManagerPage extends AbstractPage {


    public IOSDriver<?> driver;


    public MultiSignManagerPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "multiSig instruction")
    public WebElement instructionBtn;

    @FindBy(id = "textLabel")
    public WebElement instructiontextLabel;


    @FindBy(id = "MultiSignActive")
    public WebElement havedActive;

    @FindBy(id = "activeNameTF")
    public WebElement activeNameTF;

    @FindBy(id = "MutiSig edit permission edit")
    public WebElement activeContentEditBtn;

    @FindBy(id = "TRX 转账")
    public WebElement trxTranPower;

    @FindBy(id = "提取资产")
    public WebElement freezeAssetPower;

    @FindBy(id = "thresholdTextField")
    public WebElement thresholdTextField;

    @FindBy(id = "addauthorBtn")
    public WebElement addauthorBtn;

    @FindBy(id = "addAddrHoldBtn")
    public WebElement addAddrHoldBtn;


    @FindBy(id = "owner")
    public WebElement owner;

    @FindBy(id = "multiSig edit")
    public WebElement multiSigEdit;

    @FindBy(id = "address scan")
    public WebElement addressClean;

    @FindBy(id = "resourcesLabel")
    public WebElement detailLabel;

    @FindBy(id = "chargeLabel")
    public WebElement chargeLabel;

    public String getInstructionString() {
        instructionBtn.click();
        return instructiontextLabel.getText();
    }

    public String getAllerrors() {
        try {
            List<WebElement> errarr = (List<WebElement>) driver.findElementsById("errorLabels");
            String allerrString = "";
            if (errarr.size() > 0) {
                for (int i = 0; i < errarr.size(); i++) {
                    allerrString += errarr.get(i).getText();
                }
            }
            System.out.println("errs: " + allerrString);
            return allerrString;
        } catch (Exception e) {
            return "";
        }
    }

    public String getnameNullErrorString() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        addauthorBtn.click();
        System.out.println("添加权限 clicked");
        TimeUnit.SECONDS.sleep(1);
        Helper.swipScreen(driver);
        TimeUnit.SECONDS.sleep(2);
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        TimeUnit.SECONDS.sleep(1);
        Helper.swipRefreshScreen(driver);
        TimeUnit.SECONDS.sleep(1);
        return getAllerrors();

    }
    public String getPremissonNullErrorString() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        addauthorBtn.click();
        System.out.println("添加权限 clicked");
        TimeUnit.SECONDS.sleep(1);
        Helper.swipScreen(driver);
        TimeUnit.SECONDS.sleep(2);
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        TimeUnit.SECONDS.sleep(1);
        Helper.swipRefreshScreen(driver);
        TimeUnit.SECONDS.sleep(1);
        return getAllerrors();

    }
    public String addressErrWith(String address) throws Exception {
        TimeUnit.SECONDS.sleep(1);
        addauthorBtn.click();
        System.out.println("进入添加权限");
        TimeUnit.SECONDS.sleep(1);
        Helper.swipScreen(driver);
        driver.findElementsById("addressInputTF").get(0).sendKeys(address);
        closeKeyBoard();
        System.out.println("addressInputTF have inputed");
        TimeUnit.SECONDS.sleep(1);
        return getAllerrors();
    }
    public String makedouleAddressErr()throws Exception {
        //addAddrHoldBtn
        TimeUnit.SECONDS.sleep(1);
        addauthorBtn.click();
        System.out.println("进入添加权限");
        TimeUnit.SECONDS.sleep(1);
        Helper.swipScreen(driver);
        TimeUnit.SECONDS.sleep(1);
        addAddrHoldBtn.click();
        TimeUnit.SECONDS.sleep(1);
        driver.findElementsById("addressInputTF").get(0).sendKeys("TKG4UtDejJfAQx3FsyAUs86cpcRzYcijth");
        closeKeyBoard();
        driver.findElementsById("addressInputTF").get(1).sendKeys("TKG4UtDejJfAQx3FsyAUs86cpcRzYcijth");
        closeKeyBoard();
        return getAllerrors();

    }


    public String ThrHoldErrWith(String amount) throws Exception {
        TimeUnit.SECONDS.sleep(1);
        addauthorBtn.click();
        System.out.println("添加权限 clicked");
        TimeUnit.SECONDS.sleep(1);
        Helper.swipScreen(driver);
        thresholdTextField.sendKeys(amount);
        closeKeyBoard();
        System.out.println("总threahold clicked");
        TimeUnit.SECONDS.sleep(1);
        return getAllerrors();
    }

    public String getnameToolongErrorString() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        addauthorBtn.click();
        System.out.println("添加权限 clicked");
        TimeUnit.SECONDS.sleep(1);
        activeNameTF.sendKeys("TXtrbmfwZ2LxtoCveEhZT86fTss1w8rwJE");
        System.out.println("添加权限名称 clicked");
        closeKeyBoard();
        TimeUnit.SECONDS.sleep(2);

        return getAllerrors();

    }

    public void delSignData() throws Exception {
        waiteTime();
        System.out.println("delSignData()");
        if (havedaddActive()) {
            System.out.println("need to del...");
            Helper.swipScreen(driver);
            haveActivedClickFunc();
            System.out.println("Entered Active detailPage..");
            Helper.swipScreen(driver);
            waiteTime();
            driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='删除'").click();
            TimeUnit.SECONDS.sleep(8);
            driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='删除'").click();
            TimeUnit.SECONDS.sleep(8);
            confirm_btn().click();
            TimeUnit.SECONDS.sleep(1);
            driver.findElementByClassName("XCUIElementTypeSecureTextField").sendKeys("Test0001");
            waiteTime();
            driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='完成'").click();
            TimeUnit.SECONDS.sleep(3);
        }

    }

    public boolean havedaddActive() throws Exception {
        Helper.refreshWalletScreen(driver);
        waiteTime();
        try {
            System.out.println("havedActive:print:\n" + havedActive.getText());
            return true;

        } catch (Exception e) {
            System.out.println("Exception havedActive is not find");
            return false;
        }
    }

    public boolean havedaddfreezeAssetPower() {
        try {
            TimeUnit.SECONDS.sleep(2);
            haveActivedClickFunc();
            TimeUnit.SECONDS.sleep(1);

            if (freezeAssetPower.isDisplayed()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }


    public void addPermission(String name) throws Exception {
        System.out.println("into addPermission");
        TimeUnit.SECONDS.sleep(3);
        addauthorBtn.click();
        System.out.println("添加权限 clicked");
        waiteTime();
        activeNameTF.sendKeys(name);
        System.out.println("添加权限名称 clicked");
        closeKeyBoard();
        Helper.swipScreen(driver);
        waiteTime();
        activeContentEditBtn.click();
        System.out.println("进入编辑权限页面 clicked");
        waiteTime();
        trxTranPower.click();
        System.out.println("点击添加trx转账 clicked");
        waiteTime();
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        System.out.println("确认添加对应权限 clicked");
        waiteTime();
        thresholdTextField.sendKeys("1");
        System.out.println("总threahold clicked");
        closeKeyBoard();
        waiteTime();
        driver.findElementsById("addressInputTF").get(0).sendKeys("TFrK5qvApM5h9HAubPRFeNN1pAGbk8tAup");
        System.out.println("输入地址 clicked");
        driver.findElementsById("addressThreadholdNumberTF").get(0).sendKeys("1");
        System.out.println("地址对应阈值 clicked");
        waiteTime();
        closeKeyBoard();//
        System.out.println("进入确定流程...");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        TimeUnit.SECONDS.sleep(8);
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        TimeUnit.SECONDS.sleep(8);
        driver.findElementByClassName("XCUIElementTypeSecureTextField").sendKeys("Test0001");
        waiteTime();
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='完成'").click();
        waiteTime();
    }

    public void addPermissionWatch(String name) throws Exception {
        System.out.println("into addPermission");
        TimeUnit.SECONDS.sleep(3);
        addauthorBtn.click();
        System.out.println("添加权限 clicked");
        waiteTime();
        activeNameTF.sendKeys(name);
        System.out.println("添加权限名称 clicked");
        closeKeyBoard();
        Helper.swipScreen(driver);
        waiteTime();
        activeContentEditBtn.click();
        System.out.println("进入编辑权限页面 clicked");
        waiteTime();
        trxTranPower.click();
        System.out.println("点击添加trx转账 clicked");
        waiteTime();
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        System.out.println("确认添加对应权限 clicked");
        waiteTime();
        thresholdTextField.sendKeys("1");
        System.out.println("总threahold clicked");
        closeKeyBoard();
        waiteTime();
        driver.findElementsById("addressInputTF").get(0).sendKeys("TFrK5qvApM5h9HAubPRFeNN1pAGbk8tAup");
        System.out.println("输入地址 clicked");
        driver.findElementsById("addressThreadholdNumberTF").get(0).sendKeys("1");
        System.out.println("地址对应阈值 clicked");
        waiteTime();
        closeKeyBoard();//
        System.out.println("进入确定流程...");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        TimeUnit.SECONDS.sleep(8);
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='生成交易二维码'").click();
        TimeUnit.SECONDS.sleep(10);

    }

    public void modifyPermission() throws Exception {
        System.out.println("into modifyPermission");
        TimeUnit.SECONDS.sleep(3);
        haveActivedClickFunc();
        System.out.println("添加权限 clicked");
        closeKeyBoard();
        Helper.swipScreen(driver);
        activeContentEditBtn.click();
        System.out.println("进入编辑权限页面 clicked");
        freezeAssetPower.click();
        System.out.println("点击添加质押资产 clicked");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        System.out.println("确认添加对应权限 clicked");
        TimeUnit.SECONDS.sleep(1);
        Helper.swipScreenLitter(driver);
        TimeUnit.SECONDS.sleep(2);
        System.out.println("进入确定流程...");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        TimeUnit.SECONDS.sleep(8);
        TimeUnit.SECONDS.sleep(5);
        driver.findElementByXPath("(//XCUIElementTypeButton[@name=\"确认\"])[1]").click();
        TimeUnit.SECONDS.sleep(2);
        driver.findElementByClassName("XCUIElementTypeSecureTextField").sendKeys("Test0001");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='完成'").click();
    }


    public void haveActivedClickFunc() throws Exception{
        Helper.swipScreenLitter(driver);
        System.out.println("Location:\n" + havedActive.getLocation()+"\nLocation\n Rect IS:\n" + havedActive.getRect() + "\nRect");
        IOSTouchAction action = new IOSTouchAction(driver);
        action.tap(PointOption.point(havedActive.getLocation().x+ 10,havedActive.getLocation().y + 20)).perform();
        waiteTime();
    }

    public void ownerClickFunc() throws Exception{
        waiteTime();
        try {
            System.out.println("Location:\n" + owner.getLocation()+"\nLocation\n Rect IS:\n" + owner.getRect() + "\nRect");
            IOSTouchAction action = new IOSTouchAction(driver);
            action.tap(PointOption.point(owner.getLocation().x+ 10,owner.getLocation().y + 20)).perform();
            waiteTime();
            System.out.println("1次点击进入");
        }catch (Exception e){
            System.out.println("1次失败 ");

            Helper.swipRefreshScreen(driver);
            waiteTime();
            System.out.println("Location:\n" + owner.getLocation()+"\nLocation\n Rect IS:\n" + owner.getRect() + "\nRect");
            IOSTouchAction action = new IOSTouchAction(driver);
            action.tap(PointOption.point(owner.getLocation().x+ 10,owner.getLocation().y + 20)).perform();
            waiteTime();
            System.out.println("2次点击进入");

        }

    }

    public String ownerAllkeys()  throws Exception{
        ownerClickFunc();
        String getString =  driver.findElementsById("addressInputTF").get(0).getText() + " " + driver.findElementsById("addressInputTF").get(1).getText();
        System.out.println("getStringa:" + getString);
        return getString;
    }

    public boolean inputPassword(String pass) throws Exception {
        System.out.println("into addPermission");
        waiteTime();
        addauthorBtn.click();
        System.out.println("添加权限 clicked");
        waiteTime();
        activeNameTF.sendKeys("testActive");
        System.out.println("添加权限名称 clicked");
        closeKeyBoard();
        Helper.swipScreen(driver);
        waiteTime();
        activeContentEditBtn.click();
        System.out.println("进入编辑权限页面 clicked");
        waiteTime();
        trxTranPower.click();
        System.out.println("点击添加trx转账 clicked");
        waiteTime();
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        System.out.println("确认添加对应权限 clicked");
        waiteTime();
        thresholdTextField.sendKeys("1");
        System.out.println("总threahold clicked");
        closeKeyBoard();
        waiteTime();
        driver.findElementsById("addressInputTF").get(0).sendKeys("TFrK5qvApM5h9HAubPRFeNN1pAGbk8tAup");
        System.out.println("输入地址 clicked");
        driver.findElementsById("addressThreadholdNumberTF").get(0).sendKeys("1");
        System.out.println("地址对应权重 clicked");
        closeKeyBoard();
        waiteTime();
        System.out.println("进入确定流程...");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        TimeUnit.SECONDS.sleep(8);
        System.out.println("进入确定页面流程...");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        TimeUnit.SECONDS.sleep(4);
        driver.findElementByClassName("XCUIElementTypeSecureTextField").sendKeys(pass);
        TimeUnit.SECONDS.sleep(3);
        return driver.findElementByClassName("XCUIElementTypeSecureTextField").isDisplayed();
    }

    public boolean inputEmptyPassword() throws Exception {
        System.out.println("into addPermission");
        waiteTime();
        addauthorBtn.click();
        System.out.println("添加权限 clicked");
        waiteTime();
        activeNameTF.sendKeys("testActive");
        System.out.println("添加权限名称 clicked");
        closeKeyBoard();
        Helper.swipScreen(driver);
        waiteTime();
        activeContentEditBtn.click();
        System.out.println("进入编辑权限页面 clicked");
        waiteTime();
        trxTranPower.click();
        System.out.println("点击添加trx转账 clicked");
        waiteTime();
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        System.out.println("确认添加对应权限 clicked");
        waiteTime();
        thresholdTextField.sendKeys("1");
        System.out.println("总threahold clicked");
        closeKeyBoard();
        waiteTime();
        driver.findElementsById("addressInputTF").get(0).sendKeys("TFrK5qvApM5h9HAubPRFeNN1pAGbk8tAup");
        System.out.println("输入地址 clicked");
        driver.findElementsById("addressThreadholdNumberTF").get(0).sendKeys("1");
        System.out.println("地址对应权重 clicked");
        closeKeyBoard();
        waiteTime();
        System.out.println("进入确定流程...");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        TimeUnit.SECONDS.sleep(8);
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        TimeUnit.SECONDS.sleep(8);

        return driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='完成'").isEnabled();
    }

    public void addActiveBeforeConfirm(String name) throws Exception {
        multiSigEdit.click();
        waiteTime();
        addressClean.click();
        waiteTime();
        driver.findElementByClassName("XCUIElementTypeTextField").sendKeys(name);
        closeKeyBoard();
        TimeUnit.SECONDS.sleep(2);
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        System.out.println("into addPermission");
        TimeUnit.SECONDS.sleep(2);
        Helper.refreshWalletScreen(driver);
        waiteTime();
        addauthorBtn.click();
        System.out.println("添加权限 clicked");
        waiteTime();
        activeNameTF.sendKeys("func");
        System.out.println("添加权限名称 clicked");
        closeKeyBoard();
        Helper.swipScreen(driver);
        waiteTime();
        activeContentEditBtn.click();
        System.out.println("进入编辑权限页面 clicked");
        waiteTime();
        trxTranPower.click();
        System.out.println("点击添加trx转账 clicked");
        waiteTime();
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        System.out.println("确认添加对应权限 clicked");
        waiteTime();
        thresholdTextField.sendKeys("1");
        System.out.println("总threahold clicked");
        closeKeyBoard();
        waiteTime();
        driver.findElementsById("addressInputTF").get(0).sendKeys("TFrK5qvApM5h9HAubPRFeNN1pAGbk8tAup");
        System.out.println("输入地址 clicked");
        driver.findElementsById("addressThreadholdNumberTF").get(0).sendKeys("1");
        System.out.println("地址对应阈值 clicked");
        waiteTime();
        closeKeyBoard();//
        System.out.println("进入确定流程...");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        TimeUnit.SECONDS.sleep(3);
//        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();

    }
}
