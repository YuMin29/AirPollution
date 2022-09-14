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
import com.yumin.airpollution.MainActivity;
import com.yumin.airpollution.R;
import com.yumin.airpollution.databinding.FragmentSearchBinding;
import com.yumin.airpollution.recyclerview.RecyclerAdapter;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements MainActivity.SearchViewListener {
    private static final String TAG = "[SearchFragment]";
    SearchViewModel searchViewModel;
    FragmentSearchBinding fragmentSearchBinding;
    RecyclerAdapter recyclerAdapter;
    Boolean isQueryStringEmpty = true;

    public SearchFragment() {
        MainActivity.setSearchViewListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentSearchBinding = FragmentSearchBinding.inflate(inflater,container,false);
        return fragmentSearchBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        observeViewModel();
    }

    private void init() {
        searchViewModel = new SearchViewModel();

        recyclerAdapter = new RecyclerAdapter(new ArrayList<>(), false);

        LinearLayoutManager verLayoutManager = new LinearLayoutManager(getContext());
        verLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        fragmentSearchBinding.recyclerview.setLayoutManager(verLayoutManager);
        fragmentSearchBinding.recyclerview.setItemAnimator(new DefaultItemAnimator());
        fragmentSearchBinding.recyclerview.setAdapter(recyclerAdapter);
        fragmentSearchBinding.recyclerview.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    private void observeViewModel() {
        searchViewModel.isQueryStringEmpty.observe(getViewLifecycleOwner(), empty -> {
            fragmentSearchBinding.textView.setVisibility(empty ? View.VISIBLE : View.GONE);
            isQueryStringEmpty = empty;
        });

        searchViewModel.notFind.observe(getViewLifecycleOwner(), noFind -> {
            fragmentSearchBinding.recyclerview.setVisibility(noFind | isQueryStringEmpty ? View.GONE : View.VISIBLE);

            fragmentSearchBinding.notFindTextView.setVisibility(noFind ? View.VISIBLE : View.GONE);
        });

        searchViewModel.searchList.observe(getViewLifecycleOwner(), records -> {
            recyclerAdapter.clearItems();
            recyclerAdapter.addItems(records);
        });

        searchViewModel.notFindString.observe(getViewLifecycleOwner(), string ->
                fragmentSearchBinding.notFindTextView.setText(string));
    }

    @Override
    public void onQueryTextChange(String text) {
        if (searchViewModel != null) {
            String notFindWarning = getResources().getString(R.string.not_find_site_name, text);
            searchViewModel.onQueryTextChange(text, notFindWarning);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentSearchBinding = null;
    }
}
