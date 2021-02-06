package com.hhh.common.net;

import android.util.Log;


import com.hhh.common.base.BaseRetrofitConfig;
import com.hhh.common.net.interceptor.ParamInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @Description:retrofit管理
 * @Author: hhh
 * @CreateDate: 2020/9/15
 */
public class RetrofitManager {
    private volatile static RetrofitManager apiRetrofit;
    private Retrofit mRetrofit;
    private Retrofit mTraceRetrofit;

    private static final int NORMAL = 1;//普通地址
    private static final int TRACE = 2;//溯源端地址

    /**
     * 获取retrofit
     * @return RetrofitService
     */
    public static RetrofitManager getInstance() {
        if (apiRetrofit == null) {
            synchronized (Object.class) {
                if (apiRetrofit == null) {
                    apiRetrofit = new RetrofitManager();
                }
            }
        }
        return apiRetrofit;
    }

    /**
     * 初始化retrofit
     */
    private RetrofitManager() {
        //配置okHttp并设置时间、日志信息和cookies
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message -> {
            //打印信息
            Log.i("http",message);
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new ParamInterceptor())
                .addInterceptor(httpLoggingInterceptor)
                //设置超时时间
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
        //普通接口关联okHttp并加上rxJava和Gson的配置和baseUrl
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BaseRetrofitConfig.getBaseUrl())
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(BaseConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        //溯源接口关联okHttp并加上rxJava和Gson的配置和baseUrl
        mTraceRetrofit = new Retrofit.Builder()
                .baseUrl(BaseRetrofitConfig.getTraceBaseUrl())
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(BaseConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * 利用泛型传入接口class返回接口实例
     *
     * @param ser 类
     * @param <T> 类的类型
     * @return Observable
     */
    public <T> T createRs(Class<T> ser) {
        return mRetrofit.create(ser);
    }

    public <T> T createTraceRs(Class<T> ser) {
        return mTraceRetrofit.create(ser);
    }
}
