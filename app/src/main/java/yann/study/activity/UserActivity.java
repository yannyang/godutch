package yann.study.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import yann.study.R;
import yann.study.activity.base.FrameActivity;
import yann.study.adapter.UserItemAdapter;
import yann.study.business.UserBusiness;
import yann.study.controls.SlideMenuItem;
import yann.study.controls.SlideMenuView;
import yann.study.model.UserModel;
import yann.study.utility.RegexTools;


public class UserActivity extends FrameActivity implements SlideMenuView.OnSlideMenuListener{
    private ListView mListView;
    private UserItemAdapter mUserItemAdapter;
    private UserBusiness mBusinessUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("yann", "用户管理程序启动");
        appendMainBody(R.layout.user_list);
        initView();
        initVariable();
        bindData();
        createSlideMenu(R.array.SlideUserItem);

    }

    private void initVariable() {
        mUserItemAdapter = new UserItemAdapter(this);
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.lvUserList);
    }

    private void InitListeners() {

    }

    private void bindData() {
        mUserItemAdapter = new UserItemAdapter(this);
        mListView.setAdapter(mUserItemAdapter);
    }

    @Override
    public void onSlideMenuItemClick(View pView, SlideMenuItem pSlideMenuItem) {
        slideMenuToggle();
        if (pSlideMenuItem.getItemId() == 0) {
            ShowUserAddOrEditDialog(null);
        }

    }
    private void ShowUserAddOrEditDialog(UserModel pUserModel)
    {
        View _View = getLayouInflater().inflate(R.layout.user_add_or_edit, null);

        EditText _etUserName = (EditText) _View.findViewById(R.id.etUserName);

        if (pUserModel != null) {
            _etUserName.setText(pUserModel.getUserName());
        }

        String _Title;

        if(pUserModel == null)
        {
            _Title = getString(R.string.DialogTitleUser,new Object[]{getString(R.string.TitleAdd)});
        }
        else {
            _Title = getString(R.string.DialogTitleUser,new Object[]{getString(R.string.TitleEdit)});
        }

        AlertDialog.Builder _Builder = new AlertDialog.Builder(this);
        _Builder.setTitle(_Title)
                .setView(_View)
                .setIcon(R.drawable.user)
                .setNeutralButton(getString(R.string.ButtonTextSave), new OnAddOrEditUserListener(pUserModel,_etUserName,true))
                .setNegativeButton(getString(R.string.ButtonTextCancel), new OnAddOrEditUserListener(null,null,false))
                .show();
    }
    private class OnAddOrEditUserListener implements DialogInterface.OnClickListener
    {
        private UserModel mModelUser;
        private EditText etUserName;
        private boolean mIsSaveButton;

        public OnAddOrEditUserListener(UserModel pModelUser,EditText petUserName,Boolean pIsSaveButton)
        {
            mModelUser = pModelUser;
            etUserName = petUserName;
            mIsSaveButton = pIsSaveButton;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(mIsSaveButton == false)
            {
                setAlertDialogIsClose(dialog, true);
                return;
            }

            if (mModelUser == null) {
                mModelUser = new UserModel();
            }

            String _UserName = etUserName.getText().toString().trim();

            boolean _CheckResult = RegexTools.IsChineseEnglishNum(_UserName);

            if (!_CheckResult) {
                Toast.makeText(getApplicationContext(), getString(R.string.CheckDataTextChineseEnglishNum,new Object[]{etUserName.getHint()}),Toast.LENGTH_SHORT).show();
                setAlertDialogIsClose(dialog, false);
                return;
            }
            else {
                setAlertDialogIsClose(dialog, true);
            }

//            _CheckResult = mBusinessUser.isExistUserByUserName(_UserName, mModelUser.getUserId());
            _CheckResult = mBusinessUser.isExistUserByUserName(_UserName, 1);

            if (_CheckResult) {
                Toast.makeText(getApplicationContext(), getString(R.string.CheckDataTextUserExist), Toast.LENGTH_SHORT).show();

                setAlertDialogIsClose(dialog, false);
                return;
            }
            else {
                setAlertDialogIsClose(dialog, true);
            }

            mModelUser.setUserName(etUserName.getText().toString());

            boolean _Result = false;

            if (mModelUser.getUserId() == 0) {
                _Result = mBusinessUser.insertUser(mModelUser);
            }
            else {
                _Result = mBusinessUser.updateUser(mModelUser);
            }

            if (_Result) {
                bindData();
            }
            else {
                Toast.makeText(UserActivity.this, getString(R.string.TipsAddFail), Toast.LENGTH_SHORT).show();
            }
        }

    }
}

