package yann.study.business.base;

import android.content.Context;

/**
 * Created by aynn 2015/5/20.
 * 业务基础类
 */
public class BaseBusiness {
    private Context mContext;

    public BaseBusiness(Context pContext) {
        this.mContext = pContext;
    }

    protected String getString(int pResID){
        return  mContext.getString(pResID);
    }

    protected String getString(int pResID,Object[] pObject){
        return  mContext.getString(pResID,pObject);
    }
}
