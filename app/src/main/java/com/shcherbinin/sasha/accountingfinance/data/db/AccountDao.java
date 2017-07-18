package com.shcherbinin.sasha.accountingfinance.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.shcherbinin.sasha.accountingfinance.domain.account.AccountModel;
import io.reactivex.Flowable;

import java.util.List;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/17/17
 * Time: 6:21 PM
 */

@Dao
public interface AccountDao {

    @Query("SELECT * FROM " + AccountModel.TABLE_NAME)
    Flowable<List<AccountModel>> getAll();

    @Query("SELECT * FROM " + AccountModel.TABLE_NAME + " WHERE " + AccountModel.COLUMN_ID + " = :accountId")
    Flowable<AccountModel> get(String accountId);

    @Insert
    void insertAll(AccountModel... accountModels);

    @Update
    void update(AccountModel... accountModels);

    @Delete
    void delete(AccountModel accountModel);
}
