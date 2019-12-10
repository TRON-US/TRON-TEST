package activityServer.com.utils;

import android.com.utils.AppiumTestCase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import java.io.FileReader;
import java.io.StringReader;
import java.net.URI;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.openqa.selenium.json.Json;
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

public class api {
    public static String HOME_HOST = "https://list.tronlink.org";//host
    public static String TEST_HOST = "https://testlist.tronlink.org";//test
    public static String PRE_HOST = "https://testpre.tronlink.org";//pre
    public static String HttpNode = HOME_HOST;
    public static String testAddressBase58 = "TKpJUP4CCymphdug1XmGzDGDmGXZjLyf29";
    public static String testAddressBase64 = "416C0214C9995C6F3A61AB23F0EB84B0CDE7FD9C7C";
    public static String testAccountKey = "7400E3D0727F8A61041A8E8BF86599FE5597CE19DE451E59AED07D60967A5E25";
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

    static {
        PoolingClientConnectionManager pccm = new PoolingClientConnectionManager();
        pccm.setDefaultMaxPerRoute(20);
        pccm.setMaxTotal(100);

        httpClient = new DefaultHttpClient(pccm);
    }

    public static HttpResponse upgrade() throws Exception{
        final String requestUrl = HttpNode + "/api/v1/wallet/upgrade";
        System.out.println(requestUrl);
        response = createGetConnect(requestUrl);
        Assert.assertTrue(api.verificationResult(response));
        return response;
    }

  public static HttpResponse trxTransferRecord(HashMap<String, String> param) throws Exception{
    final String requestUrl = HttpNode + "/api/simple-transfer";
    URIBuilder builder = new URIBuilder(requestUrl);
    if (param != null) {
      for (String key : param.keySet()) {
        builder.addParameter(key, param.get(key));
      }
    }
    URI uri = builder.build();
    //System.out.println(requestUrl);
    response = createGetConnect(uri);
    Assert.assertTrue(api.verificationResult(response));
    return response;
  }


    public static HttpResponse multiTransaction(JsonObject transaction) throws Exception {
        final String requestUrl = HttpNode + "/api/wallet/multi/transaction";
        response = createConnect(requestUrl, transaction);
        return response;
    }

    public static HttpResponse getAssetList(JsonObject address) throws Exception {
        final String requestUrl = HttpNode + "/api/wallet/assetlist";
        response = createConnect(requestUrl, address);
        return response;
    }

    public static HttpResponse getAllClassAsset(JSONObject address) throws Exception {
        final String requestUrl = HttpNode + "/api/wallet/class/allasset";
        response = createConnect(requestUrl, address);
        return response;
    }

    public static List<String> getTrc10TokenIdList(JSONArray tokenArray) throws Exception {
        List<String> tokenIdList = new ArrayList<>();
        String id = "";
        for (int i = 0; i < tokenArray.size();i++) {
            id = tokenArray.getJSONObject(i).getString("id");
            if (id.isEmpty()){
                continue;
            }
            tokenIdList.add(id);
        }
        return tokenIdList;
    }

    public static List<String> getTrc20AddressList(JSONArray tokenArray) throws Exception {
        List<String> trc20ContractAddressList = new ArrayList<>();
        String contractAddress = "";
        for (int i = 0; i < tokenArray.size();i++) {
            contractAddress = tokenArray.getJSONObject(i).getString("contractAddress");
            if (contractAddress.isEmpty()){
                continue;
            }
            trc20ContractAddressList.add(contractAddress);
        }
        return trc20ContractAddressList;
    }



    public static HttpResponse addAsset(JsonObject address) throws Exception {
        final String requestUrl = HttpNode + "/api/wallet/addasset";
        response = createConnect(requestUrl, address);
        return response;
    }

    public static HttpResponse addAsset(JSONObject address) throws Exception {
        final String requestUrl = HttpNode + "/api/wallet/addasset";
        response = createConnect(requestUrl, address);
        return response;
    }




    public static HttpResponse multiTrxReword(HashMap<String, String> param) throws Exception{
        final String requestUrl = HttpNode + "/api/wallet/multi/trx_record";
        URIBuilder builder = new URIBuilder(requestUrl);
        if (param != null) {
            for (String key : param.keySet()) {
                builder.addParameter(key, param.get(key));
            }
        }
        URI uri = builder.build();
        //System.out.println(requestUrl);
        response = createGetConnect(uri);
        Assert.assertTrue(api.verificationResult(response));
        return response;
    }

    public static HttpResponse multiSocket(HashMap<String, String> param) throws Exception{
        final String requestUrl = HttpNode + "/api/wallet/multi/socket";
        URIBuilder builder = new URIBuilder(requestUrl);
        if (param != null) {
            for (String key : param.keySet()) {
                builder.addParameter(key, param.get(key));
            }
        }
        URI uri = builder.build();
        //System.out.println(requestUrl);
        response = createGetConnect(uri);
        Assert.assertTrue(api.verificationResult(response));
        return response;
    }

    public static HttpResponse inviteList(HashMap<String,String> param) throws Exception{
        final String requesturl = HttpNode + "/api/wallet/invite/list";
        URIBuilder builder = new URIBuilder(requesturl);
        if (param != null) {
            for (String key : param.keySet()) {
                builder.addParameter(key, param.get(key));
            }
        }
        URI uri = builder.build();
        System.out.println(uri);
        response = createGetConnect(uri);
        Assert.assertTrue(api.verificationResult(response));
        return response;

    }




    /**
     * constructor.
     */
    public static Boolean verificationResult(HttpResponse response) {
        if (response.getStatusLine().getStatusCode() != 200) {
            return false;
        }
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);

        return true;
    }

    /**
     * constructor.
     */
    public static void printJsonContent(JSONObject responseContent) {
        System.out.println("----------------------------Print JSON Start---------------------------");
        for (String str : responseContent.keySet()) {
            System.out.println(str + ":" + responseContent.get(str));
        }
        System.out.println("JSON content size are: " + responseContent.size());
        System.out.println("----------------------------Print JSON End-----------------------------");
    }



    public static HttpResponse createJsonConnect(String url, JsonObject requestBody) {
        try {
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
                connectionTimeout);
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, soTimeout);
            httppost = new HttpPost(url);
            httppost.setHeader("Content-type", "application/json; charset=utf-8");
            httppost.setHeader("Connection", "Close");
            if (requestBody != null) {
                StringEntity entity = new StringEntity(requestBody.toString(), Charset.forName("UTF-8"));
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
        return response;
    }

    public static HttpResponse createGetConnect(String url) {
        try {
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
                    connectionTimeout);
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, soTimeout);
            httpGet = new HttpGet(url);
            httpGet.setHeader("Content-type", "application/json; charset=utf-8");
            httpGet.setHeader("Connection", "Close");
            response = httpClient.execute(httpGet);
        } catch (Exception e) {
            e.printStackTrace();
            httpGet.releaseConnection();
            return null;
        }
        return response;
    }

    public static HttpResponse createGetConnect(URI uri) {
        try {
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
                    connectionTimeout);
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, soTimeout);
            httpGet = new HttpGet(uri);
            httpGet.setHeader("Content-type", "application/json; charset=utf-8");
            httpGet.setHeader("Connection", "Close");
            response = httpClient.execute(httpGet);
        } catch (Exception e) {
            e.printStackTrace();
            httpGet.releaseConnection();
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

    public static void printJsonArray(JSONArray jsonArray) {
        System.out.println("-----------------This Json Array size are " + jsonArray.size() + "----------------------");
        for (int i = 0; i < jsonArray.size();i++) {
            System.out.println(jsonArray.getString(i));
        }

    }


    public static String gettransactionsign(String fullnodeNode, String transactionString,
                                            String privateKey) {
        try {
            String requestUrl = "http://" + fullnodeNode + "/wallet/gettransactionsign";
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

    /**
     * constructor.
     */
    public static String getTransactionSignStringFromFullnode(String httpNode, String fromAddress, String toAddress,
                                        Long amount, Integer permissionId,String managerKeys) {
        try {
            final String requestUrl = "http://" + httpNode + "/wallet/createtransaction";
            JsonObject userBaseObj2 = new JsonObject();
            userBaseObj2.addProperty("to_address", toAddress);
            userBaseObj2.addProperty("owner_address", fromAddress);
            userBaseObj2.addProperty("amount", amount);
            userBaseObj2.addProperty("Permission_id",permissionId);
            //userBaseObj2.addProperty("visible",true);
            response = createConnect(requestUrl, userBaseObj2);
            transactionSignString = EntityUtils.toString(response.getEntity());

            transactionSignString = gettransactionsign(httpNode, transactionSignString, managerKeys);
                //System.out.println("transactionSignString:" + transactionSignString);



            //response = broadcastTransaction(httpNode, transactionSignString);
        } catch (Exception e) {
            e.printStackTrace();
            httppost.releaseConnection();
            return null;
        }
        return transactionSignString;
    }

    public static HttpResponse createConnect(String url, JsonObject requestBody) {
        try {
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
                    connectionTimeout);
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, soTimeout);
            httppost = new HttpPost(url);
            httppost.setHeader("Content-type", "application/json; charset=utf-8");
            httppost.setHeader("Connection", "Close");
            if (requestBody != null) {
                StringEntity entity = new StringEntity(requestBody.toString(), Charset.forName("UTF-8"));
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
        return response;
    }

    public static HttpResponse createConnect(String url, JSONObject requestBody) {
        try {
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
                connectionTimeout);
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, soTimeout);
            httppost = new HttpPost(url);
            httppost.setHeader("Content-type", "application/json; charset=utf-8");
            httppost.setHeader("Connection", "Close");
            if (requestBody != null) {
                StringEntity entity = new StringEntity(requestBody.toString(), Charset.forName("UTF-8"));
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
        return response;
    }




}
