package com.ty.warwolf.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ty.warwolf.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @ 文件名:   MainPagerAdapter
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午3:09
 * @ 描述:    主UI适配器
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

    private final List<BaseFragment> mFragmentList = new ArrayList<>();

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(BaseFragment fragment){
        mFragmentList.add(fragment);
    }
}
