package ios.tronlink.com.tronlink.wallet.regression;


import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MinePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.TransactionRecordPage;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Transaction Record function test
 */
public class TransactionRecordTest extends BaseTest {



    @Parameters({"address"})
    @Test(description = "Transaction Record test",alwaysRun = true)
    public void test001_transactionRecord(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        TransactionRecordPage transaction = mine.enterTransactionRecordPage();
        transaction.navigation_tab.click();
        Assert.assertEquals(transaction.owner_text.getText(),address);
    }


}
