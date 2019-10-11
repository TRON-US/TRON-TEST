package common.utils;

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
    List<String> account = Lists.newArrayList("ecd4bbba178b1b0d2a0c1e6e9108e0cab805a2c1365c42c9eafaff104dbf1e72",
        "ecd4bbba178b1b0d2a0c1e6e9108e0cab805a2c1365c42c9eafaff104dbf1e72",
        "ecd4bbba178b1b0d2a0c1e6e9108e0cab805a2c1365c42c9eafaff104dbf1e72",
        "ecd4bbba178b1b0d2a0c1e6e9108e0cab805a2c1365c42c9eafaff104dbf1e72");



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
