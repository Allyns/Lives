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
     *
     * http://www.tngou.net/api/book/list?id=1
     *
     * list : [{"author":"麦玲玲","bookclass":10,"count":"1633","fcount":0,"id":2874,"img":"/book/150802/b09680bea4e81d7e068c0c9d001f0809.jpg","name":"麦玲玲2010","rcount":0,"summary":"　麦玲玲女士根据玄学与风水学，对2010年（虎年）运程进行了全面分析。分别对十二生肖的虎年运程、简易行运风水布局、\u201c个人出生日\u201d运势预测、各行业未来走向、虎年吉日吉时等贴近大众生活工作实际的内容进行了全面细致的分析与讲解。能够给人指点、为人解惑。","time":1438490059000},{"author":"[日]能见正比古","bookclass":10,"count":"1055","fcount":0,"id":2873,"img":"/book/150802/7ac9306c74fb58bee334556a63795bb5.jpg","name":"血型与爱情","rcount":0,"summary":"　这本血型书要告诉你所有关于爱情的秘密！和刚认识的他（她）坐着地铁紧紧依偎，那一刻，真想一直相互凝望直到永远，可他（她）真的在乎我吗，他（她）喜欢什么隐瞒了什么，我们以后会怎样\u2026\u2026\r\n　　没办法，为了抓住自己的幸福，翻开本书吧！义。\r\n　　本书的作者约翰&#8226;梅杰&#8226;詹金斯是2012理论的最具权威的专家之一，他对我们时代这一最发人深思的现象提出了完整可靠的见解。凭借他的开拓性的研究，包括参与玛雅2012宇宙学的现代重建，以及20多年来对玛雅文化的广泛研究，詹金斯写成了这本理解2012理论的重要指南。\r\n　　该书对2012现象的历史、理论、文化和使现代人关注这一不同寻常的想法的学者做了基本的概述，对这些问题做出了富于启发性的回答，让我们从各执一词的2012纷争中拨云见日，理会玛雅文化有关这一时刻的真正智慧。","time":1438490059000},{"author":"张海涛","bookclass":10,"count":"566","fcount":0,"id":2854,"img":"/book/150802/b2ba4b001c30bbdd6a600b91d0625470.jpg","name":"足球范儿：我们","rcount":0,"summary":"　为什么中国足球屡战屡败，仍有那么多球迷矢志不渝？\r\n　　球迷是不是很傻很天真？\r\n　　中国足球，真的有那么苦大仇深么？\r\n　　长期以来由于国内足球的尴尬境地，中国足球，乃至中国球迷受到了不少愚弄和误解。一窝蜂的反赌打黑之下，谁来为球迷自身这个群体发出声音？本书的作者是一个球龄16年的老球迷，经历过1996年中国队兵败亚洲杯、1997年国安9∶1上海申花、2000年国安退出中超风波、2001年冲击世界杯、2004年甲A变身中超\u2026\u202615年间北京足球、中国足球所有荣耀的瞬间、阴霾笼罩的时刻、球迷所有的心路历程、不足为外人道的痴心、怪癖。在这个人人都可以批评中国足球的年代，再多的乱相和谩骂也无法遮掩球迷始终赤诚的内心。","time":1438490059000}]
     * page : 1
     * size : 20
     * status : true
     * total : 2642
     * totalpage : 133
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
         * author : 麦玲玲
         * bookclass : 10
         * count : 1633
         * fcount : 0
         * id : 2874
         * img : /book/150802/b09680bea4e81d7e068c0c9d001f0809.jpg
         * name : 麦玲玲2010
         * rcount : 0
         * summary : 　麦玲玲女士根据玄学与风水学，对2010年（虎年）运程进行了全面分析。分别对十二生肖的虎年运程、简易行运风水布局、“个人出生日”运势预测、各行业未来走向、虎年吉日吉时等贴近大众生活工作实际的内容进行了全面细致的分析与讲解。能够给人指点、为人解惑。
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

    public ImageBean() {
    }

    protected ImageBean(Parcel in) {
        this.page = in.readInt();
        this.size = in.readInt();
        this.status = in.readByte() != 0;
        this.total = in.readInt();
        this.totalpage = in.readInt();
        this.list = new ArrayList<ListEntity>();
        in.readList(this.list, List.class.getClassLoader());
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
