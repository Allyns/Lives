package com.allyn.lives.utils;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.allyn.lives.R;

/**
 * Created by Administrator on 2016/6/29.
 */
public class Dialog {

    public static void showMsg(Context context, String title, String Content) {
        final BottomSheetDialog sheetDialog = new BottomSheetDialog(context, R.style.AppTheme_BottomSheetDialog);
        View view1 = LayoutInflater.from(context).inflate(R.layout.myview_dialog, null);
        TextView tvClassTitle = (TextView) view1.findViewById(R.id.tvClassTitle);
        TextView tvContent = (TextView) view1.findViewById(R.id.tvContent);
        sheetDialog.setContentView(view1);
        tvContent.setText(Html.fromHtml(Content));
        tvClassTitle.setText(Html.fromHtml(title));
//        sheetDialog.setCanceledOnTouchOutside(false);
        sheetDialog.show();
    }
}
