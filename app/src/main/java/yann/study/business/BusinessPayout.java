package yann.study.business;

import android.content.Context;
import android.database.Cursor;

import java.util.List;

import yann.study.R;
import yann.study.business.base.BaseBusiness;
import yann.study.database.sqlitedal.SQLiteDALPayout;
import yann.study.model.ModelPayout;


public class BusinessPayout extends BaseBusiness {
	
	private SQLiteDALPayout mSqLiteDALPayout;
	
	public BusinessPayout(Context p_Context)
	{
		super(p_Context);
		mSqLiteDALPayout = new SQLiteDALPayout(p_Context);
	}
	
	public Boolean InsertPayout(ModelPayout p_Info)
	{
		Boolean _Result = mSqLiteDALPayout.InsertPayout(p_Info);
		
		if(_Result)
		{
			return true;
		}
		else {
			return false;
		}
	}
	
	public Boolean DeletePayoutByPayoutID(int p_PayoutID)
	{
		String _Condition = " And PayoutID = " + p_PayoutID;
		Boolean _Result = mSqLiteDALPayout.DeletePayout(_Condition);
		return _Result;
	}
	
	public Boolean DeletePayoutByAccountBookID(int p_AccountBookID)
	{
		String _Condition = " And AccountBookID = " + p_AccountBookID;
		Boolean _Result = mSqLiteDALPayout.DeletePayout(_Condition);
		return _Result;
	}
	
	public Boolean UpdatePayoutByPayoutID(ModelPayout p_Info)
	{
		String _Condition = " PayoutID = " + p_Info.GetPayoutID();
		Boolean _Result = mSqLiteDALPayout.UpdatePayout(_Condition, p_Info);
		
		if(_Result)
		{
			return true;
		}
		else {
			return false;
		}
	}
	
	public List<ModelPayout> GetPayout(String p_Condition)
	{
		return mSqLiteDALPayout.GetPayout(p_Condition);
	}
	
	public int GetCount()
	{
		return mSqLiteDALPayout.getCount("");
	}

	public List<ModelPayout> GetPayoutByAccountBookID(int p_AccountBookID)
	{
		String _Condition = " And AccountBookID = " + p_AccountBookID + " Order By PayoutDate DESC,PayoutID DESC";
		return mSqLiteDALPayout.GetPayout(_Condition);
	}
	
	public String GetPayoutTotalMessage(String p_PayoutDate,int p_AccountBookID)
	{
		String _Total[] = GetPayoutTotalByPayoutDate(p_PayoutDate,p_AccountBookID);
		return GetContext().getString(R.string.TextViewTextPayoutTotal, new Object[]{_Total[0],_Total[1]});
	}
	
	private String[] GetPayoutTotalByPayoutDate(String p_PayoutDate,int p_AccountBookID)
	{
		String _Condition = " And PayoutDate = '" + p_PayoutDate + "' And AccountBookID = " + p_AccountBookID;
		return GetPayoutTotal(_Condition);
	}
	
	public String[] GetPayoutTotalByAccountBookID(int p_AccountBookID)
	{
		String _Condition = " And AccountBookID = " + p_AccountBookID;
		return GetPayoutTotal(_Condition);
	}
	
	private String[] GetPayoutTotal(String p_Condition)
	{
		String _SqlText = "Select ifnull(Sum(Amount),0) As SumAmount,Count(Amount) As Count From Payout Where 1=1 " + p_Condition;
		String _Total[] = new String[2];
		Cursor _Cursor = mSqLiteDALPayout.execSql(_SqlText);
		if(_Cursor.getCount() == 1)
		{
			while(_Cursor.moveToNext())
			{
				_Total[0] = _Cursor.getString(_Cursor.getColumnIndex("Count"));
				_Total[1] = _Cursor.getString(_Cursor.getColumnIndex("SumAmount"));
			}
		}
		return _Total;
	}
	
	public List<ModelPayout> GetPayoutOrderByPayoutUserID(String p_Condition)
	{
		p_Condition += " Order By PayoutUserID";
		List<ModelPayout> _List = GetPayout(p_Condition);
		if(_List.size() > 0)
		{
			return _List;
		}
		
		return null;
	}
	
	public String[] GetPayoutDateAndAmountTotal(String p_Condition)
	{
		String _SqlText = "Select Min(PayoutDate) As MinPayoutDate,Max(PayoutDate) As MaxPayoutDate,Sum(Amount) As Amount From Payout Where 1=1 " + p_Condition;
		String _PayoutTotal[] = new String[3];
		Cursor _Cursor = mSqLiteDALPayout.execSql(_SqlText);
		if(_Cursor.getCount() == 1)
		{
			while(_Cursor.moveToNext())
			{
				_PayoutTotal[0] = _Cursor.getString(_Cursor.getColumnIndex("MinPayoutDate"));
				_PayoutTotal[1] = _Cursor.getString(_Cursor.getColumnIndex("MaxPayoutDate"));
				_PayoutTotal[2] = _Cursor.getString(_Cursor.getColumnIndex("Amount"));
			}
		}
		return _PayoutTotal;
	}
}
