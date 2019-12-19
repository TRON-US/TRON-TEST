package activityServer.com.apiTest;

import activityServer.com.utils.api;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import org.apache.http.HttpResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class feedBack {
  private HttpResponse response;
  private JSONObject parameter = new JSONObject();

  @Test(enabled = true, description = "Api POST /api/v1/wallet/feedback test")
  public void test001FeedBack() throws Exception {
    parameter.put("title", "test");
    parameter.put("system","Android");
    parameter.put("depict","test test test test test");
    parameter.put("email","test@test.com");
    response = api.feedBack(parameter);

    JSONObject jsonObject = api.parseResponseContent(response);
    System.out.println(jsonObject);
    api.printJsonContent(jsonObject);

    Assert.assertEquals(jsonObject.getString("msg"),"success");
  }

}
