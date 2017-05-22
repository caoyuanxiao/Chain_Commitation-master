/*
package com.chinacreator.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.chinacreator.R;

import java.util.Random;

*/
/**
 * Created by Smile on 2017/5/21.
 *//*


public class CustomView1 extends View {

    */
/**
     * 文本
     *//*

    private String mTitleText;
    */
/**
     * 文本的颜色
     *//*

    private int mTitleTextColor;
    */
/**
     * 文本的大小
     *//*

    private int mTitleTextSize;

    */
/**
     * 绘制时控制文本绘制的范围
     *//*

    private Rect mBound;
    private Paint mPaint;

    public CustomView1(Context context) {
        this(context, null);
    }

    public CustomView1(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取我们在布局文件中设置的 自定义属性设置的值。
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyImageView, defStyleAttr, 0);
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.CustomView1_titleText:
                    mTitleText = typedArray.getString(i);
                    break;
                case R.styleable.CustomView1_titleTextSize:
                    mTitleTextSize = (int) typedArray.getDimension(i, 0);
                    break;
                case R.styleable.CustomView1_titleTextColor:
                    //注意必须是使用tyoedArray.getColor才可以设置颜色
                    mTitleTextColor = typedArray.getColor(i, getResources().getColor(R.color.colorPrimary));
                    mTitleTextColor = getResources().getColor(R.color.colorAccent);
                    break;
            }
        }
        typedArray.recycle();

        */
/**
         * 获得绘制文本的宽和高
         *//*

        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        mPaint.setColor(mTitleTextColor);
        mBound = new Rect();
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTitleText= String.valueOf(new Random().nextInt(1000));
                mTitleTextColor=getResources().getColor(R.color.colorPrimary);
                postInvalidate();
            }
        });
    }

    */
/**
     * 系统帮我们测量的高度和宽度都是MATCH_PARNET，当我们设置明确的宽度和高度时，系统帮我们测量的结果就是我们设置的结果，
     * 当我们设置为WRAP_CONTENT,或者MATCH_PARENT系统帮我们测量的结果就是MATCH_PARENT的长度。
     * EXACTLY：一般是设置了明确的值或者是MATCH_PARENT
     * AT_MOST：表示子布局限制在一个最大值内，一般为WARP_CONTENT
     * UNSPECIFIED：表示子布局想要多大就多大，很少使用
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     *//*

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthmode = MeasureSpec.getMode(widthMeasureSpec);
        int widthsize = MeasureSpec.getSize(widthMeasureSpec);
        int heightmode = MeasureSpec.getMode(heightMeasureSpec);
        int heightsize = MeasureSpec.getSize(heightMeasureSpec);
        int width = 0;
        int height = 0;

        if (widthmode == MeasureSpec.EXACTLY) {
            width = widthsize;
        } else {

            float textWidth = mBound.width();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }



        if (heightmode == MeasureSpec.EXACTLY) {
            height = heightsize;
        } else {
            float textheight = mBound.height();
            int desired = (int) (getPaddingBottom() + textheight + getPaddingTop());
            height = desired;
        }
        setMeasuredDimension(width, height);
        // super.onMeasure(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setColor(mTitleTextColor);
        canvas.drawText(mTitleText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
    }
}
*/
