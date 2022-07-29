package com.yumin.airpollution;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.yumin.airpollution.databinding.ActivityMainBinding;
import java.util.List;

public class MainActivity extends DataBindingActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private MainViewModel mainViewModel;
    private ActivityMainBinding activityMainBinding;
    private static SearchViewListener searchViewListener = null;

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
        mainViewModel.fragmentList.observe(this, new Observer<List<Fragment>>() {
            @Override
            public void onChanged(List<Fragment> fragments) {
                activityMainBinding.viewPager.setAdapter(new FragmentStateAdapter(getSupportFragmentManager(),
                        getLifecycle()) {
                    @NonNull
                    @Override
                    public Fragment createFragment(int position) {
                        return fragments.get(position);
                    }

                    @Override
                    public int getItemCount() {
                        return fragments.size();
                    }
                });
            }
        });
        activityMainBinding.viewPager.setUserInputEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                activityMainBinding.viewPager.setCurrentItem(1);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                activityMainBinding.viewPager.setCurrentItem(0);
                return true;
            }
        });

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint(getString(R.string.enter_site_name));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (searchViewListener != null)
                    searchViewListener.onQueryTextChange(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    public static void setSearchViewListener(SearchViewListener listener){
        searchViewListener = listener;
    }

    public interface SearchViewListener{
        void onQueryTextChange(String queryString);
    }
}