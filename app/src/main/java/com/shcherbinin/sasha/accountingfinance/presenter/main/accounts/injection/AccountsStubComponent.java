package com.shcherbinin.sasha.accountingfinance.presenter.main.accounts.injection;

import com.shcherbinin.sasha.accountingfinance.presenter.main.dashboard.injection.DashboardModule;
import com.shcherbinin.sasha.accountingfinance.presenter.main.accounts.AccountsFragment;
import dagger.Subcomponent;

@Subcomponent(modules = {
        DashboardModule.class
})
public interface AccountsStubComponent {

    void inject(AccountsFragment fragment);
}