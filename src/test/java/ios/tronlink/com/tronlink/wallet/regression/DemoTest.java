package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.VotePage;
import org.testng.Assert;
import org.testng.annotations.*;


import ios.tronlink.com.tronlink.wallet.UITest.base.Base;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

public class DemoTest extends Base {


    @Parameters({"privateKey"})
    @BeforeClass()
    public void setUpBefore(String privateKey) throws Exception {
        System.out.print("status:" + DRIVER.getBatteryInfo().getState() + "\n");
        DRIVER.getBatteryInfo().getState();
        //DRIVER.getBatteryInfo().toString();
        System.out.println("333333333");
        new Helper().getSign(privateKey,DRIVER);
    }



    @AfterMethod
    public void afterMethod(){
        DRIVER.closeApp();
        DRIVER.launchApp();
    }


    @AfterClass
    public void tearDownAfterClass() {
        //Base.tearDownAfterClass();
        DRIVER.quit();
    }


    @Test //测试是否正常进行投票页
    public void test01_checkPopularSearch() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        VotePage votePage = assetPage.enterVotePage();
        Assert.assertEquals(votePage.vote_title.getText(),"投票");
    }







}
