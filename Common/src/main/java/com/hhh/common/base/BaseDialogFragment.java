package com.hhh.common.base;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * @Description:
 * @Author: hhh
 * @CreateDate: 2020/9/23 9:23
 */
public class BaseDialogFragment extends DialogFragment {

    @Override
    public void show(FragmentManager manager, String tag) {
//        super.show(manager, tag);
        try{
            Class c = Class.forName("androidx.fragment.app.DialogFragment");
            Constructor con = c.getConstructor();
            Object obj = con.newInstance();
            Field dismissed = c.getDeclaredField("mDismissed");
            dismissed.setAccessible(true);
            dismissed.set(obj, false);
            Field shownByMe = c.getDeclaredField("mShownByMe");
            shownByMe.setAccessible(true);
            shownByMe.set(obj,false);
        }catch (Exception e){
            e.printStackTrace();
        }
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this,tag);
        ft.commitAllowingStateLoss();
    }

    @Override
    public void dismiss() {
//        super.dismiss();
        dismissAllowingStateLoss();
    }
}
