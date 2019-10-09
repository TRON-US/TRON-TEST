package com.tronlink.wallet.pages;

import com.tronlink.wallet.UITest.base.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.appium.java_client.android.AndroidDriver;

/**
 * 资产页
 */

public class AssetPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public AssetPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "com.tronlink.wallet:id/rl_send")
    public WebElement assets_btn;

    @FindBy(id="com.tronlink.wallet:id/rl_bg_vote")
    public WebElement vote_btn;

    @FindBy(id="com.tronlink.wallet:id/appmarket")
    public WebElement market_btn;

    @FindBy(id="com.tronlink.wallet:id/app1")
    public WebElement find_btn;





    public TransferPage enterTransferPage() {
        assets_btn.click();
//        try {assets_btn.click();
//        }catch (Exception e){
//            Base.log("assets_btn button not found");
//        }
        return new TransferPage(driver);
    }

    //enter Vote Page
    public VotePage enterVotePage(){
        vote_btn.click();
        return new VotePage(driver);
    }

    //enter Market Page
    public MarketPage enterMarketPage(){
        market_btn.click();
        return new MarketPage(driver);
    }

    //enter Find Page
    public FindPage enterFindPage() {
        find_btn.click();
        return new FindPage(driver);
    }





}
