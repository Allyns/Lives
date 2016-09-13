package com.allyn.lives.view.widgets;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.allyn.lives.R;

/**
 * "订阅"界面ListView,支持下拉刷新,上拉加载
 * Created by liukun on 15/6/17.
 */
public class RefreshListView extends ListView {

    private final int DOWN_PULL_REFRESH = 0; // 下拉刷新
    private final int RELEASE_REFRESH = 1; // 松开刷新
    private final int REFRESH_ING = 2; // 正在刷新中
    private final int UP_PULL_LOAD_MORE = 3; // 上拉加载
    private final int RELEASE_LOAD_MORE = 4; // 松开加载
    private final int LOAD_MORE_ING = 5; // 正在加载中
    private Context mContext;
    private View headView;
    private ImageView header_arrow;
    private TextView header_hint_textview;
    private ProgressBar header_progressbar;
    private TextView header_time;
    private View footView;
    private ImageView foot_arrow;
    private TextView foot_hint_textview;
    private ProgressBar foot_progressbar;
    private int headViewHeight;
    private int footViewHeight;
    private Animation upHeaderAnimation; // 向上旋转的动画
    private Animation downHeaderAnimation; // 向下旋转的动画
    private Animation upFooterAnimation; // 向下旋转的动画
    private Animation downFooterAnimation; // 向上旋转的动画
    private int headCurrentState = DOWN_PULL_REFRESH; // 头布局的状态: 默认为下拉刷新状态
    private boolean isWaitRefreshAction = false;
    private int footCurrentState = UP_PULL_LOAD_MORE; // 尾布局的状态: 默认为上拉加载
    private boolean isWaitLoadMoreAction = false;
    private boolean loadMoreAble = true; //是否上拉加载
    private boolean refreshAble = true; //是否下拉刷新
    private int mTouchSlop;

    private int downY;

    private String mUserClassSimpleName;

    private OnRefreshListener mOnRefreshListener;

    private OnDownListener onDownListener;
    private boolean needLoadMore = true;

    public RefreshListView(Context context) {
        this(context, null);
    }


    public RefreshListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        initView();
    }

    private void initView() {
        //this.setOnItemLongClickListener(this);
        initHeadView();
        initFootView();
    }

    private void initHeadView() {
        headView = View.inflate(mContext, R.layout.common_list_view_head_view, null);
        header_arrow = (ImageView) headView.findViewById(R.id.header_arrow);
        header_hint_textview = (TextView) headView.findViewById(R.id.header_hint_textview);
        header_progressbar = (ProgressBar) headView.findViewById(R.id.header_progressbar);
        header_time = (TextView) headView.findViewById(R.id.header_time);
        header_time.setText(getLastRefreshDate());

        measureItem(headView);
        headViewHeight = headView.getMeasuredHeight();
        headView.setPadding(0, -headViewHeight, 0, 0);
        addHeaderView(headView);
        initAnimation();
    }

    private void initAnimation() {
        // 头部
        upHeaderAnimation = new RotateAnimation(0f, -180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        upHeaderAnimation.setDuration(500);
        upHeaderAnimation.setFillAfter(true); // 动画结束后, 停留在结束的位置上

        downHeaderAnimation = new RotateAnimation(-180f, -360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        downHeaderAnimation.setDuration(500);
        downHeaderAnimation.setFillAfter(true); // 动画结束后, 停留在结束的位置上

        // 底部
        upFooterAnimation = new RotateAnimation(-180f, -360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        upFooterAnimation.setDuration(500);
        upFooterAnimation.setFillAfter(true); // 动画结束后, 停留在结束的位置上

        downFooterAnimation = new RotateAnimation(0, -180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        downFooterAnimation.setDuration(500);
        downFooterAnimation.setFillAfter(true); // 动画结束后, 停留在结束的位置上
    }

    private void initFootView() {
        footView = View.inflate(mContext, R.layout.common_list_view_foot_view, null);
        foot_arrow = (ImageView) footView.findViewById(R.id.foot_arrow);
        foot_hint_textview = (TextView) footView.findViewById(R.id.foot_hint_textview);
        foot_progressbar = (ProgressBar) footView.findViewById(R.id.foot_progressbar);

        measureItem(footView);
        footViewHeight = footView.getMeasuredHeight();
        footView.setPadding(0, 0, 0, -footViewHeight);
        addFooterView(footView);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = (int) ev.getY();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (getLastVisiblePosition() == getCount() - 1) {
                    int screenHeight = getHeight();
                    View lastItem = getChildAt(getLastVisiblePosition() - getFirstVisiblePosition());
                    if (lastItem != null) {
                        int foot = (int) lastItem.getY();
                        if (screenHeight != foot) {
                            needLoadMore = false;
                        }
                    }
                }
                downY = (int) ev.getY();

                if (onDownListener != null)
                    onDownListener.onDownListener();

                break;
            case MotionEvent.ACTION_MOVE:
                super.onTouchEvent(ev);
                int moveY = (int) ev.getY();

                int headDiff = (moveY - downY);
                if (Math.abs(headDiff) > mTouchSlop) {
                    int headPaddingTop = -headViewHeight + headDiff / 2;

                    int footDiff = (downY - moveY);
                    int footPaddingBottom = -footViewHeight + footDiff / 2;

                    if (getFirstVisiblePosition() == 0 && -headViewHeight < headPaddingTop && headCurrentState != REFRESH_ING && refreshAble) {
                        if (headPaddingTop > headViewHeight / 4 && headCurrentState == DOWN_PULL_REFRESH) {
                            headCurrentState = RELEASE_REFRESH;
                            refreshHeadView();
                        } else if (headPaddingTop < headViewHeight / 4 && headCurrentState == RELEASE_REFRESH) {
                            headCurrentState = DOWN_PULL_REFRESH;
                            refreshHeadView();
                        }

                        isWaitRefreshAction = true;
                        header_time.setText(getLastRefreshDate());
                        headView.setPadding(0, headPaddingTop, 0, 0);
                        return true;
                    }
                    if (getLastVisiblePosition() == getCount() - 1 && -footViewHeight < footPaddingBottom && footCurrentState != LOAD_MORE_ING && needLoadMore && loadMoreAble) {

                        if (footPaddingBottom > footViewHeight / 4 && footCurrentState == UP_PULL_LOAD_MORE) {
                            footCurrentState = RELEASE_LOAD_MORE;
                            refreshFootView();
                        } else if (footPaddingBottom < footViewHeight / 4 && footCurrentState == RELEASE_LOAD_MORE) {
                            footCurrentState = UP_PULL_LOAD_MORE;
                            refreshFootView();
                        }

                        isWaitLoadMoreAction = true;
                        footView.setPadding(0, 0, 0, footPaddingBottom);

                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                super.onTouchEvent(ev);

                if (headCurrentState == RELEASE_REFRESH) {
                    headView.setPadding(0, 0, 0, 0);
                    headCurrentState = REFRESH_ING;
                    refreshHeadView();
                    if (mOnRefreshListener != null) {
                        mOnRefreshListener.onPullRefresh();
                    }
                    return true;
                } else if (headCurrentState == DOWN_PULL_REFRESH && isWaitRefreshAction) {
                    isWaitRefreshAction = false;
                    headView.setPadding(0, -headViewHeight, 0, 0);
                    return true;
                } else if (footCurrentState == RELEASE_LOAD_MORE) {
                    footView.setPadding(0, 0, 0, 0);
                    footCurrentState = LOAD_MORE_ING;
                    refreshFootView();

                    if (mOnRefreshListener != null) {
                        mOnRefreshListener.onPullLoadMore();
                    }
                } else if (footCurrentState == UP_PULL_LOAD_MORE && isWaitLoadMoreAction) {
                    isWaitLoadMoreAction = false;
                    footView.setPadding(0, 0, 0, -footViewHeight);
                }
                needLoadMore = true;
                break;
        }

        return super.onTouchEvent(ev);
    }

    private void refreshHeadView() {
        switch (headCurrentState) {
            case DOWN_PULL_REFRESH: // 下拉刷新状态
                header_hint_textview.setText(R.string.header_hint_normal);
                header_arrow.startAnimation(downHeaderAnimation); // 执行向下旋转
                break;
            case RELEASE_REFRESH: // 松开刷新状态
                header_hint_textview.setText(R.string.header_hint_ready);
                header_arrow.startAnimation(upHeaderAnimation); // 执行向上旋转
                break;
            case REFRESH_ING: // 正在刷新中状态
                header_hint_textview.setText(R.string.header_hint_loading);
                header_arrow.setVisibility(View.INVISIBLE);
                header_progressbar.setVisibility(View.VISIBLE);
                header_arrow.clearAnimation();
                break;
        }
    }

    private void refreshFootView() {
        switch (footCurrentState) {
            case UP_PULL_LOAD_MORE:
                foot_hint_textview.setText(R.string.footer_hint_normal);
                foot_arrow.startAnimation(upFooterAnimation);
                break;
            case RELEASE_LOAD_MORE:
                foot_hint_textview.setText(R.string.footer_hint_ready);
                foot_arrow.startAnimation(downFooterAnimation);
                break;
            case LOAD_MORE_ING:
                foot_hint_textview.setText(R.string.footer_hint_loadmoreing);
                foot_arrow.setVisibility(View.INVISIBLE);
                foot_progressbar.setVisibility(View.VISIBLE);
                foot_arrow.clearAnimation();
                break;
        }
    }

    /**
     * 隐藏头布局
     */
    public void hideHeadView() {
        headView.setPadding(0, -headViewHeight, 0, 0);
        headCurrentState = DOWN_PULL_REFRESH;
        header_arrow.setVisibility(View.VISIBLE);
        header_progressbar.setVisibility(View.INVISIBLE);
        header_hint_textview.setText(R.string.header_hint_normal);
        if (!TextUtils.isEmpty(mUserClassSimpleName))
            CommonRefreshDateUtils.saveLastRefreshDate(mContext, mUserClassSimpleName);

    }

    /**
     * 隐藏尾布局
     */
    public void hideFootView() {
        footView.setPadding(0, 0, 0, -footViewHeight);
        footCurrentState = UP_PULL_LOAD_MORE;
        foot_arrow.setVisibility(View.VISIBLE);
        foot_progressbar.setVisibility(View.INVISIBLE);
        foot_hint_textview.setText(R.string.footer_hint_normal);
    }


    /**
     * 获取最后更新时间
     *
     * @return
     */
    private String getLastRefreshDate() {
        return CommonRefreshDateUtils.getLastRefreshDate(mContext, TextUtils.isEmpty(mUserClassSimpleName) ? "" : mUserClassSimpleName);
    }

    private void measureItem(View child) {
        ViewGroup.LayoutParams lp = child.getLayoutParams();
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        // 根据child控件的width属性得到宽度的测量规格
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0, lp.width);
        int lpHeight = lp.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            // 根据精确的高度值来得到一个高度测量的规格
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.EXACTLY);
        } else {
            // 没有高度, 需要得到一个高度的测量规格
            childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }
        // 根据给定的测量规格去测量child的宽和高
        child.measure(childWidthSpec, childHeightSpec);
    }

    public void setUserClassSimpleName(String userClassSimpleName) {
        this.mUserClassSimpleName = userClassSimpleName;
    }

    /*public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return true;
    }*/

    public void setRefreshListener(OnRefreshListener listener) {
        if (listener != null) {
            this.mOnRefreshListener = listener;
        }
    }

    public void setOnDownListener(OnDownListener onDownListener) {
        this.onDownListener = onDownListener;
    }

    /**
     * 设置是否可以上拉加载
     *
     * @param loadMoreAble
     */
    public void setLoadMoreAble(boolean loadMoreAble) {
        this.loadMoreAble = loadMoreAble;
    }

    /**
     * 设置是否可以下拉刷新
     *
     * @param refreshAble
     */
    public void setRefreshAble(boolean refreshAble) {
        this.refreshAble = refreshAble;
    }

    public interface OnRefreshListener {
        void onPullRefresh();

        void onPullLoadMore();
    }

    public interface OnDownListener {
        void onDownListener();
    }

}
