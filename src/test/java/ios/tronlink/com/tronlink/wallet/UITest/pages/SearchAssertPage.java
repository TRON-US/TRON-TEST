package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchAssertPage extends AddAssertPage {



    public IOSDriver<?> driver;


    public SearchAssertPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(name = "token名称/token ID/智能合约地址")
    public WebElement addAssert_input;



    @FindBy(name = "testAssetIssue_1567077083240 (), 0, ID 1000027")
    public WebElement turnAsset_btn;


    @FindBy(name = "Done")
    public WebElement keyboard_btn;

    @FindBy(name = "取消")
    public WebElement back_btn;





    public AddAssertPage enterAddAssertPage(){
        back_btn.click();
        return new AddAssertPage(driver);
    }



}
