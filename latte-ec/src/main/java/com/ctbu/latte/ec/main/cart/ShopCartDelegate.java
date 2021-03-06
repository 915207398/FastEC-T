package com.ctbu.latte.ec.main.cart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ctbu.latte.delegates.bottom.BottomItemDelegate;
import com.ctbu.latte.ec.R;
import com.ctbu.latte.ec.R2;
import com.ctbu.latte.ec.pay.FastPay;
import com.ctbu.latte.ec.pay.IAlPayResultListener;
import com.ctbu.latte.net.RestClient;
import com.ctbu.latte.net.callback.ISuccess;
import com.ctbu.latte.ui.recycler.MultipleItemEntity;
import com.ctbu.latte.util.log.LatteLogger;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import java.util.function.ToDoubleBiFunction;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by chenting on 2017/11/18.
 */

public class ShopCartDelegate extends BottomItemDelegate implements ISuccess, ICartItemListener, IAlPayResultListener {

    private ShopCartAdapter mAdapter = null;
    //购物车数量标记
    private int mCurrentCount = 0;
    private int mTotalCount = 0;
    private double mTotalPrice = 0.00;

    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView mIconSelectAll = null;
    @BindView(R2.id.stub_no_item)
    ViewStubCompat mStubNoItem = null;
    @BindView(R2.id.tv_shop_cart_total_price)
    AppCompatTextView mTvTotalPrice = null;

    @OnClick(R2.id.icon_shop_cart_select_all)
    void onClickSelectAll() {
        final int tag = (int) mIconSelectAll.getTag();
        if (tag == 0) {
            mIconSelectAll.setTextColor
                    (ContextCompat.getColor(getContext(), R.color.app_main));
            mIconSelectAll.setTag(1);
            mAdapter.setIsSelectedAll(true);
            //更新RecyclerView的显示状态
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        } else {
            mIconSelectAll.setTextColor(Color.GRAY);
            mIconSelectAll.setTag(0);
            mAdapter.setIsSelectedAll(false);
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        }
    }

    //TODO 删除存在多选删除只能删除一个，且价格没有改变bug
    @OnClick(R2.id.tv_top_shop_cart_remove_selected)
    void onClickRemoveSelectedItem() {
        final List<MultipleItemEntity> data = mAdapter.getData();
        //要删除的数据
        final List<MultipleItemEntity> deleteEntities = new ArrayList<>();
        for (MultipleItemEntity entity : data) {
            final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
            if (isSelected) {
                deleteEntities.add(entity);
            }
        }
        for (int i=0;i<deleteEntities.size();i++){
            int DataCount =data.size();
            int currentPosition =deleteEntities.get(i).getField(ShopCartItemFields.POSITION);
            if(currentPosition<data.size()){
                mAdapter.remove(currentPosition);
                for(;currentPosition<DataCount-1;currentPosition++){
                    int rawItemPos =data.get(currentPosition).getField(ShopCartItemFields.POSITION);
                    data.get(currentPosition).setField(ShopCartItemFields.POSITION,rawItemPos-1);
                }
            }
        }
        checkItemCount();

    }

    @OnClick(R2.id.tv_top_shop_cart_clear)
    void onClickClear() {
        if (mAdapter.getItemCount() > 0) {
            mAdapter.getData().clear();
            mAdapter.setTotalPrice(0.00);
            onItemClick(0.00);
            mAdapter.notifyDataSetChanged();
            checkItemCount();
        }
    }

    @OnClick(R2.id.tv_shop_cart_pay)
    void onClickPay() {
        createOrder();
    }

    //创建订单，注意，和支付是没有关系的
    private void createOrder() {
        // FIXME: 2017/11/20 没填订单PAI
        final String orderUrl = "http://192.168.0.104:8080/RestDataServer/data/order_list.json";
        final WeakHashMap<String, Object> orderParams = new WeakHashMap<>();
        //加入你的参数
        RestClient.builder()
                .url(orderUrl)





                .loader(getContext())
                .params(orderParams)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //进行具体的支付
                        LatteLogger.d("ORDER", response);
                        final JSONObject data = JSON.parseObject(response).getJSONObject("data");
                        int orderId=data.getInteger("id");
                        FastPay.create(ShopCartDelegate.this)
                                .setPayResultListener(ShopCartDelegate.this)
                                .setOrderId(orderId)
                                .beginPayDialog();
                    }
                })
                .build()
                .post();

    }

    @SuppressWarnings("RestrictedApi")
    private void checkItemCount() {
        final int count = mAdapter.getItemCount();
        if (count == 0) {
            final View stubView = mStubNoItem.inflate();
            final AppCompatTextView tvToBuy =
                    (AppCompatTextView) stubView.findViewById(R.id.tv_stub_to_buy);
            tvToBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "你该购物啦！", Toast.LENGTH_SHORT).show();
                }
            });
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mIconSelectAll.setTag(0);

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("data/shop_cart_data.json")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        final ArrayList<MultipleItemEntity> data =
                new ShopCartDataConverter()
                        .setJsonData(response)
                        .convert();
        mAdapter = new ShopCartAdapter(data);
        mAdapter.setCartItemListener(this);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        mTotalPrice = mAdapter.getTotalPrice();
        mTvTotalPrice.setText(String.valueOf(mTotalPrice));
        checkItemCount();
    }

    @Override
    public void onItemClick(double itemTotalPrice) {
        final double price = mAdapter.getTotalPrice();
        mTvTotalPrice.setText(String.valueOf(price));
    }

    @Override
    public void onPaySuccess() {

    }

    @Override
    public void onPaying() {

    }

    @Override
    public void onPayFail() {

    }

    @Override
    public void onPayCancel() {

    }

    @Override
    public void onPayConnectError() {

    }
}
