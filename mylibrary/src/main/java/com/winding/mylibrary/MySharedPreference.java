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

public class MySharedPreference {

    static final String TAG = "KIWI";

    public static void save(String name, String value, Context context) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences(
                TAG, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(name, value);
        editor.commit();
    }

    public static void saveSet(String name, Set<String> set, Context context) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences(
                TAG, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putStringSet(name, set);
        editor.commit();
    }

    public static void remove(String name, Context context) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences(
                TAG, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.remove(name);
        editor.commit();
    }

    public static void saveObject(String key, Object obj,
                                                     Context context) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences(
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

    public static Object getObj(String key, Context context) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences(
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
        // 从ObjectInputStream中读取Product对象
    }

    public static void saveBitmapToSharedPreferences(String key, Bitmap bitmap,
                                                     Context context) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences(
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

    public static Bitmap getBitmapFromSharedPreferences(String key, String def,
                                                        Context context) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences(
                TAG, Activity.MODE_PRIVATE);
        String imageString = mySharedPreferences.getString(key, def);
        byte[] byteArray = Base64.decode(imageString, Base64.DEFAULT);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                byteArray);
        Bitmap bitmap = BitmapFactory.decodeStream(byteArrayInputStream);
        return bitmap;
    }

    public static String get(String name, String defvalue, Context context) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences(
                TAG, Activity.MODE_PRIVATE);
        return mySharedPreferences.getString(name, defvalue);
    }
    public static Set<String> getSet(String name, Context context) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences(
                TAG, Activity.MODE_PRIVATE);
        return mySharedPreferences.getStringSet(name, new HashSet<String>());
    }

    public static void clear(Context context) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences(
                TAG, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}