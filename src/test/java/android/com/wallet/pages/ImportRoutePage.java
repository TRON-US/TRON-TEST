package android.com.wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

public class ImportRoutePage extends AbstractPage {


  public AndroidDriver<?> driver;


  public ImportRoutePage(AndroidDriver<?> driver) {
    super(driver);
    this.driver = driver;
  }


  @FindBy(id = "com.tronlinkpro.wallet:id/import_content")
  public WebElement importContent;

  @FindBy(id = "com.tronlinkpro.wallet:id/btn_next_step")
  public WebElement NextBtn;

  @FindBy(id = "com.tronlinkpro.wallet:id/btn_next")
  public WebElement memNextBtn;

  @FindBy(id = "com.tronlinkpro.wallet:id/import_wallet_password")
  public WebElement passwordInput;

  @FindBy(id = "com.tronlinkpro.wallet:id/import_wallet_password_again")
  public WebElement passwordInputAgain;

  @FindBy(id = "com.tronlinkpro.wallet:id/input_wallet_password")
  public WebElement passwordCreatInput;

  @FindBy(id = "com.tronlinkpro.wallet:id/input_wallet_password_again")
  public WebElement passwordCreatInputAgain;

  @FindBy(id = "com.tronlinkpro.wallet:id/tv_walletname")
  public WebElement walletName;


  @FindBy(id = "com.tronlinkpro.wallet:id/tv_title")
  public WebElement title;

  @FindBy(id = "com.tronlinkpro.wallet:id/tv_watch_tag")
  public WebElement wallettag;

  @FindBy(id = "com.tronlinkpro.wallet:id/bt_create")
  public WebElement creatBtn;

  @FindBy(id = "com.tronlinkpro.wallet:id/tv_top")
  public WebElement toptitle;

  @FindBy(id = "com.tronlinkpro.wallet:id/tv_bg_right")
  public WebElement laterBackup;

  @FindBy(id = "com.tronlinkpro.wallet:id/add_watch_address")
  public WebElement watchTextField;

  @FindBy(id = "com.tronlinkpro.wallet:id/add_watch_wallet")
  public WebElement watchBtn;

  @FindBy(id = "com.tronlinkpro.wallet:id/confirm")
  public WebElement corfirm;
}
