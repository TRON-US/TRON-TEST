package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddAssertPage extends AbstractPage {


    public IOSDriver<?> driver;


    public AddAssertPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(name = "token名称/token ID/智能合约地址")
    public WebElement addAssert_input;

    @FindBy(name = "testAssetIssue_1567077083240 (), 0, ID 1000027")
    public WebElement turnAsset_btn;


    @FindBy(name = "white back arrow")
    public WebElement back_btn;

    @FindBy(name = "资产")
    public WebElement title;


    @FindBy(name = "首页资产管理")
    public WebElement mainPageAssetManage_tab;

    @FindBy(name = "热门资产")
    public WebElement hotAssetManage_tab;



    @FindBy(name = "ID 1000027")
    public WebElement myNewAddAsset_text;


    @FindBy(className =  "XCUIElementTypeSwitch")
    public List<WebElement> assertSwitch_btn;



    @FindBy(id = "com.tronlink.wallet:id/iv_switch")
    public List<WebElement> switch_btn;



    @FindBy(xpath = "//*[@text='ID 1000029']")
    public WebElement myNewAddAsset_btn;

    @FindBy(id = "com.tronlink.wallet:id/iv_switch")
    public WebElement switchFirst_btn;

    @FindBy(className = "XCUIElementTypeSwitch")
    public List<WebElement> switchArr;


    public SearchAssertPage enterSearchAssertPage(){
        addAssert_input.click();
        return new SearchAssertPage(driver);
    }



    //enter Asset Page
    public AssetPage enterAssetPage(){
        back_btn.click();
        return new AssetPage(driver);
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
    /**
     * turn off assert(close)
     */
    public void cloeAssert(){
        try {
            TimeUnit.SECONDS.sleep(1);
            switchArr.get(switchArr.size() - 1).click();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
