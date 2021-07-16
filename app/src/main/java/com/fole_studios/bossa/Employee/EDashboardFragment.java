package com.fole_studios.bossa.Employee;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fole_studios.bossa.R;
import com.fole_studios.bossa.adapters.ProductAdapter;
import com.fole_studios.bossa.background.beem.OTP;
import com.fole_studios.bossa.background.beem.OTPDataTask;
import com.fole_studios.bossa.custom.ViewDialog;
import com.fole_studios.bossa.database.DBManager;
import com.fole_studios.bossa.models.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONObject;

import java.util.ArrayList;

import static com.fole_studios.bossa.animation.CustomAnimation.fadeInVisible;

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
    private TextView _transactionId;
    private TextView _transTotalSold;
    private TextView _transTotalAmount;
    private Button _submitTransaction;
    private DBManager _dbManager;
    private Cursor _cursorProduct, _cursorTransaction;
    private TextView _noProductText;
    private int _totalAmount;
    private int _totalSold;
    private int _newTransID;
    private int _queriedTransactionID;
    private String _otpPinId;

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
        _transactionId = _view.findViewById(R.id.e_dash_transaction_id);
        _transTotalSold = _view.findViewById(R.id.e_dash_total_sold);
        _transTotalAmount = _view.findViewById(R.id.e_dash_amount);
        _submitTransaction = _view.findViewById(R.id.e_dash_trans_submit);
        _noProductText = _view.findViewById(R.id.e_dash_no_product_text);

        _totalAmount = 0;
        _totalSold = 0;
        _newTransID = 1;

        initDatabase();
        initRecyclerview();
        addProduct();
        addTransaction();

        return _view;
    }



    private void initDatabase()
    {
        _dbManager = new DBManager(getContext());
        _dbManager.open();
        _cursorProduct = _dbManager.fetchProduct();
        _cursorTransaction = _dbManager.fetchTransaction();

        if(_cursorTransaction.getCount() > 0)
        {
            _newTransID = _cursorTransaction.getInt(0);
        }

    }

    // TODO: 07/16/21 Need to update totalSold and totalAmount when a product is added.
    private void addProduct()
    {
        adapterEmpty();
        _addButton.setOnClickListener(view ->
        {
            ViewDialog _dialog = new ViewDialog();
             _dialog.showAddProduct(getContext(), _newTransID, _dbManager, _recyclerView, _adapter, _productArrayList, _noProductText);
        });
    }

    private void adapterEmpty()
    {
        if(!(_adapter.getItemCount() > 0))
        {
            _recyclerView.setVisibility(View.GONE);
            fadeInVisible(_noProductText);
        }
        else
        {
            _noProductText.setVisibility(View.GONE);
            fadeInVisible(_recyclerView);
        }
    }

    private void addTransaction()
    {
        _transactionId.setText("Transaction: " + _newTransID);

        _submitTransaction.setOnClickListener(view ->
        {
            if(_totalAmount > 0 || _totalSold > 0)
            {

                _queriedTransactionID++;
                _dbManager.insertTransaction(_newTransID, _totalAmount, _totalSold, "unconfirmed");
                _productArrayList.clear();
                _adapter.notifyDataSetChanged();
                _transactionId.setText("Transaction: " + _queriedTransactionID);
                adapterEmpty();
                _transTotalSold.setText("0");
                _transTotalAmount.setText("0");
                _totalAmount = 0;
                _totalSold = 0;
            }
            else
            {
                Toast.makeText(getContext(), "Empty Transaction", Toast.LENGTH_SHORT).show();
            }

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

        if(_cursorProduct.getCount() > 0)
        {
            do
            {
                _queriedTransactionID = Integer.parseInt(_cursorProduct.getString(1));

            }while(_cursorProduct.moveToNext());

            _cursorProduct = _dbManager.fetchLatestProducts(_queriedTransactionID);
            _newTransID = _queriedTransactionID;

            if(_dbManager.fetchLatestTransaction(_queriedTransactionID).getCount() != 0)
            {
                _newTransID = _queriedTransactionID + 1;
            }
            else
            {
                do
                {
                    _productArrayList.add(new Product(_cursorProduct.getString(2), Integer.parseInt(_cursorProduct.getString(3)), Integer.parseInt(_cursorProduct.getString(4))));
//                _dbManager.delete(Long.parseLong(_cursor.getString(0)));
                    _totalSold += Integer.parseInt(_cursorProduct.getString(3));
                    _totalAmount += Integer.parseInt(_cursorProduct.getString(4));

                }while(_cursorProduct.moveToNext());
            }

        }

        _transTotalSold.setText(String.valueOf(_totalSold));
        _transTotalAmount.setText(_totalAmount + "/=");

    }

}