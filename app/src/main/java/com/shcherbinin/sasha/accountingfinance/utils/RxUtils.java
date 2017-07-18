package com.shcherbinin.sasha.accountingfinance.utils;

import android.support.annotation.Nullable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import java.util.HashMap;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/16/17
 * Time: 7:17 PM
 */

public class RxUtils {

    private static final HashMap<Object, SpecificCompositeDisposable> sSubscriptions = new HashMap<>();

    public static void manage(Object tag, Disposable subscription) {
        SpecificCompositeDisposable subscriptions = sSubscriptions.get(tag);
        if (subscriptions == null) {
            subscriptions = new SpecificCompositeDisposable();
            sSubscriptions.put(tag, subscriptions);
        }

        subscriptions.add(subscription);
    }

    public static void manage(Object tag, Object subscriptionTab, @Nullable Disposable subscription) {
        SpecificCompositeDisposable subscriptions = sSubscriptions.get(tag);
        if (subscriptions == null) {
            subscriptions = new SpecificCompositeDisposable();
            sSubscriptions.put(tag, subscriptions);
        }

        subscriptions.add(subscriptionTab, subscription);
    }

    public static void unsubscribe(Object tag) {
        SpecificCompositeDisposable subscriptions = sSubscriptions.get(tag);
        if (subscriptions != null) {
            subscriptions.dispose();
            sSubscriptions.remove(tag);
        }
    }

    private static class SpecificCompositeDisposable {

        final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
        final HashMap<Object, Disposable> mDisposableHashMap = new HashMap<>();

        void dispose() {
            mCompositeDisposable.dispose();
        }

        boolean add(Disposable disposable) {
            return mCompositeDisposable.add(disposable);
        }

        boolean add(Object subscriptionTab, @Nullable Disposable disposable) {
            if (disposable == null) {
                return false;
            }
            Disposable oldDisposable = mDisposableHashMap.get(subscriptionTab);
            if (oldDisposable != null) {
                mCompositeDisposable.remove(oldDisposable);
            }
            mDisposableHashMap.put(subscriptionTab, disposable);
            return mCompositeDisposable.add(disposable);
        }
    }

}