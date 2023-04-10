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
            packagesNameList.add("<package name=\"android.com.tronlink.wallet.mainTest.*\"></package>");
            packagesNameList.add("<package name=\"android.com.tronlink.wallet.committeeProposal.*\"></package>");
            packagesNameList.add("<package name=\"android.com.tronlink.wallet.multiSignatureTransaction.*\"></package>");
            packagesNameList.add("<package name=\"android.com.tronlink.wallet.regression.*\"></package>");
//            packagesNameList.add("<package name=\"android.com.tronlink.wallet.lessImport.*\"></package>");
            deviceNameList = AppiumTestCase.getDeviceList(adb + " devices");
        } catch (Exception e) {
            adb = "/Users/tron/Library/Android/sdk/platform-tools/adb";
            deviceNameList = AppiumTestCase.getDeviceList(adb + "  devices");
        }
        beforeWrite();
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


    public static HttpResponse sendCoin(String httpNode, String fromAddress, String toAddress,
        Long amount, String fromKey) {
      try {
            final String requestUrl =  httpNode + "/wallet/createtransaction";
            JsonObject userBaseObj2 = new JsonObject();
            userBaseObj2.addProperty("to_address", toAddress);
            userBaseObj2.addProperty("owner_address", fromAddress);
            userBaseObj2.addProperty("amount", amount);
            userBaseObj2.addProperty("visible", true);
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



//    public static HttpResponse freezeBalance(String httpNode, String ownerAddress,
//                                             Long frozenBalance, Integer frozenDuration, Integer resourceCode, String receiverAddress,
//                                             String fromKey) {
//        try {
//            final String requestUrl = "http://" + httpNode + "/wallet/freezebalance";
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



//  /**
//   * constructor.
//   */
//  public static HttpResponse sendShieldCoin(String httpNode, String publicZenTokenOwnerAddress,
//      long fromAmount, String publicZenTokenKey, String shieldAddress,String sendAmount) {
//    try {
//      final String requestUrl = "http://" + httpNode + "/wallet/createshieldedtransaction";
//
//      Map<String, Object> map = new HashMap<String, Object>();
//      map.put("transparent_from_address", publicZenTokenOwnerAddress);
//      map.put("from_amount", fromAmount);
//      map.put("ovk", "030c8c2bc59fb3eb8afb047a8ea4b028743d23e7d38c6fa30908358431e2314d");
//      ArrayList<Object> noteList = new ArrayList<>();
//      final Map<String, Object> note = new HashMap<String, Object>();
//      Map<String, Object> noteInfo = new HashMap<String, Object>();
//      noteInfo.put("value", sendAmount);
//      noteInfo.put("payment_address", shieldAddress);
//      noteInfo.put("rcm", "c094341876e1c857d45f7b7083912e342a0d19a499236662a02ae8ef90651007");
//      noteInfo.put("memo", "Testgroup send shield coin");
//      note.put("note", noteInfo);
//      noteList.add(note);
//      map.put("shielded_receives", noteList);
//
//
//      String jsonStr = new Gson().toJson(map);
//      JsonObject jsonObj = new JsonParser().parse(jsonStr).getAsJsonObject();
//      response = createConnect(requestUrl, jsonObj);
//      transactionString = EntityUtils.toString(response.getEntity());
//      transactionSignString = gettransactionsign(httpNode, transactionString, publicZenTokenKey);
//      response = broadcastTransaction(httpNode, transactionSignString);
//    } catch (Exception e) {
//      e.printStackTrace();
//      httppost.releaseConnection();
//      return null;
//    }
//    return response;
//  }



//    @Test(enabled = false)
//    public void sendTrzToWitnessAccountToTestShiledTransaction() throws IOException{
//        List<String> witnessAccountAddressList = new ArrayList<>();
//        for (int androidDeviceNum = 1; androidDeviceNum <= 5; androidDeviceNum++) {
//            witnessAccountAddressList.add(Configuration.getByPath("testng.conf")
//                    .getString("androidWitnessAccount.witness" + androidDeviceNum + "Address"));
//        }
//        for (int iosDeviceNum = 1; iosDeviceNum <= 3; iosDeviceNum++) {
//            witnessAccountAddressList.add(Configuration.getByPath("testng.conf")
//                    .getString("iosWitnessAccount.witness" + iosDeviceNum + "Address"));
//        }
//        Long targetShieldTokenAmount = 500000000L;
//        Long shieldTokenBalance;
//        for (int i = 0; i < witnessAccountAddressList.size(); i++) {
//            shieldTokenBalance = getTokenBalance(httpnode,witnessAccountAddressList.get(i),shieldTokenId);
//            if (shieldTokenBalance <= targetShieldTokenAmount * 2 / 5) {
//                transferAsset(httpnode,foundationAccountAddress,witnessAccountAddressList.get(i),
//                        shieldTokenId,targetShieldTokenAmount,foundationAccountKey);
//            }
//        }
//
//    }

}
