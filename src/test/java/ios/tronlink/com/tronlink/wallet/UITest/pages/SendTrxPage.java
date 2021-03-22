package ios.tronlink.com.tronlink.wallet.UITest.pages;

import android.com.utils.Configuration;
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

    static String unActiveAddress = Configuration.getByPath("testng.conf")
            .getString("unActiveAddressInNile.Address1");

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

    @FindBy(id = "vote getRight arrow")
    public WebElement token_btn;

    @FindBy(name = "全部")
    public WebElement tvMax_btn;

    @FindBy(name = "发送")
    public WebElement send_btn;

    @FindBy(name = "white back arrow")
    public WebElement back_bt;

    @FindBy(id = "gotoDetailBtn")
    public WebElement gotoDetailBtn;


    @FindBy(name = "确认")
    public WebElement transferNow_btn;



    @FindBy(className = "XCUIElementTypeSecureTextField")
    public WebElement InputPasswordConfim_btn;


    @FindBy(name = "com.tronlink.wallet:id/bt_send")
    public WebElement confirm_btn;


    @FindBy(name = "com.tronlink.wallet:id/tv_error")
    public WebElement formatErrorHits_text;


    @FindBy(id = "transferAddressErrorLabel")
    public WebElement transferErrorLabel;

    @FindBy(id = "amountErrorLabel")
    public WebElement amountErrorLabel;

    @FindBy(id = "reciptErrorLabel")
    public WebElement reciptErrorLabel;

    @FindBy(id = "shieldedCurrentBalance")
    public WebElement shieldedCurrentBalance;


    @FindBy(id = "currentTRXCountLabel")
    public WebElement balance_text;


    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='tronlink_token (1000042)']")
    public WebElement trc10_btn;

    @FindBy(id = "TRX (TCCcBZEdTHmS1NfFtCYfwpjBKeTv515n71)")
    public WebElement TRX20Token;

    @FindBy(id = "tronlink_token (1000002)")
    public WebElement TRX10Token;

    @FindBy(id = "trxLabel")
    public WebElement real_money;
    //
//    @FindBy(id = "chargeLabel")
    @FindBy(id = "shieldedFeeLabel")
    public WebElement fee_text;

    @FindBy(id = "resourcesLabel")
    public WebElement resourcesLabel;




//shieldedCurrentBalance  余额  shieldedLimitHelpBtn  限额按钮  shieldedFeeLabel 手续费   shieldedLimitLabel 单笔限额

    public WebElement getTrc20Token() throws Exception{
        waiteTime();
        try {
            System.out.println("--- find by mainChain ---");
            return driver.findElementById("(TCCcBZEdTHmS1NfFtCYfwpjBKeTv515n71)");

        } catch (Exception e) {
            System.out.println("no MainChain");

        }
        try {
            System.out.println("--- find by DappChain ---");
            return driver.findElementById("(TXkdXbzjoLpxGAD2strP1zwjJzR6osNfD7)");

        } catch (Exception e) {
            System.out.println("no DappChain");
            return driver.findElementById("(TXkdXbzjoLpxGAD2strP1zwjJzR6osNfD7)");

        }

    }

    public WebElement getTrc10Token() throws Exception{
        waiteTime();
        return driver.findElementById("(1000002)");
    }

    public void swip(){
        Helper.swipScreen(driver);
    }

    public WebElement findsend_btn(){
        WebElement element = driver.findElementByIosNsPredicate("type == 'XCUIElementTypeButton' AND name == '发送'");
        return element;
    }

    public void broadcastButtonClick(){
        waiteTime();
        WebElement element = driver.findElementByIosNsPredicate("type == 'XCUIElementTypeButton' AND name == '完成'");
        waiteTime();
        element.click();
        waiteTime();

    }




    public void sendKey(WebElement el,String value) throws Exception {
        waiteTime();
        el.sendKeys(value);
        waiteTime();
    }


    public void sendAllTrx(String value) throws Exception {
        waiteTime();
        testfieldArray.get(1).sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        Helper.tapWhitePlace(driver);
        waiteTime();
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
        waiteTime();
        send_btn.click();
        TimeUnit.SECONDS.sleep(3);
    }

    public void sendAllTrc10(String value) throws Exception {
        waiteTime();
        testfieldArray.get(1).sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        Helper.tapWhitePlace(driver);
        waiteTime();
        token_btn.click();
        waiteTime();
        clickOffsetElement(getTrc10Token());
        waiteTime();
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
        TimeUnit.SECONDS.sleep(3);
        send_btn.click();
        TimeUnit.SECONDS.sleep(3);

    }

    public void sendAllTrc20(String value) throws Exception {
        waiteTime();
        testfieldArray.get(1).sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        Helper.tapWhitePlace(driver);
        waiteTime();
        token_btn.click();
        waiteTime();
        clickOffsetElement(getTrc20Token());
        waiteTime();
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
        waiteTime();
        send_btn.click();
        TimeUnit.SECONDS.sleep(3);

    }



    public void enterSendTextField(String addr) throws Exception {
        waiteTime();
        testfieldArray.get(0).clear();
        testfieldArray.get(0).clear();
        testfieldArray.get(0).sendKeys(addr);
        Helper.closeKeyBoard(driver);
        TimeUnit.SECONDS.sleep(4);
    }

    public void enterGetTextField(String addr) throws Exception {
        waiteTime();
        testfieldArray.get(1).clear();
        testfieldArray.get(1).clear();
        testfieldArray.get(1).sendKeys(addr);
        Helper.closeKeyBoard(driver);
        TimeUnit.SECONDS.sleep(4);
    }
    public void enterAmountTextField(String amount) throws Exception {
        waiteTime();
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
        waiteTime();
        send_btn.click();
        TimeUnit.SECONDS.sleep(3);
        driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '立即转账'").click();
        TimeUnit.SECONDS.sleep(3);
        InputPasswordConfim_btn.sendKeys("Test0001");
        TimeUnit.SECONDS.sleep(3);
        driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '完成'").click();
        TimeUnit.SECONDS.sleep(10);

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
        waiteTime();
        Helper.swipScreen(driver);
        waiteTime();
        driver.findElementById("ic arrow drop").click();
        waiteTime();
        driver.findElementById("active").click();
        waiteTime();
        send_btn.click();
        TimeUnit.SECONDS.sleep(3);
        driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '立即转账'").click();
        TimeUnit.SECONDS.sleep(3);
        InputPasswordConfim_btn.sendKeys("Test0001");
        TimeUnit.SECONDS.sleep(4);
        driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '完成'").click();
        TimeUnit.SECONDS.sleep(7);
        try{
            send_btn.getText();
            return false;
        }catch (Exception e){
            return  true;
        }
    }

    public TrxPage sendTrxWithNumber(String number) throws Exception{

        waiteTime();
        testfieldArray.get(1).sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        waiteTime();
        testfieldArray.get(2).sendKeys(number);
        Helper.closeKeyBoard(driver);
        waiteTime();
        send_btn.click();
        waiteTime();
        transferNow_btn.click();
        waiteTime();
        InputPasswordConfim_btn.sendKeys("Test0001");
        waiteTime();
        broadcastButtonClick();
        TimeUnit.SECONDS.sleep(4);
        return new TrxPage(driver);
    }

    public TrxPage sendTrzWithNumber(String number,String Addr) throws Exception{

        waiteTime();
        testfieldArray.get(1).sendKeys(Addr);
        waiteTime();
        log("send Number IS: " + number + "  To: " + Addr);
        testfieldArray.get(2).sendKeys(number);
        waiteTime();
        TimeUnit.SECONDS.sleep(1);
        Helper.tapWhitePlace(driver);
        send_btn.click();
        waiteTime(20);
        transferNow_btn.click();
        waiteTime();
        InputPasswordConfim_btn.sendKeys("Test0001");
        broadcastButtonClick();
        TimeUnit.SECONDS.sleep(15);
        return new TrxPage(driver);
    }
    public QRCodePage sendTrzWatchWithNumber(String number,String Addr) throws Exception{

        waiteTime();
        testfieldArray.get(1).sendKeys(Addr);
        waiteTime();
        log("send Number IS: " + number + "  To: " + Addr);
        testfieldArray.get(2).sendKeys(number);
        Helper.closeKeyBoard(driver);
        waiteTime();
        send_btn.click();
        waiteTime(20);

        return new QRCodePage(driver);
    }

    public TrxPage sendTrx10WithNumber(String number) throws Exception{
        waiteTime();
        testfieldArray.get(1).sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        Helper.closeKeyBoard(driver);
        waiteTime();
        token_btn.click();
        waiteTime();
        clickOffsetElement(getTrc10Token());
        waiteTime();
        testfieldArray.get(2).sendKeys(number);
        TimeUnit.SECONDS.sleep(1);
        waiteTime();
        Helper.tapWhitePlace(driver);
        send_btn.click();
        waiteTime();
        transferNow_btn.click();
        waiteTime();
        InputPasswordConfim_btn.sendKeys("Test0001");
        waiteTime();
        broadcastButtonClick();
        TimeUnit.SECONDS.sleep(4);
        return new TrxPage(driver);
    }

    public TrxPage sendTrx20WithNumber(String number) throws Exception{
        waiteTime();
        testfieldArray.get(1).sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        Helper.closeKeyBoard(driver);
        waiteTime();
        token_btn.click();
        clickOffsetElement(getTrc20Token());
        System.out.println("find token an clicked");
        TimeUnit.SECONDS.sleep(10);
        System.out.println("testfieldArray Size: " + testfieldArray.size());
        testfieldArray.get(2).sendKeys(number);
        TimeUnit.SECONDS.sleep(1);
        waiteTime();
        Helper.tapWhitePlace(driver);
        send_btn.click();
        waiteTime();
        transferNow_btn.click();
        waiteTime();
        InputPasswordConfim_btn.sendKeys("Test0001");
        waiteTime();
        broadcastButtonClick();
        TimeUnit.SECONDS.sleep(6);

        return new TrxPage(driver);
    }

    public void selectTokenType(String value) throws Exception {
        waiteTime();
        switch (value) {
            case "20":
                token_btn.click();
                clickOffsetElement(getTrc20Token());
                break;
            case "10":
                token_btn.click();
                waiteTime();
                clickOffsetElement(getTrc10Token());
                break;
        }
    }

    /**
     * @param cointype 10 20 noun
     * @return
     * @throws Exception
     */
    public String sendMaxCoinWithType(String... cointype) throws Exception {
        TimeUnit.SECONDS.sleep(2);
        testfieldArray.get(1).sendKeys(unActiveAddress);
        Helper.tapWhitePlace(driver);
        if (cointype.length != 0) {
            selectTokenType(cointype[0]);
        }
        waiteTime();
        tvMax_btn.click();
        waiteTime();
        log("clicked");
        Helper.swipScreen(driver);
        String allNumberText = balance_text.getText();
        System.out.println("allNumberText" + allNumberText);
        send_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return allNumberText;
    }

    public boolean sendImmediatelyEnable(){
       return driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '确认'").isEnabled();
    }

}
