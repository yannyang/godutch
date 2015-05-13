package yann.study.activity;

import android.os.Bundle;
import android.widget.GridView;

import yann.study.R;
import yann.study.activity.base.FrameActivity;
import yann.study.adapter.MainItemAdapter;


public class MainActivity extends FrameActivity {
    private GridView gvMainBody;
    private MainItemAdapter mMainItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppendMainBody(R.layout.main_body);
        InitVariable();
        InitView();
        BindData();
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
}
