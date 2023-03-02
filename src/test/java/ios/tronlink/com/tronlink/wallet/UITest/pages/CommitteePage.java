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



    @FindBy(id = "descLabel")
    public  WebElement descLabel;

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

    public void CreatProposalPage() throws Exception{
        Setuppropos.click();
       TimeUnit.SECONDS.sleep(2);
    }

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

    @FindBy(xpath = "(//XCUIElementTypeButton[@name=\"确认\"])[1]")
    public WebElement confirmButton;

    public WebElement confirmButton(){
        return driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '确认'");
    }
    public void change1proposal(String pro) throws Exception{
        TimeUnit.SECONDS.sleep(1);
        WebElement textField = textfieldList.get(1);
        textField.clear();
        textField.sendKeys("");
        textField.sendKeys(pro);
        closeKeyBoard();
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '确认'").click();
        unTillSomeThing("确认交易");
        log("开始找第二个确认");
        confirmButton.click();
        log("开始输入密码");
        passwordTF.sendKeys("Test0001");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '完成'").click();
//        TimeUnit.SECONDS.sleep(15);
        unTillSomeThing("委员会提议");
    }
    public void change2proposal(String pro) throws Exception{
        waiteTime();
        WebElement textField = textfieldList.get(2);
        textField.clear();
        textField.sendKeys("");
        textField.sendKeys(pro);
        closeKeyBoard();
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '确认'").click();
        unTillSomeThing("确认交易");
        log("开始找第二个确认");
        confirmButton.click();
        passwordTF.sendKeys("Test0001");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '完成'").click();
        unTillSomeThing("委员会提议");
    }


    public void change0proposal(String pro) throws Exception{
        waiteTime();
        WebElement textField = textfieldList.get(0);
        textField.clear();
        textField.sendKeys("");
        textField.sendKeys(pro);
        closeKeyBoard();
        waiteTime();
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '确认'").click();
        unTillSomeThing("确认交易");
        confirmButton.click();
        passwordTF.sendKeys("Test0001");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '完成'").click();
        unTillSomeThing("委员会提议");


    }
    public void change0proposalStep1(String pro) throws Exception {
        waiteTime();
        WebElement textField = textfieldList.get(0);
        textField.clear();
        textField.sendKeys("");
        textField.sendKeys(pro);
        closeKeyBoard();
        waiteTime();
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '确认'").click();
        unTillSomeThing("确认交易");
    }
    @FindBy(id = "生成交易二维码")
    public WebElement generateQRCodeBtn;
    public  void  enterQRCodePage(){
        generateQRCodeBtn.click();
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
        TimeUnit.SECONDS.sleep(6);
        return disagreeBtn.isDisplayed();
    }

    public boolean getdisagreedStateofproposal() throws Exception {
        waiteTime();
        findFirstproposalWl().click();
        TimeUnit.SECONDS.sleep(10);
        if(isElementExist("赞成")){
            return agreeBtn.isDisplayed();
        }else {
            return isElementExist("无赞成者");
        }
    }

    public void enterProposalDetail() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        WebElement wl = findFirstproposalWl();
        wl.click();
        TimeUnit.SECONDS.sleep(7);
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
        TimeUnit.SECONDS.sleep(4);

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
        unTillSomeThing("提议详情");
//        TimeUnit.SECONDS.sleep(6);
        if (Helper.isElementExist(driver,"取消赞成")) {
            backBtn.click();
            TimeUnit.SECONDS.sleep(1);
            backBtn.click();
        }else {
            agreeBtn.click();
            TimeUnit.SECONDS.sleep(15);
            driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '确认'").click();
            TimeUnit.SECONDS.sleep(10);
            passwordTF.sendKeys("Test0001");
            driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '完成'").click();
            TimeUnit.SECONDS.sleep(15);
            backBtn.click();
        }

    }

    public boolean disagreeAction() throws Exception {
        enterMyAgreedProposal();
        TimeUnit.SECONDS.sleep(4);
        if (isElementExist("暂无数据")){
            return false;
        }else {
            proposCells.get(0).click();
            unTillSomeThing("提议详情");
            if (Helper.isElementExist(driver, "赞成")) {
                backBtn.click();
                TimeUnit.SECONDS.sleep(1);
                backBtn.click();
            } else if(isElementExist("已失效")) {
                return false;
            }else if(isElementExist("提议详情")){
                disagreeBtn.click();
                TimeUnit.SECONDS.sleep(15);
                driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '确认'").click();
                TimeUnit.SECONDS.sleep(10);
                passwordTF.sendKeys("Test0001");
                driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '完成'").click();
                TimeUnit.SECONDS.sleep(10);
                TimeUnit.SECONDS.sleep(10);
                navBack();
            }
            return true;
        }
    }

    public void deleteAction() throws Exception {
        enterMyProposal();
        TimeUnit.SECONDS.sleep(4);

        if (!isElementExist("暂无数据")){
            proposCells.get(0).click();
            unTillSomeThing("提议详情");
            if (stateLabel.getText().contains("已取消")){
                backBtn.click();
                TimeUnit.SECONDS.sleep(1);
                backBtn.click();
            }else {
                deleteBtn.click();
                TimeUnit.SECONDS.sleep(15);
                driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '确认'").click();
                TimeUnit.SECONDS.sleep(10);
                passwordTF.sendKeys("Test0001");
                driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '完成'").click();
                TimeUnit.SECONDS.sleep(10);
                TimeUnit.SECONDS.sleep(10);
            }

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
