package com.tronlink.wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.appium.java_client.android.AndroidDriver;


/**
 * 转帐页
 */

public class TransferPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public TransferPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "com.tronlink.wallet:id/tv_common_title")
    public WebElement transferTtile_btn;



}
