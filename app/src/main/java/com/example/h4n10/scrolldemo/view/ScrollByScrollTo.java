package com.example.h4n10.scrolldemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * Created by zc on 2017/10/9.
 *
 * @function 滑动 by scollTo与scollBy
 */

public class ScrollByScrollTo extends Button {

    int lastX = 0;
    int lastY = 0;

    public ScrollByScrollTo(Context context) {
        super(context);
    }

    public ScrollByScrollTo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollByScrollTo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();


        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                Log.e("移动前",getLeft()+";"+getX());
                break;
            case MotionEvent.ACTION_MOVE:
//                int offsetX = x - lastX;
//                int offsetY = y - lastY;
//                ((View)getParent()).scrollBy(-offsetX,-offsetY);
                break;
            case MotionEvent.ACTION_UP:
                ((View)getParent()).scrollBy(0-100,0-100);
                break;

        }
        return true;
    }
}
