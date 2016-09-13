package com.allyn.lives.view.widgets;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allyn.lives.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 拼音搜索右侧竖状字母列表,支持选中高亮背景
 * Created by liukun on 15/6/24.
 */
public class SlideLinerLayout extends LinearLayout {
    public boolean mListViewScrollAble = true;
    private Context mContext;
    public static String[] b = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"};
    private List mList;
    public int choose = -1;// 选中

    private TextView mTextDialog;

    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;

    public SlideLinerLayout(Context context) {
        this(context, null);
    }

    public SlideLinerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mList = Arrays.asList(b);
        mList = new ArrayList(mList);
        setBackgroundColor(Color.argb(0, 0, 0, 0));
        init();
    }

    boolean mIsFirst = true;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        int childCount = getChildCount();
        int height = getHeight() / b.length;
        if (childCount > 0 && height > 0 && mIsFirst) {
            mIsFirst = false;
            for (int i = 0; i < childCount; i++) {
                getChildAt(i).setLayoutParams(new LayoutParams(height, LayoutParams.WRAP_CONTENT, 1.0f));
            }
        }
    }

    private void init() {
        int length = b.length;
        for (int i = 0; i < length; i++) {
            this.addView(generateTextView(b[i]));
        }
    }

    public TextView generateTextView(String text) {
        TextView textView = new TextView(mContext);
        textView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1.0f));
        textView.setText(text);
        textView.setTextSize(12);
        textView.setTextColor(Color.GRAY);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final float y = event.getY();// 点击y坐标
        final int oldChoose = choose;
        final int c = (int) (y / getHeight() * b.length);// 点击y坐标所占总高度的比例*b数组的长度就等于点击b中的个数.
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                mListViewScrollAble = true;
                if (mTextDialog != null && mTextDialog.getVisibility() == View.VISIBLE) {
                    mTextDialog.setVisibility(View.GONE);
                }
                break;
            default:
                mListViewScrollAble = false;
                if (oldChoose != c) {
                    if (c >= 0 && c <= b.length) {
                        if (oldChoose != -1) {
                            TextView oldChoosedView = (TextView) getChildAt(oldChoose);
                            oldChoosedView.setBackgroundResource(R.color.alpha);
                            oldChoosedView.setTextColor(Color.GRAY);
                        }
                        TextView choosedView = (TextView) getChildAt(c);
                        choosedView.setBackgroundResource(R.mipmap.red_dot);
                        choosedView.setTextColor(Color.WHITE);
                        choose = c;
                        if (onTouchingLetterChangedListener != null) {
                            onTouchingLetterChangedListener.onTouchingLetterChanged(b[c]);
                        }
                        mTextDialog.setText(b[c]);
                        mTextDialog.setVisibility(VISIBLE);
                    }
                }
                break;
        }
        return true;
    }

    public void setTextDialog(TextView textDialog) {
        this.mTextDialog = textDialog;
    }

    /**
     * 向外公开的方法
     *
     * @param onTouchingLetterChangedListener
     */
    public void setOnTouchingLetterChangedListener(
            OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }

    /**
     * 接口
     *
     * @author coder
     */
    public interface OnTouchingLetterChangedListener {
        public void onTouchingLetterChanged(String s);
    }

    public void setChoosed(String choosedText) {
        int index = getIndex(choosedText);
        if (index != -1 && choose != index) {
            if (choose != -1) {
                TextView tv = (TextView) getChildAt(choose);
                tv.setBackgroundResource(R.color.alpha);
                tv.setTextColor(Color.GRAY);
            }
            TextView tv = (TextView) getChildAt(index);
            if (tv != null) {
                tv.setBackgroundResource(R.mipmap.red_dot);
                tv.setTextColor(Color.WHITE);
                choose = index;
            }
        }
    }

    private int getIndex(String text) {
        return mList.indexOf(text);
    }

}