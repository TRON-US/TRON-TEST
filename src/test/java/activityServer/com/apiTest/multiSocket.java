package activityServer.com.apiTest;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.testng.annotations.Test;
import java.util.HashMap;
import activityServer.com.utils.tronlinkSocketClient;

/**
 * Created by wangzihe on 2019/11/12.
 */

public class multiSocket {
    private HttpResponse response;
    private JSONObject responseContent;
    private HashMap<String,String> param = new HashMap<>();

    @Test(enabled = true, description = "Mutli socket test")
    public void multiSocket() throws Exception {
        String url = "wss://testlist.tronlink.org/api/wallet/multi/socket?address=TBExF3mNvnhmEFgHW4TmYXXdhevRchnQyb&netType=main_net";
        tronlinkSocketClient mWs = new tronlinkSocketClient(url);
        mWs.connect();
        int i = 0;
        while (!mWs.isOpen() && i++ <= 5) {
            //System.out.println(mWs.getURI().toString());
            //System.out.println(mWs.isOpen());
            Thread.sleep(3000);
        }

    }
}
