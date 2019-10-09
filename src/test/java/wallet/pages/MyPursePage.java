package wallet.pages;

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


}
