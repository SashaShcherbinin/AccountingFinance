package com.shcherbinin.sasha.accountingfinance.di.activity;

import android.app.Activity;
import dagger.MembersInjector;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/16/17
 * Time: 6:11 PM
 */

public interface ActivityComponent<A extends Activity> extends MembersInjector<A> {
}
