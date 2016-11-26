package com.automic.nguyendhoang.productdiary.Common.Utils;

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
    public static String NumberStandardizde(Double number) {
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator(',');
        DecimalFormat formatter = new DecimalFormat("###,###,###,###,###.##", symbols);
        formatter.format(number);
//        return String.format(Locale.ENGLISH,"%.2f", number);
        return formatter.format(number);
    }
}
