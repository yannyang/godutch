package yann.study.database.sqlitedal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

import yann.study.R;
import yann.study.database.base.SQLiteDALBase;
import yann.study.model.ModelUser;
import yann.study.utility.DateTools;


/**
 * Created by yann on 2015/5/19.
 * 用户数据操作类
 */
public class SQLiteDALUser extends SQLiteDALBase {
    public SQLiteDALUser(Context pContext) {
        super(pContext);
    }

    @Override
    protected String[] getTableNameAndPK() {
        return new String[]{"User","UserId"};
    }
   public boolean insertUser(ModelUser pModelUser){
       ContentValues _ContentValues=createParms(pModelUser);
       long _NewId= getDataBase().insert(getTableNameAndPK()[0],null,_ContentValues);
       pModelUser.setUserId((int)_NewId);
       return _NewId>0;
   }
    public boolean deleteUser(String pCondition){
        return delete(getTableNameAndPK()[0],pCondition);
    }
    public boolean updateUser(String pCondition,ModelUser pModelUser){
        ContentValues _ContentValues=createParms(pModelUser);
        return getDataBase().update(getTableNameAndPK()[0],_ContentValues,pCondition,null)>0;
    }
    public Boolean updateUser(String p_Condition,ContentValues pContentValues)
    {
        return getDataBase().update("User", pContentValues, p_Condition, null) > 0;
    }

    public List<ModelUser> getUser(String pCondition){
        String _SqlText="Select * From User Where 1=1 "+pCondition;
        return getList(_SqlText);
    }
    private void initDefaultData(SQLiteDatabase pSQLiteDatabase){
        ModelUser _ModelUser =new ModelUser();
        String _UserName[]=getContext().getResources().getStringArray(R.array.InitUserName);
        for (int i=0;i<_UserName.length;i++){
            _ModelUser.setUserName(_UserName[i]);
            ContentValues _ContentValues=createParms(_ModelUser);
            pSQLiteDatabase.insert(getTableNameAndPK()[0],null,_ContentValues);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase pSQLiteDatabase) {
        StringBuilder _StringBuilder = new StringBuilder();
        _StringBuilder.append("  Create  TABLE User(");
        _StringBuilder.append("         [UserId] integer PRIMARY KEY AUTOINCREMENT NOT NULL");
        _StringBuilder.append("         ,[UserName] varchar(10) NOT NULL");
        _StringBuilder.append("         ,[CreateDate] datetime NOT NULL");
        _StringBuilder.append("         ,[State] integer NOT NULL");
        _StringBuilder.append("         ) ");
        Log.i("yann",_StringBuilder.toString());
        pSQLiteDatabase.execSQL(_StringBuilder.toString());

        initDefaultData(pSQLiteDatabase);
    }

    @Override
    public void onUpgrate(SQLiteDatabase pSQLiteDatabase) {

    }

    @Override
    protected Object findModel(Cursor _Cursor) {
        ModelUser _ModelUser =new ModelUser();
        _ModelUser.setUserId(_Cursor.getInt(_Cursor.getColumnIndex("UserId")));
        _ModelUser.setUserName(_Cursor.getString(_Cursor.getColumnIndex("UserName")));
        _ModelUser.setCreateDate(DateTools.getDate(_Cursor.getString(_Cursor.getColumnIndex("CreateDate")),"yyyy-MM-dd HH:mm:ss"));
         _ModelUser.setState(_Cursor.getInt(_Cursor.getColumnIndex("State")));
        return _ModelUser;
    }
public ContentValues createParms(ModelUser pModelUser){
    ContentValues _ContentValues=new ContentValues();
    _ContentValues.put("UserName", pModelUser.getUserName());
    _ContentValues.put("CreateDate",DateTools.getFormatTime(pModelUser.getCreateDate()));
    _ContentValues.put("State", pModelUser.getState());
    return _ContentValues;
}

}
