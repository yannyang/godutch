package yann.study.database.sqlitedal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;
import java.util.List;

import yann.study.R;
import yann.study.database.base.SQLiteDALBase;
import yann.study.model.ModelAccountBook;
import yann.study.utility.DateTools;


public class SQLiteDALAccountBook extends SQLiteDALBase {

	public SQLiteDALAccountBook(Context p_Context) {
		super(p_Context);
	}

	public Boolean InsertAccountBook(ModelAccountBook p_Info) {
		ContentValues _ContentValues = CreatParms(p_Info);
		Long p_NewID = getDataBase().insert("AccountBook", null, _ContentValues);
		p_Info.SetAccountBookID(p_NewID.intValue());
		return p_NewID > 0;
	}
	
	public Boolean DeleteAccountBook(String p_Condition)
	{
		return delete(getTableNameAndPK()[0], p_Condition);
	}
	
	public Boolean UpdateAccountBook(String p_Condition, ModelAccountBook p_Info)
	{
		ContentValues _ContentValues = CreatParms(p_Info);
		return getDataBase().update("AccountBook", _ContentValues, p_Condition, null) > 0;
	}
	
	public Boolean UpdateAccountBook(String p_Condition,ContentValues p_ContentValues)
	{
		return getDataBase().update("AccountBook", p_ContentValues, p_Condition, null) > 0;
	}
	
	public List<ModelAccountBook> GetAccountBook(String p_Condition)
	{
		String _SqlText = "Select * From AccountBook Where  1=1 " + p_Condition;
		return getList(_SqlText);
	}
	
	protected ModelAccountBook findModel(Cursor p_Cursor)
	{
		ModelAccountBook _ModelAccountBook = new ModelAccountBook();
		_ModelAccountBook.SetAccountBookID(p_Cursor.getInt(p_Cursor.getColumnIndex("AccountBookID")));
		_ModelAccountBook.SetAccountBookName(p_Cursor.getString(p_Cursor.getColumnIndex("AccountBookName")));
		Date _CreateDate = DateTools.getDate(p_Cursor.getString(p_Cursor.getColumnIndex("CreateDate")), "yyyy-MM-dd HH:mm:ss");
		_ModelAccountBook.SetCreateDate(_CreateDate);
		_ModelAccountBook.SetIsDefault(p_Cursor.getInt(p_Cursor.getColumnIndex("IsDefault")));
		_ModelAccountBook.SetState((p_Cursor.getInt(p_Cursor.getColumnIndex("State"))));
		
		return _ModelAccountBook;
	}

	private void InitDefaultData(SQLiteDatabase p_DataBase)
	{
		String _AccountBook[] = getContext().getResources().getStringArray(R.array.InitDefaultDataAccountBookName);
		ModelAccountBook _ModelAccountBook = new ModelAccountBook();
		_ModelAccountBook.SetAccountBookName(_AccountBook[0]);
		_ModelAccountBook.SetIsDefault(1);
	
		ContentValues _ContentValues = CreatParms(_ModelAccountBook);
		p_DataBase.insert("AccountBook", null, _ContentValues);
	}
	
	@Override
	public void onCreate(SQLiteDatabase p_DataBase) {
		StringBuilder s_CreateTableScript = new StringBuilder();
		
		s_CreateTableScript.append("		Create  TABLE AccountBook(");
		s_CreateTableScript.append("				[AccountBookID] integer PRIMARY KEY AUTOINCREMENT NOT NULL");
		s_CreateTableScript.append("				,[AccountBookName] varchar(20) NOT NULL");
		s_CreateTableScript.append("				,[CreateDate] datetime NOT NULL");
		s_CreateTableScript.append("				,[State] integer NOT NULL");
		s_CreateTableScript.append("				,[IsDefault] integer NOT NULL");
		s_CreateTableScript.append("				)");
		
		p_DataBase.execSQL(s_CreateTableScript.toString());
		
		InitDefaultData(p_DataBase);
	}

	@Override
	public void onUpgrate(SQLiteDatabase pSQLiteDatabase) {

	}


	@Override
	protected String[] getTableNameAndPK() {
		return new String[]{"AccountBook","AccountBookID"};
	}

	public ContentValues CreatParms(ModelAccountBook p_Info) {
		ContentValues _ContentValues = new ContentValues();
		_ContentValues.put("AccountBookName", p_Info.GetAccountBookName());
		_ContentValues.put("CreateDate",DateTools.getFormatDateTime(p_Info.GetCreateDate(),"yyyy-MM-dd HH:mm:ss"));
		_ContentValues.put("State",p_Info.GetState());
		_ContentValues.put("IsDefault",p_Info.GetIsDefault());
		return _ContentValues;
	}
}
