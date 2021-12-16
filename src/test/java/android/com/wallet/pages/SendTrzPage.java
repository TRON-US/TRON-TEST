package android.com.wallet.pages;

import android.com.utils.Helper;
import io.appium.java_client.android.AndroidDriver;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * 转帐页
 */

public class SendTrzPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public SendTrzPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_common_title")
    public WebElement transferTtile_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/et_address")
    public WebElement receiveAddress_text;


    @FindBy(id = "com.tronlinkpro.wallet:id/et_count")
    public WebElement tranferAmount_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/send")
    public WebElement send_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/btn_asset_confirm")
    public WebElement transferNow_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/et_new_password")
    public WebElement InputPasswordConfim_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/bt_send")
    public WebElement confirm_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_error")
    public WebElement formatErrorHits_text;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_note")
    public WebElement note_text;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_balance")
    public WebElement balance_text;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_max")
    public WebElement tvMax_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/rl_token")
    public WebElement token_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/ll_common_left")
    public WebElement back_bt;

    @FindBy(xpath = "//*[@text='(1000042)']")
    public WebElement trc10_btn;






    @FindBy(id = "com.tronlinkpro.wallet:id/tv_invalid_time")
    public WebElement invalidTime_input;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_address")
    public List<WebElement> signAddress_input;



    public void swip(){
        Helper.swipScreen(driver);
    }


    @FindBy(id = "com.tronlinkpro.wallet:id/rl_bottom_next")
    public WebElement next_btn;



    @FindBy(id = "com.tronlinkpro.wallet:id/tv_invalid_time")
    public WebElement enableTime_text;



    @FindBy(id = "com.tronlinkpro.wallet:id/tv_address")
    public List<WebElement> signAddress_text;




    @FindBy(id = "com.tronlinkpro.wallet:id/tv_selected_name")
    public WebElement selectSignName_text;



    @FindBy(id = "com.tronlinkpro.wallet:id/tv_name")
    public WebElement tokenName_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/rl_addressbook_receive")
    public WebElement addressBook_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_address_name")
    public WebElement addressName_display;

    //com.tronlinkpro.wallet:id/shield_balance
    @FindBy(id = "com.tronlinkpro.wallet:id/shield_balance")
    public WebElement shieldBalance_text;

    //com.tronlinkpro.wallet:id/single_limit
    @FindBy(id = "com.tronlinkpro.wallet:id/single_limit")
    public WebElement singleLimit_text;

    //com.tronlinkpro.wallet:id/rl_fee
    @FindBy(id = "com.tronlinkpro.wallet:id/rl_fee")
    public WebElement shieldFee_text;

    //com.tronlinkpro.wallet:id/toscan
    @FindBy(id = "com.tronlinkpro.wallet:id/toscan")
    public WebElement coldHadScan_next_btn;


    public SendTrxSuccessPage enterSendTrxSuccessPage(){
        confirm_btn.click();
        return new SendTrxSuccessPage(driver);
    }


    public String trxCount = "";

    public SendTrxSuccessPage normalSendTrx() throws Exception {
        receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        tranferAmount_text.sendKeys("1");
        swip();
        send_btn.click();
        transferNow_btn.click();
        InputPasswordConfim_btn.sendKeys("Test0001");
        confirm_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new SendTrxSuccessPage(driver);
    }
    public SendTrxSuccessPage sendTrz(String address,String sendAmount) throws Exception {
        receiveAddress_text.sendKeys(address);
        tranferAmount_text.sendKeys(sendAmount);
        swip();
        send_btn.click();
        transferNow_btn.click();
        InputPasswordConfim_btn.sendKeys("Test0001");
        confirm_btn.click();
        TimeUnit.SECONDS.sleep(10);
        return new SendTrxSuccessPage(driver);
    }


    public SendTrxSuccessPage normalSendTrc10(String number) throws Exception {
        receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        token_btn.click();
        TimeUnit.SECONDS.sleep(1);
        trc10_btn.click();
        tranferAmount_text.sendKeys(number);
        swip();
        send_btn.click();
        transferNow_btn.click();
        InputPasswordConfim_btn.sendKeys("Test0001");
        confirm_btn.click();
        TimeUnit.SECONDS.sleep(1);
        back_bt.click();
        return new SendTrxSuccessPage(driver);
    }

//    public SendTrxSuccessPage normalSendTrc20(String number) throws Exception {
//        receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
//        token_btn.click();
//        TimeUnit.SECONDS.sleep(1);
//        trc20_btn.click();
//        tranferAmount_text.sendKeys(number);
//        swip();
//        send_btn.click();
//        transferNow_btn.click();
//        InputPasswordConfim_btn.sendKeys("Test0001");
//        confirm_btn.click();
//        TimeUnit.SECONDS.sleep(1);
//        back_bt.click();
//        return new SendTrxSuccessPage(driver);
//    }

    public double getTrc10Amount() throws Exception {
        token_btn.click();
        TimeUnit.SECONDS.sleep(1);
        trc10_btn.click();
        String balance = balance_text.getText();
        double trc10Amount = 0;
        Pattern pattern = Pattern.compile("\\d+\\.?\\d*");
        Matcher matcher = pattern.matcher(balance);
        if(matcher.find())
            trc10Amount = Double.valueOf(matcher.group(0));
        return trc10Amount;
    }

//    public double getTrc20Amount() throws Exception {
//        token_btn.click();
//        TimeUnit.SECONDS.sleep(1);
//        trc20_btn.click();
//        String balance = balance_text.getText();
//        double trc10Amount = 0;
//        Pattern pattern = Pattern.compile("\\d+\\.?\\d*");
//        Matcher matcher = pattern.matcher(balance);
//        if(matcher.find())
//            trc10Amount = Double.valueOf(matcher.group(0));
//        return trc10Amount;
//    }


    public void sendKey(WebElement el,String value) throws Exception {
        el.sendKeys(value);
        TimeUnit.SECONDS.sleep(2);
    }


    public void sendAllTrx(String value) throws Exception {
        receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        //calculate trx
        switch(value){
            case "max":
//                String current = balance_text.getText();
//                int  index = current.lastIndexOf(" ");
//                current = current.substring(index + 1,current.length());
//                tranferAmount_text.sendKeys(current);
                tvMax_btn.click();
                break;
            case "mix":
                tranferAmount_text.sendKeys("0");
                break;
            case "tooMuch":
                tranferAmount_text.sendKeys("9999999999");
                break;
        }
        send_btn.click();
        TimeUnit.SECONDS.sleep(1);
    }

    public void sendAllTrc10(String value) throws Exception {
        receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        token_btn.click();
        TimeUnit.SECONDS.sleep(3);
        trc10_btn.click();
        //calculate trx
        switch(value){
            case "max":
//                String current = balance_text.getText();
//                int  index = current.lastIndexOf(" ");
//                current = current.substring(index + 1,current.length());
//                tranferAmount_text.sendKeys(current);
                tvMax_btn.click();
                break;
            case "mix":
                tranferAmount_text.sendKeys("0");
                break;
            case "tooMuch":
                tranferAmount_text.sendKeys("9999999999");
                break;
        }
        Helper.swipScreen(driver);
        send_btn.click();
        TimeUnit.SECONDS.sleep(1);
    }

//    public void sendAllTrc20(String value) throws Exception {
//        TimeUnit.SECONDS.sleep(2);
//        receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
//        token_btn.click();
//        TimeUnit.SECONDS.sleep(3);
//        trc20_btn.click();
//        //calculate trx
//        switch(value){
//            case "max":
////                String current = balance_text.getText();
////                int  index = current.lastIndexOf(" ");
////                current = current.substring(index + 1,current.length());
////                tranferAmount_text.sendKeys(current);
//                tvMax_btn.click();
//                break;
//            case "mix":
//                tranferAmount_text.sendKeys("0");
//                break;
//            case "tooMuch":
//                tranferAmount_text.sendKeys("9999999999");
//                break;
//        }
//        Helper.swipScreen(driver);
//        send_btn.click();
//        TimeUnit.SECONDS.sleep(1);
//    }



    public AssetPage sendRamonTrxSuccess(String revAddress) throws Exception {
        receiveAddress_text.sendKeys(revAddress);
        Random random = new Random();
        float count = random.nextFloat();
        DecimalFormat df = new DecimalFormat( "0.00" );
        String str = df.format(count);
        trxCount = str;
        tranferAmount_text.sendKeys(str);
        swip();
        send_btn.click();
        transferNow_btn.click();
        TimeUnit.SECONDS.sleep(2);
        next_btn.click();
        InputPasswordConfim_btn.sendKeys("Test0001");
        confirm_btn.click();
        TimeUnit.SECONDS.sleep(3);
        back_bt.click();
        TimeUnit.SECONDS.sleep(2);
        back_bt.click();
        return new AssetPage(driver);
    }


    public String getTrxCount() {
        return trxCount;
    }



}
