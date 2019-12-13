package activityServer.com.apiTest;

import activityServer.com.utils.api;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import org.apache.http.HttpResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class hotToken {
  private HttpResponse response;
  private JSONObject responseContent;
  private JSONObject addressJson = new JSONObject();
  private HashMap<String,String> header = new HashMap<>();

  @Test(enabled = true, description = "Api POST /api/wallet/hot_token test")
  public void test001AllClassAsset() throws Exception {
//    addressJson.put("address", api.testAddressBase64);
    addressJson.put("address", "417d0ccaab6375b9e1c26f370d723a1a1ac2175b41");
    header.put("DeviceID","sdfsasdfsa");
    header.put("Version","3.3.0");
    header.put("System","Android");
    header.put("Lang","2");
    header.put("chain","MainChain");
    response = api.getHotToken(addressJson,header);

    JSONObject jsonObject = api.parseResponseContent(response);
    api.printJsonContent(jsonObject);

    JSONArray tokenList = jsonObject.getJSONObject("data").getJSONArray("token");
    for (Object token : tokenList){
      System.out.println(token);
      JSONObject tokenJson = (JSONObject)JSON.toJSON(token);
      if (tokenJson.getInteger("type") == 2){
        Assert.assertTrue(!tokenJson.getString("contractAddress").isEmpty());
      }else {
        Assert.assertTrue(tokenJson.getString("contractAddress").isEmpty());
      }
      Assert.assertTrue(!tokenJson.getString("name").isEmpty());
      Assert.assertTrue(!tokenJson.getString("shortName").isEmpty());
      Assert.assertTrue(!tokenJson.getString("price").isEmpty());
      Assert.assertTrue(!tokenJson.getString("precision").isEmpty());
    }
  }

}
