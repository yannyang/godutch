package yann.study.database.sqlitedal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import yann.study.database.base.SQLiteHelper;


public class SQLiteDALCreateView implements SQLiteHelper.SQLiteDataTable {

	private Context m_Context;
	
	public SQLiteDALCreateView(Context p_Context)
	{
		m_Context = p_Context;
	}
	
	@Override
	public void onCreate(SQLiteDatabase p_DataBase) {
		StringBuilder s_CreateTableScript = new StringBuilder();
		
		s_CreateTableScript.append("		Create View v_Payout As ");
		s_CreateTableScript.append("		select a.*,b.ParentID,b.categoryname,b.Path,b.TypeFlag,c.AccountBookName from payout a LEFT JOIN category b ON a.categoryID = b.categoryID  LEFT JOIN accountbook c ON a.AccountBookID = c.AccountBookID");
		
		p_DataBase.execSQL(s_CreateTableScript.toString());
	}

	@Override
	public void onUpgrate(SQLiteDatabase pSQLiteDatabase) {

	}
}
