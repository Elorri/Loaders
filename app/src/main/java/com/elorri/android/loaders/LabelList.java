package com.elorri.android.loaders;

import android.database.ContentObservable;
import android.database.ContentObserver;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by Elorri on 05/02/2017.
 */
public class LabelList {

    private ArrayList<Label> labels;

    private final ContentObservable mContentObservable = new ContentObservable();

    public LabelList() {
        int count = 30;
        labels = new ArrayList<>(count);
        for (int i = 0; i < count; ++i) {
            labels.add(new Label(String.valueOf(i)));
        }
    }

    public void registerContentObserver(ContentObserver observer) {
        mContentObservable.registerObserver(observer);
        mSelfObserver = new SelfContentObserver(this);
    }

    public void unregisterContentObserver(ContentObserver observer) {
        mContentObservable.unregisterObserver(observer);
    }

    protected void onChange(boolean selfChange) {
        mContentObservable.dispatchChange(selfChange, null);
    }

    public ArrayList<Label> getLabels() {
        return labels;
    }


    /**
     * Cursors use this class to track changes others make to their URI.
     */
    protected static class SelfContentObserver extends ContentObserver {
        WeakReference<LabelList> mLabelList;

        public SelfContentObserver(LabelList labelList) {
            super(null);
            mLabelList = new WeakReference<LabelList>(labelList);
        }

        @Override
        public boolean deliverSelfNotifications() {
            return false;
        }

        @Override
        public void onChange(boolean selfChange) {
            LabelList labelList = mLabelList.get();
            if (labelList != null) {
                labelList.onChange(false);
            }
        }
    }

}
