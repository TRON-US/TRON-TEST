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



    @FindBy(id = "com.tronlink.wallet:id/bt_send")
    public WebElement send_btn;



    @FindBy(id = "com.tronlink.wallet:id/et_new_password")
    public WebElement pw_input;



    @FindBy(id = "com.tronlink.wallet:id/tv_proposals_name")
    public WebElement proposalName_text;









}
