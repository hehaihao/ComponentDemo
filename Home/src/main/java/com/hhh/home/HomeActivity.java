package com.hhh.home;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hhh.common.app.activity.BaseComActivity;
import com.hhh.export.CartServiceUtil;
import com.hhh.export.router.CartRouterTable;

@Route(path = "/homepage/homeActivity")
public class HomeActivity extends BaseComActivity {
    @BindView(R2.id.btn_go_cart)
    Button btn_go_cart;
    @BindView(R2.id.tv_cart_product_count)
    TextView tvCartProductCount;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        //跳转到购物车页面
        btn_go_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过路由跳转到 购物车组件的购物车页面（但没有依赖购物车组件）
                CartServiceUtil.navigateCartPage("param1", "param2");
            }
        });
        //调用购物车组件服务：获取购物车商品数量
        tvCartProductCount.setText("购物车商品数量:"+ CartServiceUtil.getCartProductCount().productCount);


        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction= manager.beginTransaction();

        Fragment userFragment = (Fragment) ARouter.getInstance().build(CartRouterTable.PATH_FRAGMENT_CART).navigation();
        transaction.add(R.id.fl_test_fragment, userFragment, "tag");
        transaction.commit();
    }

    @Override
    protected void initData() {

    }
}