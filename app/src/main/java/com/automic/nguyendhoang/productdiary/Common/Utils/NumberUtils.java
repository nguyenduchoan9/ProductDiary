package com.automic.nguyendhoang.productdiary.Common.Utils;

import com.automic.nguyendhoang.productdiary.Common.Constant;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Created by Nguyen.D.Hoang on 11/25/2016.
 */

public class NumberUtils {
    /**
     * Standardize number
     *
     * <p>Number is converted to strign with format two comma decimal</p>
     *
     * @param number Double number
     * @return return format value string.
     */
    public static String NumberStandardize(Double number) {
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator(Constant.DIVIDER_NUMBER_CURRENCY);
        DecimalFormat formatter = new DecimalFormat(Constant.FORMAT_NUMBER_CURRENCY, symbols);
        formatter.format(number);
        return formatter.format(number);
    }
}
