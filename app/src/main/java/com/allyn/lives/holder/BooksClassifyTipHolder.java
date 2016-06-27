package com.allyn.lives.holder;

import android.support.v7.widget.GridLayoutManager;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.allyn.lives.R;
import com.allyn.lives.adapter.BooksClassItemAdapter;
import com.allyn.lives.bean.BooksBean;
import com.allyn.lives.bean.BooksClassifyBean;
import com.allyn.lives.model.BooksModel;
import com.allyn.lives.utils.Config;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import java.util.Random;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/6/21.
 */
public class BooksClassifyTipHolder extends BaseViewHolder<BooksClassifyBean.TngouEntity> {

    TextView mClassityName;
    Button btnMore;
    EasyRecyclerView recyclerView;
    BooksClassItemAdapter adapter;

    public BooksClassifyTipHolder(ViewGroup viewGroup) {
        super(viewGroup, R.layout.item_books_classify_tip);
        mClassityName = $(R.id.tvClassifyName);
        btnMore = $(R.id.btnMore);
        recyclerView = $(R.id.recycler);

        adapter = new BooksClassItemAdapter(getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setErrorView(R.layout.error_layout);
        recyclerView.setProgressView(R.layout.progress_layout);
        recyclerView.setAdapterWithProgress(adapter);

    }

    @Override
    public void setData(BooksClassifyBean.TngouEntity data) {
        super.setData(data);

        mClassityName.setText(data.getName());

        int typeId = data.getId();
        BooksModel.getImageList(new Random().nextInt(Config.random_size), Config.classify_size, typeId, new Subscriber<BooksBean>() {
            @Override
            public void onNext(BooksBean imageBean) {
                adapter.clear();
                adapter.addAll(imageBean.getList());
            }

            @Override
            public void onCompleted() {
                recyclerView.showProgress();
            }

            @Override
            public void onError(Throwable e) {
                recyclerView.showError();
            }


        });

    }
}
