package com.ty.warwolf.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.ty.warwolf.R;
import com.ty.warwolf.base.BaseFragment;
import com.ty.warwolf.databinding.FragmentHomeBinding;
import com.ty.warwolf.viewmodel.HomeViewModel;
import com.ty.warwolf.util.AppBasicSetUtil;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * @ 文件名:   HomeFragment
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午3:12
 * @ 描述:    首页
 */

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {

    private HomeViewModel mViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mBinding.recyclerView.setHasFixedSize(true);

        AppBasicSetUtil.setRefreshHeader(mBinding.ptrLayout, mActivity);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        mViewModel = new HomeViewModel(mPager, mActivity, mBinding);
        mViewModel.start();
    }

    @Override
    protected void reloadData() {
        mViewModel.start();
    }


    @Override
    protected void initListener() {
        mBinding.ptrLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame,mBinding.recyclerView,header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mViewModel.start();
            }
        });

    }
}
