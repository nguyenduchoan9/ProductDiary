package com.automic.nguyendhoang.productdiary.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.automic.nguyendhoang.productdiary.Adapter.ProductAdapter;
import com.automic.nguyendhoang.productdiary.Common.Constant;
import com.automic.nguyendhoang.productdiary.Common.Utils.ConvertUtils;
import com.automic.nguyendhoang.productdiary.Common.Utils.FileUtils;
import com.automic.nguyendhoang.productdiary.Common.Utils.ParseUtils;
import com.automic.nguyendhoang.productdiary.Model.TransactionList;
import com.automic.nguyendhoang.productdiary.R;
import com.automic.nguyendhoang.productdiary.ViewModel.ProductViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rvProduct)
    RecyclerView rvProduct;

    private TransactionList transactions;
    private StaggeredGridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getData();

        initView();
    }

    private void initView() {

        List<ProductViewModel> productViewModels = ConvertUtils.convertToProductViewModel(transactions.getTransactions());
        Log.d("viewModelList", String.valueOf(productViewModels.size()));
        ProductAdapter adapter = new ProductAdapter(this, productViewModels);
        adapter.setListener(productName -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constant.PRODUCT_KEY, productName);
            MainActivity.this.startActivityQuick(ProductDetailActivity.class, bundle);
        });
        mLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);

        rvProduct.setLayoutManager(mLayoutManager);
        rvProduct.setAdapter(adapter);
    }

    public void getData() {
        String transactionJsonString = FileUtils.loadJsonFromAsset(this, Constant.TRANSACTION_FILE);
        transactions = new TransactionList();
        transactions.setTransactions(ParseUtils.parseStringToTransactionList(transactionJsonString));
        Log.d("transaction", String.valueOf(transactions.getTransactions().size()));
    }

    private void startActivityQuick(Class activityClass, Bundle bundle) {
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtras(bundle);
        intent.putExtra(Constant.TRANSACTION_KEY, transactions);
        startActivity(intent);
    }
}
