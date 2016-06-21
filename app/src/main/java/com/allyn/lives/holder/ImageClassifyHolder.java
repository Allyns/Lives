package com.allyn.lives.holder;

import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;

import com.allyn.lives.R;
import com.allyn.lives.model.bean.ImageBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/6/21.
 */
public class ImageClassifyHolder extends BaseViewHolder<ImageBean.ListEntity> {


    @Bind(R.id.ivClassify)
    ImageView mivClassify;
    @Bind(R.id.tvTitle)
    TextView mtvTitle;
    @Bind(R.id.tvContent)
    TextView mtvContent;

    public ImageClassifyHolder(ViewGroup viewParent) {
        super(viewParent, R.layout.item_image_classify_content);
        ButterKnife.bind(viewParent, itemView);
    }

    @Override
    public void setData(ImageBean.ListEntity data) {
        super.setData(data);
        com.bumptech.glide.Glide.with(getContext())
                .load("http://tnfs.tngou.net/image" + data.getImg())
                .crossFade()
                .placeholder(R.mipmap.ic_placeholder)
                .into(mivClassify);
        mtvTitle.setText(data.getName());
        mtvContent.setText(data.getSummary());
    }

}
