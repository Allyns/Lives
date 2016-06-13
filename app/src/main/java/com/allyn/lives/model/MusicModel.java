package com.allyn.lives.model;


import android.database.Cursor;
import android.net.Uri;

import com.allyn.lives.app.MainApp;
import com.allyn.lives.model.bean.MusicBean;

import java.util.ArrayList;

import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Media;
import android.util.Log;

import java.util.List;

/**
 * Created by apple on 16/6/8.
 */
public class MusicModel {
    static String TAG = "MusicModel";
    static private Uri contentUri = Media.EXTERNAL_CONTENT_URI;
    static private Uri contentUri_image = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    static private String sortOrder_img = MediaStore.Images.Media._ID + " DESC";
    static private String sortOrder = Media._ID + " DESC";

    public static List<MusicBean> getLocaleMusic() {
        List<MusicBean> musicList = new ArrayList<>();
        Cursor cursor = MainApp.getContexts().getContentResolver().query(contentUri, null, null, null, sortOrder);
        if (cursor == null) {
            Log.v(TAG, "Line(37  )   Music Loader cursor == null.");
        } else if (!cursor.moveToFirst()) {
            Log.v(TAG, "Line(39  )   Music Loader cursor.moveToFirst() returns false.");
        } else {
            int idCol = cursor.getColumnIndex(Media._ID);
            int title = cursor.getColumnIndex(Media.TITLE);
            int albumCol = cursor.getColumnIndex(Media.ALBUM);
            int durationCol = cursor.getColumnIndex(Media.DURATION);
            int sizeCol = cursor.getColumnIndex(Media.SIZE);
            int artistCol = cursor.getColumnIndex(Media.ARTIST);
            int urlCol = cursor.getColumnIndex(Media.DATA);
            int type = cursor.getColumnIndex(Media.MIME_TYPE);
            do {
                String name = cursor.getString(title);
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
//                Cursor cursor_img = MainApp.getContexts().getContentResolver().query(contentUri_image, null, MediaStore.Images.Media._ID + "=?", new String[id], sortOrder_img);
//                if (cursor_img.moveToNext()) {
//                    String img_url = cursor_img.getString(cursor_img.getColumnIndex(MediaStore.Images.Media.DATA));
//                    musicInfo.setImageurl(img_url);
//                }
                musicList.add(musicInfo);

            } while (cursor.moveToNext());
        }
        return musicList;
    }

}
