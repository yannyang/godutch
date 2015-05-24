package yann.study.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import yann.study.R;
import yann.study.adapter.base.AdapterBase;
import yann.study.business.BusinessAccountBook;
import yann.study.model.ModelAccountBook;

public class AdapterBaseAccountBook extends AdapterBase {
	
	private class Holder
	{
		ImageView ivIcon;
		TextView tvName;
		TextView tvTotal;
		TextView tvMoney;
	}
	
	public AdapterBaseAccountBook(Context pContext) {
		super(pContext, null);
		BusinessAccountBook _BusinessAccountBook = new BusinessAccountBook(pContext);
		List _List = _BusinessAccountBook.GetAccountBook("");
		setList(_List);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder _Holder;
		
		if (convertView == null) {
			convertView = getLayoutInflater().inflate(R.layout.account_book_list_item, null);
			_Holder = new Holder();
			_Holder.ivIcon = (ImageView)convertView.findViewById(R.id.ivAccountBookIcon);
			_Holder.tvName = (TextView)convertView.findViewById(R.id.tvAccountBookName);
			_Holder.tvTotal = (TextView)convertView.findViewById(R.id.tvTotal);
			_Holder.tvMoney = (TextView)convertView.findViewById(R.id.tvMoney);
			convertView.setTag(_Holder);
		}
		else {
			_Holder = (Holder) convertView.getTag();
		}
		
		ModelAccountBook _ModelAccountBook = (ModelAccountBook)getItem(position);
		if(_ModelAccountBook.GetIsDefault() == 1)
		{
			_Holder.ivIcon.setImageResource(R.drawable.account_book_default);
		}
		else {
			_Holder.ivIcon.setImageResource(R.drawable.account_book_big_icon);
		}
		
		/*BusinessPayout _BusinessPayout = new BusinessPayout(GetContext());
		String _Total[] = _BusinessPayout.GetPayoutTotalByAccountBookID(_ModelAccountBook.GetAccountBookID());		*/

//		_Holder.tvTotal.setText(FormatResString(R.string.TextViewTextAccountBookPayoutTotal, new Object[]{_Total[0]}));
		_Holder.tvName.setText(_ModelAccountBook.GetAccountBookName());
//		_Holder.tvMoney.setText(FormatResString(R.string.TextViewTextAccountBookPayoutMoney, new Object[]{_Total[1]}));
		
		return convertView;
	}

}
