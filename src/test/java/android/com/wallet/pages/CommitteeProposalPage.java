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
        createPropose_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new CreateProposePage(driver);
    }





}