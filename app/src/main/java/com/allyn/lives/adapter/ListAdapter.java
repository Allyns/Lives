package com.allyn.lives.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/3/24.
 */
public class ListAdapter extends RecyclerView.Adapter{
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

}

//public class QuickAdapter extends BaseQuickAdapter<Status> {
//    public QuickAdapter(Context context) {
//        super(context, R.layout.tweet, DataServer.getSampleData(100));
//    }
//
//    public QuickAdapter(Context context, int dataSize) {
//        super(context, R.layout.tweet, DataServer.getSampleData(dataSize));
//    }
//
//    @Override
//    protected void convert(BaseViewHolder helper, Status item) {
//        helper.setText(R.id.tweetName, item.getUserName())
//                .setText(R.id.tweetText, item.getText())
//                .setText(R.id.tweetDate, item.getCreatedAt())
//                .setImageUrl(R.id.tweetAvatar, item.getUserAvatar())
//                .setVisible(R.id.tweetRT, item.isRetweet())
//                .linkify(R.id.tweetText);
//    }
//}