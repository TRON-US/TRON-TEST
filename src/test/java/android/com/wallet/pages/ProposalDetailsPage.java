package android.com.wallet.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProposalDetailsPage extends AbstractPage {


    public AndroidDriver<?> driver;


    public ProposalDetailsPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "com.tronlink.wallet:id/tv_proposals_cancle")
    public WebElement proposalCancle_btn;



    @FindBy(id = "com.tronlink.wallet:id/tv_ok")
    public WebElement confirm_btn;

    @FindBy(id = "com.tronlink.wallet:id/tv_proposals_state")
    public WebElement proposals_state_btn;




    @FindBy(id = "com.tronlink.wallet:id/bt_send")
    public WebElement send_btn;



    @FindBy(id = "com.tronlink.wallet:id/et_new_password")
    public WebElement pw_input;



    @FindBy(id = "com.tronlink.wallet:id/tv_proposals_name")
    public WebElement proposalName_text;



    @FindBy(id = "com.tronlink.wallet:id/tv_proposals_state")
    public WebElement proposalsState_btn;



    @FindBy(id = "com.tronlink.wallet:id/num_all_votes")
    public WebElement numAllVotes_btn;




    @FindBy(id = "com.tronlink.wallet:id/num_valid_votes")
    public WebElement numValidVotes_btn;




    @FindBy(id = "com.tronlink.wallet:id/tv_create_time")
    public WebElement createTime_text;



    @FindBy(id = "com.tronlink.wallet:id/tv_end_time")
    public WebElement endTime_text;



    @FindBy(id = "com.tronlink.wallet:id/tv_no_approvers")
    public WebElement noApprovers_text;




    @FindBy(id = "com.tronlink.wallet:id/tv_content")
    public WebElement pproversPeo_text;




}
