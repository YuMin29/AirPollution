package com.yumin.airpollution;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yumin.airpollution.data.AirQuality;
import com.yumin.airpollution.data.Records;
import com.yumin.airpollution.fragment.HomeFragment;
import com.yumin.airpollution.fragment.SearchFragment;
import com.yumin.airpollution.repository.RemoteRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {
    private static final String TAG = MainActivity.class.getSimpleName();
    public MutableLiveData<List<Fragment>> fragmentList = new MutableLiveData<>();

    public MainViewModel() {
        List<Fragment> list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new SearchFragment());
        fragmentList.postValue(list);
    }
}
