package com.android.byc.hello;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    /**
     * RecyclerView 的布局方向，默认先赋值为纵向布局
     * RecyclerView的布局可以横向也可以纵向
     * 横向纵向对应的分隔线画法不一样
     */
    private int mOrientation = LinearLayoutManager.VERTICAL;
    private Drawable mDivider;
    private int mItemSize = 1;//item之间分隔线的size，默认为1

    private Paint mPaint; //绘制item分隔线的画笔，并设置其属性

    public DividerItemDecoration(Context context) {
        this(context, LinearLayoutManager.VERTICAL);
    }

    public DividerItemDecoration(Context context, int orientation) {
        this(context, orientation, R.color.colorAccent);
    }

    public DividerItemDecoration(Context context, int orientation, int dividerColor) {
        this(context, orientation, dividerColor, 1);
    }

    /**
     * context
     * orientation 绘制方向
     * dividerColor 分隔线颜色，颜色资源id
     * mItemSize 分隔线宽度，传入dp值就行
     */
    public DividerItemDecoration(Context context, int orientation, int dividerColor, int mItemSize) {
        this.mOrientation = orientation;
        if (orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL) {
            throw new IllegalArgumentException("请传入正确的参数");
        }
        /**把dp值换算成px
         this.mItemSize = 1;*/
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPaint.setColor(context.getResources().getColor(dividerColor));
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    /**
     * 绘制纵向item分隔线
     */
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getMeasuredWidth() - parent.getPaddingRight();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams =
                    (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + layoutParams.bottomMargin;
            final int bottom = top + mItemSize;
            canvas.drawRect(left, top, right, bottom, mPaint);

        }
    }

    /**
     * 绘制横向item分隔线
     */
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams =
                    (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + layoutParams.rightMargin;
            final int right = left + mItemSize;
            canvas.drawRect(left, top, right, bottom, mPaint);

        }
    }

    /**
     * 设置item分隔线的size
     * outRect
     * view
     * parent
     * state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        if (mOrientation ==
                LinearLayoutManager.VERTICAL) {
            outRect.set(0, 0, 0, mItemSize);
            //垂直排列，底部偏移
        } else {
            outRect.set(0, 0, mItemSize, 0);
            //水平排列，右边偏移
        }
    }
}/*
 public void setDrawable(@NonNull Drawable drawable){
        if(drawable == null){
            throw new IllegalArgumentException("Drawable cannot be null.");
        }
        mDivider=drawable;
    }
    DividerItemDecoration divider = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
    divider.setDrawable(ContextCompat.getDrawable(this,R.drawable.custom_divider));
    RecyclerView.addItemDecoration(divider);
    */
