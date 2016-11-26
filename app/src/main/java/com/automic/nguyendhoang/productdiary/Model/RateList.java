package com.automic.nguyendhoang.productdiary.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen.D.Hoang on 11/24/2016.
 */

public class RateList implements Parcelable{
    private List<Rate> rateList;

    public RateList(List<Rate> rateList) {
        this.rateList = rateList;
    }
    public RateList() {
        this.rateList = new ArrayList<>();
    }

    protected RateList(Parcel in) {
        rateList = in.createTypedArrayList(Rate.CREATOR);
    }

    public static final Creator<RateList> CREATOR = new Creator<RateList>() {
        @Override
        public RateList createFromParcel(Parcel in) {
            return new RateList(in);
        }

        @Override
        public RateList[] newArray(int size) {
            return new RateList[size];
        }
    };

    public List<Rate> getRateList() {
        return rateList;
    }

    public void setRateList(List<Rate> rateList) {
        this.rateList = rateList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(rateList);
    }
}
