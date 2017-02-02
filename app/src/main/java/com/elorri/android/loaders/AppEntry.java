package com.elorri.android.loaders;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;

import java.io.File;

/**
 * This class holds the per-item data in our {@link MainLoader}.
 */
public class AppEntry {





    private final MainLoader mLoader;
    private final ApplicationInfo mInfo;
    private final File mApkFile;
    private String mLabel;
    private Drawable mIcon;
    private boolean mMounted;

    public AppEntry(MainLoader loader, ApplicationInfo info) {
        mLoader = loader;
        mInfo = info;
        mApkFile = new File(info.sourceDir);
    }

    public ApplicationInfo getApplicationInfo() {
        return mInfo;
    }



    @Override
    public String toString() {
        return mLabel;
    }

    void loadLabel(Context context) {
        if (mLabel == null || !mMounted) {
            if (!mApkFile.exists()) {
                mMounted = false;
                mLabel = mInfo.packageName;
            } else {
                mMounted = true;
                CharSequence label = mInfo.loadLabel(context.getPackageManager());
                mLabel = label != null ? label.toString() : mInfo.packageName;
            }
        }
    }

    public void setLabel(String label) {
        this.mLabel = label;
    }

    public void setIcon(Drawable icon) {
        this.mIcon = icon;
    }

    public String getLabel() {
        return mLabel;
    }

    public Drawable getIcon() {
        if (mIcon == null) {
            if (mApkFile.exists()) {
              //  mIcon = mInfo.loadIcon(mLoader.mPackageManager);
                return mIcon;
            } else {
                mMounted = false;
            }
        } else if (!mMounted) {
            // If the app wasn't mounted but is now mounted, reload its icon.
            if (mApkFile.exists()) {
                mMounted = true;
               // mIcon = mInfo.loadIcon(mLoader.mPackageManager);
                return mIcon;
            }
        } else {
            return mIcon;
        }

        return mLoader.getContext().getResources()
                .getDrawable(android.R.drawable.sym_def_app_icon);
    }
}