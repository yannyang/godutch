package yann.study.business;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import yann.study.business.base.BaseBusiness;
import yann.study.database.sqlitedal.SQLiteDALUser;
import yann.study.model.UserModel;

/**
 * Created by yann on 2015/5/20.
 * 人员管理业务处理
 */
public class UserBusiness extends BaseBusiness {
    SQLiteDALUser mSQLiteDALUser;

    public UserBusiness(Context pContext) {
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
    public boolean insertUser(UserModel pUserModel) {
        return mSQLiteDALUser.insertUser(pUserModel);
    }

    public boolean deleteUserById(int pUserId) {
        String _Condition = "And UserId=" + pUserId;
        return mSQLiteDALUser.deleteUser(_Condition);
    }

    public boolean updateUser(UserModel pUserModel) {
        String _Condition = "UserId=" + pUserModel.getUserId();
        return mSQLiteDALUser.updateUser(_Condition, pUserModel);
    }

    private List<UserModel> getList(String pCondition) {
        return mSQLiteDALUser.getUser(pCondition);
    }
    public List<UserModel> getNotHideUser() {
        String _Condition=" And State=1";
        return mSQLiteDALUser.getUser(_Condition);
    }
    public UserModel getUserById(int pUserId) {
        String _Condition = "And UserId=" + pUserId;
        List<UserModel> _UserModel;
        _UserModel = mSQLiteDALUser.getUser(_Condition);
        if (_UserModel.size() == 1) {
            return _UserModel.get(0);
        } else {
            return null;
        }
    }

    public List<UserModel> getUserById(int pUserId[]) {
        List<UserModel> _UserModelList=new ArrayList<UserModel>();
        for (int i = 0; i <pUserId.length ; i++) {
            _UserModelList.add(getUserById(pUserId[i]));
        }
        return _UserModelList;
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
