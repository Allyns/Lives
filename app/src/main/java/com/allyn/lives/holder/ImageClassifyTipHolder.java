package com.allyn.lives.holder;

import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.allyn.lives.R;
import com.allyn.lives.adapter.ImageClassItemAdapter;
import com.allyn.lives.bean.ImageBean;
import com.allyn.lives.bean.ImageClassifyBean;
import com.allyn.lives.model.ImageModel;
import com.allyn.lives.netwoarks.Invoking;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/6/21.
 */
public class ImageClassifyTipHolder extends BaseViewHolder<ImageClassifyBean.TngouEntity> {

    TextView mClassityName;
    Button btnMore;
    EasyRecyclerView recyclerView;
    ImageClassItemAdapter adapter;

    public ImageClassifyTipHolder(ViewGroup viewGroup) {
        super(viewGroup, R.layout.item_image_classify_tip);
        mClassityName = $(R.id.tvClassifyName);
        btnMore = $(R.id.btnMore);
        recyclerView = $(R.id.recycler);


    }

    @Override
    public void setData(ImageClassifyBean.TngouEntity data) {
        super.setData(data);

        mClassityName.setText(data.getName());

        adapter = new ImageClassItemAdapter(getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setErrorView(R.layout.error_layout);
        recyclerView.setProgressView(R.layout.progress_layout);
        recyclerView.setAdapterWithProgress(adapter);
        int typeId = data.getId();
        ImageModel.getImageList(1, 4, typeId, new Subscriber<ImageBean>() {
            @Override
            public void onNext(ImageBean imageBean) {
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
