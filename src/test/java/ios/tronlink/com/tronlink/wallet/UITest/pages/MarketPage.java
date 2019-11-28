package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 行情页
 */
public class MarketPage extends AbstractPage {

    public IOSDriver<?> driver;

    public MarketPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }
    @FindBy(name = "行情")
    public WebElement title;

    @FindBy(name = "com.tronlink.wallet:id/iv_pic")
    public WebElement ad_pic;

    @FindBy(name ="com.tronlink.wallet:id/appmarket")
    public WebElement market_btn;

    @FindBy(name = "com.tronlink.wallet:id/iv_close")
    public WebElement adClose_btn;


    @FindBy(name = "com.tronlink.wallet:id/tv_market")
    public WebElement Market_title;

    @FindBy(name = "最新价格")
    public WebElement newPrice_btn;

    @FindBy(name = "com.tronlink.wallet:id/tv_price")
    public List<WebElement> firstRowPriceList;


    @FindBy(name = "com.tronlink.wallet:id/tv_change")
    public WebElement quoteChange_btn;


    @FindBy(name = "com.tronlink.wallet:id/tv_statue")
    public WebElement firstQuoteChange_list;


    public String sortPrice() throws Exception {
        firstRowPriceList.get(0).click();
        String price = firstRowPriceList.get(1).getText();
        TimeUnit.SECONDS.sleep(1);
        //System.out.println(price);
        return price;
    }

    public String sortQuoteChange() throws Exception {
        quoteChange_btn.click();
        String price = firstQuoteChange_list.getText();
        TimeUnit.SECONDS.sleep(1);
        return price;
    }




}
