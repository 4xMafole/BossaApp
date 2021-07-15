package com.fole_studios.bossa.Employee;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fole_studios.bossa.R;
import com.fole_studios.bossa.adapters.TransactionAdapter;
import com.fole_studios.bossa.models.Transaction;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ETransactionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ETransactionFragment extends Fragment
{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView _recyclerView;
    private ArrayList<Transaction> _transactionArrayList;
    private TransactionAdapter _adapter;

    public ETransactionFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ETransactionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ETransactionFragment newInstance(String param1, String param2)
    {
        ETransactionFragment fragment = new ETransactionFragment();
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
        View _view = inflater.inflate(R.layout.fragment_e_transaction, container, false);

        _recyclerView = _view.findViewById(R.id.e_trans_recyclerview);

        initRecyclerview();

        return _view;
    }

    private void initRecyclerview()
    {
        transactionData();
        _adapter = new TransactionAdapter(_transactionArrayList, getContext());
        _recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        _recyclerView.setAdapter(_adapter);
        _adapter.notifyDataSetChanged();
    }

    private void transactionData()
    {
        _transactionArrayList = new ArrayList<>();

        for (int i = 0; i < 2; i++)
        {
            _transactionArrayList.add(new Transaction("Transaction: " + "5432" + i, "Confirmed"));
            _transactionArrayList.add(new Transaction("Transaction: " + "43822" + i * i, "Confirmed"));
            _transactionArrayList.add(new Transaction("Transaction: " + "391" + i + i, "Confirmed"));
        }
    }
}