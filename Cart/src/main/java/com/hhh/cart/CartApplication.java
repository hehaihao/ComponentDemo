package com.hhh.cart;

import android.content.Context;
import android.util.Log;
import com.hhh.common.app.BaseApplication;

/**
 * @Description:
 * @Author: hhh
 * @CreateDate: 2021/1/29 11:23
 */
public class CartApplication extends BaseApplication {
    public Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = context;
        Log.i("CartApplication", "onCreate");
    }
}
