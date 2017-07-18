package com.shcherbinin.sasha.accountingfinance.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.shcherbinin.sasha.accountingfinance.domain.account.AccountModel;
import com.shcherbinin.sasha.accountingfinance.domain.transaction.TransactionModel;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/17/17
 * Time: 6:23 PM
 */

@Database(entities = {AccountModel.class, TransactionModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract AccountDao accountDao();

    public abstract TransactionDao transactionDao();
}