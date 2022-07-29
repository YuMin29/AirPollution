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
import com.yumin.airpollution.MainActivity;
import com.yumin.airpollution.R;
import com.yumin.airpollution.databinding.FragmentSearchBinding;
import com.yumin.airpollution.recyclerview.RecyclerAdapter;

import java.util.ArrayList;

public class SearchFragment extends DataBindingFragment implements MainActivity.SearchViewListener {
    SearchViewModel searchViewModel;
    FragmentSearchBinding fragmentSearchBinding;
    RecyclerAdapter recyclerAdapter;

    public SearchFragment() {
        MainActivity.setSearchViewListener(this);
    }


    @Override
    protected void initViewModel() {
        searchViewModel = (SearchViewModel) getViewModel(SearchViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_search, BR.viewModel, searchViewModel);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentSearchBinding = (FragmentSearchBinding) getBinding();
        setUp();
    }

    private void setUp() {
        recyclerAdapter = new RecyclerAdapter(new ArrayList<>(), false);
        LinearLayoutManager verLayoutManager;
        verLayoutManager = new LinearLayoutManager(getContext());
        verLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        fragmentSearchBinding.recyclerview.setLayoutManager(verLayoutManager);
        fragmentSearchBinding.recyclerview.setItemAnimator(new DefaultItemAnimator());
        fragmentSearchBinding.recyclerview.setAdapter(recyclerAdapter);
        fragmentSearchBinding.recyclerview.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onQueryTextChange(String queryString) {
        if (searchViewModel != null) {
            String notFindWarning = getResources().getString(R.string.not_find_site_name, queryString);
            searchViewModel.onQueryTextChange(queryString, notFindWarning);
        }
    }
}
