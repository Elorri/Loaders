package com.elorri.android.loaders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainAdapter extends ArrayAdapter<AppEntry> {


    private final Context mContext;

    public MainAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_2);
        mContext = getContext();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context
                    .LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_icon_text, parent, false);
        } else {
            view = convertView;
        }

        AppEntry item = getItem(position);
        ((ImageView) view.findViewById(R.id.icon)).setImageDrawable(item.getIcon());
        ((TextView) view.findViewById(R.id.text)).setText(item.getLabel());

        return view;
    }

    public void setData(List<AppEntry> data) {
        clear();
        if (data != null) {
            for (int i = 0; i < data.size(); i++) {
                add(data.get(i));
            }
        }
    }
}
