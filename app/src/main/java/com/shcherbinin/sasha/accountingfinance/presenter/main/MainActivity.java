package com.shcherbinin.sasha.accountingfinance.presenter.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.shcherbinin.sasha.accountingfinance.App;
import com.shcherbinin.sasha.accountingfinance.R;
import com.shcherbinin.sasha.accountingfinance.databinding.ActivityMainBinding;
import com.shcherbinin.sasha.accountingfinance.di.activity.ActivityComponent;
import com.shcherbinin.sasha.accountingfinance.di.activity.ActivityModule;
import com.shcherbinin.sasha.accountingfinance.presenter.common.BaseActivity;
import com.shcherbinin.sasha.accountingfinance.presenter.main.dashboard.DashboardFragment;
import com.shcherbinin.sasha.accountingfinance.presenter.main.dashboard.injection.DashboardModule;
import com.shcherbinin.sasha.accountingfinance.presenter.main.transaction.injection.TransactionModule;
import com.shcherbinin.sasha.accountingfinance.presenter.main.transactions.TransactionsFragment;
import com.shcherbinin.sasha.accountingfinance.presenter.main.transactions.injection.TransactionsModule;
import com.shcherbinin.sasha.accountingfinance.presenter.main.accounts.AccountsFragment;
import com.shcherbinin.sasha.accountingfinance.presenter.main.accounts.injection.AccountsModule;

public class MainActivity extends BaseActivity {

    private static final int COUNT_TAB = 3;

    private static final int TAB_DASHBOARD = 0;
    private static final int TAB_ACCOUNTS = 1;
    private static final int TAB_TRANSACTIONS = 2;

    public static void run(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    private MainRouter mMainRouter;
    private MainActivityComponent mComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()));
        binding.tabs.setupWithViewPager(binding.viewPager);
        binding.setRouter(getRouter());
        setSupportActionBar(binding.toolbar);
    }

    @Override
    public ActivityComponent getComponent() {
        if (mComponent == null) {
            MainActivityComponent.Builder builder = (MainActivityComponent.Builder) App.get(this)
                    .getActivityComponentBuilder(MainActivity.class);
            builder.activityModule(new ActivityModule(this));
            builder.dashboardModule(new DashboardModule(getRouter()));
            builder.accountsModule(new AccountsModule(getRouter()));
            builder.transactionsModule(new TransactionsModule(getRouter()));
            builder.transactionModule(new TransactionModule(getRouter()));
            mComponent = builder.build();
        }
        return mComponent;
    }

    public MainRouter getRouter() {
        if (mMainRouter == null) {
            mMainRouter = new MainRouter(this);
        }
        return mMainRouter;
    }


    private class FragmentPagerAdapter extends FragmentStatePagerAdapter {

        FragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case TAB_DASHBOARD:
                    return DashboardFragment.newInstance();
                case TAB_ACCOUNTS:
                    return AccountsFragment.newInstance();
                case TAB_TRANSACTIONS:
                    return TransactionsFragment.newInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            return COUNT_TAB;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case TAB_DASHBOARD:
                    return getString(R.string.MAIN_TAB_DASHBOARD);
                case TAB_ACCOUNTS:
                    return getString(R.string.MAIN_TAB_ACCOUNTS);
                case TAB_TRANSACTIONS:
                    return getString(R.string.MAIN_TAB_TRANSACTIONS);
                default:
                    throw new IllegalArgumentException("Exceeded max of tabs");
            }
        }
    }
}
