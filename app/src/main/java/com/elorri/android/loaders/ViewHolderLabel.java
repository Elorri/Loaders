package com.elorri.android.loaders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ViewHolderLabel extends RecyclerView.ViewHolder {

    private final TextView mLabelTextView;

    public ViewHolderLabel(View itemView) {
        super(itemView);
        mLabelTextView = ((TextView) itemView.findViewById(R.id.text));
    }

    public void setLabel(String label) {
        mLabelTextView.setText(label);
    }
}
