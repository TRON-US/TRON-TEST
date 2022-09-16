package android.com.wallet.pages;

import io.appium.java_client.android.AndroidDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * 行情页
 */
public class MarketPage extends AbstractPage {


    public MarketPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
        try {
            TimeUnit.SECONDS.sleep(2);
            // if page display AD , cloese the AD
            if (ad_pic.isDisplayed()){
                adClose_btn.click();
                TimeUnit.SECONDS.sleep(1);
            }
        }catch (Exception e){}

    }


    @FindBy(id = "com.tronlink.global:id/iv_pic")
    public WebElement ad_pic;

    @FindBy(id = "com.tronlink.global:id/tv_volume")
    public WebElement first_vol;

    @FindBy(id = "com.tronlink.global:id/tv_usd_price")
    public WebElement tv_usd_price;


    @FindBy(id = "com.tronlink.global:id/iv_close")
    public WebElement adClose_btn;


    @FindBy(id = "com.tronlink.global:id/tv_market")
    public WebElement Market_title;

    @FindBy(id = "com.tronlink.global:id/tv_price")
    public WebElement price_btn;

    @FindBy(id = "com.tronlink.global:id/tv_price")
    public List<WebElement> firstRowPriceList;


    @FindBy(id = "com.tronlink.global:id/tv_change")
    public WebElement riseChange_btn;

    @FindBy(id = "com.tronlink.global:id/tv_top_name")
    public WebElement tv_top_name;

    @FindBy(id = "com.tronlink.global:id/iv_delete")
    public WebElement iv_delete;

    @FindBy(id = "com.tronlink.global:id/tv_statue")
    public WebElement firstRise;

    @FindBy(id = "com.tronlink.global:id/iv_search")
    public WebElement market_search_btn;

    @FindBy(id = "com.tronlink.global:id/market_vol")
    public WebElement market_vol_btn;


    @FindBy(id = "com.tronlink.global:id/tv_price")
    public List<WebElement> priceList;

    @FindBy(id = "com.tronlink.global:id/et_search")
    public WebElement search_TF;

    @FindBy(id = "com.tronlink.global:id/tv_search")
    public WebElement search_after_btn;


    //com.tronlink.global:id/tv_statue
    @FindBy(id = "com.tronlink.global:id/tv_statue")
    public List<WebElement> rangeList;







}
