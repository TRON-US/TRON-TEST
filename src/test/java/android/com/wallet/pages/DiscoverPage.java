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

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_menu")
    public WebElement iv_menu;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_one")
    public WebElement search_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_ok")
    public WebElement tv_ok;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_approve")
    public WebElement tv_approve;

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

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_search")
    public WebElement search_icon_in_searchPage_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_search")
    public WebElement tv_search;


    //com.tronlinkpro.wallet:id/tv_name
    @FindBy(id = "com.tronlinkpro.wallet:id/tv_name")
    public WebElement nile_discover_note;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_browser_bookmark")
    public WebElement tv_browser_bookmark;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_browser_debug")
    public WebElement tv_browser_debug;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_browser_main")
    public WebElement iv_browser_main;

    @FindBy(id = "com.tronlinkpro.wallet:id/et_search")
    public WebElement et_search;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_address")
    public WebElement tv_address;

    public void inputSearch(String text) throws Exception{
        tv_search.click();
        TimeUnit.SECONDS.sleep(1);
        et_search.sendKeys(text);
        TimeUnit.SECONDS.sleep(1);

    }

    public void visitTheWeb() throws Exception{
        tv_address.click();
        firstEnterAWeb();
    }

    public void addFavorite() throws Exception{
        iv_menu.click();
        tv_browser_bookmark.click();
        TimeUnit.SECONDS.sleep(2);

    }

    public DAPP_SearchPage enterDAPP_SearchPage(){
        try {
            search_btn.click();
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){
            System.out.println(e);
        }
        return new DAPP_SearchPage(driver);
    }

    public void openDebugModel() throws Exception{
        findElementByText("其他").click();
        findElementByText("SUN").click();
        TimeUnit.SECONDS.sleep(2);
        firstEnterAWeb();
        iv_menu.click();
        tv_browser_debug.click();
        TimeUnit.SECONDS.sleep(3);
    }

    public void firstEnterAWeb() throws Exception{
        if (isElementExist("tv_ok")){
            tv_ok.click();
            TimeUnit.SECONDS.sleep(2);
        }
        if (isElementExist("tv_approve")){
            tv_approve.click();
        }
    }

    public void gotoBrowserMainPage(){
        iv_browser_main.click();
    }

//    tv_search




}
