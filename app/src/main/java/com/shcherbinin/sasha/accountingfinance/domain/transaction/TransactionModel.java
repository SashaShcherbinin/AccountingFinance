package com.shcherbinin.sasha.accountingfinance.domain.transaction;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import com.shcherbinin.sasha.accountingfinance.domain.account.AccountModel;

import java.util.UUID;

import static android.arch.persistence.room.ForeignKey.CASCADE;
import static com.shcherbinin.sasha.accountingfinance.domain.transaction.TransactionModel.COLUMN_ACCOUNT_ID;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/16/17
 * Time: 8:36 PM
 */

@Entity(tableName = TransactionModel.TABLE_NAME,
        indices = @Index(COLUMN_ACCOUNT_ID),
        foreignKeys = @ForeignKey(entity = AccountModel.class,
                                  parentColumns = AccountModel.COLUMN_ID,
                                  childColumns = TransactionModel.COLUMN_ACCOUNT_ID,
                                  onDelete=CASCADE))
public class TransactionModel {

    @SuppressWarnings("WeakerAccess")
    public static final String TABLE_NAME = "transaction_";
    @SuppressWarnings("WeakerAccess")
    public static final String COLUMN_AMOUNT = "amount";
    @SuppressWarnings("WeakerAccess")
    public static final String COLUMN_TITLE = "title";
    @SuppressWarnings("WeakerAccess")
    public static final String COLUMN_ACCOUNT_ID = "account_id";
    @SuppressWarnings("WeakerAccess")
    public static final String COLUMN_DATE = "date";
    @SuppressWarnings("WeakerAccess")
    public static final String COLUMN_ID = "id";

    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    private String id;
    @ColumnInfo(name = COLUMN_ACCOUNT_ID)
    private String accountId;
    @ColumnInfo(name = COLUMN_TITLE)
    private String title;
    @ColumnInfo(name = COLUMN_AMOUNT)
    private float amount;
    @ColumnInfo(name = COLUMN_DATE)
    private long date;

    public TransactionModel(String accountId, String title, float amount) {
        this.id = UUID.randomUUID().toString();
        this.accountId = accountId;
        this.title = title;
        this.amount = amount;
        this.date = System.currentTimeMillis();
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

}
