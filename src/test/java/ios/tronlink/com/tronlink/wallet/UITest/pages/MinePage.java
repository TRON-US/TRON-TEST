package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class MinePage extends AbstractPage {

    public IOSDriver<?> driver;


    public MinePage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }



    @FindBy(name = "设置")
    public WebElement setting_btn;



    @FindBy(name = "钱包管理")
    public WebElement myPurse_btn;



    //enter MyPurse Page
    public MyPursePage enterMyPursePage(){
        myPurse_btn.click();
        return new MyPursePage(driver);
    }








}
