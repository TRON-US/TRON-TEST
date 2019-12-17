package activityServer.com.apiTest;

import activityServer.com.utils.api;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import org.apache.http.HttpResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class transactionHistory {
  private HttpResponse response;

  @Test(enabled = true, description = "Api GET /api/simple-transaction test")
  public void test001TransactionTo() throws Exception {
    HashMap<String,String> parameter = new HashMap<>();
    parameter.put("to", "TMNQnpTsNHuK1NwqMf6WTBydXvNsv9p6of");
    response = api.transactionHistory(parameter);
    JSONObject jsonObject = api.parseResponseContent(response);
    api.printJsonContent(jsonObject);

    JSONArray data = jsonObject.getJSONArray("data");
    for (Object object:data){
      JSONObject history = (JSONObject) JSON.toJSON(object);
      Assert.assertTrue(!history.getString("hash").isEmpty());
      Assert.assertTrue(!history.getString("timestamp").isEmpty());
      Assert.assertTrue(!history.getString("ownerAddress").isEmpty());
      Assert.assertTrue(!history.getString("toAddress").isEmpty());
      Assert.assertTrue(!history.getString("contractType").isEmpty());
      Assert.assertEquals(history.getString("toAddress"),"TMNQnpTsNHuK1NwqMf6WTBydXvNsv9p6of");
    }


  }

  @Test(enabled = true)
  public void test002TransactionFrom() throws Exception {
    HashMap<String, String> parameter = new HashMap<>();
    parameter.put("from", "TMNQnpTsNHuK1NwqMf6WTBydXvNsv9p6of");
    response = api.transactionHistory(parameter);
    JSONObject jsonObject = api.parseResponseContent(response);
    api.printJsonContent(jsonObject);

    JSONArray data = jsonObject.getJSONArray("data");
    for (Object object : data) {
      JSONObject history = (JSONObject) JSON.toJSON(object);
      Assert.assertTrue(!history.getString("hash").isEmpty());
      Assert.assertTrue(!history.getString("timestamp").isEmpty());
      Assert.assertTrue(!history.getString("ownerAddress").isEmpty());
      Assert.assertTrue(!history.getString("toAddress").isEmpty());
      Assert.assertTrue(!history.getString("contractType").isEmpty());
      Assert.assertEquals(history.getString("ownerAddress"), "TMNQnpTsNHuK1NwqMf6WTBydXvNsv9p6of");
    }

  }

  @Test(enabled = true)
  public void test003TransactionAll() throws Exception {
    HashMap<String, String> parameter = new HashMap<>();
    parameter.put("address", "TMNQnpTsNHuK1NwqMf6WTBydXvNsv9p6of");
    response = api.transactionHistory(parameter);
    JSONObject jsonObject = api.parseResponseContent(response);
    api.printJsonContent(jsonObject);

    JSONArray data = jsonObject.getJSONArray("data");
    for (Object object : data) {
      JSONObject history = (JSONObject) JSON.toJSON(object);
      Assert.assertTrue(!history.getString("hash").isEmpty());
      Assert.assertTrue(!history.getString("timestamp").isEmpty());
      Assert.assertTrue(!history.getString("ownerAddress").isEmpty());
      Assert.assertTrue(!history.getString("toAddress").isEmpty());
      Assert.assertTrue(!history.getString("contractType").isEmpty());
    }

  }

}
