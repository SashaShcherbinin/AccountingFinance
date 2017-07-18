package com.shcherbinin.sasha.accountingfinance.data.repository.transaction;

import com.shcherbinin.sasha.accountingfinance.data.db.TransactionDao;
import com.shcherbinin.sasha.accountingfinance.data.utils.AccountHolder;
import com.shcherbinin.sasha.accountingfinance.domain.account.AccountModel;
import com.shcherbinin.sasha.accountingfinance.domain.transaction.TransactionModel;
import com.shcherbinin.sasha.accountingfinance.domain.transaction.TransactionRepository;
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

public class TransactionRepositoryImpl implements TransactionRepository {

    private AccountHolder mAccountHolder;
    private TransactionDao mTransactionDao;

    @Inject
    TransactionRepositoryImpl(AccountHolder accountHolder, TransactionDao transactionDao) {
        super();
        mAccountHolder = accountHolder;
        mTransactionDao = transactionDao;
    }

    @Override
    public Observable<List<TransactionModel>> getTransactions() {
        return mAccountHolder.getId().switchMap(accountId -> mTransactionDao.getAll(accountId).toObservable());
    }

    @Override
    public Completable addTransaction(AccountModel accountModel, String title, String amount) {
        return Completable.create(e -> {
            mTransactionDao.insertAll(new TransactionModel(accountModel.getId(), title, Float.valueOf(amount)));
            e.onComplete();
        });
    }
}
