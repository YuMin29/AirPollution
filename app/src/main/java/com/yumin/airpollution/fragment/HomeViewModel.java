package com.yumin.airpollution.fragment;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yumin.airpollution.data.AirQuality;
import com.yumin.airpollution.data.Records;
import com.yumin.airpollution.repository.RemoteRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {
    private static final String TAG = HomeViewModel.class.getSimpleName();
    public static final int THRESHOLD = 30;
    public MutableLiveData<List<Records>> horListData;
    public MutableLiveData<List<Records>> verListData;
    public MutableLiveData<Boolean> isLoading;
    private RemoteRepository remoteRepository;

    public HomeViewModel() {
        remoteRepository = new RemoteRepository();
        horListData = new MutableLiveData<>();
        verListData = new MutableLiveData<>();
        isLoading = new MutableLiveData<>();
        fetchData();
    }

    public void fetchData() {
        isLoading.setValue(true);
        remoteRepository
                .getAirQuality()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "[onSubscribe]");
                    }

                    @Override
                    public void onSuccess(Object o) {
                        Log.d(TAG, "[onSuccess]");
                        processData(o);
                        isLoading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "[onError] error = " + e.getMessage());
                        isLoading.setValue(false);
                    }
                });
    }

    private void processData(Object object) {
        if (object == null)
            return;

        final AirQuality airQuality = (AirQuality) object;
        List<Records> hor = new ArrayList<>();
        List<Records> ver = new ArrayList<>();

        for (Records records : airQuality.getRecords()) {
            try {
                if (Integer.valueOf(records.getPm25()) > THRESHOLD) {
                    ver.add(records);
                } else {
                    hor.add(records);
                }
            } catch (Exception exception) {

            }
        }

        verListData.setValue(ver);
        horListData.setValue(hor);
    }
}
