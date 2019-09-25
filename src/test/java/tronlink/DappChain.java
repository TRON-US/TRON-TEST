package tronlink;

import org.testng.annotations.Test;
import java.util.Date;
import java.util.List;

import common.utils.AppiumTestCase;
import io.appium.java_client.MobileElement;

public class DappChain extends AppiumTestCase {
    public  String walletPrivateKey = "66cba88ad4d8d987bd6895c8913009d34ad6adc42be19e0318b81f4107663242";
    @Test
    public void import_PrivateKey(){
        //startup page
        testOperation(importAccountId,"click","click import Account");
        while (!isEnabled(acceptImportAccount)){
            testOperation("swipeUp","");
        }
        testOperation(acceptImportAccount,"click","click Accept");

        //use Private Key import account
        testOperation(privateKey,"click","click Private key");
        testOperation(enterContent,"input",walletPrivateKey,"enter private key");
        testOperation(nextStep,"click","click Next step");
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
        testOperation(setUpName,"input","Test_"+timestamp,"input name");
        testOperation(creatNextStep,"click","1:input name");
        testOperation(passWord,"input","Test0001","input password");
        testOperation(creatNextStep2,"click","2:click next step");
        testOperation(passWord,"input","Test0001","input password again");
        testOperation(creatNextStep3,"click","3:click carry out");
    }


//    @Test
//    public void test01deposit() {
//        List<MobileElement> assets = (MobileElement)driver.findElementsById(assetsList);
//        assets.get(0).click();
//        testOperation(deposit,"click","click withdraw");
//        testOperation(sendCoinAmountId,"input","100","input amount of withdraw");
//        testOperation(withdrawButton,"click","click withdraw");
//    }

    @Test
    public void test02withdraw() {
        testOperation(tabMy,"click","click tab My");
        testOperation(settings,"click","click settings");
        testOperation(setting_node,"click","click node");

        List<MobileElement> list = driver.findElementsById(setting_dapp_change);
        for (MobileElement a : list){
            if (!a.isSelected())
                a.click();
        }
        testOperation(common_left,"click","click back");
        testOperation(common_left,"click","click back");
        testOperation(tabAssets,"click","click tab Assets");

        List<MobileElement> assets = driver.findElementsById(assetsList);
        assets.get(0).click();
        testOperation(deposit,"click","click withdraw");
        testOperation(sendCoinAmountId,"input","100","input amount of withdraw");
        testOperation(withdrawButton,"click","click withdraw");

    }
}
