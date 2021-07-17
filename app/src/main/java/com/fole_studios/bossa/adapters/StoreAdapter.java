package com.fole_studios.bossa.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fole_studios.bossa.R;
import com.fole_studios.bossa.models.Store;

import java.util.ArrayList;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder>
{
    private ArrayList<Store> _storeArrayList;
    private Context _context;

    public StoreAdapter(ArrayList<Store> storeArrayList, Context context)
    {
        _storeArrayList = storeArrayList;
        _context = context;
    }

    @NonNull
    @Override
    public StoreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_item, parent, false);

        return new StoreAdapter.ViewHolder(_view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreAdapter.ViewHolder holder, int position)
    {
        holder.setData(_storeArrayList.get(position).getStoreName(), _storeArrayList.get(position).getStoreId());
    }

    @Override
    public int getItemCount()
    {
        return _storeArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView _name, _storeId;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            _name = itemView.findViewById(R.id.store_name);
            _storeId = itemView.findViewById(R.id.store_id);
        }

        public void setData(String name, int storeId)
        {
            _name.setText(name);
            _storeId.setText(String.valueOf(storeId));
        }
    }
}
