package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import ios.tronlink.com.tronlink.wallet.UITest.base.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class AdvanceFuncPage extends AbstractPage {

    public IOSDriver<?> driver;

    public AdvanceFuncPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(name = "高级功能")
    public WebElement title;

    @FindBy(name = "委员会提议")
    public WebElement commiteeBtn;


    @FindBy(name = "Dapp测试工具")
    public WebElement dapp_btn;


    @FindBy(name = "转换工具")
    public WebElement mnemTools_btn;


    @FindBy(name =  "钱包数据迁移")
    public WebElement walletExport_btn;


    public CommitteePage enterCommitteePage() {
        try {
            TimeUnit.SECONDS.sleep(2);
            waiteTime(15);
            commiteeBtn.click();
            TimeUnit.SECONDS.sleep(20);
        } catch (Exception e) {
            new Base().log("committee_btn button not found");
        }
        return new CommitteePage(driver);
    }


    public MnemonicToolsPage enternemTools_btnPage() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        mnemTools_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new MnemonicToolsPage(driver);
    }

    public MinePage enterMinePage() throws Exception {
        waiteTime();
        blackBackBtn.click();
        waiteTime();
        return new MinePage(driver);
    }



}
