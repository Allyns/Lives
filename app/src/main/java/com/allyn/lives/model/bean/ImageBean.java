package com.allyn.lives.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/20.
 */
public class ImageBean implements Parcelable {

    /**
     * status : true
     * total : 714
     * tngou : [{"count":24051,"fcount":0,"galleryclass":1,"id":728,"img":"/ext/160417/677ab7206386e96d45dfa67e719f4335.jpg","rcount":0,"size":6,"time":1460857234000,"title":"粉嫩少女"},{"count":13138,"fcount":0,"galleryclass":5,"id":727,"img":"/ext/160417/9f77927365e2fc34f895bfe373dc2af2.jpg","rcount":0,"size":7,"time":1460857120000,"title":"赵雨菲清纯白诱惑"},{"count":4144,"fcount":0,"galleryclass":3,"id":709,"img":"/ext/160321/0443e950c8e75792ef033472c9071f44.jpg","rcount":0,"size":10,"time":1458560993000,"title":"性感腿模诱人黑丝腿魔鬼身材真美"}]
     */

    private boolean status;
    private int total;
    private List<TngouEntity> tngou;

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setTngou(List<TngouEntity> tngou) {
        this.tngou = tngou;
    }

    public boolean getStatus() {
        return status;
    }

    public int getTotal() {
        return total;
    }

    public List<TngouEntity> getTngou() {
        return tngou;
    }

    public static class TngouEntity {
        /**
         * count : 24051
         * fcount : 0
         * galleryclass : 1
         * id : 728
         * img : /ext/160417/677ab7206386e96d45dfa67e719f4335.jpg
         * rcount : 0
         * size : 6
         * time : 1460857234000
         * title : 粉嫩少女
         */

        private int count;
        private int fcount;
        private int galleryclass;
        private int id;
        private String img;
        private int rcount;
        private int size;
        private long time;
        private String title;

        public void setCount(int count) {
            this.count = count;
        }

        public void setFcount(int fcount) {
            this.fcount = fcount;
        }

        public void setGalleryclass(int galleryclass) {
            this.galleryclass = galleryclass;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setRcount(int rcount) {
            this.rcount = rcount;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getCount() {
            return count;
        }

        public int getFcount() {
            return fcount;
        }

        public int getGalleryclass() {
            return galleryclass;
        }

        public int getId() {
            return id;
        }

        public String getImg() {
            return img;
        }

        public int getRcount() {
            return rcount;
        }

        public int getSize() {
            return size;
        }

        public long getTime() {
            return time;
        }

        public String getTitle() {
            return title;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(status ? (byte) 1 : (byte) 0);
        dest.writeInt(this.total);
        dest.writeList(this.tngou);
    }

    public ImageBean() {
    }

    protected ImageBean(Parcel in) {
        this.status = in.readByte() != 0;
        this.total = in.readInt();
        this.tngou = new ArrayList<TngouEntity>();
        in.readList(this.tngou, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<ImageBean> CREATOR = new Parcelable.Creator<ImageBean>() {
        public ImageBean createFromParcel(Parcel source) {
            return new ImageBean(source);
        }

        public ImageBean[] newArray(int size) {
            return new ImageBean[size];
        }
    };
}
