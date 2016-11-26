package com.automic.nguyendhoang.productdiary.Common.Utils;

import android.util.Log;

import com.automic.nguyendhoang.productdiary.Common.Constant;
import com.automic.nguyendhoang.productdiary.Model.Rate;
import com.automic.nguyendhoang.productdiary.Model.Transaction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen.D.Hoang on 11/24/2016.
 */

public class ParseUtils {

    /**
     * Parse jsonString to rate list
     * <p>
     * <p>Create Rate from objects which get from json  and contain from currency key, convert change, to currency key</p>
     *
     * @param jsonString Json String
     * @return return rate list.
     */
    public static List<Rate> parseStringToRateList(String jsonString) {
        List<Rate> rates = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(jsonString);
            JSONArray jsonArray = obj.getJSONArray(Constant.JSON_DATA_FILE_KEY);
            Log.d("jsonArray", String.valueOf(jsonArray.length()));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String from = jsonObject.getString(Constant.FROM_KEY_IN_RATE_JSON);
                Double rate = jsonObject.getDouble(Constant.RATE_KEY_IN_RATE_JSON);
                String to = jsonObject.getString(Constant.TO_KEY_IN_RATE_JSON);

                Rate rateObject = new Rate(from, rate, to);
                rates.add(rateObject);
//                if (!from.equals(Constant.GBP)) {
//                    rates.add(rateObject);
//                } else {
//
//                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rates;
    }

    /**
     * Convert jsonString to transaction list
     * <p>
     * <p>Create Transaction from object that get from jsonString and contain sku(product code),
     * amount transaction value, currency key</p>
     *
     * @param jsonString Json String
     * @return return transaction list.
     */
    public static List<Transaction> parseStringToTransactionList(String jsonString) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(jsonString);
            JSONArray jsonArray = obj.getJSONArray(Constant.JSON_DATA_FILE_KEY);
            Log.d("jsonArray", String.valueOf(jsonArray.length()));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String sku = jsonObject.getString(Constant.SKU_KEY_IN_TRANSACTION_JSON);
                Double rate = Double.valueOf(jsonObject.getString(Constant.AMOUNT_KEY_IN_TRANSACTION_JSON));
                String currency = jsonObject.getString(Constant.CURRENCY_KEY_IN_TRANSACTION_JSON);

                Transaction transactionObject = new Transaction(sku, rate, currency);

                transactions.add(transactionObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return transactions;
    }
}
