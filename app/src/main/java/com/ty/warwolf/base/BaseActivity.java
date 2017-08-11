package com.ty.warwolf.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.widget.LinearLayout;

import com.dgrlucky.log.LogX;
import com.ty.warwolf.R;
import com.ty.warwolf.config.ConstZh;
import com.ty.warwolf.databinding.ActivityBaseBinding;
import com.ty.warwolf.ui.activity.MainActivity;
import com.ty.warwolf.util.PageManager;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @ 文件名:   BaseActivity
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 上午10:34
 * @ 描述:
 */

public abstract class BaseActivity<B extends ViewDataBinding> extends AppCompatActivity {

    protected BaseActivity mActivity;
    protected LoadingPager mPager;
    private boolean needInit = true;
    private ActivityBaseBinding mBaseBinding;
    protected B mBinding;
    private AlertDialog mDialog;
    protected CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        mActivity = this;
        PageManager.addPage(this);

        init(savedInstanceState);

        setSupportActionBar(mBaseBinding.toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    /**
     * 初始化
     *
     * @param savedInstanceState
     */
    private void init(Bundle savedInstanceState) {
        mBaseBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_base, null,
                false);
        LinearLayout rootLayout = (LinearLayout) mBaseBinding.getRoot();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout
                .LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        if (mPager == null) {
            mPager = new LoadingPager(this) {
                @Override
                protected View onCreateSuccessView() {
                    mBinding = DataBindingUtil.inflate(getLayoutInflater(), getLayoutId(), null, false);
                    initView(savedInstanceState);
                    initListener();
                    return mBinding.getRoot();
                }

                @Override
                protected void onStartLoadData() {
                    initData(savedInstanceState);
                }

                @Override
                public void onDataLoading(LoadedResult result) {
                    super.onDataLoading(result);
                    if (result == LoadedResult.SUCCESS) {
                        if (needInit) {
                            needInit = false;
                        }
                    }
                }

                @Override
                protected void reloadData() {
                    super.reloadData();
                    BaseActivity.this.reloadData();
                }
            };
        } else {
            ViewParent parent = mPager.getParent();
            if (parent != null && parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(mPager);
            }
        }
        mPager.loadData();
        rootLayout.addView(mPager, params);
        setContentView(rootLayout);
    }

    public abstract int getLayoutId();

    /**
     * 初始化视图 子类必须实现
     *
     * @return
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 加载数据 子类必须实现
     */
    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 初始化事件 子类选择实现
     */
    protected void initListener() {

    }

    /**
     * 重新加载数据  错误或者空页面点击
     */
    protected abstract void reloadData();


    public ActivityBaseBinding getBaseBinding() {
        return mBaseBinding;
    }


    /**
     * 添加订阅
     *
     * @param s 需要取消的Subscriptions
     */
    public void addSubscribe(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        if (s != null) {
            this.mCompositeSubscription.add(s);
        }
    }

    /**
     * 取消订阅
     */
    private void unsubscribe() {
        if (mCompositeSubscription != null && !mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    /**
     * 获取颜色
     *
     * @param colorId id
     * @return
     */
    public int getColorRes(int colorId) {
        if (mActivity != null) {
            return ContextCompat.getColor(mActivity, colorId);
        }
        return 0;
    }

    /**
     * 完成退出
     */
    public void exit() {
        PageManager.AppExit(this);
    }

    @Override
    public void onBackPressed() {
        if (this instanceof MainActivity) {
            mDialog = new AlertDialog.Builder(this)
                    .setTitle(ConstZh.EXIT_TITLE)
                    .setMessage(ConstZh.EXIT_APP)
                    .setNegativeButton(ConstZh.CANCEL, (dialogInterface, i) ->
                            dialogInterface.dismiss())
                    .setPositiveButton(ConstZh.EXIT, (dialogInterface, i) -> {
                        exit();
                    }).create();

            if (!mDialog.isShowing()) {
                mDialog.show();
            }

            return;
        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected void onDestroy() {
        try {
            if (mDialog.isShowing()) {
                mDialog.dismiss();
            }
        } catch (Exception e) {
            LogX.e(ConstZh.CANCEL_DIALOG_FAILED);
        }
        PageManager.removePage(this);
        unsubscribe();
        super.onDestroy();
    }
}
