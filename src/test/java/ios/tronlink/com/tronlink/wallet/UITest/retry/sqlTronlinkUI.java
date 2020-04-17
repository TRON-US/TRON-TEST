package ios.tronlink.com.tronlink.wallet.UITest.retry;

import org.testng.*;
import org.testng.xml.XmlSuite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

public class sqlTronlinkUI implements IReporter{
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://39.105.200.151:3306/AutoTestScan";
    static final String USER = "AutoTestScan";
    static final String PASS = "root";
    String time = "";
    int status = 0;
    String sucessClass = "";
    int sucessnum = 0;
    String failClass = "";
    int failnum = 0;
    int sum =0;

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        List<ITestResult> list = new ArrayList<ITestResult>();
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            for (ISuiteResult suiteResult : suiteResults.values()) {
                ITestContext testContext = suiteResult.getTestContext();
                IResultMap passedTests = testContext.getPassedTests();
                IResultMap failedTests = testContext.getFailedTests();
                IResultMap skippedTests = testContext.getSkippedTests();
                IResultMap failedConfig = testContext.getFailedConfigurations();
                list.addAll(this.listTestResult(passedTests));
                list.addAll(this.listTestResult(failedTests));
                list.addAll(this.listTestResult(skippedTests));
                list.addAll(this.listTestResult(failedConfig));
            }
        }
        this.sort(list);
        this.outputResult(list);
    }

    private ArrayList<ITestResult> listTestResult(IResultMap resultMap){
        Set<ITestResult> results = resultMap.getAllResults();
        return new ArrayList<ITestResult>(results);
    }

    private void sort(List<ITestResult> list){
        Collections.sort(list, new Comparator<ITestResult>() {
            @Override
            public int compare(ITestResult r1, ITestResult r2) {
                if(r1.getStartMillis()>r2.getStartMillis()){
                    return 1;
                }else{
                    return -1;
                }
            }
        });
    }

    private void outputResult(List<ITestResult> list){
        List success = new ArrayList();
        List fail = new ArrayList();
        StringBuffer sb = new StringBuffer();
        for (ITestResult result : list) {
            if (sb.length() != 0) {
                sb.append("\r\n");
            }
            if (result.getStatus() == 1) {
                success.add(result.getMethod().getMethodName());
            } else if(result.getStatus() == 2){
                fail.add(result.getMethod().getMethodName());
            }
//                sb.append(result.getTestClass().getRealClass().getName())
//                        .append(".")
//                        .append(result.getMethod().getMethodName())
////                        .append(" ")
////                        .append(this.formatDate(result.getStartMillis()))
////                        .append(" ")
////                        .append(result.getEndMillis()-result.getStartMillis())
////                        .append("毫秒 ")
//                        .append(this.getStatus(result.getStatus()));
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        time = formatter.format(date).toString();
        sucessnum = success.size();
        failnum = fail.size();
        sum = failnum + sucessnum;
        sucessClass = success.toString().replaceAll("(?:\\[|null|\\]| +)", "");
        if (fail.isEmpty()) {
            status = 1;
        } else {
            failClass = fail.toString().replaceAll("(?:\\[|null|\\]| +)", "");
            status = 2;
        }
        mysql();
//            output.write(success.toString().replaceAll("(?:\\[|null|\\]| +)", ""));
//            output.flush();
//            output.close();
    }


    public void mysql(){
        Connection conn = null;
        Statement stmt = null;
        String result = "";
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql = "INSERT INTO `AutoTestScan`.`tronlinkUI`(`time`, `status`, `sucessclass`, `sucessnum`,`failClass`,`failnum`,`sum`) VALUES ('"+time+"','"+status+"','"+sucessClass+"','"+sucessnum+"','"+failClass+"','"+failnum+"','"+sum+"')";
            stmt.executeUpdate(sql);
//            result = rs.toString();
            System.out.println(result);
//            rs.close();
            stmt.close();
            conn.close();

        }catch (Exception e){
            System.out.println(e);
        }
    }


    private String getStatus(int status){
        String statusString = null;
        switch (status) {
            case 1:
                statusString = "SUCCESS";
                break;
            case 2:
                statusString = "FAILURE";
                break;
            case 3:
                statusString = "SKIP";
                break;
            default:
                break;
        }
        return statusString;
    }

    private String formatDate(long date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }
}
