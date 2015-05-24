package yann.study.activity.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.Toast;

import java.lang.reflect.Field;

import yann.study.R;

/**
 * Created by Administrator on 2015/5/12.
 * 自己创建Activity基础类，方便一些简便操作
 */
public class BaseActivity extends Activity {
    private ProgressDialog m_ProgressDialog;
      /**
     * 显示浮出信息
     * @param pMsg 需要显示的信息
     */
    protected void showMsg(String pMsg){
        Toast.makeText(this,pMsg,Toast.LENGTH_SHORT).show();
    }

    /**
     * 打开一个新Activity
     * @param mClass 需要打开的Activity类名
     */
    protected void openActivity(Class<?> mClass){
        Intent _intent= new Intent(this,mClass);
       startActivity(_intent);
    }

    protected LayoutInflater getLayouInflater() {
        LayoutInflater _LayoutInflater = LayoutInflater.from(this);
        return _LayoutInflater;
    }
    /**
     * 关闭一个对话框
    * */
    public void setAlertDialogIsClose(DialogInterface pDialog,Boolean pIsClose)
    {
        try {
            Field _Field = pDialog.getClass().getSuperclass().getDeclaredField("mShowing");
            _Field.setAccessible(true);
            _Field.set(pDialog, pIsClose);
        } catch (Exception e) {
        }


    }

    protected void ShowProgressDialog(int p_TitleResID,int p_MessageResID) {
        m_ProgressDialog = new ProgressDialog(this);
        m_ProgressDialog.setTitle(getString(p_TitleResID));
        m_ProgressDialog.setMessage(getString(p_MessageResID));
        m_ProgressDialog.show();
    }

    protected void DismissProgressDialog() {
        if(m_ProgressDialog != null)
        {
            m_ProgressDialog.dismiss();
        }
    }
    protected AlertDialog ShowAlertDialog(int p_TitelResID,String p_Message,DialogInterface.OnClickListener p_ClickListener)
    {
        String _Title = getResources().getString(p_TitelResID);
        return ShowAlertDialog(_Title, p_Message, p_ClickListener);
    }

    protected AlertDialog ShowAlertDialog(String p_Title,String p_Message,DialogInterface.OnClickListener p_ClickListener)
    {
        return new AlertDialog.Builder(this)
                .setTitle(p_Title)
                .setMessage(p_Message)
                .setPositiveButton(R.string.ButtonTextYes, p_ClickListener)
                .setNegativeButton(R.string.ButtonTextNo, null)
                .show();
    }
}
