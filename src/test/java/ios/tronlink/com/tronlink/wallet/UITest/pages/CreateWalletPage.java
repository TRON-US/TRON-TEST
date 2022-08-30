package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 关于我们页面
 */
public class CreateWalletPage extends AbstractPage {

    public IOSDriver<?> driver;

    public CreateWalletPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
        waiteTime();
    }

    @FindBy(name = "创建钱包")
    public WebElement createButton;

    @FindBy(name = "切换冷钱包模式")
    public WebElement switchTypeToCold;

    @FindBy(name = "导入钱包")
    public WebElement importButton;

    @FindBy(name = "观察钱包")
    public WebElement watchButton;

    @FindBy(name = "Ledger")
    public WebElement ledgerButton;

    @FindBy(name = "我知道了")
    public WebElement iKnowButton;

    @FindBy(id = "policy back")
    public WebElement policyBack;

    @FindBy(id = "policy browse")
    public WebElement policyBrowse;

    @FindBy(className = "XCUIElementTypeSecureTextField")
    public WebElement passwordTF;

    @FindBy(id = "walletName")
    public WebElement walletName;

    @FindBy(id = "contentLabel")
    public WebElement contentLabel;



    public void SwitchColdWallet(){
        switchTypeToCold.click();
    }

    public void CloseTips(){
        iKnowButton.click();
    }

    public void enterCreate() throws Exception{
        createButton.click();
        TimeUnit.SECONDS.sleep(1);
    }
    public void policyBack(){
        policyBack.click();
    }

    public void createWalletAction() throws Exception{
        driver.findElementByIosClassChain("**/XCUIElementTypeButton[`label == \"创建钱包\"`]").click();
        TimeUnit.SECONDS.sleep(3);
    }
    public void inputCreatePassword() throws Exception{
        List<WebElement> passwordTfs = (List<WebElement>) driver.findElementsByClassName("XCUIElementTypeSecureTextField");
        for (int i = 0; i < passwordTfs.size(); i++) {
            passwordTfs.get(i).sendKeys("Test0001");
            closeKeyBoard();
        }
        TimeUnit.SECONDS.sleep(1);
    }

    public void backUpWalletAction(){
        driver.findElementByIosClassChain("**/XCUIElementTypeButton[`label == \"备份钱包\"`]").click();
    }

    public void beginBackUpWalletAction() throws Exception{
        driver.findElementByIosClassChain("**/XCUIElementTypeButton[`label == \"开始备份\"`]").click();
        TimeUnit.SECONDS.sleep(3);
    }

    public void showWordsAction(){
        driver.findElementByIosClassChain("**/XCUIElementTypeButton[`label == \"显示助记词\"`]").click();
    }






}
