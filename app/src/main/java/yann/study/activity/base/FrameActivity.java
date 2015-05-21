package yann.study.activity.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import yann.study.R;
import yann.study.controls.SlideMenuItem;
import yann.study.controls.SlideMenuView;

/**
 * Created by yann on 2015/5/12.
 * Activity窗口基类，可将与业务相亲的功能封装在此类中
 */
public class FrameActivity extends BaseActivity {
  SlideMenuView mSlideMenuView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }
    protected void slideMenuToggle() {
        mSlideMenuView.toggle();
    }
    //根据视图ID增加到主布局中
    protected void appendMainBody(int pResID){
        LinearLayout _LinearLayout=(LinearLayout)findViewById(R.id.ll_main_body);
      // View _View=View.inflate(this,pResID,null);
        View _View= LayoutInflater.from(this).inflate(pResID,null);
        RelativeLayout.LayoutParams _LayoutParams= new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        _LinearLayout.addView(_View, _LayoutParams);
    }
    //创建滑动菜单
    protected void createSlideMenu(int pResId){
        mSlideMenuView =new SlideMenuView(this);
        String[] _MenuItemArray=getResources().getStringArray(pResId);

        for (int i=0;i<_MenuItemArray.length;i++){
            SlideMenuItem _SlideMenuItem =new SlideMenuItem(i,_MenuItemArray[i]);

            mSlideMenuView.add(_SlideMenuItem);
        }
        mSlideMenuView.bindList();
    }
}

