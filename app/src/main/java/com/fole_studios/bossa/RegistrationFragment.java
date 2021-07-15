package com.fole_studios.bossa;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.fole_studios.bossa.Employee.EMainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import static com.fole_studios.bossa.animation.CustomAnimation.transDownInvisible;
import static com.fole_studios.bossa.animation.CustomAnimation.transUpVisible;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrationFragment extends Fragment
{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RadioButton _bossButton;
    private RadioButton _employeeButton;
    private TextInputLayout _tinLayout;
    private FloatingActionButton _submitButton;

    public RegistrationFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrationFragment newInstance(String param1, String param2)
    {
        RegistrationFragment fragment = new RegistrationFragment();
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
        View _view =  inflater.inflate(R.layout.fragment_registration, container, false);

        _bossButton = _view.findViewById(R.id.reg_boss_button);
        _employeeButton = _view.findViewById(R.id.reg_employee_button);
        _tinLayout = _view.findViewById(R.id.reg_tin_layout);
        _submitButton = _view.findViewById(R.id.reg_submit_button);

        toggleUser();
        submitData();

        return _view;
    }

    private void toggleUser()
    {
        _bossButton.setOnCheckedChangeListener((buttonView, isChecked) ->
        {
            if(isChecked)
            {
                _employeeButton.setChecked(false);
                transUpVisible(_tinLayout);
            }
        });

        _employeeButton.setOnCheckedChangeListener((buttonView, isChecked) ->
        {
            if(isChecked)
            {
                _bossButton.setChecked(false);
                transDownInvisible(_tinLayout);
            }
        });
    }

    private void submitData()
    {
        _submitButton.setOnClickListener(v ->
        {
            Intent _intent = new Intent(getContext(), EMainActivity.class);
            requireContext().startActivity(_intent);
            requireActivity().finish();
        });
    }

}