package com.fole_studios.bossa.Boss;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fole_studios.bossa.R;
import com.fole_studios.bossa.adapters.TransactionAdapter;
import com.fole_studios.bossa.animation.CustomAnimation;
import com.fole_studios.bossa.database.DBManager;
import com.fole_studios.bossa.models.Transaction;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BTransactionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BTransactionFragment extends Fragment
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
    private DBManager _dbManager;
    private Cursor _cursorTransaction;
    private TextView _noTransactionText;
    private TextView _transactionNum;
    private int _sales;
    private TextView _totalSales;

    public BTransactionFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BTransactionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BTransactionFragment newInstance(String param1, String param2)
    {
        BTransactionFragment fragment = new BTransactionFragment();
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
        View _view = inflater.inflate(R.layout.fragment_b_transaction, container, false);

        _recyclerView = _view.findViewById(R.id.b_trans_recyclerview);
        _noTransactionText = _view.findViewById(R.id.b_trans_no_trans_text);
        _transactionNum = _view.findViewById(R.id.b_trans_transaction);
        _totalSales = _view.findViewById(R.id.b_trans_sales);

        initDatabase();
        initRecyclerview();

        return _view;
    }

    private void initDatabase()
    {
        _dbManager = new DBManager(getContext());
        _dbManager.open();
        _cursorTransaction = _dbManager.fetchTransaction();
    }

    private void initRecyclerview()
    {
        transactionData();
        _adapter = new TransactionAdapter(_transactionArrayList, _dbManager, getContext());
        _recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        _recyclerView.setAdapter(_adapter);
        _adapter.notifyDataSetChanged();

        _transactionNum.setText(String.valueOf(_adapter.getItemCount()));

        if(!(_adapter.getItemCount() > 0))
        {
            CustomAnimation.transUpVisible(_noTransactionText);
        }
        else
        {
            _noTransactionText.setVisibility(View.GONE);
        }
    }

    private void transactionData()
    {
        _transactionArrayList = new ArrayList<>();

        if(_cursorTransaction.getCount() > 0)
        {
            do
            {
                String _transactionId = _cursorTransaction.getString(0);
                String _transactionConfirm = _cursorTransaction.getString(3);
                _sales += Integer.parseInt(_cursorTransaction.getString(1));
                _transactionArrayList.add(new Transaction(_transactionId, _transactionConfirm));
            }while(_cursorTransaction.moveToNext());

            _totalSales.setText(_sales + "/=");
        }

    }

}