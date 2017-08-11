package com.ty.warwolf.ui.activity;

import android.os.Bundle;

import com.ty.warwolf.R;
import com.ty.warwolf.base.BaseActivity;
import com.ty.warwolf.base.LoadingPager;
import com.ty.warwolf.databinding.ActivityLoginBinding;
import com.ty.warwolf.viewmodel.LoginViewModel;

/**
 * @ 文件名:   LoginActivity
 * @ 创建者:   ty
 * @ 时间:    2017/8/11 上午9:11
 * @ 描述:
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding> {


    private LoginViewModel mViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        getBaseBinding().toolBar.setTitle("登录");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPager.onDataLoading(LoadingPager.LoadedResult.SUCCESS);

        mViewModel = new LoginViewModel(mPager, this, mBinding);
        mBinding.setLoginVM(mViewModel);

    }

    @Override
    protected void reloadData() {
        mPager.onDataLoading(LoadingPager.LoadedResult.SUCCESS);
    }
}
