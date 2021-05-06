package android.com.wallet.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CreateProposePage extends AbstractPage {


    public AndroidDriver<?> driver;


    public CreateProposePage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "com.tronlinkpro.wallet:id/et_proposals")
    public List<WebElement> proValue_ipt;




    @FindBy(id = "com.tronlinkpro.wallet:id/tv_confirm")
    public WebElement confirm_btn;



    @FindBy(id = "com.tronlinkpro.wallet:id/et_new_password")
    public WebElement password_btn;




    @FindBy(id = "com.tronlinkpro.wallet:id/bt_send")
    public WebElement send_btn;



    @FindBy(id = "com.tronlinkpro.wallet:id/tv_reset")
    public WebElement reset_btn;






    public CommitteeProposalPage createProposal() throws Exception {
        proValue_ipt.get(1).clear();
        proValue_ipt.get(1).sendKeys("9997");
        confirm_btn.click();
        TimeUnit.SECONDS.sleep(1);
        send_btn.click();
        password_btn.sendKeys("Test0001");
        send_btn.click();
        TimeUnit.SECONDS.sleep(5);
        return new CommitteeProposalPage(driver);
    }





}
