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


//    @FindBy(name = "钱包密码")
    @FindBy(id = "钱包密码")
    public WebElement walletPassword_btn;

    @FindBy(name = "******")
    public WebElement walletPasswordSec_btn;

    @FindBy(id = "钱包管理")
    public WebElement title;

    @FindBy(name = "titleLabel")
    public List<WebElement> titleLabels;

    @FindBy(name = "备份keystore")
    public WebElement backkeystore_btn;
    @FindBy(name = "删除钱包")
    public WebElement deletewallet_btn;


    @FindBy(name = "com.tronlink.wallet:id/tv_address")
    public WebElement address_text;

    @FindBy(name = "com.tronlink.wallet:id/tv_name")
    public WebElement walletname_text;

    @FindBy(name = "com.tronlink.wallet:id/tv_create")
    public WebElement newCreate_btn;

    @FindBy(name = "black path")
    public WebElement backbtn;

    @FindBy(name = "资源")
    public WebElement asset_btn;

    @FindBy(className = "XCUIElementTypeSecureTextField")
    public WebElement password_et;

//    @FindBy(name = "type =='XCUIElementTypeButton' AND name == '确定'")
//    public WebElement confirm_btn;
//    @FindBy(partialLinkText = "type =='XCUIElementTypeButton' AND name == '取消'")
//    public WebElement cancal_btn;

    @FindBy(className = "XCUIElementTypeTextView")
    public WebElement keystore_text;

    @FindBy(name = "备份完成")
    public WebElement done_btn;

    @FindBy(name = "com.tronlink.wallet:id/rl_sign_manager")
    public WebElement multSignManager_btn;

    public MultiSignManagerPage enterMultiSignManagerPage() {
        try {
            multSignManager_btn.click();
            TimeUnit.SECONDS.sleep(1);

        }catch (Exception e){
            System.out.println(e);
        }
        return new MultiSignManagerPage(driver);
    }


    public AddwalletPage enterAddwalletPage() throws Exception {
        newCreate_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new AddwalletPage(driver);
    }
    public WalletPasswordPage enterwalletPasswordPage() throws Exception {
        Helper.swipRefreshScreen(driver);
        TimeUnit.SECONDS.sleep(1);
        try{
            walletPassword_btn.click();
            System.out.println("walletPassword_btn");
        }catch (Exception e){
            try{
                walletPasswordSec_btn.click();
                System.out.println("walletPasswordSec_btn");
            }catch (Exception el){
                Helper.swipRefreshScreen(driver);
                try{
                    walletPassword_btn.click();
                    System.out.println("walletPassword2_btn");
                }catch (Exception ee){
                    try{
                        walletPasswordSec_btn.click();
                        System.out.println("walletPasswordSec2_btn");
                    }catch (Exception le){
                        Helper.swipRefreshScreen(driver);
                        TimeUnit.SECONDS.sleep(1);
                        try {
                            walletPassword_btn.click();
                            System.out.println("walletPassword3_btn");
                        }catch (Exception lls){
                            walletPasswordSec_btn.click();
                            System.out.println("walletPasswordSec3_btn");
                        }
                    }

                }
            }
        }

        TimeUnit.SECONDS.sleep(2);
        return new WalletPasswordPage(driver);
    }



    public String getBackupKeystore(String password){
        String keystore = "";
        try {
            Helper.swipScreen(driver);
            try{
                backkeystore_btn.click();
            }catch (Exception e){
                Helper.swipScreen(driver);
                try{
                    backkeystore_btn.click();
                }catch (Exception el){
                    Helper.swipRefreshScreen(driver);
                    backkeystore_btn.click();
                }

            }
            TimeUnit.SECONDS.sleep(1);
            password_et.sendKeys(password);
            WebElement  confirm_btn;
            confirm_btn =  driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '确定'");
            confirm_btn.click();
            TimeUnit.SECONDS.sleep(2);
            keystore = keystore_text.getText();
            done_btn.click();
        }catch (Exception e){
            System.out.println(e);
        }
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

    public boolean deletWallet (String password) throws Exception{
        WebElement  confirm_btn;
//        try {
            Helper.swipScreen(driver);
            TimeUnit.SECONDS.sleep(5);

            findWebElement("删除钱包").click();
//            deletewallet_btn.click();
            System.out.println("deletewallet_btn");
            TimeUnit.SECONDS.sleep(2);
            password_et.sendKeys(password);
            TimeUnit.SECONDS.sleep(2);
            confirm_btn =  driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '确定'");
            confirm_btn.click();
            TimeUnit.SECONDS.sleep(2);
            System.out.println("isDisplayed");

            return  !confirm_btn.isDisplayed();

//        }catch (Exception e){
//            System.out.println(e);
//            return false;
//        }
    }
    public boolean deleteObserveWallet(){
        WebElement  confirm_btn;
        try {
            Helper.swipScreen(driver);
            TimeUnit.SECONDS.sleep(1);
            deletewallet_btn.click();
            TimeUnit.SECONDS.sleep(1);
            confirm_btn =  driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '确定'");
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

    public AssetPage enterAssetPage(){
        asset_btn.click();
        return new AssetPage(driver);
    }

    public void swipWalletTochangeNext() throws Exception {
        WebElement wl = driver.findElementByXPath("//XCUIElementTypeApplication[@name=\"TronLink\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]");
        List<WebElement> lwl = wl.findElements(By.className("XCUIElementTypeStaticText"));
        Helper.contentTexts(lwl,"2222222");
        int topY = wl.getLocation().y + 10;
        int botY = wl.getLocation().y + wl.getSize().height - 50;
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
        TimeUnit.SECONDS.sleep(2);

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
