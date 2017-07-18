package com.shcherbinin.sasha.accountingfinance.di.activity;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/16/17
 * Time: 6:11 PM
 */

public interface ActivityComponentBuilder<C extends ActivityComponent> {

    ActivityComponentBuilder<C> activityModule(ActivityModule module);

    C build();
}
