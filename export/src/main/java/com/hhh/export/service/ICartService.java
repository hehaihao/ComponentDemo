package com.hhh.export.service;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.hhh.export.bean.CartInfo;

/**
 * @Description: 购物车组件对外暴露的服务
 * @Author: hhh
 * @CreateDate: 2021/1/29 11:34
 */
public interface ICartService extends IProvider {
    /**
     * 获取购物车中商品数量
     * @return
     */
    CartInfo getProductCountInCart();
}
