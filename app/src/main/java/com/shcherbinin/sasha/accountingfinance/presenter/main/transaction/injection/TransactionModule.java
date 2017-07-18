package com.shcherbinin.sasha.accountingfinance.presenter.main.transaction.injection;

import com.shcherbinin.sasha.accountingfinance.presenter.main.transaction.TransactionContract;
import dagger.Module;
import dagger.Provides;

@Module
public final class TransactionModule {

    private TransactionContract.EventDelegate mEventDelegate;

    public TransactionModule(TransactionContract.EventDelegate eventDelegate) {
        mEventDelegate = eventDelegate;
    }

    @Provides
    TransactionContract.EventDelegate provideEventDelegate() {
        return mEventDelegate;
    }

}
