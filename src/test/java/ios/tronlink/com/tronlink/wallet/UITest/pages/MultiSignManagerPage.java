package ios.tronlink.com.tronlink.wallet.UITest.pages;


import io.appium.java_client.ios.IOSDriver;
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


    @FindBy(id = "MutiSignActive")
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
    public String ThrHoldErrWith(String amount) throws Exception {
        TimeUnit.SECONDS.sleep(1);
        addauthorBtn.click();
        System.out.println("添加权限 clicked");
        TimeUnit.SECONDS.sleep(1);
        Helper.swipScreen(driver);
        thresholdTextField.sendKeys(amount);
        Helper.tapWhitePlace(driver);
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
        Helper.tapWhitePlace(driver);
        TimeUnit.SECONDS.sleep(2);

        return getAllerrors();

    }

    public void delSignData() throws Exception {
        if (havedaddActive()) {
            System.out.println("need to del...");
            TimeUnit.SECONDS.sleep(1);
            havedActive.click();
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

    public boolean havedaddActive() {
        try {
            if (havedActive.isDisplayed()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean havedaddfreezeAssetPower() {
        try {
            TimeUnit.SECONDS.sleep(1);
            havedActive.click();
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
        Helper.tapWhitePlace(driver);
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
        Helper.tapWhitePlace(driver);
        TimeUnit.SECONDS.sleep(1);
        driver.findElementsById("addressInputTF").get(0).sendKeys("TFrK5qvApM5h9HAubPRFeNN1pAGbk8tAup");
        System.out.println("输入地址 clicked");
        driver.findElementsById("addressThreadholdNumberTF").get(0).sendKeys("1");
        System.out.println("地址对应阈值 clicked");
        Helper.tapWhitePlace(driver);
        TimeUnit.SECONDS.sleep(1);
        System.out.println("进入确定流程...");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        TimeUnit.SECONDS.sleep(1);
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确认'").click();
        TimeUnit.SECONDS.sleep(6);
        driver.findElementByClassName("XCUIElementTypeSecureTextField").sendKeys("Test0001");
        TimeUnit.SECONDS.sleep(2);
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='完成'").click();
        TimeUnit.SECONDS.sleep(4);
    }

    public void modifyPermission() throws Exception {
        System.out.println("into modifyPermission");
        TimeUnit.SECONDS.sleep(1);
        havedActive.click();
        System.out.println("添加权限 clicked");
        TimeUnit.SECONDS.sleep(1);
        Helper.tapWhitePlace(driver);
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
        TimeUnit.SECONDS.sleep(6);
        driver.findElementByClassName("XCUIElementTypeSecureTextField").sendKeys("Test0001");
        TimeUnit.SECONDS.sleep(2);
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='完成'").click();
        TimeUnit.SECONDS.sleep(4);
    }
}
