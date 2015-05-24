package yann.study.adapter.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by yann on 2015/5/18.
 * ��Ŀ������
 */
public abstract class AdapterBase extends BaseAdapter {
    private List mList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public AdapterBase(Context pContext, List pList) {
        mContext=pContext;
        mList=pList;
        mLayoutInflater=LayoutInflater.from(mContext);
    }

    public Context getContext() {
        return mContext;
    }

    public List getList() {
        return mList;
    }
    public void setList(List pList){
        mList=pList;
    }

    public LayoutInflater getLayoutInflater() {
        return mLayoutInflater;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

}