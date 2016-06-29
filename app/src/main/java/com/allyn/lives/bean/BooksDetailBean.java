package com.allyn.lives.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.Default;
import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/27.
 */
public class BooksDetailBean implements Parcelable {


    /**
     * author : [美]尼娜
     * bookclass : 2
     * count : 771
     * fcount : 0
     * id : 125
     * img : /book/150802/2b0e1283f691341ea94c14b89a1d4990.jpg
     * "list":[{"book":1,"id":100,"message":"","seq":0:"title":""},{}]
     * name : 风格秘语
     * rcount : 0
     * status : true
     * summary : 　迄今为止最IN、最经典的时尚装扮指南</b> 　　公开世界风向前沿人士的绝密know-how</b>启迪空前的个人风格着装革命……</b>
     * time : 1438490059000
     */
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int bookId;


    @Column("author")
    private String author;
    @Column("bookclass")
    private int bookclass;
    @Column("count")
    private String count;
    @Column("fcount")
    private int fcount;
    @Column("id")
    private int id;
    @Column("img")
    private String img;
    @NotNull
    @Column("name")
    private String name;
    @Column("rcount")
    private int rcount;
    @Default("true")
    @Column("status")
    private boolean status;
    @Column("summary")
    private String summary;
    @Column("time")
    private long time;
    @Column("list")
    private List<ListEntity> list;

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBookclass(int bookclass) {
        this.bookclass = bookclass;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setFcount(int fcount) {
        this.fcount = fcount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public String getAuthor() {
        return author;
    }

    public int getBookclass() {
        return bookclass;
    }

    public String getCount() {
        return count;
    }

    public int getFcount() {
        return fcount;
    }

    public int getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public int getRcount() {
        return rcount;
    }

    public boolean getStatus() {
        return status;
    }

    public String getSummary() {
        return summary;
    }

    public long getTime() {
        return time;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public static class ListEntity {
        /**
         * book : 125
         * id : 6294
         * message :
         * <div>
         * <div></div>
         * <p></p>
         * <p>　　第三章 灵感</p>
         * <p>　　“不愿意模仿的人，什么也创造不出来。”</p>
         * <p>　　萨尔瓦多•达利</p>
         * <p>　　琼•科克托（Jean Cocteau）</p>
         * <p></p>
         * </div>
         * seq : 0
         * title :  《风格秘语》 灵感
         */
        @Column("book")
        private int book;
        @Column("id")
        private int id;
        @Column("message")
        private String message;
        @Column("seq")
        private int seq;
        @Column("title")
        private String title;

        public void setBook(int book) {
            this.book = book;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setSeq(int seq) {
            this.seq = seq;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getBook() {
            return book;
        }

        public int getId() {
            return id;
        }

        public String getMessage() {
            return message;
        }

        public int getSeq() {
            return seq;
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
        dest.writeString(this.author);
        dest.writeInt(this.bookclass);
        dest.writeString(this.count);
        dest.writeInt(this.fcount);
        dest.writeInt(this.id);
        dest.writeString(this.img);
        dest.writeString(this.name);
        dest.writeInt(this.rcount);
        dest.writeByte(status ? (byte) 1 : (byte) 0);
        dest.writeString(this.summary);
        dest.writeLong(this.time);
        dest.writeList(this.list);
    }

    public BooksDetailBean() {
    }

    protected BooksDetailBean(Parcel in) {
        this.author = in.readString();
        this.bookclass = in.readInt();
        this.count = in.readString();
        this.fcount = in.readInt();
        this.id = in.readInt();
        this.img = in.readString();
        this.name = in.readString();
        this.rcount = in.readInt();
        this.status = in.readByte() != 0;
        this.summary = in.readString();
        this.time = in.readLong();
        this.list = new ArrayList<ListEntity>();
        in.readList(this.list, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<BooksDetailBean> CREATOR = new Parcelable.Creator<BooksDetailBean>() {
        public BooksDetailBean createFromParcel(Parcel source) {
            return new BooksDetailBean(source);
        }

        public BooksDetailBean[] newArray(int size) {
            return new BooksDetailBean[size];
        }
    };

}
