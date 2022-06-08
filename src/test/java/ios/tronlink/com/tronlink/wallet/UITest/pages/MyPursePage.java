package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyPursePage extends AssetPage {



    public IOSDriver<?> driver;


    public MyPursePage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    //expendBtn 展开或者关闭按钮

    //WalletManageMenuView  menu 的UICollectionView  里面的内容对应的id 为 ManageViewCell

    @FindBy(name = "多重签名交易")
    public WebElement multiSignBtn;

    public MultiSignRecodPage enterMultiSignRecordView() throws Exception {
        multiSignBtn.click();
        TimeUnit.SECONDS.sleep(3);
        return new MultiSignRecodPage(driver);
    }


    @FindBy(id = "carouselView")
    public  WebElement carouselView;

    @FindBy(name = "修改密码")
    public WebElement walletPassword_btn;

    @FindBy(name = "******")
    public WebElement walletPasswordSec_btn;

    @FindBy(id = "钱包管理")
    public WebElement title;

    @FindBy(name = "titleLabel")
    public List<WebElement> titleLabels;

    @FindBy(name = "备份Keystore")
    public WebElement backkeystore_btn;

    @FindBy(name = "删除钱包")
    public WebElement deletewallet_btn;

    @FindBy(id = "权限管理")
    public WebElement multiManageDoor;

    @FindBy(id = "ManageViewCell")
    public List<WebElement> ManageViewCells;

    @FindBy(name = "com.tronlink.wallet:id/tv_address")
    public WebElement address_text;

    @FindBy(name = "com.tronlink.wallet:id/tv_name")
    public WebElement walletname_text;

    @FindBy(name = "com.tronlink.wallet:id/tv_create")
    public WebElement newCreate_btn;

    @FindBy(name = "black path")
    public WebElement backbtn;


    @FindBy(id = "manage wallet")
    public  WebElement addwallet;

    @FindBy(className = "XCUIElementTypeSecureTextField")
    public WebElement password_et;

//    @FindBy(name = "type =='XCUIElementTypeButton' AND name == '确定'")
//    public WebElement confirm_btn;
//    @FindBy(partialLinkText = "type =='XCUIElementTypeButton' AND name == '取消'")
//    public WebElement cancal_btn;

    @FindBy(className = "XCUIElementTypeTextView")
    public WebElement keystore_text;

    @FindBy(name = "我已安全备份")
    public WebElement done_btn;




    public MultiSignManagerPage enterMultiSignManagerPage() {
        try {
            System.out.println("\nold 1 times finde multiManage");
            TimeUnit.SECONDS.sleep(2);
            multiManageDoor.click();

            System.out.println("is password view show: ??\n ");
            if (!isunEnterchangePassword()){
                System.out.println("showed,and closed ");
                driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='取消'").click();
                multiManageDoor.click();
            }else {
                System.out.println(" not show ");
            }

            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            try {
                System.out.println("\nold 2 times finde multiManage");
                Helper.refreshWalletScreen(driver);
                TimeUnit.SECONDS.sleep(1);
                multiManageDoor.click();
                System.out.println("is password view show: ??\n ");
                if (!isunEnterchangePassword()){
                    System.out.println("showed,and closed ");
                    driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='取消'").click();
                    multiManageDoor.click();
                }else {
                    System.out.println(" not show ");
                }
                TimeUnit.SECONDS.sleep(3);
            }catch (Exception ed){
                try {
                    System.out.println("\nold 3 times finde multiManage");
                    Helper.refreshWalletScreen(driver);
                    TimeUnit.SECONDS.sleep(1);
                    multiManageDoor.click();
                    System.out.println("is password show: ??\n ");
                    if (!isunEnterchangePassword()){
                        System.out.println("showed,and closed ");
                        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='取消'").click();
                        multiManageDoor.click();
                    }else {
                        System.out.println(" not show ");
                    }
                    TimeUnit.SECONDS.sleep(3);
                }catch (Exception edd){
                    try {
                        System.out.println("\nold 4 times finde multiManage");
                        Helper.refreshWalletScreen(driver);
                        TimeUnit.SECONDS.sleep(1);
                        multiManageDoor.click();
                        TimeUnit.SECONDS.sleep(3);
                    }catch (Exception eddd){
                        System.out.println(eddd);
                    }
                }
            }
        }
        return new MultiSignManagerPage(driver);
    }

    public MultiSignManagerPage enterMultiSignManagerPageNew() {
        try {
            System.out.println("\n1 times find multiManage");
            waiteTime();
            multiManageDoor.click();
            waiteTime();
            int i = 2;
            while (i<6){
                if (!isEnterMultiSingManagePage()){
                    if (isunEnterchangePassword()){
                        System.out.println("showed,and closed ");
                        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='取消'").click();
                    }else {
                        System.out.println("other view and back to manage");
                        backbtn.click();
                    }
                    System.out.println("\n after 1 find to try"+i+" times find multiManage");
                    Helper.refreshWalletScreen(driver);
                    waiteTime();
                    multiManageDoor.click();
                    waiteTime();
                }else {
                    break;
                }
                i++;
            }

        }catch (Exception e){
            if (!isEnterMultiSingManagePage()){
                if (isunEnterchangePassword()){
                    driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='取消'").click();
                }else {
                    backbtn.click();
                }
            }
        }
        return new MultiSignManagerPage(driver);
    }

    public boolean isEnterMultiSingManagePage(){
        try{
            System.out.println(" FIND:"+driver.findElementById("权限管理").getLocation());
            return  true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean isPaswordChangePage(){
        try{
            System.out.println(" FIND:"+driver.findElementById("oldPswField").getLocation());
            return  true;
        }catch (Exception e){
            return false;
        }
    }
    public AddwalletPage enterAddwalletPage() throws Exception {
        newCreate_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new AddwalletPage(driver);
    }

    public WalletPasswordPage enterwalletPasswordPage() throws Exception {
        walletPassword_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new WalletPasswordPage(driver);
    }

    public boolean isunEnterchangePassword(){
        try{
            driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确定'").getText();
            return  true;
        }catch (Exception e){
            return false;
        }
    }


    public String getBackupKeystore(String password) throws Exception{
        String keystore = "";
        backkeystore_btn.click();
        TimeUnit.SECONDS.sleep(1);
        password_et.sendKeys(password);
        WebElement confirm_btn =  driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '确定'");
        confirm_btn.click();
        TimeUnit.SECONDS.sleep(6);
        driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '显示Keystore'").click();
        TimeUnit.SECONDS.sleep(1);
        keystore = keystore_text.getText();
        log("keystore: " + keystore);
        done_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return keystore;
    }

//XCUIElementTypeButton
public String getBackupKeystoreInClipboard(String password){
    String keystore = "";
    try {
        Helper.swipScreen(driver);
        TimeUnit.SECONDS.sleep(2);
        backkeystore_btn.click();
        TimeUnit.SECONDS.sleep(2);
        password_et.sendKeys(password);
        TimeUnit.SECONDS.sleep(2);
        WebElement  confirm_btn;
        confirm_btn =  driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '确定'");
        confirm_btn.click();
        TimeUnit.SECONDS.sleep(2);
        WebElement clipboard = driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '复制'");
        clipboard.click();
        TimeUnit.SECONDS.sleep(2);
        keystore =  driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '已复制'").getText();
        done_btn.click();
    }catch (Exception e){
        System.out.println(e);
    }
    return keystore;
}

    //XCUIElementTypeButton
    public boolean deletableCancel(String password){
        WebElement  confirm_btn;
        try {
            Helper.swipScreen(driver);
            TimeUnit.SECONDS.sleep(1);
            deletewallet_btn.click();
            TimeUnit.SECONDS.sleep(2);
            password_et.sendKeys(password);
            TimeUnit.SECONDS.sleep(2);
            confirm_btn =  driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '取消'");
            confirm_btn.click();
            return  !confirm_btn.isDisplayed();

        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

//    public boolean deletWallet (String password) throws Exception{
//        WebElement  confirm_btn;
//            Helper.swipScreen(driver);
//            TimeUnit.SECONDS.sleep(5);
//
//            findWebElement("删除钱包").click();
//
//            System.out.println("deletewallet_btn");
//            TimeUnit.SECONDS.sleep(2);
//            password_et.sendKeys(password);
//            TimeUnit.SECONDS.sleep(2);
//            confirm_btn =  driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '确定'");
//            confirm_btn.click();
//            TimeUnit.SECONDS.sleep(2);
//            System.out.println("isDisplayed");
//
//            return  !confirm_btn.isDisplayed();
//
//    }

    public void deleteWallet(String password) throws Exception{
        Helper.swipScreen(driver);
        driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '删除钱包'").click();
        TimeUnit.SECONDS.sleep(2);
        password_et.sendKeys(password);
        TimeUnit.SECONDS.sleep(1);
        driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '确认'").click();
        TimeUnit.SECONDS.sleep(3);
    }

    @FindBy(id = "oldPswField")
    public WebElement oldPswField;

    @FindBy(id = "confimPswField")
    public WebElement confimPswField;

    @FindBy(id = "newPswField")
    public WebElement newPswField;

    public void modifyPassword(String password) throws Exception{
        Helper.swipScreen(driver);
        driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '修改密码'").click();
        TimeUnit.SECONDS.sleep(2);
        oldPswField.sendKeys("Qqqqqqq1");
        closeKeyBoard();
        newPswField.sendKeys(password);
        closeKeyBoard();
        confimPswField.sendKeys(password);
        closeKeyBoard();
        TimeUnit.SECONDS.sleep(2);
        driver.findElementByIosClassChain("**/XCUIElementTypeButton[`label == \"完成\"`]").click();
        TimeUnit.SECONDS.sleep(7);
    }

    @FindBy(id = "accountNameField")
    public WebElement accountNameField;

    public void modifyName(String name) throws Exception{
        Helper.swipScreen(driver);
        driver.findElementByIosClassChain("**/XCUIElementTypeButton[`label == \"修改钱包名\"`]").click();
        TimeUnit.SECONDS.sleep(2);
        accountNameField.sendKeys(name);
        closeKeyBoard();
        driver.findElementByIosClassChain("**/XCUIElementTypeButton[`label == \"完成\"`]").click();
    }

    public boolean deleteObserveWallet(){
        WebElement  confirm_btn;
        try {
            Helper.swipScreen(driver);
            TimeUnit.SECONDS.sleep(1);
            deletewallet_btn.click();
            TimeUnit.SECONDS.sleep(1);
            confirm_btn =  driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '确认'");
            confirm_btn.click();
            return  !confirm_btn.isDisplayed();

        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
    public String getAddress(){
        String address = "";
        try {
            address = address_text.getText();
        }catch (Exception e){
            System.out.println(e);
        }
        return address;
    }

//    public AssetPage enterAssetPage(){
//        asset_btn.click();
//        return new AssetPage(driver);
//    }

//    public AssetPage backtoAssetPage() throws Exception{
//        backbtn.click();
//        TimeUnit.SECONDS.sleep(2);
//        return new AssetPage(driver);
//    }
    public void swipWalletTochangeNext() throws Exception {
        WebElement wl = carouselView; //driver.findElementByXPath("//XCUIElementTypeApplication[@name=\"TronLink\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]");
        List<WebElement> lwl = wl.findElements(By.className("XCUIElementTypeStaticText"));
        Helper.contentTexts(lwl,"Signed");
        int topY = wl.getLocation().y + 10;
        int botY = wl.getLocation().y + wl.getSize().height - 50;
        System.out.println("\n topY: " + topY + " botY: " + botY );
        IOSTouchAction action = new IOSTouchAction(driver);
        Duration duration = Duration.ofMillis(200);
        System.out.println("start");
        action.press(
                PointOption.point(120, botY))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(120, topY))
                .release().perform();
        System.out.println("end");
        TimeUnit.SECONDS.sleep(2);
        action.tap(PointOption.point(120,botY)).perform();
        TimeUnit.SECONDS.sleep(1);
        carouselView.click();
        TimeUnit.SECONDS.sleep(1);

    }

    public void swipWalletTochangeBefore() throws Exception {
        WebElement wl = driver.findElementByXPath("//XCUIElementTypeApplication[@name=\"TronLink\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]");
        int topY = wl.getLocation().y + 10;
        int botY = wl.getLocation().y + wl.getSize().height - 50;
        IOSTouchAction action = new IOSTouchAction(driver);
        Duration duration = Duration.ofMillis(200);
        System.out.println("start");
        action.press(
                PointOption.point(120, topY))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(120, botY))
                .release().perform();
        System.out.println("end");
        TimeUnit.SECONDS.sleep(2);
        action.tap(PointOption.point(120,botY)).perform();
        TimeUnit.SECONDS.sleep(2);
    }


    public WebElement findWebElement(String element) throws Exception {
        int tries = 0;
        Boolean Element_is_exist = false;
        WebElement el = null;
        while (!Element_is_exist && tries < 5) {
            System.out.println("findWElementTimes:" + tries);
            tries++;
            try {
                el = driver.findElementById(element);
                Element_is_exist = true;
            }catch (NoSuchElementException e){
                //Element_is_exist = false;
                TimeUnit.SECONDS.sleep(2);
            }
        }
        if(el != null){
            return  el;
        }else {
            el = driver.findElementByName(element);
            return el;
        }

    }
}
