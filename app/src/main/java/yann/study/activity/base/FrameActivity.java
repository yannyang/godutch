package yann.study.activity.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import yann.study.R;

/**
 * Created by Administrator on 2015/5/13.
 */
public class FrameActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
    public void AppendMainBody(Integer pResId){
        LayoutInflater _LayoutInflater=LayoutInflater.from(this);
        RelativeLayout _RelativeLayout=(RelativeLayout)_LayoutInflater.inflate(pResId, null);
        LinearLayout _LinearLayout=(LinearLayout)findViewById(R.id.ll_main_body);
        _LinearLayout.addView(_RelativeLayout);

    }
}