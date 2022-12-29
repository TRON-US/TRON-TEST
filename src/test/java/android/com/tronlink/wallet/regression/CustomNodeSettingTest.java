package android.com.tronlink.wallet.regression;
import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AddCustomNodePage;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.InternalNodeSetPage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.NodeSetPage;
import android.com.wallet.pages.SettingPage;

import java.util.Random;
import java.util.concurrent.TimeUnit;

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
    @BeforeClass(groups = {"P0"},alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
    }

    @AfterMethod(groups = {"P0"},alwaysRun = true)
    public void afterMethod() {
        try {
            TimeUnit.SECONDS.sleep(2);
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
        }catch (Exception e){}
    }

    @AfterClass(groups = {"P0"},alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }

    @Test(alwaysRun = true)
    public void test001_SettingPageAboutNode() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SettingPage sett = asset.enterMinePage().enterSettingPage();
        Assert.assertTrue(sett.network_setting_title.getText().contains("网络设置"));
        Assert.assertTrue(sett.tv_network_name.getText().contains("TRON 主网"));
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(sett.tv_node_speed.getText().contains("ms"));
    }

    @Test(groups = {"P0"},alwaysRun = true)
    public void test002_ChangeNodeTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SettingPage sett = asset.enterMinePage().enterSettingPage();
        sett.changNetWorkTo("Shasta");
        TimeUnit.SECONDS.sleep(6);
        sett = asset.enterMinePage().enterSettingPage();
        Assert.assertTrue(sett.tv_network_name.getText().contains("TRON Shasta 测试网"));
        sett.changNetWorkTo("MainChain");
        TimeUnit.SECONDS.sleep(6);
        Assert.assertEquals(asset.currChain_name.getText(),"MainChain");

    }

    @Test(groups = {"P0"},alwaysRun = true)
    public void test003_AddCustomNodeTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SettingPage sett = asset.enterMinePage().enterSettingPage();
        sett.ll_node_root.click();
        AddCustomNodePage addCustomNodePage = sett.enterAddCustomNodePage();
        mainNetCustomIp = createRandomIp();
        log("createdIP: " + mainNetCustomIp);
        addCustomNodePage.nodeIp_input.sendKeys(mainNetCustomIp);
        addCustomNodePage.nodePort_input.sendKeys("50051");
        addCustomNodePage.saveNode();
        TimeUnit.SECONDS.sleep(8);
        boolean find = false;
        for (int i = 0 ; i<sett.node_ips.size();i++){
            String ips = sett.node_ips.get(i).getText();
            System.out.println(ips);
            if (ips.contains(mainNetCustomIp)){
                find = true;
            }
        }
        Assert.assertTrue(find);

    }
    @Test(alwaysRun = true)
    public void test004_EditCustomNodeTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SettingPage sett = asset.enterMinePage().enterSettingPage();
        sett.ll_node_root.click();
        AddCustomNodePage editPage =sett.enterEditCustomNodePage();
        editPage.nodeIp_input.clear();
        mainNetCustomIp = createRandomIp();
        log("modifyIP: " + mainNetCustomIp);
        editPage.nodeIp_input.sendKeys(mainNetCustomIp);
        editPage.saveNode();
        Assert.assertTrue(assertToast("修改成功"));
    }

    @Test(groups = {"P0"},alwaysRun = true)
    public void test005_DeleteCustomNodeTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SettingPage sett = asset.enterMinePage().enterSettingPage();
        sett.ll_node_root.click();
        AddCustomNodePage editPage =sett.enterEditCustomNodePage();
        editPage.deleteNode();
        Assert.assertTrue(assertToast("删除成功"));
    }

    @Test(alwaysRun = true)
    public void test006_SwitchSeverFromAutoToSelect() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SettingPage sett = asset.enterMinePage().enterSettingPage();
        Helper.swipScreenLitte(DRIVER);
        TimeUnit.SECONDS.sleep(2);
        sett.server_name_speed.click();
        if(isElementShotId("tv_node_name")){
            sett.switch_button.click();
            TimeUnit.SECONDS.sleep(2);
            Assert.assertFalse(isElementShotId("tv_node_name"));
        }else {
            sett.switch_button.click();
            TimeUnit.SECONDS.sleep(2);
            Assert.assertTrue(isElementShotId("tv_node_name"));
        }

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
