package com.shcherbinin.sasha.accountingfinance.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.shcherbinin.sasha.accountingfinance.domain.transaction.TransactionModel;
import io.reactivex.Flowable;

import java.util.List;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/17/17
 * Time: 6:21 PM
 */

@Dao
public interface TransactionDao {

    @Query("SELECT * FROM " + TransactionModel.TABLE_NAME +
                   " WHERE " + TransactionModel.COLUMN_ACCOUNT_ID + " = :accountId" +
                   " ORDER BY " + TransactionModel.COLUMN_DATE +
                   " DESC" +
                   " LIMIT 30")
    Flowable<List<TransactionModel>> getAll(String accountId);

    @Insert
    void insertAll(TransactionModel... transactionModels);

    @Delete
    void delete(TransactionModel transactionModel);
}
