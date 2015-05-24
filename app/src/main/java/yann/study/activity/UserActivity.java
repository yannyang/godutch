package yann.study.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import yann.study.R;
import yann.study.activity.base.FrameActivity;
import yann.study.adapter.AdapterUser;
import yann.study.business.BusinessUser;
import yann.study.controls.SlideMenuItem;
import yann.study.controls.SlideMenuView;
import yann.study.model.ModelUser;
import yann.study.utility.RegexTools;


public class UserActivity extends FrameActivity implements SlideMenuView.OnSlideMenuListener {
    private ListView mUserListView;
    private AdapterUser mUserItemAdapter;
    private BusinessUser mBusinessUser;
    private ModelUser mSelectModlUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("yann", "用户管理程序启动");
        appendMainBody(R.layout.user_list);
        initView();
        initVariable();
        bindData();
        initListeners();
        createSlideMenu(R.array.SlideUserItem);

    }

    private void initVariable() {
        mBusinessUser = new BusinessUser(this);
    }

    private void initView() {
        mUserListView = (ListView) findViewById(R.id.lvUserList);
    }

    private void initListeners() {
        registerForContextMenu(mUserListView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        AdapterContextMenuInfo _AdapterContextMenuInfo = (AdapterContextMenuInfo) menuInfo;
        ListAdapter _ListAdapter = mUserListView.getAdapter();

        mSelectModlUser = (ModelUser)_ListAdapter.getItem(_AdapterContextMenuInfo.position);

        menu.setHeaderIcon(R.drawable.user);
        menu.setHeaderTitle(mSelectModlUser.getUserName());

        createMenu(menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                ShowUserAddOrEditDialog(mSelectModlUser);
                break;
            case 2:
                delete();
                break;
            default:
                break;
        }

        return super.onContextItemSelected(item);
    }
    private void delete() {
        String _Message = getString(R.string.DialogMessageUserDelete,new Object[]{mSelectModlUser.getUserName()});
        ShowAlertDialog(R.string.DialogTitleDelete,_Message,new OnDeleteClickListener());
    }
    private class OnDeleteClickListener implements DialogInterface.OnClickListener
    {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            boolean _Result = mBusinessUser.hideUserByUserID(mSelectModlUser.getUserId());

            if (_Result == true) {
                bindData();
            }
        }
    }
    private void bindData() {
        mUserItemAdapter = new AdapterUser(this);
        mUserListView.setAdapter(mUserItemAdapter);
    }

    @Override
    public void onSlideMenuItemClick(View pView, SlideMenuItem pSlideMenuItem) {
        slideMenuToggle();
        if (pSlideMenuItem.getItemId() == 0) {
            ShowUserAddOrEditDialog(null);
        }

    }

    private void ShowUserAddOrEditDialog(ModelUser pModelUser) {
        View _View = getLayouInflater().inflate(R.layout.user_add_or_edit, null);

        EditText _etUserName = (EditText) _View.findViewById(R.id.etUserName);

        if (pModelUser != null) {
            _etUserName.setText(pModelUser.getUserName());
        }

        String _Title;

        if (pModelUser == null) {
            _Title = getString(R.string.DialogTitleUser, new Object[]{getString(R.string.TitleAdd)});
        } else {
            _Title = getString(R.string.DialogTitleUser, new Object[]{getString(R.string.TitleEdit)});
        }

        AlertDialog.Builder _Builder = new AlertDialog.Builder(this);
        _Builder.setTitle(_Title)
                .setView(_View)
                .setIcon(R.drawable.user)
                .setNeutralButton(getString(R.string.ButtonTextSave), new OnAddOrEditUserListener(pModelUser, _etUserName, true))
                .setNegativeButton(getString(R.string.ButtonTextCancel), new OnAddOrEditUserListener(null, null, false))
                .show();
    }


    private class OnAddOrEditUserListener implements DialogInterface.OnClickListener {
        private ModelUser mModelUser;
        private EditText etUserName;
        private boolean mIsSaveButton;

        public OnAddOrEditUserListener(ModelUser pModelUser, EditText petUserName, Boolean pIsSaveButton) {
            mModelUser = pModelUser;
            etUserName = petUserName;
            mIsSaveButton = pIsSaveButton;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (mIsSaveButton == false) {
                setAlertDialogIsClose(dialog, true);
                return;
            }

            if (mModelUser == null) {
                mModelUser = new ModelUser();
            }

            String _UserName = etUserName.getText().toString().trim();

            boolean _CheckResult = RegexTools.IsChineseEnglishNum(_UserName);

            if (!_CheckResult) {
                Toast.makeText(getApplicationContext(), getString(R.string.CheckDataTextChineseEnglishNum, new Object[]{etUserName.getHint()}), Toast.LENGTH_SHORT).show();
                setAlertDialogIsClose(dialog, false);
                return;
            } else {
                setAlertDialogIsClose(dialog, true);
            }
            _CheckResult = mBusinessUser.isExistUserByUserName(_UserName, mModelUser.getUserId());
            if (_CheckResult) {
                Toast.makeText(getApplicationContext(), getString(R.string.CheckDataTextUserExist), Toast.LENGTH_SHORT).show();

                setAlertDialogIsClose(dialog, false);
                return;
            } else {
                setAlertDialogIsClose(dialog, true);
            }

            mModelUser.setUserName(etUserName.getText().toString());

            boolean _Result = false;

            if (mModelUser.getUserId() == 0) {
                _Result = mBusinessUser.insertUser(mModelUser);
            } else {
                _Result = mBusinessUser.updateUser(mModelUser);
            }

            if (_Result) {
                bindData();
            } else {
                Toast.makeText(UserActivity.this, getString(R.string.TipsAddFail), Toast.LENGTH_SHORT).show();
            }
        }

    }
}

