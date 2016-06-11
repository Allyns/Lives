package com.allyn.lives.model;


import android.database.Cursor;
import android.net.Uri;

import com.allyn.lives.app.MainApp;
import com.allyn.lives.model.bean.MusicBean;

import java.util.ArrayList;

import android.provider.MediaStore.Audio.Media;
import android.util.Log;

import java.util.List;

/**
 * Created by apple on 16/6/8.
 */
public class MusicModel {
    static String TAG = "MusicModel";
    static private Uri contentUri = Media.EXTERNAL_CONTENT_URI;
    static private String sortOrder = Media.DATA;

    public static List<MusicBean> getLocaleMusic() {
        List<MusicBean> musicList = new ArrayList<>();
        Cursor cursor = MainApp.getContexts().getContentResolver().query(contentUri, null, null, null, sortOrder);
        if (cursor == null) {
            Log.v(TAG, "Line(37  )   Music Loader cursor == null.");
        } else if (!cursor.moveToFirst()) {
            Log.v(TAG, "Line(39  )   Music Loader cursor.moveToFirst() returns false.");
        } else {
            int displayNameCol = cursor.getColumnIndex(Media.DISPLAY_NAME);
            int albumCol = cursor.getColumnIndex(Media.ALBUM);
            int idCol = cursor.getColumnIndex(Media._ID);
            int durationCol = cursor.getColumnIndex(Media.DURATION);
            int sizeCol = cursor.getColumnIndex(Media.SIZE);
            int artistCol = cursor.getColumnIndex(Media.ARTIST);
            int urlCol = cursor.getColumnIndex(Media.DATA);
            do {
                String title = cursor.getString(displayNameCol);
                String name = title.substring(0, title.length() - 4);
                String album = cursor.getString(albumCol);

                int id = cursor.getInt(idCol);
                int duration = cursor.getInt(durationCol);
                long size = cursor.getLong(sizeCol);
                String artist = cursor.getString(artistCol);
                String url = cursor.getString(urlCol);
                if (album.equals("<unknown>")) {
                    album = "";
                }
                if (artist.equals("<unknown>")) {
                    artist = "未知";
                }
                MusicBean musicInfo = new MusicBean();
                musicInfo.setAlbum(album);
                musicInfo.setDuration(duration);
                musicInfo.setSize(size);
                musicInfo.setArtist(artist);
                musicInfo.setFileData(url);
                musicInfo.setId(id);
                musicInfo.setName(name);
                musicList.add(musicInfo);

            } while (cursor.moveToNext());
        }
        return musicList;
    }

}
