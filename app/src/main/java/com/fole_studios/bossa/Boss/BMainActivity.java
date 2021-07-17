package com.fole_studios.bossa.Boss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.fole_studios.bossa.Employee.EDashboardFragment;
import com.fole_studios.bossa.Employee.EProfileFragment;
import com.fole_studios.bossa.Employee.ETransactionFragment;
import com.fole_studios.bossa.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class BMainActivity extends AppCompatActivity
{

    private BottomNavigationView _bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_main);

        _bottomNavigationView = findViewById(R.id.b_main_bottom_nav);

        openFragment(BDashboardFragment.newInstance("",""));

        bottomFunctionality();
    }

    private void bottomFunctionality()
    {
        _bottomNavigationView.setOnItemSelectedListener(item ->
        {
            switch (item.getItemId()) {
                case R.id.dashboard:
                    openFragment(BDashboardFragment.newInstance("", ""));
                    return true;
                case R.id.transactions:
                    openFragment(BTransactionFragment.newInstance("", ""));
                    return true;
                case R.id.profile:
                    openFragment(BProfileFragment.newInstance("", ""));
                    return true;
            }
            return false;
        });
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.b_main_fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}