package com.hhh.common.utils;

import android.app.Application;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * 加载弹窗管理
 */
public class ShowUtil {
    private static Application mApplicationContext;

    public static void initialize(Application application){
        mApplicationContext = application;
    }

    public static Application getApplication(){
        return mApplicationContext;
    }

    public static void closeSoftKeyboard() {
        InputMethodManager inputManger = (InputMethodManager) ActivityUtil.getCurrentActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManger != null) {
            inputManger.hideSoftInputFromWindow(ActivityUtil.getCurrentActivity().getWindow().getDecorView().getWindowToken(), 0);
        }
    }
}
