package com.shcherbinin.sasha.accountingfinance.presenter.main.transaction;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.shcherbinin.sasha.accountingfinance.BR;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/18/17
 * Time: 1:51 PM
 */

public class TransactionViewModel extends BaseObservable {

    private String title;
    private String amount;
    private boolean enable;
    private TransactionContract.EventListener mEventListener;

    @SuppressWarnings("WeakerAccess")
    public TransactionViewModel(TransactionContract.EventListener eventListener) {
        super();
        mEventListener = eventListener;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
        validate();
    }

    @Bindable
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
        notifyPropertyChanged(BR.amount);
        validate();
    }

    @Bindable
    public boolean isEnable() {
        return enable;
    }

    @SuppressWarnings("WeakerAccess")
    public void setEnable(boolean enable) {
        this.enable = enable;
        notifyPropertyChanged(BR.enable);
    }

    @SuppressWarnings("WeakerAccess")
    public void validate() {
        try {
            setEnable(title.trim().length() > 2 && amount.trim().length() > 0);
        } catch (Exception e) {
            setEnable(false);
        }
    }

    public void onAddClicked() {
        mEventListener.onAddClicked(title, amount);
    }
}
