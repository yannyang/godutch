package yann.study.activity.base;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import yann.study.R;

/**
 * Created by yann on 2015/5/12.
 * Activity窗口基类，可将与业务相亲的功能封装在此类中
 */
public class FrameActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }
    //根据视图ID增加到主布局中
    protected void AppendMainBody(int pResID){
        LinearLayout _LinearLayout=(LinearLayout)findViewById(R.id.ll_main_body);
       View _View=View.inflate(this,pResID,null);
       // View _View= LayoutInflater.from(this).inflate(pResID,null);
        RelativeLayout.LayoutParams _LayoutParams= new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        _LinearLayout.addView(_View,_LayoutParams);
    }
}

