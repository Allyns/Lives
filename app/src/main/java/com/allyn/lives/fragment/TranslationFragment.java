package com.allyn.lives.fragment;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.allyn.lives.R;
import com.allyn.lives.fragment.base.BaseFragment;
import com.allyn.lives.model.TranslationModel;
import com.allyn.lives.bean.TranslationBean;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;


/**
 * A simple {@link Fragment} subclass.
 */
public class TranslationFragment extends BaseFragment {


    public static TranslationFragment newInstance() {
        TranslationFragment translationFragment = new TranslationFragment();
        return translationFragment;
    }

    @Bind(R.id.content)
    EditText mContent;
    @Bind(R.id.submit)
    Button mSubmit;
    @Bind(R.id.msg)
    TextView mMsg;
    @Bind(R.id.web_msg)
    TextView web_msg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_translation, container, false);
        ButterKnife.bind(this, v);
        ActionListener();
        return v;
    }

    private void ActionListener() {
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

    }

    public void getData() {
        TranslationModel.getData(mContent.getText().toString(), new Subscriber<TranslationBean>() {

            @Override
            public void onError(Throwable e) {
                Snackbar.make(mSubmit, "翻译失败", Snackbar.LENGTH_SHORT).show();
                Log.i("error", e.getMessage());
            }

            @Override
            public void onNext(TranslationBean translationBean) {
                mMsg.setText(translationBean.getQuery() + "   翻译结果 :  " + translationBean.getTranslation().get(0) + "\n\n" + "网络译义");
                String str = "";
                for (int i = 0; i <= translationBean.getWeb().size()-1; i++) {
                    str += "原文：" + translationBean.getWeb().get(i).getKey() + "   译文：" + translationBean.getWeb().get(i).getValue() + "\n";
                }
                web_msg.setText(str);

//                web_msg.setText("原文：" + translationBean.getWeb().get(0).getKey() + "   译文：" + translationBean.getWeb().get(0).getValue() + "\n");
            }

            @Override
            public void onCompleted() {
                mMsg.setText("翻译中...");
            }

            ;
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
