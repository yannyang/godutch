package yann.study.database.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by yann on 2015/5/19.
 * ���ݿ������
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private static SQLiteDataBaseConfig mSQLiteDataBaseConfig;
    private Context mContext;
    private static SQLiteHelper INSTANCE;

    public interface SQLiteDataTable{
        public void onCreate(SQLiteDatabase pSQLiteDatabase);
        public void onUpgrate(SQLiteDatabase pSQLiteDatabase);
    }
        private SQLiteHelper(Context pContext){
        super(pContext,mSQLiteDataBaseConfig.getDatabaseName(),null,mSQLiteDataBaseConfig.getVesion());
        mContext=pContext;
    }
  public static SQLiteHelper getInstance(Context pContext){
      if(INSTANCE==null){
          INSTANCE=new SQLiteHelper(pContext);
      }
      return INSTANCE;
  }

      @Override
    public void onCreate(SQLiteDatabase db) {
          ArrayList<String> _ArrayList=mSQLiteDataBaseConfig.GetTables();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}