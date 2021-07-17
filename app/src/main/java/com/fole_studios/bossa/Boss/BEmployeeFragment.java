package com.fole_studios.bossa.Boss;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fole_studios.bossa.R;
import com.fole_studios.bossa.adapters.EmployeeAdapter;
import com.fole_studios.bossa.adapters.StoreAdapter;
import com.fole_studios.bossa.models.Employee;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BEmployeeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BEmployeeFragment extends Fragment
{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<Employee> _employeeArrayList;
    private RecyclerView _recyclerView;
    private EmployeeAdapter _adapter;

    public BEmployeeFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BEmployeeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BEmployeeFragment newInstance(String param1, String param2)
    {
        BEmployeeFragment fragment = new BEmployeeFragment();
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
        View _view = inflater.inflate(R.layout.fragment_b_employee, container, false);

        _recyclerView = _view.findViewById(R.id.b_employee_recyclerview);

        initRecyclerview();
        return _view;
    }

    private void initRecyclerview()
    {
        employeeData();
        _adapter = new EmployeeAdapter(_employeeArrayList, getContext());
        _recyclerView.setAdapter(_adapter);
        _adapter.notifyDataSetChanged();
    }

    private void employeeData()
    {
        _employeeArrayList = new ArrayList<>();

        for(int i = 0; i < 10; i++)
        {
            _employeeArrayList.add(new Employee("John Doe", "255478382932", i));
        }
    }


}