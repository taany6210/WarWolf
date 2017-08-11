package com.ty.warwolf.ui.activity;

import android.os.Bundle;

import com.ty.warwolf.R;
import com.ty.warwolf.base.BaseActivity;
import com.ty.warwolf.base.LoadingPager;
import com.ty.warwolf.databinding.ActivityMainBinding;
import com.ty.warwolf.ui.adapter.MainPagerAdapter;
import com.ty.warwolf.ui.fragment.CategoryFragment;
import com.ty.warwolf.ui.fragment.FindFragment;
import com.ty.warwolf.ui.fragment.HomeFragment;
import com.ty.warwolf.ui.fragment.MineFragment;

/**
 * @ 文件名:   MainActivity
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 上午10:33
 * @ 描述:
 */
public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //对底部导航栏的一些处理显示字体，启用放大动画，关闭item和navigator的位移动画
        mBinding.navigation.setTextVisibility(true);
        mBinding.navigation.enableAnimation(true);
        mBinding.navigation.enableShiftingMode(false);
        mBinding.navigation.enableItemShiftingMode(false);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPager.onDataLoading(LoadingPager.LoadedResult.SUCCESS);

        MainPagerAdapter pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        HomeFragment homeFragment = new HomeFragment();
        CategoryFragment categoryFragment = new CategoryFragment();
        FindFragment findFragment = new FindFragment();
        MineFragment mineFragment = new MineFragment();

        pagerAdapter.addFragment(homeFragment);
        pagerAdapter.addFragment(categoryFragment);
        pagerAdapter.addFragment(findFragment);
        pagerAdapter.addFragment(mineFragment);
        mBinding.viewPager.setAdapter(pagerAdapter);
    }

    @Override
    protected void reloadData() {
        mPager.onDataLoading(LoadingPager.LoadedResult.SUCCESS);
    }

    @Override
    protected void initListener() {
        mBinding.navigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mBinding.viewPager.setCurrentItem(0, false);
                    break;
                case R.id.navigation_dashboard:
                    mBinding.viewPager.setCurrentItem(1, false);
                    break;
                case R.id.navigation_notifications:
                    mBinding.viewPager.setCurrentItem(2, false);
                    break;
                case R.id.navigation_mine:
                    mBinding.viewPager.setCurrentItem(3, false);
                    break;
            }

            return false;
        });
    }
}
