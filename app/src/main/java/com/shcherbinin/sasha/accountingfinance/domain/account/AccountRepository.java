package com.shcherbinin.sasha.accountingfinance.domain.account;

import io.reactivex.Completable;
import io.reactivex.Observable;

import java.util.List;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/16/17
 * Time: 8:24 PM
 */

public interface AccountRepository {

    Observable<List<AccountModel>> getAccounts();

    Completable deleteAccount(AccountModel accountModel);

    Completable addAccount(String name);

    Observable<AccountModel> getActiveAccount();

    Completable selectAccount(AccountModel accountModel);

    Completable updateAccount(AccountModel accountModel);
}
