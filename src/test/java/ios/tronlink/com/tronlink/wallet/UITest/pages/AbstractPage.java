package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AbstractPage {

    @FindBy(className = "XCUIElementTypeStaticText")
    public List<WebElement> textArray;

    public IOSDriver<?> driver;
    private SimpleDateFormat timeStamp = new SimpleDateFormat("yyyy MM dd_ HH:mm:ss ");


    public AbstractPage(IOSDriver<?> driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    public WebElement WaitforElement(By element){
        PageFactory.initElements(driver, this);
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(element));
        return driver.findElement(element);

    }

    public void swipToLeave(){
        IOSTouchAction action = new IOSTouchAction(driver);
        Duration duration = Duration.ofMillis(200);
        action.press(
                PointOption.point(-10, 100))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(260, 100))
                .release().perform();
    }


    public void log(String log) {
        String time = timeStamp.format(new Date()).toString();
        System.out.println(time + ": " + log);
    }
    public  String intToString(int value)
    {
        Integer integer = new Integer(value);
        return integer.toString();
    }

    public void waiteTime(long time) {
        driver.manage().timeouts().implicitlyWait(time,TimeUnit.SECONDS);
    }
    public void waiteTime() {
        waiteTime(5);
    }
}
