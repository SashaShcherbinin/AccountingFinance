package com.shcherbinin.sasha.accountingfinance.presenter.main.transaction;

import com.shcherbinin.sasha.accountingfinance.domain.transaction.TransactionInteractor;
import com.shcherbinin.sasha.accountingfinance.presenter.common.BasePresenter;
import com.shcherbinin.sasha.accountingfinance.presenter.common.handler.DefaultErrorHandler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;

public final class TransactionPresenter extends BasePresenter<TransactionContract.View> implements
        TransactionContract.EventListener {

    private TransactionContract.EventDelegate mEventDelegate;
    private TransactionInteractor mTransactionInteractor;
    private DefaultErrorHandler mErrorHandler;

    @Inject
    TransactionPresenter(TransactionContract.EventDelegate eventDelegate,
                         TransactionInteractor transactionInteractor,
                         DefaultErrorHandler defaultErrorHandler) {
        mEventDelegate = eventDelegate;
        mTransactionInteractor = transactionInteractor;
        mErrorHandler = defaultErrorHandler;
    }

    @Override
    public void attachView(TransactionContract.View view) {
        super.attachView(view);
    }

    @Override
    public void onAddClicked(String title, String amount) {
        mTransactionInteractor.addTransaction(title, amount)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> mView.closeDialog(), throwable ->
                        mErrorHandler.handleError(throwable, message -> mView.showError(message)));
    }
}