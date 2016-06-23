package com.allyn.lives.holder;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.allyn.lives.R;
import com.allyn.lives.bean.ImageBean;
import com.allyn.lives.netwoarks.IPConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2016/6/21.
 */
public class ImageClassifyHolder extends BaseViewHolder<ImageBean.ListEntity> {


    ImageView mivClassify;
    TextView mtvTitle;
    TextView mtvContent;

    public ImageClassifyHolder(ViewGroup viewParent) {
        super(viewParent, R.layout.item_image_classify_content);
        mivClassify = $(R.id.ivClassify);
        mtvTitle = $(R.id.tvTitle);
        mtvContent = $(R.id.tvContent);
    }

    @Override
    public void setData(ImageBean.ListEntity data) {
        super.setData(data);
        com.bumptech.glide.Glide.with(getContext())
                .load(IPConfig.ImageUrl + data.getImg())
                .crossFade()
                .placeholder(R.mipmap.ic_placeholder)
                .into(mivClassify);
        mtvTitle.setText(data.getName());
        mtvContent.setText(data.getSummary());
    }

}
