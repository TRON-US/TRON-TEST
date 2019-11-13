package activityServer.com.apiTest;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.testng.annotations.Test;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import activityServer.com.utils.api;
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
        //String url = "wss://testlist.tronlink.org/api/wallet/multi/socket?address=TBExF3mNvnhmEFgHW4TmYXXdhevRchnQyb&state=1";
        String url = "wss://testlist.tronlink.org/api/wallet/multi/socket/";
        URI uri = new URI(url);
        tronlinkSocketClient mWs = new tronlinkSocketClient(url);

        mWs.connect();

        while (!mWs.getReadyState().equals(WebSocketClient.READYSTATE.OPEN)) {

            System.out.println(mWs.getURI().toString());
            System.out.println("还没有打开");
            System.out.println(mWs.isOpen());
        }
        mWs.send("address=TBExF3mNvnhmEFgHW4TmYXXdhevRchnQyb&state=1");




        System.exit(1);

        param.put("address","TBExF3mNvnhmEFgHW4TmYXXdhevRchnQyb");
        param.put("start","0");
        param.put("limit","40");
        param.put("state","0");
        param.put("netType","main_net");


    }
}
