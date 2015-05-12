package yann.study.activity;

import android.os.Bundle;
import android.widget.Button;

import yann.study.R;
import yann.study.activity.base.FrameActivity;

public class MainActivity extends FrameActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.test_layout);
        AppendMainBody(R.layout.test_layout);
    }
}
