package com.automic.nguyendhoang.productdiary.Model;

/**
 * Created by Nguyen.D.Hoang on 11/24/2016.
 */

public class ConvertedCurrency {
    private String initCurrency;
    private Double initValue;
    private Double convertedValue;

    public ConvertedCurrency(String initCurrency, Double initValue, Double convertedValue) {
        this.initCurrency = initCurrency;
        this.initValue = initValue;
        this.convertedValue = convertedValue;
    }

    public ConvertedCurrency() {
    }

    public String getInitCurrency() {
        return initCurrency;
    }

    public void setInitCurrency(String initCurrency) {
        this.initCurrency = initCurrency;
    }

    public Double getInitValue() {
        return initValue;
    }

    public void setInitValue(Double initValue) {
        this.initValue = initValue;
    }

    public Double getConvertedValue() {
        return convertedValue;
    }

    public void setConvertedValue(Double convertedValue) {
        this.convertedValue = convertedValue;
    }
}
