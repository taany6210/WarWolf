package com.ty.warwolf.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.ty.warwolf.R;
import com.ty.warwolf.listener.PtrRefreshListener;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * @ 文件名:   TRefreshGif
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午2:36
 * @ 描述:
 */

public class TRefreshGif extends FrameLayout implements PtrUIHandler {

    private PtrRefreshListener mRefreshListener;
    private GifView mGifView;

    public TRefreshGif(Context context) {
        this(context, null);
    }

    public TRefreshGif(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TRefreshGif(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        View header = LayoutInflater.from(getContext()).inflate(R.layout.gif_refresh_header, this, true);
        mGifView = ((GifView) header.findViewById(R.id.gif_view));
        mGifView.setPaused(true);

    }


    @Override
    public void onUIReset(PtrFrameLayout frame) {
        mGifView.setPaused(true);
        if (mRefreshListener != null) {
            mRefreshListener.onPullReset();
        }

    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        if (mRefreshListener != null) {
            mRefreshListener.onPullStart();
        }

    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        mGifView.setPaused(false);
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        mGifView.setPaused(true);
    }



    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
    }

    public void setRefreshListener(PtrRefreshListener refreshListener) {
        mRefreshListener = refreshListener;
    }
}
