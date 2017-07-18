package com.shcherbinin.sasha.accountingfinance.di.activity;

import android.app.Activity;
import dagger.MapKey;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/16/17
 * Time: 6:11 PM
 */

@MapKey
public @interface ActivityKey {
    Class<? extends Activity> value();
}
