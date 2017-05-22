package com.chinacreator.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.chinacreator.R;

/**
 * Created by Smile on 2017/5/21.
 */

public class MyImageView extends View {
    /**
     * 文本
     */
    private String mTitleText;
    /**
     * 文本的颜色
     */
    private int mTitleTextColor;
    /**
     * 文本的大小
     */
    private int mTitleTextSize;

    private Rect mBund;

    private Rect mRect;

    private int mImageviewType;

    private Bitmap mImageResouce;

    private Paint mPaint;

    int mWidth;
    int mHeight;


    public MyImageView(Context context) {
        this(context, null);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyImageView, defStyleAttr, 0);
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.MyImageView_titleText:
                    mTitleText = typedArray.getString(i);
                    break;
                case R.styleable.MyImageView_titleTextSize:
                    mTitleTextSize = (int) typedArray.getDimension(i, 0);
                    break;
                case R.styleable.MyImageView_titleTextColor:
                    //注意必须是使用tyoedArray.getColor才可以设置颜色
                    mTitleTextColor = typedArray.getColor(i, getResources().getColor(R.color.colorPrimary));
                    mTitleTextColor = getResources().getColor(R.color.colorAccent);
                    break;
                case R.styleable.MyImageView_imageScr:
                    mImageResouce = BitmapFactory.decodeResource(getResources(), typedArray.getResourceId(i, defStyleAttr));
                    break;
                case R.styleable.MyImageView_imageScaleType:
                    mImageviewType = typedArray.getInt(i, defStyleAttr);
                    break;
            }
        }
        //需要把
        typedArray.recycle();

        //需要设置涂料
        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setColor(mTitleTextColor);
        mPaint.setTextSize(mTitleTextSize);
        //设置text的形状
        mBund = new Rect();
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBund);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //设置宽度
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //需要比较文字宽度和图片宽度那个比较大 取最大的
            int textWidth = mTitleText.length() + getPaddingRight() + getPaddingLeft();
            int imageWidth = mImageResouce.getWidth() + getPaddingLeft() + getPaddingRight();
            mWidth = Math.max(textWidth, imageWidth);
            mWidth = Math.min(mWidth, widthSize);
        }

        //设置高度
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //需要比较文字宽度和图片宽度那个比较大 取最大的
            int textHeight = mBund.height() + getPaddingBottom() + getPaddingTop();
            int imageHeight = mImageResouce.getHeight() + getPaddingBottom() + getPaddingTop();
            mHeight = textHeight + imageHeight;
            mHeight = Math.min(mHeight, heightSize);
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //设置边框
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0, 0, mWidth, mHeight, mPaint);

        //设置中间的Rect的大小
        mRect.left = getPaddingLeft();
        mRect.right = mWidth - getPaddingRight();
        mRect.top = getPaddingTop();
        mRect.bottom = mHeight - getPaddingBottom();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);

        //设置的高度小于字体的宽度
        if (mBund.width() > mWidth) {
            TextPaint paint = new TextPaint(mPaint);
            String msg = TextUtils.ellipsize(mTitleText, paint, (float) mWidth - getPaddingLeft() - getPaddingRight(),
                    TextUtils.TruncateAt.END).toString();
            canvas.drawText(msg, getPaddingLeft(), mHeight - getPaddingBottom(), mPaint);
        } else {
            //设置字体居中
            canvas.drawText(mTitleText, getWidth() / 2 - mBund.width() / 2, mHeight - getPaddingBottom(), mPaint);
        }

        //取消使用掉的快  设置mRect块去掉Textview代码块之后剩下的buttom所在的位置
        mRect.bottom -= mBund.height();

        if (mImageviewType == 0) {
            canvas.drawBitmap(mImageResouce, null, mRect, mPaint);
        } else {
            //计算居中的矩形范围
            mRect.left = mWidth / 2 - mImageResouce.getWidth() / 2;
            mRect.right = mWidth / 2 + mImageResouce.getWidth() / 2;
            mRect.top = (mHeight - mBund.height()) / 2 - mImageResouce.getHeight() / 2;
            mRect.bottom = (mHeight - mBund.height()) / 2 + mImageResouce.getHeight() / 2;

            canvas.drawBitmap(mImageResouce, null, mRect, mPaint);
        }

        //设置Imageview

        /*mPaint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        canvas.drawBitmap(mImageResouce, getWidth() / 2 - mImageResouce.getWidth() / 2, getHeight() / 2 - mImageResouce.getHeight() / 2, mPaint);

        mPaint.setColor(mTitleTextColor);
        canvas.drawText(mTitleText, getWidth() / 2 - mBund.width() / 2, getHeight() / 2 - mBund.height() / 2, mPaint);*/
    }
}
