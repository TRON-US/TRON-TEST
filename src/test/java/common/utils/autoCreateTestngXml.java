package common.utils;

import java.util.HashMap;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.testng.collections.Lists;

public class autoCreateTestngXml {
    private String reportPath = "src/test/resources/tronlink-testng.xml";
    private String adb = "/Users/tron/Library/Android/sdk/platform-tools/adb";
//    private String adb = "adb";
    private String packagesName = "<package name=\"com.tronklink.wallet.regression.*\"></package>";
    private String platformName = "Android";
    private Boolean noReset = false;
    private Integer systemPort = 8200;
    private Integer port = 4800;
    int accoutNumber = 0;
    List<String> deviceNameList = new ArrayList<>();
    List<String> account = Lists.newArrayList(
        "ecd4bbba178b1b0d2a0c1e6e9108e0cab805a2c1365c42c9eafaff104dbf1e72",
        "6a77e8edd232f4102e4fcaca02234df7176a9398fdde1792ae5377b009482fca",
        "a3f47c598631ada1d24e186f96b9d6e5e5fcd1123bb51d4adfe08bb7c081ffde",
        "b50aa8ce2140be6995e79d657064e5a3983ac0a47bfdcbb5e9f4b930ba2996a5",
        "d4446cf4ccfe02f165f0ba01e3d5a56546e41eebf26c3cfe33564bababeef74d",
        "3999ce04f0ba5e05776d355b194f369a6d56f4fd7711a31adf2044690236bf5b",
        "022f883a91a14567a8b1ad9722b73971f5c748586e951b7a8eed0ef6e29950ac",
        "4865dab66fe80391f8de760a586258dc3ebff66ee6408c2eff85e1a2e3e43e10",
        "a1866b9c8b2effb0edc091b3d56b787a03b455b8b001414cb19acc1869230026",
        "f88184cfc003612d02b94956bccde12b8086c5010b3401357e7bdc8dd7727f4d",
        "71951c4a6b1d827ee9180ddd46d61b9963c2763737f3d3724049c6ae50e5efed",
        "cfd889566341aea937737ecf4bc35f9be7c5b43f594c9a230a0348183472245e",
        "11c7013416aac83fd6070abb8ffceb0ad102d9f87dfc9c98308b0fd47e8c3a1a",
        "0ea138885c1fb2b6adaad51033c8876df0e37ccf7dd322cfad5504d671fb2a79",
        "8f3839e21b4ada348da3d85ccc72e1f1898a39b877f0f6f5b35137588a344345",
        "cf9933e99ee2b272147dd563c7f880de751c30d61cd6681a158f0a8056023d9b",
        "223f2e4a3010286540d3c119d2a1d55b1d54248f63f1c4d9ccfbd0d105ab15c7",
        "844f7f5da381943403e8324db4fda13dce9af35b72cf2ea3846fafa12c5d9890",
        "6850fd0a0f2cb94167bf0507a738fa9eef51d6fdc65e8452039f711a4bdf3135",
        "cec7fc3c9c603ae6cdc026c777db037b8ca4995d451fa5fe7b2f19a0dc01cd98",
        "7652071f95c376e6d1100f9fed5c520f22262c1530f328bb1c3ed10bad771e68"
        );



    @BeforeClass
    public void beforeClass() throws IOException{
        deviceNameList = AppiumTestCase.getDeviceList(adb + " devices");
        beforeWrite();
    }



    @Test(enabled = true)
    public void createXml() throws IOException {


        StringBuilder sb = new StringBuilder();


        for(Iterator<String> it = deviceNameList.iterator(); it.hasNext();){
            String  udid=it.next();
            sb.append("    <test name= \"" + udid + "\">\n");
            adb = "/Users/tron/Library/Android/sdk/platform-tools/adb -s " + udid;
            String platformVersion = AppiumTestCase.cmdReturn(adb + " shell getprop ro.build.version.release");
            String deviceName = AppiumTestCase.cmdReturn(adb + " shell getprop ro.product.name");
            sb.append("        <parameter name=\"port\"  value=\"" + port++ + "\"/>\n");
            sb.append("        <parameter name=\"udid\" value=\"" + udid + "\"/>\n");
            sb.append("        <parameter name=\"platformName\" value=\"" + platformName + "\"/>\n");
            sb.append("        <parameter name=\"platformVersion\" value= \"" + platformVersion + "\"/>\n");
            sb.append("        <parameter name=\"deviceName\" value= \"" + deviceName + "\"/>\n");
            sb.append("        <parameter name=\"noReset\" value=\"" + noReset.toString() + "\"/>\n");
            sb.append("        <parameter name=\"systemPort\"  value=\"" + systemPort++ + "\"/>\n");
            sb.append("        <parameter name=\"privateKey\"  value=\"" + account.get(accoutNumber++) + "\"/>\n");
            sb.append("        <packages>\n" +
                    "            " + packagesName + "\n" +
                    "        </packages>\n" +
                    "    </test>\n");
            it.remove();
        }

        sb.append("</suite>");
        String res = sb.toString();
        try {
            Files.write((Paths.get(reportPath)), res.getBytes("utf-8"), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void beforeWrite() {
        StringBuilder sb = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
        + "<!DOCTYPE suite SYSTEM \"http://testng.org/testng-1.0.dtd\">\n"
        + "<suite name=\"tronlink\" parallel=\"tests\" thread-count=\"" + deviceNameList.size() + "\">\n");
        String res = sb.toString();
        try {
            Files.write((Paths.get(reportPath)), res.getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
