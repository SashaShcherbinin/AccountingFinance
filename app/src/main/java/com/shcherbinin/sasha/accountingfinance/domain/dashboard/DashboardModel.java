package com.shcherbinin.sasha.accountingfinance.domain.dashboard;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/16/17
 * Time: 8:36 PM
 */

public class DashboardModel {

    private String accountName;
    private String balance;
    private String income;
    private String expenses;

    public DashboardModel(String accountName, String balance, String income, String expenses) {
        this.accountName = accountName;
        this.balance = balance;
        this.income = income;
        this.expenses = expenses;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getExpenses() {
        return expenses;
    }

    public void setExpenses(String expenses) {
        this.expenses = expenses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DashboardModel)) return false;

        DashboardModel that = (DashboardModel) o;

        if (accountName != null ? !accountName.equals(that.accountName) : that.accountName != null) return false;
        if (balance != null ? !balance.equals(that.balance) : that.balance != null) return false;
        if (income != null ? !income.equals(that.income) : that.income != null) return false;
        return expenses != null ? expenses.equals(that.expenses) : that.expenses == null;

    }

    @Override
    public int hashCode() {
        int result = accountName != null ? accountName.hashCode() : 0;
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (income != null ? income.hashCode() : 0);
        result = 31 * result + (expenses != null ? expenses.hashCode() : 0);
        return result;
    }
}
