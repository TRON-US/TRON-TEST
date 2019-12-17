package activityServer.com.apiTest;

import activityServer.com.utils.api;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import org.apache.http.HttpResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class getLatestAPK {

  private HttpResponse response;
  @Test(enabled = true, description = "Api /api/v1/wallet/getLatestAPK  test")
  public void test001AllClassAsset() throws Exception {
    response = api.getLatestAPK();

    JSONObject jsonObject = api.parseResponseContent(response);
    api.printJsonContent(jsonObject);

    String data = jsonObject.getString("data");

    org.testng.Assert.assertTrue(!data.isEmpty());
  }
}
