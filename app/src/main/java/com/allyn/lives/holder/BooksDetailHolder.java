package com.allyn.lives.holder;

import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.allyn.lives.R;
import com.allyn.lives.bean.BooksDetailBean;
import com.allyn.lives.netwoarks.IPConfig;
import com.allyn.lives.utils.Dialog;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2016/6/28.
 */
public class BooksDetailHolder extends BaseViewHolder<BooksDetailBean.ListEntity> implements View.OnClickListener {

    TextView mtitle;
    TextView mtitleSize;
    CardView card_action;

    String mContent;
    String mTitle;

    public BooksDetailHolder(ViewGroup viewGroup) {
        super(viewGroup, R.layout.item_details_title);
        mtitle = $(R.id.tvTitle);
        card_action = $(R.id.card_action);
        mtitleSize = $(R.id.tvTitleSize);
        card_action.setOnClickListener(this);
    }

    @Override
    public void setData(BooksDetailBean.ListEntity data) {
        super.setData(data);
        mContent=data.getMessage();
        mTitle=data.getTitle();
        mtitle.setText(mTitle);
        int index = getLayoutPosition() + 1;
        mtitleSize.setText(String.valueOf(index + ". "));
    }

    @Override
    public void onClick(View view) {
        Dialog.showMsg(getContext(),mTitle,mContent);
    }

}
