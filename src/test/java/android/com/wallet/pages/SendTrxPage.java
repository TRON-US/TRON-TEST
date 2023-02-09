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

    public WebElement addressBookTab(){
        return  findElementByText("地址本");
    }
    @FindBy(id = "com.tronlinkpro.wallet:id/confirm")
    public WebElement confirm;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_name")
    public WebElement tv_name;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_arrow_right")
    public WebElement iv_arrow_right;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_right_active_account")
    public WebElement tv_right_active_account;

    @FindBy(id = "com.tronlinkpro.wallet:id/error_view")
    public WebElement error_view;

    @FindBy(id = "com.tronlinkpro.wallet:id/net_error")
    public WebElement net_error;

    @FindBy(id = "com.tronlinkpro.wallet:id/btn_transaction_info")
    public WebElement btn_transaction_info;

    //com.tronlinkpro.wallet:id/toscan
    @FindBy(id = "com.tronlinkpro.wallet:id/toscan")
    public WebElement coldHadScan_next_btn;

    @FindBy(id ="com.tronlinkpro.wallet:id/et_search")
    public WebElement et_search;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_delete")
    public WebElement inputStepOneDelete;


    @FindBy(id = "com.tronlinkpro.wallet:id/iv_one_delete")
    public WebElement transferAddress_deleteBtn;

    @FindBy(id = "com.tronlinkpro.wallet:id/et_transfer_address")
    public WebElement transferAddress_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/et_input_address")
    public WebElement receiveAddress_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/btn_next")
    public WebElement next_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/et_count")
    public WebElement tranferCount_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/bt_send")
    public WebElement send_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/bt_send")
    public WebElement bt_send;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_trans_content")
    public WebElement tv_trans_content;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_trans_type")
    public WebElement tv_trans_type;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_multi_sign")
    public WebElement tv_multi_sign;

    @FindBy(id = "com.tronlinkpro.wallet:id/btn_asset_confirm")
    public WebElement transferNow_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_account")
    public WebElement tv_account;

    @FindBy(id = "com.tronlinkpro.wallet:id/et_new_password")
    public WebElement InputPasswordConfim_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/btn_confirm")
    public WebElement confirm_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/transfer_out_address")
    public WebElement from_address;

    @FindBy(id = "com.tronlinkpro.wallet:id/receiving_address")
    public WebElement to_address;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_error")
    public WebElement formatErrorHits_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_info_subtitle")
    public WebElement real_money;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_fee")
    public WebElement fee_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_address_book")
    public WebElement tv_address_book;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_add_address")
    public WebElement tv_add_address;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_consume_resource")
    public WebElement bandwidth_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_note")
    public WebElement note_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_no_bandwidth")
    public WebElement no_bandwidth;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_no_energy")
    public WebElement tv_no_energy;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_balance")
    public WebElement balance_text;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_max")
    public WebElement tvMax_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/rl_token")
    public WebElement token_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/ll_common_left")
    public WebElement back_bt;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_tips")
    public WebElement iv_tips;

    public void showResTips(){
        iv_tips.click();
    }

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_tips_fee")
    public WebElement iv_tips_fee;

    public void showFeeResTips(){
        iv_tips_fee.click();
    }

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_resource_consume_left")
    public WebElement tv_resource_consume_left;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_left")
    public WebElement tv_left;

    @FindBy(xpath = "//*[@text='(1000002)']")
    public WebElement trc10_btn;


//    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[3]")
//    public WebElement trc20_btn;

    @FindBy(xpath = "//*[@text='(TCCcB***15n71)']")
    public WebElement trc20_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/rl_bottom_next")
    public WebElement rl_bottom_next;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_invalid_time")
    public WebElement invalidTime_input;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_address")
    public List<WebElement> signAddress_input;


    public void swip() {
        Helper.swipScreen(driver);
    }



    @FindBy(id = "com.tronlinkpro.wallet:id/tv_selected_name")
    public WebElement selectSignName_text;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_name")
    public WebElement tvName_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/rl_addressbook_receive")
    public WebElement addressBook_btn;



    @FindBy(id = "com.tronlinkpro.wallet:id/tv_address_name")
    public WebElement addressName_display;

    @FindBy(id = "com.tronlinkpro.wallet:id/ll_add_note")
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

    public TransactionDetailInfomaitonPage enterTransationDetailPage(){
        btn_transaction_info.click();
        return new TransactionDetailInfomaitonPage(driver);
    }

    public String trxCount = "";

    public WebElement  trx_btn() {
        return  token_itemIV;
    }

    public WebElement  trc10_btn() {
        return findElementByText("ID:1000002");
    }



    public void sendAddressAndInputNumber(String address,String number) throws Exception{
        receiveAddress_text.sendKeys(address);
        TimeUnit.SECONDS.sleep(2);
        if ( next_btn.isEnabled()){
            next_btn.click();
        }else {
            TimeUnit.SECONDS.sleep(3);
            next_btn.click();
        }
        TimeUnit.SECONDS.sleep(1);
        tranferCount_text.sendKeys(number);
        TimeUnit.SECONDS.sleep(1);
    }

    public void normalSendStepOne(){
        receiveAddress_text.sendKeys("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
        next_btn.click();
    }

//    public void  watchWalletSend(String type){
//        receiveAddress_text.sendKeys("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
//        selectCoinType(type);
//    }




    public void SendTRXToConfirmView(String sendAmount) throws Exception{
        sendKeys(receiveAddress_text,"TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
        enableClick(next_btn);
        sendKeys(tranferCount_text,sendAmount);
        enableClick(send_btn);
        TimeUnit.SECONDS.sleep(3);
    }

    public SendTrxSuccessPage sendTrx(String sendAmount) throws Exception {
        SendTRXToConfirmView(sendAmount);
        enableClick(confirm_btn);
        sendKeys(InputPasswordConfim_btn,"Test0001");
        enableClick(send_btn);
        TimeUnit.SECONDS.sleep(4);
        return new SendTrxSuccessPage(driver);
    }

    public SendTrxSuccessPage sendNFT(String address) throws Exception {
        sendKeys(receiveAddress_text,address);
//        TimeUnit.SECONDS.sleep(3);
//        if (!next_btn.isEnabled()){
//            TimeUnit.SECONDS.sleep(3);
//        }
        enableClick(next_btn);
//        if (!send_btn.isEnabled()){
//            TimeUnit.SECONDS.sleep(3);
//        }
        enableClick(send_btn);
        TimeUnit.SECONDS.sleep(2);//弹出一个新的确认视图
//        if (!confirm_btn.isEnabled()){
//            TimeUnit.SECONDS.sleep(3);
//        }
        enableClick(confirm_btn);
//        TimeUnit.SECONDS.sleep(4);
        sendKeys(InputPasswordConfim_btn,"Test0001");
//        InputPasswordConfim_btn.sendKeys("Test0001");
        send_btn.click();
        TimeUnit.SECONDS.sleep(4);
        return new SendTrxSuccessPage(driver);
    }


    public SendTrxSuccessPage sendTrcTokenWithCurrent(String sendAmount) throws Exception {
        sendKeys(receiveAddress_text,"TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
//        receiveAddress_text.sendKeys("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
        enableClick(next_btn);
        sendKeys(tranferCount_text,sendAmount);
//        tranferCount_text.sendKeys(sendAmount);
        enableClick(send_btn);
//        TimeUnit.SECONDS.sleep(6);
        enableClick(confirm_btn);
//        TimeUnit.SECONDS.sleep(4);
        sendKeys(InputPasswordConfim_btn,"Test0001");
//        InputPasswordConfim_btn.sendKeys("Test0001");
        enableClick(send_btn);
//        send_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new SendTrxSuccessPage(driver);
    }

    public SendTrxSuccessPage sendTrc20(String number) throws Exception {
        receiveAddress_text.sendKeys("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
        next_btn.click();
        tranferCount_text.sendKeys(number);
        send_btn.click();
        TimeUnit.SECONDS.sleep(3);
        confirm_btn.click();
        InputPasswordConfim_btn.sendKeys("Test0001");
        send_btn.click();
        TimeUnit.SECONDS.sleep(4);
//        back_bt.click();
        return new SendTrxSuccessPage(driver);
    }

    @FindBy(id = "com.tronlinkpro.wallet:id/ll_add_note")
    public WebElement iv_add_note;

    @FindBy(id = "com.tronlinkpro.wallet:id/ll_add_note")
    public WebElement ll_add_note;

    @FindBy(id = "com.tronlinkpro.wallet:id/bt_note_remove")
    public WebElement bt_note_remove;
    
    @FindBy(id = "com.tronlinkpro.wallet:id/info")
    public WebElement info;

    @FindBy(id = "com.tronlinkpro.wallet:id/btn_confirm_2")
    public WebElement btn_confirm_2;

    @FindBy(xpath = "//*[@text='添加转账备注']")
    public WebElement note_transfer;

    public SendTrxSuccessPage sendTrxWithNote(String sendAmount,String note) throws Exception {
        sendKeys(receiveAddress_text,"TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
//        receiveAddress_text.sendKeys("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
//        next_btn.click();
        enableClick(next_btn);
        sendKeys(tranferCount_text,sendAmount);
//        tranferCount_text.sendKeys(sendAmount);
        note_transfer.click();
        sendKeys(et_note,note);
//        et_note.sendKeys(note);
        enableClick(send_btn);
//        send_btn.click();
        TimeUnit.SECONDS.sleep(3);
        enableClick(confirm_btn);
//        confirm_btn.click();
//        TimeUnit.SECONDS.sleep(4);
        sendKeys(InputPasswordConfim_btn,"Test0001");
//        InputPasswordConfim_btn.sendKeys("Test0001");
        enableClick(send_btn);
//        send_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new SendTrxSuccessPage(driver);
    }

    public void SendTokenWithNameAmountWatch(String amount,String name) throws Exception{
        sendKeys(receiveAddress_text,"TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
//        receiveAddress_text.sendKeys("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
        enableClick(next_btn);
//        next_btn.click();
        selectTokenByName(name);
        sendKeys(tranferCount_text,amount);
//        tranferCount_text.sendKeys(amount);
        enableClick(send_btn);
//        send_btn.click();
        TimeUnit.SECONDS.sleep(3);
        enableClick(confirm_btn);
//        confirm_btn.click();
    }




    public void selectCoinType(String type) throws Exception {
        switch (type) {
            case "10":
                selectTokenType("10");
                break;
            case "20":
                selectTokenType("20");
                break;
            default:
                break;
        }
    }


    public SendTrxSuccessPage sendTrxTypeWithNotes(String sendAmount, String notes, String type) throws Exception {
        receiveAddress_text.sendKeys("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
        next_btn.click();
        selectCoinType(type);
        tranferCount_text.sendKeys(sendAmount);
        add_note.click();
        et_note.sendKeys(notes);
        send_btn.click();
        confirm_btn.click();
        InputPasswordConfim_btn.sendKeys("Test0001");
        send_btn.click();
        TimeUnit.SECONDS.sleep(7);
        return new SendTrxSuccessPage(driver);
    }

//no press send button
    public void sendAllTrx(String value) throws Exception {
        sendKeys(receiveAddress_text,"TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
//        receiveAddress_text.sendKeys("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
//        next_btn.click();
        enableClick(next_btn);
        switch (value) {
            case "max":
                tvMax_btn.click();
                break;
            case "min":
                tranferCount_text.sendKeys("0");
                break;
            case "tooMuch":
                tranferCount_text.sendKeys("9999999999");
                break;
        }
        swip();
        driver.hideKeyboard();
        TimeUnit.SECONDS.sleep(1);
    }

    public void sendAllTrc10(String value) throws Exception {
        sendKeys(receiveAddress_text,"TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
//        receiveAddress_text.sendKeys("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
        selectTokenType("10");
        switch (value) {
            case "max":
                tvMax_btn.click();
                break;
            case "min":
                tranferCount_text.sendKeys("0");
                break;
            case "tooMuch":
                tranferCount_text.sendKeys("9999999999");
                break;
        }
        Helper.swipScreen(driver);
        enableClick(send_btn);
//        send_btn.click();
        TimeUnit.SECONDS.sleep(3);
    }

    public void sendTokenMin(String type, String udid) throws Exception {
        sendKeys(receiveAddress_text,"TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
        enableClick(next_btn);
        switch (type) {
            case "20":
                selectTokenType("20");
                break;
            case "10":
                selectTokenType("10");
                break;
            case "trx":
                selectTokenType("trx");
                break;
        }
        keyboardSogou(udid);
        tranferCount_text.sendKeys("0");
        driver.hideKeyboard();
        keyboardUnicode(udid);
        TimeUnit.SECONDS.sleep(1);
    }


    public void sendAllTrc20(String value) throws Exception {
        sendKeys(receiveAddress_text,"TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
        enableClick(next_btn);
        selectTokenType("20");

        switch (value) {
            case "max":
                tvMax_btn.click();
                break;
            case "tooMuch":
                tranferCount_text.sendKeys("9999999999");
                break;
            default:
                System.out.println("Send min to use sendTokenMin Func");
        }
        TimeUnit.SECONDS.sleep(1);
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
        TimeUnit.SECONDS.sleep(1);
        Helper.swipScreen(driver);
        tvMax_btn.click();
        String allNumberText = balance_text.getText();
        System.out.println("allNumberText: " + allNumberText);
        send_btn.click();
        TimeUnit.SECONDS.sleep(2);
        return allNumberText;
    }

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_symbol")
    public WebElement tv_symbol;

    @FindBy(id = "com.tronlinkpro.wallet:id/assets_name")
    public WebElement assets_name;

    @FindBy(id = "com.tronlinkpro.wallet:id/assets_name")
    public List<WebElement> assets_names;

    public void selectTokenByName(String name) throws Exception{
        tv_symbol.click();
        et_search.sendKeys(name);
        TimeUnit.SECONDS.sleep(1);
        assets_name.click();
    }


    public void selectTokenType(String value) throws Exception {
        waiteTime();
        switch (value) {
            case "20":
                tv_symbol.click();
                et_search.sendKeys("TRX");
                TimeUnit.SECONDS.sleep(1);
                for (int i = 0; i < 3; i++) {
                    if (assets_names.get(i).getText().equalsIgnoreCase("TRX")){
                        assets_names.get(i).click();
                        return;
                    }
                }
                break;
            case "10":
                selectTokenByName("tronlink_token");
                break;
            default:
                System.out.println("choice TRX");
        }
    }



    public String getTrxCount() {
        return trxCount;
    }

    public void inputFormAddress(String address) throws Exception{
        tv_multi_sign.click();
        TimeUnit.SECONDS.sleep(2);
        receiveAddress_text.sendKeys(address);
    }

    public void goToSecondPage(){
        next_btn.click();
    }



}
