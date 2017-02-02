package com.elorri.android.loaders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<ViewHolderAppEntry> {


    private final Context mContext;
    private final List<AppEntry> mList;


    public MainAdapter(Context context, List<AppEntry> list) {
        mContext = context;
        mList=list;
    }

    public void swapData(List<AppEntry> data) {
        mList.clear();
        mList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolderAppEntry onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item_icon_text, parent, false);
        return new ViewHolderAppEntry(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderAppEntry viewHolderAppEntry, int position) {
        AppEntry appEntry=mList.get(position);

        viewHolderAppEntry.setIcon(appEntry.getIcon());
        viewHolderAppEntry.setLabel(appEntry.getLabel());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
