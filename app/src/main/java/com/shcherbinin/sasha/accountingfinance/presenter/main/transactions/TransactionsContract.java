package com.shcherbinin.sasha.accountingfinance.presenter.main.transactions;

import com.shcherbinin.sasha.accountingfinance.domain.transaction.TransactionModel;

import java.util.List;

public interface TransactionsContract {

    interface View {

        void showContent(List<TransactionModel> transactionModels);

        void showEmpty();
    }

    interface EventListener {

    }

    interface EventDelegate {

    }
}