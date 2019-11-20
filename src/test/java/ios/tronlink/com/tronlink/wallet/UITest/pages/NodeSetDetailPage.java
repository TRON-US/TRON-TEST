package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class NodeSetDetailPage extends AbstractPage {

    public IOSDriver<?> driver;

    public NodeSetDetailPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(name = "节点设置")
    public WebElement title;

    @FindBy(id = "black path")
    public WebElement back_btn;


    public NodeSetPage backToAction() throws Exception {
        back_btn.click();
        return new NodeSetPage(driver);
    }





}
