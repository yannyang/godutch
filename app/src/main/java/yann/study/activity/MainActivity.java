package yann.study.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import yann.study.R;
import yann.study.activity.base.FrameActivity;
import yann.study.adapter.MainItemAdapter;
import yann.study.controls.SlideMenuItem;
import yann.study.controls.SlideMenuView;


public class MainActivity extends FrameActivity implements SlideMenuView.OnSlideMenuListener {
    private GridView gvMainBody;
    private MainItemAdapter mMainItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("yann", "程序启动");
        appendMainBody(R.layout.main_body);
        initVariable();
        initView();
        bindData();
        initListeners();
        createSlideMenu(R.array.SlideMenuItem);

    }

    private void initVariable() {
        mMainItemAdapter = new MainItemAdapter(this);
    }

    private void initView() {
        gvMainBody = (GridView) findViewById(R.id.gvMainBody);
    }

    private void initListeners() {
        gvMainBody.setOnItemClickListener(new onAppGridItemClickListener());
    }

    private void bindData() {
        gvMainBody.setAdapter(mMainItemAdapter);
    }



    @Override
    public void onSlideMenuItemClick(View pView, SlideMenuItem pSlideMenuItem) {
        slideMenuToggle();
      /*  if (pSlideMenuItem.getItemID() == 0) {
            ShowDatabaseBackupDialog();
        }*/
    }


    private class onAppGridItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView p_Adapter, View p_View, int p_Position, long arg3) {
            String _MenuName = (String) p_Adapter.getAdapter().getItem(p_Position);
            if (_MenuName.equals(getString(R.string.body_title_porsonnal_manage))) {
                openActivity(UserActivity.class);
                return;
            }
        }
    }
}

