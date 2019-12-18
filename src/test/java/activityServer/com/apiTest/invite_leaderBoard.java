package activityServer.com.apiTest;

import activityServer.com.utils.api;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import org.apache.http.HttpResponse;
import org.testng.annotations.Test;

public class invite_leaderBoard {

  private HttpResponse response;
  private HashMap<String, String> parameter = new HashMap();

  @Test(enabled = true, description = "Api GET /api/wallet/invite/leaderBoard test")
  public void test001InviteLeaderBoard() throws Exception {
    parameter.put("start", "1");
    parameter.put("limit","20");
    response = api.inviteLeaderBoard(parameter);

    JSONObject inviteListInfo = api.parseResponseContent(response);
    api.printJsonContent(inviteListInfo);

    JSONObject inviteList = inviteListInfo.getJSONObject("data");
  }
}
