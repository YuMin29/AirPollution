package com.yumin.airpollution;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public abstract class DataBindingFragment<T extends ViewDataBinding> extends Fragment {
    private AppCompatActivity activity;
    private T viewDataBinding;
    private ViewModelProvider viewModelProvider;

    protected abstract void initViewModel();

    protected abstract DataBindingConfig getDataBindingConfig();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (AppCompatActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DataBindingConfig dataBindingConfig = getDataBindingConfig();
        viewDataBinding = DataBindingUtil.inflate(inflater, dataBindingConfig.getLayout(), container, false);
        viewDataBinding.setLifecycleOwner(this);
        viewDataBinding.setVariable(dataBindingConfig.getVmVariableId(), dataBindingConfig.getViewModel());
        SparseArray bindingParams = dataBindingConfig.getBindingParams();
        for (int i = 0; i < bindingParams.size(); i++) {
            viewDataBinding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i));
        }
        viewDataBinding.executePendingBindings();
        return viewDataBinding.getRoot();
    }

    protected T getBinding() {
        return viewDataBinding;
    }

    protected <V extends ViewModel> V getViewModel(@NonNull Class<V> modelClass) {
        if (viewModelProvider == null) {
            viewModelProvider = new ViewModelProvider(this);
        }
        return viewModelProvider.get(modelClass);
    }
}