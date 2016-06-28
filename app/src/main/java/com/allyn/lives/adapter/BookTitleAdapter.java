package com.allyn.lives.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.allyn.lives.bean.BooksDetailBean;
import com.allyn.lives.holder.BooksDetailHolder;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 */
public class BookTitleAdapter extends RecyclerArrayAdapter<BooksDetailBean.ListEntity>{

    public BookTitleAdapter(Context context){
        super(context);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new BooksDetailHolder(parent);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public int getViewType(int position) {
        return super.getViewType(position);
    }

}

