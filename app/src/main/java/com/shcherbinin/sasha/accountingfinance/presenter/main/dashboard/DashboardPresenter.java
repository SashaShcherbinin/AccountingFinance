package com.shcherbinin.sasha.accountingfinance.presenter.main.dashboard;

import android.support.annotation.Nullable;
import com.shcherbinin.sasha.accountingfinance.domain.dashboard.DashboardInteractor;
import com.shcherbinin.sasha.accountingfinance.presenter.common.BasePresenter;
import com.shcherbinin.sasha.accountingfinance.utils.RxUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

import javax.inject.Inject;

public final class DashboardPresenter extends BasePresenter<DashboardContract.View> implements
        DashboardContract.EventListener {

    private DashboardContract.EventDelegate mEventDelegate;
    private DashboardInteractor mDashboardInteractor;

    @Inject
    DashboardPresenter(DashboardContract.EventDelegate eventDelegate,
                       DashboardInteractor dashboardInteractor) {
        mEventDelegate = eventDelegate;
        mDashboardInteractor = dashboardInteractor;
    }

    @Override
    public void attachView(DashboardContract.View view) {
        super.attachView(view);
        RxUtils.manage(this, mDashboardInteractor.get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dashboardModel -> mView.showContent(dashboardModel), Timber::e));
    }

    @Nullable
    DashboardContract.View getView() {
        return mView;
    }

}