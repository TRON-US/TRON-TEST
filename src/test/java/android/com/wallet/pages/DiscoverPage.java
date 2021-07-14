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

    @FindBy(id = "wallet.tronlink.harmony:id/iv_one")
    public WebElement search_btn;

    @FindBy(id = "wallet.tronlink.harmony:id/tv_ok")
    public WebElement tv_ok;

    @FindBy(id = "wallet.tronlink.harmony:id/ll_close")
    public WebElement ll_close;

    @FindBy(id = "wallet.tronlink.harmony:id/ll_common_left")
    public WebElement ll_common_left;


    @FindBy(id = "wallet.tronlink.harmony:id/url_entrance_view")
    public WebElement url_entrance_view;

    //wallet.tronlink.harmony:id/tv_title
    @FindBy(id = "wallet.tronlink.harmony:id/tv_title")
    public WebElement dapp_title;

    @FindBy(id = "wallet.tronlink.harmony:id/title")
    public WebElement hot_title;

    @FindBy(id = "wallet.tronlink.harmony:id/middle_title")
    public WebElement middle_title;

    @FindBy(id = "wallet.tronlink.harmony:id/tv_type")
    public WebElement tv_type_title;


    //wallet.tronlink.harmony:id/imageview
    @FindBy(id = "wallet.tronlink.harmony:id/imageview")
    public WebElement search_history_btn;

    //wallet.tronlink.harmony:id/iv_scan
    @FindBy(id = "wallet.tronlink.harmony:id/iv_scan")
    public WebElement qr_scan_btn;

    //wallet.tronlink.harmony:id/tv_search
    @FindBy(id = "wallet.tronlink.harmony:id/tv_search")
    public WebElement search_icon_in_searchPage_btn;

    //wallet.tronlink.harmony:id/tv_name
    @FindBy(id = "wallet.tronlink.harmony:id/tv_name")
    public WebElement nile_discover_note;

    @FindBy(id = "wallet.tronlink.harmony:id/tv_name")
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
