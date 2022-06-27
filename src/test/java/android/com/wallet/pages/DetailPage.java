package android.com.wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.android.AndroidDriver;

public class DetailPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public DetailPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "com.tronlinkpro.wallet:id/name")
    public WebElement name;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_contract_type")
    public WebElement tv_contract_type;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_vdd")
    public WebElement tv_vdd;


    @FindBy(id = "com.tronlinkpro.wallet:id/iv_share")
    public WebElement iv_share;



}
