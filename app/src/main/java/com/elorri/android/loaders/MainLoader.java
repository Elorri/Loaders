package com.elorri.android.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.os.CancellationSignal;
import android.support.v4.os.OperationCanceledException;

/**
 * An implementation of AsyncTaskLoader which loads a {@code List<Label>}
 * containing all installed applications on the device.
 */
public class MainLoader extends AsyncTaskLoader<LabelList> {
    final ForceLoadContentObserver mObserver;

    LabelList mLabelList;
    CancellationSignal mCancellationSignal;

    /* Runs on a worker thread */
    @Override
    public LabelList loadInBackground() {
        synchronized (this) {
            if (isLoadInBackgroundCanceled()) {
                throw new OperationCanceledException();
            }
            mCancellationSignal = new CancellationSignal();
        }
        try {
            LabelList labelList = new LabelList();
            labelList.registerContentObserver(mObserver);
            return labelList;
        } finally {
            synchronized (this) {
                mCancellationSignal = null;
            }
        }
    }

    @Override
    public void cancelLoadInBackground() {
        super.cancelLoadInBackground();

        synchronized (this) {
            if (mCancellationSignal != null) {
                mCancellationSignal.cancel();
            }
        }
    }

    /* Runs on the UI thread */
    @Override
    public void deliverResult(LabelList labelList) {
        if (isReset()) {
            // An async query came in while the loader is stopped
            return;
        }
        LabelList oldLabelList = mLabelList;
        mLabelList = labelList;

        if (isStarted()) {
            super.deliverResult(labelList);
        }
    }

    public MainLoader(Context context) {
        super(context);
        mObserver = new ForceLoadContentObserver();
    }

    /**
     * Starts an asynchronous load of the contacts list data. When the result is ready the callbacks
     * will be called on the UI thread. If a previous load has been completed and is still valid
     * the result may be passed to the callbacks immediately.
     * <p/>
     * Must be called from the UI thread
     */
    @Override
    protected void onStartLoading() {
        if (mLabelList != null) {
            deliverResult(mLabelList);
        }
        if (takeContentChanged() || mLabelList == null) {
            forceLoad();
        }
    }

    /**
     * Must be called from the UI thread
     */
    @Override
    protected void onStopLoading() {
        // Attempt to cancel the current load task if possible.
        cancelLoad();
    }

    @Override
    public void onCanceled(LabelList labelList) {
       //close cursor do any relief work here
    }

    @Override
    protected void onReset() {
        super.onReset();

        // Ensure the loader is stopped
        onStopLoading();

        if (mLabelList != null) {
            //close cursor do any relief work here
        }
        mLabelList = null;
    }

}
