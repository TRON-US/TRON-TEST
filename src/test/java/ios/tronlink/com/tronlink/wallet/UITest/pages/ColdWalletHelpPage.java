package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * 关于我们页面
 */
public class ColdWalletHelpPage extends AbstractPage {

    public IOSDriver<?> driver;

    public ColdWalletHelpPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "walletNameLabel")
    public WebElement walletNameLabel;

    @FindBy(id = "recieveLabel")
    public WebElement recieveLabel;

    @FindBy(id = "offlineLabel")
    public WebElement offlineLabel;

    @FindBy(id = "addressLabel")
    public WebElement addressLabel;


    @FindBy(name = "资源")
    public WebElement AssetBtn;

    @FindBy(name = "我")
    public WebElement MineBtn;

    @FindBy(name = "home manager")
    public WebElement AddWalletBtn;

    @FindBy(name = "冷钱包的使用说明")
    public WebElement coldWallettitle;

    @FindBy(name = "私钥导入")
    public WebElement importtitle;

    @FindBy(name = "助记词")
    public WebElement memtitle;

    @FindBy(name = "Keystore")
    public WebElement Keystorettitle;

    @FindBy(name = "创建钱包")
    public WebElement createttitle;


//了解冷钱包  XCUIElementTypeButton

}
