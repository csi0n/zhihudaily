package com.csi0n.zhihudaily.ui.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.csi0n.zhihudaily.R;
import com.csi0n.zhihudaily.controller.MainController;
import com.csi0n.zhihudaily.ui.fragment.BaseFragment;
import com.csi0n.zhihudaily.ui.fragment.TianQiFragment;
import com.csi0n.zhihudaily.ui.fragment.ZhihuDailyFragment2;

import butterknife.Bind;

public class Main extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    @Bind(value = R.id.toolbar)
     Toolbar toolbar;
    @Bind(value = R.id.drawer_layout)
     DrawerLayout drawer;
    @Bind(value = R.id.nav_view)
     NavigationView navigationView;
    private MainController mMainController;
    //private ZhiHuDailyFragment mZhiHuDailyFragment;
    private ZhihuDailyFragment2 mZhihuDailyFragment2;
    private TianQiFragment mTianQiFragment;

    @Override
    protected void setRootView() {
        setContentView(R.layout.aty_main);
    }

    @Override
    protected void initWidget() {
        //mZhiHuDailyFragment = new ZhiHuDailyFragment();
        mTianQiFragment = new TianQiFragment();
        mZhihuDailyFragment2 = new ZhihuDailyFragment2();
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        mMainController = new MainController(this);
        mMainController.initMain();
        changeFragment(mZhihuDailyFragment2);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_zihudaily) {
            // Handle the camera action
            changeFragment(mZhihuDailyFragment2);
        } else if (id == R.id.nav_tianqi) {
            changeFragment(mTianQiFragment);
        } else if (id == R.id.nav_setting) {

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void startSetting() {

    }

    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.fl_content, targetFragment);
    }

}
