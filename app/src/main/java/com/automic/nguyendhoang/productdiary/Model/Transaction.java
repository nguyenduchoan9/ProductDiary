package com.automic.nguyendhoang.productdiary.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nguyen.D.Hoang on 11/24/2016.
 */

public class Transaction implements Parcelable{
    private String sku;
    private double amount;
    private String currency;


    public Transaction(String sku, Double amount, String currency) {
        this.sku = sku;
        this.amount = amount;
        this.currency = currency;
    }

    public Transaction() {
    }


    protected Transaction(Parcel in) {
        sku = in.readString();
        amount = in.readDouble();
        currency = in.readString();
    }

    public static final Creator<Transaction> CREATOR = new Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sku);
        dest.writeDouble(amount);
        dest.writeString(currency);
    }
}
