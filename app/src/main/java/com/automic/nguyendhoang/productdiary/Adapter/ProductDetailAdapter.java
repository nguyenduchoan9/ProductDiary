package com.automic.nguyendhoang.productdiary.Adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.automic.nguyendhoang.productdiary.Common.Constant;
import com.automic.nguyendhoang.productdiary.Common.Utils.ConvertUtils;
import com.automic.nguyendhoang.productdiary.Common.Utils.NumberUtils;
import com.automic.nguyendhoang.productdiary.Model.ConvertedCurrency;
import com.automic.nguyendhoang.productdiary.Model.Rate;
import com.automic.nguyendhoang.productdiary.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nguyen.D.Hoang on 11/24/2016.
 */

public class ProductDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ConvertedCurrency> mConvertedCurrencies;
    private ConvertUtils mConvertUtils;

    public ProductDetailAdapter(List<ConvertedCurrency> convertedCurrencies, List<Rate> rates) {
        this.mConvertedCurrencies = convertedCurrencies;
        this.mConvertUtils = new ConvertUtils(rates);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_detail_item, parent, false);
        return new ProductDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ConvertedCurrency convertedCurrency = mConvertedCurrencies.get(position);
        ((ProductDetailViewHolder) holder).bindData(convertedCurrency);
    }

    @Override
    public int getItemCount() {
        return mConvertedCurrencies.size();
    }

    class ProductDetailViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvInitValue)
        TextView tvInitValue;
        @BindView(R.id.tvConvertedValue)
        TextView tvConvertedValue;

        public ProductDetailViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(ConvertedCurrency convertedCurrency) {
            String initCurrencySymbol = convertedCurrency.equals(Constant.GBP) ? Constant.POUND_CHARACTER : Constant.DOLLAR_CHARACTER;
            tvInitValue.setText(Html.fromHtml(initCurrencySymbol + NumberUtils.NumberStandardizde(convertedCurrency.getInitValue()) + " - " + convertedCurrency.getInitCurrency()));

            Double convertedValue = mConvertUtils.convertToSpecifiedCurrency(convertedCurrency.getInitCurrency(),
                    convertedCurrency.getInitValue(), Constant.GBP, "");
            tvConvertedValue.setText(Html.fromHtml(Constant.POUND_CHARACTER + NumberUtils.NumberStandardizde(convertedValue)));
        }

    }
}
