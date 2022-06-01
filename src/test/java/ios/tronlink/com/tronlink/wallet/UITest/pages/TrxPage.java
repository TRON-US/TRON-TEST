package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TrxPage extends AbstractPage {

    public IOSDriver<?> driver;


    public TrxPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }



    @FindBy(name = "转入") ////XCUIElementTypeApplication[@name="TronLink"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[4]/XCUIElementTypeButton[3]
    public List<WebElement> transferIn_btnArray;
    @FindBy(name = "转出") ////XCUIElementTypeApplication[@name="TronLink"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[4]/XCUIElementTypeButton[3]
    public List<WebElement> transferOut_btnArray;



    @FindBy(name = "freezeLabel")
    public WebElement freezeCount_text;



    @FindBy(name = "balanceLabel")
    public WebElement trxTotal_text;



    @FindBy(name = "white back arrow")
    public WebElement back_btn;

    //token详情右上角按钮
    @FindBy(name = "token details")
    public WebElement tokendetails;

//匿名币的详情按钮
@FindBy(id = "shieldedDetailBtn")
public WebElement shieldedDetailBtn;
//票据详情页里面 返回按钮  black path  一个图片
    //instructionBtn  票据详情里的问号
    //blanceLabel 票据的余额按钮
//cellAmount
    //commitID
    //copyBtn
    //cellView
    //tokenPageReceiveBtn

    @FindBy(name = "全部")
    public List<WebElement> tranfer_all;

    @FindBy(id = "transferWithdrawBtn")
    public WebElement tranferOutBtn;

    @FindBy(xpath = "//XCUIElementTypeApplication[@name=\"TronLink\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeButton[3]")
    public WebElement tranferInBtn;


    @FindBy(xpath = "//XCUIElementTypeApplication[@name=\"TronLink\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeButton[1]")
    public WebElement sendBtn;

    @FindBy(name = "发送")
    public WebElement tranSendBtn;

    @FindBy(id = "tokenPageReceiveBtn")
    public WebElement receiveBtn;


    @FindBy(id = "amountDesContent")
    public WebElement amountDesContent;

    @FindBy(className = "XCUIElementTypeTextField")
    public WebElement textField;



    @FindBy(id = "最大")
    public WebElement maxBtn;

    @FindBy(id = "amountErrorLabel")
    public WebElement amountErrorLabel;

    @FindBy(name = "blueamountErrorLabel")
    public WebElement blueamountErrorLabel;




    public TrzTokenDetailPage enterTrzDetailPage() throws Exception{
        waiteTime();
        shieldedDetailBtn.click();
        return  new TrzTokenDetailPage(driver);
    }
    public TrzTokenDetailPage enterTrzProjectPage() throws Exception{
        waiteTime();
        tokendetails.click();
        return  new TrzTokenDetailPage(driver);
    }



    public List<WebElement> getFirstTransferNumberList(){
        WebElement view = driver.findElementByXPath("//XCUIElementTypeApplication[@name='TronLink']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeTable/XCUIElementTypeCell");
//        List<WebElement> lintels = view.findElements(By.className("XCUIElementTypeCell"));
//        WebElement firstly = lintels.get(1);
        List<WebElement> listed = view.findElements(By.className("XCUIElementTypeStaticText"));

        return listed;

    }
    public List<WebElement> getFirstTransferOutNumber()  {

        List<WebElement> list = (List<WebElement>) driver.findElementsByXPath("//XCUIElementTypeApplication[@name='TronLink']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText");
        return  list;
    }

    public TransactionRecordPage getFirstTransferItemDetailPage() throws InterruptedException {
        System.out.println("11111114");
//        WebElement element =  driver.findElementByXPath("//XCUIElementTypeApplication[@name='TronLink']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeTable/XCUIElementTypeCell[2]");

        WebElement view = driver.findElementByXPath("//XCUIElementTypeApplication[@name='TronLink']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeTable/XCUIElementTypeCell");
        System.out.println("a");

        System.out.println("aover");

        List<WebElement> lintels = view.findElements(By.className("XCUIElementTypeCell"));
        System.out.println("steqp1 XCUIElementTypeCell ");

        System.out.println("b");

        WebElement firstly = lintels.get(1);
        List<WebElement> listed = firstly.findElements(By.className("XCUIElementTypeStaticText"));
        System.out.println(listed.size());
        System.out.println("b1");

        for (WebElement e:listed
        ) {
            System.out.println(e.getText());
        }

        System.out.println("c");

        firstly.click();

        System.out.println("22222224");

        TimeUnit.SECONDS.sleep(6);
        System.out.println("66666664");

        return new  TransactionRecordPage(driver);
    }

    public SendTrxPage enterTransferPage() throws Exception {
        sendBtn.click();
        return new SendTrxPage(driver);
    }
    public TransferPage enterTransferInPage() throws Exception {
        tranferInBtn.click();
        return new TransferPage(driver);
    }
    public TransferPage enterTransferOutPage() throws Exception {
        tranferOutBtn.click();
        return new TransferPage(driver);
    }

    //


    public AssetPage enterAssetPage() throws Exception {
        back_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new AssetPage(driver);
    }

    public boolean enterNumberRecordPage(String record){
        waiteTime();
        Helper.swipRefreshScreen(driver);
        waiteTime();
        try{
            TimeUnit.SECONDS.sleep(1);
            driver.findElementById(record).click();
            waiteTime();
            log(driver.findElementById("headerLabel").getText());

        }catch (Exception e){

            driver.findElementByName(record).click();
            log(driver.findElementById("headerLabel").getText());
        }

        return  driver.findElementById("headerLabel").getText().contains(record);

    }

    public TransactionRecordPage enterTransactionRecordPage(String record){
        waiteTime();
        Helper.swipRefreshScreen(driver);
        waiteTime();
        driver.findElementByName(record).click();
        waiteTime();
        try{
            log(driver.findElementById("headerLabel").getText());
        }catch (Exception e){
            driver.findElementByName(record).click();
            log(driver.findElementById("headerLabel").getText());
        }

        return  new TransactionRecordPage(driver);
    }



    public boolean enterWithDrawNumberRecordPage(String record){
       return enterDepositNumberRecordPage(record);
    }

    public boolean enterDepositNumberRecordPage(String record){
        waiteTime();
        log("进入token page");
        Helper.swipeLeftScreen(driver);
        waiteTime();
        Helper.swipeLeftScreen(driver);
        waiteTime();
        Helper.swipeLeftScreen(driver);
        waiteTime();
        Helper.swipeLeftScreen(driver);
        waiteTime();
        Helper.swipeLeftScreen(driver);
        waiteTime();
        Helper.swipeLeftScreen(driver);
        waiteTime();
        Helper.TapLocationName(driver,record);
        log("\n have click : " + record);
        waiteTime();
        try{
            log("detail page number is：" + driver.findElementById("headerLabel").getText());
        }catch (Exception e){
            log("没有进入那条记录,再次尝试点击");
            Helper.TapLocationName(driver,record);
        }
        waiteTime();
        return  driver.findElementById("headerLabel").getText().contains(record);

    }
}
