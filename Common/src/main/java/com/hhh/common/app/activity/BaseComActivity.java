package com.hhh.common.app.activity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hhh.common.R2;
import com.hhh.common.listener.PermissionListener;
import com.hhh.common.utils.ActivityUtil;
import com.hhh.common.utils.LogUtil;
import com.hhh.common.utils.SharePreferenceUtil;
import com.hhh.common.widget.svprogress.SVProgressHUD;
import com.tbruyelle.rxpermissions2.RxPermissions;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Description:
 * @Author: hhh
 * @CreateDate: 2020/9/27 13:57
 */
public abstract class BaseComActivity extends AppCompatActivity implements View.OnClickListener{
    protected SVProgressHUD svProgressHUD;
    protected FragmentManager mFragmentManager;
    @BindView(R2.id.base_topBar_tv_top_margin)
    TextView topBarTvTopMargin;
    @BindView(R2.id.base_topBar_lin_main)
    LinearLayout topBarLinMain;
    @BindView(R2.id.base_topBar_iv_back)
    ImageView topBarIvBack;
    @BindView(R2.id.base_topBar_tv_title)
    TextView topBarTvTitle;
    @BindView(R2.id.base_topBar_tv_right)
    TextView topBarTvRight;
    @BindView(R2.id.base_topBar_iv_right)
    ImageView topBarIvRight;

    protected Unbinder mUnbinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getSupportFragmentManager();
        //设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        svProgressHUD = new SVProgressHUD(this);
        initTopBar();
        initView();
        initData();
    }

    /**
     * 初始化自定义topBar
     */
    private void initTopBar() {

        if (topBarIvBack != null) {
            topBarIvBack.setOnClickListener(view -> ActivityUtil.finishActivity(ActivityUtil.getCurrentActivity()));
        }
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected boolean isLogin(String userIdKey){
        if(TextUtils.isEmpty(userIdKey)) return false;
        String userId = SharePreferenceUtil.getString(userIdKey);
        if(TextUtils.isEmpty(userId)) return false;
        else return true;
    }

    /**
     * 判断是否登录，如果未登录则跳转指定界面
     * @param userIdKey
     * @param cls
     * @return
     */
    protected boolean isLoginAndJumpLogin(String userIdKey, Class<?> cls){
        if(TextUtils.isEmpty(userIdKey)) return false;
        String userId = SharePreferenceUtil.getString(userIdKey);
        if(TextUtils.isEmpty(userId)) {
            ActivityUtil.startActivity(cls,false);
            return false;
        }else return true;
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

    /**
     * 关闭加载框
     */
    protected void dismissOnMainThread(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(svProgressHUD.isShowing()){
                    svProgressHUD.dismiss();
                }
            }
        });
    }

    public final <E extends View> E getView(int id) {
        try {
            return (E) findViewById(id);
        } catch (ClassCastException ex) {
            throw ex;
        }
    }

    protected void addOnClickListeners(@IdRes int... ids) {
        try {
            if (ids != null) {
                for (@IdRes int id : ids) {
                    getView(id).setOnClickListener(this);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {}

    /***
     * 申请多个权限
     */
    @SuppressLint("CheckResult")
    public void checkPermissionsRequest(FragmentActivity activity, PermissionListener listener, String... permission) {
        RxPermissions permissions = new RxPermissions(activity);
        permissions.setLogging(true);
        permissions.request(permission)
                .subscribe(aBoolean -> {
                    if(listener != null)listener.permissionResult(aBoolean);
                    LogUtil.i("获取多个权限--:" + aBoolean);
                });
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

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.i("ActivityStack==" + ActivityUtil.getActivityStack().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }
}
