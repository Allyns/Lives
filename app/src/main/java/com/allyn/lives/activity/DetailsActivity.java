package com.allyn.lives.activity;

import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.allyn.lives.R;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("聚完");
        setSupportActionBar(toolbar);
    }

    public void toGithub(View view) {
        final BottomSheetDialog sheetDialog = new BottomSheetDialog(DetailsActivity.this, R.style.AppTheme_BottomSheetDialog);
        View view1 = LayoutInflater.from(this).inflate(R.layout.myview_dialog, null);
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

    public void sample1(View view) {

    }
}
