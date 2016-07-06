package com.allyn.lives.manage;

import android.media.MediaPlayer;
import android.util.Log;

import com.allyn.lives.bean.MusicBean;
import com.allyn.lives.presenter.MusicLocalPresenter;

import java.util.List;


/**
 * Created by Administrator on 2016/7/6.
 */
public class PlayMainage {

    /***
     * 顺序播放
     */
    public static final int ORDER = 1;
    /***
     * 随机播放
     */
    public static final int RANDOM = 2;
    /***
     * 单曲循环
     */
    public static final int REPOT = 3;

    /***
     * 当前状态，默认为顺序播放
     */
    public static final int Code = 1;

    static List<MusicBean> musicBeen = MusicLocalPresenter.getMusicList();

    public static MediaPlayer mediaPlayer = null;

    public static MediaPlayer play(int musicPostion) {
        MusicBean musicBean = musicBeen.get(musicPostion);
        if (mediaPlayer == null) {
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(musicBean.getFileData());
                mediaPlayer.prepare();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mediaPlayer.start();
        return mediaPlayer;
    }

    public static void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public static void pause() {
        if (mediaPlayer != null & mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public static int getMusicSize() {
        return musicBeen.size();
    }

    public static List<MusicBean> getList() {
        return musicBeen;
    }

    /**
     * 改变时间样式
     */
    public static String formatTime(long time) {
        String m = "";
        String n = "";
        //得到所有秒
        long t = time / 1000;
        long mm = t / 60;
        long ss = t % 60;
        if (mm < 10) {
            m = "0" + mm;
        } else {
            m = mm + "";
        }
        if (ss < 10) {
            n = "0" + ss;
        } else {
            n = ss + "";
        }
        return m + ":" + n;
    }
}
