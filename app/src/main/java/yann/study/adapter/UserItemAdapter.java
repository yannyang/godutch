package yann.study.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import yann.study.R;
import yann.study.adapter.base.GodutchBaseAdapter;
import yann.study.business.UserBusiness;
import yann.study.controls.SlideMenuItem;
import yann.study.model.UserModel;

/**
 * Created by yann on 2015/5/13.
 * 功能主菜单项目适配器 *
 */
public class UserItemAdapter extends GodutchBaseAdapter {
    private  class Holder{
        ImageView ivUserIcon;
         TextView tvUserListName;
    }
    public UserItemAdapter(Context pContext)
    {
        super(pContext, null);
        UserBusiness _UserBusiness=new UserBusiness(pContext);
        List<UserModel> _UserModels=_UserBusiness.getNotHideUser();
        setList(_UserModels);
    }

    private Context mContext;



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder _Holder;
        if(convertView==null){

            convertView=getLayoutInflater().inflate(R.layout.user_list_item,null);
            _Holder=new Holder();
            _Holder.ivUserIcon=(ImageView)convertView.findViewById(R.id.ivUserIcon);
            _Holder.tvUserListName=(TextView)convertView.findViewById(R.id.tvUserListName);
            convertView.setTag(_Holder);
        }else{
            _Holder=(Holder)convertView.getTag();
        }
        UserModel _UserModel = (UserModel) getList().get(position);
        _Holder.ivUserIcon.setImageResource(R.drawable.user);
        _Holder.tvUserListName.setText(_UserModel.getUserName());
        return convertView;
    }
}
