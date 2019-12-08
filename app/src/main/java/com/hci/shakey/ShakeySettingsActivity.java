package com.hci.shakey;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShakeySettingsActivity extends AppCompatActivity {

    private TextView text;
    private Spinner spinner,spinnerwechat,spinneralipay,spinnermap,spinnermusic,spinnerdidi;
    public static int oslock = 0;
    public static int wechat= 1;
    public static int alipay = 2;
    public static int map = 3;
    public static int music = 4;
    public static int didi = 5;
    private String [] oslock_str1,wechat_str1,alipay_str1,didi_str1,map_str1,music_str1;
    private String [] oslock_str2,wechat_str2,alipay_str2,didi_str2,map_str2,music_str2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shakey_settings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //1:创建数据源
        oslock_str1 = new String[]{"oslock_", "oslock_", "oslock_"};
        oslock_str2 = new String[]{"showaaa","showbbb","showccc"};
        wechat_str1 = new String[]{"wechat_", "wechat_", "wechat_"};
        wechat_str2 = new String[]{"showaaa","showbbb","showccc"};
        alipay_str1 = new String[]{"alipay_", "oslock_", "oslock_"};
        alipay_str2 = new String[]{"showaaa","showbbb","showccc"};
        map_str1 = new String[]{"map_", "map_", "map_"};
        map_str2 = new String[]{"showaaa","showbbb","showccc"};
        music_str1 = new String[]{"music_", "music_", "music_"};
        music_str2 = new String[]{"showaaa","showbbb","showccc"};
        didi_str1 = new String[]{"didi_", "didi_", "didi_"};
        didi_str2 = new String[]{"showaaa","showbbb","showccc"};
        //2:获取控件
        text = (TextView) findViewById(R.id.text);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinnerwechat = (Spinner) findViewById(R.id.spinnerwechat);
        spinneralipay = (Spinner) findViewById(R.id.spinneralipay);
        spinnermap = (Spinner) findViewById(R.id.spinnermap);
        spinnermusic = (Spinner) findViewById(R.id.spinnermusic);
        spinnerdidi = (Spinner) findViewById(R.id.spinnerdidi);
        /** 把数据设置给适配器
         * * this:上下文
         * * getDate():数据源
         * * R.layout.list_test：列布局
         * * new String[]{"title","name"}
         * * new int[]{R.id.img,R.id.tv}*/
        SimpleAdapter adapter = new SimpleAdapter(this, getDate(oslock), R.layout.spinner, new String[]{"title", "name"}, new int[]{R.id.text1, R.id.text2});
        SimpleAdapter adapterwechat = new SimpleAdapter(this, getDate(wechat), R.layout.spinner, new String[]{"title", "name"}, new int[]{R.id.text1, R.id.text2});
        SimpleAdapter adapteralipay = new SimpleAdapter(this, getDate(alipay), R.layout.spinner, new String[]{"title", "name"}, new int[]{R.id.text1, R.id.text2});
        SimpleAdapter adaptermap = new SimpleAdapter(this, getDate(map), R.layout.spinner, new String[]{"title", "name"}, new int[]{R.id.text1, R.id.text2});
        SimpleAdapter adaptermusic = new SimpleAdapter(this, getDate(music), R.layout.spinner, new String[]{"title", "name"}, new int[]{R.id.text1, R.id.text2});
        SimpleAdapter adapterdidi = new SimpleAdapter(this, getDate(didi), R.layout.spinner, new String[]{"title", "name"}, new int[]{R.id.text1, R.id.text2});

        //把适配器绑定给控件
        spinner.setAdapter(adapter);
        spinnerwechat.setAdapter(adapterwechat);
        spinneralipay.setAdapter(adapteralipay);
        spinnermap.setAdapter(adaptermap);
        spinnermusic.setAdapter(adaptermusic);
        spinnerdidi.setAdapter(adapterdidi);
        //监听事件（SimpleAdapter 适配器用setOnItemSelectedListener监听）
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                text.setText(oslock_str1[position] + " " + oslock_str2[position]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
                text.setText(oslock_str1[0] + " " + oslock_str2[0]);
            }
        });
        spinnerwechat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                text.setText(wechat_str1[position] + " " + wechat_str2[position]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
                text.setText(wechat_str1[0] + " " + wechat_str2[0]);
            }
        });
        spinneralipay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                text.setText(alipay_str1[position] + " " + alipay_str2[position]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
                text.setText(alipay_str1[0] + " " + alipay_str2[0]);
            }
        });
        spinnermap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                text.setText(map_str1[position] + " " + map_str2[position]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
                text.setText(map_str1[0] + " " + map_str2[0]);
            }
        });
        spinnermusic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                text.setText(music_str1[position] + " " + music_str2[position]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
                text.setText(music_str1[0] + " " + music_str2[0]);
            }
        });
        spinnerdidi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                text.setText(didi_str1[position] + " " + didi_str2[position]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
                text.setText(didi_str1[0] + " " + didi_str2[0]);
            }
        });
    }
    //将数据封装在List中
    public List<Map<String,String>> getDate(int types){
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        Map<String, String> map = null;
        switch(types){
            case 0:
                for(int i = 0; i<oslock_str1.length; i++){
                    map = new HashMap<String, String>();
                    map.put("title", oslock_str1[i]);
                    map.put("name", oslock_str2[i]);
                    list.add(map);
                }
                break;
            case 1:
                for(int i = 0; i<wechat_str1.length; i++){
                    map = new HashMap<String, String>();
                    map.put("title", wechat_str1[i]);
                    map.put("name", wechat_str2[i]);
                    list.add(map);
                }
                break;
            case 2:
                for(int i = 0; i<alipay_str1.length; i++){
                    map = new HashMap<String, String>();
                    map.put("title", alipay_str1[i]);
                    map.put("name", alipay_str2[i]);
                    list.add(map);
                }
                break;
            case 3:
                for(int i = 0; i<map_str1.length; i++){
                    map = new HashMap<String, String>();
                    map.put("title", map_str1[i]);
                    map.put("name", map_str2[i]);
                    list.add(map);
                }
                break;
            case 4:
                for(int i = 0; i<music_str1.length; i++){
                    map = new HashMap<String, String>();
                    map.put("title", music_str1[i]);
                    map.put("name", music_str2[i]);
                    list.add(map);
                }
                break;
            case 5:
                for(int i = 0; i<didi_str1.length; i++){
                    map = new HashMap<String, String>();
                    map.put("title", didi_str1[i]);
                    map.put("name", didi_str2[i]);
                    list.add(map);
                }
                break;
            default:
                break;
        }
        return list;
    }
}
