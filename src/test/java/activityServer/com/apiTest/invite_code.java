package activityServer.com.apiTest;

import activityServer.com.utils.api;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.testng.annotations.Test;

public class invite_code {
  private HttpResponse response;
  private JSONObject responseContent;
  private JSONObject addressJson = new JSONObject();

  @Test(enabled = true, description = "Api POST /api/wallet/invite/code test")
  public void test001AllClassAsset() throws Exception {
//    addressJson.put("address", api.testAddressBase64);
    addressJson.put("invitedCode", "10000");
    response = api.insertInviteCode(addressJson);

    JSONObject inviteCodeinfo = api.parseResponseContent(response);
    api.printJsonContent(inviteCodeinfo);
  }
}
