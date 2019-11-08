package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class VotePage extends AbstractPage {


    public IOSDriver<?> driver;



    @FindBy(xpath = "(//XCUIElementTypeButton[@name='投票'])[1]")
    public WebElement vote_title;


    public VotePage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }








}
