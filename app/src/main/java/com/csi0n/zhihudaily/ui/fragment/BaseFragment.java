package com.csi0n.zhihudaily.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.csi0n.zhihudaily.utils.CLog;

import org.xutils.x;

/**
 * Created by chqss on 2016/2/27 0027.
 */
public abstract class BaseFragment extends Fragment {
    protected abstract void initWidget();
    private boolean injected = false;
    public FragmentActivity aty;
    protected BaseFragment currentFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aty = getActivity();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!injected) {
            x.view().inject(this, this.getView());
        }
        initWidget();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        injected = true;
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onStart() {
        super.onStart();
        CLog.iMessage(this.getClass().getName() + "**********onStart**********");
    }

    @Override
    public void onResume() {
        super.onResume();
        CLog.iMessage(this.getClass().getName() + "**********onResume**********");
    }

    @Override
    public void onPause() {
        super.onPause();
        CLog.iMessage(this.getClass().getName() + "**********onPause**********");
    }

    @Override
    public void onStop() {
        super.onStop();
        CLog.iMessage(this.getClass().getName() + "**********onStop**********");
    }

    @Override
    public void onDestroy() {
        CLog.iMessage(this.getClass().getName() + "**********onDestroy**********");
        super.onDestroy();
    }


    public void startActivity(Class<?> classz) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), classz);
        getActivity().startActivity(intent);
    }

    public void startActivity(Class<?> classz,Bundle bundle){
        Intent intent = new Intent();
        intent.setClass(getActivity(), classz);
        intent.putExtras(bundle);
        getActivity().startActivity(intent);
    }
}
