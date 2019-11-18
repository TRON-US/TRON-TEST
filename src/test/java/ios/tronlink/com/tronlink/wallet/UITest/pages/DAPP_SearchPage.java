package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class DAPP_SearchPage extends AbstractPage {

    public IOSDriver<?> driver;

    public DAPP_SearchPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "com.tronlink.wallet:id/et_search")
    public WebElement search_text;


    public DAPP_SearchResultPage search(String keyword){
        try {
            search_text.sendKeys(keyword);
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            System.out.println(e);
        }
        return new DAPP_SearchResultPage(driver);
    }






}
