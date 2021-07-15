package com.fole_studios.bossa.Employee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.fole_studios.bossa.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class EMainActivity extends AppCompatActivity
{

    private BottomNavigationView _bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_employee);

        _bottomNavigationView = findViewById(R.id.m_e_bottom_nav);

        openFragment(EDashboardFragment.newInstance("",""));

        bottomFunctionality();
    }

    private void bottomFunctionality()
    {
        _bottomNavigationView.setOnItemSelectedListener(item ->
        {
            switch (item.getItemId()) {
                case R.id.dashboard:
                    openFragment(EDashboardFragment.newInstance("", ""));
                    return true;
                case R.id.transactions:
                    openFragment(ETransactionFragment.newInstance("", ""));
                    return true;
                case R.id.profile:
                    openFragment(EProfileFragment.newInstance("", ""));
                    return true;
            }
            return false;
        });
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.m_e_fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}