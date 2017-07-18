package com.shcherbinin.sasha.accountingfinance.domain.dashboard;

import com.shcherbinin.sasha.accountingfinance.data.repository.dashboard.DashboardRepositoryImpl;
import io.reactivex.Observable;

import javax.inject.Inject;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/16/17
 * Time: 8:24 PM
 */

public class DashboardInteractor {

    private DashboardRepository mDashboardRepository;

    @Inject
    DashboardInteractor(DashboardRepositoryImpl dashboardRepository) {
        super();
        mDashboardRepository = dashboardRepository;
    }

    public Observable<DashboardModel> get() {
        return mDashboardRepository.get();
    }
}
