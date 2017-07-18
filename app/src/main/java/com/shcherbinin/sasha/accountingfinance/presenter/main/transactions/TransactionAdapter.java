package com.shcherbinin.sasha.accountingfinance.presenter.main.transactions;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.shcherbinin.sasha.accountingfinance.databinding.ItemTransactionBinding;
import com.shcherbinin.sasha.accountingfinance.domain.transaction.TransactionModel;

import java.util.List;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/17/17
 * Time: 12:45 PM
 */

class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {

    private List<TransactionModel> mAccountModels;
    private TransactionsContract.EventListener mEventListener;

    TransactionAdapter(TransactionsContract.EventListener eventListener) {
        super();
        mEventListener = eventListener;
    }

    public void setData(List<TransactionModel> transactionModels) {
        mAccountModels = transactionModels;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemTransactionBinding itemBinding = ItemTransactionBinding.inflate(layoutInflater, parent, false);
        return new MyViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(mAccountModels.get(position));
    }

    @Override
    public int getItemCount() {
        return mAccountModels != null ? mAccountModels.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ItemTransactionBinding binding;

        MyViewHolder(ItemTransactionBinding binding) {
            super(binding.getRoot());
            binding.setEvent(mEventListener);
            this.binding = binding;
        }

        void bind(TransactionModel item) {
            binding.setTransaction(item);
            binding.executePendingBindings();
        }
    }
}