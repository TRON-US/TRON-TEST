package android.com.wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import io.appium.java_client.android.AndroidDriver;

public class AddAssertPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public AddAssertPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }
    @FindBy(id = "wallet.tronlink.global:id/iv_close")
    public WebElement close;

    @FindBy(id = "wallet.tronlink.global:id/tv_sort_pop")
    public WebElement tipview;

    @FindBy(id = "wallet.tronlink.global:id/et_search")
    public WebElement addAssert_input;

    @FindBy(id = "wallet.tronlink.global:id/iv_search_icon")
    public WebElement iv_search_icon;

    @FindBy(id = "wallet.tronlink.global:id/et_search")
    public WebElement et_search;

    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.support.v7.app.ActionBar.Tab[1]/android.widget.TextView")
    public WebElement firstTab;

    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.support.v7.app.ActionBar.Tab[2]/android.widget.TextView")
    public WebElement secondTab;

    @FindBy(id = "wallet.tronlink.global:id/iv_common_left")
    public WebElement back_btn;

    @FindBy(id = "wallet.tronlink.global:id/tv_sort_type")
    public WebElement sort_type;

    @FindBy(id = "wallet.tronlink.global:id/tv_tab_title")
    public List<WebElement> mainPageAssetManage_tab;


    @FindBy(id = "wallet.tronlink.global:id/iv_switch")
    public List<WebElement> switch_btn;

    @FindBy(id = "wallet.tronlink.global:id/assets_count")
    public List<WebElement> accountnumbers;


    @FindBy(id = "wallet.tronlink.global:id/tv_common_title")
    public WebElement tv_common_title;


    @FindBy(xpath = "//*[@text='ID 1000029']")
    public WebElement myNewAddAsset_btn;


    public AssetPage enterAssetPage(){
        //driver.navigate().back();
        back_btn.click();
        return new AssetPage(driver);
    }


    public SearchAssertPage enterSearchAssertPage(){
        addAssert_input.click();
        return new SearchAssertPage(driver);
    }


    public void removeAsset(){
        try {
            for(int i = 0;i < switch_btn.size(); i++){
                switch_btn.get(i).click();
            }
        }catch (Exception e){
            System.out.println("no assets can remove, skip");
        }

    }

    public int getSwitchBtnCount(){
        int count = switch_btn.size();
        return count;
    }




}
