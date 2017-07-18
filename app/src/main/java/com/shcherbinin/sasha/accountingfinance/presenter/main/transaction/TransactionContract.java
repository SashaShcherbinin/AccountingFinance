package com.shcherbinin.sasha.accountingfinance.presenter.main.transaction;

public interface TransactionContract {

    interface View {

        void closeDialog();

        void showError(String message);
    }

    interface EventListener {

        void onAddClicked(String title, String amount);
    }

    interface EventDelegate {

    }
}