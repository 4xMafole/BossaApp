package com.fole_studios.bossa;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RadioButton;

import com.fole_studios.bossa.Employee.EMainActivity;
import com.fole_studios.bossa.auth.SaveSharedPrefs;
import com.fole_studios.bossa.background.beem.OTPClient;
import com.fole_studios.bossa.database.DBManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.shashank.sony.fancytoastlib.FancyToast;

import in.aabhasjindal.otptextview.OtpTextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment
{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextInputLayout _tinLayout;
    private RadioButton _bossButton;
    private RadioButton _employeeButton;
    private TextInputEditText _phoneNumber;
    private OtpTextView _otpTextView;
    private FloatingActionButton _submitButton;
    private DBManager _dbManager;
    private String _user;
    private OTPClient _client;
    private ProgressBar _progressbar;

    public LoginFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2)
    {
        LoginFragment fragment = new LoginFragment();
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
        View _view = inflater.inflate(R.layout.fragment_login, container, false);

        _bossButton = _view.findViewById(R.id.log_boss_button);
        _employeeButton = _view.findViewById(R.id.log_employee_button);
        _phoneNumber = _view.findViewById(R.id.log_phone_number);
        _otpTextView = _view.findViewById(R.id.log_otp);
        _submitButton = _view.findViewById(R.id.log_submit_button);
        _progressbar = _view.findViewById(R.id.log_progress_bar);

        toggleUser();
        initDatabase();
        submitData();
        return _view;
    }

    private void submitData()
    {
        _submitButton.setOnClickListener(v ->
        {
            _progressbar.setVisibility(View.VISIBLE);
            _submitButton.setVisibility(View.INVISIBLE);

            if(_user != null)
            {
                if(_user.contains("Boss") || _user.contains("Employee") && !(TextUtils.isEmpty(_phoneNumber.getText().toString())))
                {
                    if(_user.contains("Boss"))
                    {
                        _submitButton.setVisibility(View.VISIBLE);
                        _progressbar.setVisibility(View.INVISIBLE);
                        FancyToast.makeText(getContext(), "Boss system is under construction", FancyToast.LENGTH_SHORT, FancyToast.INFO, false).show();
                    }
                    else
                    {
                        authenticateUser();
                    }
                }
                else
                {
                    _submitButton.setVisibility(View.VISIBLE);
                    _progressbar.setVisibility(View.INVISIBLE);
                    FancyToast.makeText(getContext(), "Fill all details", FancyToast.LENGTH_SHORT, FancyToast.INFO, false).show();
                }
            }
            else
            {
                _submitButton.setVisibility(View.VISIBLE);
                _progressbar.setVisibility(View.INVISIBLE);
                FancyToast.makeText(getContext(), "Are you a boss or an employee?", FancyToast.LENGTH_SHORT, FancyToast.INFO, false).show();

            }
        });
    }

    private void authenticateUser()
    {
        if(_client == null)
        {
            if(_dbManager.fetchCurrentEmployee(_phoneNumber.getText().toString()).getCount() != 0)
            {
                FancyToast.makeText(getContext(), "Verification code has been sent", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                _progressbar.setVisibility(View.INVISIBLE);
                _submitButton.setVisibility(View.VISIBLE);
                _client = new OTPClient(getContext(), _phoneNumber.getText().toString());
                _client.run();
            }
            else
            {
                _submitButton.setVisibility(View.VISIBLE);
                _progressbar.setVisibility(View.INVISIBLE);
                FancyToast.makeText(getContext(), "User not found", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
            }

        }
        else
        {
            _client.pinValidity(_otpTextView.getOTP());

            Handler handler = new Handler();
            handler.postDelayed(() ->
            {
                if(_client._isPin)
                {
                    openMain();
                }
            }, 30000);
        }
    }

    private void initDatabase()
    {
        _dbManager = new DBManager(getContext());
        _dbManager.open();
    }

    private void toggleUser()
    {
        _bossButton.setOnCheckedChangeListener((buttonView, isChecked) ->
        {
            if(isChecked)
            {
                _user = _bossButton.getText().toString();
                _employeeButton.setChecked(false);
            }
        });

        _employeeButton.setOnCheckedChangeListener((buttonView, isChecked) ->
        {
            if(isChecked)
            {
                _user = _employeeButton.getText().toString();
                _bossButton.setChecked(false);
            }
        });
    }

    private void openMain()
    {
        SaveSharedPrefs.setLoggedIn(getContext(), true);
        Intent _mainIntent = new Intent(getContext(), EMainActivity.class);
        requireContext().startActivity(_mainIntent);
        requireActivity().finish();
    }

}