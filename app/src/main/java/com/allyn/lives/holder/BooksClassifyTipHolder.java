package com.allyn.lives.holder;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.allyn.lives.R;
import com.allyn.lives.activity.books.BooksClassifyDetailsActivity;
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
    public void setData(final BooksClassifyBean.TngouEntity data) {
        super.setData(data);
        mClassityName.setText(data.getName());

        final int Id = data.getId();

        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BooksClassifyDetailsActivity.class);
                intent.putExtra(Config.BookId, Id);
                intent.putExtra(Config.BookClassifyName, data.getName());
                getContext().startActivity(intent);
            }
        });

        BooksModel.getBooksList(2, Config.classify_size, Id, new Subscriber<BooksBean>() {
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
