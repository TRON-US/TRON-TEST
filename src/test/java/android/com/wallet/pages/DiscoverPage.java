package android.com.wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
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

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_ok")
    public WebElement tv_ok;

    @FindBy(id = "com.tronlinkpro.wallet:id/ll_close")
    public WebElement ll_close;

    @FindBy(id = "com.tronlinkpro.wallet:id/ll_common_left")
    public WebElement ll_common_left;


    @FindBy(id = "com.tronlinkpro.wallet:id/url_entrance_view")
    public WebElement url_entrance_view;

    //com.tronlinkpro.wallet:id/tv_title
    @FindBy(id = "com.tronlinkpro.wallet:id/tv_title")
    public WebElement dapp_title;

    @FindBy(id = "com.tronlinkpro.wallet:id/title")
    public WebElement hot_title;

    @FindBy(id = "com.tronlinkpro.wallet:id/middle_title")
    public WebElement middle_title;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_type")
    public WebElement tv_type_title;


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

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_name")
    public List<WebElement> hotDapp_names;

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
