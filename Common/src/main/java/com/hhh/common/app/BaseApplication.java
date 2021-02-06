package com.hhh.common.app;

import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hhh.common.BuildConfig;
import com.hhh.common.base.BaseRetrofitConfig;
import com.hhh.common.utils.ActivityUtil;
import com.hhh.common.utils.LogUtil;
import com.hhh.common.utils.ShowUtil;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

/**
 * @Description: 基类application
 * @Author: hhh
 * @CreateDate: 2020/9/15
 */
public abstract class BaseApplication extends MultiDexApplication {

    private static Context mContext;//上下文
    private String baseUrl = "";
    private String traceBaseUrl = "";

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (BuildConfig.DEBUG) {
            // 打印日志
            ARouter.openLog();
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
        }
        // 尽可能早，推荐在Application中初始化
        ARouter.init(this);
        MultiDex.install(this);
        initConfig();
    }

    public static Context getAppContext() {
        return mContext;
    }

    /**
     * 初始化 application
     */
    public void initConfig() {
        //初始化
        ShowUtil.initialize(this);
        //设置打印开关
        LogUtil.setIsLog(BuildConfig.DEBUG);
        //注册Activity生命周期
        registerActivityLifecycleCallbacks(ActivityUtil.getMyActivityLifeCycleCallBack());
        baseUrl = initBaseUrl();
        traceBaseUrl = initTraceBaseUrl();
        BaseRetrofitConfig.setBaseUrl(baseUrl);
        BaseRetrofitConfig.setTraceBaseUrl(traceBaseUrl);
    }

    public String initBaseUrl() {
        return BuildConfig.HTTP_BASE_URL;
    }

    public String initTraceBaseUrl() {
        return BuildConfig.HTTP_TRACE_BASE_URL;
    }
}
