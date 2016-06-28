package com.allyn.lives.holder;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.allyn.lives.R;
import com.allyn.lives.activity.books.BooksDetailsActivity;
import com.allyn.lives.bean.BooksBean;
import com.allyn.lives.netwoarks.IPConfig;
import com.allyn.lives.utils.Config;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2016/6/21.
 */
public class BooksHolder extends BaseViewHolder<BooksBean.ListEntity> implements View.OnClickListener {


    ImageView mivClassify;
    TextView mtvTitle;
    TextView mtvContent;
    CardView card_action;

    int id;
    String title;

    public BooksHolder(ViewGroup viewParent) {
        super(viewParent, R.layout.item_books_classify_content);
        mivClassify = $(R.id.ivClassify);
        mtvTitle = $(R.id.tvTitle);
        mtvContent = $(R.id.tvContent);
        card_action = $(R.id.card_action);
    }

    @Override
    public void setData(BooksBean.ListEntity data) {
        super.setData(data);
        card_action.setOnClickListener(this);
        id = data.getId();
        title = data.getName();
        com.bumptech.glide.Glide.with(getContext())
                .load(IPConfig.ImageUrl + data.getImg())
                .crossFade()
                .placeholder(R.mipmap.ic_placeholder)
                .into(mivClassify);
        mtvTitle.setText(title);
        mtvContent.setText(data.getSummary());
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getContext(), BooksDetailsActivity.class);
        intent.putExtra(Config.DetailId, id);
        intent.putExtra(Config.Title, title);
        getContext().startActivity(intent);
    }
}
