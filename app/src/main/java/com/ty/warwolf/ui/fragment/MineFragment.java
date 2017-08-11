package com.ty.warwolf.ui.fragment;

import android.os.Bundle;

import com.ty.warwolf.R;
import com.ty.warwolf.base.BaseFragment;
import com.ty.warwolf.base.LoadingPager;
import com.ty.warwolf.databinding.FragmentHomeBinding;

/**
 * @ 文件名:   MineFragment
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午3:12
 * @ 描述:
 */

public class MineFragment extends BaseFragment<FragmentHomeBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPager.onDataLoading(LoadingPager.LoadedResult.SUCCESS);
    }

    @Override
    protected void reloadData() {

    }
}
