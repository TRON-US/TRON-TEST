package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.concurrent.TimeUnit;

public class TransferPage extends AbstractPage {

    public IOSDriver<?> driver;


    public TransferPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(name = "转账")
    public WebElement title;

    @FindBy(name = "directionContent")
    public WebElement chain_text;

    @FindBy(name = "passwordErrorLabel")
    public WebElement passwordErrorLabel;

    @FindBy(name = "blueamountErrorLabel")
    public WebElement blueamountErrorLabel;

    @FindBy(className = "XCUIElementTypeTextField")
    public WebElement count_text;


    @FindBy(name = "amountDesContent")
    public WebElement availableBalance_text;


    @FindBy(className = "XCUIElementTypeSecureTextField")
    public WebElement password_input;

    @FindBy(id = "chargeText")
    public WebElement chargeText;


    @FindBy(id = "titleLabel")
    public WebElement titleLabel;


    @FindBy(id = "trxLabel")
    public WebElement trxLabel;

    @FindBy(id = "bandwidthLabel")
    public WebElement bandwidthLabel;

    @FindBy(id = "resourcesLabel")
    public WebElement bandwidthText;



    public WebElement get_inter_btn(){
        return  driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '转入'");
    }
    public WebElement get_out_btn(){
        return  driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '转出'");
    }
    public WebElement get_finish_btn(){
        return driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '完成'");
    }


    public void inputAndTapToTransfer() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        count_text.sendKeys("10");
        closeKeyBoard();
        TimeUnit.SECONDS.sleep(3);
        get_inter_btn().click();
        TimeUnit.SECONDS.sleep(8);

    }
    public void inputAndTapToTransferOut() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        count_text.sendKeys("10");
        closeKeyBoard();
        TimeUnit.SECONDS.sleep(3);
        get_out_btn().click();
        TimeUnit.SECONDS.sleep(8);

    }
    public String getvalueofBandwidthText() throws Exception{
        TimeUnit.SECONDS.sleep(2);
        WebElement element = driver.findElementById("resourcesLabel");
        String val = element.getText().split(" ")[0];

        return  val;
    }

    @FindBy(id = "chargeLabel")
    public WebElement chargeLabel;
    public String getFeeText(){
        return  chargeLabel.getText().split(" ")[0];
    }



    public TrxPage enterTrxPageWithTransferSuccess() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        count_text.sendKeys("10");
        closeKeyBoard();
        TimeUnit.SECONDS.sleep(3);
        get_inter_btn().click();
        waiteTime(8);
        confirm_btn().click();
        waiteTime(4);
        password_input.sendKeys("Test0001");
        closeKeyBoard();
        TimeUnit.SECONDS.sleep(3);
//        get_finish_btn().click();
        TimeUnit.SECONDS.sleep(8);
        return new TrxPage(driver);
    }

    public TrxPage enterTrxPageWithTransferSuccess(String count) throws Exception {
        TimeUnit.SECONDS.sleep(2);
        count_text.sendKeys(count);
        closeKeyBoard();
        TimeUnit.SECONDS.sleep(3);
        get_inter_btn().click();
        waiteTime(8);
        confirm_btn().click();
        waiteTime(4);
        password_input.sendKeys("Test0001");
        closeKeyBoard();
//        get_finish_btn().click();
        TimeUnit.SECONDS.sleep(8);
        return new TrxPage(driver);
    }


    public TrxPage enterTrxPageWithTransferOutSuccess() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        count_text.sendKeys("10");
        closeKeyBoard();
        TimeUnit.SECONDS.sleep(3);
        get_out_btn().click();
        waiteTime(8);
        confirm_btn().click();
        waiteTime(4);
        password_input.sendKeys("Test0001");
        closeKeyBoard();
        TimeUnit.SECONDS.sleep(3);
        get_finish_btn().click();
        TimeUnit.SECONDS.sleep(8);
        return new TrxPage(driver);
    }


    public TrxPage enterTrxPageWithTransferOutSuccess(String count) throws Exception {
        waiteTime();
        count_text.sendKeys(count);
        closeKeyBoard();
        waiteTime();
        get_out_btn().click();
        waiteTime();
        confirm_btn().click();
        waiteTime();
        password_input.sendKeys("Test0001");
        closeKeyBoard();
        waiteTime();
        get_finish_btn().click();
        waiteTime();
        return new TrxPage(driver);
    }






}
