package activityServer.com.apiTest;

import activityServer.com.utils.api;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import org.apache.http.HttpResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class versionLog {
  private HttpResponse response;
  private HashMap<String,String> parameter = new HashMap();

  @Test(enabled = true, description = "Api GET /api/v1/wallet/version_log test")
  public void test001AllClassAsset() throws Exception {
    parameter.put("lang", "2");
    parameter.put("system","Android");
    response = api.getVersionLog(parameter);

    JSONObject jsonObject = api.parseResponseContent(response);
    api.printJsonContent(jsonObject);
    JSONObject data = jsonObject.getJSONArray("data").getJSONObject(0);

    Assert.assertEquals(data.getString("system"),"Android");
    Assert.assertTrue(!data.getString("create_time").isEmpty());
    Assert.assertTrue(!data.getString("title").isEmpty());
    Assert.assertTrue(!data.getString("version").isEmpty());
    Assert.assertEquals(jsonObject.getString("msg"),"success");

    parameter.put("lang", "1");
    parameter.put("system","Android");
    response = api.getVersionLog(parameter);

    jsonObject = api.parseResponseContent(response);
    api.printJsonContent(jsonObject);
    data = jsonObject.getJSONArray("data").getJSONObject(0);

    Assert.assertEquals(data.getString("system"),"Android");
    Assert.assertTrue(!data.getString("create_time").isEmpty());
    Assert.assertTrue(!data.getString("title").isEmpty());
    Assert.assertTrue(!data.getString("version").isEmpty());
    Assert.assertEquals(jsonObject.getString("msg"),"success");
  }

}
