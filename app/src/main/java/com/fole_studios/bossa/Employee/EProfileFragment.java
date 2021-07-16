package com.fole_studios.bossa.Employee;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fole_studios.bossa.AuthActivity;
import com.fole_studios.bossa.R;
import com.fole_studios.bossa.auth.SaveSharedPrefs;
import com.fole_studios.bossa.database.DBManager;

import javax.xml.parsers.DocumentBuilder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EProfileFragment extends Fragment
{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button _logoutButton;
    private TextView _phoneNumber;
    private TextView _email;
    private TextView _storeName;
    private TextView _storeId;
    private DBManager _dbManager;
    private Cursor _cursor;
    private TextView _username;

    public EProfileFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EProfileFragment newInstance(String param1, String param2)
    {
        EProfileFragment fragment = new EProfileFragment();
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
        View _view = inflater.inflate(R.layout.fragment_e_profile, container, false);

        _logoutButton = _view.findViewById(R.id.e_profile_logout_button);
        _username = _view.findViewById(R.id.e_profile_username);
        _phoneNumber = _view.findViewById(R.id.e_profile_phone_number);
        _email = _view.findViewById(R.id.e_profile_email_address);
        _storeName = _view.findViewById(R.id.e_profile_store_name);
        _storeId = _view.findViewById(R.id.e_profile_store_id);

        initDatabase();
        logout();
        currentUserInfo();
        return _view;
    }

    private void initDatabase()
    {
        _dbManager = new DBManager(getContext());
        _dbManager.open();
    }

    private void currentUserInfo()
    {
        String _contact = SaveSharedPrefs.getPhoneNumber(getContext());

        if(_dbManager.fetchCurrentEmployee(_contact).getCount() > 0)
        {
            _cursor = _dbManager.fetchCurrentEmployee(_contact);
            populateData();
        }

    }

    private void populateData()
    {
        _username.setText(_cursor.getString(1));
        _phoneNumber.setText(_cursor.getString(2));
        _email.setText(_cursor.getString(3));
        _storeId.setText(String.valueOf(_cursor.getString(4)));
        _storeName.setText(_cursor.getString(5));
    }

    private void logout()
    {
        _logoutButton.setOnClickListener(view ->
        {
            SaveSharedPrefs.setLoggedIn(getContext(), false);
            Intent _authIntent = new Intent(getContext(), AuthActivity.class);
            requireActivity().startActivity(_authIntent);
            requireActivity().finish();
        });
    }
}