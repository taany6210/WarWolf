package com.ty.warwolf.ui.activity;

import android.os.Bundle;

import com.ty.warwolf.R;
import com.ty.warwolf.base.BaseActivity;
import com.ty.warwolf.base.LoadingPager;
import com.ty.warwolf.databinding.ActivityLoginBinding;

/**
 * @ 文件名:   LoginActivity
 * @ 创建者:   ty
 * @ 时间:    2017/8/11 上午9:11
 * @ 描述:
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
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
