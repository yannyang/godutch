package yann.study.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import yann.study.R;
import yann.study.adapter.base.AdapterBase;
import yann.study.business.BusinessUser;
import yann.study.model.ModelUser;

/**
 * Created by yann on 2015/5/13.
 * 功能主菜单项目适配器 *
 */
public class AdapterUser extends AdapterBase {
    private  class Holder{
        ImageView ivUserIcon;
         TextView tvUserListName;
    }
    public AdapterUser(Context pContext)
    {
        super(pContext, null);
        BusinessUser _BusinessUser =new BusinessUser(pContext);
        List<ModelUser> _ModelUsers = _BusinessUser.getNotHideUser();
        setList(_ModelUsers);
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
        ModelUser _ModelUser = (ModelUser) getList().get(position);
        _Holder.ivUserIcon.setImageResource(R.drawable.user);
        _Holder.tvUserListName.setText(_ModelUser.getUserName());
        return convertView;
    }
}
