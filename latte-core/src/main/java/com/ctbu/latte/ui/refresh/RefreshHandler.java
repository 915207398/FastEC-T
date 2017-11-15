package com.ctbu.latte.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ctbu.latte.app.Latte;
import com.ctbu.latte.net.RestClient;
import com.ctbu.latte.net.callback.ISuccess;

/**
 * Created by chenting on 2017/11/15.
 */

public class RefreshHandler implements
        SwipeRefreshLayout.OnRefreshListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;
//    private final PagingBean BEAN;

    public RefreshHandler(SwipeRefreshLayout refresh_layout) {
        REFRESH_LAYOUT = refresh_layout;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行一些网络请求
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 1000);
    }

    public void firstPage(String url) {
//        BEAN.setDelayed(1000);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(Latte.getApplicationContext(),response,Toast.LENGTH_LONG).show();
//                        final JSONObject object = JSON.parseObject(response);
//                        BEAN.setTotal(object.getInteger("total"))
//                                .setPageSize(object.getInteger("page_size"));
//                        //设置Adapter
//                        mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response));
//                        mAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLERVIEW);
//                        RECYCLERVIEW.setAdapter(mAdapter);
//                        BEAN.addIndex();
                    }
                })
                .build()
                .get();
    }
    @Override
    public void onRefresh() {
        refresh();
    }
}
