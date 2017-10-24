package com.winding.mylibrary;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 刘少帅 on 2017/10/24
 */

public class SPUtils {

    static final String TAG = "KIWI";
    /**
     * 是否包含某个键
     */
    public static boolean constains(String key) {
        SharedPreferences mySharedPreferences = MyApp.getContext().getSharedPreferences(
                TAG, Activity.MODE_PRIVATE);
        return mySharedPreferences.contains(key);
    }
    /**
     * 移除所有
     */
    public static void clear() {
        SharedPreferences mySharedPreferences = MyApp.getContext().getSharedPreferences(
                TAG, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
    /**移除某个
     * @param name
     */
    public static void remove(String name) {
        SharedPreferences mySharedPreferences = MyApp.getContext().getSharedPreferences(
                TAG, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.remove(name);
        editor.commit();
    }

    /**保存string类型的数据
     * @param name
     * @param value
     */
    public static void saveS(String name, String value) {
        SharedPreferences mySharedPreferences = MyApp.getContext().getSharedPreferences(
                TAG, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(name, value);
        editor.commit();
    }

    /**获取String类型的数据
     * @param name
     * @return
     */
    public static String getS(String name) {
        SharedPreferences mySharedPreferences = MyApp.getContext().getSharedPreferences(
                TAG, Activity.MODE_PRIVATE);
        return mySharedPreferences.getString(name, "");
    }

    public static void saveB(String name, boolean value) {
        SharedPreferences mySharedPreferences = MyApp.getContext().getSharedPreferences(
                TAG, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putBoolean(name,value);
        editor.commit();
    }
    public static boolean getB(String name) {
        SharedPreferences mySharedPreferences = MyApp.getContext().getSharedPreferences(
                TAG, Activity.MODE_PRIVATE);
        return mySharedPreferences.getBoolean(name,false);
    }
    public static void saveI(String name, int value) {
        SharedPreferences mySharedPreferences = MyApp.getContext().getSharedPreferences(
                TAG, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putInt(name,value);
        editor.commit();
    }
    public static int getI(String name) {
        SharedPreferences mySharedPreferences = MyApp.getContext().getSharedPreferences(
                TAG, Activity.MODE_PRIVATE);
        return mySharedPreferences.getInt(name,-999);
    }
    public static void saveSet(String name, Set<String> set) {
        SharedPreferences mySharedPreferences = MyApp.getContext().getSharedPreferences(
                TAG, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putStringSet(name, set);
        editor.commit();
    }
    public static Set<String> getSet(String name) {
        SharedPreferences mySharedPreferences = MyApp.getContext().getSharedPreferences(
                TAG, Activity.MODE_PRIVATE);
        return mySharedPreferences.getStringSet(name, new HashSet<String>());
    }
    public static void saveBitmap(String key, Bitmap bitmap) {
        SharedPreferences mySharedPreferences = MyApp.getContext().getSharedPreferences(
                TAG, Activity.MODE_PRIVATE);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String imageString = new String(Base64.encodeToString(byteArray,
                Base64.DEFAULT));
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(key, imageString);
        editor.commit();
    }

    public static Bitmap getBitmap(String key, String def) {
        SharedPreferences mySharedPreferences = MyApp.getContext().getSharedPreferences(
                TAG, Activity.MODE_PRIVATE);
        String imageString = mySharedPreferences.getString(key, def);
        byte[] byteArray = Base64.decode(imageString, Base64.DEFAULT);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                byteArray);
        Bitmap bitmap = BitmapFactory.decodeStream(byteArrayInputStream);
        return bitmap;
    }
    public static void saveObject(String key, Object obj) {
        SharedPreferences mySharedPreferences = MyApp.getContext().getSharedPreferences(
                TAG, Context.MODE_APPEND);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos=null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String newWord = new String(Base64.encodeToString(baos.toByteArray(),Base64.DEFAULT));
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        // 将编码后的字符串写到base64.xml文件中
        editor.putString(key, newWord);
        editor.commit();
    }

    public static Object getObj(String key) {
        SharedPreferences mySharedPreferences = MyApp.getContext().getSharedPreferences(
                TAG, Activity.MODE_PRIVATE);
        String imageString = mySharedPreferences.getString(key, "");
        if(imageString.length()==0){
            return null;
        }
        byte[] byteArray = Base64.decode(imageString, Base64.DEFAULT);
        ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
        try {
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
