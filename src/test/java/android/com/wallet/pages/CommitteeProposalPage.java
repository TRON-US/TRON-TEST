package android.com.wallet.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommitteeProposalPage extends AbstractPage {


    public AndroidDriver<?> driver;


    public CommitteeProposalPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "com.tronlink.wallet:id/title_all_votes")
    public WebElement allVotes_text;



    @FindBy(id = "com.tronlink.wallet:id/et_search")
    public WebElement search_ipt;



    @FindBy(id = "com.tronlink.wallet:id/iv_search")
    public WebElement search_btn;



    @FindBy(id = "com.tronlink.wallet:id/tv_proposals_name")
    public List<WebElement> data_url_text;



    @FindBy(id = "com.tronlink.wallet:id/tv_create_propose")
    public WebElement createPropose_btn;




    @FindBy(id = "com.tronlink.wallet:id/tv_title")
    public WebElement title_btn;



    @FindBy(id = "com.tronlink.wallet:id/iv_agree")
    public WebElement agree_btn;



    @FindBy(id = "com.tronlink.wallet:id/et_new_password")
    public WebElement pw_input;



    @FindBy(id = "com.tronlink.wallet:id/bt_send")
    public WebElement send_btn;



    @FindBy(id = "com.tronlink.wallet:id/num_all_votes")
    public WebElement approveNum_text;



    @FindBy(id = "com.tronlink.wallet:id/tv_proposals_id")
    public WebElement proposalId_text;



    @FindBy(id = "com.tronlink.wallet:id/tv_proposals_state")
    public WebElement proposals_state_btn;



    @FindBy(id = "com.tronlink.wallet:id/tv_my_proposals")
    public WebElement myProposals_text;



    @FindBy(id = "com.tronlink.wallet:id/tv_approved_proposals")
    public WebElement myApproved_text;



    @FindBy(id = "com.tronlink.wallet:id/tv_my_proposals")
    public WebElement myProposal_btn;


    public void searchResult() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        search_ipt.sendKeys("com");
        TimeUnit.SECONDS.sleep(1);
        search_btn.click();
        TimeUnit.SECONDS.sleep(2);
        //String url = data_url_text.get(1).getText();
        //return url;
    }


    public CreateProposePage enterCreateProposePage() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        createPropose_btn.click();
        TimeUnit.SECONDS.sleep(3);
        return new CreateProposePage(driver);
    }


//    public void approveProposal() throws Exception {
//        agree_btn.click();
//        TimeUnit.SECONDS.sleep(2);
//
//    }


    public ProposalDetailsPage enterpProposalDetailsPage() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        proposalId_text.click();
        TimeUnit.SECONDS.sleep(3);
        return new ProposalDetailsPage(driver);
    }



    public MyProposalsPage enterMyProposals() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        myProposals_text.click();
        TimeUnit.SECONDS.sleep(3);
        return new MyProposalsPage(driver);
    }



    public MyProposalsPage enterMyProposalsPage() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        myProposal_btn.click();
        TimeUnit.SECONDS.sleep(5);
        return new MyProposalsPage(driver);
    }




}
