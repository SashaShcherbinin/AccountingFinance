package com.shcherbinin.sasha.accountingfinance.presenter.main.transaction;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.shcherbinin.sasha.accountingfinance.databinding.FragmentTransactionBinding;
import com.shcherbinin.sasha.accountingfinance.di.Injector;
import com.shcherbinin.sasha.accountingfinance.presenter.main.transaction.injection.TransactionStubComponent;

import javax.inject.Inject;

public class TransactionDialog extends DialogFragment implements TransactionContract.View {

    public static TransactionDialog newInstance() {
        return new TransactionDialog();
    }

    private FragmentTransactionBinding mBinding;

    @Inject TransactionPresenter mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Injector.getComponent(getActivity(), TransactionStubComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentTransactionBinding.inflate(inflater, container, false);
        mBinding.setViewModel(new TransactionViewModel(mPresenter));
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
    public void closeDialog() {
        dismiss();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
