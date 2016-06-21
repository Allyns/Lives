package com.allyn.lives.holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.allyn.lives.R;
import com.allyn.lives.model.bean.ImageClassifyBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/6/21.
 */
public class ImageClassifyTipHolder extends BaseViewHolder<ImageClassifyBean.TngouEntity> implements View.OnClickListener {


    @Bind(R.id.tvClassifyName)
    TextView mClassityName;
    @Bind(R.id.btnClassifyMore)
    Button mBtnClassifyMore;

    public ImageClassifyTipHolder(ViewGroup viewGroup) {
        super(viewGroup, R.layout.item_image_classify_tip);
        ButterKnife.bind(viewGroup, itemView);
    }

    @Override
    public void setData(ImageClassifyBean.TngouEntity data) {
        super.setData(data);
        mClassityName.setText(data.getName());
        mBtnClassifyMore.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //startactivity
    }
}
