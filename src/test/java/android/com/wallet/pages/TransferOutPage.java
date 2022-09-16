//package android.com.wallet.pages;
//
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//
//import java.util.concurrent.TimeUnit;
//
//import io.appium.java_client.android.AndroidDriver;
//
//public class TransferOutPage extends AbstractPage {
//
//
//    public AndroidDriver<?> driver;
//
//
//    public TransferOutPage(AndroidDriver<?> driver) {
//        super(driver);
//        this.driver = driver;
//    }
//
//
//
//    @FindBy(id = "com.tronlink.global:id/et_count")
//    public WebElement count_text;
//
//
//
//    @FindBy(id = "com.tronlink.global:id/tv_chain_name")
//    public WebElement chain_text;
//
//
//    @FindBy(id = "com.tronlink.global:id/btn_asset_confirm")
//    public WebElement transferOut_btn;
//
//
//
//    public String getTransferInInfo(String info) throws Exception {
//        count_text.sendKeys("10");
//        transferOut_btn.click();
//        TimeUnit.SECONDS.sleep(1);
//        String value = "";
//        switch (info){
//            case "trx":
//                value = trx_text.getText();
//                break;
//            case "hits":
//                value = hits_text.getText();
//                break;
//            case "fee":
//                value = fee_text.getText().split(" ")[0];
//                break;
//        }
//        return value;
//    }
//
//
//
//
//}
