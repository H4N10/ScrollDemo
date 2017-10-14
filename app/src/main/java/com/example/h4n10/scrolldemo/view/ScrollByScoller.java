package com.example.h4n10.scrolldemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by zc on 2017/10/9.
 *
 * @function Scroll 侧滑菜单
 */

public class ScrollByScoller extends LinearLayout {

    View menuView ; //可滑动的view
    View contentView; // 主要内容
    int lastX = 0;
    //判断横滑还是竖滑
    int xStart = 0;
    int xEnd = 0;
    int yStart = 0;
    int yEnd = 0;
    private int width ;
    private int height ;
    public ScrollByScoller(Context context) {
        super(context);
    }

    public ScrollByScoller(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public ScrollByScoller(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        menuView = getChildAt(0);
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(width+menuView.getWidth(),MeasureSpec.EXACTLY);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        Log.e("测量后",menuView.getWidth()+"");

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //侧滑菜单项
        menuView = getChildAt(0);
        //主菜单项
        contentView = getChildAt(1);
        if(menuView !=null){
            menuView.layout(-menuView.getWidth(),0,0,getHeight());
        }
        if(contentView !=null){
            contentView.layout(0,0,contentView.getWidth(),getHeight());
        }
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
            return true;
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x-lastX;
                int scrollX = getScrollX() - offsetX;
                if(scrollX > 0){
                    scrollTo(0,0);
                }else if( scrollX > -menuView.getWidth()   ){
                    scrollBy(-offsetX,0);
                }else if(scrollX <= -menuView.getWidth()  ){
                    scrollTo(-menuView.getWidth(),0);
                }
                lastX = x;
                break;
            case MotionEvent.ACTION_UP:
                if(Math.abs(getScrollX())>150){
                    scrollTo(-menuView.getWidth(),0);
                }else{
                    scrollTo(0,0);
                }
                break;
        }
        return true;
    }

}
