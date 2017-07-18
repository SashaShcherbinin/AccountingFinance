package com.shcherbinin.sasha.accountingfinance.di.activity;

import com.shcherbinin.sasha.accountingfinance.presenter.main.MainActivity;
import com.shcherbinin.sasha.accountingfinance.presenter.main.MainActivityComponent;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/16/17
 * Time: 6:11 PM
 */

@Module(subcomponents = {
        MainActivityComponent.class,
})
public abstract class ActivityBindingsModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    public abstract ActivityComponentBuilder mainActivityComponentBuilder(MainActivityComponent.Builder builder);

}
