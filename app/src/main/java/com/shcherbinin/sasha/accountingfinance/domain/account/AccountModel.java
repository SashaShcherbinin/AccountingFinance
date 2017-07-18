package com.shcherbinin.sasha.accountingfinance.domain.account;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.UUID;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/16/17
 * Time: 8:36 PM
 */

@Entity(tableName = AccountModel.TABLE_NAME)
public class AccountModel {

    @SuppressWarnings("WeakerAccess")
    public static final String TABLE_NAME = "account";
    @SuppressWarnings("WeakerAccess")
    public static final String COLUMN_NAME = "name";
    @SuppressWarnings("WeakerAccess")
    public static final String COLUMN_ID = "id";

    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    private String id;
    @ColumnInfo(name = COLUMN_NAME)
    private String name;

    public AccountModel(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
