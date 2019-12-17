package activityServer.com.apiTest;

import activityServer.com.utils.api;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class trxPrice {
  private HttpResponse response;
  private JSONObject responseContent;

  @Test(enabled = true, description = "Api /api/v1/wallet/trxPrice test")
  public void test001TrxPrice() throws Exception {
    response = api.trxPrice();

    JSONObject jsonObject = api.parseResponseContent(response);
    api.printJsonContent(jsonObject);

    String data = jsonObject.getString("data");

    JSONArray jsonArray = (JSONArray)JSON.parse(data);
    JSONObject trxData = jsonArray.getJSONObject(0);
    Assert.assertTrue(!trxData.getString("id").isEmpty());
    Assert.assertTrue(!trxData.getString("name").isEmpty());
    Assert.assertTrue(!trxData.getString("symbol").isEmpty());
    Assert.assertTrue(trxData.getString("symbol") == "TRX");
    Assert.assertTrue(!trxData.getString("rank").isEmpty());
    Assert.assertTrue(!trxData.getString("price_usd").isEmpty());
    Assert.assertTrue(!trxData.getString("price_btc").isEmpty());
    Assert.assertTrue(!trxData.getString("24h_volume_usd").isEmpty());
    Assert.assertTrue(!trxData.getString("market_cap_usd").isEmpty());
    Assert.assertTrue(!trxData.getString("available_supply").isEmpty());
    Assert.assertTrue(!trxData.getString("total_supply").isEmpty());

  }

}
