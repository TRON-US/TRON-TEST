package activityServer.com.apiTest;

import activityServer.com.utils.api;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.testng.annotations.Test;

public class invite_getCode {
  private HttpResponse response;
  private JSONObject responseContent;
  private JSONObject parameter = new JSONObject();

  @Test(enabled = true, description = "Api POST /api/wallet/invite/get_code test")
  public void test001AllClassAsset() throws Exception {
    parameter.put("address", api.testAddressBase64);
    response = api.getInviteCode(parameter);

    JSONObject inviteCodeinfo = api.parseResponseContent(response);
    api.printJsonContent(inviteCodeinfo);
  }

}
