package yann.study.database.base;

import android.content.Context;

import java.util.ArrayList;

import yann.study.R;

/**
 * Created by yann on 2015/5/19.
 * 数据库配置类
 */
public class SQLiteDataBaseConfig {
    private static final String DATABASE_NAME="GoDutchDatabase";
    private static final int VESION=1;
    private static SQLiteDataBaseConfig INSTANCE;
    private static  Context mContext;

    private SQLiteDataBaseConfig() {
    }

    public static SQLiteDataBaseConfig getInstance(Context pContext){
        if (INSTANCE==null){
            INSTANCE=new SQLiteDataBaseConfig();
            mContext=pContext;
        }
        return INSTANCE;
    }

    public String getDataBaseName(){
        return DATABASE_NAME;
    }
    public int getVesion(){
        return VESION;
    }
    public ArrayList<String> GetTables(){
        ArrayList<String> _ArrayList=new ArrayList<String>();
        String[] _SQLiteDALClassName=mContext.getResources().getStringArray(R.array.SQLiteDALClassName);
        String _PackagePath=mContext.getPackageName()+".database.sqlitedal.";
        for (int i = 0; i <_SQLiteDALClassName.length ; i++) {
            _ArrayList.add(_PackagePath+_SQLiteDALClassName[i]);
        }

        return _ArrayList;
    }
}
