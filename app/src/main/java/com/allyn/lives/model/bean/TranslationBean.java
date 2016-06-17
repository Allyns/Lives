package com.allyn.lives.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/17.
 */
public class TranslationBean implements Parcelable {

    /**
     * translation : ["Listening to music"]
     * query : 听歌
     * errorCode : 0
     * web : [{"value":["Songs","k pop&j pop","chanson"],"key":"听歌"},{"value":["TinggeCom"],"key":"听歌网"},{"value":["How to listen to music","What about listening to songs"],"key":"听歌怎么样"}]
     */

    private String query;
    private int errorCode;
    private List<String> translation;
    private List<WebEntity> web;

    public void setQuery(String query) {
        this.query = query;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setTranslation(List<String> translation) {
        this.translation = translation;
    }

    public void setWeb(List<WebEntity> web) {
        this.web = web;
    }

    public String getQuery() {
        return query;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public List<String> getTranslation() {
        return translation;
    }

    public List<WebEntity> getWeb() {
        return web;
    }

    public static class WebEntity {
        /**
         * value : ["Songs","k pop&j pop","chanson"]
         * key : 听歌
         */

        private String key;
        private List<String> value;

        public void setKey(String key) {
            this.key = key;
        }

        public void setValue(List<String> value) {
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public List<String> getValue() {
            return value;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.query);
        dest.writeInt(this.errorCode);
        dest.writeStringList(this.translation);
        dest.writeList(this.web);
    }

    public TranslationBean() {
    }

    protected TranslationBean(Parcel in) {
        this.query = in.readString();
        this.errorCode = in.readInt();
        this.translation = in.createStringArrayList();
        this.web = new ArrayList<WebEntity>();
        in.readList(this.web, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<TranslationBean> CREATOR = new Parcelable.Creator<TranslationBean>() {
        public TranslationBean createFromParcel(Parcel source) {
            return new TranslationBean(source);
        }

        public TranslationBean[] newArray(int size) {
            return new TranslationBean[size];
        }
    };
}
