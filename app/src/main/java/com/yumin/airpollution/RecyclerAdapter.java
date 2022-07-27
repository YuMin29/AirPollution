package com.yumin.airpollution;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yumin.airpollution.data.Records;
import com.yumin.airpollution.databinding.HorItemViewBinding;
import com.yumin.airpollution.databinding.VerItemGoodBinding;
import com.yumin.airpollution.databinding.VerItemNormalBinding;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final int VIEW_TYPE_HOR = 0;
    private static final int VIEW_TYPE_VER_GOOD = 1;
    private static final int VIEW_TYPE_VER_NORMAL = 2;
    private List<Records> recordList = new ArrayList<>();
    private boolean isHorizontal;

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
                        LayoutInflater.from(parent.getContext()),parent,false);
                return new HorItemViewHolder(horItemViewBinding);
            case VIEW_TYPE_VER_GOOD:
                VerItemGoodBinding verItemGoodBinding = VerItemGoodBinding.inflate(
                        LayoutInflater.from(parent.getContext()),parent,false);
                return new VerGoodItemViewHolder(verItemGoodBinding);
            case VIEW_TYPE_VER_NORMAL:
            default:
                VerItemNormalBinding verItemNormalBinding = VerItemNormalBinding.inflate(
                        LayoutInflater.from(parent.getContext()),parent,false);
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

    public void clearItems(){
        recordList.clear();
    }

    public void addItems(List<Records> data){
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

    public class HorItemViewHolder extends BaseViewHolder{
        HorItemViewBinding mViewBinding;
        ItemViewModel itemViewModel;

        public HorItemViewHolder(@NonNull HorItemViewBinding binding) {
            super(binding.getRoot());
            mViewBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final Records airQuality = recordList.get(position);
            itemViewModel = new ItemViewModel(airQuality,null);
            mViewBinding.setViewModel(itemViewModel);
        }
    }

    public class VerGoodItemViewHolder extends BaseViewHolder{
        VerItemGoodBinding mViewBinding;
        ItemViewModel itemViewModel;
        public VerGoodItemViewHolder(@NonNull VerItemGoodBinding binding) {
            super(binding.getRoot());
            mViewBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final Records airQuality = recordList.get(position);
            itemViewModel = new ItemViewModel(airQuality,null);
            mViewBinding.setViewModel(itemViewModel);
        }
    }

    public class VerNormalItemViewHolder extends BaseViewHolder implements ItemViewModel.ItemNormalListener{
        VerItemNormalBinding mViewBinding;
        ItemViewModel itemViewModel;
        public VerNormalItemViewHolder(@NonNull VerItemNormalBinding binding) {
            super(binding.getRoot());
            mViewBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final Records records = recordList.get(position);
            itemViewModel = new ItemViewModel(records,this);
            mViewBinding.setViewModel(itemViewModel);
        }

        @Override
        public void onItemClick() {
            Toast.makeText(itemView.getContext(), "yyy",Toast.LENGTH_LONG).show();
        }
    }
}
