package com.yumin.airpollution;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.yumin.airpollution.data.Records;
import com.yumin.airpollution.recyclerview.RecyclerAdapter;

import java.util.List;

public class BindingUtils {
    @BindingAdapter({"adapterList"})
    public static void adapterList(RecyclerView recyclerView, List<Records> data) {
        RecyclerAdapter adapter = (RecyclerAdapter) recyclerView.getAdapter();
        if (data == null)
            return;

        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(data);
        }
    }
}
