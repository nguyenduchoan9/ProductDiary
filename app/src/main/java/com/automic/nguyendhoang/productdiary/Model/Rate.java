package com.automic.nguyendhoang.productdiary.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nguyen.D.Hoang on 11/24/2016.
 */

public class Rate implements Parcelable {
    private String from;
    private Double rate;
    private String to;

    public Rate(String from, Double rate, String to) {
        this.from = from;
        this.rate = rate;
        this.to = to;
    }

    public Rate() {
    }


    protected Rate(Parcel in) {
        from = in.readString();
        rate = in.readDouble();
        to = in.readString();
    }

    public static final Creator<Rate> CREATOR = new Creator<Rate>() {
        @Override
        public Rate createFromParcel(Parcel in) {
            return new Rate(in);
        }

        @Override
        public Rate[] newArray(int size) {
            return new Rate[size];
        }
    };

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(from);
        dest.writeDouble(rate);
        dest.writeString(to);
    }
}
