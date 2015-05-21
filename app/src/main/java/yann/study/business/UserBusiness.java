package yann.study.business;

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
    SQLiteDALUser mSqLiteDALUser;

    public UserBusiness(Context pContext) {
        super(pContext);
        mSqLiteDALUser = new SQLiteDALUser(pContext);
    }

    public boolean insertUser(UserModel pUserModel) {
        return mSqLiteDALUser.insertUser(pUserModel);
    }

    public boolean deleteUserById(int pUserId) {
        String _Condition = "And UserId=" + pUserId;
        return mSqLiteDALUser.deleteUser(_Condition);
    }

    public boolean updateUser(UserModel pUserModel) {
        String _Condition = "And UserId=" + pUserModel.getUserId();
        return mSqLiteDALUser.updateUser(_Condition, pUserModel);
    }

    private List<UserModel> getList(String pCondition) {
        return mSqLiteDALUser.getUser(pCondition);
    }
    public List<UserModel> getNotHideUser() {
        String _Condition=" And State=1";
        return mSqLiteDALUser.getUser(_Condition);
    }
    public UserModel getUserById(int pUserId) {
        String _Condition = "And UserId=" + pUserId;
        List<UserModel> _UserModel;
        _UserModel = mSqLiteDALUser.getUser(_Condition);
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
        List _List = mSqLiteDALUser.getUser(_Condition);
        if (_List.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

}
