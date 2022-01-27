package android.com.utils;

import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.NodeSetPage;
import android.com.wallet.pages.SettingPage;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Helper {



    public static void tapScreen(AndroidDriver<?> driver){
        AndroidTouchAction action = new AndroidTouchAction(driver);
        PointOption whiteplace = PointOption.point(8,166);
        action.tap(whiteplace).release().perform();
    }

    public static void swipScreen(AndroidDriver<?> driver){
        AndroidTouchAction action = new AndroidTouchAction(driver);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
//        System.out.println("4/5上拉到1/5   " + width + "   " + height);
        Duration duration = Duration.ofMillis(200);
        action.press(
                PointOption.point(width/3, height*2/3))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(width/3, height/5))
                .release().perform();
    }

    public static void swipScreenLitte(AndroidDriver<?> driver){
        AndroidTouchAction action = new AndroidTouchAction(driver);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
//        System.out.println("4/5上拉到1/5   " + width + "   " + height);
        Duration duration = Duration.ofMillis(200);
        action.press(
                PointOption.point(width/2, height/2))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(width/2, height/3))
                .release().perform();
    }

    public static void swipToChangeMyPaurse(AndroidDriver<?> driver){
        AndroidTouchAction action = new AndroidTouchAction(driver);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
//        System.out.println("2/5上拉到1/5   " + width + "   " + height);
        Duration duration = Duration.ofMillis(200);
        action.press(
                PointOption.point(width/2, height*2/5))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(width/2, height*1/5))
                .release().perform();
    }


    public static void swipScreenToTop(AndroidDriver<?> driver){
        AndroidTouchAction action = new AndroidTouchAction(driver);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
//        System.out.print("1/5下拉到4/5   " + width + "   " + height);
        Duration duration = Duration.ofMillis(200);
        action.press(
                PointOption.point(width/2, height/5))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(width/2, height*4/5))
                .release().perform();
    }

    public static void swipScreenFromTopToBottom(AndroidDriver<?> driver){
        AndroidTouchAction action = new AndroidTouchAction(driver);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
//        System.out.print("4/5上拉到1/5    " + width + "   " + height);
        Duration duration = Duration.ofMillis(200);
        action.press(
            PointOption.point(width/2, height*4/5))
            .waitAction(WaitOptions.waitOptions(duration))
            .moveTo(PointOption.point(width/2, height/5))
            .release().perform();
    }

    public static void swipeDownScreen(AndroidDriver<?> driver){
        AndroidTouchAction action = new AndroidTouchAction(driver);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
//        System.out.print("2/5下拉到4/5   " + width + "   " + height);
        Duration duration = Duration.ofMillis(200);
        action.press(
            PointOption.point(width/2, height*2/5))
            .waitAction(WaitOptions.waitOptions(duration))
            .moveTo(PointOption.point(width/2, height*4/5+50))
            .release().perform();
    }



    public static void swipeLeftScreen(AndroidDriver<?> driver){
        AndroidTouchAction action = new AndroidTouchAction(driver);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
//        System.out.println("swip the screen left");
//        System.out.print("   " + width + "   " + height);
        Duration duration = Duration.ofMillis(200);
        action.press(
                PointOption.point(width*4/5, height/2))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(width*1/5, height/2))
                .release().perform();
    }



    // swip the screen until element is display
    public static void scrollToElementUntilVisible(AndroidDriver<?> driver, WebElement we){
        for (int i=0;i<5;i++) {
            try {
                TimeUnit.SECONDS.sleep(2);
                we.isDisplayed();
                break;
            }catch (Exception e){
                AndroidTouchAction action = new AndroidTouchAction(driver);
                int width = driver.manage().window().getSize().width;
                int height = driver.manage().window().getSize().height;
                Duration duration = Duration.ofMillis(200);
                action.press(
                        PointOption.point(width/2, height*1/3))
                        .waitAction(WaitOptions.waitOptions(duration))
                        .moveTo(PointOption.point(width/2, height/5))
                        .release().perform();
                System.out.println("swip the screen...");
            }

        }


    }


    public AndroidDriver DRIVER = null;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_walletname")
    public WebElement walletNameSwitch_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_offline_sign_desc")
    public WebElement coldWalletScan_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/assets_name")
    public List<WebElement> asset_list;


    public void getSign(String testPrivateKey,AndroidDriver driver){
        this.DRIVER = driver;
        waiteTime();
        try {
            walletNameSwitch_btn.isDisplayed();
        }catch (Exception e){
            getSignOperate(testPrivateKey);
        }
    }

    public void getShieldSign(String testPrivateKey,AndroidDriver driver){
        this.DRIVER = driver;
        try {
            walletNameSwitch_btn.isDisplayed();
        }catch (Exception e){
            getShieldSignOperate(testPrivateKey);
        }
    }


    public void getCreateWalletSign(Boolean isNormal, String walletName,
        String password,AndroidDriver driver){
        this.DRIVER = driver;
        getCreateWallet(isNormal,walletName,password);
    }

    public void getWatchWalletSign(String address,AndroidDriver driver){
        this.DRIVER = driver;
        try {
            walletNameSwitch_btn.isDisplayed();
        }catch (Exception e){
            getWatchWalletSignOperate(address);
        }
    }

    public void getShieldWatchWalletSign(String udid,String nsk,String ak,String ovk,String shieldAddress,AndroidDriver driver){
        this.DRIVER = driver;
        try {
            walletNameSwitch_btn.isDisplayed();
        }catch (Exception e){
            getShieldWatchWalletSignOperate(udid,nsk,ak,ovk,shieldAddress);
        }
    }


    public void getColdWalletSign(String privateKey,AndroidDriver driver){
        this.DRIVER = driver;
        try {
            walletNameSwitch_btn.isDisplayed();
        }catch (Exception e){
            getColdWalletSignOperate(privateKey);
        }
    }

    public void getShieldColdWalletSign(String privateSk,AndroidDriver driver){
        this.DRIVER = driver;
        try {
            walletNameSwitch_btn.isDisplayed();
        }catch (Exception e){
            getShieldColdWalletSignOperate(privateSk);
        }
    }



    public void changeDappchain() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        NodeSetPage nodeSetPage = setting.enterNodeSetPage();
        nodeSetPage.enterSettingPageChoiseDappChain();
        DRIVER.closeApp();
        DRIVER.activateApp("com.tronlinkpro.wallet");
    }

    public void getCreateWallet(Boolean isNormal,String walletName,String password){
        try {
            findWebElement("com.tronlinkpro.wallet:id/tv_import").click();
            swipUntilElementEnable("com.tronlinkpro.wallet:id/bt_accept");
            findWebElement("com.tronlinkpro.wallet:id/bt_accept").click();
            if (isNormal) {
                findWebElement("com.tronlinkpro.wallet:id/create_option_desc").click();
            } else {
                findWebElement("com.tronlinkpro.wallet:id/create_option_desc_shield").click();
            }
            TimeUnit.SECONDS.sleep(1);
            //点击创建钱包
            findWebElement("com.tronlinkpro.wallet:id/cd_cw").click();
            findWebElement("com.tronlinkpro.wallet:id/et_name").sendKeys(walletName);
            findWebElement("com.tronlinkpro.wallet:id/creat").click();
            findWebElement("com.tronlinkpro.wallet:id/et_password").sendKeys(password);
            findWebElement("com.tronlinkpro.wallet:id/creat").click();
            //findWebElement("com.tronlinkpro.wallet:id/creat").click();
            findWebElement("com.tronlinkpro.wallet:id/et_password").sendKeys(password);
            findWebElement("com.tronlinkpro.wallet:id/creat").click();
            TimeUnit.SECONDS.sleep(8);
            findWebElement("com.tronlinkpro.wallet:id/iv_common_left").click();
            TimeUnit.SECONDS.sleep(3);
            //校验是否导入成功
            System.out.println("开始校验是否导入成功");
            findWebElement("com.tronlinkpro.wallet:id/assets_name");
            System.out.println("完成校验，导入成功");
        } catch (Exception e) {
            System.out.println(e);

        }
    }
    public void waiteTime(long time) {
        DRIVER.manage().timeouts().implicitlyWait(time,TimeUnit.SECONDS);
    }
    public void waiteTime() {
        waiteTime(15);
    }

    public void getSignOperate(String testPrivateKey){
        try {
            findWebElement("com.tronlinkpro.wallet:id/tv_import").click();
            swipUntilElementEnable("com.tronlinkpro.wallet:id/bt_accept");
            findWebElement("com.tronlinkpro.wallet:id/bt_accept").click();

            findWebElement("com.tronlinkpro.wallet:id/import_content").sendKeys(testPrivateKey);
            findWebElement("com.tronlinkpro.wallet:id/btn_next_step").click();
            swipScreenLitte(DRIVER);
            findWebElement("com.tronlinkpro.wallet:id/import_wallet_name").clear();
            findWebElement("com.tronlinkpro.wallet:id/import_wallet_name").sendKeys("Auto-test");

            findWebElement("com.tronlinkpro.wallet:id/import_wallet_password").sendKeys("Test0001");



            findWebElement("com.tronlinkpro.wallet:id/import_wallet_password_again").sendKeys("Test0001");
            findWebElement("com.tronlinkpro.wallet:id/btn_next_step").click();
            TimeUnit.SECONDS.sleep(6);
            //校验是否导入成功
            System.out.println("开始校验是否导入成功");
            findWebElement("com.tronlinkpro.wallet:id/tv_walletname");
            System.out.println("完成校验，导入成功");
        }catch (Exception e){
            System.out.println("\n-----------\n导入失败!!!!!\n");

            System.out.println(e);
        }

    }

    public void  getColdWalletSignOperate(String testPrivateKey){
        try {
            findWebElement("com.tronlinkpro.wallet:id/rl_switch").click();
            findWebElement("com.tronlinkpro.wallet:id/btn_confirm").click();

            findWebElement("com.tronlinkpro.wallet:id/tv_import").click();
            swipUntilElementEnable("com.tronlinkpro.wallet:id/bt_accept");
            findWebElement("com.tronlinkpro.wallet:id/bt_accept").click();

            findWebElement("com.tronlinkpro.wallet:id/import_content").sendKeys(testPrivateKey);
            findWebElement("com.tronlinkpro.wallet:id/btn_next_step").click();
            swipScreenLitte(DRIVER);
            findWebElement("com.tronlinkpro.wallet:id/import_wallet_name").clear();
            findWebElement("com.tronlinkpro.wallet:id/import_wallet_name").sendKeys("Cold-test");

            findWebElement("com.tronlinkpro.wallet:id/import_wallet_password").sendKeys("Test0001");

            findWebElement("com.tronlinkpro.wallet:id/import_wallet_password_again").sendKeys("Test0001");
            findWebElement("com.tronlinkpro.wallet:id/btn_next_step").click();
            TimeUnit.SECONDS.sleep(6);

            findWebElement("com.tronlinkpro.wallet:id/tv_nonet_desc");

        }catch (Exception e){
            System.out.println(e);
        }
    }



    public void getShieldSignOperate(String testPrivateKey){
        try {
            findWebElement("com.tronlinkpro.wallet:id/tv_import").click();
            swipUntilElementEnable("com.tronlinkpro.wallet:id/bt_accept");
            findWebElement("com.tronlinkpro.wallet:id/bt_accept").click();
            try {
                //新增匿名账户页面
                // 普通账户id：com.tronlinkpro.wallet:id/create_option_desc
                //匿名账户id:com.tronlinkpro.wallet:id/create_option_desc_shield
                findWebElement("com.tronlinkpro.wallet:id/create_option_desc_shield").click();
            } catch (Exception e) {

            }
            findWebElement("com.tronlinkpro.wallet:id/cd_pk").click();
            findWebElement("com.tronlinkpro.wallet:id/et_content").sendKeys(testPrivateKey);
            findWebElement("com.tronlinkpro.wallet:id/bt_next").click();
            findWebElement("com.tronlinkpro.wallet:id/et_name").sendKeys("Auto-test");
            findWebElement("com.tronlinkpro.wallet:id/creat").click();
            findWebElement("com.tronlinkpro.wallet:id/et_password").sendKeys("Test0001");
            TimeUnit.SECONDS.sleep(1);
            findWebElement("com.tronlinkpro.wallet:id/creat").click();
            TimeUnit.SECONDS.sleep(1);
            findWebElement("com.tronlinkpro.wallet:id/et_password").sendKeys("Test0001");
            findWebElement("com.tronlinkpro.wallet:id/creat").click();
            TimeUnit.SECONDS.sleep(3);
            //校验是否导入成功
            System.out.println("开始校验是否导入成功");
            findWebElement("com.tronlinkpro.wallet:id/assets_name");
            System.out.println("完成校验，导入成功");
        }catch (Exception e){
            System.out.println(e);
        }

    }


    public void  getShieldWatchWalletSignOperate(String udid,String nsk,String ak,String ovk,String shieldAddress){
        try {
            findWebElement("com.tronlinkpro.wallet:id/tv_import").click();
            swipUntilElementEnable("com.tronlinkpro.wallet:id/bt_accept");
            findWebElement("com.tronlinkpro.wallet:id/bt_accept").click();
            try {
                findWebElement("com.tronlinkpro.wallet:id/create_option_desc_shield").click();
            } catch (Exception e) {

            }
            //点击观察钱包
            findWebElement("com.tronlinkpro.wallet:id/cd_ow").click();
            TimeUnit.SECONDS.sleep(1);
            DRIVER.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout/android.view.ViewGroup[1]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.EditText")).sendKeys(nsk);
            DRIVER.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout/android.view.ViewGroup[2]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.EditText")).sendKeys(ak);
            switch (udid) {
                //大屏幕手机单独处理
                case "d94d4ea":
                    DRIVER.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout/android.view.ViewGroup[3]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.EditText")).sendKeys(ovk);
                    TimeUnit.SECONDS.sleep(1);
                    swipScreenFromTopToBottom(DRIVER);
                    swipScreenFromTopToBottom(DRIVER);
                    TimeUnit.SECONDS.sleep(1);
                    DRIVER.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout/android.view.ViewGroup[4]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.EditText")).sendKeys(shieldAddress);
                    break;
                default:
                    TimeUnit.SECONDS.sleep(1);
                    swipScreenFromTopToBottom(DRIVER);
                    swipScreenFromTopToBottom(DRIVER);
                    TimeUnit.SECONDS.sleep(1);
                    DRIVER.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout/android.view.ViewGroup[2]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.EditText")).sendKeys(ovk);
                    DRIVER.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout/android.view.ViewGroup[3]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.EditText")).sendKeys(shieldAddress);
                    TimeUnit.SECONDS.sleep(1);
            }
            findWebElement("com.tronlinkpro.wallet:id/btn_next").click();
            findWebElement("com.tronlinkpro.wallet:id/et_name").sendKeys("Auto-shield-test");
            findWebElement("com.tronlinkpro.wallet:id/creat").click();
            TimeUnit.SECONDS.sleep(3);

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void tapBetweenTwoElement(WebElement a, WebElement b) throws Exception{
        AndroidTouchAction action = new AndroidTouchAction(DRIVER);
        int width = DRIVER.manage().window().getSize().width;
        action.press(PointOption.point(width/2,(a.getLocation().getY() + b.getLocation().getY())/2))
            .release().perform();
    }




    public void  getWatchWalletSignOperate(String address){
        try {
            findWebElement("com.tronlinkpro.wallet:id/tv_observation").click();
            swipUntilElementEnable("com.tronlinkpro.wallet:id/bt_accept");
            findWebElement("com.tronlinkpro.wallet:id/bt_accept").click();

            findWebElement("com.tronlinkpro.wallet:id/add_watch_address").sendKeys(address);

            findWebElement("com.tronlinkpro.wallet:id/add_watch_name").clear();
            findWebElement("com.tronlinkpro.wallet:id/add_watch_name").sendKeys("WatchWallet");
            findWebElement("com.tronlinkpro.wallet:id/add_watch_wallet").click();
            TimeUnit.SECONDS.sleep(1);

        }catch (Exception e){
            System.out.println(e);
        }
    }




    public void  getShieldColdWalletSignOperate(String testPrivateKey){
        try {
            findWebElement("com.tronlinkpro.wallet:id/tv_cold_wallet").click();
            findWebElement("com.tronlinkpro.wallet:id/tv_ok").click();
            swipUntilElementEnable("com.tronlinkpro.wallet:id/bt_accept");
            findWebElement("com.tronlinkpro.wallet:id/bt_accept").click();
            try {
                //新增匿名账户页面
                // 普通账户id：com.tronlinkpro.wallet:id/create_option_desc
                //匿名账户id:com.tronlinkpro.wallet:id/create_option_desc_shield
                findWebElement("com.tronlinkpro.wallet:id/create_option_desc_shield").click();
            } catch (Exception e) {
                System.out.println(e);

            }
            findWebElement("com.tronlinkpro.wallet:id/cd_pk").click();
            findWebElement("com.tronlinkpro.wallet:id/et_content").sendKeys(testPrivateKey);
            findWebElement("com.tronlinkpro.wallet:id/bt_next").click();
            findWebElement("com.tronlinkpro.wallet:id/et_name").sendKeys("Cold-test");
            findWebElement("com.tronlinkpro.wallet:id/creat").click();
            findWebElement("com.tronlinkpro.wallet:id/et_password").sendKeys("Test0001");
            findWebElement("com.tronlinkpro.wallet:id/creat").click();
            findWebElement("com.tronlinkpro.wallet:id/creat").click();
            findWebElement("com.tronlinkpro.wallet:id/et_password").sendKeys("Test0001");
            findWebElement("com.tronlinkpro.wallet:id/creat").click();
            TimeUnit.SECONDS.sleep(1);
            findWebElement("com.tronlinkpro.wallet:id/tv_offline_sign_desc");
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){
            System.out.println(e);
        }
    }






    public void swipUntilElementEnable(String id) throws Exception{
        TimeUnit.SECONDS.sleep(1);
        int width = DRIVER.manage().window().getSize().width;
        int height = DRIVER.manage().window().getSize().height;
        System.out.print("  " + width + "   " + height);
        AndroidTouchAction action = new AndroidTouchAction(DRIVER);
        Duration duration = Duration.ofMillis(300);

        while (!findWebElement(id).isEnabled()) {
            action.press(
                    PointOption.point(width/2, height*4/5))
                    .waitAction(WaitOptions.waitOptions(duration))
                    .moveTo(PointOption.point(width/2, height/5))
                    .release().perform();
        }
    }

    public void swipUntilElementDisplayed(String id) throws Exception{
        TimeUnit.SECONDS.sleep(1);
        //while (findWebElement(id).isEnabled() == false) {
        while (!findWebElement(id).isDisplayed()) {
            AndroidTouchAction action = new AndroidTouchAction(DRIVER);
            int width = DRIVER.manage().window().getSize().width;
            int height = DRIVER.manage().window().getSize().height;
            System.out.print("  " + width + "   " + height);
            Duration duration = Duration.ofMillis(200);
            action.press(
                PointOption.point(width/2, height*4/5))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(width/2, height/5))
                .release().perform();
        }
    }



    public void swipUntilElementEnable(String id,AndroidDriver<?> driver) throws Exception{
        this.DRIVER = driver;
        TimeUnit.SECONDS.sleep(1);
        //while (findWebElement(id).isEnabled() == false) {
        while (!findWebElement(id).isEnabled()) {
            AndroidTouchAction action = new AndroidTouchAction(DRIVER);
            int width = DRIVER.manage().window().getSize().width;
            int height = DRIVER.manage().window().getSize().height;
            Duration duration = Duration.ofMillis(200);
            action.press(
                    PointOption.point(width/2, height*4/5))
                    .waitAction(WaitOptions.waitOptions(duration))
                    .moveTo(PointOption.point(width/2, height/5))
                    .release().perform();
        }
    }



    public WebElement findWebElement(String element) throws Exception {
//        int tries = 0;
//        Boolean Element_is_exist = false;
        WebElement el = null;
//        while (!Element_is_exist && tries < 3) {
//            tries++;
//            try {
//                el = DRIVER.findElementById(element);
//                Element_is_exist = true;
//            }catch (NoSuchElementException e){
//                //Element_is_exist = false;
//                TimeUnit.SECONDS.sleep(2);
//            }
//        }
        el = DRIVER.findElementById(element);
        return el;
    }

    public static boolean isElementExist(AndroidDriver<?> driver, String text) {
        try {
            driver.findElementByAndroidUIAutomator("new UiSelector().text(\""+text+"\")");
            System.out.println("IsFindByText: "+text);
            return  true;
        }catch (org.openqa.selenium.NoSuchElementException ex){
            return  false;
        }
    }
}
