package com.tronlink.wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.android.AndroidDriver;

/**
 * 收款页
 */

public class VotePage extends AbstractPage {

    public AndroidDriver<?> driver;

    public VotePage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "com.tronlink.wallet:id/title")
    public WebElement voteTitle_btn;

}
