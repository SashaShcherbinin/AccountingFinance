package com.shcherbinin.sasha.accountingfinance.domain.account;

import com.shcherbinin.sasha.accountingfinance.data.repository.account.AccountRepositoryImpl;
import io.reactivex.Completable;
import io.reactivex.Observable;

import javax.inject.Inject;
import java.util.List;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/16/17
 * Time: 8:24 PM
 */

public class AccountInteractor {

    private AccountRepository mAccountRepository;

    @Inject
    AccountInteractor(AccountRepositoryImpl accountRepository) {
        super();
        mAccountRepository = accountRepository;
    }

    public Observable<List<AccountModel>> getAccounts() {
        return mAccountRepository.getAccounts();
    }

    public Completable deleteAccount(AccountModel accountModel) {
        return mAccountRepository.deleteAccount(accountModel);
    }

    public Completable addAccount(String name) {
        return mAccountRepository.addAccount(name);
    }

    public Observable<AccountModel> getActiveAccount() {
        return mAccountRepository.getActiveAccount();
    }

    public Completable selectAccount(AccountModel accountModel) {
        return mAccountRepository.selectAccount(accountModel);
    }

    public Completable updateAccount(AccountModel accountModel) {
        return mAccountRepository.updateAccount(accountModel);
    }
}
