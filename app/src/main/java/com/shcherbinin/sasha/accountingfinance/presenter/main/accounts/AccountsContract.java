package com.shcherbinin.sasha.accountingfinance.presenter.main.accounts;

import com.shcherbinin.sasha.accountingfinance.domain.account.AccountModel;

import java.util.List;

public interface AccountsContract {

    interface View {

        void showContent(List<AccountModel> accountModels);

        void showContent(AccountModel accountModel);

        void showLoading();

        void showAddDialog();

        void showEditDialog(AccountModel accountModel);
    }

    interface EventListener {

        void onAddClicked();

        void onSelected(AccountModel accountModel);

        void onEditClicked(AccountModel accountModel);

        void onDeleteClicked(AccountModel accountModel);
    }

    interface EventDelegate {

    }
}