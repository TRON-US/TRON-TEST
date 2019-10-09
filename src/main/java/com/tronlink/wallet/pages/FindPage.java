package com.tronlink.wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.android.AndroidDriver;

public class FindPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public FindPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }



}
