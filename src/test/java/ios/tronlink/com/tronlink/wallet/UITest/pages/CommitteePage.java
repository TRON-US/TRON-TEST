package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
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

    @FindBy(id = "nodeNameLabel")
    public WebElement nodeNameLabel;

    @FindBy(id = "stateLabel")
    public WebElement stateLabel;


    @FindBy(id = "createTimeValue")
    public WebElement createTimeValue;

    @FindBy(id = "deadlineTimeValue")
    public WebElement deadlineTimeValue;

    @FindBy(id = "totalVoteValue")
    public WebElement totalVoteValue;


    public void change1proposal(String pro) throws Exception{
            TimeUnit.SECONDS.sleep(2);
            textfieldList.get(1).clear();
            textfieldList.get(1).clear();
            textfieldList.get(1).sendKeys("");
            textfieldList.get(1).sendKeys(pro);
            Helper.tapWhitePlace(driver);
            waiteTime();
            driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '确认'").click();
            TimeUnit.SECONDS.sleep(6);
            passwordTF.sendKeys("Test0001");
            driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '完成'").click();
            TimeUnit.SECONDS.sleep(3);
            //XCUIElementTypeButton name
            //XCUIElementTypeStaticText

    }
    public void change2proposal(String pro) throws Exception{
        waiteTime();
        textfieldList.get(2).clear();
        textfieldList.get(2).clear();
        textfieldList.get(2).sendKeys("");
        textfieldList.get(2).sendKeys(pro);
        Helper.tapWhitePlace(driver);
        waiteTime();
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '确认'").click();
        TimeUnit.SECONDS.sleep(6);
        passwordTF.sendKeys("Test0001");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '完成'").click();
        TimeUnit.SECONDS.sleep(3);
        //XCUIElementTypeButton name
        //XCUIElementTypeStaticText

    }
    public void change0proposal(String pro) throws Exception{
        waiteTime();
        textfieldList.get(0).clear();
        textfieldList.get(0).clear();
        textfieldList.get(0).sendKeys("");
        textfieldList.get(0).sendKeys(pro);
        Helper.tapWhitePlace(driver);
        waiteTime();
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '确认'").click();
        TimeUnit.SECONDS.sleep(6);
        passwordTF.sendKeys("Test0001");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '完成'").click();
        TimeUnit.SECONDS.sleep(3);
        //XCUIElementTypeButton name
        //XCUIElementTypeStaticText

    }
    public String getNameofproposal() throws Exception {
        enterProposalDetail();
        return nodeNameLabel.getText();
    }
    public String getStateofproposal() throws Exception {
        enterProposalDetail();
        return stateLabel.getText();
    }
    public boolean cheacktimeorderofproposal() throws Exception{
        enterProposalDetail();
        if(deadlineTimeValue.getText().compareTo(createTimeValue.getText()) > 0){
            return  true;
        }else {
            return  false;
        }
    }


    public WebElement findFirstproposalWl( ) throws Exception {
            TimeUnit.SECONDS.sleep(3);
            log("try 进入我发起的提议");
            enterMyProposal();
            log("success 进入我发起的提议");
            waiteTime();
            List<WebElement> wls = (List<WebElement>) driver.findElementsByClassName("XCUIElementTypeCell");
            return wls.get(0);
    }
    public WebElement findFirstAgreedroposalWl( ) throws Exception {
        TimeUnit.SECONDS.sleep(3);
        enterMyAgreedProposal();
        List<WebElement> wls = (List<WebElement>) driver.findElementsByClassName("XCUIElementTypeCell");
        return wls.get(0);
    }

    public int findvoteNumbers() throws Exception {
        waiteTime();
        findFirstAgreedroposalWl().click();
        TimeUnit.SECONDS.sleep(3);
        return  Integer.parseInt(totalVoteValue.getText());
    }
    public int findvoteafterNumbers() throws Exception {
        waiteTime();
        findFirstproposalWl().click();
        TimeUnit.SECONDS.sleep(3);
        return  Integer.parseInt(totalVoteValue.getText());
    }
    public boolean getagreedStateofproposal() throws Exception {
        waiteTime();
        findFirstAgreedroposalWl().click();
        TimeUnit.SECONDS.sleep(4);
        return disagreeBtn.isDisplayed();
    }

    public boolean getdisagreedStateofproposal() throws Exception {
        waiteTime();
        findFirstproposalWl().click();
        TimeUnit.SECONDS.sleep(4);
        return agreeBtn.isDisplayed();
    }

    public void enterProposalDetail() throws Exception {
        WebElement wl = findFirstproposalWl();
        wl.click();
        TimeUnit.SECONDS.sleep(4);
    }


    public void enterMyProposal() throws Exception {
        waiteTime();
        int topX = mysetuppropos.getLocation().x + 10;
        int topY = mysetuppropos.getLocation().y + 10;
        log("\n topY: " + topX + " botY: " + topY );
        IOSTouchAction action = new IOSTouchAction(driver);
        System.out.println("start");
        action.tap(PointOption.point(topX,topY)).perform();
        System.out.println("end");
//        mysetuppropos.click();

    }

    public void enterMyAgreedProposal() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        int topX = myagreedpropos.getLocation().x + 10;
        int topY = myagreedpropos.getLocation().y + 10;
        log("\n topY: " + topX + " botY: " + topY );
        IOSTouchAction action = new IOSTouchAction(driver);
        System.out.println("start");
        action.tap(PointOption.point(topX,topY)).perform();
        System.out.println("end");
        TimeUnit.SECONDS.sleep(4);

    }

    public void agreeAction() throws Exception {
        enterMyProposal();
        waiteTime();
        proposCells.get(0).click();
        waiteTime();
        agreeBtn.click();
        TimeUnit.SECONDS.sleep(6);
        passwordTF.sendKeys("Test0001");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '完成'").click();
        TimeUnit.SECONDS.sleep(3);
        backBtn.click();
    }

    public void disagreeAction() throws Exception {
        enterMyAgreedProposal();
        proposCells.get(0).click();
        waiteTime();
        disagreeBtn.click();
        TimeUnit.SECONDS.sleep(6);
        passwordTF.sendKeys("Test0001");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '完成'").click();
        TimeUnit.SECONDS.sleep(3);
        backBtn.click();
    }

    public void deleteAction() throws Exception {
        enterMyProposal();
        waiteTime();
        proposCells.get(0).click();
        waiteTime();
        deleteBtn.click();
        TimeUnit.SECONDS.sleep(2);
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '确认'").click();
        TimeUnit.SECONDS.sleep(6);
        passwordTF.sendKeys("Test0001");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '完成'").click();
        TimeUnit.SECONDS.sleep(3);
        backBtn.click();
    }
}
