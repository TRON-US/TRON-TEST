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


    @FindBy(id = "com.tronlink.global:id/name")
    public WebElement name;

    @FindBy(id = "com.tronlink.global:id/tv_contract_type")
    public WebElement tv_contract_type;

    @FindBy(id = "com.tronlink.global:id/tv_vdd")
    public WebElement tv_vdd;


    @FindBy(id = "com.tronlink.global:id/iv_share")
    public WebElement iv_share;


    @FindBy(id = "com.tronlink.global:id/start_time")
    public WebElement start_time;

    @FindBy(id = "com.tronlink.global:id/token_describe")
    public WebElement token_describe;

    @FindBy(id = "com.tronlink.global:id/token_url")
    public WebElement token_url;


}
