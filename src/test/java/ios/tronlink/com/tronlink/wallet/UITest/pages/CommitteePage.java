package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommitteePage extends AbstractPage {

    public IOSDriver<?> driver;

    public CommitteePage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(className = "XCUIElementTypeTextField")
    public List<WebElement> textfieldList;

    @FindBy(className = "XCUIElementTypeSecureTextField")
    public  WebElement passwordTF;

    @FindBy(className = "XCUIElementTypeCell")
    public  List<WebElement> proposCells;

    @FindBy(id = "myProposalsTitle")
    public  WebElement mysetuppropos;

    @FindBy(id = "agreedProposalsTitle")
    public  WebElement myagreedpropos;

    @FindBy(name = "发起提议")
    public  WebElement Setuppropos;


    @FindBy(id = "my committee touch 6")
    public WebElement outagreeBtn; //可以点击没有同意的

    @FindBy(id = "my committee touch 5")
    public WebElement outagreedBtn; //可以点击没有同意的


    @FindBy(id = "赞成")
    public WebElement agreeBtn;

    @FindBy(id = "取消赞成")
    public WebElement disagreeBtn;

    @FindBy(id = "撤销")
    public WebElement deleteBtn;

    @FindBy(id = "black path")
    public WebElement backBtn;

    public void change1proposal(String pro){
        try {
            TimeUnit.SECONDS.sleep(2);
            textfieldList.get(1).clear();
            textfieldList.get(1).sendKeys(pro);
            Helper.tapWhitePlace(driver);
            TimeUnit.SECONDS.sleep(1);
            driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '确认'").click();
            TimeUnit.SECONDS.sleep(3);
            passwordTF.sendKeys("Test0001");
            driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '完成'").click();
            TimeUnit.SECONDS.sleep(3);
            //XCUIElementTypeButton name
            //XCUIElementTypeStaticText

        }catch (Exception e){

        }
    }

    public WebElement findFirstproposalWl( ) throws Exception {
         TimeUnit.SECONDS.sleep(5);
            enterMyProposal();
            List<WebElement> wls = (List<WebElement>) driver.findElementsByClassName("XCUIElementTypeCell");
            return wls.get(0);
    }
    public WebElement findFirstAgreedroposalWl( ) throws Exception {
        TimeUnit.SECONDS.sleep(5);
        enterMyAgreedProposal();
        List<WebElement> wls = (List<WebElement>) driver.findElementsByClassName("XCUIElementTypeCell");
        return wls.get(0);
    }

    public void enterMyProposal() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        mysetuppropos.click();
    }

    public void enterMyAgreedProposal() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        myagreedpropos.click();
    }

    public void agreeAction() throws Exception {
        enterMyProposal();
        proposCells.get(0).click();
        TimeUnit.SECONDS.sleep(4);
        agreeBtn.click();
        TimeUnit.SECONDS.sleep(4);
        passwordTF.sendKeys("Test0001");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '完成'").click();
        TimeUnit.SECONDS.sleep(3);
        backBtn.click();
    }

    public void disagreeAction() throws Exception {
        enterMyAgreedProposal();
        proposCells.get(0).click();
        TimeUnit.SECONDS.sleep(4);
        disagreeBtn.click();
        TimeUnit.SECONDS.sleep(4);
        passwordTF.sendKeys("Test0001");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '完成'").click();
        TimeUnit.SECONDS.sleep(3);
        backBtn.click();
    }

    public void deleteAction() throws Exception {
        enterMyProposal();
        proposCells.get(0).click();
        TimeUnit.SECONDS.sleep(4);
        deleteBtn.click();
        TimeUnit.SECONDS.sleep(2);
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '确认'").click();
        TimeUnit.SECONDS.sleep(2);
        passwordTF.sendKeys("Test0001");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '完成'").click();
        TimeUnit.SECONDS.sleep(3);
        backBtn.click();
    }
}
