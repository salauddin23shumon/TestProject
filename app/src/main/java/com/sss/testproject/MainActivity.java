package com.sss.testproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.sss.testproject.listData.DataListFragment;
import com.sss.testproject.userLogin.LoginFragment;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginComplete {

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fragment = new LoginFragment();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }
    }

    @Override
    public void onLoginSuccess() {
        fragment = new DataListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }
}