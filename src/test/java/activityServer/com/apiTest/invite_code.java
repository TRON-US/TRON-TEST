package activityServer.com.apiTest;

import activityServer.com.utils.api;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class invite_code {
  private HttpResponse response;
  private JSONObject responseContent;
  private JSONObject addressJson = new JSONObject();

  @Test(enabled = true, description = "Api POST /api/wallet/invite/code test")
  public void test001InviteCode() throws Exception {
//    addressJson.put("address", api.testAddressBase64);
    addressJson.put("invitedCode", "11Zm");
    addressJson.put("address", "417d0ccaab6375b9e1c26f370d723a1a1ac2175b41");
    addressJson.put("system", "Android");
    addressJson.put("deviceId", "ffffefefe");
    response = api.insertInviteCode(addressJson);

    JSONObject inviteCodeinfo = api.parseResponseContent(response);
    api.printJsonContent(inviteCodeinfo);
    Assert.assertEquals(inviteCodeinfo.getString("message"),"Invited code is err");
  }
}
