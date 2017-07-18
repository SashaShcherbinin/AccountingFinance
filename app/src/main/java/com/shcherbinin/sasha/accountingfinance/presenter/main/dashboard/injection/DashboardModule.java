package com.shcherbinin.sasha.accountingfinance.presenter.main.dashboard.injection;

import com.shcherbinin.sasha.accountingfinance.presenter.main.dashboard.DashboardContract;
import dagger.Module;
import dagger.Provides;

@Module
public final class DashboardModule {

    private DashboardContract.EventDelegate mEventDelegate;

    public DashboardModule(DashboardContract.EventDelegate eventDelegate) {
        mEventDelegate = eventDelegate;
    }

    @Provides
    DashboardContract.EventDelegate provideEventDelegate() {
        return mEventDelegate;
    }

}
