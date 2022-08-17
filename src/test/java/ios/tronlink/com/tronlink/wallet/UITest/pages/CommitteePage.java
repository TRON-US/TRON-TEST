package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import ios.tronlink.com.tronlink.wallet.UITest.base.Base;
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

    @FindBy(xpath = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeButton[2]")
    public WebElement confirmButton;

    public void change1proposal(String pro) throws Exception{
        TimeUnit.SECONDS.sleep(1);
        textfieldList.get(1).clear();
        textfieldList.get(1).sendKeys("");
        textfieldList.get(1).sendKeys(pro);
        closeKeyBoard();
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '确认'").click();
        TimeUnit.SECONDS.sleep(5);
        log("开始找第二个确认");
        confirmButton.click();
        log("开始输入密码");
        passwordTF.sendKeys("Test0001");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '完成'").click();
        TimeUnit.SECONDS.sleep(15);

    }
    public void change2proposal(String pro) throws Exception{
        waiteTime();
        textfieldList.get(2).clear();
        textfieldList.get(2).sendKeys("");
        textfieldList.get(2).sendKeys(pro);
        closeKeyBoard();
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '确认'").click();
        TimeUnit.SECONDS.sleep(15);
        log("开始找第二个确认");
        confirmButton.click();
        passwordTF.sendKeys("Test0001");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '完成'").click();
        TimeUnit.SECONDS.sleep(15);
    }


    public void change0proposal(String pro) throws Exception{
        waiteTime();
        textfieldList.get(0).clear();
        textfieldList.get(0).sendKeys("");
        textfieldList.get(0).sendKeys(pro);
        closeKeyBoard();
        waiteTime();
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '确认'").click();
        TimeUnit.SECONDS.sleep(5);
        confirmButton.click();
        passwordTF.sendKeys("Test0001");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '完成'").click();
        TimeUnit.SECONDS.sleep(15);


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
        TimeUnit.SECONDS.sleep(3);
        waiteTime();
        List<WebElement> wls = (List<WebElement>) driver.findElementsByClassName("XCUIElementTypeCell");
        return wls.get(0);
    }
    public WebElement findFirstAgreedroposalWl( ) throws Exception {
        TimeUnit.SECONDS.sleep(3);
        enterMyAgreedProposal();
        waiteTime();
        List<WebElement> wls = (List<WebElement>) driver.findElementsByClassName("XCUIElementTypeCell");
        return wls.get(0);
    }

    public int findvoteNumbers() throws Exception {
        waiteTime();
        findFirstAgreedroposalWl().click();
        TimeUnit.SECONDS.sleep(5);
        return  Integer.parseInt(totalVoteValue.getText());
    }
    public int findvoteafterNumbers() throws Exception {
        waiteTime();
        findFirstproposalWl().click();
        TimeUnit.SECONDS.sleep(5);
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
        waiteTime();
        WebElement wl = findFirstproposalWl();
        wl.click();
        TimeUnit.SECONDS.sleep(8);
    }


    public void enterMyProposal() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        waiteTime();
        int topX = mysetuppropos.getLocation().x + 10;
        int topY = mysetuppropos.getLocation().y + 10;
        log("\n MyProposal topX: " + topX + " botY: " + topY );
        IOSTouchAction action = new IOSTouchAction(driver);
        action.tap(PointOption.point(topX,topY)).perform();
        log("MyProposal have clicked");

    }

    public void enterMyAgreedProposal() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        waiteTime();
        int topX = myagreedpropos.getLocation().x + 10;
        int topY = myagreedpropos.getLocation().y + 10;
        log("\n AgreedProposal topX: " + topX + " botY: " + topY );
        IOSTouchAction action = new IOSTouchAction(driver);
        action.tap(PointOption.point(topX,topY)).perform();
        log("MyAgreed have clicked");
        TimeUnit.SECONDS.sleep(4);

    }

    public void agreeAction() throws Exception {
        enterMyProposal();
        TimeUnit.SECONDS.sleep(3);
        proposCells.get(0).click();
        TimeUnit.SECONDS.sleep(6);
        if (Helper.isElementExist(driver,"取消赞成")) {
            waiteTime();
            backBtn.click();
            waiteTime();
            backBtn.click();
        }else {
            agreeBtn.click();
            TimeUnit.SECONDS.sleep(4);
            driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '确认'").click();
            passwordTF.sendKeys("Test0001");
            driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '完成'").click();
            TimeUnit.SECONDS.sleep(15);
            backBtn.click();
        }

    }

    public void disagreeAction() throws Exception {
        enterMyAgreedProposal();
        TimeUnit.SECONDS.sleep(3);
        proposCells.get(0).click();
        TimeUnit.SECONDS.sleep(6);
        if (Helper.isElementExist(driver,"赞成")) {
            waiteTime();
            backBtn.click();
            waiteTime();
            backBtn.click();
        }else {
            disagreeBtn.click();
            TimeUnit.SECONDS.sleep(4);
            driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '确认'").click();
            passwordTF.sendKeys("Test0001");
            driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '完成'").click();
            TimeUnit.SECONDS.sleep(10);
            TimeUnit.SECONDS.sleep(10);
            backBtn.click();
        }


    }

    public void deleteAction() throws Exception {
        enterMyProposal();
        TimeUnit.SECONDS.sleep(3);
        proposCells.get(0).click();
        TimeUnit.SECONDS.sleep(6);
        if (stateLabel.getText().contains("已取消")){
            waiteTime();
            backBtn.click();
            waiteTime();
            backBtn.click();
        }else {
            deleteBtn.click();
            TimeUnit.SECONDS.sleep(2);
            driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '确认'").click();
            passwordTF.sendKeys("Test0001");
            driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '完成'").click();
            TimeUnit.SECONDS.sleep(15);
            backBtn.click();
        }
    }

    @FindBy(name = "暂无数据")
    public WebElement tempTagName;

    public void loadingWaitForOS(){
        try {
            boolean tempTag = true;
            int times = 30;
            while(tempTag&&times>0){
                try {
                    log(tempTagName.getText() + "Loading ... Times: " + String.valueOf(times));
                    TimeUnit.SECONDS.sleep(1);
                    times--;
                }catch (Exception le){
                    tempTag = false;
                }
            }
        } catch (Exception e) {
            new Base().log("committee_btn button not found");
        }
    }

}
