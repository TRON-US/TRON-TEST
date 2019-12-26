package activityServer.com.apiTest;

import activityServer.com.utils.api;
import activityServer.com.utils.tronlinkSocketClient;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import org.apache.http.HttpResponse;
import org.junit.Assert;
import org.testng.annotations.Test;

public class multiTransactionError {
    private HttpResponse response;
    private JSONObject responseContent;
    private HashMap<String,String> param = new HashMap<>();
    private String multiAddress = "418da4f11299320fee533deaedb5523981320ac472";
    private String multiKey = "dc55f562ff23aea25241f671372447d439ebecd19cb266cce3fa07dc96494c0f";
    private String fullnodeHttpServer = "47.252.85.177:8090";
    private String base58MutiAddress = "TNt9sTSLrjdFJmyeQGYSMGu5sVat1Ew3Tz";
    private JSONObject rawDataObject;
    private JSONArray transactionArray;
    private String foundationAccountKey = "fb4bd20d2af1bfad551f917f6110cdc8948cb41cbf5e6e3dbd26acb0f20eecef";
    private String foundationAccountAddress = "TFoPyG82ixipCTVqzkmLa9ivftHcsrXVrp";
    private String foundationBase58Address = "413ff5c065bdcdf7c3da16823ad0f6d3dff611122e";
    private String hash;

    private String testKey = "de164ddc64b1783c3ebced73b32c479b3daa203cf323bd738a9ecfc4674d6017";
    private String testAddress = "TN2jfdYCX9vvozqjwVvPjMd7vRj8HKyxUe";

    @Test(enabled = false, description = "Api first persion multi transaction")
    public void test001FirstOneMultiTransaction() throws Exception {
      String signString = api.getTransactionSignStringFromFullnode(fullnodeHttpServer,multiAddress,foundationAccountAddress,10L,3,foundationAccountKey);
      JsonObject jsonObject = new JsonParser().parse(signString).getAsJsonObject();
      jsonObject.remove("raw_data_hex");
      jsonObject.remove("txID");
      jsonObject.remove("visible");

      JsonObject transaction = new JsonObject();
      transaction.add("transaction",jsonObject);
      transaction.addProperty("address",base58MutiAddress);
      transaction.addProperty("netType","main_net");
      System.out.println(transaction);
      response = api.multiTransaction(transaction);
      api.printJsonContent(api.parseResponseContent(response));



    }
    @Test(enabled = true, description = "Api multi transaction")
    public void test002FirstOneMultiTransaction() throws Exception {
      String url = "wss://testlist.tronlink.org/api/wallet/multi/socket?address=" + base58MutiAddress + "&netType=main_net";
      tronlinkSocketClient mWs = new tronlinkSocketClient(url);
      mWs.connect();
      int i = 0;
      while (!mWs.isOpen() && i++ <= 5) {
        //System.out.println(mWs.getURI().toString());
        //System.out.println(mWs.isOpen());
        Thread.sleep(3000);
      }
      String transaction = tronlinkSocketClient.getResonse();
      rawDataObject = JSON.parseObject(transaction);
      transactionArray = rawDataObject.getJSONArray("data");
      JSONObject contractData = transactionArray.getJSONObject(0).getJSONObject("contractData");
      System.out.println(contractData);

      if (!transactionArray.isEmpty()){
        hash = transactionArray.getJSONObject(0).getString("hash");
        rawDataObject = transactionArray.getJSONObject(0).getJSONObject("currentTransaction").getJSONObject("raw_data");
        //signature = transactionArray.getJSONObject(0).getJSONObject("currentTransaction").getJSONArray("signature");
      }

      System.out.println("rawDataObject:" + rawDataObject);
    }

  @Test(enabled = true,description = "SignatureFormatERROR")
  public void test002signError() throws Exception {
    if (!hash.isEmpty()) {
      JSONArray signature = transactionArray.getJSONObject(0).getJSONObject("currentTransaction").getJSONArray("signature");
      System.out.println(signature.toString());
      JSONObject transaction = new JSONObject();
      transaction.put("address", base58MutiAddress);
      transaction.put("netType", "main_net");
      JSONObject raw = new JSONObject();
      signature.add("hhhhhhhhhh");
      raw.put("signature", signature);
      raw.put("raw_data", rawDataObject);
      transaction.put("transaction", raw);
      response = api.multiTransaction(transaction);
      JSONObject jsonObject = api.parseResponseContent(response);
      Assert.assertEquals(jsonObject.getString("message"), "Signature format error");
      Assert.assertEquals(jsonObject.getString("code"), "20302");
      signature.remove(signature.size()-1);
    }
  }

    @Test(enabled = true, description = "PermissionERROR")
    public void test009signError() throws Exception {
      if (!hash.isEmpty()){
        JSONObject transaction = new JSONObject();
        transaction.put("address",base58MutiAddress);
        transaction.put("netType","main_net");
        JSONObject raw = new JSONObject();
        //todo 修改key为fakekey
        JSONArray signatureArray = transactionArray.getJSONObject(0).getJSONObject("currentTransaction").getJSONArray("signature");
        signatureArray.add(api.getSignature(hash,testKey));
        raw.put("signature",signatureArray);
        raw.put("raw_data",rawDataObject);
        transaction.put("transaction",raw);
        System.out.println(transaction);
        response = api.multiTransaction(transaction);
        JSONObject jsonObject = api.parseResponseContent(response);
        api.printJsonContent(api.parseResponseContent(response));
        Assert.assertEquals(jsonObject.getString("message"),"Permission error");
        Assert.assertEquals(jsonObject.getString("code"),"20304");
      }
    }

  @Test(enabled = true,description = "Othererror")
  public void test003signError() throws Exception {
    if (!hash.isEmpty()) {
      JSONObject jsonObject = transactionArray.getJSONObject(0);
      JSONArray signature = new JSONArray();
      signature = jsonObject.getJSONObject("currentTransaction").getJSONArray("signature");
      JSONObject transaction = new JSONObject();
      transaction.put("address",base58MutiAddress );
      transaction.put("netType", "main_net");
      JSONObject raw = new JSONObject();
      signature.add(api.getSignature(hash,multiKey));
      raw.put("signature", signature);
      raw.put("raw_data", rawDataObject);
      transaction.put("transaction", raw);
      System.out.println(transaction);
      response = api.multiTransaction(transaction);
      JSONObject result = api.parseResponseContent(response);
      Assert.assertEquals(result.getString("message"), "Othererror");
      Assert.assertEquals(result.getString("code"), "20320");
    }
  }

    }

