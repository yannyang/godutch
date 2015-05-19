package yann.study.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import yann.study.R;
import yann.study.adapter.base.GodutchBaseAdapter;
import yann.study.controls.SlideMenuItem;

/**
 * Created by yann on 2015/5/13.
 * 功能主菜单项目适配器 *
 */
public class UserItemAdapter extends GodutchBaseAdapter {
    private  class Holder{
         TextView tvMenuListItem;
    }
    public UserItemAdapter(Context pContext, List pList) {
        super(pContext, pList);
    }

    private Context mContext;



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder _Holder;
        if(convertView==null){

            convertView=getLayoutInflater().inflate(R.layout.menu_list_item,null);
            _Holder=new Holder();
            _Holder.tvMenuListItem=(TextView)convertView.findViewById(R.id.tvListItemTitle);
            convertView.setTag(_Holder);
        }else{
            _Holder=(Holder)convertView.getTag();
        }
        SlideMenuItem _SlideMenuItem =(SlideMenuItem)getList().get(position);
        _Holder.tvMenuListItem.setText(_SlideMenuItem.getTitle());
        return convertView;
    }
}
