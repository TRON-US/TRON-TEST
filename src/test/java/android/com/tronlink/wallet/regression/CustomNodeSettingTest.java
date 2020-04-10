package android.com.tronlink.wallet.regression;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AddCustomNodePage;
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
    Integer mainnetBeforeNodeNum;
    Integer dappnetBeforeNodeNum;
    Integer mainnetAfterNodeNum;
    Integer dappnetAftereNodeNum;
    String mainNetCustomIp = "1.1.1.1";
    String dappNetCustomIp = "2.2.2.2";
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

    @Test(enabled = true,description = "Node setting of main chain page test")
    public void test001_NodeSettingOfMainChainTest() throws Exception {
        NodeSetPage nodeSetPage = enterNodeSettingPage();
        InternalNodeSetPage internalNodeSetPage = nodeSetPage.enterInternalMainChainPage();
        Assert.assertTrue(internalNodeSetPage.fulllNode_text.getText().contains("Full Node"));
        mainnetBeforeNodeNum = internalNodeSetPage.ip_list.size();
        Assert.assertTrue(checkIpFormat(internalNodeSetPage.ip_list.get(0).getText()));
        Integer port = Integer.valueOf(internalNodeSetPage.port_list.get(mainnetBeforeNodeNum-1).getText());
        Assert.assertTrue(port > 0 && port < 65536 );
    }


    @Test(enabled = true,description = "Add custom node to mainNet test")
    public void test002_AddCustomNodeToMainNetTest() throws Exception {
        NodeSetPage nodeSetPage = enterNodeSettingPage();
        InternalNodeSetPage internalNodeSetPage = nodeSetPage.enterInternalMainChainPage();
        mainnetBeforeNodeNum = internalNodeSetPage.ip_list.size();
        AddCustomNodePage addCustomNodePage = internalNodeSetPage.enterAddCustomNodePage();
        addCustomNodePage.nodeIp_input.sendKeys(mainNetCustomIp);
        addCustomNodePage.nodePort_input.sendKeys("50051");
        internalNodeSetPage = addCustomNodePage.saveNode();
        mainnetAfterNodeNum = internalNodeSetPage.ip_list.size();
        System.out.println("mainnetBeforeNodeNum:" + mainnetBeforeNodeNum);
        System.out.println("mainnetAfterNodeNum:" + mainnetAfterNodeNum);
        Assert.assertTrue(mainnetAfterNodeNum == mainnetBeforeNodeNum + 1);
        String ip = internalNodeSetPage.ip_list.get(mainnetAfterNodeNum-1).getText();
        Assert.assertEquals(ip,mainNetCustomIp);
    }

    @Test(enabled = true,description = "Delete custom node from mainNet test")
    public void test003_DeleteCustomNodeToMainNetTest() throws Exception {
        NodeSetPage nodeSetPage = enterNodeSettingPage();
        InternalNodeSetPage internalNodeSetPage = nodeSetPage.enterInternalMainChainPage();
        mainnetBeforeNodeNum = internalNodeSetPage.ip_list.size();
        AddCustomNodePage addCustomNodePage = internalNodeSetPage.enterEditCustomNodePage();
        internalNodeSetPage = addCustomNodePage.deleteNode();
        Integer currentNodeNum = internalNodeSetPage.ip_list.size();
        Assert.assertEquals(currentNodeNum,mainnetBeforeNodeNum);

    }


    @Test(enabled = true,description = "Node setting of dapp chain page test")
    public void test004_NodeSettingOfDappChainTest() throws Exception {
        NodeSetPage nodeSetPage = enterNodeSettingPage();
        InternalNodeSetPage internalNodeSetPage = nodeSetPage.enterInternalDAppChainPage();
        Assert.assertTrue(internalNodeSetPage.fulllNode_text.getText().contains("Full Node"));
        int nodeNumber = internalNodeSetPage.ip_list.size();
        Assert.assertTrue(checkIpFormat(internalNodeSetPage.ip_list.get(0).getText()));
        Integer port = Integer.valueOf(internalNodeSetPage.port_list.get(nodeNumber-1).getText());
        Assert.assertTrue(port > 0 && port < 65536 );
    }





    @Test(enabled = true,description = "Add custom node to dappNet test")
    public void test005_AddCustomNodeToMainNetTest() throws Exception {
        NodeSetPage nodeSetPage = enterNodeSettingPage();
        InternalNodeSetPage internalNodeSetPage = nodeSetPage.enterInternalDAppChainPage();
        AddCustomNodePage addCustomNodePage = internalNodeSetPage.enterAddCustomNodePage();
        addCustomNodePage.nodeIp_input.sendKeys(dappNetCustomIp);
        addCustomNodePage.nodePort_input.sendKeys("50051");
        internalNodeSetPage = addCustomNodePage.saveNode();
        mainnetAfterNodeNum = internalNodeSetPage.ip_list.size();
        Assert.assertTrue(mainnetAfterNodeNum == mainnetBeforeNodeNum + 1);
        String ip = internalNodeSetPage.ip_list.get(mainnetAfterNodeNum-1).getText();
        Assert.assertEquals(ip,dappNetCustomIp);
    }


    @Test(enabled = true,description = "Delete custom node in dappNet test")
    public void test006_AddCustomNodeToMainNetTest() throws Exception {
        NodeSetPage nodeSetPage = enterNodeSettingPage();
        InternalNodeSetPage internalNodeSetPage = nodeSetPage.enterInternalDAppChainPage();
        dappnetBeforeNodeNum = internalNodeSetPage.ip_list.size();
        AddCustomNodePage addCustomNodePage = internalNodeSetPage.enterEditCustomNodePage();
        internalNodeSetPage = addCustomNodePage.deleteNode();
        Integer currentNodeNum = internalNodeSetPage.ip_list.size();
        Assert.assertEquals(currentNodeNum,dappnetBeforeNodeNum);

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
