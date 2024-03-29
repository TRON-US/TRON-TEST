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
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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
    String mainNetCustomIp = "";
    String dappNetCustomIp = "";
    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
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
        mainnetBeforeNodeNum = internalNodeSetPage.content_list.size();
        Assert.assertNotEquals(mainnetBeforeNodeNum,0);
        Assert.assertTrue(checkIpFormat(internalNodeSetPage.firstIpByID.getText()));
        Integer port = Integer.valueOf(internalNodeSetPage.firstPort.getText());
        Assert.assertTrue(port > 0 && port < 65536 );
        waiteTime();
        Assert.assertTrue(internalNodeSetPage.title_text.getText().contains("节点设置"));
    }


    @Test(enabled = true,description = "Add custom node to mainNet test")
    public void test002_AddCustomNodeToMainNetTest() throws Exception {
        NodeSetPage nodeSetPage = enterNodeSettingPage();
        InternalNodeSetPage internalNodeSetPage = nodeSetPage.enterInternalMainChainPage();
        mainnetBeforeNodeNum = internalNodeSetPage.content_list.size();
        AddCustomNodePage addCustomNodePage = internalNodeSetPage.enterAddCustomNodePage();
        mainNetCustomIp = createRandomIp();
        addCustomNodePage.nodeIp_input.sendKeys(mainNetCustomIp);
        addCustomNodePage.nodePort_input.sendKeys("50051");
        internalNodeSetPage = addCustomNodePage.saveNode();
        mainnetAfterNodeNum = internalNodeSetPage.content_list.size();
        System.out.println("mainnetBeforeNodeNum:" + mainnetBeforeNodeNum);
        System.out.println("mainnetAfterNodeNum:" + mainnetAfterNodeNum);
        Assert.assertTrue(mainnetAfterNodeNum == mainnetBeforeNodeNum + 1);
    }

    @Test(enabled = true,description = "Delete custom node from mainNet test")
    public void test003_DeleteCustomNodeFromMainNetTest() throws Exception {
        NodeSetPage nodeSetPage = enterNodeSettingPage();
        InternalNodeSetPage internalNodeSetPage = nodeSetPage.enterInternalMainChainPage();
        mainnetBeforeNodeNum = internalNodeSetPage.content_list.size();
        AddCustomNodePage addCustomNodePage = internalNodeSetPage.enterEditCustomNodePage();
        internalNodeSetPage = addCustomNodePage.deleteNode();
        Integer currentNodeNum = internalNodeSetPage.content_list.size();
        Assert.assertTrue(currentNodeNum + 1 == mainnetBeforeNodeNum);

    }


    @Test(enabled = true,description = "Node setting of dapp chain page test")
    public void test004_NodeSettingOfDappChainTest() throws Exception {
        NodeSetPage nodeSetPage = enterNodeSettingPage();
        InternalNodeSetPage internalNodeSetPage = nodeSetPage.enterInternalDAppChainPage();
        int nodeNumber = internalNodeSetPage.content_list.size();
        Assert.assertNotEquals(nodeNumber,0);
        Assert.assertTrue(checkIpFormat(internalNodeSetPage.firstIP.getText()));
        Integer port = Integer.valueOf(internalNodeSetPage.firstPort.getText());
        Assert.assertTrue(port > 0 && port < 65536 );
        waiteTime();
        Assert.assertTrue(internalNodeSetPage.title_text.getText().contains("节点设置"));
    }


    @Test(enabled = true,description = "Add custom node to dappNet test")
    public void test005_AddCustomNodeToDappNetTest() throws Exception {
        NodeSetPage nodeSetPage = enterNodeSettingPage();
        InternalNodeSetPage internalNodeSetPage = nodeSetPage.enterInternalDAppChainPage();
        dappnetBeforeNodeNum = internalNodeSetPage.content_list.size();
        AddCustomNodePage addCustomNodePage = internalNodeSetPage.enterAddCustomNodePage();
        dappNetCustomIp = createRandomIp();
        addCustomNodePage.nodeIp_input.sendKeys(dappNetCustomIp);
        addCustomNodePage.nodePort_input.sendKeys("50051");
        internalNodeSetPage = addCustomNodePage.saveNode();
        dappnetAftereNodeNum = internalNodeSetPage.content_list.size();
        System.out.println("dappnetBeforeNodeNum:" + dappnetBeforeNodeNum);
        System.out.println("dappnetAftereNodeNum:" + dappnetAftereNodeNum);
        Assert.assertTrue(dappnetAftereNodeNum == dappnetBeforeNodeNum + 1);
    }


    @Test(enabled = true,description = "Delete custom node in dappNet test")
    public void test006_DeleteCustomNodeFromDappNetTest() throws Exception {
        NodeSetPage nodeSetPage = enterNodeSettingPage();
        InternalNodeSetPage internalNodeSetPage = nodeSetPage.enterInternalDAppChainPage();
        dappnetBeforeNodeNum = internalNodeSetPage.content_list.size();
        AddCustomNodePage addCustomNodePage = internalNodeSetPage.enterEditCustomNodePage();
        internalNodeSetPage = addCustomNodePage.deleteNode();
        Integer currentNodeNum = internalNodeSetPage.content_list.size();
        Assert.assertTrue(currentNodeNum + 1 == dappnetBeforeNodeNum);
    }

    @Test(enabled = true ,description ="Show Tips of choise other ip")
    public  void test007_ShowTipsOfChoiseOtherIP() throws Exception{
        NodeSetPage nodeSetPage = enterNodeSettingPage();
        nodeSetPage.enterSettingPageChoiseMainChain();
        InternalNodeSetPage nodeipsetting =  nodeSetPage.enterDappChainNodeSettingPage();
        nodeipsetting.firstIpByID.click();
        Assert.assertTrue(nodeipsetting.tipContent.getText().contains("DAppChain"));
        nodeipsetting.cancelBtn.click();
        nodeipsetting.backBtn.click();
        nodeipsetting = nodeSetPage.enterShastaNodeSettingPage();
        nodeipsetting.firstIpByID.click();
        Assert.assertTrue(nodeipsetting.tipContent.getText().contains("Shasta"));
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

    public String createRandomIp() {
        Random random = new Random();
        String ip = "";
        for (int i = 0; i < 4; i++) {
            int ipStr = random.nextInt(255);
            ip = ip + String.valueOf(ipStr) + '.';
        }
        return ip.substring(0,ip.length()-1);

    }


}
