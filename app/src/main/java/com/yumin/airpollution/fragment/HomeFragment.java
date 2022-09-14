package com.yumin.airpollution.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.yumin.airpollution.databinding.FragmentHomeBinding;
import com.yumin.airpollution.recyclerview.RecyclerAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private static final String TAG = "[HomeFragment]";
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding fragmentHomeBinding;
    private RecyclerAdapter horAdapter;
    private RecyclerAdapter verAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater,container,false);
        return fragmentHomeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        horAdapter = new RecyclerAdapter(new ArrayList<>(), true);
        verAdapter = new RecyclerAdapter(new ArrayList<>(), false);
        init();
        observeViewModel();
    }

    private void init() {
        homeViewModel = new HomeViewModel();

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

    private void observeViewModel() {
        homeViewModel.isLoading.observe(getViewLifecycleOwner(), loading ->
                fragmentHomeBinding.progressBar.setVisibility(loading ? View.VISIBLE : View.GONE));

        homeViewModel.verListData.observe(getViewLifecycleOwner(), items -> {
            verAdapter.clearItems();
            verAdapter.addItems(items);
        });

        homeViewModel.horListData.observe(getViewLifecycleOwner(), items -> {
            horAdapter.clearItems();
            horAdapter.addItems(items);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentHomeBinding = null;
    }
}
