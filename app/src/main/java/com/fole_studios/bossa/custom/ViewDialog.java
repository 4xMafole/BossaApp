package com.fole_studios.bossa.custom;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.fole_studios.bossa.R;
import com.fole_studios.bossa.adapters.ProductAdapter;
import com.fole_studios.bossa.database.DBManager;
import com.fole_studios.bossa.models.Product;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Objects;

import static com.fole_studios.bossa.animation.CustomAnimation.fadeInVisible;

public class ViewDialog
{
    Dialog _dialog;
    int _proSold = 0, _proCost = 0;

    public void showAddProduct(Context context, int transactionId,  DBManager dbManager, RecyclerView recyclerView,
                               ProductAdapter adapter, ArrayList<Product> productArrayList, TextView noProductText,
                               TextView productSoldtText, TextView productTotalAmount, int productSold, int productAmount)
    {
        startDialog(context, R.layout.add_product);

        TextInputEditText _productName = _dialog.findViewById(R.id.ad_product_name);
        TextInputEditText _productSold = _dialog.findViewById(R.id.ad_product_sold);
        TextInputEditText _productCost = _dialog.findViewById(R.id.ad_product_cost);
        Button _submitButton = _dialog.findViewById(R.id.ad_product_submit);

        _proSold += productSold;
        _proCost += productAmount;

        _submitButton.setOnClickListener(view ->
        {
            if(TextUtils.isEmpty(_productName.getText()) || TextUtils.isEmpty(_productSold.getText()) || TextUtils.isEmpty(_productCost.getText()))
            {
                Toast.makeText(context, "Fill the details", Toast.LENGTH_SHORT).show();
                return;
            }

            String _proName = Objects.requireNonNull(_productName.getText()).toString();
            int _proSold1 = Integer.parseInt(Objects.requireNonNull(_productSold.getText()).toString());
            int _proCost1 = Integer.parseInt(Objects.requireNonNull(_productCost.getText()).toString());
            _proSold += _proSold1;
            _proCost += _proCost1;

            productSoldtText.setText(String.valueOf(_proSold));
            productTotalAmount.setText(_proCost + "/=");

            productArrayList.add(new Product(_proName, _proSold1, _proCost1));
            dbManager.insertProduct(_proName, transactionId, _proSold1, _proCost1);
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);

            if(!(adapter.getItemCount() > 0))
            {
                recyclerView.setVisibility(View.GONE);
                fadeInVisible(noProductText);
            }
            else
            {
                noProductText.setVisibility(View.GONE);
                fadeInVisible(recyclerView);
            }


                _dialog.dismiss();
        });

        endDialog();
    }

    public void showTransaction(Context context, DBManager dbManager, String transactionID)
    {
        startDialog(context, R.layout.transaction_popup);

        TextView _transID = _dialog.findViewById(R.id.popup_transaction_id);
        RecyclerView _recyclerView = _dialog.findViewById(R.id.popup_recyclerview);
        TextView _totalSold = _dialog.findViewById(R.id.popup_total_sold);
        TextView _transAmount = _dialog.findViewById(R.id.popup_amount);

        Cursor _cursor = dbManager.fetchLatestProducts(Integer.parseInt(transactionID));
        int _sold = 0, _amount = 0;

        _transID.setText("Transaction: " + transactionID);

        ArrayList<Product> _productArrayList = new ArrayList<>();

        if(_cursor.getCount() > 0)
        {
            do
            {
                _productArrayList.add(new Product(_cursor.getString(2), Integer.parseInt(_cursor.getString(3)), Integer.parseInt(_cursor.getString(4))));
//                _dbManager.delete(Long.parseLong(_cursor.getString(0)));
                _sold += Integer.parseInt(_cursor.getString(3));
                _amount += Integer.parseInt(_cursor.getString(4));

            }while(_cursor.moveToNext());

            _totalSold.setText(String.valueOf(_sold));
            _transAmount.setText(_amount + "/=");
        }

        ProductAdapter _adapter = new ProductAdapter(_productArrayList, context);
        _recyclerView.setAdapter(_adapter);
        _adapter.notifyDataSetChanged();


        endDialog();
    }

    private void startDialog(Context context, int layoutID)
    {
        _dialog = new Dialog(context);
        _dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        _dialog.setCancelable(true);
        _dialog.setContentView(layoutID);
    }

    private void endDialog()
    {
        _dialog.show();
        Window _window = _dialog.getWindow();
        _window.setBackgroundDrawableResource(android.R.color.transparent);
        _window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}
