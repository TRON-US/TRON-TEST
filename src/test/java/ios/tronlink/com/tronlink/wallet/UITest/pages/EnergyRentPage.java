package ios.tronlink.com.tronlink.wallet.UITest.pages;


import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EnergyRentPage extends AbstractPage {
    public IOSDriver<?> driver;


    public EnergyRentPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(name = "闪兑")
    public WebElement title;


    @FindBy(name = "租用量")
    public WebElement contentNamerant;



}
