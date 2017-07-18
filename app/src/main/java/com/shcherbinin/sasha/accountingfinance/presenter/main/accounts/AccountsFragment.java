package com.shcherbinin.sasha.accountingfinance.presenter.main.accounts;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.kennyc.view.MultiStateView;
import com.rubengees.easyheaderfooteradapter.EasyHeaderFooterAdapter;
import com.shcherbinin.sasha.accountingfinance.R;
import com.shcherbinin.sasha.accountingfinance.databinding.DialogAddAccountBinding;
import com.shcherbinin.sasha.accountingfinance.databinding.FragmentAccountsBinding;
import com.shcherbinin.sasha.accountingfinance.databinding.ItemAddAccountBinding;
import com.shcherbinin.sasha.accountingfinance.di.Injector;
import com.shcherbinin.sasha.accountingfinance.domain.account.AccountModel;
import com.shcherbinin.sasha.accountingfinance.presenter.common.BaseFragment;
import com.shcherbinin.sasha.accountingfinance.presenter.main.accounts.injection.AccountsStubComponent;

import javax.inject.Inject;
import java.util.List;

public class AccountsFragment extends BaseFragment implements AccountsContract.View {

    public static AccountsFragment newInstance() {
        return new AccountsFragment();
    }

    private FragmentAccountsBinding mBinding;
    private AccountAdapter mAdapter;

    @Inject AccountsPresenter mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Injector.getComponent(getActivity(), AccountsStubComponent.class).inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new AccountAdapter(mPresenter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        EasyHeaderFooterAdapter adapter = new EasyHeaderFooterAdapter(mAdapter);
        ItemAddAccountBinding footerBinding = DataBindingUtil.inflate(inflater, R.layout.item_add_account, null, false);
        footerBinding.setEvent(mPresenter);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        footerBinding.getRoot().setLayoutParams(layoutParams);
        adapter.setFooter(footerBinding.getRoot());

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_accounts, container, false);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.recyclerView.setAdapter(adapter);
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
    public void showContent(List<AccountModel> accountModels) {
        mBinding.multiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
        mAdapter.setData(accountModels);
    }

    @Override
    public void showContent(AccountModel accountModel) {
        mAdapter.setData(accountModel);
    }

    @Override
    public void showLoading() {
        mBinding.multiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
    }

    @Override
    public void showEditDialog(AccountModel accountModel) {
        DialogAddAccountBinding dialogBinding = DialogAddAccountBinding
                .inflate(LayoutInflater.from(getContext()), null, false);
        dialogBinding.nameEt.setText(accountModel.getName());
        new AlertDialog.Builder(getContext())
                .setView(dialogBinding.getRoot())
                .setPositiveButton(R.string.GENERAL_BUTTON_SAVE, (dialogInterface, i) -> {
                    String s = dialogBinding.nameEt.getText().toString().trim();
                    if (s.length() > 0) {
                        accountModel.setName(s);
                        mPresenter.onSaveAccount(accountModel);
                    }
                })
                .show();
    }

    @Override
    public void showAddDialog() {
        DialogAddAccountBinding dialogBinding = DialogAddAccountBinding
                .inflate(LayoutInflater.from(getContext()), null, false);
        new AlertDialog.Builder(getContext())
                .setView(dialogBinding.getRoot())
                .setPositiveButton(R.string.GENERAL_BUTTON_ADD, (dialogInterface, i) -> {
                    String s = dialogBinding.nameEt.getText().toString().trim();
                    if (s.length() > 0) {
                        mPresenter.onAddAccount(s);
                    }
                })
                .show();
    }
}
