package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BrowserPage extends AbstractPage {

    public IOSDriver<?> driver;

    public BrowserPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "dapp topNav more white")
    public WebElement menuBtn;

    @FindBy(id = "dapp topNav more")
    public WebElement dAppMenuBtn;

    public void openMenu(){
        menuBtn.click();
    }
    @FindBy(name = "搜索名称或输入网址")
    public WebElement inputText;

    @FindBy(xpath = "//XCUIElementTypeTable[@name=\"搜索名称或输入网址, 开始您的探索吧\"]/XCUIElementTypeOther[2]/XCUIElementTypeButton")
    public WebElement searchEntrance;

    @FindBy(xpath = "//XCUIElementTypeTable[@name=\"空列表\"]/XCUIElementTypeOther[2]/XCUIElementTypeButton")
    public WebElement searchSenEntrance;

    @FindBy(className = "XCUIElementTypeTextField")
    public WebElement inputTextField;

    @FindBy(name = "SUN")
    public WebElement SUN;

    @FindBy(name = "立即进入")
    public WebElement interImmediately;

    @FindBy(name = "新标签页")
    public WebElement itemNewTab;

    @FindBy(name = "浏览记录")
    public WebElement itemHistoryTab;

    @FindBy(name = "收藏夹")
    public WebElement itemFavoriteTab;

    @FindBy(name = "收藏")
    public WebElement itemAddFavoriteTab;

    @FindBy(name = "切换钱包")
    public WebElement itemSwitchTab;

    @FindBy(name = "连接管理")
    public WebElement itemConnectTab;

    @FindBy(name = "SUN")
    public WebElement SUNi;

    @FindBy(name = "允许")
    public WebElement approve;

    @FindBy(xpath = "//XCUIElementTypeApplication[@name=\"TronLink\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell")
    public WebElement firstDAppItem;

    @FindBy(xpath = "//XCUIElementTypeApplication[@name=\"TronLink\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]")
    public WebElement webUrlPath;

    @FindBy(className = "XCUIElementTypeCell")
    public List<WebElement> cells;

    @FindBy(id = "dapp topNav home")
    public WebElement dAppHome;

    @FindBy(id = "dapp topNav back")
    public WebElement dAppBack;

    @FindBy(id = "browser manage bottom clear")
    public WebElement bottomClear;

    @FindBy(id = "browser manage bottom add")
    public WebElement bottomAdd;

    @FindBy(id = "browser manage bottom back")
    public WebElement bottomBack;

    @FindBy(id = "import wallet change address c")
    public WebElement closeSwitchWallet;

    //not use ios not this func,but need open setting in iphone
    public void openDebugModel() throws Exception{
        inputText.click();
        TimeUnit.SECONDS.sleep(1);
        inputTextField.sendKeys("sun.io");
        TimeUnit.SECONDS.sleep(3);
        SUNi.click();
        interImmediately.click();
        TimeUnit.SECONDS.sleep(1);
        menuBtn.click();
    }

    public void inputSearch(String text) throws Exception{
        searchEntrance.click();
        TimeUnit.SECONDS.sleep(2);
        inputTextField.sendKeys(text);
        TimeUnit.SECONDS.sleep(2);
    }

    public void inputSenSearch(String text) throws Exception{
        searchSenEntrance.click();
        TimeUnit.SECONDS.sleep(2);
        inputTextField.sendKeys(text);
        TimeUnit.SECONDS.sleep(2);
    }


    public void visitTheWeb() throws Exception{
        firstDAppItem.click();
        firstEnterAWeb();
    }

    public void visitTheWWWWeb() throws Exception{
        webUrlPath.click();
        firstEnterAWeb();
    }

    public void firstEnterAWeb() throws Exception{
        if (isElementExist("立即进入")){
            interImmediately.click();
        }
        TimeUnit.SECONDS.sleep(5);
        if (isElementExist("允许")){
            approve.click();
        }
    }


    public void openNewTab(){
        openMenu();
        itemNewTab.click();
    }

    public void openTab(String name){
       driver.findElementByName(name).click();
    }

    public void backHome(){
        dAppHome.click();
    }

    public void openHistory(){
        menuBtn.click();
        itemHistoryTab.click();
    }

    public void openFavorites(){
        dAppMenuBtn.click();
        itemFavoriteTab.click();
    }

    public void favoritesBackToWeb(){
        bottomBack.click();
    }

    public void addFavorite() throws Exception{
        dAppMenuBtn.click();
        itemAddFavoriteTab.click();
        TimeUnit.SECONDS.sleep(3);
    }

    public void openSwitchWallet() throws Exception{
        menuBtn.click();
        itemSwitchTab.click();
        TimeUnit.SECONDS.sleep(3);
    }

    public void closeSwitchWallet(){
        closeSwitchWallet.click();
    }

    public void openConnectManage(){
        menuBtn.click();
        itemConnectTab.click();
    }
}