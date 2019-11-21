package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 转帐页
 */

public class SendTrxPage extends AbstractPage {

    public IOSDriver<?> driver;

    public SendTrxPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(className = "XCUIElementTypeTextField")
    public List<WebElement> testfieldArray;

    @FindBy(className = "XCUIElementTypeStaticText")
    public List<WebElement> alltextArray;

    @FindBy(className = "XCUIElementTypeTextView")
    public WebElement textview;

    @FindBy(name = "ic arrow drop")
    public WebElement token_btn;

    @FindBy(name = "MAX")
    public WebElement tvMax_btn;

    @FindBy(name = "发送")
    public WebElement send_btn;

    @FindBy(name = "white back arrow")
    public WebElement back_bt;



    @FindBy(name = "com.tronlink.wallet:id/tv_common_title")
    public WebElement transferTtile_btn;

    @FindBy(name = "com.tronlink.wallet:id/et_address")
    public WebElement receiveAddress_text;


    @FindBy(name = "com.tronlink.wallet:id/et_count")
    public WebElement tranferCount_text;


    @FindBy(name = "立即转账")
    public WebElement transferNow_btn;



    @FindBy(className = "XCUIElementTypeSecureTextField")
    public WebElement InputPasswordConfim_btn;


    @FindBy(name = "com.tronlink.wallet:id/bt_send")
    public WebElement confirm_btn;


    @FindBy(name = "com.tronlink.wallet:id/tv_error")
    public WebElement formatErrorHits_text;


    @FindBy(name = "com.tronlink.wallet:id/tv_note")
    public WebElement note_text;


    @FindBy(name = "com.tronlink.wallet:id/tv_balance")
    public WebElement balance_text;


    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='tronlink_token (1000042)']")
    public WebElement trc10_btn;


    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='TRX (TXgxGokbKCeHrg6CbSgtJegZN9hEmVhwBP)']")
    public WebElement trc20_btn;



    public void swip(){
        Helper.swipScreen(driver);
    }

    public void broadcastButtonClick(){

        WebElement element = driver.findElementByIosNsPredicate("type == 'XCUIElementTypeButton' AND name == '完成'");
        element.click();

    }

    public SendTrxSuccessPage enterSendTrxSuccessPage(){
        confirm_btn.click();
        return new SendTrxSuccessPage(driver);
    }


    public SendTrxSuccessPage normalSendTrx() throws Exception {
        receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        tranferCount_text.sendKeys("1");
        swip();
        send_btn.click();
        transferNow_btn.click();
        InputPasswordConfim_btn.sendKeys("Test0001");
        confirm_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new SendTrxSuccessPage(driver);
    }

    public SendTrxSuccessPage normalSendTrc10(String number) throws Exception {
        receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        token_btn.click();
        trc10_btn.click();
        tranferCount_text.sendKeys(number);
        swip();
        send_btn.click();
        transferNow_btn.click();
        InputPasswordConfim_btn.sendKeys("Test0001");
        confirm_btn.click();
        TimeUnit.SECONDS.sleep(1);
        back_bt.click();
        return new SendTrxSuccessPage(driver);
    }

    public SendTrxSuccessPage normalSendTrc20(String number) throws Exception {
        receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        token_btn.click();
        trc20_btn.click();
        tranferCount_text.sendKeys(number);
        swip();
        send_btn.click();
        transferNow_btn.click();
        InputPasswordConfim_btn.sendKeys("Test0001");
        confirm_btn.click();
        TimeUnit.SECONDS.sleep(1);
        back_bt.click();
        return new SendTrxSuccessPage(driver);
    }

    public double getTrc10Amount() throws Exception {
        token_btn.click();
        trc10_btn.click();
        String balance = balance_text.getText();
        double trc10Amount = 0;
        Pattern pattern = Pattern.compile("\\d+\\.?\\d*");
        Matcher matcher = pattern.matcher(balance);
        if(matcher.find())
            trc10Amount = Double.valueOf(matcher.group(0));
        return trc10Amount;
    }

    public double getTrc20Amount() throws Exception {
        token_btn.click();
        trc20_btn.click();
        String balance = balance_text.getText();
        double trc10Amount = 0;
        Pattern pattern = Pattern.compile("\\d+\\.?\\d*");
        Matcher matcher = pattern.matcher(balance);
        if(matcher.find())
            trc10Amount = Double.valueOf(matcher.group(0));
        return trc10Amount;
    }


    public void sendKey(WebElement el,String value) throws Exception {
        el.sendKeys(value);
        TimeUnit.SECONDS.sleep(2);
    }


    public void sendAllTrx(String value) throws Exception {
        testfieldArray.get(1).sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        //calculate trx
        switch(value){
            case "max":
                tvMax_btn.click();
                break;
            case "mix":
                testfieldArray.get(2).sendKeys("0");
                break;
            case "tooMuch":
                testfieldArray.get(2).sendKeys("9999999999");
                break;
        }
        send_btn.click();
        TimeUnit.SECONDS.sleep(1);
    }

    public void sendAllTrc10(String value) throws Exception {
        testfieldArray.get(1).sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        Helper.tapWhitePlace(driver);
        token_btn.click();
        trc10_btn.click();
        //calculate trx
        switch(value){
            case "max":
                tvMax_btn.click();
                break;
            case "mix":
                testfieldArray.get(2).sendKeys("0");
                break;
            case "tooMuch":
                testfieldArray.get(2).sendKeys("9999999999");
                break;
        }
        Helper.tapWhitePlace(driver);
        send_btn.click();
    }

    public void sendAllTrc20(String value) throws Exception {
        testfieldArray.get(1).sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        Helper.tapWhitePlace(driver);
        token_btn.click();
        trc20_btn.click();
        //calculate trx
        switch(value){
            case "max":

                tvMax_btn.click();
                break;
            case "mix":
                testfieldArray.get(2).sendKeys("0");
                break;
            case "tooMuch":
                testfieldArray.get(2).sendKeys("9999999999");
                break;
        }
        Helper.tapWhitePlace(driver);
        send_btn.click();
    }





}
