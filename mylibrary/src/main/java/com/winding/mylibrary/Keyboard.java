package com.winding.mylibrary;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by HY on 2016/5/2.
 * 软键盘显示、隐藏工具类
 */
public class Keyboard {

    /**
     * 显示或隐藏软键盘
     *
     * @param view
     * @param isShow true = show , false = hide
     */
    public static void toggleSoftKeyboard(View view, boolean isShow) {
        Context context = view.getContext();
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(INPUT_METHOD_SERVICE);
        if (isShow) {
            view.requestFocus();
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        } else {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 显示软键盘
     *
     * @param view
     */
    public static void showSoftKeyboard(View view) {
        Context context = view.getContext();
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(INPUT_METHOD_SERVICE);
        view.requestFocus();
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }


    public static boolean isShow(Context context){
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            return true;
        }
        return false;
    }



    /**
     * 隐藏软键盘
     *
     * @param view
     */
    public static void hideSoftKeyboard(View view) {
        Context context = view.getContext();
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(INPUT_METHOD_SERVICE);
        //imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }



/**
 * 打开软键盘
 * 魅族可能会有问题
 *
 * @param mEditText
 * @param mContext
 */
public static void openKeybord(EditText mEditText, Context mContext) {
    InputMethodManager imm = (InputMethodManager) mContext
            .getSystemService(INPUT_METHOD_SERVICE);
    imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
            InputMethodManager.HIDE_IMPLICIT_ONLY);
}

/**
 * 关闭软键盘
 *
 * @param mEditText
 * @param mContext
 */
public static void closeKeybord(EditText mEditText, Context mContext) {
    InputMethodManager imm = (InputMethodManager) mContext.getSystemService(INPUT_METHOD_SERVICE);

    imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
}


/**
 * des:隐藏软键盘,这种方式参数为activity
 *
 * @param activity
 */
public static void hideInputForce(Activity activity) {
    if (activity == null || activity.getCurrentFocus() == null)
        return;

    ((InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE))
            .hideSoftInputFromWindow(activity.getCurrentFocus()
                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
}

/**
 * 打开键盘
 **/
public static void showInput(View view) {
    InputMethodManager imm = (InputMethodManager)view.getContext().getSystemService(INPUT_METHOD_SERVICE);
    if (imm != null) {
        view.requestFocus();
        imm.showSoftInput(view, 0);
    }
}
}