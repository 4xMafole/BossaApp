package com.fole_studios.bossa.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fole_studios.bossa.R;
import com.fole_studios.bossa.custom.ViewDialog;
import com.fole_studios.bossa.models.Transaction;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder>
{
    private ArrayList<Transaction> _transactionArrayList;
    private Context _context;

    public TransactionAdapter(ArrayList<Transaction> transactionArrayList, Context context)
    {
        _transactionArrayList = transactionArrayList;
        _context = context;
    }

    @NonNull
    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item, parent, false);

        return new ViewHolder(_view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.ViewHolder holder, int position)
    {
        holder.setData(_transactionArrayList.get(position).getTransactionID(), _transactionArrayList.get(position).getConfirmation());
        transactionPopup(holder, _transactionArrayList.get(position).getTransactionID());
        confirmationCard(holder._confirmation, holder._cardLabel);
    }

    private void confirmationCard(TextView confirmation, CardView cardLabel)
    {
        String _confirmationStr = confirmation.getText().toString();

        if(_confirmationStr.equals("confirmed"))
        {
            cardLabel.setCardBackgroundColor(_context.getResources().getColor(R.color.greenish_main));
            confirmation.setTextColor(_context.getResources().getColor(R.color.greenish_light));
        }
        else if(_confirmationStr.equals("unconfirmed"))
        {
            cardLabel.setCardBackgroundColor(_context.getResources().getColor(R.color.reddish_main));
            cardLabel.setCardBackgroundColor(_context.getResources().getColor(R.color.reddish_main));
            confirmation.setTextColor(_context.getResources().getColor(R.color.reddish_light));
        }

    }

    private void transactionPopup(ViewHolder holder, String transactionID)
    {
        holder._transactionContainer.setOnClickListener(view ->
        {
            ViewDialog _dialog = new ViewDialog();
            _dialog.showTransaction(_context, transactionID);
        });
    }

    @Override
    public int getItemCount()
    {
        return _transactionArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView _transactionID, _confirmation;
        public CardView _cardLabel, _transactionContainer;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            _transactionID = itemView.findViewById(R.id.trans_item_id);
            _confirmation = itemView.findViewById(R.id.trans_item_label_text);
            _cardLabel = itemView.findViewById(R.id.trans_item_label_container);
            _transactionContainer = itemView.findViewById(R.id.trans_item_container);

        }

        public void setData(String id, String label)
        {
            _transactionID.setText("Transaction: " + id);
            _confirmation.setText(label);
        }

    }

}
