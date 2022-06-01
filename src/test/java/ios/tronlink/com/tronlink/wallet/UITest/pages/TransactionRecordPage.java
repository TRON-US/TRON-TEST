package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * 交易记录页
 */
public class TransactionRecordPage extends AbstractPage {

    public IOSDriver<?> driver;

    public TransactionRecordPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "ic account")
    public  WebElement icNav_Icon;


    @FindBy(name = "headerLabel")
    public WebElement headerLabel_text;





}
