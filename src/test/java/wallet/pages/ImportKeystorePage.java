package wallet.pages;

import io.appium.java_client.pagefactory.AndroidFindBys;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import java.util.concurrent.TimeUnit;
import io.appium.java_client.android.AndroidDriver;

public class ImportKeystorePage extends AbstractPage {


  public AndroidDriver<?> driver;


  public ImportKeystorePage(AndroidDriver<?> driver) {
    super(driver);
    this.driver = driver;
  }


  @FindBy(id = "com.tronlink.wallet:id/et_content")
  public WebElement content_text;



  @FindBy(id = "com.tronlink.wallet:id/et_password")
  public WebElement password_text;



  @FindBy(id = "com.tronlink.wallet:id/bt_next")
  public WebElement next_btn;



  @FindBy(id = "com.tronlink.wallet:id/tv_error")
  public List<WebElement> error_hits;



  public PrivateKeySetNamePage enterPrivateKeySetNamePage(String key,String password) throws Exception{
    content_text.sendKeys(key);
    password_text.sendKeys(password);
    next_btn.click();
    TimeUnit.SECONDS.sleep(1);
    return new PrivateKeySetNamePage(driver);
  }



  public String inputErrorKeyGetHits(String key,String password) throws Exception {
    content_text.sendKeys(key);
    password_text.sendKeys(password);
    next_btn.click();
    TimeUnit.SECONDS.sleep(1);
    return error_hits.get(0).getText();
  }

  public String inputErrorPasswordHits(String key,String password) throws Exception {
    content_text.sendKeys(key);
    password_text.sendKeys(password);
    next_btn.click();
    TimeUnit.SECONDS.sleep(1);
    return error_hits.get(1).getText();
  }



}
