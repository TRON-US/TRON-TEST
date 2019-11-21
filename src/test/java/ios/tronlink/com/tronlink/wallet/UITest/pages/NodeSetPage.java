package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class NodeSetPage extends AbstractPage {

    public IOSDriver<?> driver;

    public NodeSetPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "black path")
    public WebElement back_btn;

    @FindBy(name = "设置")
    public WebElement title;

    @FindBy(name =  "MainChain")
    public WebElement MainChain_btn;

    @FindBy(name =  "DAppChain")
    public WebElement DAppChain_btn;


    @FindBy(className = "XCUIElementTypeButton")
    public List<WebElement> buttonArray;



    public NodeSetDetailPage enterSettingPageDAppChain() throws Exception {
        DAppChain_btn.click();
        return new NodeSetDetailPage(driver);
    }

    public NodeSetDetailPage enterSettingPageMainChain() throws Exception {
        MainChain_btn.click();
        return new NodeSetDetailPage(driver);
    }


    public SettingPage enterSettingPageChoiseDappChain() throws Exception {
        buttonArray.get(3).click();
        back_btn.click();
        return new SettingPage(driver);
    }


    public SettingPage enterSettingPageChoiseMainChain() throws Exception {
        buttonArray.get(1).click();
        back_btn.click();
        return new SettingPage(driver);
    }



}
