package ios.tronlink.com.tronlink.wallet.utils;

import android.com.utils.AppiumTestCase;

import android.com.utils.Configuration;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;

import java.io.InputStreamReader;
import java.net.URI;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
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

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
    public static AtomicInteger multiSignIndex = new AtomicInteger(1);

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
    public void sendCoinToTestCount() throws IOException{
        HashMap<String,String> testAccountList = new HashMap<>();
        testAccountList.put("TWv2FEsoPp5XKxujVHffoNwksgJSxvf3QG","6a77e8edd232f4102e4fcaca02234df7176a9398fdde1792ae5377b009482fca");
        testAccountList.put("TXhQk442CCGLydh6cfyfqvM6yJanEGeQj1","b50aa8ce2140be6995e79d657064e5a3983ac0a47bfdcbb5e9f4b930ba2996a5");
        testAccountList.put("TKktQcbjXsXZDKPYLvUm8sxox2cT83g5rP","d4446cf4ccfe02f165f0ba01e3d5a56546e41eebf26c3cfe33564bababeef74d");
        testAccountList.put("TBQUhYhdQpMRksBGAbpbTWSiE7WkGgy3Km","3999ce04f0ba5e05776d355b194f369a6d56f4fd7711a31adf2044690236bf5b");
        testAccountList.put("TALf34yjuLZjF1WQqCaUkf73X8WbhfiEyM","022f883a91a14567a8b1ad9722b73971f5c748586e951b7a8eed0ef6e29950ac");

        Long balance = 0L;
        Long targetAmount = 6999000000L;
        Long tokenBalance = 0L;
        Long targetTokenAmount = 500000000L;
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
            System.out.print("\nTokenBalance:" + tokenBalance + "\n");
            System.out.print("TRXBalance:" + balance + "\n");
            if (balance <= targetAmount) {
                sendCoin(httpnode,foundationAccountAddress,entry.getKey().toString(),targetAmount - balance,foundationAccountKey);
                //freezeBalance(httpnode,foundationAccountAddress,7000000000L,3,0,entry.getKey().toString(),foundationAccountKey);
            }

            if (tokenBalance <= targetTokenAmount * 3 / 5) {
                transferAsset(httpnode,foundationAccountAddress,entry.getKey().toString(),tokenId,targetTokenAmount - tokenBalance,foundationAccountKey);
            }

        }

    }

    @Test(enabled = true)
    public void createXml() throws IOException {
        HashMap<String,String> testAccountList = new HashMap<>();

        testAccountList.put("TWv2FEsoPp5XKxujVHffoNwksgJSxvf3QG","6a77e8edd232f4102e4fcaca02234df7176a9398fdde1792ae5377b009482fca");
        testAccountList.put("TXhQk442CCGLydh6cfyfqvM6yJanEGeQj1","b50aa8ce2140be6995e79d657064e5a3983ac0a47bfdcbb5e9f4b930ba2996a5");
        testAccountList.put("TKktQcbjXsXZDKPYLvUm8sxox2cT83g5rP","d4446cf4ccfe02f165f0ba01e3d5a56546e41eebf26c3cfe33564bababeef74d");
        testAccountList.put("TBQUhYhdQpMRksBGAbpbTWSiE7WkGgy3Km","3999ce04f0ba5e05776d355b194f369a6d56f4fd7711a31adf2044690236bf5b");
        testAccountList.put("TALf34yjuLZjF1WQqCaUkf73X8WbhfiEyM","022f883a91a14567a8b1ad9722b73971f5c748586e951b7a8eed0ef6e29950ac");

        StringBuilder sb = new StringBuilder();
        String deviceList = AppiumTestCase.cmdReturn("idevice_id -l");
        System.out.println("----------------------------");
        System.out.println(deviceList);
        System.out.println("----------------------------");
        String testCaseDir = "src/test/java/ios/tronlink/com/tronlink/wallet/regression";
//        String testCaseDir = "src/test/java/ios/tronlink/com/tronlink/wallet/lessImport";
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
                sb.append(
                        "        <parameter name=\"shieldSK\" value=\""
                                + Configuration.getByPath("testng.conf").getString("IosShieldAccount.sk" + multiSignIndex.get())
                                + "\"/>\n");
                sb.append(
                        "        <parameter name=\"shieldAddress\" value=\""
                                + Configuration.getByPath("testng.conf").getString("IosShieldAccount.shieldAddress" + multiSignIndex.get())
                                + "\"/>\n");
                sb.append(
                        "        <parameter name=\"publicShieldSK\" value=\""
                                + Configuration.getByPath("testng.conf").getString("IosShieldPublicAccount.privateKey" + multiSignIndex.get())
                                + "\"/>\n");
                sb.append(
                        "        <parameter name=\"publicShieldAddress\" value=\""
                                + Configuration.getByPath("testng.conf").getString("IosShieldPublicAccount.publicAddress" + multiSignIndex.get())
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



    public static HttpResponse sendCoin(String httpNode, String fromAddress, String toAddress,
                                        Long amount, String fromKey) {
//        System.out.println("\nhttpNode: " + httpNode + "\nfromAddress: " + fromAddress + "\ntoAddress: " + toAddress + "\namount: " + amount + "\nfromKey: " + fromKey);
        try {
            final String requestUrl =  httpNode + "/wallet/createtransaction";
            JsonObject userBaseObj2 = new JsonObject();
            userBaseObj2.addProperty("to_address", toAddress);
            userBaseObj2.addProperty("owner_address", fromAddress);
            userBaseObj2.addProperty("amount", amount);
            userBaseObj2.addProperty("visible", true);
            response = createConnect(requestUrl, userBaseObj2);
            transactionString = EntityUtils.toString(response.getEntity());
//            System.out.println("createConnect: " + response);
            transactionSignString = gettransactionsign(httpNode, transactionString, fromKey);
//            System.out.println("transactionSignString: " + transactionSignString);
            response = broadcastTransaction(httpNode, transactionSignString);
//            System.out.println("broadcastTransaction: " + parseResponseContent(response));
            System.out.println("\n\nSend TRX Amount: " + amount + "To Address: " + toAddress);

        } catch (Exception e) {
            System.out.println("\n\nSend TRX Failed: " + amount + "To Address: " + toAddress);
            e.printStackTrace();
            httppost.releaseConnection();
            return null;
        }
        return response;
    }

    /**
     * constructor.
     */
    public static HttpResponse transferAsset(String httpNode, String ownerAddress,
                                             String toAddress, String assetIssueById, Long amount, String fromKey) {

        try {
            final String requestUrl = httpNode + "/wallet/transferasset";
            JsonObject userBaseObj2 = new JsonObject();
            userBaseObj2.addProperty("owner_address", ownerAddress);
            userBaseObj2.addProperty("to_address", toAddress);
            userBaseObj2.addProperty("asset_name", assetIssueById);
            userBaseObj2.addProperty("amount", amount);
            userBaseObj2.addProperty("visible", true);
            response = createConnect(requestUrl, userBaseObj2);
            transactionString = EntityUtils.toString(response.getEntity());
            System.out.println("\n\nSend Token"+ assetIssueById  +" Amount: " + amount + "To Address: " + toAddress);
            transactionSignString = gettransactionsign(httpNode, transactionString, fromKey);
            response = broadcastTransaction(httpNode, transactionSignString);
        } catch (Exception e) {
            e.printStackTrace();
            httppost.releaseConnection();
            return null;
        }
        return response;
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


    public static String gettransactionsign(String httpNode, String transactionString,
                                            String privateKey) {
        try {
            String requestUrl = httpNode + "/wallet/gettransactionsign";
            JsonObject userBaseObj2 = new JsonObject();
            userBaseObj2.addProperty("transaction", transactionString);
            userBaseObj2.addProperty("privateKey", privateKey);
            response = createConnect(requestUrl, userBaseObj2);
            transactionSignString = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
            httppost.releaseConnection();
            return null;
        }
        return transactionSignString;
    }

    public static HttpResponse broadcastTransaction(String httpNode, String transactionSignString) {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            String requestUrl = httpNode + "/wallet/broadcasttransaction";

            httppost = new HttpPost(requestUrl);
            httppost.setHeader("Content-type", "application/json; charset=utf-8");
            httppost.setHeader("Connection", "Close");
            if (transactionSignString != null) {
                StringEntity entity = new StringEntity(transactionSignString, Charset.forName("UTF-8"));
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                httppost.setEntity(entity);
            }
            response = httpClient.execute(httppost);
        } catch (Exception e) {
            e.printStackTrace();
            httppost.releaseConnection();
            return null;
        }

        responseContent = parseResponseContent(response);
        Integer times = 0;

        while (times++ <= 10 && responseContent.getString("code") != null && responseContent
                .getString("code").equalsIgnoreCase("SERVER_BUSY")) {
            try {
                response = httpClient.execute(httppost);
            } catch (Exception e) {
                e.printStackTrace();
                httppost.releaseConnection();
                return null;
            }
            responseContent = parseResponseContent(response);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        httppost.releaseConnection();
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
            System.out.print("V2 token:" + String.valueOf(tokenArray.getJSONObject(i).get("key")));
            if (String.valueOf(tokenArray.getJSONObject(i).get("key")).equals(tokenId)) {
                return Long.parseLong(tokenArray.getJSONObject(i).get("value").toString());
            }
        }
        httpGet.releaseConnection();
        return 0L;
    }



//    public static HttpResponse freezeBalance(String httpNode, String ownerAddress,
//                                             Long frozenBalance, Integer frozenDuration, Integer resourceCode, String receiverAddress,
//                                             String fromKey) {
//        try {
//            final String requestUrl =  httpNode + "/wallet/freezebalance";
//            JsonObject userBaseObj2 = new JsonObject();
//            userBaseObj2.addProperty("owner_address", ownerAddress);
//            userBaseObj2.addProperty("frozen_balance", frozenBalance);
//            userBaseObj2.addProperty("frozen_duration", frozenDuration);
//            userBaseObj2.addProperty("visible", true);
//            if (resourceCode == 0) {
//                userBaseObj2.addProperty("resource", "BANDWIDTH");
//            }
//            if (resourceCode == 1) {
//                userBaseObj2.addProperty("resource", "ENERGY");
//            }
//            if (receiverAddress != null) {
//                userBaseObj2.addProperty("receiver_address", receiverAddress);
//            }
//            response = createConnect(requestUrl, userBaseObj2);
//            transactionString = EntityUtils.toString(response.getEntity());
//            System.out.print(transactionString);
//            transactionSignString = gettransactionsign(httpNode, transactionString, fromKey);
//            response = broadcastTransaction(httpNode, transactionSignString);
//        } catch (Exception e) {
//            e.printStackTrace();
//            httppost.releaseConnection();
//            return null;
//        }
//        return response;
//    }

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
