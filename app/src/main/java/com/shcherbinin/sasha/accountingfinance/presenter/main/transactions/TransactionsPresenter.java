package com.shcherbinin.sasha.accountingfinance.presenter.main.transactions;

import android.support.annotation.Nullable;
import com.shcherbinin.sasha.accountingfinance.domain.transaction.TransactionInteractor;
import com.shcherbinin.sasha.accountingfinance.presenter.common.BasePresenter;
import com.shcherbinin.sasha.accountingfinance.utils.RxUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

import javax.inject.Inject;

public final class TransactionsPresenter extends BasePresenter<TransactionsContract.View> implements
        TransactionsContract.EventListener {

    private TransactionsContract.EventDelegate mEventDelegate;
    private TransactionInteractor mTransactionInteractor;

    @Inject
    TransactionsPresenter(TransactionsContract.EventDelegate eventDelegate,
                          TransactionInteractor transactionInteractor) {
        mEventDelegate = eventDelegate;
        mTransactionInteractor = transactionInteractor;
    }

    @Override
    public void attachView(TransactionsContract.View view) {
        super.attachView(view);
        RxUtils.manage(this, mTransactionInteractor.getTransactions()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(transactionModels -> {
                    if(transactionModels.size() > 0) {
                        mView.showContent(transactionModels);
                    } else {
                        mView.showEmpty();
                    }
                }, Timber::e));
    }

    @Nullable
    TransactionsContract.View getView() {
        return mView;
    }

}