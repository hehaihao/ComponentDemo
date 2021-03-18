package com.hhh.export;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hhh.export.bean.CartInfo;
import com.hhh.export.router.CartRouterTable;
import com.hhh.export.service.ICartService;

/**
 * 购物车组件服务工具类
 * 其他组件直接使用此类即可：页面跳转、获取服务。
 * @Author: hhh
 * @CreateDate: 2021/1/29 11:33
 */
public class CartServiceUtil {
    /**
     * 跳转到购物车页面
     * @param param1
     * @param param2
     */
    public static void navigateCartPage(String param1, String param2){
        ARouter.getInstance()
                .build(CartRouterTable.PATH_PAGE_CART)
                .withString("key1",param1)
                .withString("key2",param2)
                .navigation();
    }

    /**
     * 获取服务
     * @return
     */
    public static ICartService getService(){
        //return ARouter.getInstance().navigation(ICartService.class);//如果只有一个实现，这种方式也可以
        return (ICartService) ARouter.getInstance().build(CartRouterTable.PATH_SERVICE_CART).navigation();
    }

    /**
     * 获取购物车中商品数量
     * @return
     */
    public static CartInfo getCartProductCount(){
        return getService().getProductCountInCart();
    }
}
