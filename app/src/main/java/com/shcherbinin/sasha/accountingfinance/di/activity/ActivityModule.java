package com.shcherbinin.sasha.accountingfinance.di.activity;

import android.app.Activity;
import dagger.Module;
import dagger.Provides;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/16/17
 * Time: 6:11 PM
 */

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

}
