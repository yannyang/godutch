package yann.study.model;

import java.util.Date;

/**
 * Created by yann on 2015/5/19.
 * 用户实体
 */
public class ModelUser {
    //用户表ID主键
    private int mUserId;
    //用户姓名
    private String mUserName;
    //用户创建日期
    private Date mCreateDate=new Date();
    //用户状态0失效，1启用
    private int mState=1;


    public int getUserId() {
        return mUserId;
    }

    public String getUserName() {
        return mUserName;
    }

    public Date getCreateDate() {
        return mCreateDate;
    }

    public void setUserId(int pUserId) {
        this.mUserId = pUserId;
    }

    public void setUserName(String pUserName) {
        this.mUserName = pUserName;
    }

    public int getState() {
        return mState;
    }

    public void setState(int pState) {
        this.mState = pState;
    }


    public void setCreateDate(Date pCreateDate) {
        this.mCreateDate = pCreateDate;
    }
}
