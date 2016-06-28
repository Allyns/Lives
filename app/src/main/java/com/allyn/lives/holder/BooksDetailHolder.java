package com.allyn.lives.holder;

import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.allyn.lives.R;
import com.allyn.lives.bean.BooksDetailBean;
import com.allyn.lives.netwoarks.IPConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2016/6/28.
 */
public class BooksDetailHolder extends BaseViewHolder<BooksDetailBean.ListEntity> implements View.OnClickListener {

    TextView mtitle;
    TextView mtitleSize;
    CardView card_action;

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
        mtitle.setText(data.getTitle());
        int index = getLayoutPosition() + 1;
        mtitleSize.setText(String.valueOf(index + ". "));
    }

    @Override
    public void onClick(View view) {
        final BottomSheetDialog sheetDialog = new BottomSheetDialog(getContext(), R.style.AppTheme_BottomSheetDialog);
        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.myview_dialog, null);
        Button button = (Button) view1.findViewById(R.id.button_dialog);
        TextView textView = (TextView) view1.findViewById(R.id.textView5_dialogD);
        sheetDialog.setContentView(view1);
        textView.setText("1839565349");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sheetDialog.dismiss();
            }
        });
        sheetDialog.setCanceledOnTouchOutside(false);
        sheetDialog.show();
    }

}
