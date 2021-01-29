package com.hhh.cart;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hhh.export_cart.bean.CartInfo;
import com.hhh.export_cart.router.CartRouterTable;
import com.hhh.export_cart.service.ICartService;

/**
 * 购物车组件服务的实现
 * @author hehaihao
 */
@Route(path = CartRouterTable.PATH_SERVICE_CART)
public class CartServiceImpl implements ICartService {

    @Override
    public CartInfo getProductCountInCart() {
        CartInfo cartInfo = new CartInfo();
        cartInfo.productCount = 666;
        return cartInfo;
    }

    @Override
    public void init(Context context) {
        //初始化工作，服务注入时会调用
    }
}
