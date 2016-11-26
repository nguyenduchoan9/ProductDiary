package com.automic.nguyendhoang.productdiary.Common;

/**
 * Created by Nguyen.D.Hoang on 11/24/2016.
 */

public class Constant {

    public final static String TOOLBAR_TITLE = "Transaction for ";

    public final static String RATE_FILE = "rates.json";
    public final static String TRANSACTION_FILE = "transactions.json";
    public final static String JSON_DATA_FILE_KEY = "data";
    public final static String START_JSON_DATA_FILE_KEY = "{\"data\":";
    public final static String END_JSON_DATA_FILE_KEY = "}:";

    public final static String FROM_KEY_IN_RATE_JSON = "from";
    public final static String RATE_KEY_IN_RATE_JSON = "rate";
    public final static String TO_KEY_IN_RATE_JSON = "to";

    public final static String SKU_KEY_IN_TRANSACTION_JSON = "sku";
    public final static String AMOUNT_KEY_IN_TRANSACTION_JSON = "amount";
    public final static String CURRENCY_KEY_IN_TRANSACTION_JSON = "currency";

    public final static char DIVIDER_NUMBER_CURRENCY = ',';
    public final static String FORMAT_NUMBER_CURRENCY = "###,###,###,###,###.##";

    public final static String GBP = "GBP";
    public final static String CURRENCY_DIVIDER = "-";

    public final static String TRANSACTION_STRING = " transactions";

    public final static String PRODUCT_KEY = "PRODUCT_KEY";
    public final static String TRANSACTION_KEY = "TRANSACTION_KEY";

    public final static String POUND_CHARACTER = "&pound;";
    public final static String DOLLAR_CHARACTER = "&#36;";
}
