package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MnemonicToolsPage extends AbstractPage {

    public IOSDriver<?> driver;

    public MnemonicToolsPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(name = "助记词转换工具")
    public WebElement title;

    @FindBy(id = "black path")
    public WebElement back_btn;

    @FindBy(className = "XCUIElementTypeTextView")
    public  WebElement textview;

    @FindBy(name = "一键转换")
    public WebElement swtch_btn;
    @FindBy(name = "4a801bcb60323224030c176f57e08ccfcb33e95fc3826d6b875b67673cb52b63")
    public WebElement assetExpectText;


    public void   switchMnems() throws Exception {
        textview.sendKeys("broken mind human shrimp bulk margin engage uncle shop panel list risk blame affair wage breeze oblige green nest affair dad report kitchen cycle");
        swtch_btn.click();
    }

}
