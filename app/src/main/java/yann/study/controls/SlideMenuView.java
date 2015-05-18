package yann.study.controls;

import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import yann.study.R;

/**
 * Created by yann on 2015/5/16.
 * 滑动菜单
 */
public class SlideMenuView {
    private Activity mActivity;
    private List mList;
    private boolean mMenuState;
    private RelativeLayout mRelativeLayout;

    public SlideMenuView(Activity pActivity) {

        mActivity = pActivity;
        InitVariable();
        InitView();
        InitListeners();
    }

    private void Open() {
RelativeLayout.LayoutParams _LayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        _LayoutParams.addRule(RelativeLayout.BELOW,R.id.layTopBox);
        mMenuState=false;
    }

    public void Close() {
    }

    public void Toggle() {
    }

    public void Add() {
    }

    public void BindList() {
    }

    private void InitVariable() {
        mRelativeLayout = (RelativeLayout) mActivity.findViewById(R.id.layBottomBox);
        mList = new ArrayList();
        mMenuState = true;
    }

    private void InitView() {

    }

    private void InitListeners() {
        mRelativeLayout.setOnClickListener(new OnSlideMenuClick());
    }

    public void OnSlideMenuClick() {
    }

    private class OnSlideMenuClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {

        }
    }
}
