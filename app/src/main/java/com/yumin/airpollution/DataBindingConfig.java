package com.yumin.airpollution;

import android.util.SparseArray;

import androidx.lifecycle.ViewModel;

public class DataBindingConfig {
    private final int layout;
    private final int vmVariableId;
    private final ViewModel viewModel;
    private SparseArray bindingParams = new SparseArray();

    public DataBindingConfig(int layout, int vmVariableId, ViewModel viewModel) {
        this.layout = layout;
        this.vmVariableId = vmVariableId;
        this.viewModel = viewModel;
    }

    public int getLayout() {
        return layout;
    }

    public int getVmVariableId() {
        return vmVariableId;
    }

    public ViewModel getViewModel() {
        return viewModel;
    }

    public SparseArray getBindingParams() {
        return bindingParams;
    }

    public void addBindingParam(int variableId, Object object) {
        if (bindingParams.get(variableId) == null) {
            bindingParams.put(variableId, object);
        }
    }
}
