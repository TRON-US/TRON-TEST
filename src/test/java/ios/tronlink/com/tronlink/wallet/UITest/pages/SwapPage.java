package ios.tronlink.com.tronlink.wallet.UITest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
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

    @FindBy(name = "按默认")
    public WebElement defaultButton;

    @FindBy(name = "按 APY")
    public WebElement apyButton;

    public void switchToAPY() throws Exception{
        defaultButton.click();
        TimeUnit.SECONDS.sleep(1);
        apyButton.click();
    }

    @FindBy(name = "待领取收益")
    public WebElement clmButton;

    public void enterClmPage() throws Exception{
        TimeUnit.SECONDS.sleep(1);
        clmButton.click();
        TimeUnit.SECONDS.sleep(1);
        try {
            if (isElementExist("选择钱包")) {
                driver.findElementByName("Auto_test").click();
            }
        }catch (Exception e){
            log("4.12.0");
        }
        unTillSomeThing("TRON DAO");
        TimeUnit.SECONDS.sleep(2);
    }

    public void enterMyFinancial() throws Exception{
        TimeUnit.SECONDS.sleep(1);
        driver.findElementByName("理财资产").click();
        unTillSomeThing("TRON DAO");
    }

    public void showByTypeItem() throws Exception{
        TimeUnit.SECONDS.sleep(1);
        driver.findElementByName("按项目").click();
        unTillSomeThing("TRON DAO");
    }

    public void enterSwitchPage() throws Exception{
        TimeUnit.SECONDS.sleep(1);
        driver.findElementByName("Auto_test").click();
        unTillSomeThing("选择后可查看全部钱包汇总数据");
    }
    @FindBy(name = "TRX")
    public WebElement trxButton;

    @FindBy(name = "TRX")
    public List<WebElement> trxButtons;

    public float getTrxButtonY(){
        return trxButton.getLocation().getY();
    }
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
