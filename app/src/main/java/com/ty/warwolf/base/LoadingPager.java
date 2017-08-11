package com.ty.warwolf.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.ty.warwolf.R;
import com.ty.warwolf.databinding.PageLoadingBinding;
import com.ty.warwolf.databinding.PagerEmptyBinding;
import com.ty.warwolf.databinding.PagerErrorBinding;
import com.ty.warwolf.listener.DataLoadListener;
import com.ty.warwolf.util.UIUtil;
import com.ty.warwolf.view.AVLoadingIndicatorView;

/**
 * @ 文件名:   LoadingPager
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 上午10:34
 * @ 描述:
 */

public abstract class LoadingPager extends FrameLayout implements DataLoadListener, View.OnClickListener {
    /**
     * 无状态
     */
    private static final int STATE_NONE = -1;
    /**
     * 加载中的状态
     */
    public static final int STATE_LOADING = 0;
    /**
     * 空的状态
     */
    public static final int STATE_EMPTY = 1;
    /**
     * 错误的状态
     */
    public static final int STATE_ERROR = 2;
    /**
     * 成功的状态
     */
    public static final int STATE_SUCCESS = 3;
    /**
     * 当前view的状态
     */
    private int mCurrentState = STATE_NONE;
    /**
     * 正在加载中的view
     */
    private PageLoadingBinding mLoadingBinding;
    /**
     * 没有数据的view
     */
    private PagerEmptyBinding mEmptyBinding;
    /**
     * 加载失败的view
     */
    private PagerErrorBinding mErrorBinding;
    /**
     * 成功的view
     */
    private View mSuccessView;

    private LayoutInflater inflate;

    public LoadingPager(Context context) {
        this(context, null);
    }

    public LoadingPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        initView();
    }


    /**
     * 初始化
     */
    private void initView() {
        /**
         * 1. 加载数据
         */
        if (mLoadingBinding == null) {
            mLoadingBinding = DataBindingUtil.inflate(inflate, R.layout.page_loading, null,false);
            setProgressStyle(15);
            addView(mLoadingBinding.getRoot());
        }

        /**
         * 2. 数据为空
         */
        if (mEmptyBinding == null) {
            mEmptyBinding = DataBindingUtil.inflate(inflate, R.layout.pager_empty, null,false);
            addView(mEmptyBinding.getRoot());
            mEmptyBinding.getRoot().setOnClickListener(this);
        }

        /**
         * 3. 加载失败
         */
        if (mErrorBinding == null) {
            mErrorBinding = DataBindingUtil.inflate(inflate, R.layout.pager_error, null,false);
            addView(mErrorBinding.getRoot());
            mErrorBinding.getRoot().setOnClickListener(this);
        }
        /**
         * 4. 成功
         */
        if (mSuccessView == null) {
            mSuccessView = onCreateSuccessView();
            FrameLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            addView(mSuccessView, params);
        }

        safeUpdateUIStyle();
    }

    public void setProgressStyle(int style) {
        AVLoadingIndicatorView progressView = new AVLoadingIndicatorView(this.getContext());
        progressView.setIndicatorColor(0xffFF0066);
        progressView.setIndicatorId(style);
        mLoadingBinding.switcher.setView(progressView);
    }

    /**
     * 根据状态显示view
     */
    private void safeUpdateUIStyle() {
        UIUtil.runInMainThread(new Runnable() {
            @Override
            public void run() {
                updateUIStyle();
            }
        });
    }

    private void updateUIStyle() {

        /**
         * 1.loading
         */
        if (mLoadingBinding != null) {
            if ((mCurrentState == STATE_LOADING) || (mCurrentState == STATE_NONE)) {
                mLoadingBinding.getRoot().setVisibility(VISIBLE);
            } else {
                mLoadingBinding.getRoot().setVisibility(GONE);
            }
            if (mCurrentState == STATE_LOADING) {
                bringChildToFront(mLoadingBinding.getRoot());
                return;
            }
        }

        /**
         * 2. empty
         */
        if (mEmptyBinding != null) {
            if (mCurrentState == STATE_EMPTY) {
                mEmptyBinding.getRoot().setVisibility(VISIBLE);
            } else {
                mEmptyBinding.getRoot().setVisibility(GONE);
            }
            if (mCurrentState == STATE_EMPTY) {
                bringChildToFront(mEmptyBinding.getRoot());
            }
        }


        /**
         * 3. error
         */
        if (mErrorBinding != null) {
            if (mCurrentState == STATE_ERROR) {
                mErrorBinding.getRoot().setVisibility(VISIBLE);
            } else {
                mErrorBinding.getRoot().setVisibility(GONE);
            }
            if (mCurrentState == STATE_ERROR) {
                bringChildToFront(mErrorBinding.getRoot());
            }
        }

        /**
         * 4. success
         */
        if (mSuccessView != null) {
            if (mCurrentState == STATE_SUCCESS) {
                mSuccessView.setVisibility(VISIBLE);
            } else {
                mSuccessView.setVisibility(GONE);
            }
        }
    }

    /**
     * 加载数据的方法
     */
    public void loadData() {
        if (mCurrentState == STATE_EMPTY || mCurrentState == STATE_ERROR || mCurrentState == STATE_NONE) {
            mCurrentState = STATE_LOADING;
            onStartLoadData();
        }
        safeUpdateUIStyle();
    }

    @Override
    public void onDataLoading(LoadedResult result) {
        mCurrentState = result.getState();
        safeUpdateUIStyle();
    }

    protected abstract View onCreateSuccessView();

    protected abstract void onStartLoadData();

    public PagerErrorBinding getErrorBinding() {
        return mErrorBinding;
    }

    public PageLoadingBinding getLoadingBinding() {
        return mLoadingBinding;
    }

    public PagerEmptyBinding getEmptyBinding() {
        return mEmptyBinding;
    }

    public int getCurrentState() {
        return mCurrentState;
    }

    protected void reloadData() {
    }

    public enum LoadedResult {
        LOADING(STATE_LOADING), EMPTY(STATE_EMPTY), ERROR(STATE_ERROR), SUCCESS(STATE_SUCCESS);

        int state;

        LoadedResult(int state) {
            this.state = state;
        }

        public int getState() {
            return state;
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ll_reload) {
            mCurrentState = STATE_LOADING;
            safeUpdateUIStyle();
            reloadData();
        }
    }

}
