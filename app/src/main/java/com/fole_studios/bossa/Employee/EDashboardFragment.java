package com.fole_studios.bossa.Employee;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fole_studios.bossa.R;
import com.fole_studios.bossa.adapters.ProductAdapter;
import com.fole_studios.bossa.custom.ViewDialog;
import com.fole_studios.bossa.models.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EDashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EDashboardFragment extends Fragment
{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView _recyclerView;
    private ArrayList<Product> _productArrayList;
    private FloatingActionButton _addButton;
    private ProductAdapter _adapter;

    public EDashboardFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EDashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EDashboardFragment newInstance(String param1, String param2)
    {
        EDashboardFragment fragment = new EDashboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View _view = inflater.inflate(R.layout.fragment_e_dashboard, container, false);

        _recyclerView = _view.findViewById(R.id.e_dash_recyclerview);
        _addButton = _view.findViewById(R.id.e_dash_add_button);


        initRecyclerview();
        addProduct();

        return _view;
    }

    private void addProduct()
    {
        _addButton.setOnClickListener(view ->
        {
            ViewDialog _dialog = new ViewDialog();
             _dialog.showAddProduct(getContext(),_recyclerView, _adapter, _productArrayList);
        });
    }

    private void initRecyclerview()
    {
        productData();
        _adapter = new ProductAdapter(_productArrayList, getContext());
        _recyclerView.setAdapter(_adapter);
        _adapter.notifyDataSetChanged();
    }

    private void productData()
    {
        _productArrayList = new ArrayList<>();
        for(int i = 0; i < 3; i++)
        {
            _productArrayList.add(new Product("Mafuta ya Kupikia", (int) 0.5f, String.valueOf(1000)));
            _productArrayList.add(new Product("Maziwa fresh", 12, String.valueOf(3800)));
            _productArrayList.add(new Product("Unga wa ngano", (int) 0.5, String.valueOf(1200)));
        }
    }
}