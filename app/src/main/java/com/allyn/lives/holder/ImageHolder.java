package com.allyn.lives.holder;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.allyn.lives.R;
import com.allyn.lives.activity.DetailsActivity;
import com.allyn.lives.bean.ImageBean;
import com.allyn.lives.netwoarks.IPConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2016/6/21.
 */
public class ImageHolder extends BaseViewHolder<ImageBean.ListEntity> implements View.OnClickListener {


    ImageView mivClassify;
    TextView mtvTitle;
    TextView mtvContent;
    CardView card_action;

    int id;

    public ImageHolder(ViewGroup viewParent) {
        super(viewParent, R.layout.item_image_classify_content);
        mivClassify = $(R.id.ivClassify);
        mtvTitle = $(R.id.tvTitle);
        mtvContent = $(R.id.tvContent);
        card_action = $(R.id.card_action);
        card_action.setOnClickListener(this);
    }

    @Override
    public void setData(ImageBean.ListEntity data) {
        super.setData(data);
        id = data.getId();
        com.bumptech.glide.Glide.with(getContext())
                .load(IPConfig.ImageUrl + data.getImg())
                .crossFade()
                .placeholder(R.mipmap.ic_placeholder)
                .into(mivClassify);
        mtvTitle.setText(data.getName());
        mtvContent.setText(data.getSummary());
    }

    @Override
    public void onClick(View view) {
        getContext().startActivity(new Intent(getContext(), DetailsActivity.class));
    }
}
