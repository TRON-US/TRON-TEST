package android.com.wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

public class DiscoverPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public DiscoverPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_one")
    public WebElement search_btn;

    //com.tronlinkpro.wallet:id/tv_title
    @FindBy(id = "com.tronlinkpro.wallet:id/tv_title")
    public WebElement dapp_title;

    //com.tronlinkpro.wallet:id/imageview
    @FindBy(id = "com.tronlinkpro.wallet:id/imageview")
    public WebElement search_history_btn;

    //com.tronlinkpro.wallet:id/iv_scan
    @FindBy(id = "com.tronlinkpro.wallet:id/iv_scan")
    public WebElement qr_scan_btn;

    //com.tronlinkpro.wallet:id/tv_search
    @FindBy(id = "com.tronlinkpro.wallet:id/tv_search")
    public WebElement search_icon_in_searchPage_btn;

    //com.tronlinkpro.wallet:id/tv_name
    @FindBy(id = "com.tronlinkpro.wallet:id/tv_name")
    public WebElement nile_discover_note;

    public DAPP_SearchPage enterDAPP_SearchPage(){
        try {
            search_btn.click();
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){
            System.out.println(e);
        }
        return new DAPP_SearchPage(driver);
    }






}
