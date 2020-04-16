package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class QRCodePage extends AbstractPage {
    public IOSDriver<?> driver;

    public QRCodePage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "请用冷钱包扫描下方二维码")
    public WebElement scanTitle;

    @FindBy(name = "冷钱包已扫描，下一张")
    public WebElement nextBtn;


    @FindBy(name = "冷钱包已签名")
    public WebElement overBtn;


}
