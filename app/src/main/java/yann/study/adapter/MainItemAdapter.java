package yann.study.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import yann.study.R;

/**
 * Created by yann on 2015/5/13.
 * 功能主菜单项目适配器 *
 */
public class MainItemAdapter extends BaseAdapter {

    private  class Holder{
        ImageView ivMainIcon;
        TextView tvMainTitle;
    }
    //Init main body app imageview id
    private Integer[] mImageInteger={
            R.drawable.app_recordkeeping,
            R.drawable.app_keepingquerying,
            R.drawable.app_countmanage,
            R.drawable.app_accountbookmanage,
            R.drawable.app_classcsmanage,
            R.drawable.app_porsonnalmanage
    };

    private String[] mTextView= new String[6];
    private Context mContext;

    public MainItemAdapter(Context pContext) {
        mContext=pContext;
        mTextView[0]=pContext.getString(R.string.body_title_record_keeping);
        mTextView[1]=pContext.getString(R.string.body_title_keeping_querying);
        mTextView[2]=pContext.getString(R.string.body_title_count_manage);
        mTextView[3]=pContext.getString(R.string.body_title_accountbook_manage);
        mTextView[4]=pContext.getString(R.string.body_title_classcs_manage);
        mTextView[5]=pContext.getString(R.string.body_title_porsonnal_manage);
    }

    @Override
    public int getCount() {
        return mImageInteger.length;
    }

    @Override
    public Object getItem(int position) {
        return mTextView[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder _Holder;
        if(convertView==null){
            LayoutInflater _LayoutInflater=LayoutInflater.from(mContext);
            convertView=_LayoutInflater.inflate(R.layout.main_item,null);
            _Holder=new Holder();
            _Holder.ivMainIcon=(ImageView)convertView.findViewById(R.id.ivMainIcon);
            _Holder.tvMainTitle=(TextView)convertView.findViewById(R.id.tvMainTitle);
            convertView.setTag(_Holder);
        }else{
            _Holder=(Holder)convertView.getTag();
        }
        _Holder.ivMainIcon.setImageResource(mImageInteger[position]);
        _Holder.tvMainTitle.setText(mTextView[position]);
        return convertView;
    }
}
