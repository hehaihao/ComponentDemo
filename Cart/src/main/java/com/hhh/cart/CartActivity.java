package com.hhh.cart;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hhh.common.app.activity.BaseComActivity;
import com.hhh.common.utils.LogUtil;
import com.hhh.export.router.CartRouterTable;

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
        Bundle args = getIntent().getExtras();
        if(args != null){
            String key1 = args.getString("key1");
            String key2 = args.getString("key2");
            LogUtil.d(key1+","+key2);
        }
    }
}