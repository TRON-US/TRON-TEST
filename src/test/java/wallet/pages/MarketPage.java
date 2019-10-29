package wallet.pages;

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

    @FindBy(id = "com.tronlink.wallet:id/iv_pic")
    public WebElement ad_pic;


    @FindBy(id = "com.tronlink.wallet:id/iv_close")
    public WebElement adClose_btn;


    @FindBy(id = "com.tronlink.wallet:id/tv_market")
    public WebElement Market_title;

    @FindBy(id = "com.tronlink.wallet:id/tv_price")
    public WebElement newPrice_btn;

    @FindBy(id = "com.tronlink.wallet:id/tv_price")
    public List<WebElement> firstRowPriceList;


    @FindBy(id = "com.tronlink.wallet:id/tv_change")
    public WebElement quoteChange_btn;


    @FindBy(id = "com.tronlink.wallet:id/tv_statue")
    public WebElement firstQuoteChange_list;


    public String sortPrice(){
        firstRowPriceList.get(0).click();
        String price = firstRowPriceList.get(1).getText();
        //System.out.println(price);
        return price;
    }

    public String sortQuoteChange(){
        quoteChange_btn.click();
        String price = firstQuoteChange_list.getText();
        return price;
    }




}
