package com.fole_studios.bossa.Boss;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fole_studios.bossa.R;
import com.fole_studios.bossa.adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BProfileFragment extends Fragment
{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private BEmployeeFragment _bEmployeeFragment;
    private BStoreFragment _bStoreFragment;

    public BProfileFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BProfileFragment newInstance(String param1, String param2)
    {
        BProfileFragment fragment = new BProfileFragment();
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
        View _view = inflater.inflate(R.layout.fragment_b_profile, container, false);

        viewPager = _view.findViewById(R.id.b_view_pager);
        tabLayout = _view.findViewById(R.id.b_tab_layout);

        setupViewpager();

        return _view;
    }

    private void setupViewpager()
    {
        _bEmployeeFragment = new BEmployeeFragment();
        _bStoreFragment = new BStoreFragment();

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(requireActivity().getSupportFragmentManager(), 0);

        viewPagerAdapter.addFragment(_bStoreFragment,"stores");
        viewPagerAdapter.addFragment(_bEmployeeFragment,"employees");

        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.getTabAt(0).setText("Stores");
        tabLayout.getTabAt(1).setText("Employees");
    }

}