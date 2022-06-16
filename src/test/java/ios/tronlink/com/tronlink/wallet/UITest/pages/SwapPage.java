package ios.tronlink.com.tronlink.wallet.UITest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.ios.IOSDriver;

public class SwapPage extends AbstractPage {
    public IOSDriver<?> driver;


    public SwapPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(name = "闪兑")
    public WebElement title;

    @FindBy(xpath = "//XCUIElementTypeApplication[@name=\"TronLink\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeTable/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeTextField")
    public WebElement inputField2;

    @FindBy(xpath = "//XCUIElementTypeApplication[@name=\"TronLink\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeTable/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextField")
    public WebElement inputField1;

    @FindBy(xpath = "(//XCUIElementTypeButton[@name=\"闪兑\"])[2]")
    public WebElement swapButton;

    @FindBy(id = "titleLabel")
    public WebElement titleLabel;


    @FindBy(xpath = "//XCUIElementTypeButton[@name=\"确认\"]")
    public WebElement xpathConfirm;


    public void inputPassword()throws Exception{
        xpathConfirm.click();
        TimeUnit.SECONDS.sleep(1);
        checkPasswotd_input.sendKeys("Test0001");
        xpathFinish.click();
        TimeUnit.SECONDS.sleep(4);

    }
}
