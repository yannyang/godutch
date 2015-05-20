package yann.study.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import yann.study.R;
import yann.study.activity.base.FrameActivity;
import yann.study.adapter.UserItemAdapter;
import yann.study.controls.SlideMenuItem;
import yann.study.controls.SlideMenuView;


public class UserActivity extends FrameActivity implements SlideMenuView.OnSlideMenuListener{
    private ListView mListView;
    private UserItemAdapter mUserItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("yann", "用户管理程序启动");
        appendMainBody(R.layout.user_list);
        initVariable();
        initView();
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
        mListView.setAdapter(mUserItemAdapter);
    }

    @Override
    public void onSlideMenuItemClick(View pView, SlideMenuItem pSlideMenuItem) {
        showMsg(pSlideMenuItem.getTitle());
    }
}

