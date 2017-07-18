package com.shcherbinin.sasha.accountingfinance.presenter.main;

import android.support.v7.app.AppCompatActivity;
import com.shcherbinin.sasha.accountingfinance.presenter.main.dashboard.DashboardContract;
import com.shcherbinin.sasha.accountingfinance.presenter.main.transaction.TransactionContract;
import com.shcherbinin.sasha.accountingfinance.presenter.main.transaction.TransactionDialog;
import com.shcherbinin.sasha.accountingfinance.presenter.main.transactions.TransactionsContract;
import com.shcherbinin.sasha.accountingfinance.presenter.main.accounts.AccountsContract;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/16/17
 * Time: 7:14 PM
 */

public class MainRouter implements
        DashboardContract.EventDelegate,
        AccountsContract.EventDelegate,
        TransactionsContract.EventDelegate,
        TransactionContract.EventDelegate {

    private AppCompatActivity mActivity;

    MainRouter(AppCompatActivity activity) {
        mActivity = activity;
    }

    public void onAddClicked() {
        TransactionDialog.newInstance().show(mActivity.getSupportFragmentManager(), "TransactionDialog");
    }
}
