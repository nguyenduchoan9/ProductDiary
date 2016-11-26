package com.automic.nguyendhoang.productdiary.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen.D.Hoang on 11/24/2016.
 */

public class TransactionList implements Parcelable{
    private List<Transaction> transactions;

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public TransactionList(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public TransactionList() {
        this.transactions = new ArrayList<>();
    }

    protected TransactionList(Parcel in) {
        transactions = in.createTypedArrayList(Transaction.CREATOR);
    }

    public static final Creator<TransactionList> CREATOR = new Creator<TransactionList>() {
        @Override
        public TransactionList createFromParcel(Parcel in) {
            return new TransactionList(in);
        }

        @Override
        public TransactionList[] newArray(int size) {
            return new TransactionList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(transactions);
    }
}
