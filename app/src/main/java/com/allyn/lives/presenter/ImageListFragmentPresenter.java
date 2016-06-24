package com.allyn.lives.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import com.allyn.lives.bean.ImageBean;
import com.allyn.lives.fragment.image.ImageListFragment;
import com.allyn.lives.model.ImageModel;
import com.allyn.lives.utils.Config;
import com.jude.beam.expansion.list.BeamListFragmentPresenter;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/6/24.
 */
public class ImageListFragmentPresenter extends BeamListFragmentPresenter<ImageListFragment, ImageBean.ListEntity> implements SwipeRefreshLayout.OnRefreshListener {

    int index = 1;

    @Override
    protected void onCreate(@NonNull ImageListFragment view, Bundle savedState) {
        super.onCreate(view, savedState);
        onRefresh();
    }

    @Override
    protected void onCreateView(@NonNull ImageListFragment view) {
        super.onCreateView(view);

    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        getData(Config.def_size);
    }

    public void getData(int page) {
        Log.i("typeId=========",""+getView().getArguments().getInt(Config.typdId));
        ImageModel.getImageList(page, Config.size, getView().getArguments().getInt(Config.typdId), new Subscriber<ImageBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ImageBean imageBean) {
                getAdapter().addAll(imageBean.getList());
            }
        });
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        index++;
        getData(index);
    }


}
