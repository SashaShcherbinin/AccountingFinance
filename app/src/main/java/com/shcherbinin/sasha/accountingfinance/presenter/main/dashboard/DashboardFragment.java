package com.shcherbinin.sasha.accountingfinance.presenter.main.dashboard;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.kennyc.view.MultiStateView;
import com.shcherbinin.sasha.accountingfinance.R;
import com.shcherbinin.sasha.accountingfinance.databinding.FragmentDashboardBinding;
import com.shcherbinin.sasha.accountingfinance.di.Injector;
import com.shcherbinin.sasha.accountingfinance.domain.dashboard.DashboardModel;
import com.shcherbinin.sasha.accountingfinance.presenter.common.BaseFragment;
import com.shcherbinin.sasha.accountingfinance.presenter.main.dashboard.injection.DashboardStubComponent;

import javax.inject.Inject;

public class DashboardFragment extends BaseFragment implements DashboardContract.View {

    public static DashboardFragment newInstance() {
        return new DashboardFragment();
    }
    private FragmentDashboardBinding mBinding;

    @Inject DashboardPresenter mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Injector.getComponent(getActivity(), DashboardStubComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false);
        mBinding.multiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
    }

    @Override
    public void showContent(DashboardModel dashboardModel) {
        mBinding.multiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
        mBinding.setDashboard(dashboardModel);
    }
}
