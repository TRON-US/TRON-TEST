package android.com.wallet.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyProposalsPage extends AbstractPage {


    public AndroidDriver<?> driver;


    public MyProposalsPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "com.tronlink.wallet:id/tv_make_proposal")
    public WebElement createProposal_btn;




}
