package com.allyn.lives.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;

import com.allyn.lives.R;
import com.allyn.lives.activity.base.BaseActivity;
import com.allyn.lives.app.MainApp;
import com.allyn.lives.fragment.SettingsFragment;
import com.allyn.lives.fragment.TranslationFragment;
import com.allyn.lives.fragment.books.BooksMainFragment;
import com.allyn.lives.fragment.books.RecommendBooksFragment;
import com.allyn.lives.fragment.music.MusicLocalFragment;
import com.allyn.lives.fragment.video.TVFragment;
import com.allyn.lives.service.MusicService;
import com.allyn.lives.view.bottontab.BottomBarTab;
import com.allyn.lives.view.bottontab.BottomNavigationBar;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private boolean isok = true;

    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    @Bind(R.id.bottomLayout)
    BottomNavigationBar bottomLayout;

    MusicService mService;
    boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Intent intent = new Intent(this, MusicService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        setThere();
        initView();
    }

    private void setThere() {
        isok = getSharedPreferences("config", MODE_PRIVATE).getBoolean("isUserDarkMode", false);
//        if (isok) {
        setTheme(R.style.AppTheme);
//        } else {
//            setTheme(R.style.Mytheme);
//        }
    }

    private void initView() {

//        setSupportActionBar(toolbar);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, BooksMainFragment.newInstance()).commitAllowingStateLoss();

        setUpBottomNavigationBar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mConnection != null)
            unbindService(mConnection);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        int id = item.getItemId();
        if (id == R.id.nav_gallery) {
            fragment = BooksMainFragment.newInstance();
        } else if (id == R.id.nav_camera) {
            fragment = MusicLocalFragment.newInstance();
            bottomLayout.setVisibility(View.GONE);
        } else if (id == R.id.nav_manage) {
            fragment = TranslationFragment.newInstance();
            bottomLayout.setVisibility(View.GONE);
        }
//        else if (id == R.id.nav_slideshow) {
//            fragment = TVFragment.newInstance();
//            bottomLayout.setVisibility(View.GONE);
//        }
        else if (id == R.id.nav_share) {
            setDarkTheme(isok);
            this.recreate();
            return true;
        } else if (id == R.id.nav_send) {
            fragment = SettingsFragment.newInstance();
            bottomLayout.setVisibility(View.GONE);
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .commitAllowingStateLoss();

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setDarkTheme(boolean is) {
        SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isUserDarkMode", !is);
        editor.commit();
    }

    public void setUpBottomNavigationBar() {
        bottomLayout.addTab(R.mipmap.ic_book_default, getResources().getString(R.string.classify), MainApp.getContexts().getResources().getColor(R.color.colorPrimary));
        bottomLayout.addTab(R.drawable.ic_settings, getResources().getString(R.string.recommend), MainApp.getContexts().getResources().getColor(R.color.colorAccent));
        bottomLayout.setOnTabListener(new BottomNavigationBar.TabListener() {
            @Override
            public void onSelected(BottomBarTab tab, int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = BooksMainFragment.newInstance();
                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        fragment = RecommendBooksFragment.newInstance();
                        break;
                }
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, fragment)
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        .commitAllowingStateLoss();
            }
        });
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            MusicService.MyBinder binder = (MusicService.MyBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}