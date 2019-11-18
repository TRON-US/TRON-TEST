package ios.tronlink.com.tronlink.wallet.regression;



import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.ReceiptPage;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * receipt trx test
 */
public class ReceiptTest extends BaseTest {



    @Parameters({"address"})
    @Test(description = "check Receipt Address",alwaysRun = true)
    public void test001_ckeckReceiptTrxAddress(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        ReceiptPage receiptPage =  asset.enterReceiptPage();
        Assert.assertEquals(receiptPage.ownerAddress_btn.getText(),address);
    }


}
