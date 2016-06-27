package com.allyn.lives.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/20.
 */
public class BooksBean implements Parcelable {


    /**
     * list : [{"author":"郭昕","bookclass":4,"count":"1410","fcount":0,"id":1239,"img":"/book/150802/c9e6346d7eb6fa4a40d818754e0e4858.jpg","name":"心存慰藉","rcount":0,"summary":"　人生并非坦途，有快乐就有痛苦，有幸福就有悲伤。人情的冷暖，世态的炎凉，不能回避就要学会正确面对。在经历沧桑和磨砺之后，对人生我们就能多一些反思，多一些深刻的属于我们自己的体悟。漫长而又短暂的人生，一路走来，总会在心底印留下各色的记忆，关于我们曾经遭遇过的人，关于我们曾经经历过的事，关于我们曾经阅过的风情、风景，或美好，或苦痛，有些值得我们用毕生去追思、缅怀、玩味，有些则需要我们努力去抛弃、丢失、忘却\u2026\u2026","time":1438490059000},{"author":"苏菁","bookclass":4,"count":"1668","fcount":0,"id":1238,"img":"/book/150802/f8b347d5c59124e82974108f3e8ad24b.jpg","name":"你相信婚姻能拯","rcount":0,"summary":"　本书是一部纪实性心灵励志小说。主人公梦琪童年时代因寄养在外婆家，缺少父母的关爱，形成了较为孤僻的性格。这样的性格伴随着她走入了婚姻，当她濒临失去丈夫和儿子的边缘时，她幡然醒悟，开始了对婚姻的自救。正是在母亲的位置上，她深刻反省自己，拯救自己，由于完善了自己的性格，从而挽救了濒临崩溃的婚姻，重新找回了爱情的她，带着一份责任感来到了首都北京，从心理咨询开始了对整个社会命运的探索，她给别人做心理咨询、她深入北京各类素质培训机构、医院、北京家庭、各大高等院校、监狱、法院\u2026\u2026","time":1438490059000},{"author":"张世琦","bookclass":4,"count":"1511","fcount":0,"id":1237,"img":"/book/150802/34d2b35f671f2dcf536861b71811aeb8.jpg","name":"青年不可不知Ⅰ","rcount":0,"summary":"　婚姻问题并不神秘。人到了一定年龄，婚姻问题便摆在面前，逼着你不得不处理，不得不正确回答有关婚姻的各种问题。因此，对婚姻应该有正确认识，应该看到婚姻有可能给人带来巨大麻烦的危险性，必须正确处理好。上至高官巨富，下至平民百姓，对任何人来说，婚姻问题都是光明正大的，两性问题都是不可回避的。","time":1438490059000},{"author":"陈彤","bookclass":4,"count":"778","fcount":0,"id":1236,"img":"/book/150802/d6e6d98754088f3b70cfc2ce0c6c5542.jpg","name":"旧爱新欢","rcount":0,"summary":"　判断一个男人是否成功，有很多方式，最简单的一种，是看他有几个办公室；判断一个女人是否丰富，也有很多方式，最直接的一种，是看她睡过多少张床。有的女人很简单，闺床\u2014\u2014婚床，一生！有的女人很幸福，还没学会走路，就已经睡过好几张童床。有的女人一生睡过很多床，但其实只睡过一张，因为每张床和另一张没什么不同。","time":1438490059000}]
     * page : 1
     * size : 4
     * status : true
     * total : 415
     * totalpage : 104
     */

    private int page;
    private int size;
    private boolean status;
    private int total;
    private int totalpage;
    private List<ListEntity> list;

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public boolean getStatus() {
        return status;
    }

    public int getTotal() {
        return total;
    }

    public int getTotalpage() {
        return totalpage;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public static class ListEntity {
        /**
         * author : 郭昕
         * bookclass : 4
         * count : 1410
         * fcount : 0
         * id : 1239
         * img : /book/150802/c9e6346d7eb6fa4a40d818754e0e4858.jpg
         * name : 心存慰藉
         * rcount : 0
         * summary : 　人生并非坦途，有快乐就有痛苦，有幸福就有悲伤。人情的冷暖，世态的炎凉，不能回避就要学会正确面对。在经历沧桑和磨砺之后，对人生我们就能多一些反思，多一些深刻的属于我们自己的体悟。漫长而又短暂的人生，一路走来，总会在心底印留下各色的记忆，关于我们曾经遭遇过的人，关于我们曾经经历过的事，关于我们曾经阅过的风情、风景，或美好，或苦痛，有些值得我们用毕生去追思、缅怀、玩味，有些则需要我们努力去抛弃、丢失、忘却……
         * time : 1438490059000
         */

        private String author;
        private int bookclass;
        private String count;
        private int fcount;
        private int id;
        private String img;
        private String name;
        private int rcount;
        private String summary;
        private long time;

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

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public void setTime(long time) {
            this.time = time;
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

        public String getSummary() {
            return summary;
        }

        public long getTime() {
            return time;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.page);
        dest.writeInt(this.size);
        dest.writeByte(status ? (byte) 1 : (byte) 0);
        dest.writeInt(this.total);
        dest.writeInt(this.totalpage);
        dest.writeList(this.list);
    }

    public BooksBean() {
    }

    protected BooksBean(Parcel in) {
        this.page = in.readInt();
        this.size = in.readInt();
        this.status = in.readByte() != 0;
        this.total = in.readInt();
        this.totalpage = in.readInt();
        this.list = new ArrayList<ListEntity>();
        in.readList(this.list, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<BooksBean> CREATOR = new Parcelable.Creator<BooksBean>() {
        public BooksBean createFromParcel(Parcel source) {
            return new BooksBean(source);
        }

        public BooksBean[] newArray(int size) {
            return new BooksBean[size];
        }
    };
}
