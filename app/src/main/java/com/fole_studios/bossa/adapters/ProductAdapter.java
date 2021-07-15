package com.fole_studios.bossa.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fole_studios.bossa.R;
import com.fole_studios.bossa.models.Product;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>
{
    private ArrayList<Product> _productArrayList;
    private Context _context;

    public ProductAdapter(ArrayList<Product> productArrayList, Context context)
    {
        _productArrayList = productArrayList;
        _context = context;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);

        return new ProductAdapter.ViewHolder(_view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position)
    {
        holder.setData(_productArrayList.get(position).getProductName(), _productArrayList.get(position).getProductSold(), _productArrayList.get(position).getProductCost());
    }

    @Override
    public int getItemCount()
    {
        return _productArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView _productName, _productSold, _productCost;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            _productName = itemView.findViewById(R.id.product_name);
            _productSold = itemView.findViewById(R.id.product_sold);
            _productCost = itemView.findViewById(R.id.product_cost);

        }

        public void setData(String productName, int productSold, String productCost)
        {
            _productName.setText(productName);
            _productSold.setText(String.valueOf(productSold));
            _productCost.setText(productCost + "/=");

        }
    }
}
