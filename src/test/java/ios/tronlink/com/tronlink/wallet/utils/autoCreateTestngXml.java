package ios.tronlink.com.tronlink.wallet.utils;

import android.com.utils.AppiumTestCase;
import android.com.utils.Configuration;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.tron.protos.Protocol;
import org.tron.trident.core.ApiWrapper;
import org.tron.trident.proto.Chain;
import org.tron.trident.proto.Response;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class autoCreateTestngXml {
    private String reportPath = "src/test/resources/tronlink-ios.xml";
    private String adb = "adb";
    private String packagesName = "<package name=\"ios.tronlink.com.tronlink.wallet.regression.*\"></package>";
    private String preClass = "<class name=\"ios.tronlink.com.tronlink.wallet.";
    private String afterClass = "\"></class>";
    private List<String> dirList = new ArrayList<>();
    private String platformName = "iOS";
    private Boolean noReset = false;
    private Integer webDriverPort = 8100;
    private Integer port = 4700;
    private Integer bpPort = 2251;
    List<String> iosDeviceNameList = new ArrayList<>();
    List<String> iosDeviceUdidList = new ArrayList<>();
    static HttpClient httpClient;
    static HttpPost httppost;
    static HttpGet httpGet;
    static HttpResponse response;
    static Integer connectionTimeout = 2000;
    static Integer soTimeout = 2000;
    static String transactionString;
    static String transactionSignString;
    static JSONObject responseContent;
    static JSONObject signResponseContent;
    static JSONObject transactionApprovedListContent;
    static List<String> taskClassNameList = new ArrayList<>();
    static List<String> taskSingleClassNameList = new ArrayList<>();
    static List<String> removeClassNameList = new ArrayList<>();
    private String httpnode = "http://" +Configuration.getByPath("testng.conf").getString("nileex.httpnode");
    private String foundationAccountKey = Configuration.getByPath("testng.conf").getString("foundationAccount.key");
    private String foundationAccountAddress = Configuration.getByPath("testng.conf").getString("foundationAccount.address");
    static String tokenId = Configuration.getByPath("testng.conf").getString("foundationAccount.tokenId");
    static String shieldTokenId = Configuration.getByPath("testng.conf").getString("foundationAccount.shieldTokenId");
    public static AtomicInteger multiSignIndex = new AtomicInteger(3);

    static {
        PoolingClientConnectionManager pccm = new PoolingClientConnectionManager();
        pccm.setDefaultMaxPerRoute(20);
        pccm.setMaxTotal(100);

        httpClient = new DefaultHttpClient(pccm);
    }

    public   List<String> GetIosDeviceNameList(String cmd) throws IOException {
        Process process = Runtime.getRuntime().exec(cmd);
        InputStreamReader isr=new InputStreamReader(process.getInputStream());
        Scanner s=new Scanner(isr);
        List<String> deviceNamesList = new ArrayList<>();
        String str;
        while(s.hasNext()){
            str = s.next();
//            if (str.equals("List")||str.equals("of")||str.equals("devices")||str.equals("attached")||str.equals("device")||str.equals("unauthorized")){
//                continue;
//            }
            deviceNamesList.add(str);
        }
        return deviceNamesList;
    }


    @BeforeClass
    public void beforeClass() throws IOException{
        List<String> devicelist = GetIosDeviceNameList("idevice_id -l");
        System.out.println("-----------------\n" + devicelist + "\n-----------------\n");
        iosDeviceNameList = devicelist;
        dirList.add("regression");
        beforeWrite();
    }




    @Test(enabled = true)
    public void createXml() throws IOException {
        HashMap<String,String> testAccountList = new HashMap<>();


        testAccountList.put("TS8o6WcHroSnzWNt4AiserAuVkye5Msvcm","f88184cfc003612d02b94956bccde12b8086c5010b3401357e7bdc8dd7727f4d");
        testAccountList.put("TBtMRD79NkLyAvMkCTTj5VC5KZnz2Po2XZ","71951c4a6b1d827ee9180ddd46d61b9963c2763737f3d3724049c6ae50e5efed");

        StringBuilder sb = new StringBuilder();
        String deviceList = AppiumTestCase.cmdReturn("idevice_id -l");
        System.out.println("----------------------------");
        System.out.println(deviceList);
        System.out.println("----------------------------");
//        String testCaseDir = "src/test/java/ios/tronlink/com/tronlink/wallet/regression";
        String testCaseDir = "src/test/java/ios/tronlink/com/tronlink/wallet/lessImport";
        taskSingleClassNameList = findNameList(taskSingleClassNameList,testCaseDir,1);
        taskClassNameList= taskSingleClassNameList;

//        removeClassNameList.add("DappSendTrxTest");
//        taskClassNameList = removeSingleClass(taskSingleClassNameList, removeClassNameList);
        String classContent = "";
        for (int i = 0; i < taskClassNameList.size();i++) {
            classContent = classContent + "            " + preClass + taskClassNameList.get(i).substring(0,taskClassNameList.get(i).length() - 5) + afterClass + "\n";
        }


//        String testCaseDir = "src/test/java/ios/tronlink/com/tronlink/wallet/regression";
//        taskClassNameList = findNameList(taskClassNameList,testCaseDir,1);
//
//        String classContent = "";
//        String classNoColdWallet = "";
//        for (int i = 0; i < taskClassNameList.size();i++) {
//            classContent = classContent + "            " + preClass + taskClassNameList.get(i).substring(0,taskClassNameList.get(i).length() - 5) + afterClass + "\n";
//            if (taskClassNameList.get(i).substring(0,taskClassNameList.get(i).length() - 5).contains("ZColdWalletTest")){
//                continue;
//            }
//            classNoColdWallet = classNoColdWallet + "            " + preClass + taskClassNameList.get(i).substring(0,taskClassNameList.get(i).length() - 5) + afterClass + "\n";
//        }
//
//        System.out.println("\nclassNoColdWallet " +classNoColdWallet);


//        System.out.println("classContent " +classContent);


            Iterator<HashMap.Entry<String, String>> entries = testAccountList.entrySet().iterator();
            for (Iterator<String> it = iosDeviceNameList.iterator(); it.hasNext()&&entries.hasNext(); ) {

                HashMap.Entry<String, String> entry = entries.next();
                String udid = it.next();
                if (!deviceList.contains(udid)){
                    continue;
                }
                AppiumTestCase.cmdReturn("ideviceinstaller -U com.tronlink.hdwallet -u " + udid);
                System.out.print("\nUninstall  " + udid + " Success\n");
                AppiumTestCase.cmdReturn("ideviceinstaller -i Tronlink.ipa -u " + udid);
                System.out.print("\nInstall " + udid + " Success\n");

                sb.append("    <test name= \"" + udid + "\">\n");
                String platformVersion = getDeviceVersion(udid);
                String deviceName = getDeviceName(udid);
                sb.append("        <parameter name=\"port\"  value=\"" + port++ + "\"/>\n");
                sb.append("        <parameter name=\"udid\" value=\"" + udid + "\"/>\n");
                sb.append(
                        "        <parameter name=\"platformName\" value=\"" + platformName + "\"/>\n");
                sb.append("        <parameter name=\"platformVersion\" value= \"" + platformVersion
                        + "\"/>\n");
                sb.append(
                        "        <parameter name=\"deviceName\" value= \"" + deviceName + "\"/>\n");
                sb.append(
                        "        <parameter name=\"noReset\" value=\"" + noReset.toString() + "\"/>\n");
                sb.append(
                        "        <parameter name=\"webDriverPort\"  value=\"" + webDriverPort++ + "\"/>\n");
                sb.append(
                        "        <parameter name=\"bpPort\"  value=\"" + bpPort++ + "\"/>\n");
                sb.append(
                        "        <parameter name=\"automationName\"  value=\"XCUITest\"/>\n");
                sb.append(
                        "        <parameter name=\"xcodeOrgId\"  value=\"36ZRMVQ2ZY\"/>\n");
                sb.append(
                        "        <parameter name=\"xcodeSigningId\"  value=\"iPhone Developer\"/>\n");
                sb.append(
                        "        <parameter name=\"bundleId\"  value=\"" + "com.tronlink.hdwallet"
                                + "\"/>\n");
                sb.append(
                        "        <parameter name=\"privateKey\"  value=\"" + entry.getValue()
                                + "\"/>\n");
                sb.append(
                        "        <parameter name=\"address\"  value=\"" + entry.getKey()
                                + "\"/>\n");
                sb.append(
                        "        <parameter name=\"ownerPrivateKey\" value=\""
                                + Configuration.getByPath("testng.conf").getString("iosMultiSignAccount.owner" + multiSignIndex.get() + "PrivateKey")
                                + "\"/>\n");
                sb.append(
                        "        <parameter name=\"ownerAddress\" value=\""
                                + Configuration.getByPath("testng.conf").getString("iosMultiSignAccount.owner" + multiSignIndex.get() + "Address")
                                + "\"/>\n");
                sb.append(
                        "        <parameter name=\"multiSignPrivateKey\" value=\""
                                + Configuration.getByPath("testng.conf").getString("iosMultiSignAccount.multiSign" + multiSignIndex.get() + "PrivateKey")
                                + "\"/>\n");
                sb.append(
                        "        <parameter name=\"multiSignAddress\" value=\""
                                + Configuration.getByPath("testng.conf").getString("iosMultiSignAccount.multiSign" + multiSignIndex.get() + "Address")
                                + "\"/>\n");
                sb.append(
                        "        <parameter name=\"witnessKey\" value=\""
                                + Configuration.getByPath("testng.conf").getString("iosWitnessAccount.witness" + multiSignIndex.get() + "Key")
                                + "\"/>\n");
                sb.append(
                        "        <parameter name=\"witnessAddress\" value=\""
                                + Configuration.getByPath("testng.conf").getString("iosWitnessAccount.witness" + multiSignIndex.get() + "Address")
                                + "\"/>\n");
                sb.append(
                        "        <parameter name=\"witnessUrl\" value=\""
                                + Configuration.getByPath("testng.conf").getString("iosWitnessAccount.witness" + multiSignIndex.get() + "Url")
                                + "\"/>\n");
                multiSignIndex.addAndGet(1);
                sb.append("        <classes>\n");
                sb.append(classContent);
                sb.append("        </classes>\n");
                sb.append("    </test>\n");
//                String res = sb.toString();
//                System.out.println("----------------------------\nft111ag: \n----------------------------");
//                System.out.println(res);
                it.remove();
            }


        sb.append("</suite>");
        String res = sb.toString();
//        System.out.println("----------------------------\nftag: \n----------------------------");
//        System.out.println(res);

        try {
            Files.write((Paths.get(reportPath)), res.getBytes("utf-8"), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("----------------------------\nfail to write: \n----------------------------");
            e.printStackTrace();
        }
    }


    public void beforeWrite() {

        StringBuilder sb = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<!DOCTYPE suite SYSTEM \"http://testng.org/testng-1.0.dtd\">\n"
                + "<suite name=\"ios.com.tronlink\" parallel=\"tests\" thread-count=\"" + iosDeviceNameList.size() + "\">\n"
                + "    <listeners>\n"
                + "        <listener class-name=\"ios.tronlink.com.tronlink.wallet.UITest.retry.RetryListener\"/>\n"
                + "    </listeners>\n");
        String res = sb.toString();
        try {
            Files.write((Paths.get(reportPath)), res.getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test(enabled = true)
    public void sendCoinToTestCount() throws IOException{
        HashMap<String,String> testAccountList = new HashMap<>();
            testAccountList.put("TS8o6WcHroSnzWNt4AiserAuVkye5Msvcm","f88184cfc003612d02b94956bccde12b8086c5010b3401357e7bdc8dd7727f4d");
            testAccountList.put("TBtMRD79NkLyAvMkCTTj5VC5KZnz2Po2XZ","71951c4a6b1d827ee9180ddd46d61b9963c2763737f3d3724049c6ae50e5efed");

        Long balance = 0L;
        Long targetAmount = 6999000000L;
        Long tokenBalance = 0L;
        Long targetTokenAmount = 900000000L;
        for (HashMap.Entry entry : testAccountList.entrySet()) {
            try {
                balance = 0L;
                balance = getBalance(httpnode, entry.getKey().toString());
                tokenBalance = 0L;
                tokenBalance = getTokenBalance(httpnode,entry.getKey().toString());
            } catch (Exception e) {
                System.out.println("查询余额出错！！！---->" + entry.getKey().toString());
                System.out.print(e + "\n");
            }
            System.out.print("address: " + entry.getKey().toString()  + "  TRX balance: " + balance + "\n");

            if (balance < targetAmount) {
                System.out.print("\ntargetAmount:" + targetAmount + "\n");
                System.out.print("balance:" + balance + "\n");
                System.out.println("sendTRX To: ---->  " + entry.getKey().toString());
                sendTRXCoin(foundationAccountAddress,entry.getKey().toString(),targetAmount - balance,foundationAccountKey);
            }

            System.out.print("address: " + entry.getKey().toString()  + " TRC10 balance: " + tokenBalance + "\n");

            if (tokenBalance < targetTokenAmount) {
                System.out.println("sendTRC10 To: ---->  " + entry.getKey().toString());
                sendTRC10Coin(foundationAccountAddress,entry.getKey().toString(),tokenId,targetTokenAmount - tokenBalance,foundationAccountKey);
            }

        }

    }


    public static String sendTRXCoin(String fromAddress, String toAddress,
                                     Long amount, String fromKey){
        try {
            ApiWrapper wrapper = new ApiWrapper("grpc.nile.trongrid.io:50051", "grpc.nile.trongrid.io:50061", fromKey);

            Response.TransactionExtention transactionExtention = wrapper.transfer( fromAddress, toAddress,amount);

            Chain.Transaction transaction = wrapper.signTransaction(transactionExtention);

            String broadcast = wrapper.broadcastTransaction(transaction);
            System.out.println("TransferContract:" + broadcast);

            return  broadcast;

        }catch (Exception e){
            System.out.println("TransferFail:" + e.getMessage());
        }
        return null;
    }

    public static String sendTRC10Coin(String fromAddress, String toAddress,
                                       String trc10TokenId, Long amount, String fromKey){
        try {


            ApiWrapper wrapper = new ApiWrapper("grpc.nile.trongrid.io:50051", "grpc.nile.trongrid.io:50061", fromKey);

            Integer trc10Token = Integer.parseInt(trc10TokenId);

            Response.TransactionExtention transactionExtention = wrapper
                    .transferTrc10(fromAddress,toAddress,trc10Token,amount);

            Chain.Transaction  transaction = wrapper.signTransaction(transactionExtention);


            String broadcast = wrapper.broadcastTransaction(transaction);
            System.out.println("Transaction trc10 token :" + broadcast);
            return  broadcast;

        }catch (Exception e){
            System.out.println("TransferFail Transaction trc10 :" + e.getMessage());
        }
        return null;
    }




    public static HttpResponse createConnect(String url, JsonObject requestBody) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            httppost = new HttpPost(url);

            if (requestBody != null) {
                StringEntity entity = new StringEntity(requestBody.toString(), Charset.forName("UTF-8"));
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                httppost.setEntity(entity);
            }
            response = httpClient.execute(httppost);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }




    public static JSONObject parseResponseContent(HttpResponse response) {
        try {
            String result = EntityUtils.toString(response.getEntity());
            StringEntity entity = new StringEntity(result, Charset.forName("UTF-8"));
            response.setEntity(entity);
            JSONObject obj = JSONObject.parseObject(result);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static HttpResponse createConnectGet(String url, Map<String,String> param) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();
            httpGet = new HttpGet(uri);
            response = httpclient.execute(httpGet);

        } catch (Exception e) {
            e.printStackTrace();
            httpGet.releaseConnection();
            return null;
        }
        return response;
    }

    public static Long getBalance(String httpNode, String queryAddress) {
        try {
            String requestUrl =  httpNode + "/wallet/getaccount";
            Map<String,String> map = new HashMap<String,String>();
            map.put("address",queryAddress);
            map.put("visible","true");
            response = createConnectGet(requestUrl, map);
        } catch (Exception e) {
            e.printStackTrace();
            httpGet.releaseConnection();
            return null;
        }
        responseContent = parseResponseContent(response);
        httpGet.releaseConnection();
        return Long.parseLong(responseContent.get("balance").toString());
    }

    public static Long getTokenBalance(String httpNode, String queryAddress) {
        try {
            String requestUrl = httpNode + "/wallet/getaccount";
            Map<String,String> map = new HashMap<String,String>();
            map.put("address",queryAddress);
            map.put("visible","true");
            response = createConnectGet(requestUrl, map);

        } catch (Exception e) {
            e.printStackTrace();
            httpGet.releaseConnection();
            return null;
        }
        responseContent = parseResponseContent(response);
        JSONArray tokenArray = responseContent.getJSONArray("assetV2");
        for (int i = 0; i < tokenArray.size();i++) {
            if (String.valueOf(tokenArray.getJSONObject(i).get("key")).equals(tokenId)) {
//                 System.out.print("TRC10 token " + Long.parseLong(tokenArray.getJSONObject(i).get("value").toString()) + "\n");
                return Long.parseLong(tokenArray.getJSONObject(i).get("value").toString());
            }
        }
        httpGet.releaseConnection();
        return 0L;
    }


    public static String getDeviceName(String udid) {
        String deviceName = "";
        try {
            deviceName = AppiumTestCase.cmdReturn("ideviceinfo -u " + udid + "  -k DeviceName");
        } catch (Exception e) {
            System.out.print(e);
        }
        return deviceName;
    }

    public static String getDeviceVersion(String udid) {
        String deviceVersion = "";
        try {
            deviceVersion = AppiumTestCase.cmdReturn("ideviceinfo -u " + udid + "  -k ProductVersion");
        } catch (Exception e) {
            System.out.print(e);
        }
        return deviceVersion;
    }

    public static List<String> findNameList(List<String> nameList,String pathName,int depth) throws IOException{
        //List<String> nameList = new ArrayList<>();
        String[] dirNameArray = pathName.split("/");
        String dirName = dirNameArray[dirNameArray.length - 1];
        int filecount=0;
        //获取pathName的File对象
        File dirFile = new File(pathName);
        //判断该文件或目录是否存在，不存在时在控制台输出提醒
        if (!dirFile.exists()) {
            System.out.println("do not exit");
            return null;
        }
        //判断如果不是一个目录，就判断是不是一个文件，时文件则输出文件路径
        if (!dirFile.isDirectory()) {
            if (dirFile.isFile()) {
                System.out.println(dirFile.getCanonicalFile());
            }
            return null;
        }

        for (int j = 0; j < depth; j++) {
            System.out.print("  ");
        }
        System.out.print("|--");
        System.out.println(dirFile.getName());
        //获取此目录下的所有文件名与目录名
        String[] fileList = dirFile.list();
        int currentDepth=depth+1;
        for (int i = 0; i < fileList.length; i++) {
            //遍历文件目录
            String string = fileList[i];
            //File("documentName","fileName")是File的另一个构造器
            File file = new File(dirFile.getPath(),string);
            String name = file.getName();
            //如果是一个目录，搜索深度depth++，输出目录名后，进行递归
            if (file.isDirectory()) {
                //递归
                findNameList(nameList,file.getCanonicalPath(),currentDepth);
            }else{
                //如果是文件，则直接输出文件名
                for (int j = 0; j < currentDepth; j++) {
                    System.out.print("   ");
                }
                if (!name.contains(".DS_Store")){
                    System.out.print("|--");
                    System.out.println(name);
                    nameList.add(dirName + "." + name);
                }else {
                    System.out.println("found DS_Store !!!");
                }


            }
        }

        Collections.sort(nameList);
        return nameList;
    }

    public List<String> removeSingleClass(List<String> singleClassList, List<String> removeList) {
        for (int i = 0 ; i < singleClassList.size();i++) {
            for (int j = 0; j < removeList.size();j++) {
                if (singleClassList.get(i).contains(removeList.get(j))) {
                    singleClassList.remove(i);
                    continue;
                }
            }
        }
        return singleClassList;
    }





}
