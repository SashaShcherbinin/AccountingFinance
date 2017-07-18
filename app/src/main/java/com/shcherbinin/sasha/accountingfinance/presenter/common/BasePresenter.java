package com.shcherbinin.sasha.accountingfinance.presenter.common;

import com.shcherbinin.sasha.accountingfinance.utils.RxUtils;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/16/17
 * Time: 7:09 PM
 */

public abstract class BasePresenter<T> {

    protected T mView;

    public void attachView(T view) {
        mView = view;
    }

    public void detachView() {
        mView = null;
        RxUtils.unsubscribe(this);
    }


}
