package yann.study.database.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import yann.study.utility.Reflection;

/**
 * Created by yann on 2015/5/19.
 * ï¿½ï¿½ï¿½Ý¿ï¿½ï¿½ï¿½ï¿½ï¿½ï¿?
 */
public  class  SQLiteHelper extends SQLiteOpenHelper {

    private static SQLiteDataBaseConfig SQLITE_DATABASE_CONFIG;
    private Context mContext;
    private static SQLiteHelper INSTANCE;
    private Reflection mReflection;

    public interface SQLiteDataTable{
        public void onCreate(SQLiteDatabase pSQLiteDatabase);
        public void onUpgrate(SQLiteDatabase pSQLiteDatabase);
    }
        private SQLiteHelper(Context pContext){
        super(pContext, SQLITE_DATABASE_CONFIG.getDatabaseName(), null, SQLITE_DATABASE_CONFIG.getVesion());
        mContext=pContext;
    }
  public static SQLiteHelper getInstance(Context pContext){
      if(INSTANCE==null){
          SQLITE_DATABASE_CONFIG=SQLiteDataBaseConfig.getInstance(pContext);
          INSTANCE=new SQLiteHelper(pContext);
      }
      return INSTANCE;
  }

      @Override
    public void onCreate(SQLiteDatabase pDatabase) {
          ArrayList<String> _ArrayList=SQLITE_DATABASE_CONFIG.GetTables();
          mReflection=new Reflection();
          for (int i = 0; i <_ArrayList.size() ; i++) {
              try {
                  SQLiteDataTable _SQLiteDataTable= (SQLiteDataTable) mReflection.newInstance(
                          _ArrayList.get(i),
                          new Object[]{mContext},
                          new Class[]{Context.class}
                  );
                  _SQLiteDataTable.onCreate(pDatabase);
              } catch (Exception e) {
                  e.printStackTrace();
              }
          }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}