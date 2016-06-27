package com.allyn.lives.fragment.video;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.allyn.lives.R;
import com.allyn.lives.fragment.base.BaseFragment;

/**
 * Created by Administrator on 2016/3/22.
 */
public class TVFragment extends BaseFragment {

    public static TVFragment newInstance() {
        TVFragment fragment = new TVFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tv_fragment_main, container, false);
        TextView textView = (TextView) v.findViewById(R.id.tvHtml);
        String html = "<html><head><title>TextView使用HTML</title></head><body><p><strong>强调</strong></p><p><em>斜体</em></p>"
                + "<p><a href=\"http://www.dreamdu.com/xhtml/\">超链接HTML入门</a>学习HTML!</p><p><font color=\"#aabb00\">颜色1"
                + "</p><p><font color=\"#00bbaa\">颜色2</p><h1>标题1</h1><h3>标题2</h3><h6>标题3</h6><p>大于>小于<</p><p>" +
                "下面是网络图片</p><img src=\"http://avatar.csdn.net/0/3/8/2_zhang957411207.jpg\"/></body></html>";
        String mg = "<div> \\n <div></div> \\n <p></p> \\n <p>　　再婚，是指夫妻离婚或者一方死亡（包括生理死亡和宣告死亡），或者被宣告婚姻无效，在原来婚姻关系消灭以后，又与他人另行结婚。它是以前一次的婚姻关系结束为前提。对再婚者来说，前一次的婚姻，不管是离婚还是丧偶，都是婚姻生活的不幸。婚姻法第19条规定，夫妻双方可以约定婚姻关系存续期间所得的财产以及婚前财产归各自所有、共同所有或者是部分各自所有、部分共同所有。约定应当采用书面形式。</p> \\n <p>　　再婚涉及的财产问题比较复杂。有的涉及前婚的子女抚养问题，有的涉及年老后关于自己的赡养问题，还有的涉及对自己的遗产继承问题等等。有些老年人再婚受到子女阻拦，在很大程度上，是子女怕父亲或者母亲的财产被另一方骗走，失去了老年时的生活经济基础。再婚失败较多的现象提醒人们，如果再婚，最好按照婚姻法的规定，对各自现有的财产以及婚后共同取得的财产，事先有个明确约定。年轻一点的再婚者，对自己抚养前婚子女的问题、对赡养自己父母的问题、对自己手中现有财产等问题，最好与对方有个约定；年老一点的再婚者，除了对自己手中现有的财产、对自己年老的赡养等问题有个约定以外，甚至还应该约定，如果一方去世了，另一方对财产应当如何继承、占有等等。对可能出现争议、产生纠纷的问题，事先想到，用书面的方式加以约定，这对促进再婚家庭的巩固，防止日后发生纷争，以及对双方子女、亲属的往来，都是大有好处的。</p> \\n <p>　　再婚登记好有助婚姻牢</p> \\n <p>　　结婚、复婚、再婚，这三个法学术语尽管不完全一样，但也有相同之处，这就是男女双方都应当遵守婚姻法关于结婚原则和结婚条件的规定，双方应当亲自到婚姻登记机关去履行结婚登记手续，从法律上确认夫妻关系，从而使婚姻关系受国家法律保护。至于是否举行婚礼，这完全由双方当事人自愿。</p> \\n <p>　　目前，有些再婚者，特别是年龄大一些的再婚者，不履行结婚登记手续，两人完全处于一种试婚状态，像临时搭伙过日子一样，能过多长时间就过多长时间，如果因为性格不合等原因，说分手就分手，免得去办理离婚手续。当事人只看到了省事的一面，可是没有看到这种非法婚姻关系会带来许多麻烦的另一面。</p> \\n <p>　　例如有一对老年人再婚，女方嫁到了男方家之后，俩人在一起生活了5年，本来各方面条件都很好，他们也曾经想过要补办登记手续，但考虑到已经生活挺长时间了，不登记也照常可以在一起生活，也就一直没有登记。一日，男人突然因患脑血栓住进医院，其配偶只好在身旁照顾，5个月后，老头儿死亡，于是婚姻关系结束。这时，老头儿的亲生子女赶老太太走，让她让出房屋，说这房子是老头儿的，老头儿的子女有继承权，而老太太因为没有和老头儿履行结婚登记手续，他们是非法婚姻关系，不存在继承遗产问题。老太太认为，自己与老头儿已经在一起生活5年，而且在老头儿患病、住院期间，是她精心照料的。老头儿一死就赶她走，这不合情理。这种现象，确实在情理上讲不通，但在法律上就能讲得通。因为没有履行结婚登记手续的婚姻是非法婚姻，男女双方确实没有互相继承遗产的权利。</p> \\n <p>　　有的再婚者担心，履行了再婚登记手续，再婚后，万一婚姻不长久，会在财产分割上带来麻烦。其实，这种担心没有必要。婚姻法第19条规定，夫妻对财产可以约定。再婚时，可以用书面形式对财产进行约定。这种约定，既可以在婚前约定，也可以在再婚后约定，当然，约定后双方还可以修改。这样，就可以避免一些分割财产方面的争议。</p> \\n <p>　　到婚姻登记机关办理登记手续，其好处是多方面的，不仅是婚姻关系合法有效，受国家法律保护，而且登记机关将按照婚姻法和有关法律的规定，对双方当事人是否具备结婚条件进行审查。这样，可以防止不具备结婚条件的人与他人再婚。如：对方如果与他人的婚姻关系没有解除，不能与他人再婚，这可以防止重婚或者他人骗婚现象的发生等等。履行结婚登记手续，有利于保护婚姻当事人的合法权益。</p> \\n <p></p> \\n</div>";
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());//滚动
        textView.setText(Html.fromHtml(html + mg));
        return v;
    }
}
