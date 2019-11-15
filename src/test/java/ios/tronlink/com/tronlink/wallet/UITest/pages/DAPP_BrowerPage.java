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


    @FindBy(id = "com.tronlink.wallet:id/et_url")
    public WebElement url_input;


    @FindBy(id = "com.tronlink.wallet:id/bt")
    public WebElement enterBrowser_btn;


    @FindBy(id = "com.tronlink.wallet:id/middle_title")
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
