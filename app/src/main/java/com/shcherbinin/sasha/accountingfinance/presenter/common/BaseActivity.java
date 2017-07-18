package com.shcherbinin.sasha.accountingfinance.presenter.common;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import com.shcherbinin.sasha.accountingfinance.di.activity.ActivityComponent;
import com.shcherbinin.sasha.accountingfinance.di.activity.HasComponent;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/16/17
 * Time: 6:31 PM
 */

public abstract class BaseActivity extends AppCompatActivity implements
        HasComponent<ActivityComponent> {

    public static final String BASE_ACTIVITY_COMPONENT = "base_activity_component";

    @Override
    public Object getSystemService(@NonNull String name) {
        if (BASE_ACTIVITY_COMPONENT.equals(name)) {
            return getComponent();
        }
        return super.getSystemService(name);
    }

}
