package com.example.h4n10.scrolldemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by zc on 2017/10/9.
 *
 * @function 滑动 by  offsetLeftAndRight()与offsetTopAndBottom()
 */

public class ScrollByOffset extends Button {
    int lastX = 0;
    int lastY = 0;
    public ScrollByOffset(Context context) {
        super(context);
    }

    public ScrollByOffset(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollByOffset(Context context, AttributeSet attrs, int defStyleAttr) {
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
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;

                offsetLeftAndRight(offsetX);
                offsetTopAndBottom(offsetY);
                break;
        }
        return true;
    }
}
