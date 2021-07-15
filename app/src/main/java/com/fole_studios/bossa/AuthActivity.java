package com.fole_studios.bossa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.fole_studios.bossa.adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class AuthActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private RegistrationFragment _registrationFragment;
    private LoginFragment _loginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);

        _registrationFragment = new RegistrationFragment();
        _loginFragment = new LoginFragment();

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);

        viewPagerAdapter.addFragment(_registrationFragment,"register");
        viewPagerAdapter.addFragment(_loginFragment,"login");

        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.getTabAt(0).setText("register");
        tabLayout.getTabAt(1).setText("login");

    }

}