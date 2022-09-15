package com.yumin.airpollution.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yumin.airpollution.data.Records;
import com.yumin.airpollution.databinding.HorItemViewBinding;
import com.yumin.airpollution.databinding.VerItemGoodBinding;
import com.yumin.airpollution.databinding.VerItemNormalBinding;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final int VIEW_TYPE_HOR = 0;
    private static final int VIEW_TYPE_VER_GOOD = 1;
    private static final int VIEW_TYPE_VER_NORMAL = 2;
    private boolean isHorizontal;
    private List<Records> recordList;

    public RecyclerAdapter(List<Records> recordsList, boolean isHorizontal) {
        this.recordList = recordsList;
        this.isHorizontal = isHorizontal;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_HOR:
                HorItemViewBinding horItemViewBinding = HorItemViewBinding.inflate(
                        LayoutInflater.from(parent.getContext()), parent, false);
                return new HorItemViewHolder(horItemViewBinding);
            case VIEW_TYPE_VER_GOOD:
                VerItemGoodBinding verItemGoodBinding = VerItemGoodBinding.inflate(
                        LayoutInflater.from(parent.getContext()), parent, false);
                return new VerGoodItemViewHolder(verItemGoodBinding);
            case VIEW_TYPE_VER_NORMAL:
            default:
                VerItemNormalBinding verItemNormalBinding = VerItemNormalBinding.inflate(
                        LayoutInflater.from(parent.getContext()), parent, false);
                return new VerNormalItemViewHolder(verItemNormalBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    public void clearItems() {
        recordList.clear();
    }

    public void addItems(List<Records> data) {
        if (recordList == null)
            return;
        recordList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (isHorizontal) {
            return VIEW_TYPE_HOR;
        } else {
            if (recordList.get(position).getStatus().equals("良好"))
                return VIEW_TYPE_VER_GOOD;
            else
                return VIEW_TYPE_VER_NORMAL;
        }
    }

    public class HorItemViewHolder extends BaseViewHolder {
        HorItemViewBinding horItemViewBinding;

        public HorItemViewHolder(@NonNull HorItemViewBinding binding) {
            super(binding.getRoot());
            horItemViewBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final Records records = recordList.get(position);
            horItemViewBinding.siteId.setText(records.getSiteId());
            horItemViewBinding.siteName.setText(records.getSiteName());
            horItemViewBinding.pm25.setText(records.getPm25());
            horItemViewBinding.siteCounty.setText(records.getCounty());
            horItemViewBinding.siteStatus.setText(records.getStatus());
        }
    }

    public class VerGoodItemViewHolder extends BaseViewHolder {
        VerItemGoodBinding verItemGoodBinding;

        public VerGoodItemViewHolder(@NonNull VerItemGoodBinding binding) {
            super(binding.getRoot());
            verItemGoodBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final Records records = recordList.get(position);
            verItemGoodBinding.goodSiteId.setText(records.getSiteId());
            verItemGoodBinding.goodPm25.setText(records.getPm25());
            verItemGoodBinding.siteName.setText(records.getSiteName());
            verItemGoodBinding.county.setText(records.getCounty());
        }
    }

    public class VerNormalItemViewHolder extends BaseViewHolder {
        VerItemNormalBinding verItemNormalBinding;

        public VerNormalItemViewHolder(@NonNull VerItemNormalBinding binding) {
            super(binding.getRoot());
            verItemNormalBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final Records records = recordList.get(position);
            verItemNormalBinding.normalSiteId.setText(records.getSiteId());
            verItemNormalBinding.pm25.setText(records.getPm25());
            verItemNormalBinding.county.setText(records.getCounty());
            verItemNormalBinding.siteName.setText(records.getSiteName());
            verItemNormalBinding.normalStatus.setText(records.getStatus());
            verItemNormalBinding.arrow.setOnClickListener(view ->
                    Toast.makeText(itemView.getContext(), records.getStatus(), Toast.LENGTH_SHORT).show());
        }
    }
}
