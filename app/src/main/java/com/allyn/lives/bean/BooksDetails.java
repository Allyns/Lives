package com.allyn.lives.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/27.
 */
public class BooksDetails implements Parcelable {


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

    private String author;
    private int bookclass;
    private String count;
    private int fcount;
    private int id;
    private String img;
    private String name;
    private int rcount;
    private boolean status;
    private String summary;
    private long time;
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
         * <p>　　我刚才用那么长的篇幅告诉你，你要做自己的女神，要独具创意，要倾听内心的声音。现在呢，我又要告诉你，你还得寻找别的女神，得模仿，得从你身边的世界获得灵感，汲取养分。所以，我可能有点前言不搭后语，自相矛盾。（我当然可以这么做，对不对？这是我写的一本书啊。）不过，也许我的话并没有前后矛盾。我认为，灵感是一种只有你自己知道的体验，是你和真实的自我发生联系时才出现的。你知道你对自己有什么幻想，你知道你想成为一个什么样的人，这时候灵感才会降临。你必须先搞清楚这一点，然后才能回答那个老生常谈的问题：“你的灵感是哪里来的？”我知道，这个问题人们通常是请艺术家和设计师、音乐家和女演员回答的。不过，你也该请自己回答一下。时髦而独具风情的女人知道答案。艺术家呢？女演员呢？摇滚歌星呢？</p>
         * <p>　　时装的发展史告诉我们，即使“原创作品”也模仿了某个人或某件东西。他们受到外界的启发产生了一个创意，采用了这个意像的某些元素，然后把它变成自己的。对艺术家和设计师来说，灵感的来源是不受限制的。他们随时随地可以受到启发。</p>
         * <p>　　约翰•加利亚诺（John Galliano）慢跑的时候，在塞纳河边看到一排无家可归的流浪汉，他受他们的启发设计了潇洒的流浪者风格的时装。维维恩•韦斯特伍德（Vivienne Westwood）从伦敦朋克乐队的歌手们穿的衣服上得到灵感，把它们变成了高级时装。斯蒂芬•斯普劳斯（Stephen Sprouse）在纽约的文化特区东村（East Village）游荡了一天，开始收集人们在墙壁上胡乱涂写的文字，他的这种做法后来变得相当有名。设计师们受到眼前景像的触动，这些景像以某种奇特的方式跟他们自身发生了联系。他们希望自己也成为其中的一分子，于是他们采用了这些意像，把它们稍加修改，变成了自己的东西。</p>
         * <p>　　你看待生活，也应该用大致相同的眼光。风格就是认识你自己，搞清楚你想要成为什么样的人；要做到这一点，你就得寻找自己的灵感。看看从小和你一起长大的女朋友，想一想你在旅途中的见闻。看看艺术作品和艺术家、电影和女演员、音乐和摇滚明星。想一想你愿意与谁、与什么认同，然后把它们做一点调整，让它们适应你的性格……然后，像你心目中那些了不起的偶像一样，你要努力向这个理想角色靠拢。</p>
         * <p>　　一切事物都可以成为灵感的源泉——大自然、艺术、建筑物、文学、旅行、电影、音乐，等等。美无处不在，但你要发现她。不要对身边的东西熟视无睹：你要观察它们，洞悉它们。把你的眼睛擦亮，注意日常生活中的细微之处。时装也像艺术、音乐、旅行、文学和电影一样，是我们从这个世界上得到的小礼物。而你的着装风格呢，它是表达你的文化、身份认同、热情以及你的精神世界的一种方式。</p>
         * <p>　　要趣味盎然才好。</p>
         * <p>　　风格是用简单的方式陈述复杂事物的一种办法。</p>
         * <p>　　琼•科克托（Jean Cocteau）</p>
         * <p></p>
         * </div>
         * seq : 0
         * title :  《风格秘语》 灵感
         */

        private int book;
        private int id;
        private String message;
        private int seq;
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

    public BooksDetails() {
    }

    protected BooksDetails(Parcel in) {
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

    public static final Parcelable.Creator<BooksDetails> CREATOR = new Parcelable.Creator<BooksDetails>() {
        public BooksDetails createFromParcel(Parcel source) {
            return new BooksDetails(source);
        }

        public BooksDetails[] newArray(int size) {
            return new BooksDetails[size];
        }
    };

}
