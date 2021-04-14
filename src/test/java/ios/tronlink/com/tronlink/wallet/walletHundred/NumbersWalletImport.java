package ios.tronlink.com.tronlink.wallet.walletHundred;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NumbersWalletImport extends BaseTest {


    @AfterMethod(alwaysRun = true)
    public void afterMethod(Method methed) throws Exception {
        try {

            DRIVER.closeApp();
            DRIVER.launchApp();
        } catch (Exception e) {
        }

    }
    @Test(description = "Import 100 wallet in addressBook", alwaysRun = true)
    public void test000_Import100Walletbook() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        minePage.driver.findElementById("地址本").click();
        TimeUnit.SECONDS.sleep(1);

        //nameField 名称
        //addressField 地址
        //remarkField 备注
        //rightLabel 保存
        List<String> array = readFile("src/test/resources/100address.txt");

        for (int i = 0; i < array.size(); i++) {
            log(array.get(i));
            minePage.driver.findElementById("addressBook add").click();
            TimeUnit.SECONDS.sleep(1);
            minePage.driver.findElementById("nameField").sendKeys("Auto_add_" + (i+1));
            Helper.closeKeyBoard(minePage.driver);
            minePage.driver.findElementById("addressField").sendKeys(array.get(i));
            Helper.closeKeyBoard(minePage.driver);
            try {
                System.out.println(minePage.driver.findElementById("rightLabel").getLocation());
                log("have");
            }catch (Exception e){
                log("no");
            }
            minePage.driver.findElementById("rightLabel").click();
            log("导入了 " + (i+1) + "个地址");

        }

    }

    public ImportPrivateKeyPage enterImportPrivateKeyPage() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        waiteTime();
        assetPage.addWallet_btn.click();
        waiteTime();
        DRIVER.findElementByName("私钥").click();
        TimeUnit.SECONDS.sleep(2);
        return new ImportPrivateKeyPage(DRIVER);
    }

    @Test(description = "Import 100 wallet in wallet", alwaysRun = false)
    public void test001_Import100Wallet() throws Exception {
        List<String> array = readFile("src/test/resources/100privatekeyios.txt");

        for (int i = 0; i < array.size(); i++) {
            ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
            PrivateKeySetNamePage setName = importPrivateKey.enterPrivateKeySetNamePage(array.get(i));
            System.out.println("\nsetName.getError_hits():" + setName.getError_hits());
            if (setName.getError_hits().contains("钱包已存在")) {
                System.out.println("\nWallet Exist: " + array.get(i) + "第" + i + "个");
                DRIVER.closeApp();
                DRIVER.launchApp();
                continue;
            }
            PrivateKeySetPwdPage setPwd = setName.enterPrivateKeySetPwdPage("Auto_add_" + i);
            PrivateKeySetPwdAgainPage setPwdAgain = setPwd.enterPrivateKeySetPwdAgainPage("Test0001");
            TimeUnit.SECONDS.sleep(1);
            setPwdAgain.pwd_input.sendKeys("Test0001");
            Helper.tapWhitePlace(DRIVER);
            setPwdAgain.getComplish_btn().click();
            TimeUnit.SECONDS.sleep(4);

        }


    }


    public List<String> readFile(String fileName) {
        List<String> temparray;
        temparray = new ArrayList<>();

        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            while ((tempString = reader.readLine()) != null) {
                System.out.println("line " + line + ": " + tempString);
                temparray.add(tempString);
                line++;
            }
            reader.close();
            return temparray;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return null;
    }

}
