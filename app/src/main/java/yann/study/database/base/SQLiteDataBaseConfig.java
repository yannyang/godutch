package yann.study.database.base;

import java.util.ArrayList;

/**
 * Created by yann on 2015/5/19.
 * 菜单视图
 */
public class SQLiteDataBaseConfig {
    private static final String DATABASE_NAME="GoDutchDataBase";
    private static final int VESION=1;
    private static SQLiteDataBaseConfig INSTANCE;

    private SQLiteDataBaseConfig() {
    }

    public static SQLiteDataBaseConfig GetInstance(){
        if (INSTANCE==null){
            INSTANCE=new SQLiteDataBaseConfig();
        }
        return INSTANCE;
    }

    public String getDatabaseName(){
        return DATABASE_NAME;
    }
    public int getVesion(){
        return VESION;
    }
    public ArrayList<String> GetTables(){
        ArrayList<String> _ArrayList=new ArrayList<String>();
        _ArrayList.add("");
        return _ArrayList;
    }
}
