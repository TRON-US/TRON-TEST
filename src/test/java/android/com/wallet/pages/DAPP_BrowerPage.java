package android.com.wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

public class DAPP_BrowerPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public DAPP_BrowerPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "com.tronlink.global:id/et_url")
    public WebElement url_input;


    @FindBy(id = "com.tronlink.global:id/bt")
    public WebElement enterBrowser_btn;


    @FindBy(id = "com.tronlink.global:id/tv_common_title")
    public WebElement dappTtile_btn;




    public void testUrl(){
        try {
            url_input.sendKeys("https://sun.io");
            enterBrowser_btn.click();
            TimeUnit.SECONDS.sleep(8);
        }catch (Exception e){
            System.out.println(e);
        }

    }




}
