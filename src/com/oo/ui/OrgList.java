package com.oo.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.oo.DBHelper;
import com.oo.R;
import org.json.JSONArray;
import org.json.JSONObject;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-8-1
 * Time: 下午2:13
 * To change this template use File | Settings | File Templates.
 */
public class OrgList extends Activity implements OnItemClickListener, OnClickListener {
    DBHelper db;
    ListView lv;
    Button BtnQ;
    //Button BtnV;
    EditText q;
    int _id;
    JSONArray jsonArray;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.org_list);

        BtnQ = (Button) findViewById(R.id.BtnQ);
        BtnQ.setOnClickListener(this);

        //BtnV.setOnClickListener(this);

        q = (EditText) findViewById(R.id.q);

        db = new DBHelper(this);

        lv = (ListView) findViewById(R.id.listview);

        jsonArray = db.getAllOrganization();
        try {
            //生成动态数组，加入数据
            ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("id", jsonObject.get("id"));
                map.put("name", jsonObject.get("name"));
                map.put("address", jsonObject.get("address"));
//                map.put("item4", jsonObject.get("buildArea"));
//                map.put("item5", jsonObject.get("dateCreated"));
                listItem.add(map);
            }
            //生成适配器的Item和动态数组对应的元素
            SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem,//数据源
                    R.layout.org_list_item,//ListItem的XML实现
                    //动态数组与ImageItem对应的子项
                    new String[]{"id", "name", "address"},
                    //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                    new int[]{R.id.id, R.id.name, R.id.address}
            );

            lv.setAdapter(listItemAdapter);

            // getBillsTotal
//            total = (TextView) findViewById(R.id.totalitem);
//            total.setText(billdb.getBillsTotal(today));

            lv.setOnItemClickListener(this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
//        menu.add(0, 1, 0, "建筑列表").setIcon(R.drawable.editbills);
//        //menu.add(0, 2, 0, "建筑列表").setIcon(R.drawable.editbills2);
//        //menu.add(0, 3, 0, "账目报表").setIcon(R.drawable.billsum1);
        menu.add(0, 4, 0, "退 出").setIcon(R.drawable.quit);
        menu.add(0, 5, 0, "关于FireSystem");
        return true;
    }

    public void onClick(View v) {
        if (v.equals(BtnQ)) {
            String queryStr = q.getText().toString();

            if (queryStr != null && queryStr.length() > 0) {
                jsonArray = db.searchOrganization(queryStr);
            } else {
                jsonArray = db.getAllOrganization();
            }

            try {
                //生成动态数组，加入数据
                ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("id", jsonObject.get("id"));
                    map.put("name", jsonObject.get("name"));
                    map.put("address", jsonObject.get("address"));
//                map.put("item4", jsonObject.get("buildArea"));
//                map.put("item5", jsonObject.get("dateCreated"));
                    listItem.add(map);
                }
                //生成适配器的Item和动态数组对应的元素
                SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem,//数据源
                        R.layout.org_list_item,//ListItem的XML实现
                        //动态数组与ImageItem对应的子项
                        new String[]{"id", "name", "address"},
                        //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                        new int[]{R.id.id, R.id.name, R.id.address}
                );

                lv.setAdapter(listItemAdapter);

                // getBillsTotal
//            total = (TextView) findViewById(R.id.totalitem);
//            total.setText(billdb.getBillsTotal(today));

                lv.setOnItemClickListener(this);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        _id = (int) l;
        try {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String id = (String) jsonObject.getString("id");
            Intent intent = new Intent();
            intent.putExtra("id", id);
            intent.setClassName(OrgList.this, "com.oo.ui.OrgFrm");
            startActivity(intent);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //Log.v("FireBox", "getmenuitemid=" + item.getItemId());
        switch (item.getItemId()) {
            case 1:
//                Intent intent = new Intent();
//                //intent.setClass(Frm_Addbills.this, Grid_bills.class);
//                intent.setClassName(Grid_building.this,"com.cola.ui.Grid_building");
//                startActivity(intent);
//                return true;
            case 2:
//                Intent intentBuilding = new Intent();
//                intentBuilding.setClassName(Frm_Addbills.this,"com.cola.ui.Grid_building");
//                startActivity(intentBuilding);
//                return true;

//                int nOrientation = getRequestedOrientation();
//                if (nOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
//                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                else
//                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                //return true;

            case 3:
//                Intent i = new Intent();
//                i.setClass(Frm_Addbills.this, LocalService.class);
//                ComponentName compName = startService(i);
//                if (compName == null)
//                {
//                    Log.e("main_Service", "startService() failed!");
//                }
//                //startService(new Intent(Frm_Addbills.this,mainService.class));
//                return true;
            case 4:
                QuitApp();
                return true;
            case 5:
                new AlertDialog.Builder(this)
                        .setTitle("FireBox")
                        .setMessage("作者:Mark Zhung Email:Mark.zhung@gmail.com")
                        .show();
                return true;
        }
        return false;
    }

    public void QuitApp() {
        new AlertDialog.Builder(OrgList.this).setTitle("提示").setMessage(
                "确定退出?").setIcon(R.drawable.quit).setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        finish();
                    }
                }).setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                }).show();

    }


}
