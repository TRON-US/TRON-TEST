package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
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

    @FindBy(name = "com.tronlink.wallet:id/rl_send")
    public WebElement assets_btn;



    @FindBy(name = "com.tronlink.wallet:id/ll_deposit")
    public WebElement transferIn_btn;

    @FindBy(name = "转入") ////XCUIElementTypeApplication[@name="TronLink"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[4]/XCUIElementTypeButton[3]
    public List<WebElement> transferIn_btnArray;
    @FindBy(name = "转出") ////XCUIElementTypeApplication[@name="TronLink"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[4]/XCUIElementTypeButton[3]
    public List<WebElement> transferOut_btnArray;

    @FindBy(name = "com.tronlink.wallet:id/tv_trx_amount")
    public WebElement trx_text;



    @FindBy(name = "freezeLabel")
    public WebElement freezeCount_text;



    @FindBy(name = "balanceLabel")
    public WebElement trxTotal_text;



    @FindBy(name = "white back arrow")
    public WebElement back_btn;



    @FindBy(name = "全部")
    public List<WebElement> tranfer_all;

    @FindBy(id = "transferWithdrawBtn")
    public WebElement tranferOutBtn;

    @FindBy(id = "transferDepositBtn")
    public WebElement tranferInBtn;

    @FindBy(name = "com.tronlink.wallet:id/tv_count")
    public List<WebElement> tranferIncount_text;

//    @FindBy(name = "balanceLabel")
//    public WebElement ;

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

    public TransferPage enterTransferPage() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        tranferInBtn.click();
        TimeUnit.SECONDS.sleep(2);
        return new TransferPage(driver);
    }
    public TransferPage enterTransferInPage() throws Exception {
//        System.out.println(transferIn_btnArray.size());
//        transferIn_btnArray.get(1).click();
        TimeUnit.SECONDS.sleep(3);
        tranferInBtn.click();
        TimeUnit.SECONDS.sleep(2);
        return new TransferPage(driver);
    }
    public TransferPage enterTransferOutPage() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        tranferOutBtn.click();
        TimeUnit.SECONDS.sleep(1);
        return new TransferPage(driver);
    }


    public AssetPage enterAssetPage() throws Exception {
        back_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new AssetPage(driver);
    }




}
