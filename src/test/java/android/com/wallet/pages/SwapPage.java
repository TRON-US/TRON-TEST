package android.com.wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

public class SwapPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public SwapPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


//
public void enterMyFinancial() throws Exception{
    TimeUnit.SECONDS.sleep(1);
    findElementByText("理财资产").click();
    unTillSomeThing("TRON");
}

    public void showByTypeItem() throws Exception{
        TimeUnit.SECONDS.sleep(1);
        findElementByText("按项目").click();
        unTillSomeThing("TRON");
    }


    public float getTrxButtonY(){
        return findElementByText("TRX").getLocation().getY();
    }

    public void switchToAPY() throws Exception{
        findElementByText("按默认").click();
        TimeUnit.SECONDS.sleep(1);
        findElementByText("按 APY").click();
    }

    public void enterClmPage() throws Exception{
        TimeUnit.SECONDS.sleep(1);
        findElementByText("待领取收益").click();
        TimeUnit.SECONDS.sleep(1);
        try {
            if (isElementExist("选择钱包")) {
                findElementByText("Auto-test").click();
            }
        }catch (Exception e){
            log("4.12.0");
        }
        unTillSomeThing("TRON");
        TimeUnit.SECONDS.sleep(2);
    }


    public void enterSwitchPage() throws Exception{
        TimeUnit.SECONDS.sleep(1);
        findElementByText("钱包汇总").click();
        unTillSomeThing("选择后可查看单一钱包理财数据");
    }


    @FindBy(id = "com.tronlinkpro.wallet:id/ll_common_left")
    public WebElement back_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_token_name_from")
    public WebElement tv_token_name_from;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_token_name_to")
    public WebElement tv_token_name_to;
    
    @FindBy(id = "com.tronlinkpro.wallet:id/tv_token_from")
    public WebElement tv_token_from;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_token_to")
    public WebElement tv_token_to;

    @FindBy(id = "com.tronlinkpro.wallet:id/et_amount_from")
    public WebElement et_amount_from;

    @FindBy(id = "com.tronlinkpro.wallet:id/et_amount_to")
    public WebElement et_amount_to;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_rate_number")
    public WebElement tv_rate_number;

    @FindBy(id = "com.tronlinkpro.wallet:id/btn_swap_instant")
    public WebElement btn_swap_instant;

    @FindBy(id = "com.tronlinkpro.wallet:id/text_token_to")
    public WebElement text_token_to;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_rate_right")
    public WebElement tv_rate_right ;
    
    @FindBy(id = "com.tronlinkpro.wallet:id/token_consume_name")
    public WebElement token_consume_name;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_date")
    public WebElement tv_date;

    @FindBy(id = "com.tronlinkpro.wallet:id/token_consume_count")
    public WebElement token_consume_count;

    public void  inputFromAmount(String amount){
        et_amount_from.sendKeys(amount);
    }

    public void swapConfirmView() throws Exception{
        btn_swap_instant.click();
        TimeUnit.SECONDS.sleep(3);
    }

    public void swapSentAction() throws Exception{
        send.click();
        et_new_password.sendKeys("Test0001");
        bt_send.click();
    }

}
