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

    @FindBy(id = "TRX转账")
    public WebElement trxTranPower;

    @FindBy(id = "冻结资产")
    public WebElement freezeAssetPower;

    @FindBy(id = "thresholdTextField")
    public WebElement thresholdTextField;

    @FindBy(id = "addauthorBtn")
    public WebElement addauthorBtn;

    @FindBy(id = "addAddrHoldBtn")
    public WebElement addAddrHoldBtn;


    @FindBy(id = "owner")
    public WebElement owner;




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
        Helper.closeKeyBoard(driver);
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
        Helper.closeKeyBoard(driver);
        driver.findElementsById("addressInputTF").get(1).sendKeys("TKG4UtDejJfAQx3FsyAUs86cpcRzYcijth");
        Helper.closeKeyBoard(driver);
        return getAllerrors();

    }


    public String ThrHoldErrWith(String amount) throws Exception {
        TimeUnit.SECONDS.sleep(1);
        addauthorBtn.click();
        System.out.println("添加权限 clicked");
        TimeUnit.SECONDS.sleep(1);
        Helper.swipScreen(driver);
        thresholdTextField.sendKeys(amount);
        Helper.closeKeyBoard(driver); 
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
        Helper.closeKeyBoard(driver);
        TimeUnit.SECONDS.sleep(2);

        return getAllerrors();

    }

    public void delSignData() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("delSignData()");
        if (havedaddActive()) {
            System.out.println("need to del...");
            Helper.swipScreen(driver);
            haveActivedClickFunc();
//            TimeUnit.SECONDS.sleep(3);
//            System.out.println("Location:\n" + havedActive.getLocation()+"\nLocation\n Rect IS:\n" + havedActive.getRect() + "\nRect");
//            IOSTouchAction action = new IOSTouchAction(driver);
//            action.tap(PointOption.point(havedActive.getLocation().x+ 10,havedActive.getLocation().y + 20)).perform();
//            TimeUnit.SECONDS.sleep(3);
            System.out.println("Entered Active detailPage..");
            Helper.swipScreen(driver);
            TimeUnit.SECONDS.sleep(3);
            driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='删除'").click();
            TimeUnit.SECONDS.sleep(1);
            driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='删除'").click();
            TimeUnit.SECONDS.sleep(4);
            driver.findElementByClassName("XCUIElementTypeSecureTextField").sendKeys("Test0001");
            TimeUnit.SECONDS.sleep(2);
            driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='完成'").click();
            TimeUnit.SECONDS.sleep(3);
        }

    }

    public boolean havedaddActive() throws Exception {
        Helper.refreshWalletScreen(driver);
        TimeUnit.SECONDS.sleep(3);
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
            TimeUnit.SECONDS.sleep(1);
            haveActivedClickFunc();
//            havedActive.click();
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
        TimeUnit.SECONDS.sleep(1);
        addauthorBtn.click();
        System.out.println("添加权限 clicked");
        TimeUnit.SECONDS.sleep(1);
        activeNameTF.sendKeys(name);
        System.out.println("添加权限名称 clicked");
        Helper.closeKeyBoard(driver);
        Helper.swipScreen(driver);
        TimeUnit.SECONDS.sleep(2);
        activeContentEditBtn.click();
        System.out.println("进入编辑权限页面 clicked");
        TimeUnit.SECONDS.sleep(2);
        trxTranPower.click();
        System.out.println("点击添加trx转账 clicked");
        TimeUnit.SECONDS.sleep(2);
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        System.out.println("确认添加对应权限 clicked");
        TimeUnit.SECONDS.sleep(1);
        thresholdTextField.sendKeys("1");
        System.out.println("总threahold clicked");
        Helper.closeKeyBoard(driver);
        TimeUnit.SECONDS.sleep(1);
        driver.findElementsById("addressInputTF").get(0).sendKeys("TFrK5qvApM5h9HAubPRFeNN1pAGbk8tAup");
        System.out.println("输入地址 clicked");
        driver.findElementsById("addressThreadholdNumberTF").get(0).sendKeys("1");
        System.out.println("地址对应阈值 clicked");
        TimeUnit.SECONDS.sleep(3);
        Helper.closeKeyBoard(driver);//
        System.out.println("进入确定流程...");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        TimeUnit.SECONDS.sleep(1);
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        TimeUnit.SECONDS.sleep(10);
        driver.findElementByClassName("XCUIElementTypeSecureTextField").sendKeys("Test0001");
        TimeUnit.SECONDS.sleep(2);
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='完成'").click();
        TimeUnit.SECONDS.sleep(4);
    }

    public void modifyPermission() throws Exception {
        System.out.println("into modifyPermission");
        TimeUnit.SECONDS.sleep(1);
        haveActivedClickFunc();
        System.out.println("添加权限 clicked");
        TimeUnit.SECONDS.sleep(1);
        Helper.closeKeyBoard(driver);
        Helper.swipScreen(driver);
        TimeUnit.SECONDS.sleep(2);
        activeContentEditBtn.click();
        System.out.println("进入编辑权限页面 clicked");
        TimeUnit.SECONDS.sleep(2);
        freezeAssetPower.click();
        System.out.println("点击添加冻结资产 clicked");
        TimeUnit.SECONDS.sleep(2);
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        System.out.println("确认添加对应权限 clicked");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("进入确定流程...");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        TimeUnit.SECONDS.sleep(1);
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        TimeUnit.SECONDS.sleep(10);
        driver.findElementByClassName("XCUIElementTypeSecureTextField").sendKeys("Test0001");
        TimeUnit.SECONDS.sleep(2);
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='完成'").click();
        TimeUnit.SECONDS.sleep(4);
    }


    public void haveActivedClickFunc() throws Exception{
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Location:\n" + havedActive.getLocation()+"\nLocation\n Rect IS:\n" + havedActive.getRect() + "\nRect");
        IOSTouchAction action = new IOSTouchAction(driver);
        action.tap(PointOption.point(havedActive.getLocation().x+ 10,havedActive.getLocation().y + 20)).perform();
        TimeUnit.SECONDS.sleep(3);
    }

    public void ownerClickFunc() throws Exception{
        TimeUnit.SECONDS.sleep(3);
        try {
            System.out.println("Location:\n" + owner.getLocation()+"\nLocation\n Rect IS:\n" + owner.getRect() + "\nRect");
            IOSTouchAction action = new IOSTouchAction(driver);
            action.tap(PointOption.point(owner.getLocation().x+ 10,owner.getLocation().y + 20)).perform();
            TimeUnit.SECONDS.sleep(3);
            System.out.println("1次点击进入");
        }catch (Exception e){
            System.out.println("1次失败 ");

            Helper.swipRefreshScreen(driver);
            TimeUnit.SECONDS.sleep(3);
            System.out.println("Location:\n" + owner.getLocation()+"\nLocation\n Rect IS:\n" + owner.getRect() + "\nRect");
            IOSTouchAction action = new IOSTouchAction(driver);
            action.tap(PointOption.point(owner.getLocation().x+ 10,owner.getLocation().y + 20)).perform();
            TimeUnit.SECONDS.sleep(3);
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
        TimeUnit.SECONDS.sleep(1);
        addauthorBtn.click();
        System.out.println("添加权限 clicked");
        TimeUnit.SECONDS.sleep(1);
        activeNameTF.sendKeys("testActive");
        System.out.println("添加权限名称 clicked");
        Helper.closeKeyBoard(driver);
        Helper.swipScreen(driver);
        TimeUnit.SECONDS.sleep(2);
        activeContentEditBtn.click();
        System.out.println("进入编辑权限页面 clicked");
        TimeUnit.SECONDS.sleep(2);
        trxTranPower.click();
        System.out.println("点击添加trx转账 clicked");
        TimeUnit.SECONDS.sleep(2);
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        System.out.println("确认添加对应权限 clicked");
        TimeUnit.SECONDS.sleep(1);
        thresholdTextField.sendKeys("1");
        System.out.println("总threahold clicked");
        Helper.closeKeyBoard(driver);
        TimeUnit.SECONDS.sleep(1);
        driver.findElementsById("addressInputTF").get(0).sendKeys("TFrK5qvApM5h9HAubPRFeNN1pAGbk8tAup");
        System.out.println("输入地址 clicked");
        driver.findElementsById("addressThreadholdNumberTF").get(0).sendKeys("1");
        System.out.println("地址对应权重 clicked");
        Helper.closeKeyBoard(driver);
        TimeUnit.SECONDS.sleep(1);
        System.out.println("进入确定流程...");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        TimeUnit.SECONDS.sleep(1);
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        TimeUnit.SECONDS.sleep(10);
        driver.findElementByClassName("XCUIElementTypeSecureTextField").sendKeys(pass);
        TimeUnit.SECONDS.sleep(3);
        return driver.findElementByClassName("XCUIElementTypeSecureTextField").isDisplayed();
    }

    public boolean inputEmptyPassword() throws Exception {
        System.out.println("into addPermission");
        TimeUnit.SECONDS.sleep(1);
        addauthorBtn.click();
        System.out.println("添加权限 clicked");
        TimeUnit.SECONDS.sleep(1);
        activeNameTF.sendKeys("testActive");
        System.out.println("添加权限名称 clicked");
        Helper.closeKeyBoard(driver);
        Helper.swipScreen(driver);
        TimeUnit.SECONDS.sleep(2);
        activeContentEditBtn.click();
        System.out.println("进入编辑权限页面 clicked");
        TimeUnit.SECONDS.sleep(2);
        trxTranPower.click();
        System.out.println("点击添加trx转账 clicked");
        TimeUnit.SECONDS.sleep(2);
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        System.out.println("确认添加对应权限 clicked");
        TimeUnit.SECONDS.sleep(1);
        thresholdTextField.sendKeys("1");
        System.out.println("总threahold clicked");
        Helper.closeKeyBoard(driver);
        TimeUnit.SECONDS.sleep(1);
        driver.findElementsById("addressInputTF").get(0).sendKeys("TFrK5qvApM5h9HAubPRFeNN1pAGbk8tAup");
        System.out.println("输入地址 clicked");
        driver.findElementsById("addressThreadholdNumberTF").get(0).sendKeys("1");
        System.out.println("地址对应权重 clicked");
        Helper.closeKeyBoard(driver);
        TimeUnit.SECONDS.sleep(1);
        System.out.println("进入确定流程...");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        TimeUnit.SECONDS.sleep(1);
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        TimeUnit.SECONDS.sleep(10);

        return driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='完成'").isEnabled();
    }

}
