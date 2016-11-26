package com.automic.nguyendhoang.productdiary.ViewModel;

import android.util.Log;

import com.automic.nguyendhoang.productdiary.Common.Utils.ConvertUtils;
import com.automic.nguyendhoang.productdiary.Model.ConvertedCurrency;
import com.automic.nguyendhoang.productdiary.Model.Rate;
import com.automic.nguyendhoang.productdiary.Model.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen.D.Hoang on 11/24/2016.
 */

public class ProductDetailViewModel {
    private Double total;
    private List<ConvertedCurrency> convertedCurrencies;

    public ProductDetailViewModel(Double total, List<ConvertedCurrency> convertedCurrencies) {
        this.total = total;
        this.convertedCurrencies = convertedCurrencies;
    }

    public ProductDetailViewModel() {
        this.total = 0.0;
        this.convertedCurrencies = new ArrayList<>();
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<ConvertedCurrency> getConvertedCurrencies() {
        return convertedCurrencies;
    }

    public void setConvertedCurrencies(List<ConvertedCurrency> convertedCurrencies) {
        this.convertedCurrencies = convertedCurrencies;
    }

    public void prepareModel(List<Rate> rates, List<Transaction> transactions) {
        ConvertUtils convertUtils = new ConvertUtils(rates);
        int i = 0;
        for (Transaction transaction : transactions) {
            Double convertedValue = convertUtils.convertToSpecifiedCurrency(transaction.getCurrency(), transaction.getAmount(), "GBP", "");
            ConvertedCurrency convertedCurrency = new ConvertedCurrency(transaction.getCurrency(),
                    transaction.getAmount(), convertedValue);

            total += convertedValue;
            convertedCurrencies.add(convertedCurrency);
            Log.d("prepareModel", String.valueOf(i));
            i++;
        }
    }
}
