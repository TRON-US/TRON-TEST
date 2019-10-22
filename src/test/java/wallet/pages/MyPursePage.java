package wallet.pages;

import org.omg.CORBA.TIMEOUT;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.android.AndroidDriver;

/**
 * 我的钱包页面
 */
public class MyPursePage extends AbstractPage {

    public AndroidDriver<?> driver;

    public MyPursePage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "com.tronlink.wallet:id/rl_sign_manager")
    public WebElement multSignManager_btn;



    @FindBy(id = "com.tronlink.wallet:id/tv_create")
    public WebElement newCreate_btn;




    public MultiSignManagerPage enterMultiSignManagerPage() {
        try {
            multSignManager_btn.click();
            TimeUnit.SECONDS.sleep(1);

        }catch (Exception e){
            System.out.println(e);
        }
        return new MultiSignManagerPage(driver);
    }


    public AddwalletPage enterAddwalletPage() throws Exception {
        newCreate_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new AddwalletPage(driver);
    }






}
