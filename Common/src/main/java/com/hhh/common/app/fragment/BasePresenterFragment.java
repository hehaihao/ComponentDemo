package com.hhh.common.app.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hhh.common.base.BasePresenter;
import com.hhh.common.base.BaseView;
import com.hhh.common.utils.ActivityUtil;
import com.hhh.common.widget.svprogress.SVProgressHUD;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

/**
 * @Description:基类BaseFragment
 * @Author: hhh
 * @CreateDate: 2020/9/15
 */
public abstract class BasePresenterFragment<P extends BasePresenter> extends BaseComFragment implements BaseView, View.OnClickListener {

    protected SVProgressHUD svProgressHUD;
    protected Context mContext;
    protected View view;
    protected FragmentManager mChildFragmentManager;

    protected P presenter;

    protected abstract P createPresenter();

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChildFragmentManager = getChildFragmentManager();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        svProgressHUD = new SVProgressHUD(getActivity());
        //得到context,在后面的子类Fragment中都可以直接调用
        mContext = ActivityUtil.getCurrentActivity();
        presenter = createPresenter();
        initView();
        initData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initListener();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //do something
        //销毁时，解除绑定
        if (presenter != null) {
            presenter.detachView();
        }
    }

    private void initListener() {
    }

    /**
     * 显示加载中
     */
    @Override
    public void showLoading(String s) {
        show(s);
    }

    /**
     * 隐藏加载中
     */
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

}
