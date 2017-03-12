package com.elorri.android.loaders;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class Fragment2 extends Fragment implements LoaderManager.LoaderCallbacks<LabelList> {

    // We use a custom ArrayAdapter to bind application info to the ListView.
    private MainAdapter mAdapter;

    // The Loader's id (this id is specific to the ListFragment's LoaderManager)
    private static final int LOADER_ID = 1;
    private Context mContext;
    private MainLoader mLoader;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container);
        Button button = (Button) view.findViewById(R.id.start_loader);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLoader != null) {
                    Log.e("App", Thread.currentThread().getStackTrace()[2] + "" + this + "triggered");
                    mLoader.onContentChanged();
                } else {
                    getLoaderManager().initLoader(LOADER_ID, null, Fragment2.this);
                }

            }
        });
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MainAdapter(mContext, new ArrayList<Label>());
        recyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Initialize a Loader with id '1'. If the Loader with this id already
        // exists, then the LoaderManager will reuse the existing Loader.
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "" + this + "init loader 1 id " + LOADER_ID);
        getLoaderManager().initLoader(LOADER_ID, null, this); //Will trigger
        // MainFragment.onCreateLoader and MainLoader.onStartLoading
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "" + this + "init loader 1 id " + LOADER_ID);
        getLoaderManager().initLoader(LOADER_ID, null, this);
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "" + this + "init loader 1 id " + LOADER_ID);
        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<LabelList> onCreateLoader(int id, Bundle args) {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        mLoader = new MainLoader(getActivity());
        return mLoader;
        // return new CursorLoader();
    }


    @Override
    public void onLoadFinished(Loader<LabelList> loader, LabelList data) {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        mAdapter.swapData(data.getLabels());
    }

    @Override
    public void onLoaderReset(Loader<LabelList> loader) {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        mAdapter.swapData(null);
    }
}
