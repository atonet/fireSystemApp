package com.oo;

import android.content.Context;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-8-1
 * Time: 下午2:06
 * To change this template use File | Settings | File Templates.
 */
public class DBHelper {
    private static final String TAG = "DBHelper";
    Context context;
    //private static final String DATABASE_NAME = "cola.db";
    //    private static final String dbUrl = "http://192.168.1.6:8080/fireSystem/";
    private static final String dbUrl = "http://minierp.publicvm.com:2600/fireSystem-0.1/";
//   private static final String dbUrl = "http://192.168.1.41:2600/fireSystem-0.1/";
   //private static final String dbUrl = "http://127.0.0.1:8080/fireSystem/";
    public DBHelper(Context _context) {
        context = _context;
    }
    private JSONArray getDataFromJson(String url) {
        HttpClient hc = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        JSONArray jsonArray = null;
        try {
            HttpResponse httpResponse = hc.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpEntity.getContent()));
            StringBuffer stringBuffer = new StringBuffer();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line + "\n");
            }
            bufferedReader.close();
            jsonArray = new JSONArray(stringBuffer.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jsonArray;
    }
    private JSONObject getObjectFromJson(String url) {
        HttpClient hc = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        JSONObject jsonObject = null;
        try {
            HttpResponse httpResponse = hc.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpEntity.getContent()));
            StringBuffer stringBuffer = new StringBuffer();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line + "\n");
            }
            bufferedReader.close();
            jsonObject = new JSONObject(stringBuffer.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jsonObject;
    }


    public JSONArray getAllBuilding() {
        String url = dbUrl + "building/listAsJson";
        return getDataFromJson(url);
    }
    public JSONArray getBuildingById(String id) {
        String url = dbUrl + "building/showAsJson?id="+id;
        return getDataFromJson(url);
    }
    public JSONArray getBuildingListByOrgId(String id) {
        String url = dbUrl + "building/listByOrgAsJson?id="+id;
        return getDataFromJson(url);
    }

    public JSONArray searchBuilding(String q) {
        String url = dbUrl + "building/searchAsJson?q=" + q;
        return getDataFromJson(url);
    }
    public JSONArray getAllOrganization() {
        String url = dbUrl + "organization/listAsJson";
        return getDataFromJson(url);
    }
    public JSONObject getOrgById(String id) {
        String url = dbUrl + "organization/showAsJson?id="+id;
        return getObjectFromJson(url);
    }

    public JSONArray searchOrganization(String q) {
        String url = dbUrl + "organization/searchAsJson?q=" + q;
        return getDataFromJson(url);
    }
    public HashMap getHmOrgType() throws JSONException {
        String url = dbUrl + "organizationType/listAsJson";
         JSONArray jsonArray = getDataFromJson(url);
        HashMap<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            map.put( jsonObject.getString("id") ,jsonObject.getString("name"));
        }
        return  map;
    }
    public HashMap getHmOrgOtherType() throws JSONException {
        String url = dbUrl + "organizationOtherType/listAsJson";
        JSONArray jsonArray = getDataFromJson(url);
        HashMap<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            map.put( jsonObject.getString("id") ,jsonObject.getString("name"));
        }
        return  map;
    }



}
