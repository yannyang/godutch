package yann.study.business;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import yann.study.business.base.BaseBusiness;
import yann.study.database.sqlitedal.SQLiteDALUser;
import yann.study.model.ModelUser;

/**
 * Created by yann on 2015/5/20.
 * 人员管理业务处理
 */
public class BusinessUser extends BaseBusiness {
    SQLiteDALUser mSQLiteDALUser;

    public BusinessUser(Context pContext) {
        super(pContext);
        mSQLiteDALUser = new SQLiteDALUser(pContext);
    }
    public Boolean hideUserByUserID(int pUserID)
    {
        String _Condition = " UserID = " + pUserID;
        ContentValues _ContentValues = new ContentValues();
        _ContentValues.put("State", 0);
        Boolean _Result = mSQLiteDALUser.updateUser(_Condition, _ContentValues);

        if(_Result)
        {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean insertUser(ModelUser pModelUser) {
        return mSQLiteDALUser.insertUser(pModelUser);
    }

    public boolean deleteUserById(int pUserId) {
        String _Condition = "And UserId=" + pUserId;
        return mSQLiteDALUser.deleteUser(_Condition);
    }

    public boolean updateUser(ModelUser pModelUser) {
        String _Condition = "UserId=" + pModelUser.getUserId();
        return mSQLiteDALUser.updateUser(_Condition, pModelUser);
    }

    private List<ModelUser> getList(String pCondition) {
        return mSQLiteDALUser.getUser(pCondition);
    }
    public List<ModelUser> getNotHideUser() {
        String _Condition=" And State=1";
        return mSQLiteDALUser.getUser(_Condition);
    }
    public ModelUser getUserById(int pUserId) {
        String _Condition = "And UserId=" + pUserId;
        List<ModelUser> _ModelUser;
        _ModelUser = mSQLiteDALUser.getUser(_Condition);
        if (_ModelUser.size() == 1) {
            return _ModelUser.get(0);
        } else {
            return null;
        }
    }
    public ModelUser getModelUserByUserID(int pUserID) {
        List<ModelUser> _List = mSQLiteDALUser.getUser(" And UserID = " + pUserID);
        if (_List.size() == 1) {
            return _List.get(0);
        }
        else {
            return null;
        }
    }
    public String getUserNameByUserID(String p_UserID)
    {
        List<ModelUser> _List = getUserListByUserID(p_UserID.split(","));
        String _Name = "";

        for(int i=0;i<_List.size();i++)
        {
            _Name += _List.get(i).getUserName() + ",";
        }
        return _Name;
    }

    public List<ModelUser> getUserListByUserID(String pUserID[]) {
        List<ModelUser> _List = new ArrayList<ModelUser>();
        for (int i = 0; i < pUserID.length; i++) {
            _List.add(getModelUserByUserID(Integer.valueOf(pUserID[i])));
        }

        return _List;
    }


    public List<ModelUser> getUserById(int pUserId[]) {
        List<ModelUser> _ModelUserList =new ArrayList<ModelUser>();
        for (int i = 0; i <pUserId.length ; i++) {
            _ModelUserList.add(getUserById(pUserId[i]));
        }
        return _ModelUserList;
    }

    public boolean isExistUserByUserName(String pUserName,Integer pUserID)
    {
        String _Condition = " And UserName = '" + pUserName + "'";
        if(pUserID != null)
        {
            _Condition += " And UserId <> " + pUserID;
        }
        List _List = mSQLiteDALUser.getUser(_Condition);
        if (_List.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

}
