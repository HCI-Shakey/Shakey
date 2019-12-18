package com.hci.shakey.support;

import com.hci.shakey.MainActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LocalDataBase {
    public static LocalDataBaseHelper mHelper;
    protected static SQLiteDatabase db;

    protected static ContentValues values;
    protected static List<ActionTimesPair> retlist;

    protected static Handler mHandler;
    protected static String mCategory;

    public static final String TABLENAME = "localactionstable";

    public static final String ENVIRONMENT= "environment";
    public static final String ACTIONTODO = "actiontodo";
    public static final String TIMES = "times";

    //需要初始化，放在MainActivity里
    public static void initialize(Context mContext) {
        mHelper = LocalDataBaseHelper.instance(mContext);
        //Log.i("mHelp00er",""+mHelper);
        db = mHelper.getWritableDatabase();
    }

    // 新建(environment, action, 1), 内部有判重
    public static void addAction(String env, String action){
        db = mHelper.getWritableDatabase();
        db.beginTransaction();

        values = new ContentValues();
        values.clear();

        Cursor c = db.query(TABLENAME,null, "environment = ? and actiontodo = ?",new String[]{env, action},null,null,null);
        if (c.getCount()>=1){
            c.close();
        } else {
            c.close();
            values.put(ENVIRONMENT, env);
            values.put(ACTIONTODO, action);
            values.put(TIMES, "1");

            db.insert(TABLENAME,null,values);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
    }

    // 更新动作次数
    public static void updateActionTimes(String env, String action){
        db=mHelper.getWritableDatabase();
        //db.beginTransaction();
        Cursor c = db.query(TABLENAME,null, "environment = ? and actiontodo = ?",new String[]{env, action},null,null,null);
        if (c.moveToFirst()) {
            String curTimesStr = c.getString(c.getColumnIndex(TIMES));
            Integer curTimes = Integer.valueOf(curTimesStr);
            curTimes = curTimes + 1;
            curTimesStr = "" + curTimes;
            Log.i("times update", curTimesStr);
            values.clear();
            values.put(TIMES, curTimesStr);
            db.update(TABLENAME, values, "environment = ? and actiontodo = ?",new String[]{env, action});
            c.close();

        } else {
            c.close();
            addAction(env, action);
        }
    }

    public static List<ActionTimesPair> getActions(String env){ //collected==true则只获取收藏的，否则获取所有的
        db=mHelper.getWritableDatabase();
        retlist=new ArrayList<>();
        db.beginTransaction();
        Cursor c;
        c = db.query(TABLENAME,null, "environment = ?",new String[]{env},null,null,null);
        if (c.moveToFirst()){
            do {
                ActionTimesPair curp = new ActionTimesPair();
                curp.action = c.getString(c.getColumnIndex(ACTIONTODO));
                curp.times = Integer.valueOf(c.getString(c.getColumnIndex(TIMES)));
                retlist.add(curp);
            } while (c.moveToNext());
        }
        c.close();
        db.endTransaction();
        return retlist;
    }

    public static List<String> getRecommends(String env) {
        getActions(env);
        List<String> recs = new ArrayList<>();
        if (retlist.size() < 3) {
            Log.e("retlistError", "num less than 3");
            return recs;
        }
        Collections.sort(retlist, new Comparator<ActionTimesPair>() {
            @Override
            public int compare(ActionTimesPair o1, ActionTimesPair o2) {
                if(o1.times >= o2.times) {
                    return -1;
                }
                else {
                    return 1;
                }
            }
        });
        for (int i=0; i<3; ++i) {
            recs.add(retlist.get(i).action);
        }
        return recs;
    }


    //清空表中所有数据
    public static void clear(){
        db.execSQL("DELETE FROM "+TABLENAME) ;
    }


}

class ActionTimesPair{
    public String action;
    public Integer times;
    public ActionTimesPair() {}
}

