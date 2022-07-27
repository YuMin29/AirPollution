package com.yumin.airpollution;

import androidx.lifecycle.MutableLiveData;

import com.yumin.airpollution.data.Records;

public class ItemViewModel {
    public MutableLiveData<Records> records;
    private ItemNormalListener listener;

    public ItemViewModel(Records records, ItemNormalListener listener) {
        this.records = new MutableLiveData<>(records);
        this.listener = listener;
    }

    public void onNormalClick(){
        if (listener != null)
            listener.onItemClick();
    }

    public interface ItemNormalListener {
        void onItemClick();
    }
}
