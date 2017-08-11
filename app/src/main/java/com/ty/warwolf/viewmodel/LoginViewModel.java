package com.ty.warwolf.viewmodel;

import com.ty.warwolf.base.BaseActivity;
import com.ty.warwolf.base.BaseViewModel;
import com.ty.warwolf.base.LoadingPager;
import com.ty.warwolf.databinding.ActivityLoginBinding;

/**
 * @ 文件名:   LoginViewModel
 * @ 创建者:   ty
 * @ 时间:    2017/8/11 上午9:14
 * @ 描述:
 */

public class LoginViewModel extends BaseViewModel<ActivityLoginBinding> {

    public LoginViewModel(LoadingPager pager, BaseActivity activity, ActivityLoginBinding binding) {
        super(pager, activity, binding);
    }



}
