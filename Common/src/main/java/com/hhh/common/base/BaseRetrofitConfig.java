package com.hhh.common.base;

/**
 * @Description:retrofit 配置
 * @Author: hhh
 * @CreateDate: 2020/9/15
 */
public class BaseRetrofitConfig {
    private static String baseUrl;

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static void setBaseUrl(String baseUrl) {
        BaseRetrofitConfig.baseUrl = baseUrl;
    }

    private static String traceBaseUrl;

    public static String getTraceBaseUrl() {
        return traceBaseUrl;
    }

    public static void setTraceBaseUrl(String traceBaseUrl) {
        BaseRetrofitConfig.traceBaseUrl = traceBaseUrl;
    }

}
