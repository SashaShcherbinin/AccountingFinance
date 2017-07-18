package com.shcherbinin.sasha.accountingfinance.presenter.main.transactions.injection;

import com.shcherbinin.sasha.accountingfinance.presenter.main.transactions.TransactionsFragment;
import dagger.Subcomponent;

@Subcomponent(modules = {
        TransactionsModule.class
})
public interface TransactionsStubComponent {

    void inject(TransactionsFragment fragment);
}