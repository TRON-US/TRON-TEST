package android.com.wallet.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class MyProposalsPage extends AbstractPage {


    public AndroidDriver<?> driver;


    public MyProposalsPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "wallet.tronlink.harmony:id/iv_agree")
    public WebElement agree_btn;



    @FindBy(id = "wallet.tronlink.harmony:id/et_new_password")
    public WebElement pw_input;



    @FindBy(id = "wallet.tronlink.harmony:id/bt_send")
    public WebElement send_btn;



    @FindBy(id = "wallet.tronlink.harmony:id/tv_proposals_id")
    public WebElement proposalId_text;



    @FindBy(id = "wallet.tronlink.harmony:id/tv_make_proposal")
    public WebElement createProposal_btn;



    @FindBy(id = "wallet.tronlink.harmony:id/num_all_votes")
    public WebElement approveNum_text;



    public ProposalDetailsPage enterpProposalDetailsPage() throws Exception {
        proposalId_text.click();
        TimeUnit.SECONDS.sleep(2);
        return new ProposalDetailsPage(driver);
    }




}
