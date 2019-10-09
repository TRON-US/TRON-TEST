package common.utils;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import

public class autoCreateTestngXml {
    private String reportPath = "src/test/resources/tronlink-testng.xml";


    @BeforeClass
    public void beforeClass() {
        beforeWrite();

    }



    @Test(enabled = true)
    public void createXml() {
        AppiumTestCase.platformVersion = cmdReturn(adb + " shell getprop ro.build.version.release");
        TronLink.deviceName = cmdReturn(adb + " -d shell getprop ro.product.model");

        devicesReturn;


    }


    public void beforeWrite() {
        StringBuilder sb = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
        + "<!DOCTYPE suite SYSTEM \"http://testng.org/testng-1.0.dtd\">"
        + "<suite name=\"tronlink\" parallel=\"tests\" thread-count=\"3\">");
        String res = sb.toString();
        try {
            Files.write((Paths.get(reportPath)), res.getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
