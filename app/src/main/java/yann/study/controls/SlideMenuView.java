package yann.study.controls;

import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import yann.study.R;
import yann.study.adapter.MenuItemAdapterBase;

/**
 * Created by yann on 2015/5/16.
 * 滑动菜单
 */
public class SlideMenuView {
    private Activity mActivity;
    private List mList;
    private boolean mMenuState;
    private RelativeLayout layBottomBox;
    private int mRelativeLayoutHeight;
    private OnSlideMenuListener mOnSlideMenuListener;

    public interface OnSlideMenuListener{
        public abstract void onSlideMenuItemClick(View pView,SlideMenuItem pSlideMenuItem);
    }

    public SlideMenuView(Activity pActivity) {

        mActivity = pActivity;
        initView();
        if (pActivity instanceof OnSlideMenuListener) {
            mOnSlideMenuListener = (OnSlideMenuListener) pActivity;
            initVariable();
            initListeners();
        }
    }
    public void initView() {
        layBottomBox = (RelativeLayout) mActivity.findViewById(R.id.layBottomBox);
    }

    private void open() {
        Log.i("yann","Open方法："+ layBottomBox.getHeight() + "");
        RelativeLayout.LayoutParams _LayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        //RelativeLayout.LayoutParams _LayoutParams=(RelativeLayout.LayoutParams)layBottomBox.getLayoutParams();
      //  _LayoutParams.height=RelativeLayout.LayoutParams.MATCH_PARENT;
     //   _LayoutParams.width=RelativeLayout.LayoutParams.MATCH_PARENT;
        _LayoutParams.addRule(RelativeLayout.BELOW, R.id.layTopBox);
        layBottomBox.setLayoutParams(_LayoutParams);
        mMenuState = false;
    }

    private void close() {
        Log.i("yann","Close方法"+ layBottomBox.getHeight() + "");
        RelativeLayout.LayoutParams _LayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, mRelativeLayoutHeight);
      //  RelativeLayout.LayoutParams _LayoutParams=(RelativeLayout.LayoutParams)layBottomBox.getLayoutParams();
       // _LayoutParams.height=100;
        _LayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layBottomBox.setLayoutParams(_LayoutParams);
        mMenuState = true;
    }

    public void toggle() {
        if (mMenuState) {
            open();
        } else {
            close();
        }
    }

    public void add(SlideMenuItem pSlideMenuItem) {
        mList.add(pSlideMenuItem);
    }

    public void bindList() {
        MenuItemAdapterBase _MenuItemAdapter = new MenuItemAdapterBase(mActivity, mList);
        ListView _ListView = (ListView) mActivity.findViewById(R.id.lvSlideList);
        _ListView.setAdapter(_MenuItemAdapter);
        _ListView.setOnItemClickListener(new OnSlideMenuItemClick());
    }

    private void initVariable() {
        layBottomBox = (RelativeLayout) mActivity.findViewById(R.id.layBottomBox);
        mList = new ArrayList();
        mRelativeLayoutHeight= layBottomBox.getLayoutParams().height;
        mMenuState = true;
    }

    private void initListeners() {
        layBottomBox.setOnClickListener(new OnSlideMenuClick());
        layBottomBox.setFocusableInTouchMode(true);
        layBottomBox.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_MENU && event.getAction() == KeyEvent.ACTION_UP) {
                    toggle();
                }
                return false;
            }
        });
    }

    private class OnSlideMenuClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
             toggle();

        }
    }

    private class OnSlideMenuItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            SlideMenuItem _SlideMenuItem =(SlideMenuItem)parent.getItemAtPosition(position);
            mOnSlideMenuListener.onSlideMenuItemClick(view, _SlideMenuItem);
        }
    }
    public void RemoveBottomBox()
    {
        RelativeLayout _MainLayout = (RelativeLayout)mActivity.findViewById(R.id.layMainLayout);
        _MainLayout.removeView(layBottomBox);
        layBottomBox = null;
    }

}
