package yann.study.activity.base;

<<<<<<< HEAD
import android.os.Bundle;
import android.view.View;
import android.view.Window;
=======
import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
>>>>>>> f10554b7f2cf896c7adf30c4ebe4ddd80ab190db
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import yann.study.R;

/**
<<<<<<< HEAD
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

=======
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
>>>>>>> f10554b7f2cf896c7adf30c4ebe4ddd80ab190db
