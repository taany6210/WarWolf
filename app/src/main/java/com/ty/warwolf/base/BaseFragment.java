package com.ty.warwolf.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;

import com.ty.warwolf.R;
import com.ty.warwolf.databinding.ActivityBaseBinding;
import com.ty.warwolf.databinding.FragmentBaseBinding;

import java.lang.reflect.Field;

import rx.Subscription;


/**
 * @ 文件名:   BaseFragment
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 上午10:34
 * @ 描述:
 */
public abstract class BaseFragment <B extends ViewDataBinding> extends Fragment {
    protected BaseActivity mActivity;
    protected LoadingPager mPager;
    protected LayoutInflater mInflater;
    private FragmentBaseBinding mBaseBinding;
    protected B mBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
    }

    public ActivityBaseBinding getBaseBinding() {
        return mActivity == null ? null : mActivity.getBaseBinding();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        mBaseBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_base, null, false);
        mInflater = inflater;
        LinearLayout layout = (LinearLayout) mBaseBinding.getRoot();
        if (mPager == null) {
            mPager = new LoadingPager(mActivity) {
                @Override
                protected View onCreateSuccessView() {
                    mBinding = DataBindingUtil.inflate(mInflater, BaseFragment.this.getLayoutId(), null, false);
                    initView(savedInstanceState);
                    initListener();
                    return mBinding.getRoot();
                }

                @Override
                protected void onStartLoadData() {
                    initData(savedInstanceState);
                }

                @Override
                protected void reloadData() {
                    BaseFragment.this.reloadData();
                }
            };
        } else {
            ViewParent parent = mPager.getParent();
            if (parent != null && parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(mPager);
            }
            initTitleBar();
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout
                .LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layout.addView(mPager, params);
        return layout;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mPager != null) {
            mPager.loadData();
        }
    }

    /**
     * 返回layout布局ID
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化view
     *
     * @return
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 初始化数据
     */
    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 初始化事件,子类需要时覆写
     */
    protected void initListener() {
    }

    /**
     * 初始化titleBar
     */
    protected void initTitleBar() {

    }

    /**
     * 重新加载
     */
    protected abstract void reloadData();


    /**
     * 管理Fragment切换
     *
     * @param resourceId       要替换的资源id
     * @param targetFragment   要替换的目标Fragment
     * @param isAddToBackStack 是否添加到返回栈
     * @param bundle           替换Fragment时携带的参数
     */
    protected void replaceFragment(int resourceId, Fragment targetFragment, boolean
            isAddToBackStack, Bundle bundle) {
        if (getActivity() == null) {
            return;
        }

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        if (bundle != null) {
            targetFragment.setArguments(bundle);

        }

        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.replace(resourceId, targetFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * 管理Fragment切换
     *
     * @param resourceId     要替换的资源id
     * @param targetFragment 要替换的目标Fragment
     */
    protected void replaceFragment(int resourceId, Fragment targetFragment) {
        replaceFragment(resourceId, targetFragment, false, null);
    }

    /**
     * 添加订阅
     *
     * @param s 需要取消的Subscriptions
     */
    protected void addSubscribe(Subscription s) {
        mActivity.addSubscribe(s);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
