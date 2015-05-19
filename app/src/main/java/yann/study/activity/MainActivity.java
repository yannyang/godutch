package yann.study.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import yann.study.R;
import yann.study.activity.base.FrameActivity;
import yann.study.adapter.MainItemAdapter;
import yann.study.controls.SlideMenuView;
import yann.study.controls.SliderMenuItem;


public class MainActivity extends FrameActivity implements SlideMenuView.OnSlideMenuListener{
    private GridView gvMainBody;
    private MainItemAdapter mMainItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("yann", "程序启动");
        setContentView(R.layout.activity_main);
        AppendMainBody(R.layout.main_body);
        InitVariable();
        InitView();
        BindData();
        CreateSlideMenu(R.array.SlideMenuItem);
    }

    private void InitVariable() {
        mMainItemAdapter = new MainItemAdapter(this);
    }

    private void InitView() {
        gvMainBody = (GridView) findViewById(R.id.gvMainBody);
    }

    private void InitListeners() {

    }

    private void BindData() {
        gvMainBody.setAdapter(mMainItemAdapter);
    }

    @Override
    public void onSlideMenuItemClick(View pView, SliderMenuItem pSliderMenuItem) {
        ShowMsg(pSliderMenuItem.getTitle());
    }
}

