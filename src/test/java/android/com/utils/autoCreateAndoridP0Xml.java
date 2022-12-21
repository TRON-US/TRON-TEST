package android.com.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
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
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class autoCreateAndoridP0Xml {
    private String reportPath = "src/test/resources/android-p0.xml";
    private String adb = "adb";
    private String preClass = "<class name=\"android.com.tronlink.wallet.";
    private String afterClass = "\"></class>";
    private String platformName = "Android";
    private Boolean noReset = false;
    private Boolean isMultiDevices = false;
    private Integer systemPort = 8200;
    private Integer port = 4800;
    List<String> deviceNameList = new ArrayList<>();
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
    static List<String> classNameList = new ArrayList<>();
    private String httpnode = "http://" +Configuration.getByPath("testng.conf").getString("nileex.httpnode");
    private String dappChainHttpNode = Configuration.getByPath("testng.conf").getString("nileex.dappChainHttpNode");
    private String foundationAccountKey = Configuration.getByPath("testng.conf").getString("foundationAccount.key");
    private String foundationAccountAddress = Configuration.getByPath("testng.conf").getString("foundationAccount.address");
    public static AtomicInteger multiSignIndex = new AtomicInteger(1);
    static String tokenId = Configuration.getByPath("testng.conf").getString("foundationAccount.tokenId");
    private Boolean isMuiltDevices = false;

    static {
        PoolingClientConnectionManager pccm = new PoolingClientConnectionManager();
        pccm.setDefaultMaxPerRoute(20);
        pccm.setMaxTotal(100);

        httpClient = new DefaultHttpClient(pccm);
    }


    @BeforeClass
    public void beforeClass() throws IOException{
        try {
            deviceNameList = AppiumTestCase.getDeviceList(adb + " devices");
        } catch (Exception e) {
            adb = Configuration.getByPath("testng.conf").getString("envPath.adb");
            deviceNameList = AppiumTestCase.getDeviceList(adb + "  devices");
        }
        beforeWrite();
    }


    @Test(enabled = true)
    public void sendCoinToTestCount() throws IOException{
        HashMap<String,String> testAccountList = new HashMap<>();
        testAccountList.put("TMhGDU7NiXwckCW64PqAvWFuC2kR1WSF5J","11c7013416aac83fd6070abb8ffceb0ad102d9f87dfc9c98308b0fd47e8c3a1a");
        testAccountList.put("TEtG9fnVi2qythiog6owPrg4sD9rwFBQBN","8f3839e21b4ada348da3d85ccc72e1f1898a39b877f0f6f5b35137588a344345");
        testAccountList.put("TR8CyAPJFMjCvphCVuWeeVxBh5iTG7VWxe","cfd889566341aea937737ecf4bc35f9be7c5b43f594c9a230a0348183472245e");
        testAccountList.put("TMDs8oTj8mVnakqiVyDKdp2ruWPdFeDgbq","7652071f95c376e6d1100f9fed5c520f22262c1530f328bb1c3ed10bad771e68");
        testAccountList.put("TDf3JZtjDeEqsFdPGp6vT9meG3JxMwmXwA","0ea138885c1fb2b6adaad51033c8876df0e37ccf7dd322cfad5504d671fb2a79");

        Long balance = 0L;
        Long targetAmount = 6998000000L;
        Long tokenBalance = 0L;
        Long targetTokenAmount = 500000000L;
        for (HashMap.Entry entry : testAccountList.entrySet()) {
            try {
                balance = 0L;
                balance = getBalance(httpnode, entry.getKey().toString());
                tokenBalance = 0L;
                tokenBalance = getTokenBalance(httpnode,entry.getKey().toString());
            } catch (Exception e) {
                System.out.print(e + "\n");
            }
            System.out.print("TokenBalance:" + tokenBalance + "\n");
            System.out.print("TRXbalance:" + balance + "\n");
            if (balance <= targetAmount * 3 / 5) {
                sendCoin(httpnode,foundationAccountAddress,entry.getKey().toString(),targetAmount - balance,foundationAccountKey);
            }

            if (tokenBalance <= targetTokenAmount * 3 / 5) {
                transferAsset(httpnode,foundationAccountAddress,entry.getKey().toString(),tokenId,targetTokenAmount - tokenBalance,foundationAccountKey);
            }

        }

    }


    @Test(enabled = true)
    public void createXml() throws IOException {


        String testCaseDir = "src/test/java/android/com/tronlink/wallet/regression";
        classNameList = findNameList(classNameList,testCaseDir,1);
        testCaseDir = "src/test/java/android/com/tronlink/wallet/committeeProposal";
        classNameList = findNameList(classNameList,testCaseDir,1);
        testCaseDir = "src/test/java/android/com/tronlink/wallet/multiSignatureTransaction";
        classNameList = findNameList(classNameList,testCaseDir,1);
        testCaseDir = "src/test/java/android/com/tronlink/wallet/mainTest";
        classNameList = findNameList(classNameList,testCaseDir,1);
        Integer deviceIndex = 0;
        List<List<String>> classContent = new ArrayList<>();

        for (int i = 0; i < classNameList.size();i++) {
            if (deviceIndex == deviceNameList.size()) {
                deviceIndex = 0;
            }
            if (classContent.size() <= deviceIndex){
                classContent.add(new ArrayList<>());
            }
            classContent.get(deviceIndex++).add( "            " + preClass + classNameList.get(i).substring(0,classNameList.get(i).length() - 5) + afterClass + "\n");

        }


        if (deviceNameList.size()>1){
            isMuiltDevices = true;
        }

        StringBuilder sb = new StringBuilder();

        {
            Set<Integer> accIndex = new HashSet<>();
            Random random = new Random();
            Integer index = random.nextInt(5)+1;

            for (Iterator<String> it = deviceNameList.iterator(); it.hasNext(); ) {
                while (accIndex.contains(index)){
                    random = new Random();
                    index = random.nextInt(5)+1;
                }
                accIndex.add(index);
                String udid = it.next();
                sb.append("    <test name= \"" + udid + "\">\n");

                adb = "adb -s " + udid;
                AppiumTestCase.cmdReturn(adb + " uninstall com.tronlinkpro.wallet");
                System.out.print("Uninstall succesfully\n");

                AppiumTestCase.cmdReturn(adb + " install TronLink.apk");
                System.out.print("Install succesfully");
                String platformVersion = AppiumTestCase
                    .cmdReturn(adb + " shell getprop ro.build.version.release");
                String deviceName = AppiumTestCase
                    .cmdReturn(adb + " shell getprop ro.product.name");
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
                    "        <parameter name=\"systemPort\"  value=\"" + systemPort++ + "\"/>\n");
                sb.append(
                    "        <parameter name=\"address\"  value=\""
                        + Configuration.getByPath("testng.conf").getString("androidMultiSignAccount.address" + multiSignIndex.get())
                        + "\"/>\n");
                sb.append(
                    "        <parameter name=\"privateKey\"  value=\""
                        + Configuration.getByPath("testng.conf").getString("androidMultiSignAccount.privateKey" + multiSignIndex.get())
                        + "\"/>\n");
                sb.append(
                    "        <parameter name=\"ownerPrivateKey\" value=\""
                        + Configuration.getByPath("testng.conf").getString("androidMultiSignAccount.owner" + multiSignIndex.get() + "PrivateKey")
                        + "\"/>\n");
                sb.append(
                    "        <parameter name=\"ownerAddress\" value=\""
                        + Configuration.getByPath("testng.conf").getString("androidMultiSignAccount.owner" + multiSignIndex.get() + "Address")
                        + "\"/>\n");
                sb.append(
                    "        <parameter name=\"multiSignPrivateKey\" value=\""
                        + Configuration.getByPath("testng.conf").getString("androidMultiSignAccount.multiSign" + multiSignIndex.get() + "PrivateKey")
                        + "\"/>\n");
                sb.append(
                    "        <parameter name=\"multiSignAddress\" value=\""
                        + Configuration.getByPath("testng.conf").getString("androidMultiSignAccount.multiSign" + multiSignIndex.get() + "Address")
                        + "\"/>\n");
                sb.append(
                    "        <parameter name=\"witnessKey\" value=\""
                        + Configuration.getByPath("testng.conf").getString("androidWitnessAccount.witness" + multiSignIndex.get() + "Key")
                        + "\"/>\n");
                sb.append(
                    "        <parameter name=\"witnessAddress\" value=\""
                        + Configuration.getByPath("testng.conf").getString("androidWitnessAccount.witness" + multiSignIndex.get() + "Address")
                        + "\"/>\n");
                sb.append(
                    "        <parameter name=\"witnessUrl\" value=\""
                        + Configuration.getByPath("testng.conf").getString("androidWitnessAccount.witness" + multiSignIndex.get() + "Url")
                        + "\"/>\n");
                sb.append(
                    "        <parameter name=\"shieldSK\" value=\""
                        + Configuration.getByPath("testng.conf").getString("androidShieldAccount.sk" + multiSignIndex.get())
                        + "\"/>\n");
                sb.append(
                    "        <parameter name=\"shieldAddress\" value=\""
                        + Configuration.getByPath("testng.conf").getString("androidShieldAccount.shieldAddress" + multiSignIndex.get())
                        + "\"/>\n");
                sb.append("    <groups>\n");
                sb.append("    <run>\n");
                sb.append("    <include name=\"P0\"/>\n");
                sb.append("    </run>\n");
                sb.append("    </groups>\n");
                multiSignIndex.addAndGet(1);
                sb.append("        <classes>\n");
                for (int i = 0; i < classContent.get(deviceIndex-1).size();i++) {
                    sb.append(classContent.get(deviceIndex-1).get(i));
                }
                sb.append(    "        </classes>\n" +
                    "    </test>\n");
                it.remove();
                deviceIndex++;
            }
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
            + "<suite name=\"android.com.tronlink\" parallel=\"tests\" thread-count=\"" + deviceNameList.size() + "\">\n"
            + "    <listeners>\n"
            + "        <listener class-name=\"android.com.wallet.UITest.retry.RetryListener\"/>\n"
            + "    </listeners>\n");       String res = sb.toString();
        try {
            Files.write((Paths.get(reportPath)), res.getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HttpResponse sendCoin(String httpNode, String fromAddress, String toAddress,
                                        Long amount, String fromKey) {
        try {
            final String requestUrl =  httpNode + "/wallet/createtransaction";
            JsonObject userBaseObj2 = new JsonObject();
            userBaseObj2.addProperty("to_address", toAddress);
            userBaseObj2.addProperty("owner_address", fromAddress);
            userBaseObj2.addProperty("amount", amount);
            userBaseObj2.addProperty("visible", true);
//            System.out.print("userBaseObj2:" + userBaseObj2.toString());
            response = createConnect(requestUrl, userBaseObj2);
            transactionString = EntityUtils.toString(response.getEntity());
            System.out.println("\nSend amount: " + amount);
            transactionSignString = gettransactionsign(httpNode, transactionString, fromKey);
            response = broadcastTransaction(httpNode, transactionSignString);
        } catch (Exception e) {
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
            System.out.println("\nSend amount: " + amount);
            System.out.println("\nSend asset_name: " + assetIssueById);
            transactionSignString = gettransactionsign(httpNode, transactionString, fromKey);
            response = broadcastTransaction(httpNode, transactionSignString);
        } catch (Exception e) {
            e.printStackTrace();
            httppost.releaseConnection();
            return null;
        }
        return response;
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
            String requestUrl =  httpNode + "/wallet/gettransactionsign";
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
            String requestUrl =  httpNode + "/wallet/broadcasttransaction";

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


    public static Long getTokenBalance(String httpNode, String queryAddress) {
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
                System.out.print("|--");
                System.out.println(name);
                if (!name.contains(".DS_")){
                    nameList.add(dirName + "." + name);
                }

            }
        }
        return nameList;
    }



}
