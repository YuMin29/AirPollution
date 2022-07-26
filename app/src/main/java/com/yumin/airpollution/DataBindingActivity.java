package com.yumin.airpollution;

import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public abstract class DataBindingActivity extends AppCompatActivity {
    private ViewDataBinding mViewDataBinding;
    private ViewModelProvider mViewModelProvider;

    protected abstract void initViewModel();

    protected abstract DataBindingConfig getDataBindingConfig();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initViewModel();
        performDataBinding();
    }

    public ViewDataBinding getViewDataBinding() {
        return mViewDataBinding;
    }

    private void performDataBinding() {
        DataBindingConfig dataBindingConfig = this.getDataBindingConfig();
        mViewDataBinding = DataBindingUtil.setContentView(this, dataBindingConfig.getLayout());
        mViewDataBinding.setVariable(dataBindingConfig.getVmVariableId(), dataBindingConfig.getViewModel());
        SparseArray<Object> bindingParams = dataBindingConfig.getBindingParams();
        for (int i = 0; i < bindingParams.size(); ++i) {
            mViewDataBinding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i));
        }
        mViewDataBinding.executePendingBindings();
    }

    protected <T extends ViewModel> T getViewModel(@NonNull Class<T> modelClass) {
        if (mViewModelProvider == null) {
            mViewModelProvider = new ViewModelProvider(this);
        }
        return mViewModelProvider.get(modelClass);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }
}