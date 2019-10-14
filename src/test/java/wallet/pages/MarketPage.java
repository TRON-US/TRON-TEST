package wallet.pages;

import io.appium.java_client.android.AndroidDriver;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * 行情页
 */
public class MarketPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public MarketPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

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
