package android.com.wallet.pages;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WalletPasswordPage extends AbstractPage {

  public AndroidDriver<?> driver;


  public WalletPasswordPage(AndroidDriver<?> driver)  {
    super(driver);
    this.driver = driver;
  }


  @FindBy(id = "com.tronlink.global:id/et_oldpassword")
  public WebElement oldPassword_et;

  @FindBy(id = "com.tronlink.global:id/et_newpassword")
  public WebElement newPassword_et;

  @FindBy(id = "com.tronlink.global:id/et_confirm_password")
  public WebElement confirmPassword_et;

  @FindBy(id = "com.tronlink.global:id/ok")
  public WebElement ok_btn;

  @FindBy(id = "com.tronlink.global:id/tv_error")
  public WebElement error;

  public void changePassword(String oldpw,String newpw,String confirmpw) throws Exception{
    oldPassword_et.sendKeys(oldpw);
    newPassword_et.sendKeys(newpw);
    confirmPassword_et.sendKeys(confirmpw);
    ok_btn.click();
  }

}
