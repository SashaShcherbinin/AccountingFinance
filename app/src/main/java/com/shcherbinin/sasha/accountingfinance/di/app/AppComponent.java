package com.shcherbinin.sasha.accountingfinance.di.app;

import com.shcherbinin.sasha.accountingfinance.App;
import com.shcherbinin.sasha.accountingfinance.di.activity.ActivityBindingsModule;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/16/17
 * Time: 6:11 PM
 */

@Singleton
@Component(modules = {
        ActivityBindingsModule.class,
        AppModule.class,
        RoomModule.class
})
public interface AppComponent {

    void inject(App app);
}
