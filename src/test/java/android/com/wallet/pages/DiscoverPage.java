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

    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.ActionBar.Tab[1]/android.widget.TextView")
    public WebElement normalUsed;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_web_title")
    public WebElement tv_web_title;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_approve")
    public WebElement tv_approve;

    @FindBy(id = "com.tronlinkpro.wallet:id/ll_common_left")
    public WebElement ll_common_left;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_subtitle")
    public WebElement tv_subtitle;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_title")
    public WebElement dapp_title;


    @FindBy(id = "com.tronlinkpro.wallet:id/iv_browser_history_back")
    public WebElement iv_browser_history_back;

    @FindBy(id = "com.tronlinkpro.wallet:id/middle_title")
    public WebElement middle_title;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_type")
    public WebElement tv_type_title;


    @FindBy(id = "com.tronlinkpro.wallet:id/imageview")
    public WebElement search_history_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_scan")
    public WebElement qr_scan_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_search")
    public WebElement tv_search;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_browser_tab")
    public WebElement tv_browser_tab;

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

    @FindBy(id = "com.tronlinkpro.wallet:id/bt_cancel")
    public WebElement bt_cancel;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_text_hint")
    public WebElement tv_text_hint;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_address")
    public WebElement tv_address;

    @FindBy(id = "com.tronlinkpro.wallet:id/li_browser_newtab")
    public WebElement li_browser_newtab;


    @FindBy(id = "com.tronlinkpro.wallet:id/li_browser_history")
    public WebElement li_browser_history;

    @FindBy(id = "com.tronlinkpro.wallet:id/li_browser_bookmark_package")
    public WebElement li_browser_bookmark_package;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_no_data")
    public WebElement tv_no_data;


    @FindBy(id = "com.tronlinkpro.wallet:id/iv_browser_bookmark_back")
    public WebElement iv_browser_bookmark_back;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_tab_snapshot")
    public List<WebElement> iv_tab_snapshot;

    @FindBy(id = "com.tronlinkpro.wallet:id/sdv_image")
    public List<WebElement> sdv_image;


    @FindBy(id = "com.tronlinkpro.wallet:id/li_browser_switch_wallet")
    public WebElement li_browser_switch_wallet;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_wallet_name")
    public WebElement tv_wallet_name;


    @FindBy(id = "com.tronlinkpro.wallet:id/li_browser_dapp_link_manager")
    public WebElement li_browser_dapp_link_manager;

    public void openSwitchWallet() throws Exception{
        iv_menu.click();
        li_browser_switch_wallet.click();
        TimeUnit.SECONDS.sleep(1);

    }


    public void openConnectManage() throws Exception{
        iv_menu.click();
        li_browser_dapp_link_manager.click();
        TimeUnit.SECONDS.sleep(1);
    }



    public void openNewTab(){
        iv_menu.click();
        li_browser_newtab.click();
    }

    public void historyBackToWeb() throws Exception{
        iv_browser_history_back.click();
        TimeUnit.SECONDS.sleep(1);
    }
    public void favoritesBackToWeb() throws Exception{
        iv_browser_bookmark_back.click();
        TimeUnit.SECONDS.sleep(1);
    }


    public void inputSearch(String text) throws Exception{
        TimeUnit.SECONDS.sleep(2);
        tv_search.click();
        TimeUnit.SECONDS.sleep(2);
        et_search.sendKeys(text);
        TimeUnit.SECONDS.sleep(1);

    }

    public void visitTheWeb() throws Exception{
        tv_address.click();
        firstEnterAWeb();
        TimeUnit.SECONDS.sleep(3);
    }


    public void openFavorites() throws Exception{
        iv_menu.click();
        li_browser_bookmark_package.click();
        TimeUnit.SECONDS.sleep(1);
    }

    public void openHistory() throws Exception{
        iv_menu.click();
        li_browser_history.click();
        TimeUnit.SECONDS.sleep(1);
    }

    public void openTabs() throws Exception{
        tv_browser_tab.click();
        TimeUnit.SECONDS.sleep(1);
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
//        findElementByText("其他").click();
        findElementByText("APENFT").click();
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
        TimeUnit.SECONDS.sleep(5);
    }

    public void gotoBrowserMainPage(){
        iv_browser_main.click();
    }

//    tv_search




}
