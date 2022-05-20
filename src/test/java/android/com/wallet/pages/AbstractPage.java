package android.com.wallet.pages;

import android.com.utils.AppiumTestCase;
import android.com.utils.Helper;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;




public abstract class AbstractPage {

    public WebElement addressBookTab() {
        return findElementByText("地址本");
    }

    public  static String apkPackageName = "com.tronlinkpro.wallet";

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_content")
    public WebElement tv_content;

    @FindBy(id = "com.tronlinkpro.wallet:id/title")
    public WebElement title;

    @FindBy(id = "com.tronlinkpro.wallet:id/toscan")
    public WebElement toscan;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_ok")
    public  WebElement confirmBan;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_cancle")
    public WebElement cancelBtn;

    @FindBy(id = "com.tronlinkpro.wallet:id/ll_common_left")
    public WebElement backBtn;

    @FindBy(id = "com.tronlinkpro.wallet:id/bt_send")
    public WebElement bt_send;

    @FindBy(id = "com.tronlinkpro.wallet:id/btn_done")
    public WebElement btn_done;

    @FindBy(id = "com.tronlinkpro.wallet:id/bt_send")
    public WebElement confirmBtn;

@FindBy(id = "com.tronlinkpro.wallet:id/btn_confirm")
public WebElement btn_confirm;

@FindBy(id = "com.tronlinkpro.wallet:id/bt_go")
public WebElement bt_go;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_common_title")
    public WebElement nav_title;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_common_right2")
    public WebElement tv_common_right2;
    
    @FindBy(id = "com.tronlinkpro.wallet:id/tv_main_title")
    public WebElement tv_main_title;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_common_right")
    public WebElement right_title;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_hide_assets")
    public WebElement hidasset;

    @FindBy(id = "com.tronlinkpro.wallet:id/et_new_password")
    public WebElement et_new_password;

    @FindBy(id = "com.tronlinkpro.wallet:id/confirm")
    public WebElement confirm;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_title")
    public WebElement tv_title;

    public void inputPopViewPassword(String password){
        et_new_password.sendKeys(password);
        bt_send.click();
    }

    public AndroidDriver<?> driver;


    public AbstractPage(AndroidDriver<?> driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    public WebElement WaitforElement(By element){
        PageFactory.initElements(driver, this);
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(element));
        return driver.findElement(element);

    }
    public String prettyString(String arg){
        String value = arg;
        if (arg.contains(",")){
            value = arg.replace(",","");
        }
        return value;
    }

    public void waiteTime(long time) {
        this.driver.manage().timeouts().implicitlyWait(time,TimeUnit.SECONDS);
    }
    public void waiteTime() {
        waiteTime(15);
    }

    /**
     * is element is exist
     * @param name
     * @return boolean value
     */
    public boolean isElementExist(String name) {
        try {
            driver.findElementById(name);
            return  true;
        }catch (org.openqa.selenium.NoSuchElementException ex){
            try {
                System.out.println("Not Fount by ID: " + name);
                driver.findElementByAndroidUIAutomator("new UiSelector().text(\""+name+"\")");
                return  true;
            }catch (org.openqa.selenium.NoSuchElementException eex){
                try {
                    System.out.println("Not Fount by Text: " + name);
                    driver.findElementByName(name);
                    return  true;
                }catch (org.openqa.selenium.NoSuchElementException xxx) {
                    System.out.println("Not Fount by Name!");
                    return false;
                }
            }
        }
    }

    public boolean isTextExist(String text) {
        try {
            driver.findElementByAndroidUIAutomator("new UiSelector().text(\""+text+"\")");
            return  true;
        }catch (org.openqa.selenium.NoSuchElementException ex){
            System.out.println("Not Fount by Text" + text);
            return false;
        }
    }


    /**
     *
     * @param  text ,which you want to find
     * @return WebElement
     */
    public WebElement findElementByText(String text) {

       return driver.findElementByAndroidUIAutomator("new UiSelector().text(\""+text+"\")");

    }

    public WebElement confirm_btn() {

        return findElementByText("确认");

    }

    public WebElement finish_btn() {

        return findElementByText("完成");

    }

    public void keyboardSogou(String udid) throws Exception{
        AppiumTestCase.cmdReturn("adb -s " + udid + " shell settings put secure default_input_method com.sohu.inputmethod.sogou/.SogouIME");
    }

    public void keyboardUnicode(String udid) throws Exception{
        AppiumTestCase.cmdReturn("adb -s " + udid + " shell settings put secure default_input_method io.appium.settings/.UnicodeIME");
    }

    public void clickOffsetElement(WebElement wele){
        int x = wele.getLocation().getX();
        int y = wele.getLocation().getY();
        AndroidTouchAction action = new AndroidTouchAction(driver);
        action.press(PointOption.point(x+20,y+14)).release().perform();
    }
    public void clickElementCenter(WebElement wele){
        int x = wele.getLocation().getX();
        int y = wele.getLocation().getY();
        int width = wele.getSize().getWidth();
        int hight = wele.getSize().getHeight();
        AndroidTouchAction action = new AndroidTouchAction(driver);
        action.press(PointOption.point(x+width/2,y+hight/2)).release().perform();
    }

    public void printLocation(WebElement wl){
        int x = wl.getLocation().getX();
        int y = wl.getLocation().getY();
        int width = wl.getSize().getWidth();
        int hight = wl.getSize().getHeight();
        System.out.println("x: " + x + " y: " + y + " width: " + width + " hight: "+ hight);
    }

    public void swipUntilElementEnable(WebElement element) throws Exception{
        TimeUnit.SECONDS.sleep(1);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        System.out.print("  " + width + "   " + height);
        AndroidTouchAction action = new AndroidTouchAction(driver);
        Duration duration = Duration.ofMillis(300);

        while (! element.isEnabled()) {
            action.press(
                    PointOption.point(width/2, height*4/5))
                    .waitAction(WaitOptions.waitOptions(duration))
                    .moveTo(PointOption.point(width/2, height/5))
                    .release().perform();
        }
    }

    public  void swipScreenLitte(){
        AndroidTouchAction action = new AndroidTouchAction(driver);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        Duration duration = Duration.ofMillis(200);
        action.press(
                PointOption.point(width/2, height/2))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(width/2, height/3))
                .release().perform();
    }

    public void deleteWallet() throws Exception{
        driver.findElementById("com.tronlinkpro.wallet:id/tv_trx_value").click();
        TimeUnit.SECONDS.sleep(1);
        Helper.swipScreen(driver);
        driver.findElementById("com.tronlinkpro.wallet:id/delete").click();
        driver.findElementById("com.tronlinkpro.wallet:id/et_password").sendKeys("Test0001");
        driver.findElementById("com.tronlinkpro.wallet:id/tv_ok").click();
        TimeUnit.SECONDS.sleep(3);
    }

    public void deleteWalletNone() throws Exception{
        driver.findElementById("com.tronlinkpro.wallet:id/tv_trx_value").click();
        TimeUnit.SECONDS.sleep(1);
        Helper.swipScreen(driver);
        driver.findElementById("com.tronlinkpro.wallet:id/delete").click();
        driver.findElementById("com.tronlinkpro.wallet:id/tv_ok").click();
        TimeUnit.SECONDS.sleep(3);
    }
    public void deleteColdWallet() throws Exception{
        driver.findElementById("com.tronlinkpro.wallet:id/tv_title").click();
        TimeUnit.SECONDS.sleep(1);
        Helper.swipScreen(driver);
        driver.findElementById("com.tronlinkpro.wallet:id/delete").click();
        driver.findElementById("com.tronlinkpro.wallet:id/et_password").sendKeys("Test0001");
        driver.findElementById("com.tronlinkpro.wallet:id/tv_ok").click();
        TimeUnit.SECONDS.sleep(3);
    }

    public String longidWithID(String ids){
        return "com.tronlinkpro.wallet:id/" + ids ;
    }

    public WebElement findByShotId(String ids){
        return driver.findElementById(longidWithID(ids));
    }



}
