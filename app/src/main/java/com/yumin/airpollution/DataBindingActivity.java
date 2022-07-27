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

public abstract class DataBindingActivity<T extends ViewDataBinding> extends AppCompatActivity {
    private T viewDataBinding;
    private ViewModelProvider viewModelProvider;

    protected abstract void initViewModel();

    protected abstract DataBindingConfig getDataBindingConfig();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initViewModel();
        performDataBinding();
    }

    public T getViewDataBinding() {
        return viewDataBinding;
    }

    private void performDataBinding() {
        DataBindingConfig dataBindingConfig = this.getDataBindingConfig();
        viewDataBinding = DataBindingUtil.setContentView(this, dataBindingConfig.getLayout());
        viewDataBinding.setVariable(dataBindingConfig.getVmVariableId(), dataBindingConfig.getViewModel());
        SparseArray<Object> bindingParams = dataBindingConfig.getBindingParams();
        for (int i = 0; i < bindingParams.size(); ++i) {
            viewDataBinding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i));
        }
        viewDataBinding.executePendingBindings();
        viewDataBinding.setLifecycleOwner(this);
    }

    protected <V extends ViewModel> V getViewModel(@NonNull Class<V> modelClass) {
        if (viewModelProvider == null) {
            viewModelProvider = new ViewModelProvider(this);
        }
        return viewModelProvider.get(modelClass);
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