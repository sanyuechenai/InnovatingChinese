package com.yueworld.baseproject.utils;

import android.content.Context;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vincent Chen on 2018/6/6.
 */
public class StringUtils {

    public static String getString(Context context, int id){
        return context.getResources().getString(id);
    }

    public static boolean isEmpty(Object str){
        if(str == null){
            return true;
        }
        if(str instanceof String){
            return ((String)str).length() == 0;
        }
        return false;
    }

    //判断字符串是不是数字
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0;) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    /**
     *
     * 判断一个集合中是否含有对象
     * @param key  匹配的对象
     * @param list 集合对象
     * @return
     */
    public static boolean isListContainKey(String key, List<String> list){
        for (int i = 0; i <list.size(); i++) {
            if(key.equals(list.get(i))){
                return true;
            }
        }
        return false;
    }

    /**
     * 邮箱格式的判定
     * @param strEmail
     * @return
     */
    public static boolean isEmail(String strEmail) {
        String str = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }
}
