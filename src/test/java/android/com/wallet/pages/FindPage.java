package android.com.wallet.pages;

import io.appium.java_client.android.AndroidDriver;

public class FindPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public FindPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }



}
