package com.elorri.android.loaders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<ViewHolderLabel> {

    private final Context mContext;
    private final List<Label> mLabels;

    public MainAdapter(Context context, List<Label> labels) {
        mContext = context;
        mLabels =labels;
    }

    public void swapData(List<Label> data) {
        mLabels.clear();
        mLabels.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolderLabel onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_label, parent, false);
        return new ViewHolderLabel(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderLabel viewHolderLabel, int position) {
        Label label = mLabels.get(position);
        viewHolderLabel.setLabel(label.getLabel());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
