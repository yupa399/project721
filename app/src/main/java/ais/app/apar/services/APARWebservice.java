package ais.app.apar.services;

import android.content.Context;
import android.util.Log;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import ais.app.apar.db.DBController;
import ais.app.apar.entity.MainContent;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class APARWebservice implements WSConstants {
    private static final String TAG = "APARWebservice";

    private static DBController dbController;

    static final MediaType jsonHeader = MediaType.parse(jsonType);
    static final String getContentUrl = hostUrl + "getMainContent.php";


    public static String updateMainContent(Context context) {

        JSONObject query = new JSONObject();
        query.put("mainContent", "latest");
        RequestBody req = RequestBody.create(jsonHeader, query.toJSONString());
        Request request = new Request.Builder().url(getContentUrl).post(req).build();
        String dataStr = executeOK3Post(request, "data");
        if (dataStr == null) {
            return null;
        }
        ArrayList<MainContent> stuffList = (ArrayList<MainContent>) JSON.parseArray(dataStr, MainContent.class);
        dbController = new DBController(context);
        dbController.updateMainContent(stuffList.get(0));
        return successFlag;
    }


    private static String executeOK3Post(Request request, String key) {
        OkHttpClient client = new OkHttpClient();
        String res;
        try {
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                String resJSONString = response.body().string();
                JSONObject ret = JSON.parseObject(resJSONString);
                res = ret.getString(key);
                Log.i("Success", ret.getString("msg"));
            } else {
                res = response.message();
            }
        } catch (IOException e) {
            e.printStackTrace();
            res = e.getMessage();
        }
        return res;
    }


}
