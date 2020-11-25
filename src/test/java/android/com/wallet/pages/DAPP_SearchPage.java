package android.com.wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

public class DAPP_SearchPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public DAPP_SearchPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "com.tronlinkpro.wallet:id/et_search")
    public WebElement search_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_name")
    public WebElement dappName_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_delete")
    public WebElement iv_delete;


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
