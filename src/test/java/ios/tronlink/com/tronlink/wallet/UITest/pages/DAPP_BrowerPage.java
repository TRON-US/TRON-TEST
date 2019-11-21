package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class DAPP_BrowerPage extends AbstractPage {

    public IOSDriver<?> driver;

    public DAPP_BrowerPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(className = "XCUIElementTypeTextField")
    public WebElement url_input;


    @FindBy(className = "XCUIElementTypeButton")
    public WebElement enterBrowser_btn;


    @FindBy(id = "F1000000-0000-0000-8D0B-000000000000")
    public WebElement dappTtile_btn;




    public void testUrl(){
        try {
            url_input.sendKeys("https://www.wink.org/#/");
            enterBrowser_btn.click();
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){
            System.out.println(e);
        }

    }




}
