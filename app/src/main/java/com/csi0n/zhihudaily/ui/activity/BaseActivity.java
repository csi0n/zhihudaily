package com.csi0n.zhihudaily.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.csi0n.zhihudaily.AppManager;
import com.csi0n.zhihudaily.ui.fragment.BaseFragment;
import com.csi0n.zhihudaily.utils.CLog;

import org.xutils.x;

/**
 * Created by chqss on 2016/2/27 0027.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected abstract void initWidget();

    public BaseActivity aty;
    protected BaseFragment currentFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!"com.csi0n.zhihudaily".equals(getApplication().getPackageName()))
            AppManager.getAppManager().AppExit(this);
        AppManager.getAppManager().addActivity(this);
        aty = this;
        x.view().inject(this);
        initWidget();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        CLog.iMessage(this.getClass().getName() + "**********onCreate**********");
    }

    @Override
    protected void onStart() {
        super.onStart();
        CLog.iMessage(this.getClass().getName() + "**********onStart**********");
    }

    @Override
    protected void onResume() {
        super.onResume();
        CLog.iMessage(this.getClass().getName() + "**********onResume**********");
    }

    @Override
    protected void onPause() {
        super.onPause();
        CLog.iMessage(this.getClass().getName() + "**********onPause**********");
    }

    @Override
    protected void onStop() {
        super.onStop();
        CLog.iMessage(this.getClass().getName() + "**********onStop**********");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        CLog.iMessage(this.getClass().getName() + "**********onRestart**********");
    }

    @Override
    protected void onDestroy() {
        CLog.iMessage(this.getClass().getName() + "**********onDestroy**********");
        super.onDestroy();
    }

    public void changeFragment(int resView, BaseFragment targetFragment) {
        if (targetFragment.equals(currentFragment)) {
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!targetFragment.isAdded()) {
            transaction.add(resView, targetFragment, targetFragment.getClass().getName());
        }
        if (targetFragment.isHidden()) {
            transaction.show(targetFragment);
        }
        if (currentFragment != null && currentFragment.isVisible()) {
            transaction.hide(currentFragment);
        }
        currentFragment = targetFragment;
        transaction.commit();
    }
    public void skipActivity(Context context, Class<?> classz) {
        startActivity(context, classz, null);
        aty.finish();
    }
    private void startActivity(Context context, Class<?> classz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(context, classz);
        if (bundle != null)
            intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
