package com.allyn.lives.ui;

import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.allyn.lives.R;
import com.allyn.lives.bottontab.BottomBarTab;
import com.allyn.lives.bottontab.BottomNavigationBar;
import com.allyn.lives.fragment.MainFragment;
import com.allyn.lives.fragment.TVFragment;
import com.allyn.lives.ui.base.BaseActivity;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationBar bottomLayout;
    private boolean isreome = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Instant run");

        isreome = getSharedPreferences("config", MODE_PRIVATE).getBoolean("isUserDarkMode", false);

        if (isreome) {
            setTheme(R.style.AppTheme);

        } else {
            setTheme(R.style.Mytheme);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, MainFragment.newInstance()).commitAllowingStateLoss();

        setUpBottomNavigationBar();
    }

    public void setUpBottomNavigationBar() {

        bottomLayout = (BottomNavigationBar) findViewById(R.id.bottomLayout);
        bottomLayout.addTab(R.mipmap.ic_launcher, "首页", 0xff4a5965);
        bottomLayout.addTab(R.mipmap.ic_launcher, "发现", 0xff096c54);
        bottomLayout.addTab(R.mipmap.ic_launcher, "歌单", 0xff8a6a64);
        bottomLayout.addTab(R.mipmap.ic_launcher, "个人中心", 0xff553b36);
        bottomLayout.setOnTabListener(new BottomNavigationBar.TabListener() {
            @Override
            public void onSelected(BottomBarTab tab, int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = MainFragment.newInstance();
                        break;
                    case 1:
                        fragment = TVFragment.newInstance();
                        break;
                    case 2:
                        fragment = MainFragment.newInstance();
                        break;
                    case 3:
                        fragment = TVFragment.newInstance();
                        break;
                    default:
                        fragment = MainFragment.newInstance();
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

        if (id == R.id.nav_camera) {
            fragment = MainFragment.newInstance();
            bottomLayout.setVisibility(View.VISIBLE);
        } else if (id == R.id.nav_gallery) {
            fragment = TVFragment.newInstance();
            bottomLayout.setVisibility(View.GONE);
        } else if (id == R.id.nav_slideshow) {
            fragment = TVFragment.newInstance();
            bottomLayout.setVisibility(View.GONE);
        } else if (id == R.id.nav_manage) {
            fragment = MainFragment.newInstance();
            bottomLayout.setVisibility(View.GONE);
        } else if (id == R.id.nav_share) {
            setDarkTheme(isreome);
            this.recreate();
            return true;
        } else if (id == R.id.nav_send) {
            fragment = MainFragment.newInstance();
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .commitAllowingStateLoss();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setDarkTheme(boolean is) {
        SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isUserDarkMode", !is);
        editor.commit();
    }
}