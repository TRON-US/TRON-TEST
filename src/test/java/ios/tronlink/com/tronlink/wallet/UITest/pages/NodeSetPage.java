package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.reporters.jq.Main;

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

    @FindBy(name =  "TRON 主网")
    public WebElement MainChain_btn;

    @FindBy(name =  "DAppChain 主网")
    public WebElement DAppChain_btn;

    @FindBy(name =  "TRON Shasta 测试网")
    public WebElement Shasta_btn;

    @FindBy(id = "node setting select normal")
    public List<WebElement> normal_list;

    @FindBy(id = "node setting select selected")
    public WebElement select_btn;

    @FindBy(id = "node setting select normal")
    public WebElement normal_first;

//    @FindBy(className = "XCUIElementTypeButton")
//    public List<WebElement> buttonArray;  //原来有5个后来定位有时候顺序不一致

    public NodeSetDetailPage enterSettingPageShasta() throws Exception {
        clickOffsetElement(Shasta_btn);
        return new NodeSetDetailPage(driver);
    }


    public NodeSetDetailPage enterSettingPageDAppChain() throws Exception {
        clickOffsetElement(DAppChain_btn);
        return new NodeSetDetailPage(driver);
    }

    public NodeSetDetailPage enterSettingPageMainChain() throws Exception {
        clickOffsetElement(MainChain_btn);
        return new NodeSetDetailPage(driver);
    }


    public SettingPage enterSettingPageChoiseDappChain() throws Exception {
        int normal = normal_first.getLocation().getY();
        int selected = select_btn.getLocation().getY();

        System.out.println("normal: " + normal + " selected: " + selected);
        if (normal > selected){
            normal_list.get(1).click();
            try {
                queding_btn().click();
            }catch (Exception e){
                System.out.println("Already in");
            }
        }
        waiteTime();
        back_btn.click();
        waiteTime();
        return new SettingPage(driver);
    }


    public SettingPage enterSettingPageChoiseMainChain() throws Exception {
        int normal = normal_first.getLocation().getY();
        int selected = select_btn.getLocation().getY();
        if (normal < selected){
            normal_list.get(0).click();
            try {
                queding_btn().click();
            }catch (Exception e){
                System.out.println("Already in");
            }
        }
        waiteTime();
        back_btn.click();
        waiteTime();
        return new SettingPage(driver);
    }



}
