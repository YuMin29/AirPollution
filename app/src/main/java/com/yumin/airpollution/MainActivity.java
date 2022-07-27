package com.yumin.airpollution;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yumin.airpollution.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends DataBindingActivity {
    private static final String TAG = "[" + MainActivity.class.getSimpleName() + "]";
    private MainViewModel mainViewModel;
    private ActivityMainBinding activityMainBinding;
    LinearLayoutManager verLayoutManager;
    LinearLayoutManager horLayoutManager;
    RecyclerAdapter horAdapter;
    RecyclerAdapter verAdapter;

    @Override
    protected void initViewModel() {
        mainViewModel = (MainViewModel) getViewModel(MainViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_main, BR.viewModel, mainViewModel);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = (ActivityMainBinding) getViewDataBinding();
        horAdapter = new RecyclerAdapter(new ArrayList<>(),true);
        verAdapter = new RecyclerAdapter(new ArrayList<>(),false);
        setUp();
    }

    private void setUp() {
        horLayoutManager = new LinearLayoutManager(this);
        horLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        activityMainBinding.horRecyclerview.setLayoutManager(horLayoutManager);
        activityMainBinding.horRecyclerview.setItemAnimator(new DefaultItemAnimator());
        activityMainBinding.horRecyclerview.setAdapter(horAdapter);

        verLayoutManager = new LinearLayoutManager(this);
        verLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        activityMainBinding.verRecyclerView.setLayoutManager(verLayoutManager);
        activityMainBinding.verRecyclerView.setItemAnimator(new DefaultItemAnimator());
        activityMainBinding.verRecyclerView.setAdapter(verAdapter);
        activityMainBinding.verRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        searchItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return false;
            }
        });

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}