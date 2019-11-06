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

    @FindBy(id = "com.tronlink.wallet:id/et_search")
    public WebElement addAssert_input;


    @FindBy(id = "com.tronlink.wallet:id/iv_switch")
    public WebElement assertSatus_btn;


    @FindBy(id = "com.tronlink.wallet:id/iv_common_left")
    public WebElement back_btn;


    @FindBy(id = "com.tronlink.wallet:id/tv_tab_title")
    public List<WebElement> mainPageAssetManage_tab;


    @FindBy(id = "com.tronlink.wallet:id/iv_switch")
    public List<WebElement> switch_btn;


    @FindBy(id = "com.tronlink.wallet:id/iv_switch")
    public WebElement switchFirst_btn;


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
