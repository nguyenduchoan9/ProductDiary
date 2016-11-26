package com.automic.nguyendhoang.productdiary.Common.Utils;

import com.automic.nguyendhoang.productdiary.Model.Rate;
import com.automic.nguyendhoang.productdiary.Model.Transaction;
import com.automic.nguyendhoang.productdiary.ViewModel.ProductViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nguyen.D.Hoang on 11/24/2016.
 */

public class ConvertUtils {

    /**
     * Convert transaction List to Map
     * <p>
     * <p>
     * transaction List converted to Map with Key (@code = transaction.getSku()) which is product name
     * and Value is equivalent to total transaction which has the same product name(transaction.getSku())
     * <p/>
     *
     * @param transactions transaction List
     * @return Map contain Key(product name) and Value(total of transaction of product)
     */
    private static HashMap<String, Integer> countTransactionOfProduct(List<Transaction> transactions) {
        if (transactions == null || transactions.size() == 0) {
            return null;
        }

        HashMap<String, Integer> productMap = new HashMap<>();
        for (Transaction transaction :
                transactions) {
            String productName = transaction.getSku();
            /**Check product name exist or not
             * Result:
             *  if exist
             *      quantity of product plus one
             *  else
             *      quantity equivalent to one
             */
            Integer quantity = productMap.containsKey(productName) ? productMap.get(productName) + 1 : 1;

            productMap.put(productName, quantity);
        }

        return productMap;
    }

    /**
     * Convert transaction List to ProductViewModel
     * <p>
     * TransactionList is converted to Transaction Map(@code = countTransactionOfProduct(transactions)).
     * Then all of transaction in map is converted to ProductViewModel and add to List
     * <p/>
     *
     * @param transactions transaction List
     * @return return ProductViewModel List.
     */
    public static List<ProductViewModel> convertToProductViewModel(List<Transaction> transactions) {
        List<ProductViewModel> productViewModels = new ArrayList<>();

        HashMap<String, Integer> productMap = countTransactionOfProduct(transactions);

        for (Map.Entry<String, Integer> entry : productMap.entrySet()) {
            String productName = entry.getKey();
            Integer quantity = entry.getValue();

            productViewModels.add(new ProductViewModel(productName, quantity));
        }

        return productViewModels;
    }

    private Map<String, Double> currencyRate;

    public ConvertUtils(List<Rate> rateLists) {
        currencyRate = convertToCurrencyRate(rateLists);
    }

    /**
     * Convert currency rate List to Currency Map
     * <p>
     * Currency rate List is converted to Currency Map with Key (@code = currencyKey) which is
     * equivalent  to currency from (@code = rate.getFrom()) + '-' + currency to (@code = rate.getTo())
     * and Value (@code= rate.getRate())
     * <p/>
     *
     * @param rateLists rate List
     * @return return Currency Rate Map.
     */
    private Map<String, Double> convertToCurrencyRate(List<Rate> rateLists) {
        if (rateLists == null || rateLists.size() == 0) {
            return null;
        }
        Map<String, Double> currencyRateMap = new HashMap<>();
        for (Rate rate : rateLists) {
            String currencyKey = rate.getFrom() + "-" + rate.getTo();
            currencyRateMap.put(currencyKey, rate.getRate());
        }
        return currencyRateMap;
    }

    /**
     * Convert currency value to specified currency value
     * <p>
     * Any currency value with specified currency key is converted to
     * <p/>
     *
     * @param fromCurrencyKey     from currency Key
     * @param initValue           from currency value need to be convert to specified currency(@code = toCurrencyKey) value
     * @param toCurrencyKey       to currency key
     * @param previousCurrencyKey previous currency key of currency key
     * @return return specified currency value.
     */
    public Double convertToSpecifiedCurrency(String fromCurrencyKey, Double initValue, String toCurrencyKey, String previousCurrencyKey) {
        Double convertedValue = null;

        if (fromCurrencyKey.equals(toCurrencyKey)) {
            return initValue;
        }
        /**Check key which is equivalent to from Currency Key (@code =fromCurrencyKey) + '- ' + to Currency Key (@code = toCurrencyKey)
         * Result:
         *  is exist
         *      return converted currency value
         * */
        if (currencyRate.containsKey(fromCurrencyKey + "-" + toCurrencyKey)) {
            Double rate = currencyRate.get(fromCurrencyKey + "-" + toCurrencyKey);
            convertedValue = initValue * rate;
            return convertedValue;
        }

        List<String> strings = new ArrayList<>();

        // get all of currency that fromCurrency can convert to
        for (Map.Entry<String, Double> entry : currencyRate.entrySet()) {
            String key = entry.getKey();

            String[] splits = key.split("-");
            if (splits[0].equals(fromCurrencyKey) && !splits[1].equals(previousCurrencyKey)) {
                strings.add(splits[1]);
            }
        }

        for (String nextCurrencyKey : strings) {
            Double nextInitValue = initValue;
            // calculate converted currency value (@code=nextInitValue) of next currency
            if (currencyRate.containsKey(fromCurrencyKey + "-" + nextCurrencyKey)) {
                Double rate = currencyRate.get(fromCurrencyKey + "-" + nextCurrencyKey);
                nextInitValue = nextInitValue * rate;
            }
            return convertToSpecifiedCurrency(nextCurrencyKey, nextInitValue, toCurrencyKey, fromCurrencyKey);
        }
        return convertedValue;
    }

}
