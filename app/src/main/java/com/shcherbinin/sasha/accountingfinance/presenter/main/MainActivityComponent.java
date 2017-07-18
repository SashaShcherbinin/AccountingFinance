package com.shcherbinin.sasha.accountingfinance.presenter.main;

import com.shcherbinin.sasha.accountingfinance.di.activity.ActivityComponent;
import com.shcherbinin.sasha.accountingfinance.di.activity.ActivityComponentBuilder;
import com.shcherbinin.sasha.accountingfinance.di.activity.ActivityModule;
import com.shcherbinin.sasha.accountingfinance.di.activity.ActivityScope;
import com.shcherbinin.sasha.accountingfinance.presenter.main.accounts.injection.AccountsModule;
import com.shcherbinin.sasha.accountingfinance.presenter.main.accounts.injection.AccountsStubComponent;
import com.shcherbinin.sasha.accountingfinance.presenter.main.dashboard.injection.DashboardModule;
import com.shcherbinin.sasha.accountingfinance.presenter.main.dashboard.injection.DashboardStubComponent;
import com.shcherbinin.sasha.accountingfinance.presenter.main.transaction.injection.TransactionModule;
import com.shcherbinin.sasha.accountingfinance.presenter.main.transaction.injection.TransactionStubComponent;
import com.shcherbinin.sasha.accountingfinance.presenter.main.transactions.injection.TransactionsModule;
import com.shcherbinin.sasha.accountingfinance.presenter.main.transactions.injection.TransactionsStubComponent;
import dagger.Module;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {
        ActivityModule.class,
        MainActivityComponent.MainActivityModule.class,
})
public interface MainActivityComponent extends
        ActivityComponent<MainActivity>,
        DashboardStubComponent,
        TransactionsStubComponent,
        TransactionStubComponent,
        AccountsStubComponent {

    @Subcomponent.Builder
    interface Builder extends ActivityComponentBuilder<MainActivityComponent> {

        ActivityComponentBuilder<MainActivityComponent> dashboardModule(DashboardModule module);

        ActivityComponentBuilder<MainActivityComponent> accountsModule(AccountsModule module);

        ActivityComponentBuilder<MainActivityComponent> transactionsModule(TransactionsModule module);

        ActivityComponentBuilder<MainActivityComponent> transactionModule(TransactionModule module);
    }

    @Module(includes = {DashboardModule.class, AccountsModule.class, TransactionsModule.class, TransactionModule.class})
    class MainActivityModule {

    }

}
