package com.shcherbinin.sasha.accountingfinance.presenter.main.accounts;

import android.support.annotation.Nullable;
import com.shcherbinin.sasha.accountingfinance.domain.account.AccountInteractor;
import com.shcherbinin.sasha.accountingfinance.domain.account.AccountModel;
import com.shcherbinin.sasha.accountingfinance.presenter.common.BasePresenter;
import com.shcherbinin.sasha.accountingfinance.utils.RxUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

import javax.inject.Inject;

public final class AccountsPresenter extends BasePresenter<AccountsContract.View> implements
        AccountsContract.EventListener {

    private AccountsContract.EventDelegate mEventDelegate;
    private AccountInteractor mAccountInteractor;

    @Inject
    AccountsPresenter(AccountsContract.EventDelegate eventDelegate, AccountInteractor accountInteractor) {
        mEventDelegate = eventDelegate;
        mAccountInteractor = accountInteractor;
    }

    @Override
    public void attachView(AccountsContract.View view) {
        super.attachView(view);
        RxUtils.manage(this, mAccountInteractor.getAccounts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mView.showLoading())
                .subscribe(accountModels -> mView.showContent(accountModels), Timber::e));

        RxUtils.manage(this, mAccountInteractor.getActiveAccount()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mView.showLoading())
                .subscribe(accountModel -> mView.showContent(accountModel), Timber::e));
    }

    @Nullable
    AccountsContract.View getView() {
        return mView;
    }

    @Override
    public void onAddClicked() {
        mView.showAddDialog();
    }

    @Override
    public void onSelected(AccountModel accountModel) {
        RxUtils.manage(this, mAccountInteractor.selectAccount(accountModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    @Override
    public void onEditClicked(AccountModel accountModel) {
        mView.showEditDialog(accountModel);
    }

    @Override
    public void onDeleteClicked(AccountModel accountModel) {
        RxUtils.manage(this, "delete", mAccountInteractor.deleteAccount(accountModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    @SuppressWarnings("WeakerAccess")
    public void onAddAccount(String name) {
        RxUtils.manage(this, "add", mAccountInteractor.addAccount(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    @SuppressWarnings("WeakerAccess")
    public void onSaveAccount(AccountModel accountModel) {
        RxUtils.manage(this, "save", mAccountInteractor.updateAccount(accountModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }
}