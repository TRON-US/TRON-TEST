package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
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

    @FindBy(id = "gotoDetailBtn")
    public WebElement gotoDetailBtn;



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


    @FindBy(id = "transferAddressErrorLabel")
    public WebElement transferErrorLabel;


    @FindBy(name = "com.tronlink.wallet:id/tv_balance")
    public WebElement balance_text;


    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='tronlink_token (1000042)']")
    public WebElement trc10_btn;




    public WebElement getTrc20Token() throws Exception{
        TimeUnit.SECONDS.sleep(3);
        List<WebElement> cells =  driver.findElementByClassName("XCUIElementTypeTable").findElements(By.className("XCUIElementTypeCell"));
        return  cells.get(1);
    }
    public WebElement getTrc10Token() throws Exception{
        TimeUnit.SECONDS.sleep(3);
        List<WebElement> cells =  driver.findElementByClassName("XCUIElementTypeTable").findElements(By.className("XCUIElementTypeCell"));
        return  cells.get(2);
    }

    public void swip(){
        Helper.swipScreen(driver);
    }

    public WebElement findsend_btn(){
        WebElement element = driver.findElementByIosNsPredicate("type == 'XCUIElementTypeButton' AND name == '发送'");
        return element;
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
        getTrc10Token().click();
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
        getTrc20Token().click();
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
        getTrc10Token().click();
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
        getTrc20Token().click();
        String balance = balance_text.getText();
        double trc10Amount = 0;
        Pattern pattern = Pattern.compile("\\d+\\.?\\d*");
        Matcher matcher = pattern.matcher(balance);
        if(matcher.find())
            trc10Amount = Double.valueOf(matcher.group(0));
        return trc10Amount;
    }


    public void sendKey(WebElement el,String value) throws Exception {
        TimeUnit.SECONDS.sleep(2);
        el.sendKeys(value);
        TimeUnit.SECONDS.sleep(2);
    }


    public void sendAllTrx(String value) throws Exception {
        TimeUnit.SECONDS.sleep(2);
        testfieldArray.get(1).sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        Helper.tapWhitePlace(driver);
        TimeUnit.SECONDS.sleep(2);
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
        TimeUnit.SECONDS.sleep(2);
        send_btn.click();
        TimeUnit.SECONDS.sleep(1);
    }

    public void sendAllTrc10(String value) throws Exception {
        TimeUnit.SECONDS.sleep(2);
        testfieldArray.get(1).sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        Helper.tapWhitePlace(driver);
        TimeUnit.SECONDS.sleep(2);
        token_btn.click();
        TimeUnit.SECONDS.sleep(2);
        getTrc10Token().click();
        TimeUnit.SECONDS.sleep(2);
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
        TimeUnit.SECONDS.sleep(2);
        send_btn.click();
    }

    public void sendAllTrc20(String value) throws Exception {
        TimeUnit.SECONDS.sleep(2);
        testfieldArray.get(1).sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        Helper.tapWhitePlace(driver);
        TimeUnit.SECONDS.sleep(2);
        token_btn.click();
        TimeUnit.SECONDS.sleep(2);
        getTrc20Token().click();
        TimeUnit.SECONDS.sleep(2);
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
        TimeUnit.SECONDS.sleep(2);
        send_btn.click();
    }



    public void enterSendTextField(String addr) throws Exception {
        TimeUnit.SECONDS.sleep(2);
        testfieldArray.get(0).clear();
        testfieldArray.get(0).clear();
        testfieldArray.get(0).sendKeys(addr);
        Helper.closeKeyBoard(driver);
        TimeUnit.SECONDS.sleep(4);
    }

    public void enterGetTextField(String addr) throws Exception {
        TimeUnit.SECONDS.sleep(2);
        testfieldArray.get(1).clear();
        testfieldArray.get(1).clear();
        testfieldArray.get(1).sendKeys(addr);
        Helper.closeKeyBoard(driver);
        TimeUnit.SECONDS.sleep(4);
    }
    public void enterAmountTextField(String amount) throws Exception {
        TimeUnit.SECONDS.sleep(2);
        testfieldArray.get(2).clear();
        testfieldArray.get(2).clear();
        testfieldArray.get(2).sendKeys(amount);
        Helper.closeKeyBoard(driver);
        TimeUnit.SECONDS.sleep(1);
    }
    public boolean overstepAuthority(String addr) throws Exception{
        enterSendTextField(addr);
        try{
            driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '确认'").getText();
            return true;
        }catch (Exception e){
            return  false;
        }
    }

    public boolean multiSignUIChanged(String addr) throws Exception{
        enterSendTextField(addr);

        try{
            log(driver.findElementById("权限").getText());
            return true;
        }catch (Exception e){
            return  false;
        }
    }
    public boolean multiSignOwnerSend(String addr) throws Exception{
        enterSendTextField(addr);
        enterGetTextField("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        enterAmountTextField("1.1");
        TimeUnit.SECONDS.sleep(2);
        send_btn.click();
        TimeUnit.SECONDS.sleep(4);
        InputPasswordConfim_btn.sendKeys("Test0001");
        TimeUnit.SECONDS.sleep(1);
        driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '完成'").click();
        TimeUnit.SECONDS.sleep(3);

        try{
            send_btn.getText();
            return false;
        }catch (Exception e){
            return  true;
        }
    }
    public boolean multiSignActiveSend(String addr) throws Exception{
        enterSendTextField(addr);
        enterGetTextField("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        enterAmountTextField("1.2");
        TimeUnit.SECONDS.sleep(1);
        Helper.swipScreen(driver);
        TimeUnit.SECONDS.sleep(3);
        driver.findElementsById("ic arrow drop").get(1).click();
        TimeUnit.SECONDS.sleep(2);
        driver.findElementById("active").click();
        TimeUnit.SECONDS.sleep(1);
        send_btn.click();
        TimeUnit.SECONDS.sleep(4);
        InputPasswordConfim_btn.sendKeys("Test0001");
        TimeUnit.SECONDS.sleep(1);
        driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '完成'").click();
        TimeUnit.SECONDS.sleep(3);
        try{
            send_btn.getText();
            return false;
        }catch (Exception e){
            return  true;
        }
    }


}
