package com.shcherbinin.sasha.accountingfinance.presenter.main.accounts.injection;

import com.shcherbinin.sasha.accountingfinance.presenter.main.accounts.AccountsContract;
import dagger.Module;
import dagger.Provides;

@Module
public final class AccountsModule {

    private AccountsContract.EventDelegate mEventDelegate;

    public AccountsModule(AccountsContract.EventDelegate eventDelegate) {
        mEventDelegate = eventDelegate;
    }

    @Provides
    AccountsContract.EventDelegate provideEventDelegate() {
        return mEventDelegate;
    }

}
