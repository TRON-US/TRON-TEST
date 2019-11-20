package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static java.lang.Thread.sleep;

public class WalletPasswordPage extends AbstractPage {



    public IOSDriver<?> driver;


    public WalletPasswordPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }
    @FindBy(name = "输入旧的钱包密码")
    public WebElement oldPassword_et;

    @FindBy(name = "8-32个字符")
    public WebElement newPassword_et;

    @FindBy(name = "再次输入新钱包密码")
    public WebElement confirmPassword_et;

    @FindBy(name = "完成")
    public WebElement ok_btn;

    //￼
    @FindBy(name = "两次输入密码不一致")
    public WebElement errornoEqual;

    @FindBy(name = "密码错误")
    public WebElement errorWrong;

    @FindBy(name = "字符数不合法")
    public WebElement errorinvalue;

    @FindBy(name = "不少于8位字符，至少由大小写字母和数字组成")
    public WebElement errorFromat;
//
    @FindBy(name = "新旧密码不能一致")
    public WebElement erroroldnewEqual;

    @FindBy(className = "XCUIElementTypeStaticText")
    public List<WebElement> testarray;

    public void changePassword(String oldpw,String newpw,String confirmpw) throws Exception{
        oldPassword_et.sendKeys(oldpw);
        TouchAction action = new TouchAction(driver);
        PointOption whiteplace = PointOption.point(10,160);

        action.tap(whiteplace).perform();

        sleep(1);
        newPassword_et.sendKeys(newpw);
        action.tap(whiteplace).perform();

        confirmPassword_et.sendKeys(confirmpw);
        action.tap(whiteplace).perform().release();
        sleep(1);

        ok_btn.click();
    }



}
