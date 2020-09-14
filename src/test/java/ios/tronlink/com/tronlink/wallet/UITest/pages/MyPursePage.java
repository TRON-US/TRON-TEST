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


    @FindBy(id = "carouselView")
    public  WebElement carouselView;

    @FindBy(id = "钱包密码")
    public WebElement walletPassword_btn;

    @FindBy(name = "******")
    public WebElement walletPasswordSec_btn;

    @FindBy(id = "钱包管理")
    public WebElement title;

    @FindBy(name = "titleLabel")
    public List<WebElement> titleLabels;

    @FindBy(name = "备份 Keystore")
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

    @FindBy(name = "资源")
    public WebElement asset_btn;

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
                    System.out.println("is password view show: ??\n ");
                    if (!isunEnterchangePassword()){
                        System.out.println("showed,and closed ");
                        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='取消'").click();
                    }else {
                        System.out.println(" not show ");
                    }

                    System.out.println("\n after 1find to try"+i+" times find multiManage");
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
            try {
                System.out.println("\n after Exception 2 times find multiManage");
                Helper.refreshWalletScreen(driver);
                waiteTime();
                multiManageDoor.click();
                waiteTime();
                int i = 2;
                while (i<5){
                    if (!isEnterMultiSingManagePage()){
                        System.out.println("is password show: ??\n ");
                        if (!isunEnterchangePassword()){
                            System.out.println("showed,and closed ");
                            driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='取消'").click();
                        }else {
                            System.out.println(" not show ");
                        }

                        System.out.println("\nIN Exception "+i+" times find multiManage");
                        Helper.refreshWalletScreen(driver);
                        waiteTime();
                        multiManageDoor.click();
                        waiteTime();
                    }else {
                        break;
                    }
                    i++;
                }
            }catch (Exception ed){
                try {
                    System.out.println("\nafter Exception 3 times find multiManage");
                    Helper.refreshWalletScreen(driver);
                    waiteTime();
                    ManageViewCells.get(0).click();
                    waiteTime();
                    int i = 2;
                    while (i<5){
                        if (!isEnterMultiSingManagePage()){
                            System.out.println("\nIN 2Exception"+i+" times find multiManage");
                            Helper.refreshWalletScreen(driver);
                            waiteTime();
                            multiManageDoor.click();
                            waiteTime();
                        }else {
                            break;
                        }
                        i++;
                    }
                }catch (Exception edd){
                    try {
                        System.out.println("\n4 times find multiManage");
                        Helper.refreshWalletScreen(driver);
                        waiteTime();
                        multiManageDoor.click();
                        waiteTime();
                    }catch (Exception eddd){
                        System.out.println(eddd);
                    }
                }
            }
        }
        return new MultiSignManagerPage(driver);
    }

    public boolean isEnterMultiSingManagePage(){
        try{
            System.out.println(" FIND:"+driver.findElementById("multiSig instruction").getLocation());
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
        System.out.println("开始尝试进入修改页面：");
        Helper.refreshWalletScreen(driver);
        TimeUnit.SECONDS.sleep(5);
        try{
            walletPassword_btn.click();
            int i = 2;
            while (i<6){
                if (!isPaswordChangePage()){
                    System.out.println("is password view show: ??\n ");
                    if (!isunEnterchangePassword()){
                        System.out.println("showed,and closed ");
                        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='取消'").click();
                    }else {
                        System.out.println(" not show ");
                    }

                    System.out.println("\n after 1 find to try"+i+" times find walletPassword");
                    Helper.refreshWalletScreen(driver);
                    TimeUnit.SECONDS.sleep(2);
                    walletPassword_btn.click();
                    TimeUnit.SECONDS.sleep(1);
                }else {
                    break;
                }
                i++;
            }

        }catch (Exception e){
            try{
                walletPassword_btn.click();
                System.out.println("walletPasswordSec_btn  2 ");
            }catch (Exception el){
                Helper.refreshWalletScreen(driver);
                try{
                    walletPassword_btn.click();
                    System.out.println("walletPassword2_btn  3");
                }catch (Exception ee){
                    try{
                        walletPassword_btn.click();
                        System.out.println("walletPasswordSec2_btn  4");
                    }catch (Exception le){
                        Helper.refreshWalletScreen(driver);
                        TimeUnit.SECONDS.sleep(4);
                        try {
                            walletPassword_btn.click();
                            System.out.println("walletPassword3_btn  5");
                        }catch (Exception lls){
                            walletPassword_btn.click();
                            System.out.println("walletPasswordSec3_btn  6");
                        }
                    }

                }
            }
        }
        System.out.println("进入钱包修改页面 ");
        TimeUnit.SECONDS.sleep(1);
        return new WalletPasswordPage(driver);
    }

    public boolean isunEnterchangePassword(){
        try{
            driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton'  AND name ='确定'").getText();
            return  false;
        }catch (Exception e){
            return true;
        }
    }


    public String getBackupKeystore(String password){
        String keystore = "";
        try {
            Helper.swipScreen(driver);
            waiteTime();
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
            waiteTime();
            password_et.sendKeys(password);
            waiteTime();
            WebElement confirm_btn =  driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '确定'");
            confirm_btn.click();
            TimeUnit.SECONDS.sleep(2);
            driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '我知道了'").click();
            TimeUnit.SECONDS.sleep(2);
            waiteTime();
            keystore = keystore_text.getText();
            log("keystore: " + keystore);
            waiteTime();
            done_btn.click();
            TimeUnit.SECONDS.sleep(1);
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

    public void deletWallet (String password) throws Exception{
        WebElement  confirm_btn;
        Helper.swipScreen(driver);
        TimeUnit.SECONDS.sleep(5);

        findWebElement("删除钱包").click();

        System.out.println("deletewallet_btn");
        TimeUnit.SECONDS.sleep(2);
        password_et.sendKeys(password);
        TimeUnit.SECONDS.sleep(2);
        confirm_btn =  driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '确定'");
        confirm_btn.click();
        TimeUnit.SECONDS.sleep(2);

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
        TimeUnit.SECONDS.sleep(2);
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
