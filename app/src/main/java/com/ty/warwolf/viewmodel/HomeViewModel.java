package com.ty.warwolf.viewmodel;

import android.view.View;
import android.widget.Toast;

import com.ty.warwolf.base.BaseActivity;
import com.ty.warwolf.base.BaseViewModel;
import com.ty.warwolf.base.LoadingPager;
import com.ty.warwolf.config.AppConst;
import com.ty.warwolf.databinding.FragmentHomeBinding;
import com.ty.warwolf.model.bean.base.Today;
import com.ty.warwolf.model.retrofit.RetrofitFactory;
import com.ty.warwolf.model.retrofit.TFunc1;
import com.ty.warwolf.model.retrofit.TSubscriber;
import com.ty.warwolf.ui.adapter.HomeRvAdapter;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @ 文件名:   HomeViewModel
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午3:40
 * @ 描述:    首页VM
 */

public class HomeViewModel extends BaseViewModel<FragmentHomeBinding> {

    public HomeViewModel(LoadingPager pager, BaseActivity activity, FragmentHomeBinding binding) {
        super(pager, activity, binding);
    }


    public void start() {
        RetrofitFactory.sApiService
                .getHistory(AppConst.APP_KEY, "11", "4")
                .map(new TFunc1<>(mPager))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new TSubscriber<List<Today>>(mPager) {
                    @Override
                    public void onDataSuccess(List<Today> todayList) {
                        mPager.onDataLoading(LoadingPager.LoadedResult.SUCCESS);
                        mBinding.ptrLayout.refreshComplete();

                        HomeRvAdapter homeRvAdapter = new HomeRvAdapter();
                        mBinding.recyclerView.setAdapter(homeRvAdapter);
                        homeRvAdapter.setList(todayList);
                        homeRvAdapter.setViewModel(HomeViewModel.this);
                    }
                });
    }


    /**
     * item点击事件
     *
     * @param view  view
     * @param today 实例
     */
    public void onItemClick(View view, Today today) {
        Toast.makeText(mActivity, today.getTitle(), Toast.LENGTH_SHORT).show();
    }

}
