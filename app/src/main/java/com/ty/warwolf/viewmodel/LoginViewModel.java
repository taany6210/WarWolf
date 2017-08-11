package com.ty.warwolf.viewmodel;

import android.app.ProgressDialog;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.ty.warwolf.base.BaseActivity;
import com.ty.warwolf.base.BaseViewModel;
import com.ty.warwolf.base.LoadingPager;
import com.ty.warwolf.config.ConstZh;
import com.ty.warwolf.databinding.ActivityLoginBinding;
import com.ty.warwolf.model.bean.base.User;
import com.ty.warwolf.model.retrofit.RetrofitFactory;
import com.ty.warwolf.model.retrofit.TFunc1;
import com.ty.warwolf.model.retrofit.TSubscriber;
import com.ty.warwolf.util.EncryptUtil;
import com.ty.warwolf.util.FormValidation;
import com.ty.warwolf.util.KeyboardUtil;
import com.ty.warwolf.view.SimpleTextWatcher;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @ 文件名:   LoginViewModel
 * @ 创建者:   ty
 * @ 时间:    2017/8/11 上午9:14
 * @ 描述:    登录VM
 */

public class LoginViewModel extends BaseViewModel<ActivityLoginBinding> {

    private static final String TAG = "LoginViewModel";
    public ObservableField<String> phone = new ObservableField<>();
    public ObservableField<String> passWord = new ObservableField<>();
    public ObservableBoolean isCanLogin = new ObservableBoolean(false);
    private final ProgressDialog mDialog;
    private Subscription mSubscribe;

    public LoginViewModel(LoadingPager pager, BaseActivity activity, ActivityLoginBinding binding) {
        super(pager, activity, binding);
        mDialog = new ProgressDialog(mActivity);
        mDialog.setCancelable(false);
        mDialog.setMessage(ConstZh.LOGIN_LOADING);
    }

    public void loginClick(View view) {
        KeyboardUtil.hide(mActivity);
        mSubscribe = RetrofitFactory.sApiService.login(phone.get(), EncryptUtil.MD5(passWord.get()))
                .map(new TFunc1<>(mPager))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new TSubscriber<User>(mPager) {
                    @Override
                    public void onDataSuccess(User user) {
                        mDialog.dismiss();

                    }

                    @Override
                    public void onDataError(String message) {
                        super.onDataError(message);
                        mDialog.dismiss();
                    }
                });
        addSubscribe(mSubscribe);
    }

    /**
     * 跳转忘记密码
     * @param view
     */
    public void forgetClick(View view) {
        KeyboardUtil.hide(mActivity);
        Toast.makeText(mActivity, "忘记密码", Toast.LENGTH_SHORT).show();
    }


    /**
     * 监听手机号码输入EditText文字变化
     */
    public TextWatcher textPhoneWatcher = new SimpleTextWatcher() {
        @Override
        public void afterTextChanged(Editable editable) {
            phone.set(editable.toString().trim());
            isCanLogin.set(formatLogin());
        }
    };

    /**
     * 监听密码输入变化
     */
    public TextWatcher textPassWordWatcher = new SimpleTextWatcher() {
        @Override
        public void afterTextChanged(Editable editable) {
            passWord.set(editable.toString().trim());
            isCanLogin.set(formatLogin());
        }
    };


    /**
     * 监听键盘删除键
     */
    public View.OnKeyListener onKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                isCanLogin.set(formatLogin());
            }
            return false;
        }
    };


    /**
     * 验证是否可以进行登录
     *
     * @return
     */
    public boolean formatLogin() {
        if (phone.get() != null && passWord.get() != null) {
            if (FormValidation.isPhone(phone.get().trim()) && checkPassWord()) {
                Log.e(TAG, "满足登录条件了");
                return true;
            }
        }
        return false;
    }


    /**
     * 检查密码是否合法
     *
     * @return
     */
    public boolean checkPassWord() {
        if (passWord.get() == null) {
            return false;
        }
        if (TextUtils.isEmpty(passWord.get().trim())) {
            return false;
        }
        if (passWord.get().trim().length() < 6) {
            return false;
        }
        return true;
    }

}
