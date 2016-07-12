package com.allyn.lives.manage;

import android.content.ContentResolver;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.util.Log;

import com.allyn.lives.app.MainApp;
import com.allyn.lives.bean.MusicBean;
import com.allyn.lives.fragment.music.MusicLocalListFragment;

import java.io.File;
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
    public static int Code = 1;

    public static int getCode() {
        return Code;
    }

    public static void setCode(int code) {
        Code = code;
    }

    static List<MusicBean> musicBeen;

    public static MediaPlayer mediaPlayer = null;


    public static MediaPlayer play(int musicPostion, boolean isliteorm) {
        MusicBean musicBean;
        if (isliteorm) {
            musicBeen = MainApp.getLiteOrm().query(MusicBean.class);
            musicBean = musicBeen.get(musicPostion);
            Log.i("", "isliteorm=================true");

        } else {
            musicBeen = MusicLocalListFragment.getMusicList();
            Log.i("", "isliteorm===============false");
            musicBean = musicBeen.get(musicPostion);
        }
        if (mediaPlayer == null) {
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(musicBean.getFileData());
                Log.i("", "musicBean.getFileData()-----------------" + musicBean.getFileData());
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
        if (mm / 10 == 0) {
            m = "0" + mm;
        } else {
            m = mm + "";
        }
        if (ss / 10 == 0) {
            n = "0" + ss;
        } else {
            n = ss + "";
        }
        return m + ":" + n;
    }

    public static boolean deleteMusic(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * 从数据库中删除歌曲
     */
    public static boolean delete(Context context, MusicBean musicBean) {
        ContentResolver cr = context.getContentResolver();
        Uri uri = MediaStore.Audio.Media.getContentUriForPath(musicBean.getFileData());
//        boolean isok=deleteMusic(musicBean.getFileData());
//        if (isok){
//            return true;
//        }
        long s = cr.delete(uri, BaseColumns._ID + "=" + musicBean.getId(), null);
        if (s != -1) {
            return true;
        } else {
            return false;
        }
    }
}
