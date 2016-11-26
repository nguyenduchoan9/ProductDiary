package com.automic.nguyendhoang.productdiary;

import com.automic.nguyendhoang.productdiary.Common.Utils.ConvertUtils;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RateCurrencyTest {
    @Test
    public void testConversionSelf() {
        ConvertUtils converter = new ConvertUtils(DataTestRepository.getRateList());
        assertThat(converter.convertToSpecifiedCurrency("USD", 1.0, "USD", ""), is(equalTo(1.0d)));
    }

    @Test
    public void testConversionDirect() {
        ConvertUtils converter = new ConvertUtils(DataTestRepository.getRateList());
        assertThat(converter.convertToSpecifiedCurrency("USD", 1.0, "GBP", ""), is(equalTo(0.77d)));
    }

    @Test
    public void testConversionJumpOne() {
        ConvertUtils converter = new ConvertUtils(DataTestRepository.getRateList());
        assertThat(converter.convertToSpecifiedCurrency("CAD", 1.0, "GBP", ""), is(equalTo(0.7084d)));
    }

    @Test
    public void testConversionJumpTwo() {
        ConvertUtils converter = new ConvertUtils(DataTestRepository.getRateList());
        assertThat(converter.convertToSpecifiedCurrency("CAD", 1.0, "AUD", ""), is(equalTo(0.587972d)));
    }
}