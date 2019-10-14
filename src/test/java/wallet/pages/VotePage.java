package wallet.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * 收款页
 */

public class VotePage extends AbstractPage {

    public AndroidDriver<?> driver;

    public VotePage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "com.tronlink.wallet:id/title")
    public WebElement voteTitle_btn;


    @FindBy(id = "com.tronlink.wallet:id/reset")
    public WebElement reset_btn;


    @FindBy(id = "com.tronlink.wallet:id/et_input")
    public WebElement et_input;


    @FindBy(id = "com.tronlink.wallet:id/rl_vote")
    public WebElement vote_btn;




    public VoteConfirmPage enterVoteConfirmPage(){
        reset_btn.click();
        et_input.sendKeys("1");
        vote_btn.click();
        return new VoteConfirmPage(driver);
    }

}
