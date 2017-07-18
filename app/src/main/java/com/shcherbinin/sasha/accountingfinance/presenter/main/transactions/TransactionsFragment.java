package com.shcherbinin.sasha.accountingfinance.presenter.main.transactions;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.kennyc.view.MultiStateView;
import com.shcherbinin.sasha.accountingfinance.R;
import com.shcherbinin.sasha.accountingfinance.databinding.FragmentTransactionsBinding;
import com.shcherbinin.sasha.accountingfinance.di.Injector;
import com.shcherbinin.sasha.accountingfinance.domain.transaction.TransactionModel;
import com.shcherbinin.sasha.accountingfinance.presenter.common.BaseFragment;
import com.shcherbinin.sasha.accountingfinance.presenter.main.transactions.injection.TransactionsStubComponent;

import javax.inject.Inject;
import java.util.List;

public class TransactionsFragment extends BaseFragment implements TransactionsContract.View {

    public static TransactionsFragment newInstance() {
        return new TransactionsFragment();
    }

    private FragmentTransactionsBinding mBinding;
    private TransactionAdapter mAdapter;

    @Inject TransactionsPresenter mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Injector.getComponent(getActivity(), TransactionsStubComponent.class).inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new TransactionAdapter(mPresenter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_transactions, container, false);
        mBinding.multiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
        mBinding.recyclerView.setLayoutManager(layout);
        mBinding.recyclerView.setAdapter(mAdapter);
        mBinding.recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), layout.getOrientation()));
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
    public void showContent(List<TransactionModel> transactionModels) {
        mBinding.multiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
        mAdapter.setData(transactionModels);
    }

    @Override
    public void showEmpty() {
        mBinding.multiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
    }
}
