package com.fole_studios.bossa.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fole_studios.bossa.R;
import com.fole_studios.bossa.models.Employee;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder>
{
    private ArrayList<Employee> _employeeArrayList;
    private Context _context;

    public EmployeeAdapter(ArrayList<Employee> employeeArrayList, Context context)
    {
        _employeeArrayList = employeeArrayList;
        _context = context;
    }

    @NonNull
    @Override
    public EmployeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_item, parent, false);

        return new EmployeeAdapter.ViewHolder(_view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.ViewHolder holder, int position)
    {
        holder.setData(_employeeArrayList.get(position).getEmployeeName(), _employeeArrayList.get(position).getPhoneNumber(), _employeeArrayList.get(position).getStoreId());
    }

    @Override
    public int getItemCount()
    {
        return _employeeArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView _name, _phone, _storeId;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            _name = itemView.findViewById(R.id.employee_name);
            _phone = itemView.findViewById(R.id.employee_phone);
            _storeId = itemView.findViewById(R.id.employee_store_id);
        }

        public void setData(String name, String phone, int storeId)
        {
            _name.setText(name);
            _phone.setText(phone);
            _storeId.setText(String.valueOf(storeId));
        }
    }
}
