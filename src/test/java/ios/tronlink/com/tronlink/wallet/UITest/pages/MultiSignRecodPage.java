package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.clipboard.ClipboardContentType;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MultiSignRecodPage extends AbstractPage {

    //signBtn 签名按钮
    //剩余时间  leftTimeLabel
    //typeLabel TRX转账
    //amountLabel  转账的金额
//sendAddress 发起方地址
    //getAddress 接收方地址
    //waitingCell 代签名的cell
    //successCell 成功cell
//    failedcell  失败cell
    //copyS
    //copyG 按钮
    //black path 返回按钮

    public IOSDriver<?> driver;


    public MultiSignRecodPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "successCell")
    public List<WebElement> successCells;

    @FindBy(id = "failedcell")
    public List<WebElement> failedcells;

    @FindBy(id = "waitingCell")
    public List<WebElement> waitingCells;

    @FindBy(id = "signBtn")
    public List<WebElement> signBtns;

    @FindBy(id = "copyS")
    public List<WebElement> copySend_btn;


    @FindBy(id = "copyG")
    public List<WebElement> copyGet_btn;


    @FindBy(id = "leftTimeLabel")
    public List<WebElement> leftTimeLabels;


    @FindBy(id = "typeLabel")
    public List<WebElement> typeLabels;

    @FindBy(className = "XCUIElementTypeSecureTextField")
    public WebElement password_input;

    @FindBy(className = "XCUIElementTypeCollectionView")
    public WebElement topMenuView;

    public String getListString(List<WebElement> list) {
        if (list.size() == 0) return "";
        String tempstring = "";
        for (int i = 0; i < list.size(); i++) {
            try {
                tempstring += list.get(i).getText();
            } catch (Exception e) {
                System.out.println("ERROR" + e);
            }

        }
        return tempstring;

    }

    public void signSuccess() throws Exception {
        signBtns.get(0).click();
        TimeUnit.SECONDS.sleep(2);
        password_input.sendKeys("Test0001");
        driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '完成'").click();
        TimeUnit.SECONDS.sleep(8);

    }

    public int getwaitingCellsCount() {
        try {
            return waitingCells.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public int getSuccessCellsCount() {  //不实用
        try {
            return successCells.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public void TapWatingMenu() {
        int topY = topMenuView.getLocation().y;
        int left = topMenuView.getRect().width / 4;
        IOSTouchAction action = new IOSTouchAction(driver);
        System.out.println("start");
        action.tap(PointOption.point(left, topY + 10)).perform();
        System.out.println("end");
    }

    public void TapSuccessMenu() {
        int topY = topMenuView.getLocation().y;
        int center = topMenuView.getRect().width / 2;
        int left = topMenuView.getRect().width / 4;

        IOSTouchAction action = new IOSTouchAction(driver);
        action.tap(PointOption.point(center, topY + 10)).perform();
        action.tap(PointOption.point(left, topY + 10)).perform();
        action.tap(PointOption.point(center, topY + 10)).perform();

    }

    public void TapFailMenu() {
        int topY = topMenuView.getLocation().y;
        int center = topMenuView.getRect().width / 2;
        int right = center + topMenuView.getRect().width / 4;
        IOSTouchAction action = new IOSTouchAction(driver);
        System.out.println("start");
        action.tap(PointOption.point(right, topY + 10)).perform();
        System.out.println("end");
    }

    public boolean signWrongPass() throws Exception {
        signBtns.get(0).click();
        TimeUnit.SECONDS.sleep(2);
        password_input.sendKeys("Test000");
        driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '完成'").click();
        TimeUnit.SECONDS.sleep(3);
        try {
            password_input.getLocation();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean isHaveMultiSingTrans() {

        try {
            log(driver.findElementById("多重签名交易").getText());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getSendAddress() throws Exception {
        log("kaishi");
        try {
            System.out.println(copySend_btn.size());

        } catch (Exception e) {
            System.out.println(e);
        }
        log("will copy click");

        copySend_btn.get(0).click();
        log("copy click");
        TimeUnit.SECONDS.sleep(2);
        log(driver.getClipboard(ClipboardContentType.PLAINTEXT));
        log(driver.getClipboard(ClipboardContentType.URL));

        log("clipboard");
        log(driver.getClipboardText());

        return driver.getClipboardText();
    }

    public String getGetAddress() throws Exception {
        copyGet_btn.get(0).click();
        TimeUnit.SECONDS.sleep(2);
        log(driver.getClipboard(ClipboardContentType.PLAINTEXT));
        log(driver.getClipboardText());
        return driver.getClipboardText();

    }
}
