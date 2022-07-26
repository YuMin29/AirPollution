package com.yumin.airpollution;

import android.util.SparseArray;

import androidx.lifecycle.ViewModel;

public class DataBindingConfig {
    private final int mLayout;
    private final int mVmVariableId;
    private final ViewModel mViewModel;
    private SparseArray mBindingParams = new SparseArray();

    public DataBindingConfig(int layout, int vmVariableId, ViewModel viewModel) {
        mLayout = layout;
        mVmVariableId = vmVariableId;
        this.mViewModel = viewModel;
    }

    public int getLayout() {
        return mLayout;
    }

    public int getVmVariableId() {
        return mVmVariableId;
    }

    public ViewModel getViewModel() {
        return mViewModel;
    }

    public SparseArray getBindingParams() {
        return mBindingParams;
    }

    public void addBindingParam(int variableId, Object object) {
        if (mBindingParams.get(variableId) == null) {
            mBindingParams.put(variableId, object);
        }
    }
}
