package com.shcherbinin.sasha.accountingfinance.di;

import com.shcherbinin.sasha.accountingfinance.di.activity.HasComponent;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/16/17
 * Time: 6:46 PM
 */

public class Injector {
    @SuppressWarnings("unchecked")
    public static <C> C getComponent(Object o, Class<C> component) {
        return component.cast(((HasComponent)o).getComponent());
    }
}
