package com.shcherbinin.sasha.accountingfinance.domain.transaction;

import com.shcherbinin.sasha.accountingfinance.data.repository.account.AccountRepositoryImpl;
import com.shcherbinin.sasha.accountingfinance.data.repository.transaction.TransactionRepositoryImpl;
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

public class TransactionInteractor {

    private TransactionRepository mTransactionRepository;
    private AccountRepositoryImpl mAccountRepository;

    @Inject
    TransactionInteractor(TransactionRepositoryImpl transactionRepository,
                          AccountRepositoryImpl accountRepository) {
        super();
        mTransactionRepository = transactionRepository;
        mAccountRepository = accountRepository;
    }

    public Observable<List<TransactionModel>> getTransactions() {
        return mTransactionRepository.getTransactions();
    }

    public Completable addTransaction(String title, String amount) {
        return mAccountRepository.getActiveAccount().take(1).singleOrError()
                .flatMapCompletable(accountModel -> mTransactionRepository.addTransaction(accountModel, title, amount));
    }
}
