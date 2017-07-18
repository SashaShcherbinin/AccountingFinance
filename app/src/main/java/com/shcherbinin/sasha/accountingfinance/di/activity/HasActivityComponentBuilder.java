package com.shcherbinin.sasha.accountingfinance.di.activity;

import android.app.Activity;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/16/17
 * Time: 6:11 PM
 */

public interface HasActivityComponentBuilder {
    ActivityComponentBuilder getActivityComponentBuilder(Class<? extends Activity> aClass);
}
