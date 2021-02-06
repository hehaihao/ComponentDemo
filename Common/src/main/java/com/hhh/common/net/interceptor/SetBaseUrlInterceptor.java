package com.hhh.common.net.interceptor;


import com.hhh.common.utils.ConstantValue;
import com.hhh.common.utils.SharePreferenceUtil;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Description:切换节点拦截器
 * @Author: hhh
 * @CreateDate: 2020/10/12 13:38
 */
public class SetBaseUrlInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取原始的originalRequest
        Request originalRequest = chain.request();
        //获取老的url
        HttpUrl oldUrl = originalRequest.url();
        //获取originalRequest的创建者builder
        Request.Builder builder = originalRequest.newBuilder();

        //设置当前节点
        String currNode = SharePreferenceUtil.getString(ConstantValue.CURRENT_NODE);
        HttpUrl baseURL = HttpUrl.parse(currNode);

        //重建新的HttpUrl，需要重新设置的url部分
        HttpUrl newHttpUrl = oldUrl.newBuilder()
                .scheme(baseURL.scheme())//http协议如：http或者https
                .host(baseURL.host())//主机地址
                .port(baseURL.port())//端口
                .build();
        //获取处理后的新newRequest
        Request newRequest = builder.url(newHttpUrl).build();
        return  chain.proceed(newRequest);
    }
}
