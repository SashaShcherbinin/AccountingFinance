package com.shcherbinin.sasha.accountingfinance.domain.transaction;

import com.shcherbinin.sasha.accountingfinance.domain.account.AccountModel;
import io.reactivex.Completable;
import io.reactivex.Observable;

import java.util.List;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/16/17
 * Time: 8:24 PM
 */

public interface TransactionRepository {

    Observable<List<TransactionModel>> getTransactions();

    Completable addTransaction(AccountModel accountModel, String title, String amount);
}
