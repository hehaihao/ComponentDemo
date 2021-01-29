package com.hhh.export_cart.router;

/**
 * @Description:
 * 购物车组件路由表
 * 即 购物车组件中 所有可以从外部跳转的页面 的路由信息
 * @Author: hhh
 * @CreateDate: 2021/1/29 11:34
 */
public interface CartRouterTable {
    /**
     * 购物车页面
     */
    String PATH_PAGE_CART = "/cart/cartActivity";

    /**
     * 购物车fragment
     */
    String PATH_FRAGMENT_CART = "/cart/cartFragment";

    /**
     * 购物车服务
     */
    String PATH_SERVICE_CART = "/cart/service";
}
