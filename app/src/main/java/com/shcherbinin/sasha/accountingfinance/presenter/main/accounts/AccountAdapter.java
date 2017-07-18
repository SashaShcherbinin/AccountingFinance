package com.shcherbinin.sasha.accountingfinance.presenter.main.accounts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.shcherbinin.sasha.accountingfinance.databinding.ItemAccountBinding;
import com.shcherbinin.sasha.accountingfinance.domain.account.AccountModel;

import java.util.List;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 7/17/17
 * Time: 12:45 PM
 */

class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.MyViewHolder> {

    private List<AccountModel> mAccountModels;
    private AccountsContract.EventListener mEventListener;
    private AccountModel mAccountModel;

    AccountAdapter(AccountsContract.EventListener eventListener) {
        super();
        mEventListener = eventListener;
    }

    public void setData(List<AccountModel> accountModels) {
        mAccountModels = accountModels;
        notifyDataSetChanged();
    }

    public void setData(AccountModel accountModel) {
        mAccountModel = accountModel;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemAccountBinding itemBinding = ItemAccountBinding.inflate(layoutInflater, parent, false);
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

        private final ItemAccountBinding binding;

        MyViewHolder(ItemAccountBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(AccountModel item) {
            binding.setAccount(item);
            binding.setActive(mAccountModel != null && item.getId().equals(mAccountModel.getId()));
            binding.setEvent(mEventListener);
            binding.executePendingBindings();
        }
    }
}