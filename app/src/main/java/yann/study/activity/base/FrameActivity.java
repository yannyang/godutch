package yann.study.activity.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import yann.study.R;

/**
 * Created by Administrator on 2015/5/12.
 */
public class FrameActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }
    //根据视图ID增加到主布局中
    protected void AppendMainBody(int pResID){
        LinearLayout _LinearLayout=(LinearLayout)findViewById(R.id.llMainBody);
       View _View=View.inflate(this,pResID,null);
       // View _View= LayoutInflater.from(this).inflate(pResID,null);
        RelativeLayout.LayoutParams _LayoutParams= new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        _LinearLayout.addView(_View,_LayoutParams);
    }
}
