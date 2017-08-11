package com.ty.warwolf.ui.fragment;

import android.os.Bundle;

import com.ty.warwolf.R;
import com.ty.warwolf.base.BaseFragment;
import com.ty.warwolf.base.LoadingPager;
import com.ty.warwolf.databinding.FragmentFindBinding;

/**
 * @ 文件名:   FindFragment
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午3:12
 * @ 描述:    发现
 */

public class FindFragment extends BaseFragment<FragmentFindBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find;
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
