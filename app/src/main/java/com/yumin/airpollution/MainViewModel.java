package com.yumin.airpollution;

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

public class MainViewModel extends ViewModel {
    private static final String TAG = "[" + MainActivity.class.getSimpleName() + "]";
    private RemoteRepository remoteRepository;
    public MutableLiveData<List<Records>> horListData;
    public MutableLiveData<List<Records>> verListData;

    public MainViewModel() {
        remoteRepository = new RemoteRepository();
        horListData = new MutableLiveData<>();
        verListData = new MutableLiveData<>();
        fetchData();
    }

    public void fetchData(){
        remoteRepository
                .getAirQuality()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG,"[onSubscribe]");
                    }

                    @Override
                    public void onSuccess(Object o) {
                        Log.d(TAG,"[onSuccess]");
                        processData(o);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,"[onError] error = "+e.getMessage());
                    }
                });
    }

    private void processData(Object o){
        if (o == null)
            return;
        final AirQuality airQuality = (AirQuality) o;
        List<Records> hor = new ArrayList<>();
        List<Records> ver = new ArrayList<>();
        for (Records records : airQuality.getRecords()) {
            if (Integer.valueOf(records.getPm25()) > 7) {
                ver.add(records);
            } else {
                hor.add(records);
            }
        }
        verListData.postValue(ver);
        horListData.postValue(hor);
    }


    public LiveData<List<Records>> getHorListData(){
        return horListData;
    }

    public LiveData<List<Records>> getVerListData(){
        return verListData;
    }
}
