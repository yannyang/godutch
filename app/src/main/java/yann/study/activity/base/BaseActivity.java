package yann.study.activity.base;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/5/12.
 * 自己创建Activity基础类，方便一些简便操作
 */
public class BaseActivity extends Activity {
    /**
     * 显示浮出信息
     * @param pMsg 需要显示的信息
     */
    protected void ShowMsg(String pMsg){
        Toast.makeText(this,pMsg,Toast.LENGTH_SHORT).show();
    }

    /**
     * 打开一个新Activity
     * @param mClass 需要打开的Activity类名
     */
    protected void OpenActivity(Class<?> mClass){
        Intent _intent= new Intent(this,mClass);
       startActivity(_intent);
    }
}
