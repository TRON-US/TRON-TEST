package common.utils;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import java.util.ArrayList;
import java.util.List;

public class RunSuite {
    public static void main(String[] args) {
        TestListenerAdapter tla = new TestListenerAdapter();
        TestNG testng = new TestNG();

        List<String> testFieldList = new ArrayList<>();
        testFieldList.add("/Users/tron/Documents/TEST/TRON-TEST/src/test/resources/tronlink-main.xml");
        testng.setTestSuites(testFieldList);

        testng.addListener(tla);
        testng.setSuiteThreadPoolSize(2);

        testng.run();
        System.out.println("ConfigurationFailures: "+tla.getConfigurationFailures());
        System.out.println("FailedTests: " + tla.getFailedTests());
    }
}
