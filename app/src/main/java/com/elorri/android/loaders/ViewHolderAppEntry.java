package com.elorri.android.loaders;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolderAppEntry extends RecyclerView.ViewHolder {

    private final ImageView mIconImageView;
    private final TextView mLabelTextView;

    public ViewHolderAppEntry(View itemView) {
        super(itemView);
        mIconImageView = ((ImageView) itemView.findViewById(R.id.icon));
        mLabelTextView = ((TextView) itemView.findViewById(R.id.text));
    }

    public void setIcon(Drawable icon) {
        mIconImageView.setImageDrawable(icon);
    }

    public void setLabel(String label) {
        mLabelTextView.setText(label);
    }
}
