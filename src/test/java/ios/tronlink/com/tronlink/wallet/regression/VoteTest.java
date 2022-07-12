package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class VoteTest extends BaseTest {

    public VotePage enterVotePage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        TimeUnit.SECONDS.sleep(4);
        return vote;
    }

    @Test(description = "Test into VotePage'", alwaysRun = true)
    public void test001_enterVotePageTest() throws Exception {
        enterVotePage();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(isElementExist("投票 "));
        Assert.assertTrue(isElementExist("超级代表"));
        Assert.assertTrue(isElementExist("多重签名投票"));
        Assert.assertTrue(isElementExist("resource manager introduce"));
        Assert.assertTrue(isElementExist("投票"));
    }

     @Test(alwaysRun = true)
     public void test002_VotePageAmountTest() throws Exception {
         VotePage page = enterVotePage();
         Double total = sepRightNumberTextToDouble( page.totalVoteAmountLabel.getText(),"总投票权");
         Double voted = sepRightNumberTextToDouble( page.usedVoteAmountLabel.getText(),"已投票");
         Double av = Double.parseDouble(page.availableAmountLabel.getText());
         Assert.assertEquals(voted+av,total,0.0001);

     }

      @Test(alwaysRun = true)
      public void test003_VotePopViewTest() throws Exception {
          VotePage page = enterVotePage();
          page.enterIntroduce();
          Assert.assertTrue(isElementExist("投票说明"));
          Assert.assertTrue(isElementExist("我知道了"));
          page.know.click();
          page.enterSortPobView();
          Assert.assertTrue(isElementExist("排序"));
          Assert.assertTrue(isElementExist("已投票数（高到低）"));
          Assert.assertTrue(isElementExist("预计年化收益（高到低）"));
          Assert.assertTrue(isElementExist("得票数（高到低）"));
          page.TapAnyWhere(200,200);
          Assert.assertFalse(isElementExist("排序"));
      }





}
