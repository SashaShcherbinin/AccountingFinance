package com.shcherbinin.sasha.accountingfinance.presenter.main.transaction.injection;

import com.shcherbinin.sasha.accountingfinance.presenter.main.transaction.TransactionDialog;
import dagger.Subcomponent;

@Subcomponent(modules = {
        TransactionModule.class
})
public interface TransactionStubComponent {

    void inject(TransactionDialog fragment);
}