package com.elorri.android.loaders;

/**
 * This class holds the per-item data in our {@link MainLoader}.
 */
public class Label {

    private String mLabel;

    public Label(String label) {
        mLabel = label;
    }

    public String getLabel() {
        return mLabel;
    }
}