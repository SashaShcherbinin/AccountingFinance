package com.shcherbinin.sasha.accountingfinance.presenter.main.dashboard;

import com.shcherbinin.sasha.accountingfinance.domain.dashboard.DashboardModel;

public interface DashboardContract {

    interface View {

        void showContent(DashboardModel dashboardModel);
    }

    interface EventListener {

    }

    interface EventDelegate {

    }
}