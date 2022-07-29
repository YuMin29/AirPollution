package com.yumin.airpollution.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.yumin.airpollution.BR;
import com.yumin.airpollution.DataBindingConfig;
import com.yumin.airpollution.DataBindingFragment;
import com.yumin.airpollution.R;
import com.yumin.airpollution.databinding.FragmentHomeBinding;
import com.yumin.airpollution.recyclerview.RecyclerAdapter;

import java.util.ArrayList;

public class HomeFragment extends DataBindingFragment {
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding fragmentHomeBinding;
    private RecyclerAdapter horAdapter;
    private RecyclerAdapter verAdapter;

    public HomeFragment() {
    }

    @Override
    protected void initViewModel() {
        homeViewModel = (HomeViewModel) getViewModel(HomeViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_home, BR.viewModel, homeViewModel);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentHomeBinding = (FragmentHomeBinding) getBinding();
        horAdapter = new RecyclerAdapter(new ArrayList<>(), true);
        verAdapter = new RecyclerAdapter(new ArrayList<>(), false);
        setUp();
    }

    private void setUp() {

        LinearLayoutManager horLayoutManager;
        horLayoutManager = new LinearLayoutManager(getContext());
        horLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        fragmentHomeBinding.horRecyclerview.setLayoutManager(horLayoutManager);
        fragmentHomeBinding.horRecyclerview.setItemAnimator(new DefaultItemAnimator());
        fragmentHomeBinding.horRecyclerview.setAdapter(horAdapter);

        LinearLayoutManager verLayoutManager;
        verLayoutManager = new LinearLayoutManager(getContext());
        verLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        fragmentHomeBinding.verRecyclerView.setLayoutManager(verLayoutManager);
        fragmentHomeBinding.verRecyclerView.setItemAnimator(new DefaultItemAnimator());
        fragmentHomeBinding.verRecyclerView.setAdapter(verAdapter);
        fragmentHomeBinding.verRecyclerView.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }
}
