package com.shcherbinin.sasha.accountingfinance.data.repository.dashboard;

import com.shcherbinin.sasha.accountingfinance.data.db.AccountDao;
import com.shcherbinin.sasha.accountingfinance.data.db.TransactionDao;
import com.shcherbinin.sasha.accountingfinance.data.utils.AccountHolder;
import com.shcherbinin.sasha.accountingfinance.domain.dashboard.DashboardModel;
import com.shcherbinin.sasha.accountingfinance.domain.dashboard.DashboardRepository;
import com.shcherbinin.sasha.accountingfinance.domain.transaction.TransactionModel;
import io.reactivex.Observable;

import javax.inject.Inject;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/16/17
 * Time: 8:24 PM
 */

public class DashboardRepositoryImpl implements DashboardRepository {

    private AccountDao mAccountDao;
    private TransactionDao mTransactionDao;
    private AccountHolder mAccountHolder;

    @Inject
    DashboardRepositoryImpl(AccountDao accountDao, TransactionDao transactionDao, AccountHolder accountHolder) {
        super();
        mAccountDao = accountDao;
        mTransactionDao = transactionDao;
        mAccountHolder = accountHolder;
    }

    @Override
    public Observable<DashboardModel> get() {
        return mAccountHolder.getId().switchMap(s -> Observable.zip(
                mAccountDao.get(s).toObservable(),
                mTransactionDao.getAll(s).toObservable(),
                (accountModel, transactionModels) -> {
                    float balance = 0;
                    float income = 0;
                    float expenses = 0;
                    for (TransactionModel transactionModel : transactionModels) {
                        float amount = transactionModel.getAmount();
                        balance += amount;
                        if (amount > 0) {
                            income += amount;
                        } else {
                            expenses += amount;
                        }
                    }
                    return new DashboardModel(accountModel.getName(),
                            String.valueOf(balance),
                            String.valueOf(income),
                            String.valueOf(expenses));
                }));
    }
}
