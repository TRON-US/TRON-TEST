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

    @FindBy(id =  "TRON 主网")
    public WebElement MainChain_btn;

    @FindBy(id =  "DAppChain 主网")
    public WebElement DAppChain_btn;

    @FindBy(id =  "TRON Shasta 测试网")
    public WebElement Shasta_btn;

    @FindBy(id = "node setting select normal")
    public List<WebElement> normal_list;

    @FindBy(id = "node setting select selected")
    public WebElement select_btn;

    @FindBy(id = "node setting select normal")
    public WebElement normal_first;

    @FindBy(xpath = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTable[1]/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[1]")
    public WebElement xMainChain;

    @FindBy(xpath = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTable[1]/XCUIElementTypeCell[2]/XCUIElementTypeStaticText[1]")
    public WebElement xShasta;

    @FindBy(xpath = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTable[1]/XCUIElementTypeCell[3]/XCUIElementTypeStaticText[1]")
    public WebElement xDappChain;
//    @FindBy(className = "XCUIElementTypeButton")
//    public List<WebElement> buttonArray;  //原来有5个后来定位有时候顺序不一致

    public NodeSetDetailPage enterSettingPageShasta() throws Exception {
        xShasta.click();
        return new NodeSetDetailPage(driver);
    }


    public NodeSetDetailPage enterSettingPageDAppChain() throws Exception {
        xDappChain.click();
        return new NodeSetDetailPage(driver);
    }

    public NodeSetDetailPage enterSettingPageMainChain() {
        xMainChain.click();
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

    public SettingPage enterSettingPageChoiseShastahain() throws Exception {

        int normal = normal_first.getLocation().getY();
        int selected = select_btn.getLocation().getY();
        if (selected < normal ){
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
