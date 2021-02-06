package com.hhh.cart;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hhh.common.app.activity.BaseComActivity;
import com.hhh.export_cart.router.CartRouterTable;

@Route(path = CartRouterTable.PATH_PAGE_CART)
public class CartActivity extends BaseComActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cart;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}