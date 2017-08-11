package com.ty.warwolf.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ty.warwolf.R;
import com.ty.warwolf.databinding.ItemHomeTodayBinding;
import com.ty.warwolf.model.bean.base.Today;
import com.ty.warwolf.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @ 文件名:   HomeRvAdapter
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午4:33
 * @ 描述:
 */

public class HomeRvAdapter extends RecyclerView.Adapter<HomeRvAdapter.HomeViewHolder> {

    private List<Today> mList = new ArrayList<>();
    private HomeViewModel mViewModel;

    public List<Today> getList() {
        return mList;
    }

    public void setList(List<Today> list) {
        if (list != null) {
            mList.clear();
            addList(list);
        }
    }

    private void addList(List<Today> list) {
        if (list != null) {
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public HomeViewModel getViewModel() {
        return mViewModel;
    }

    public void setViewModel(HomeViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemHomeTodayBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_home_today, parent, false);
        return new HomeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        holder.bindData(mList.get(position),mViewModel);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    static class HomeViewHolder extends RecyclerView.ViewHolder {

        private ItemHomeTodayBinding mBinding;

        public HomeViewHolder(ItemHomeTodayBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bindData(Today today,HomeViewModel viewModel) {
            mBinding.setToday(today);
            mBinding.setHomeVM(viewModel);
            mBinding.executePendingBindings();
        }

    }
}
