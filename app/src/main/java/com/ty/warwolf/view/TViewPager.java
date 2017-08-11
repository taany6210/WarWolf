package com.ty.warwolf.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @ 文件名:   TViewPager
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午2:54
 * @ 描述:
 */

public class TViewPager extends LazyViewPager {

    private boolean scroll = true;

    public TViewPager(Context context) {
        super(context);
    }

    public TViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScroll(boolean scroll) {
        this.scroll = scroll;
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (scroll)
            return false;
        else
            return super.onTouchEvent(arg0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (scroll)
            return false;
        else
            return super.onInterceptTouchEvent(arg0);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }
}
