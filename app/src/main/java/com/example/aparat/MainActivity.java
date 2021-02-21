package com.example.aparat;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.aparat.adapter.AdapterTabs;
import com.example.aparat.databinding.ActivityMainBinding;
import com.example.aparat.fragment.CategoryFragment;
import com.example.aparat.fragment.FavoriteFragment;
import com.example.aparat.fragment.HomeFragment;
import com.example.aparat.fragment.LoginFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setSupportActionBar(mainBinding.toolbar);
        setContentView(mainBinding.getRoot());
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new CategoryFragment());
        fragmentList.add(new FavoriteFragment());
        fragmentList.add(new  LoginFragment());
        AdapterTabs adapterTabs = new AdapterTabs(getSupportFragmentManager(), fragmentList);
        mainBinding.viewPager.setAdapter(adapterTabs);

        mainBinding.bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Home:
                        mainBinding.viewPager.setCurrentItem(0);
                        mainBinding.bottomNav.getMenu().findItem(R.id.Home).setChecked(true);
                        break;
                    case R.id.category:
                        mainBinding.viewPager.setCurrentItem(1);
                        mainBinding.bottomNav.getMenu().findItem(R.id.category).setChecked(true);
                        break;
                    case R.id.favorite:
                        mainBinding.viewPager.setCurrentItem(2);
                        mainBinding.bottomNav.getMenu().findItem(R.id.favorite).setChecked(true);
                        break;

                    case R.id.login:
                        mainBinding.viewPager.setCurrentItem(3);
                        mainBinding.bottomNav.getMenu().findItem(R.id.login).setChecked(true);
                        break;

                }
                return false;
            }
        });
        mainBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mainBinding.bottomNav.getMenu().findItem(R.id.Home).setChecked(true);
                        break;
                    case 1:
                        mainBinding.bottomNav.getMenu().findItem(R.id.category).setChecked(true);
                        break;
                    case 2:
                        mainBinding.bottomNav.getMenu().findItem(R.id.favorite).setChecked(true);
                        break;
                    case 3:
                        mainBinding.bottomNav.getMenu().findItem(R.id.login).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }
}