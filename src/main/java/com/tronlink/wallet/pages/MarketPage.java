package com.tronlink.wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import io.appium.java_client.android.AndroidDriver;

/**
 * 行情页
 */
public class MarketPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public MarketPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "com.tronlink.wallet:id/tv_market")
    public WebElement Market_title;

    @FindBy(id = "com.tronlink.wallet:id/tv_price")
    public WebElement newPrice_btn;

    @FindBy(id = "com.tronlink.wallet:id/tv_price")
    public List<WebElement> firstRowPriceList;


    public String sortFun(){
        firstRowPriceList.get(0).click();
        String price = firstRowPriceList.get(1).getText();
        System.out.println(price);
        return price;
    }




}
