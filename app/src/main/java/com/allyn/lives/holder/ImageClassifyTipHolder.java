package com.allyn.lives.holder;

import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.allyn.lives.R;
import com.allyn.lives.model.bean.ImageClassifyBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/6/21.
 */
public class ImageClassifyTipHolder extends BaseViewHolder<ImageClassifyBean.TngouEntity> {

    TextView mClassityName;
    CardView card_action;


    public ImageClassifyTipHolder(ViewGroup viewGroup) {
        super(viewGroup, R.layout.item_image_classify_tip);
        mClassityName = $(R.id.tvClassifyName);
        card_action = $(R.id.card_action);
    }

    @Override
    public void setData(ImageClassifyBean.TngouEntity data) {
        super.setData(data);
        mClassityName.setText(data.getName());
    }
}
