package com.automic.nguyendhoang.productdiary.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.automic.nguyendhoang.productdiary.Common.Constant;
import com.automic.nguyendhoang.productdiary.R;
import com.automic.nguyendhoang.productdiary.ViewModel.ProductViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nguyen.D.Hoang on 11/24/2016.
 */

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ProductViewModel> mTransactionViewModelList;

    private Context mContext;

    private Listener mListener;

    public void setListener(Listener mListener) {
        this.mListener = mListener;
    }

    public interface Listener {
        void onItemClickListener(ProductViewModel productName);
    }

    public ProductAdapter(Context context, List<ProductViewModel> transactionViewModelList) {
        mTransactionViewModelList = transactionViewModelList;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProductViewModel productViewModel = mTransactionViewModelList.get(position);
        Log.d("adapterHolder", String.valueOf(mTransactionViewModelList.size()));
        Log.d("adapterHolder", productViewModel.getProductName());
        Log.d("adapterHolder", String.valueOf(productViewModel.getNumberOfTransaction()));
        ((ProductViewHolder) holder).bindData(productViewModel);
    }


    @Override
    public int getItemCount() {
        return mTransactionViewModelList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tvProduct)
        TextView tvProduct;
        @BindView(R.id.tvTransaction)
        TextView tvTransaction;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bindData(ProductViewModel productViewModel) {
            tvProduct.setText(productViewModel.getProductName());
            tvTransaction.setText(String.valueOf(productViewModel.getNumberOfTransaction()) + Constant.TRANSACTION_STRING);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            ProductViewModel productViewModel = mTransactionViewModelList.get(position);
            mListener.onItemClickListener(productViewModel);
        }
    }

}

