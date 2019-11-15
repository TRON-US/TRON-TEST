package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.VotePage;
import org.testng.Assert;
import org.testng.annotations.*;

public class DemoTest extends BaseTest {


    @Test //测试是否正常进行投票页
    public void test01_checkPopularSearch() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        VotePage votePage = assetPage.enterVotePage();
        Assert.assertEquals(votePage.vote_title.getText(),"投票");
    }







}
