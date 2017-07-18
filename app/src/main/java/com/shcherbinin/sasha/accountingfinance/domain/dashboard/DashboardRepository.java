package com.shcherbinin.sasha.accountingfinance.domain.dashboard;

import io.reactivex.Observable;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/16/17
 * Time: 8:24 PM
 */

public interface DashboardRepository {

    Observable<DashboardModel> get();
}
