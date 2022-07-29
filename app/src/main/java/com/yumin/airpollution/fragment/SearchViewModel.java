package com.yumin.airpollution.fragment;

import android.util.Log;
import android.widget.Filter;

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

public class SearchViewModel extends ViewModel {
    private static final String TAG = SearchViewModel.class.getSimpleName();
    public MutableLiveData<Boolean> isEmpty = new MutableLiveData<>();
    public MutableLiveData<Boolean> notFind = new MutableLiveData<>();
    public MutableLiveData<String> notFindString = new MutableLiveData<>();
    public MutableLiveData<List<Records>> searchList = new MutableLiveData<>();
    private RemoteRepository remoteRepository;
    private List<Records> list = new ArrayList<>();

    public SearchViewModel() {
        isEmpty.postValue(true);
        notFind.postValue(false);
        remoteRepository = new RemoteRepository();
        fetchData();
    }

    public void fetchData() {
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
                        Log.d(TAG, "[onSuccess] size = " + ((AirQuality) o).getRecords().size());
                        if (((AirQuality) o).getRecords().size() > 0) {
                            list = ((AirQuality) o).getRecords();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "[onError] error = " + e.getMessage());
                    }
                });
    }

    public void onQueryTextChange(String queryString, String notFindWarning) {
        isEmpty.postValue(queryString.isEmpty());
        notFind.postValue(false);

        if (!queryString.isEmpty()) {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    ArrayList<Records> filteredList = new ArrayList<>();
                    for (Records records : list) {
                        if (records.getSiteName().contains(charSequence.toString())) {
                            filteredList.add(records);
                        }
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = filteredList;
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    List<Records> resultList = (List<Records>) results.values;
                    if (resultList.size() > 0) {
                        searchList.postValue((List<Records>) results.values);
                    } else {
                        notFind.postValue(true);
                        notFindString.postValue(notFindWarning);
                    }
                }
            };
            filter.filter(queryString);
        }
    }
}
