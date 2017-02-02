package com.elorri.android.loaders;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Elorri on 01/02/2017.
 */
public class MainFragment extends ListFragment implements LoaderManager.LoaderCallbacks<List<AppEntry>> {

    private static final String TAG = "ADP_AppListFragment";
    private static final boolean DEBUG = true;

    // We use a custom ArrayAdapter to bind application info to the ListView.
    private MainAdapter mAdapter;

    // The Loader's id (this id is specific to the ListFragment's LoaderManager)
    private static final int LOADER_ID = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.activity_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_configure_locale:
                configureLocale();
                return true;
        }
        return false;
    }

    /**
     * Notifies the Loader that a configuration change has has occurred (i.e. by
     * calling {@link Loader#onContentChanged()}).
     *
     * This feature was added so that it would be easy to see the sequence of
     * events that occurs when a content change is detected. Connect your
     * device via USB and analyze the logcat to see the sequence of methods that
     * are called as a result!
     */
    private void configureLocale() {
        Loader<AppEntry> loader = getLoaderManager().getLoader(LOADER_ID);
        if (loader != null) {
            startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mAdapter = new MainAdapter(getActivity());
        setEmptyText("No applications");
        setListAdapter(mAdapter);
        setListShown(false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        // Initialize a Loader with id '1'. If the Loader with this id already
        // exists, then the LoaderManager will reuse the existing Loader.
        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<List<AppEntry>> onCreateLoader(int id, Bundle args) {
        return new MainLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<AppEntry>> loader, List<AppEntry> data) {
        mAdapter.setData(data);

        if (isResumed()) {
            setListShown(true);
        } else {
            setListShownNoAnimation(true);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<AppEntry>> loader) {
        mAdapter.setData(null);
    }
}