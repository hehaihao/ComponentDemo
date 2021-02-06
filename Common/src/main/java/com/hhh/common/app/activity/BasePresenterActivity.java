package com.hhh.common.app.activity;

import android.os.Bundle;
import com.hhh.common.base.BasePresenter;
import com.hhh.common.base.BaseView;
import com.hhh.common.utils.ActivityUtil;
import androidx.annotation.Nullable;

/**
 * @Description: 基类BaseActivity
 * @Author: hhh
 * @CreateDate: 2020/9/15
 */
public abstract class BasePresenterActivity<P extends BasePresenter> extends BaseComActivity implements BaseView {

    protected P presenter;

    protected abstract P createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁时，解除绑定
        if (presenter != null) {
            presenter.detachView();
        }
    }

    protected void initListener() {
    }

    @Override
    public void showLoading(String s) {
        show(s);
    }

    @Override
    public void hideLoading() {
        dismiss();
    }

    /**
     * token过期重新登录
     */
    @Override
    public void reLogin() {

    }
}
