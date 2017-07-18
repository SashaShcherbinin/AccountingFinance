package com.shcherbinin.sasha.accountingfinance.data.repository.account;

import com.shcherbinin.sasha.accountingfinance.data.db.AccountDao;
import com.shcherbinin.sasha.accountingfinance.data.utils.AccountHolder;
import com.shcherbinin.sasha.accountingfinance.domain.account.AccountModel;
import com.shcherbinin.sasha.accountingfinance.domain.account.AccountRepository;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;

import javax.inject.Inject;
import java.util.List;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/16/17
 * Time: 8:24 PM
 */

public class AccountRepositoryImpl implements AccountRepository {

    private AccountDao mAccountDao;
    private AccountHolder mAccountHolder;

    @Inject
    AccountRepositoryImpl(AccountDao accountDao, AccountHolder accountHolder) {
        super();
        mAccountDao = accountDao;
        mAccountHolder = accountHolder;
    }

    @Override
    public Observable<List<AccountModel>> getAccounts() {
        return mAccountDao.getAll().toObservable();
    }

    @Override
    public Completable deleteAccount(AccountModel accountModel) {
        return mAccountHolder.getId().take(1).singleOrError().flatMapCompletable(s -> Completable.create(e -> {
            if (s.equals(accountModel.getId())) {
                mAccountHolder.putId(null).blockingAwait();
            }
            mAccountDao.delete(accountModel);
            e.onComplete();
        }));
    }

    @Override
    public Completable addAccount(String name) {
        return Observable.create((ObservableOnSubscribe<AccountModel>) e -> {
            AccountModel accountModel = new AccountModel(name);
            mAccountDao.insertAll(accountModel);
            e.onNext(accountModel);
            e.onComplete();
        }).flatMapCompletable(accountModel -> mAccountHolder.putId(accountModel.getId()));
    }

    @Override
    public Observable<AccountModel> getActiveAccount() {
        return mAccountHolder.getId()
                .switchMap(id -> mAccountDao.get(id).toObservable());
    }

    @Override
    public Completable selectAccount(AccountModel accountModel) {
        return mAccountHolder.putId(accountModel.getId());
    }

    @Override
    public Completable updateAccount(AccountModel accountModel) {
        return Completable.create(e -> {
            mAccountDao.update(accountModel);
            e.onComplete();
        });
    }

}
