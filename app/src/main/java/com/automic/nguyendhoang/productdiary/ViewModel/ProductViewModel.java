package com.automic.nguyendhoang.productdiary.ViewModel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nguyen.D.Hoang on 11/24/2016.
 */

public class ProductViewModel  implements Parcelable{
    private String productName;
    private int numberOfTransaction;

    protected ProductViewModel(Parcel in) {
        productName = in.readString();
        numberOfTransaction = in.readInt();
    }

    public static final Creator<ProductViewModel> CREATOR = new Creator<ProductViewModel>() {
        @Override
        public ProductViewModel createFromParcel(Parcel in) {
            return new ProductViewModel(in);
        }

        @Override
        public ProductViewModel[] newArray(int size) {
            return new ProductViewModel[size];
        }
    };

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getNumberOfTransaction() {
        return numberOfTransaction;
    }

    public void setNumberOfTransaction(int numberOfTransaction) {
        this.numberOfTransaction = numberOfTransaction;
    }

    public ProductViewModel() {

    }

    public ProductViewModel(String productName, int numberOfTransaction) {

        this.productName = productName;
        this.numberOfTransaction = numberOfTransaction;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productName);
        dest.writeInt(numberOfTransaction);
    }
}
