package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TrzTokenDetailPage extends AbstractPage {

    public IOSDriver<?> driver;


    public TrzTokenDetailPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    //票据详情页里面 返回按钮  black path  一个图片
    //instructionBtn  票据详情里的问号
    //blanceLabel 票据的余额按钮
//cellAmount
    //commitID
    //copyBtn
    //cellView
//匿名币的详情按钮
    @FindBy(id = "shieldedDetailBtn")
    public WebElement shieldedDetailBtn;

    @FindBy(id = "instructionBtn")
    public WebElement instructionBtn;

    @FindBy(id = "blanceLabel")
    public WebElement blanceLabel;

    @FindBy(id = "cellAmount")
    public List<WebElement> cellAmount;

    @FindBy(id = "cellView")
    public List<WebElement> cellView;

    @FindBy(id = "匿名交易中的票据（Note） – 帮助中心")
    public WebElement helpWebview;

    @FindBy(id = "typeLabel")
    public WebElement typeLabel;

    @FindBy(id = "nameLabel")
    public WebElement nameLabel;




}
