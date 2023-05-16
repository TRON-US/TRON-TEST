package android.com.utils;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.JsonObject;
import com.alibaba.fastjson.JSONObject;

import java.net.URI;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
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
import org.tron.trident.core.ApiWrapper;
import org.tron.trident.proto.Chain;
import org.tron.trident.proto.Response;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class autoCreateTestngXml {
    private String reportPath = "src/test/resources/tronlink-testng.xml";
    //private String adb = "/Users/tron/Library/Android/sdk/platform-tools/adb";
    private String adb = "adb";
    private List<String> packagesNameList = new ArrayList<>();
    private String platformName = "Android";
    private Boolean noReset = false;
    private Integer systemPort = 8200;
    private Integer port = 4800;
    List<String> deviceNameList = new ArrayList<>();
    static HttpClient httpClient;
    static HttpPost httppost;
    static HttpGet httpGet;
    static HttpResponse response;
    static ApiWrapper wrapper;
    static Integer connectionTimeout = 2000;
    static Integer soTimeout = 2000;
    static String transactionString;
    static String transactionSignString;
    static JSONObject responseContent;
    static JSONObject signResponseContent;
    static JSONObject transactionApprovedListContent;
    private Boolean isMultiDevices = false;
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


    @BeforeClass
    public void beforeClass() throws IOException{
        try {
//            packagesNameList.add("<package name=\"android.com.tronlink.wallet.mainTest.*\"></package>");
//            packagesNameList.add("<package name=\"android.com.tronlink.wallet.committeeProposal.*\"></package>");
//            packagesNameList.add("<package name=\"android.com.tronlink.wallet.multiSignatureTransaction.*\"></package>");
//            packagesNameList.add("<package name=\"android.com.tronlink.wallet.regression.*\"></package>");
            packagesNameList.add("<package name=\"android.com.tronlink.wallet.lessImport.*\"></package>");
            deviceNameList = AppiumTestCase.getDeviceList(adb + " devices");
        } catch (Exception e) {
            adb = "/Users/tron/Library/Android/sdk/platform-tools/adb";
            deviceNameList = AppiumTestCase.getDeviceList(adb + "  devices");
        }
        beforeWrite();
    }




    @Test(enabled = true)
    public void createXml() throws IOException {

       if (deviceNameList.size()>1){
           isMultiDevices = true;
       }

        StringBuilder sb = new StringBuilder();

        {
            Set<Integer> accIndex = new HashSet<>();
            Random random = new Random();
            Integer index = random.nextInt(4)+6;

            for (Iterator<String> it = deviceNameList.iterator(); it.hasNext(); ) {
                while (accIndex.contains(index)){
                    random = new Random();
                    index = random.nextInt(4)+6;
                }
                accIndex.add(index);

                String udid = it.next();

                sb.append("    <test name= \"" + udid + "\">\n");
                adb = "adb -s " + udid;
                AppiumTestCase.cmdReturn(adb + " uninstall com.tronlinkpro.wallet");
                System.out.print("Uninstall succesfully\n");
                //install app
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
                        + Configuration.getByPath("testng.conf").getString("androidMultiSignAccount.address" + index)
                        + "\"/>\n");
                sb.append(
                    "        <parameter name=\"privateKey\"  value=\""
                        + Configuration.getByPath("testng.conf").getString("androidMultiSignAccount.privateKey" + index)
                        + "\"/>\n");
                sb.append(
                    "        <parameter name=\"ownerPrivateKey\" value=\""
                        + Configuration.getByPath("testng.conf").getString("androidMultiSignAccount.owner" + index + "PrivateKey")
                        + "\"/>\n");
                sb.append(
                    "        <parameter name=\"ownerAddress\" value=\""
                        + Configuration.getByPath("testng.conf").getString("androidMultiSignAccount.owner" + index + "Address")
                        + "\"/>\n");
                sb.append(
                    "        <parameter name=\"multiSignPrivateKey\" value=\""
                        + Configuration.getByPath("testng.conf").getString("androidMultiSignAccount.multiSign" + index + "PrivateKey")
                        + "\"/>\n");
                sb.append(
                    "        <parameter name=\"multiSignAddress\" value=\""
                        + Configuration.getByPath("testng.conf").getString("androidMultiSignAccount.multiSign" + index + "Address")
                        + "\"/>\n");
                sb.append(
                    "        <parameter name=\"witnessKey\" value=\""
                        + Configuration.getByPath("testng.conf").getString("androidWitnessAccount.witness" + index + "Key")
                        + "\"/>\n");
                sb.append(
                    "        <parameter name=\"witnessAddress\" value=\""
                        + Configuration.getByPath("testng.conf").getString("androidWitnessAccount.witness" + index + "Address")
                        + "\"/>\n");
                sb.append(
                    "        <parameter name=\"witnessUrl\" value=\""
                        + Configuration.getByPath("testng.conf").getString("androidWitnessAccount.witness" + index + "Url")
                        + "\"/>\n");
                sb.append("        <packages>\n");
                for (int i = 0; i < packagesNameList.size();i++) {
                    sb.append("            " + packagesNameList.get(i) + "\n");
                }
                sb.append("        </packages>\n");
                sb.append("    </test>\n");
                it.remove();
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
            testAccountList.put("TSsaSxHnb3xLTop2A8LrDk1P896yiDeupe","cec7fc3c9c603ae6cdc026c777db037b8ca4995d451fa5fe7b2f19a0dc01cd98");
            testAccountList.put("TUvda1oqrNLbqDKhZDxDnrPhiDCdxem218","cf9933e99ee2b272147dd563c7f880de751c30d61cd6681a158f0a8056023d9b");
            testAccountList.put("TKEH31jJ2YQ3Bteh1ngjwdT8667ztyYPSp","223f2e4a3010286540d3c119d2a1d55b1d54248f63f1c4d9ccfbd0d105ab15c7");
            testAccountList.put("TAzrJHKa57nXnn3dZGFG87PDuWx12dY97s","844f7f5da381943403e8324db4fda13dce9af35b72cf2ea3846fafa12c5d9890");
            testAccountList.put("TWhc6AAh6BWRr3k5dV8iMvkp8ys7NHzXCk","6850fd0a0f2cb94167bf0507a738fa9eef51d6fdc65e8452039f711a4bdf3135");

        Long balance = 0L;
        Long targetAmount = 6998000000L;
        Long tokenBalance = 0L;
        Long targetTokenAmount = 900000000L;
        for (HashMap.Entry entry : testAccountList.entrySet()) {
            try {
                balance = 0L;
                balance = getBalance(httpnode, entry.getKey().toString());
                tokenBalance = 0L;
                tokenBalance = getTokenBalance(httpnode,entry.getKey().toString());
            } catch (Exception e) {
                System.out.print(e + "\n");
            }

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


    public static String sendTRXCoin(String fromAddress, String toAddress,
                                     Long amount, String fromKey){
        try {
            wrapper = new ApiWrapper("grpc.nile.trongrid.io:50051", "grpc.nile.trongrid.io:50061", fromKey);

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


            wrapper = new ApiWrapper("grpc.nile.trongrid.io:50051", "grpc.nile.trongrid.io:50061", fromKey);

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
          if (String.valueOf(tokenArray.getJSONObject(i).get("key")).equals(tokenId)) {
                return Long.parseLong(tokenArray.getJSONObject(i).get("value").toString());
            }
        }
        httpGet.releaseConnection();
        return 0L;
    }



}
