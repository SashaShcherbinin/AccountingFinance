package com.shcherbinin.sasha.accountingfinance.presenter.common.handler;

import android.app.Activity;
import com.shcherbinin.sasha.accountingfinance.R;
import com.shcherbinin.sasha.accountingfinance.exeption.NoAccountException;

import javax.inject.Inject;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/18/17
 * Time: 1:26 PM
 */

public class DefaultErrorHandler {

    private Activity mActivity;

    @Inject
    DefaultErrorHandler(Activity activity) {
        mActivity = activity;
    }

    public void handleError(Throwable throwable, ErrorView errorView) {
        if (throwable instanceof NoAccountException) {
            errorView.showError(mActivity.getString(R.string.GENERAL_ERROR_NO_AN_ACCOUNT));
        } else {
            errorView.showError(mActivity.getString(R.string.GENERAL_ERROR_COMMON));
        }
    }

    public interface ErrorView {
        void showError(String message);
    }

}
