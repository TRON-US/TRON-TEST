package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import ios.tronlink.com.tronlink.wallet.UITest.base.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.concurrent.TimeUnit;
/**
 * 关于我们页面
 */
public class AboutUsPage extends AbstractPage {

    public IOSDriver<?> driver;

    public AboutUsPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(name = "关于我们")
    public WebElement title;

    @FindBy(name = "加入社群")
    public WebElement intoGroup_btn;

    @FindBy(name = "版本日志")
    public WebElement recordVersion_btn;

    public VersionsPage enterVersionsPage(){
        try {
            recordVersion_btn.click();
        }catch (Exception e){
            System.out.println(e);
        }
        return new VersionsPage(driver);
    }

    public GroupPage enterGroupPage() {
        try {
            TimeUnit.SECONDS.sleep(1);
            intoGroup_btn.click();
            TimeUnit.SECONDS.sleep(3);

        } catch (Exception e) {
            new Base().log("intoGroup_btn button not found");
        }
        return new GroupPage(driver);
    }

}
