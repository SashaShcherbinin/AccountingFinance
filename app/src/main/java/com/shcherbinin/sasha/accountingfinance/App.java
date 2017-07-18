package com.shcherbinin.sasha.accountingfinance;

import android.app.Activity;
import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.util.Log;
import com.shcherbinin.sasha.accountingfinance.di.activity.ActivityComponentBuilder;
import com.shcherbinin.sasha.accountingfinance.di.activity.HasActivityComponentBuilder;
import com.shcherbinin.sasha.accountingfinance.di.app.AppComponent;
import com.shcherbinin.sasha.accountingfinance.di.app.AppModule;
import com.shcherbinin.sasha.accountingfinance.di.app.DaggerAppComponent;
import timber.log.Timber;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.Map;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/16/17
 * Time: 6:11 PM
 */

public class App extends MultiDexApplication implements HasActivityComponentBuilder {

    private AppComponent mComponent;

    @Inject Map<Class<? extends Activity>, Provider<ActivityComponentBuilder>> mActivityComponentBuilders;

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(BuildConfig.DEBUG ? new Timber.DebugTree() : new RemoteTree());

        mComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        mComponent.inject(this);
    }

    public AppComponent getComponent() {
        return mComponent;
    }

    @Override
    public ActivityComponentBuilder getActivityComponentBuilder(Class<? extends Activity> aClass) {
        return mActivityComponentBuilders.get(aClass).get();
    }

    private static class RemoteTree extends Timber.Tree {

        @Override
        protected boolean isLoggable(String tag, int priority) {
            return priority >= Log.ERROR;
        }

        @Override
        protected void log(int priority, String tag, String message, Throwable throwable) {
            // TODO: add logging library here
        }
    }

}
