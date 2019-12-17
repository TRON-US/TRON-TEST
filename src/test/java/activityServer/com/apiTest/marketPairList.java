package activityServer.com.apiTest;

import activityServer.com.utils.api;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class marketPairList {
  private HttpResponse response;

  @Test(enabled = true, description = "Api GET /api/exchange/marketPair/list test")
  public void test001MarketPairList() throws Exception {

    response = api.marketPairList();

    JSONObject jsonObject = api.parseResponseContent(response);
    api.printJsonContent(jsonObject);

    JSONObject data = jsonObject.getJSONObject("data");
    JSONArray rows = data.getJSONArray("rows");
    for (Object exchange : rows){
      JSONObject exchangeJSON = (JSONObject)JSON.toJSON(exchange);
      Assert.assertTrue(!exchangeJSON.getString("fTokenName").isEmpty());
      Assert.assertTrue(!exchangeJSON.getString("id").isEmpty());
      Assert.assertTrue(!exchangeJSON.getString("volume").isEmpty());
      Assert.assertTrue(!exchangeJSON.getString("gain").isEmpty());
      Assert.assertTrue(!exchangeJSON.getString("price").isEmpty());
      Assert.assertTrue(!exchangeJSON.getString("fPrecision").isEmpty());
      Assert.assertTrue(!exchangeJSON.getString("fShortName").isEmpty());
      Assert.assertTrue(!exchangeJSON.getString("fTokenAddr").isEmpty());
      Assert.assertTrue(!exchangeJSON.getString("unit").isEmpty());
      Assert.assertTrue(!exchangeJSON.getString("logo").isEmpty());
    }
  }
}
