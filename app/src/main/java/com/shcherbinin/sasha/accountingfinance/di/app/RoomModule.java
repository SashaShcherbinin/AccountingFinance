package com.shcherbinin.sasha.accountingfinance.di.app;

import android.arch.persistence.room.Room;
import android.content.Context;
import com.shcherbinin.sasha.accountingfinance.data.db.AccountDao;
import com.shcherbinin.sasha.accountingfinance.data.db.AppDatabase;
import com.shcherbinin.sasha.accountingfinance.data.db.TransactionDao;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/17/17
 * Time: 6:32 PM
 */

@Module
public class RoomModule {

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "app.db").build();
    }

    @Provides
    @Singleton
    AccountDao provideAccountDao(AppDatabase appDatabase) {
        return appDatabase.accountDao();
    }

    @Provides
    @Singleton
    TransactionDao provideTransactionDao(AppDatabase appDatabase) {
        return appDatabase.transactionDao();
    }
}
