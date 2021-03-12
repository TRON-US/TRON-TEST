package android.com.wallet.pages;

import android.com.utils.Configuration;
import android.com.utils.Helper;
import io.appium.java_client.android.AndroidDriver;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;


/**
 * 转帐页
 */

public class SendTrxPage extends AbstractPage {

    static String unActiveAddress = Configuration.getByPath("testng.conf")
            .getString("unActiveAddressInNile.Address1");

    public AndroidDriver<?> driver;

    public SendTrxPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    //com.tronlinkpro.wallet:id/toscan
    @FindBy(id = "com.tronlinkpro.wallet:id/toscan")
    public WebElement coldHadScan_next_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_common_title")
    public WebElement transferTtile_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/iv_one_delete")
    public WebElement transferAddress_deleteBtn;

    @FindBy(id = "com.tronlinkpro.wallet:id/et_transfer_address")
    public WebElement transferAddress_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/et_address")
    public WebElement receiveAddress_text;


    @FindBy(id = "com.tronlinkpro.wallet:id/et_count")
    public WebElement tranferCount_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/send")
    public WebElement send_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/bt_go")
    public WebElement transferNow_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/et_new_password")
    public WebElement InputPasswordConfim_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/bt_send")
    public WebElement confirm_btn;
    @FindBy(id = "com.tronlinkpro.wallet:id/tv_from_address")
    public WebElement from_address;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_address")
    public WebElement to_address;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_error")
    public WebElement formatErrorHits_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_real_money")
    public WebElement real_money;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_fee")
    public WebElement fee_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_fee_amount_bw")
    public WebElement bandwidth_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_note")
    public WebElement note_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_no_bandwidth")
    public WebElement no_bandwidth;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_balance")
    public WebElement balance_text;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_max")
    public WebElement tvMax_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/rl_token")
    public WebElement token_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/ll_common_left")
    public WebElement back_bt;

    @FindBy(xpath = "//*[@text='(1000002)']")
    public WebElement trc10_btn;


//    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[3]")
//    public WebElement trc20_btn;

    @FindBy(xpath = "//*[@text='(TCCc...5n71)']")
    public WebElement trc20_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_invalid_time")
    public WebElement invalidTime_input;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_address")
    public List<WebElement> signAddress_input;


    public void swip() {
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
    public WebElement tvName_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/rl_addressbook_receive")
    public WebElement addressBook_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_address_name")
    public WebElement addressName_display;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_add_note")
    public WebElement add_note;

    @FindBy(id = "com.tronlinkpro.wallet:id/et_note")
    public WebElement et_note;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_delete")
    public WebElement tv_delete;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_logo")
    public WebElement token_itemIV;


    public SendTrxSuccessPage enterSendTrxSuccessPage() {
        confirm_btn.click();
        return new SendTrxSuccessPage(driver);
    }


    public String trxCount = "";

    public WebElement  trx_btn() {
        return  token_itemIV;
    }

    public WebElement  trc10_btn() {
        return findElementByText("(1000002)");
    }

    public WebElement trc20_btn() {
        try {
            return findElementByText("(TCCc...5n71)");

        } catch (Exception e) {
            System.out.println("no MainChain");

        }

        try {

            return findElementByText("(TXkd...NfD7)");

        } catch (Exception e) {
            System.out.println("no DappChain");

            return findElementByText("(TXkd...NfD7)");

        }

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

    public SendTrxSuccessPage sendTrx(String sendAmount) throws Exception {
        receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        tranferCount_text.sendKeys(sendAmount);
        swip();
        send_btn.click();
        transferNow_btn.click();
        InputPasswordConfim_btn.sendKeys("Test0001");
        confirm_btn.click();
        TimeUnit.SECONDS.sleep(5);
        return new SendTrxSuccessPage(driver);
    }

    public SendTrxSuccessPage sendTrc10(String number) throws Exception {
        receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        tranferCount_text.sendKeys(number);
        swip();
        send_btn.click();
        transferNow_btn.click();
        InputPasswordConfim_btn.sendKeys("Test0001");
        confirm_btn.click();
        TimeUnit.SECONDS.sleep(5);
        back_bt.click();
        return new SendTrxSuccessPage(driver);
    }

    public SendTrxSuccessPage publicSendTrz(String receiverAddress, String number) throws Exception {
        receiveAddress_text.sendKeys(receiverAddress);
        tranferCount_text.sendKeys(number);
        swip();
        send_btn.click();
        transferNow_btn.click();
        InputPasswordConfim_btn.sendKeys("Test0001");
        confirm_btn.click();
        TimeUnit.SECONDS.sleep(5);
        back_bt.click();
        return new SendTrxSuccessPage(driver);
    }


    public SendTrxSuccessPage sendTrc20(String number) throws Exception {
        receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        tranferCount_text.sendKeys(number);
        swip();
        send_btn.click();
        transferNow_btn.click();
        InputPasswordConfim_btn.sendKeys("Test0001");
        confirm_btn.click();
        TimeUnit.SECONDS.sleep(5);
        back_bt.click();
        return new SendTrxSuccessPage(driver);
    }

    public void selectCoinType(String type) throws Exception {
        switch (type) {
            case "trc10":
                selectTokenType("10");
                break;
            case "trc20":
                selectTokenType("20");
                break;
            default:
                break;
        }
    }


    public SendTrxSuccessPage sendTrxTypeWithNotes(String sendAmount, String notes, String type) throws Exception {
        receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        selectCoinType(type);
        tranferCount_text.sendKeys(sendAmount);
        swip();
        waiteTime();
        add_note.click();
        waiteTime();
        et_note.sendKeys(notes);
        swip();
        waiteTime();
        send_btn.click();
        transferNow_btn.click();
        InputPasswordConfim_btn.sendKeys("Test0001");
        confirm_btn.click();
        TimeUnit.SECONDS.sleep(5);
        return new SendTrxSuccessPage(driver);
    }


    public SendTrxSuccessPage normalSendTrc10(String number) throws Exception {
        receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        token_btn.click();
        TimeUnit.SECONDS.sleep(1);
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
        TimeUnit.SECONDS.sleep(1);
        trc20_btn().click();
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
        TimeUnit.SECONDS.sleep(1);
        trc10_btn.click();
        String balance = balance_text.getText();
        double trc10Amount = 0;
        Pattern pattern = Pattern.compile("\\d+\\.?\\d*");
        Matcher matcher = pattern.matcher(balance);
        if (matcher.find())
            trc10Amount = Double.valueOf(matcher.group(0));
        return trc10Amount;
    }

    public double getTrc20Amount() throws Exception {
        token_btn.click();
        TimeUnit.SECONDS.sleep(1);
        trc20_btn().click();
        String balance = balance_text.getText();
        double trc10Amount = 0;
        Pattern pattern = Pattern.compile("\\d+\\.?\\d*");
        Matcher matcher = pattern.matcher(balance);
        if (matcher.find())
            trc10Amount = Double.valueOf(matcher.group(0));
        return trc10Amount;
    }


    public void sendKey(WebElement el, String value) throws Exception {
        el.sendKeys(value);
        TimeUnit.SECONDS.sleep(2);
    }


    public void sendAllTrx(String value) throws Exception {
        receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        //calculate trx
        switch (value) {
            case "max":
                tvMax_btn.click();
                break;
            case "mix":
                tranferCount_text.sendKeys("0");
                break;
            case "tooMuch":
                tranferCount_text.sendKeys("9999999999");
                break;
        }
        swip();
        waiteTime();
        send_btn.click();
        TimeUnit.SECONDS.sleep(1);
    }

    public void sendAllTrc10(String value) throws Exception {
        receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        selectTokenType("10");
        switch (value) {
            case "max":
                tvMax_btn.click();
                break;
            case "mix":
                tranferCount_text.sendKeys("0");
                break;
            case "tooMuch":
                tranferCount_text.sendKeys("9999999999");
                break;
        }
        Helper.swipScreen(driver);
        send_btn.click();
        TimeUnit.SECONDS.sleep(1);
    }

    public void sendAllTrc20(String value) throws Exception {
        TimeUnit.SECONDS.sleep(2);
        receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        selectTokenType("20");

        switch (value) {
            case "max":
                tvMax_btn.click();
                break;
            case "mix":
                tranferCount_text.sendKeys("0");
                break;
            case "tooMuch":
                tranferCount_text.sendKeys("9999999999");
                break;
        }
        Helper.swipScreen(driver);
        send_btn.click();
        TimeUnit.SECONDS.sleep(1);
    }

    public String sendMaxTrc20() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        receiveAddress_text.sendKeys(unActiveAddress);
        selectTokenType("20");
        tvMax_btn.click();
        Helper.swipScreen(driver);
        String allNumberText = balance_text.getText();
        System.out.println("sendMaxTrc20_allNumberText" + allNumberText);
        send_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return allNumberText;
    }

    /**
     * @param cointype 10 20 noun
     * @return
     * @throws Exception
     */
    public String sendMaxCoinWithType(String... cointype) throws Exception {
        TimeUnit.SECONDS.sleep(2);
        receiveAddress_text.sendKeys(unActiveAddress);
        if (cointype.length != 0) {
            selectTokenType(cointype[0]);
        }
        tvMax_btn.click();
        Helper.swipScreen(driver);
        String allNumberText = balance_text.getText();
        System.out.println("allNumberText: " + allNumberText);
        send_btn.click();
        TimeUnit.SECONDS.sleep(2);
        return allNumberText;
    }

    public void selectTokenType(String value) throws Exception {
        waiteTime();
        switch (value) {
            case "20":
                token_btn.click();
                TimeUnit.SECONDS.sleep(3);
                trc20_btn().click();
                break;
            case "10":
                token_btn.click();
                TimeUnit.SECONDS.sleep(3);
                trc10_btn().click();
        }
    }

    public AssetPage sendRamonTrxSuccess(String revAddress) throws Exception {
        receiveAddress_text.sendKeys(revAddress);
        Random random = new Random();
        float count = random.nextFloat();
        DecimalFormat df = new DecimalFormat("0.00");
        String str = df.format(count);
        trxCount = str;
        tranferCount_text.sendKeys(str);
        swip();
        waiteTime();
        add_note.click();
        waiteTime();
        et_note.sendKeys("I'm multiSign notes");
        waiteTime();
        swip();
        send_btn.click();
        TimeUnit.SECONDS.sleep(2);
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

    public void inputFormAddress(String address) {
        waiteTime();
        transferAddress_deleteBtn.click();
        waiteTime();
        transferAddress_text.sendKeys(address);
        waiteTime();
    }


}
