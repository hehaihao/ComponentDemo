package com.hhh.common.utils;

import android.annotation.SuppressLint;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Toast统一工具类
 */
public class ToastUtil {
    private static Toast toast;

    public ToastUtil() {
    }

    public static void showToast(final String msg) {
        if ("main".equals(Thread.currentThread().getName())) {
            createToast(msg);
        } else {
            ActivityUtil.getCurrentActivity().runOnUiThread(() -> ToastUtil.createToast(msg));
        }

    }

    @SuppressLint("ShowToast")
    private static void createToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(ShowUtil.getApplication(), msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        LinearLayout linearLayout = (LinearLayout) toast.getView();
        TextView messageTextView = (TextView) linearLayout.getChildAt(0);
        messageTextView.setTextSize(15.0F);
        toast.show();
    }

    /**
     * 在中心显示 Toast
     *
     * @param msg 信息
     */
    public static void showCenterToast(final String msg) {
        if ("main".equals(Thread.currentThread().getName())) {
            createCenterToast(msg);
        } else {
            ActivityUtil.getCurrentActivity().runOnUiThread(() -> ToastUtil.createCenterToast(msg));
        }
    }

    @SuppressLint("ShowToast")
    private static void createCenterToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(ShowUtil.getApplication(), msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }

        LinearLayout linearLayout = (LinearLayout) toast.getView();
        TextView messageTextView = (TextView) linearLayout.getChildAt(0);
        toast.setGravity(17, 0, 0);
        messageTextView.setTextSize(15.0F);
        toast.show();
    }

    public static void cancelToast() {
        if (toast != null) {
            toast.cancel();
        }
    }
}
