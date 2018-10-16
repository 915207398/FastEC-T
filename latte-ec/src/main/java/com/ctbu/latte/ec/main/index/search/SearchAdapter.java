package com.ctbu.latte.ec.main.index.search;

import android.support.v7.widget.AppCompatTextView;

import com.ctbu.latte.ec.R;
import com.ctbu.latte.ui.recycler.MultipleFields;
import com.ctbu.latte.ui.recycler.MultipleItemEntity;
import com.ctbu.latte.ui.recycler.MultipleRecyclerAdapter;
import com.ctbu.latte.ui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * Created by CaiPengFei on 2017/11/25.
 */

public class SearchAdapter extends MultipleRecyclerAdapter {

    protected SearchAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(SearchItemType.ITEM_SEARCH, R.layout.item_search);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (entity.getItemType()) {
            case SearchItemType.ITEM_SEARCH:
                final AppCompatTextView tvSearchItem = holder.getView(R.id.tv_search_item);
                final String history = entity.getField(MultipleFields.TEXT);
                tvSearchItem.setText(history);
                break;
            default:
                break;
        }
    }
}
