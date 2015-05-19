package yann.study.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import yann.study.R;
import yann.study.activity.base.FrameActivity;
import yann.study.adapter.MainItemAdapter;
import yann.study.controls.SlideMenuView;
import yann.study.controls.SlideMenuItem;


public class MainActivity extends FrameActivity implements SlideMenuView.OnSlideMenuListener{
    private GridView gvMainBody;
    private MainItemAdapter mMainItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("yann", "程序启动");
        setContentView(R.layout.activity_main);
        appendMainBody(R.layout.main_body);
        initVariable();
        initView();
        bindData();
        createSlideMenu(R.array.SlideMenuItem);

    }

    private void initVariable() {
        mMainItemAdapter = new MainItemAdapter(this);
    }

    private void initView() {
        gvMainBody = (GridView) findViewById(R.id.gvMainBody);
    }

    private void InitListeners() {

    }

    private void bindData() {
        gvMainBody.setAdapter(mMainItemAdapter);
    }

    @Override
    public void onSlideMenuItemClick(View pView, SlideMenuItem pSlideMenuItem) {
        showMsg(pSlideMenuItem.getTitle());
    }
}

