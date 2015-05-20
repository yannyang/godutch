package yann.study.database.base;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yann on 2015/5/19.
 * 数据库操作
 */
public abstract class SQLiteDALBase implements SQLiteHelper.SQLiteDataTable {
    private Context mContext;
    private SQLiteDatabase mSqLiteDatabase;

    public Context getContext() {
        return mContext;
    }

    protected abstract String[] getTableNameAndPK();

    protected SQLiteDALBase(Context pContext) {
        this.mContext = pContext;
    }

    public SQLiteDatabase getDatabase() {
        if (mSqLiteDatabase == null) {
            mSqLiteDatabase = SQLiteHelper.getInstance(mContext).getWritableDatabase();
        }
        return mSqLiteDatabase;
    }

    public void beginTransaction() {
        mSqLiteDatabase.beginTransaction();
    }

    public void setTransactionSuccessful() {
        mSqLiteDatabase.setTransactionSuccessful();
    }

    public void endTransaction() {
        mSqLiteDatabase.endTransaction();
    }

    public int getCount(String pCondition) {
        String _String[] = getTableNameAndPK();
        Cursor _Cursor = execSql("Select " + _String[1] + " From " + _String[0] + " Where 1=1 " + pCondition);
        int _Count = _Cursor.getCount();
        _Cursor.close();
        return _Count;
    }

    protected int getCount(String pPK, String pTableName, String pCondition) {
        String _String[] = getTableNameAndPK();
        Cursor _Cursor = execSql("Select " + pPK + " From " + pTableName + " Where 1=1 " + pCondition);
        int _Count = _Cursor.getCount();
        _Cursor.close();
        return _Count;
    }

    protected boolean delete(String pTableName, String pCondition) {
        return getDatabase().delete(pTableName, " 1=1 " + pCondition, null) >= 0;
    }
    protected List getList(String pSqlText){
        Cursor _Cursor=execSql(pSqlText);
        return cursorToList(_Cursor);
    }
    protected abstract Object findModel(Cursor _Cursor);
    protected List cursorToList(Cursor _Cursor){
        List _List=new ArrayList();
        while (_Cursor.moveToNext()){
            Object _Object=findModel(_Cursor);
            _List.add(_Object);
        }
        return _List;
    }

    protected Cursor execSql(String pSqlText) {
        return getDatabase().rawQuery(pSqlText, null);
    }

}
