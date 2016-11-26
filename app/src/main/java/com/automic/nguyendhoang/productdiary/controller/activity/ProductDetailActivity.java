package com.automic.nguyendhoang.productdiary.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.automic.nguyendhoang.productdiary.Adapter.ProductDetailAdapter;
import com.automic.nguyendhoang.productdiary.Common.Constant;
import com.automic.nguyendhoang.productdiary.Common.Utils.FileUtils;
import com.automic.nguyendhoang.productdiary.Common.Utils.NumberUtils;
import com.automic.nguyendhoang.productdiary.Common.Utils.ParseUtils;
import com.automic.nguyendhoang.productdiary.Model.Rate;
import com.automic.nguyendhoang.productdiary.Model.Transaction;
import com.automic.nguyendhoang.productdiary.Model.TransactionList;
import com.automic.nguyendhoang.productdiary.R;
import com.automic.nguyendhoang.productdiary.ViewModel.ProductDetailViewModel;
import com.automic.nguyendhoang.productdiary.ViewModel.ProductViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailActivity extends AppCompatActivity {

    private List<Rate> rates = new ArrayList<>();
    private TransactionList transaction = new TransactionList();
    private List<Transaction> transactionProduct = new ArrayList<>();
    private ProductViewModel model;
    private ProductDetailViewModel productDetailViewModel;

    @BindView(R.id.tvTotal)
    TextView tvTotal;
    @BindView(R.id.rvDetailProduct)
    RecyclerView rvDetailProduct;

    private StaggeredGridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);
        getDataFromBundle();

        getDataFromAsset();

        prepareViewModel();

        initView();
    }

    private void initView() {
        getSupportActionBar().setTitle(Constant.TOOLBAR_TITLE + model.getProductName());

        Double total = productDetailViewModel.getTotal();
        tvTotal.setText(Html.fromHtml(Constant.POUND_CHARACTER + NumberUtils.NumberStandardize(total)));

        initRecycleView();

    }

    private void initRecycleView() {
        ProductDetailAdapter adapter = new ProductDetailAdapter(productDetailViewModel.getConvertedCurrencies(), rates);
        mLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);

        rvDetailProduct.setLayoutManager(mLayoutManager);
        rvDetailProduct.setAdapter(adapter);

    }


    private void getDataFromAsset() {
        String rateJsonString = FileUtils.loadJsonFromAsset(this, Constant.RATE_FILE);
        rates = ParseUtils.parseStringToRateList(rateJsonString);
        Log.d("rates", String.valueOf(rates.size()));
    }

    private void prepareViewModel() {
        transactionProduct = getTransactionByProduct();
        productDetailViewModel = new ProductDetailViewModel();
        productDetailViewModel.prepareModel(rates, transactionProduct);
        Log.d("transaction", String.valueOf(productDetailViewModel.getConvertedCurrencies().size()));
        Log.d("transaction", String.valueOf(productDetailViewModel.getTotal()));
    }

    private void getDataFromBundle() {
        Bundle bundle = getIntent().getExtras();
        model = bundle.getParcelable(Constant.PRODUCT_KEY);

        transaction = getIntent().getParcelableExtra(Constant.TRANSACTION_KEY);

        Log.d("model", model.getProductName());
        Log.d("model", String.valueOf(rates.size()));
        Log.d("model", String.valueOf(transaction.getTransactions().size()));
    }

    private List<Transaction> getTransactionByProduct() {
        List<Transaction> transactionList = new ArrayList<>();
        List<Transaction> transactions = transaction.getTransactions();
        for (Transaction itemTransaction : transactions) {
            if (itemTransaction.getSku().equals(model.getProductName())) {
                transactionList.add(itemTransaction);
            }
        }
        return transactionList;
    }


}
