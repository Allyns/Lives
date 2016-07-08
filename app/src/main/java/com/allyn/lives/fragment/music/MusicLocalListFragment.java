package com.allyn.lives.fragment.music;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.allyn.lives.R;
import com.allyn.lives.activity.music.MusicPlayActivivy;
import com.allyn.lives.adapter.MusicListAdapter;
import com.allyn.lives.bean.MusicBean;
import com.allyn.lives.events.MusicBeamEvent;
import com.allyn.lives.fragment.base.BaseFragment;
import com.allyn.lives.manage.PlayMainage;
import com.allyn.lives.model.MusicModel;
import com.allyn.lives.service.MusicService;
import com.allyn.lives.utils.Config;
import com.allyn.lives.utils.RxBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.yokeyword.indexablelistview.IndexEntity;
import me.yokeyword.indexablelistview.IndexableStickyListView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by apple on 16/6/8.
 */
public class MusicLocalListFragment extends BaseFragment {

    public static MusicLocalListFragment newInstance() {
        MusicLocalListFragment musicLocalListFragment = new MusicLocalListFragment();
        return musicLocalListFragment;
    }


    @Bind(R.id.searchview)
    SearchView mSearchView;

    @Bind(R.id.tvAuthorName)
    TextView tvAuthorName;
    @Bind(R.id.tvMusicNmae)
    TextView tvMusicNmae;
    @Bind(R.id.tvCode)
    TextView tvCode;

    @Bind(R.id.relativeLayout)
    RelativeLayout relativeLayout;

    @Bind(R.id.listView)
    IndexableStickyListView mListView;

    static List<MusicBean> been;
    MusicListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_list, container, false);
        ButterKnife.bind(this, view);
        initView();
        getData();
        listener();

        RxBus.getDefault().toObserverable(MusicBeamEvent.class).subscribe(new Action1<MusicBeamEvent>() {
            @Override
            public void call(MusicBeamEvent musicBeamEvent) {
                getMsg();
            }
        });

        return view;
    }

    public void getMsg() {
        int position = MusicService.servicePosition;
        if (position == -1) {
            position = 0;
        }
        tvAuthorName.setText(been.get(position).getArtist());
        tvMusicNmae.setText(been.get(position).getName());
        MediaPlayer mediaPlayer = PlayMainage.mediaPlayer;
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                tvCode.setText("播放中...");
            } else {
                tvCode.setText("暂停中...");
            }
        } else {
            tvCode.setText("暂停中...");
        }
    }

    private void initView() {
        adapter = new MusicListAdapter(getActivity());
        mListView.setAdapter(adapter);
    }

    public void getData() {
        Observable.create(new Observable.OnSubscribe<List<MusicBean>>() {
            @Override
            public void call(Subscriber<? super List<MusicBean>> subscriber) {
                subscriber.onNext(MusicModel.getLocaleMusic());
                subscriber.onCompleted();
            }
        }).filter(new Func1<List<MusicBean>, Boolean>() {
            @Override
            public Boolean call(List<MusicBean> musicBeen) {
                return filemp3(musicBeen);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<MusicBean>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<MusicBean> musicBeen) {
                        been = musicBeen;
                        Collections.reverse(musicBeen);
                        mListView.bindDatas(musicBeen);
                        getMsg();
                    }
                });
    }

    public boolean filemp3(List<MusicBean> musicBeen) {
        Observable.from(musicBeen).filter(new Func1<MusicBean, Boolean>() {
            @Override
            public Boolean call(MusicBean musicBean) {
                return musicBean.getFileData().contains(".mp3");
            }
        });
        return true;
    }

    private void listener() {
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), MusicPlayActivivy.class));
            }
        });
        mListView.setOnItemContentClickListener(new IndexableStickyListView.OnItemContentClickListener() {
            @Override
            public void onItemClick(View v, IndexEntity indexEntity) {
                int index = -1;
                for (MusicBean musicBean : getMusicList()) {
                    index++;
                    if (musicBean.getName().equals(indexEntity.getName())) {
                        Intent intent = new Intent(getActivity(), MusicService.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt(Config.position, index);
                        intent.putExtra(Config.bunder, bundle);
                        getActivity().startService(intent);
                    }
                }
            }
        });
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mListView.searchTextChange(newText);
                return true;
            }
        });
    }

    public static List<MusicBean> getMusicList() {
        return been;
    }
}
