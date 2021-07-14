package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ImportKeystorePage extends AbstractPage {


  public IOSDriver<?> driver;

  public ImportKeystorePage(IOSDriver<?> driver) {
    super(driver);
    this.driver = driver;
  }

  @FindBy(className = "XCUIElementTypeTextView")
  public WebElement content_text;



  @FindBy(className = "XCUIElementTypeSecureTextField")
  public WebElement password_text;



  @FindBy(name = "com.tronlink.wallet:id/tv_error")
  public List<WebElement> error_hits;

  @FindBy(id = "errorStr")
  public WebElement errorStr;


  public PrivateKeySetNamePage enterPrivateKeySetNamePage(String key,String password) throws Exception{
    inputKeyAndPassword(key,password);
    TimeUnit.SECONDS.sleep(1);
    return new PrivateKeySetNamePage(driver);
  }

  public void inputKeyAndPassword(String key,String password) throws Exception {
    waiteTime();
    content_text.sendKeys(key);
    Helper.closeKeyBoard(driver);
    waiteTime();
    password_text.sendKeys(password);
    Helper.closeKeyBoard(driver);
    waiteTime();
    driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '下一步'").click();
  }




}
