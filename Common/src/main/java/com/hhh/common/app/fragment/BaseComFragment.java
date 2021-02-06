package com.hhh.common.app.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhh.common.listener.PermissionListener;
import com.hhh.common.utils.ActivityUtil;
import com.hhh.common.utils.LogUtil;
import com.hhh.common.widget.svprogress.SVProgressHUD;
import com.tbruyelle.rxpermissions2.RxPermissions;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Description:基类BaseFragment
 * @Author: hhh
 * @CreateDate: 2020/9/15
 */
public abstract class BaseComFragment extends Fragment implements View.OnClickListener {

    protected SVProgressHUD svProgressHUD;
    protected Context mContext;

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected View view = null;
    protected Unbinder mUnbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        svProgressHUD = new SVProgressHUD(getActivity());
        mUnbinder = ButterKnife.bind(this,view);
        //得到context,在后面的子类Fragment中都可以直接调用
        mContext = ActivityUtil.getCurrentActivity();
        initView();
        initData();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        initListener();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //do something
    }

    private void initListener() {
    }

    /**
     * 显示加载框
     * @param showMessage
     */
    protected void show(String showMessage){
        if(!svProgressHUD.isShowing()){
            svProgressHUD.showWithStatus(showMessage);
        }
    }

    /**
     * 关闭加载框
     */
    protected void dismiss(){
        if(svProgressHUD.isShowing()){
            svProgressHUD.dismiss();
        }
    }

    public final <E extends View> E getView(int id) {
        try {
            return (E) view.findViewById(id);
        } catch (ClassCastException ex) {
            throw ex;
        }
    }

    public final void addOnClickListeners(@IdRes int... ids) {
        try {
            for (@IdRes int id : ids)
                getView(id).setOnClickListener(this);
        } catch (ClassCastException ex) {
            throw ex;
        }
    }

    /**
     * 申请单个权限
     * @param activity
     */
    @SuppressLint("CheckResult")
    public void checkPermissionRequest(FragmentActivity activity, String permission, PermissionListener listener) {
        RxPermissions permissions = new RxPermissions(activity);
        permissions.setLogging(true);
        permissions.request(permission)
                .subscribe(aBoolean -> {
                    if(listener != null)listener.permissionResult(aBoolean);
                    LogUtil.i("获取单个权限--:" + aBoolean);
                });
    }
}
