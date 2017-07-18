package com.shcherbinin.sasha.accountingfinance.presenter.main.transactions.injection;

import com.shcherbinin.sasha.accountingfinance.presenter.main.transactions.TransactionsContract;
import dagger.Module;
import dagger.Provides;

@Module
public final class TransactionsModule {

    private TransactionsContract.EventDelegate mEventDelegate;

    public TransactionsModule(TransactionsContract.EventDelegate eventDelegate) {
        mEventDelegate = eventDelegate;
    }

    @Provides
    TransactionsContract.EventDelegate provideEventDelegate() {
        return mEventDelegate;
    }

}
