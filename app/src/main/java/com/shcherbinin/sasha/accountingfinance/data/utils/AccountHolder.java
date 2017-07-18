package com.shcherbinin.sasha.accountingfinance.data.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.subjects.PublishSubject;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/18/17
 * Time: 11:10 AM
 */

@Singleton
public class AccountHolder {

    private static final String NAME = "account";
    private static final String ID = "id";

    private final SharedPreferences mSharedPreferences;
    private PublishSubject<String> mSharedSubject = PublishSubject.create();

    @Inject
    AccountHolder(Context context) {
        mSharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public Observable<String> getId() {
        return Observable.create((ObservableOnSubscribe<String>) e -> {
            String string = mSharedPreferences.getString(ID, null);
            if (string != null) {
                e.onNext(string);
            }
            e.onComplete();
        }).repeatWhen(objectObservable -> mSharedSubject);
    }

    @SuppressLint("ApplySharedPref")
    public Completable putId(@Nullable String id) {
        return Completable.create(e -> {
            mSharedPreferences.edit().putString("id", id).commit();
            mSharedSubject.onNext("");
            e.onComplete();
        });
    }
}
