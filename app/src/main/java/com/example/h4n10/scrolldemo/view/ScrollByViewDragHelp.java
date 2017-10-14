package com.example.h4n10.scrolldemo.view;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by zc on 2017/10/13.
 *
 * @function
 */

public class ScrollByViewDragHelp extends FrameLayout {

    private int width ;
    private int height ;
    View menuView ; //可滑动的view
    View contentView; // 主要内容
    //判断横滑还是竖滑
    int xStart = 0;
    int xEnd = 0;
    int yStart = 0;
    int yEnd = 0;

    private ViewDragHelper mDragger;

    public ScrollByViewDragHelp(Context context) {
        super(context);
    }

    public ScrollByViewDragHelp(final Context context, AttributeSet attrs) {
        super(context, attrs);
        mDragger = ViewDragHelper.create(this,1.0f,new ViewDragHelper.Callback(){

            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child == contentView;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                if(left < 0)
                    left = 0;
                return left > menuView.getWidth() ? menuView.getWidth():left;
            }
        });

    }

    public ScrollByViewDragHelp(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        menuView = getChildAt(0);
        contentView = getChildAt(1);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                xStart = (int)ev.getX();
                yStart = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                xEnd = (int)ev.getX();
                yEnd = (int)ev.getY();
                break;
        }
        if(Math.abs(xEnd-xStart) > Math.abs(yEnd-yStart))
            return mDragger.shouldInterceptTouchEvent(ev);
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragger.processTouchEvent(event);
        return true;
    }
}
