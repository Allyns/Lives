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
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.allyn.lives.R;
import com.allyn.lives.activity.music.MusicPlayActivivy;
import com.allyn.lives.adapter.SortAdapter;
import com.allyn.lives.bean.MusicBean;
import com.allyn.lives.events.MusicBeamEvent;
import com.allyn.lives.events.MusicCodeEvent;
import com.allyn.lives.fragment.base.BaseFragment;
import com.allyn.lives.manage.PlayMainage;
import com.allyn.lives.model.MusicModel;
import com.allyn.lives.service.MusicService;
import com.allyn.lives.utils.Config;
import com.allyn.lives.utils.RxBus;
import com.allyn.lives.view.widgets.CharacterParser;
import com.allyn.lives.view.widgets.PinyinComparator;
import com.allyn.lives.view.widgets.RefreshListView;
import com.allyn.lives.view.widgets.SlideLinerLayout;
import com.allyn.lives.view.widgets.SortModel;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by apple on 16/6/8.
 */
public class MusicLocalListFragment extends BaseFragment {

    public static MusicLocalListFragment newInstance() {
        MusicLocalListFragment musicLocalListFragment = new MusicLocalListFragment();
        return musicLocalListFragment;
    }


    @Bind(R.id.lv_sort)
    RefreshListView mListView;

    @Bind(R.id.dialog)
    TextView dialog;
    @Bind(R.id.sidrbar)
    SlideLinerLayout sidrbar;

    @Bind(R.id.tvAuthorName)
    TextView tvAuthorName;

    @Bind(R.id.tvCode)
    TextView tvCode;

    @Bind(R.id.relativeLayout)
    RelativeLayout relativeLayout;

    static List<MusicBean> been;
    SortAdapter adapter;

    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;


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
        RxBus.getDefault().toObserverable(MusicCodeEvent.class).subscribe(new Action1<MusicCodeEvent>() {
            @Override
            public void call(MusicCodeEvent musicBeamEvent) {
                getMsg();
            }
        });

        return view;
    }

    public void getMsg() {
        int position = MusicService.servicePosition;
        if (position == -1) {
            position = 0;
            relativeLayout.setVisibility(View.GONE);
        } else {
            relativeLayout.setVisibility(View.VISIBLE);
        }
        sidrbar.setTextDialog(dialog);
        tvAuthorName.setText(PlayMainage.getList().get(position).getArtist());
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
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        mListView.setAdapter(adapter);
    }

    public void getData() {
        Observable.create(new Observable.OnSubscribe<List<MusicBean>>() {
            @Override
            public void call(Subscriber<? super List<MusicBean>> subscriber) {
                subscriber.onNext(MusicModel.getLocaleMusic());
                subscriber.onCompleted();
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
                        if (musicBeen.size()!=0){
                            been = musicBeen;
                            List<SortModel> mSortList = filledData(initData());
                            Collections.sort(mSortList, pinyinComparator);
                            adapter = new SortAdapter(getActivity(), mSortList);
                            // 根据a-z进行排序源数据
                            mListView.setAdapter(adapter);
                            mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
                                @Override
                                public void onScrollStateChanged(AbsListView view, int scrollState) {
                                    if (scrollState == SCROLL_STATE_FLING || scrollState == SCROLL_STATE_TOUCH_SCROLL){
                                        dialog.setVisibility(View.VISIBLE);
                                    }else{
                                        dialog.setVisibility(View.GONE);
                                    }
                                }

                                @Override
                                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                                    String letter = ((SortModel)adapter.getItem(firstVisibleItem)).getSortLetters();
                                    if(letter != null && !letter.equals("") && sidrbar.mListViewScrollAble){
                                        sidrbar.setChoosed(letter);
                                        dialog.setText(letter);
                                    }
                                }
                            });
                            getMsg();
                        }
                    }
                });
    }

    public List<String> initData() {
        List<String> arr = new ArrayList<>();
        for (MusicBean musicBean : been) {
            arr.add(musicBean.getName());
        }
        return arr;
    }

    private List<SortModel> filledData(List<String> data) {
        List<SortModel> mSortList = new ArrayList<SortModel>();

        for (int i = 0; i < data.size(); i++) {
            SortModel sortModel = new SortModel();
            sortModel.setName(data.get(i));
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(data.get(i));
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;
    }

    private void listener() {
        //设置右侧触摸监听
        sidrbar.setOnTouchingLetterChangedListener(new SlideLinerLayout.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    stopScroll(mListView);
                    mListView.setSelection(position);
                } else {

                }

            }
        });
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), MusicPlayActivivy.class));
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //这里要利用adapter.getItem(position)来获取当前position所对应的对象
                int index = -1;
                for (MusicBean musicBean : getMusicList()) {
                    index++;
                    if (musicBean.getName().equals(been.get(position).getName())) {
                        Intent intent = new Intent(getActivity(), MusicService.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt(Config.position, index);
                        intent.putExtra(Config.bunder, bundle);
                        getActivity().startService(intent);
                    }
                }
            }
        });
    }

    public static List<MusicBean> getMusicList() {
        return been;
    }

    /**
     * 反射使得listView停止滚动
     *
     * @param view
     */
    private void stopScroll(AbsListView view) {
        try {
            Field field = android.widget.AbsListView.class.getDeclaredField("mFlingRunnable");
            field.setAccessible(true);
            Object flingRunnable = field.get(view);
            if (flingRunnable != null) {
                Method method = Class.forName("android.widget.AbsListView$FlingRunnable").getDeclaredMethod("endFling");
                method.setAccessible(true);
                method.invoke(flingRunnable);
            }
        } catch (Exception e) {
        }
    }
}
