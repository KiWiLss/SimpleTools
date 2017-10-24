package com.winding.mylibrary;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
    static Toast toast;

    private ToastUtils() {
        throw new AssertionError();
    }


    /**短时间吐司资源文字
     * @param resource
     */
    public static void show( int resource) {
        show(MyApp.getContext(), MyApp.getContext().getResources().getText(resource), Toast.LENGTH_SHORT,0);
    }

    /**短时间吐司文字
     * @param
     */
    public static void show( String msg) {
        show(MyApp.getContext(), msg, Toast.LENGTH_SHORT,0);
    }
    /**短时间吐司文字,设置位置
     * @param
     */
    public static void show( String msg,int gravity) {
        show(MyApp.getContext(), msg, Toast.LENGTH_SHORT,gravity);
    }


    /**短时间吐司资源文字,自定义显示时间
     * @param resource
     */
    public static void showCustom( int resource,int customTime) {
        show(MyApp.getContext(), MyApp.getContext().getResources().getText(resource), customTime,0);
    }
    /**短时间吐司资源文字,可设置显示位置
     * @param resource
     */
    public static void show( int resource,int gravity) {
        show(MyApp.getContext(), MyApp.getContext().getResources().getText(resource), Toast.LENGTH_SHORT,gravity);
    }

    /**长时间吐司资源文字
     * @param resource
     */
    public static void showL(int resource,int gravity) {
        show(MyApp.getContext(), MyApp.getContext().getResources().getText(resource), Toast.LENGTH_LONG,gravity);
    }

    /**长时间吐司文字
     * @param resource
     */
    public static void showL(String resource) {
        show(MyApp.getContext(),resource, Toast.LENGTH_LONG,0);
    }

    /**长时间吐司文字,设置位置
     * @param resource
     */
    public static void showL(String resource,int gravity) {
        show(MyApp.getContext(),resource, Toast.LENGTH_LONG,gravity);
    }

    /**长时间吐司资源文字,自定义显示时间
     * @param resource
     */
    public static void showLCustom(int resource,int customTime) {
        show(MyApp.getContext(), MyApp.getContext().getResources().getText(resource), customTime,0);
    }
    /**长时间吐司资源文字
     * @param resource
     */
    public static void showL(int resource) {
        show(MyApp.getContext(), MyApp.getContext().getResources().getText(resource), Toast.LENGTH_LONG,0);
    }

    public static void show(Context paramContext, CharSequence paramCharSequence, int paramInt,int gravity) {
        if (toast != null) {
            toast.setText(paramCharSequence);
            toast.setDuration(paramInt);
            if (gravity!=0){
                toast.setGravity(gravity,0,0);
            }
            toast.show();
            return;
        }
        toast = Toast.makeText(paramContext, paramCharSequence, paramInt);
        if (gravity!=0){
            toast.setGravity(gravity,0,0);
        }
        toast.show();
    }

    public static void setGravity(int gravity, int xOffset, int yOffset){
        if (toast != null) {
            toast.setGravity(gravity, xOffset, yOffset);
        }
    }

    public static void toastCancle() {
        if (toast != null)
            toast.cancel();
    }
}
