package com.shcherbinin.sasha.accountingfinance.presenter.main.dashboard.injection;

import com.shcherbinin.sasha.accountingfinance.presenter.main.dashboard.DashboardFragment;
import dagger.Subcomponent;

@Subcomponent(modules = {
        DashboardModule.class
})
public interface DashboardStubComponent {

    void inject(DashboardFragment fragment);
}