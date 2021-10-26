package ios.tronlink.com.tronlink.wallet.UITest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.ios.IOSDriver;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

public class ImportPage extends AbstractPage {


  public IOSDriver<?> driver;

  public ImportPage(IOSDriver<?> driver) {
    super(driver);
    this.driver = driver;
  }

  @FindBy(name = "添加钱包")
  public WebElement Title;
  @FindBy(name = "导入")
  public WebElement ImportTitle;
  @FindBy(name = "新建")
  public WebElement newTitle;
  @FindBy(name = "配对硬件钱包")
  public WebElement pareHardWalletTitle;
  @FindBy(name = "私钥")
  public WebElement privatekeyButton;
  @FindBy(name = "助记词")
  public WebElement mnemonicButton;
  @FindBy(name = "Keystore")
  public WebElement KeystoreButton;
  @FindBy(name = "观察钱包")
  public WebElement watchButton;
  @FindBy(name = "创建钱包")
  public WebElement creatButton;
  @FindBy(name = "Ledger")
  public WebElement LedgerButton;
  @FindBy(name = "HD 钱包")
  public WebElement HDWalletButton;
  @FindBy(name = "非 HD 钱包")
  public WebElement noHDWalletButton;
  @FindBy(className = "XCUIElementTypeTextField")
  public WebElement inputTextField;
  @FindBy(name = "下一步")
  public WebElement nextButton;
  @FindBy(className = "XCUIElementTypeSecureTextField")
  public WebElement inputPasswordTextField;


}
