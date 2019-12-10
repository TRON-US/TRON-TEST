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

    @FindBy(name = "com.tronlink.wallet:id/tv_trx_amount")
    public WebElement trx_text;



    @FindBy(name = "com.tronlink.wallet:id/tv_no_bandwidth")
    public WebElement hits_text;



    @FindBy(name = "directionContent")
    public WebElement chain_text;



    @FindBy(className = "XCUIElementTypeTextField")
    public WebElement count_text;



    @FindBy(name = "com.tronlink.wallet:id/bt_go")
    public WebElement transferIn_btn;



    @FindBy(name = "com.tronlink.wallet:id/tv_bw_amount")
    public WebElement fee_text;



    @FindBy(name = "amountDesContent")
    public WebElement availableBalance_text;



    @FindBy(className = "XCUIElementTypeSecureTextField")
    public WebElement password_input;






    @FindBy(name = "com.tronlink.wallet:id/tv_tab_title")
    public WebElement transferIn_tab;



    @FindBy(name = "com.tronlink.wallet:id/time")
    public WebElement time_text;

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
        Helper.tapWhitePlace(driver);
        get_inter_btn().click();
        TimeUnit.SECONDS.sleep(2);

    }
    public void inputAndTapToTransferOut() throws Exception {
        count_text.sendKeys("10");
        Helper.tapWhitePlace(driver);
        get_out_btn().click();
        TimeUnit.SECONDS.sleep(2);

    }
    public String getvalueofBandwidthText(){
        WebElement element = driver.findElementById("bandwidthText");
        String val = element.getText().split(" ")[0];

        return  val;
    }


    public String getTransferInfo(String info) throws Exception {
        count_text.sendKeys("10");
        Helper.tapWhitePlace(driver);
        get_inter_btn().click();
        TimeUnit.SECONDS.sleep(1);
        String value = "";
        switch (info){
            case "trx":
                value = trx_text.getText();
                break;
            case "hits":
                value = hits_text.getText();
                break;
            case "fee":
                value = fee_text.getText().split(" ")[0];
                break;
        }
        return value;
    }


    public TrxPage enterTrxPageWithTransferSuccess() throws Exception {
        count_text.sendKeys("10");
        Helper.tapWhitePlace(driver);
        get_inter_btn().click();
        TimeUnit.SECONDS.sleep(2);
        password_input.sendKeys("Test0001");
        Helper.tapWhitePlace(driver);
        get_finish_btn().click();
        TimeUnit.SECONDS.sleep(3);
        return new TrxPage(driver);
    }

    public TrxPage enterTrxPageWithTransferSuccess(String count) throws Exception {
        count_text.sendKeys(count);
        Helper.tapWhitePlace(driver);
        get_inter_btn().click();
        TimeUnit.SECONDS.sleep(5);
        password_input.sendKeys("Test0001");
        Helper.tapWhitePlace(driver);
        get_finish_btn().click();
        TimeUnit.SECONDS.sleep(3);
        return new TrxPage(driver);
    }


    public TrxPage enterTrxPageWithTransferOutSuccess() throws Exception {
        count_text.sendKeys("10");
        Helper.tapWhitePlace(driver);
        get_out_btn().click();
        TimeUnit.SECONDS.sleep(2);
        password_input.sendKeys("Test0001");
        Helper.tapWhitePlace(driver);
        get_finish_btn().click();
        TimeUnit.SECONDS.sleep(3);
        return new TrxPage(driver);
    }

    public TrxPage enterTrxPageWithTransferOutSuccess(String count) throws Exception {
        count_text.sendKeys(count);
        Helper.tapWhitePlace(driver);
        get_out_btn().click();
        TimeUnit.SECONDS.sleep(2);
        password_input.sendKeys("Test0001");
        Helper.tapWhitePlace(driver);
        get_finish_btn().click();
        TimeUnit.SECONDS.sleep(3);
        return new TrxPage(driver);
    }

    public boolean checkStep() {

        transferIn_tab.click();
        try {
            time_text.getText();
        }catch (Exception e){

        }
        return false;
    }









}
