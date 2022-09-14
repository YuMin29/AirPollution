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

//    Filter filter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            ArrayList<Records> filteredList = new ArrayList<>();
//            if (constraint == null || constraint.length() == 0) {
//                filteredList.addAll(recordList);
//            } else {
//                for (Records records : recordList) {
//                    if (records.getSiteName().contains(constraint.toString())) {
//                        filteredList.add(records);
//                    }
//                }
//            }
//            FilterResults filterResults = new FilterResults();
//            filterResults.values = filteredList;
//            return filterResults;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            recordList.clear();
//            recordList.addAll((Collection<? extends Records>) results.values);
//            notifyDataSetChanged();
//        }
//    };

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
            final Records airQuality = recordList.get(position);
            horItemViewBinding.siteId.setText(airQuality.getSiteId());
            horItemViewBinding.siteName.setText(airQuality.getSiteName());
            horItemViewBinding.pm25.setText(airQuality.getPm25());
            horItemViewBinding.siteCounty.setText(airQuality.getCounty());
            horItemViewBinding.siteStatus.setText(airQuality.getStatus());
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
            final Records airQuality = recordList.get(position);
            verItemGoodBinding.goodSiteId.setText(airQuality.getSiteId());
            verItemGoodBinding.goodPm25.setText(airQuality.getPm25());
            verItemGoodBinding.siteName.setText(airQuality.getSiteName());
            verItemGoodBinding.county.setText(airQuality.getCounty());
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
