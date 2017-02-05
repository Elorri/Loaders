package com.elorri.android.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of AsyncTaskLoader which loads a {@code List<Label>}
 * containing all installed applications on the device.
 */
public class MainLoader extends AsyncTaskLoader<List<Label>> {


    private List<Label> mLabels;

    public MainLoader(Context context) {
        super(context);
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
    }

    @Override
    public void setUpdateThrottle(long delayMS) {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        super.setUpdateThrottle(delayMS);
    }

    @Override
    public void onCanceled(List<Label> data) {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        super.onCanceled(data);
    }

    @Override
    public List<Label> loadInBackground() {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        int count = 30;
        mLabels = new ArrayList<>(count);
        for (int i = 0; i < count; ++i) {
            mLabels.add(new Label(String.valueOf(i)));
        }
       // mLabels.registerContentObserver(mObserver);

        //deliverResult(mLabels); //should nt be called on B thread
        //onContentChanged();
        //forceLoad();
        return mLabels;
    }

    @Override
    public void cancelLoadInBackground() {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        super.cancelLoadInBackground();
    }

    @Override
    public boolean isLoadInBackgroundCanceled() {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "" + super.isLoadInBackgroundCanceled());
        return super.isLoadInBackgroundCanceled();
    }

    @Override
    public void waitForLoader() {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        super.waitForLoader();
    }

    @Override
    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        super.dump(prefix, fd, writer, args);
    }

    @Override
    protected List<Label> onLoadInBackground() {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        return super.onLoadInBackground();
    }




    /*From Loader*/

    @Override
    public void deliverResult(List<Label> data) {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        super.deliverResult(data);
    }

    @Override
    public void deliverCancellation() {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        super.deliverCancellation();
    }

    @Override
    public void registerListener(int id, OnLoadCompleteListener<List<Label>> listener) {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "listener " + listener);
        super.registerListener(id, listener);
    }

    @Override
    public void unregisterListener(OnLoadCompleteListener<List<Label>> listener) {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "listener " + listener);
        super.unregisterListener(listener);
    }

    @Override
    public boolean isStarted() {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "" + isStarted());
        return super.isStarted();
    }

    @Override
    public boolean isAbandoned() {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "" + isAbandoned());
        return super.isAbandoned();
    }

    @Override
    public boolean isReset() {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "" + isReset());
        return super.isReset();
    }

    @Override
    protected void onStartLoading() {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        super.onStartLoading();

        if (mLabels != null) {
            deliverResult(mLabels);
        }
        if (takeContentChanged() || mLabels == null) {
            forceLoad();
        }
    }

    @Override
    public boolean cancelLoad() {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        return super.cancelLoad();
    }

    @Override
    protected boolean onCancelLoad() {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        return super.onCancelLoad();
    }

    @Override
    public void forceLoad() {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        super.forceLoad();
    }

    @Override
    protected void onForceLoad() {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        super.onForceLoad();
    }

    @Override
    protected void onReset() {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        super.onReset();
    }

    @Override
    public void stopLoading() {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        super.stopLoading();
    }

    @Override
    protected void onStopLoading() {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        super.onStopLoading();
    }

    @Override
    public void abandon() {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        super.abandon();
    }

    @Override
    protected void onAbandon() {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        super.onAbandon();
    }

    @Override
    public boolean takeContentChanged() {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        return super.takeContentChanged();
    }

    @Override
    public void commitContentChanged() {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        super.commitContentChanged();
    }

    @Override
    public void rollbackContentChanged() {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        super.rollbackContentChanged();
    }

    @Override
    public void onContentChanged() {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        super.onContentChanged();
    }

}
