package com.elorri.android.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.os.CancellationSignal;
import android.support.v4.os.OperationCanceledException;
import android.util.Log;

/**
 * An implementation of AsyncTaskLoader which loads a {@code List<Label>}
 * containing all installed applications on the device.
 */
public class MainLoader extends AsyncTaskLoader<LabelList> {
    final ForceLoadContentObserver mObserver;

    LabelList mLabelList;
    CancellationSignal mCancellationSignal;
    private boolean isLoadding;

    /* Runs on a worker thread */
    @Override
    public LabelList loadInBackground() {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "" + this + "triggered");
        synchronized (this) {
            if (isLoadInBackgroundCanceled()) {
                throw new OperationCanceledException();
            }
            mCancellationSignal = new CancellationSignal();
        }
        try {
            LabelList labelList = new LabelList();
            //labelList.registerContentObserver(mObserver);
            for (int i = 0; i < 5; i++) {
                isLoadding = true;
                longWork(1000L);
                Log.e("App", Thread.currentThread().getStackTrace()[2] + "" + this + i + " sec");
                isLoadding = false;
            }
            return labelList;
        } finally {
            synchronized (this) {
                mCancellationSignal = null;
            }
        }
    }

    private void longWork(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
            Log.e("App", Thread.currentThread().getStackTrace()[2] + "" + this + "");
            return;
        }
        LabelList oldLabelList = mLabelList;
        mLabelList = labelList;

        if (isStarted()) {
            super.deliverResult(labelList);
            Log.e("App", Thread.currentThread().getStackTrace()[2] + "" + this + " isStarted ");
        }

    }

    public MainLoader(Context context) {
        super(context);
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
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
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "" + this + "");
        if (mLabelList != null) {
            Log.e("App", Thread.currentThread().getStackTrace()[2] + "" + this + "");
            deliverResult(mLabelList);
        }
        if (takeContentChanged() || mLabelList == null) {
            Log.e("App", Thread.currentThread().getStackTrace()[2] + "" + this + "");
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
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "" + this + "triggered");
    }

    @Override
    protected void onReset() {
        super.onReset();

        // Ensure the loader is stopped
        onStopLoading();

        if (mLabelList != null) {
            Log.e("App", Thread.currentThread().getStackTrace()[2] + "" + this + "");
            //close cursor do any relief work here
        }
        mLabelList = null;
    }

}
