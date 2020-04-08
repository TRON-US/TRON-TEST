package android.com.tronlink.wallet.regression;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.DAPP_BrowerPage;
import android.com.wallet.pages.InternalNodeSetPage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.NodeSetPage;
import android.com.wallet.pages.SettingPage;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * setting function test
 */
public class CustomNodeSettingTest extends Base {
    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlink.wallet");
        }catch (Exception e){}
    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }

    @Test(description = "Node setting of main chain page test")
    public void test001_NodeSettingOfMainChainTest() throws Exception {
        NodeSetPage nodeSetPage = enterNodeSettingPage();
        InternalNodeSetPage internalNodeSetPage = nodeSetPage.enterInternalMainChainPage();
        Assert.assertTrue(internalNodeSetPage.fulllNode_text.getText().contains("Full Node"));
        int nodeNumber = internalNodeSetPage.ip_list.size();
        Assert.assertTrue(checkIpFormat(internalNodeSetPage.ip_list.get(0).getText()));
        Integer port = Integer.valueOf(internalNodeSetPage.port_list.get(nodeNumber-1).getText());
        Assert.assertTrue(port > 0 && port < 65536 );
    }

    @Test(description = "Node setting of dapp chain page test")
    public void test002_NodeSettingOfDappChainTest() throws Exception {
        NodeSetPage nodeSetPage = enterNodeSettingPage();
        InternalNodeSetPage internalNodeSetPage = nodeSetPage.enterInternalDAppChainPage();
        Assert.assertTrue(internalNodeSetPage.fulllNode_text.getText().contains("Full Node"));
        int nodeNumber = internalNodeSetPage.ip_list.size();
        Assert.assertTrue(checkIpFormat(internalNodeSetPage.ip_list.get(0).getText()));
        Integer port = Integer.valueOf(internalNodeSetPage.port_list.get(nodeNumber-1).getText());
        Assert.assertTrue(port > 0 && port < 65536 );
    }

    //reset app turn to MainChain
    public NodeSetPage enterNodeSettingPage() {
        try {
            AssetPage asset = new AssetPage(DRIVER);
            MinePage mine = asset.enterMinePage();
            SettingPage set = mine.enterSettingPage();
            NodeSetPage nodeSet = set.enterNodeSetPage();
            TimeUnit.SECONDS.sleep(1);
            return nodeSet;
        } catch (Exception e) {
        }

        return null;
    }

    public boolean checkIpFormat(String ipStr) {
        String[] ip = ipStr.split(".");
        for (int i = 0; i < ip.length;i++) {
            if (Integer.valueOf(ip[i]) < 0 || Integer.valueOf(ip[i]) >255) {
                return false;
            }
        }
        return true;
    }


}
