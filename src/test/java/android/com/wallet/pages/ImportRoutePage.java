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


  @FindBy(id = "com.tronlink.global:id/tv_linked_account_count")
  public WebElement tv_linked_account_count;

  public void enterLinkedWallet(){
    tv_linked_account_count.click();
  }

  @FindBy(id = "com.tronlink.global:id/net_error")
  public WebElement net_error;

  @FindBy(id = "com.tronlink.global:id/iv_common_right")
  public WebElement iv_common_right;

  public void closeLinked(){
    iv_common_right.click();
  }

  @FindBy(id = "com.tronlink.global:id/import_content")
  public WebElement importContent;

  @FindBy(id = "com.tronlink.global:id/btn_next_step")
  public WebElement NextBtn;

  @FindBy(id = "com.tronlink.global:id/btn_next")
  public WebElement memNextBtn;

  @FindBy(id = "com.tronlink.global:id/import_wallet_password")
  public WebElement passwordInput;

  @FindBy(id = "com.tronlink.global:id/import_wallet_password_again")
  public WebElement passwordInputAgain;

  @FindBy(id = "com.tronlink.global:id/input_wallet_password")
  public WebElement passwordCreatInput;

  @FindBy(id = "com.tronlink.global:id/input_wallet_password_again")
  public WebElement passwordCreatInputAgain;

  @FindBy(id = "com.tronlink.global:id/tv_walletname")
  public WebElement walletName;


  @FindBy(id = "com.tronlink.global:id/tv_title")
  public WebElement title;

  @FindBy(id = "com.tronlink.global:id/tv_watch_tag")
  public WebElement wallettag;

  @FindBy(id = "com.tronlink.global:id/bt_create")
  public WebElement creatBtn;

  @FindBy(id = "com.tronlink.global:id/tv_top")
  public WebElement toptitle;

  @FindBy(id = "com.tronlink.global:id/tv_bg_right")
  public WebElement laterBackup;

  @FindBy(id = "com.tronlink.global:id/bt_success")
  public WebElement bt_success;

  @FindBy(id = "com.tronlink.global:id/iv_common_left")
  public WebElement iv_common_left;

  @FindBy(id = "com.tronlink.global:id/ll_common_left")
  public WebElement ll_common_left;

  @FindBy(id = "com.tronlink.global:id/add_watch_address")
  public WebElement watchTextField;

  @FindBy(id = "com.tronlink.global:id/tv_trx_value")
  public WebElement tv_trx_value;

  public void enterManagePage(){
    tv_trx_value.click();
  }

  @FindBy(id = "com.tronlink.global:id/tv_backup")
  public WebElement tv_backup;

  @FindBy(id = "com.tronlink.global:id/add_watch_wallet")
  public WebElement watchBtn;

  @FindBy(id = "com.tronlink.global:id/confirm")
  public WebElement corfirm;
}
