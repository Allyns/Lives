package com.allyn.lives.view.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.allyn.lives.R;

/**
 * Created by Administrator on 2016/7/7.
 */
public class CircleImageView extends ImageView {
    private static final Xfermode MASK_XFERMODE;
    private Bitmap mask;
    private Paint paint;
    private int mBorderWidth = 1;
    private int mBorderColor = Color.parseColor("#f2f2f2");
    private boolean useDefaultStyle = false;

    static {
        PorterDuff.Mode localMode = PorterDuff.Mode.DST_IN;
        MASK_XFERMODE = new PorterDuffXfermode(localMode);
    }

    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircularImage);
        mBorderColor = a.getColor(R.styleable.CircularImage_border_color, mBorderColor);
        final int def = (int) (2 * context.getResources().getDisplayMetrics().density + 0.5f);
        mBorderWidth = a.getDimensionPixelOffset(R.styleable.CircularImage_border_width, def);
        a.recycle();
    }

    private void useDefaultStyle(boolean useDefaultStyle) {
        this.useDefaultStyle = useDefaultStyle;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (useDefaultStyle) {
            super.onDraw(canvas);
            return;
        }
        final Drawable localDraw = getDrawable();
        if (localDraw == null) {
            return;
        }
        if (localDraw instanceof XmlResourceParser) {
            return;
        }
        if (this.paint == null) {
            final Paint localPaint = new Paint();
            localPaint.setFilterBitmap(false);
            localPaint.setAntiAlias(true);
            localPaint.setXfermode(MASK_XFERMODE);
            this.paint = localPaint;
        }
        final int width = getWidth();
        final int height = getHeight();
        /** 保存layer */
        int layer = canvas.saveLayer(0.0F, 0.0F, width, height, null, 31);
        /** 设置drawable的大小 */
        localDraw.setBounds(0, 0, width, height);
        /** 将drawable绑定到bitmap(this.mask)上面（drawable只能通过bitmap显示出来） */
        localDraw.draw(canvas);
        if ((this.mask == null) || (this.mask.isRecycled())) {
            this.mask = createOvalBitmap(width, height);
        }
        /** 将bitmap画到canvas上面 */
        canvas.drawBitmap(this.mask, 0.0F, 0.0F, this.paint);
        /** 将画布复制到layer上 */
        canvas.restoreToCount(layer);
        drawBorder(canvas, width, height);
    }

    /**
     * 绘制圆形边框
     */
    private void drawBorder(Canvas canvas, final int width, final int height) {
        if (mBorderWidth == 0) {
            return;
        }
        final Paint mBorderPaint = new Paint();
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setColor(mBorderColor);
        mBorderPaint.setStrokeWidth(mBorderWidth);
        canvas.drawCircle(width / 2, height / 2, (width - mBorderWidth) / 2, mBorderPaint);
        canvas = null;
    }

    public Bitmap createOvalBitmap(final int width, final int height) {
        Bitmap.Config localConfig = Bitmap.Config.ARGB_8888;
        Bitmap localBitmap = Bitmap.createBitmap(width, height, localConfig);
        Canvas localCanvas = new Canvas(localBitmap);
        Paint localPaint = new Paint();
        final int padding = (mBorderWidth - 3) > 0 ? mBorderWidth - 3 : 1;
        /**
         * 设置椭圆的大小(因为椭圆的最外边会和border的最外边重合的，如果图片最外边的颜色很深，有看出有棱边的效果，所以为了让体验更加好，
         * 让其缩进padding px)
         */
        RectF localRectF = new RectF(padding, padding, width - padding, height - padding);
        localCanvas.drawOval(localRectF, localPaint);

        return localBitmap;
    }

}