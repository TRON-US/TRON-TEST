package android.com.wallet.pages;

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


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_ok")
    public  WebElement confirmBan;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_cancle")
    public WebElement cancelBtn;

    @FindBy(id = "com.tronlinkpro.wallet:id/ll_common_left")
    public WebElement backBtn;

    @FindBy(id = "com.tronlinkpro.wallet:id/bt_send")
    public WebElement bt_send;

    @FindBy(id = "com.tronlinkpro.wallet:id/bt_go")
    public WebElement bt_go;

    @FindBy(id = "com.tronlinkpro.wallet:id/bt_send")
    public WebElement confirmBtn;

    @FindBy(id = "com.tronlinkpro.wallet:id/bt_go")
    public WebElement sendBtn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_common_title")
    public WebElement nav_title;

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
            System.out.println("FindById: "+name);
            return  true;
        }catch (org.openqa.selenium.NoSuchElementException ex){
            try {
                System.out.println("Not Fount by ID" + name);
                driver.findElementByAndroidUIAutomator("new UiSelector().text(\""+name+"\")");
                System.out.println("FindByText: "+name);
                return  true;
            }catch (org.openqa.selenium.NoSuchElementException eex){
                try {
                    System.out.println("Not Fount by Text" + name);
                    driver.findElementByName(name);
                    System.out.println("FindByName: "+name);
                    return  true;
                }catch (org.openqa.selenium.NoSuchElementException xxx) {
                    System.out.println("Not Fount by Name");
                    return false;
                }
            }
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
}
