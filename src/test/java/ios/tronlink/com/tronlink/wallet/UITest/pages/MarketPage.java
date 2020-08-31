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
    @FindBy(name = "市场")
    public WebElement title;


    @FindBy(id = "市场")
    public WebElement market_Tab_Button;


    @FindBy(name = "最新价格")
    public WebElement newPrice_btn;

    @FindBy(id = "自选")
    public WebElement my_Pair;

    @FindBy(id = "TRX")
    public WebElement trx_Pair;

    @FindBy(id = "USDT")
    public WebElement usdt_Pair;

    @FindBy(name = "涨跌幅")
    public WebElement updown_OrderBtn;

    @FindBy(name = "最新价格")
    public WebElement newlest_OrderBtn;

    @FindBy(name = "交易对/成交量")
    public WebElement amount_OrderBtn;

    @FindBy(name = "长按交易对添加自选")
    public WebElement noPair_Text;

    @FindBy(id = "market new search")
    public WebElement market_Search;

    @FindBy(name = "/TRX")
    public List<WebElement> trxlist;

    @FindBy(name = "/USDT")
    public List<WebElement> usdtlist;






}
